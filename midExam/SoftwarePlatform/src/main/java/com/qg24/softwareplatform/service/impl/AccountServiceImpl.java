package com.qg24.softwareplatform.service.impl;

import com.qg24.softwareplatform.mapper.AccountMapper;
import com.qg24.softwareplatform.po.dto.LoginDTO;
import com.qg24.softwareplatform.po.dto.RegisterDTO;
import com.qg24.softwareplatform.po.dto.UpdatePasswordDTO;
import com.qg24.softwareplatform.po.dto.UpdateUserInfoDTO;
import com.qg24.softwareplatform.po.entity.User;
import com.qg24.softwareplatform.po.entity.UserAndToken;
import com.qg24.softwareplatform.po.vo.HomePageUserInfoVO;
import com.qg24.softwareplatform.properties.MailProperties;
import com.qg24.softwareplatform.service.AccountService;
import com.qg24.softwareplatform.util.AliOssUtil;
import com.qg24.softwareplatform.util.JwtUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private MailSender mailSender;

    @Autowired
    private MailProperties mailProperties;
    @Autowired
    private AliOssUtil aliOssUtil;

    @Override
    public UserAndToken login(LoginDTO loginDTO) {
        Map<String, Object> claims = new HashMap<>();
        UserAndToken userAndToken = new UserAndToken();
        User user = null;
        // TODO 常量类设置 0 1
        if (loginDTO.getRole() == 1) {
            user = accountMapper.selectByAccountAndPassword(loginDTO);
        } else if (loginDTO.getRole() == 0) {
            user = accountMapper.selectByUserIdAndPassword(loginDTO);
        }

        // 判断是否根据DTO找到用户信息
        if (Objects.isNull(user)) {
            return null;
        }

        // 找到用户信息, 构建JWT
        String userId = user.getUserId();
        claims.put("userId", userId);
        String token = JwtUtils.generateToken(claims);

        userAndToken.setToken(token);
        userAndToken.setUserId(userId);
        return userAndToken;
    }

    @Override
    public int register(RegisterDTO registerDTO) {
        // 首先验证是否是已有账户
        User user = accountMapper.selectByEmail(registerDTO);
        if (!Objects.isNull(user)) {
            return -1;
        }

        // 新邮箱, 进行邮箱验证
        String code = redisTemplate.opsForValue().get("CodeOf" + registerDTO.getEmail());
        if (code != null && code.equals(registerDTO.getVerificationCode())) {
            // 认证通过, 先给用户生成新ID
            User newUser = new User();
            newUser.setUserId(UUID.randomUUID().toString());
            BeanUtils.copyProperties(registerDTO, newUser);

            // 注册, 插入用户表, 将redis内的邮箱键值对删除
            accountMapper.registerNewUser(newUser);
            redisTemplate.delete("CodeOf" + registerDTO.getEmail());
            return 1;
        } else if (code != null) {
            // 认证不通过
            return 0;
        }
        return -1;
    }

    @Override
    public void sendVerificationCodeAndSaveCode(String to) {
        // 初始化消息体
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        // 生成随机验证码
        String code = generateVerificationCode();

        // 存入邮箱和验证码的键值对 三分钟内有效
        redisTemplate.opsForValue().set(
                "CodeOf" + to,
                code,
                3,
                TimeUnit.MINUTES
        );

        mailMessage.setFrom(mailProperties.getUsername());
        mailMessage.setTo(to);
        mailMessage.setSubject("Verification Code");
        mailMessage.setText("Your verification code is: " + code);

        mailSender.send(mailMessage);
    }

    @Override
    public HomePageUserInfoVO getHomePageUserInfo(String userId) {
        User user = accountMapper.selectById(userId);
        if(Objects.isNull(user)){
            return null;
        }else{
            HomePageUserInfoVO homePageInfoVo = new HomePageUserInfoVO();
            BeanUtils.copyProperties(user,homePageInfoVo);
            return homePageInfoVo;
        }
    }

    @Override
    public void updatePassword(UpdatePasswordDTO updatePasswordDTO) {
        accountMapper.updatePassword(updatePasswordDTO);
    }

    @Override
    public void updateUserInfo(UpdateUserInfoDTO updateUserInfoDTO) throws IOException {
        // 取得头像文件, 保存至云端获取url
        MultipartFile image = updateUserInfoDTO.getImage();
        String extension = Objects.requireNonNull(
                image.getOriginalFilename()).substring(image.getOriginalFilename().lastIndexOf(".")
        );
        String fileName = UUID.randomUUID() + extension;
        byte[] bytes = updateUserInfoDTO.getImage().getBytes();
        String url = aliOssUtil.upload(bytes, fileName);

        User user = new User();
        BeanUtils.copyProperties(updateUserInfoDTO, user);
        user.setImage(url);

        accountMapper.updateUserInfo(user);
    }

    public String generateVerificationCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }

}
