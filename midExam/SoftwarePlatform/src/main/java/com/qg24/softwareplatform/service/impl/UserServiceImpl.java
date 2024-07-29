package com.qg24.softwareplatform.service.impl;

import com.qg24.softwareplatform.mapper.UserMapper;
import com.qg24.softwareplatform.po.dto.HomePageShowSoftwareDTO;
import com.qg24.softwareplatform.po.dto.NewUserInfoDTO;
import com.qg24.softwareplatform.po.dto.ShowPersonalSoftwareInfoDTO;
import com.qg24.softwareplatform.po.entity.Order;
import com.qg24.softwareplatform.po.entity.UserSoftwareAuth;
import com.qg24.softwareplatform.po.result.Result;
import com.qg24.softwareplatform.po.vo.SimpleSoftwareVO;
import com.qg24.softwareplatform.po.vo.UserApplicationRecordVO;
import com.qg24.softwareplatform.po.vo.UserBuySoftwareVO;
import com.qg24.softwareplatform.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.qg24.softwareplatform.util.AliOssUtil;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AliOssUtil aliOssUtil;
    @Override
    public int getTotalSoftware(String userId) {
        return userMapper.getTotalNumber(userId);
    }

    @Override
    public List<SimpleSoftwareVO> getHomePageShowSoftware(ShowPersonalSoftwareInfoDTO dto) {
        return userMapper.getUserSoftware(dto.getUserId(),dto.getPageSize(),(dto.getPage()-1)* dto.getPageSize());
    }

    @Override
    public List<UserApplicationRecordVO> getApplicationRecord(String userId) {
        List<UserApplicationRecordVO> list = userMapper.getApplicationRecord(userId);
        for(int i=0;i<list.size();i++){
            //将tags字符串转为list
            list.get(i).StringToTags();
        }
        return list;
    }

    @Override
    public List<UserBuySoftwareVO> getAvailableSoftware(String userId) {
        List<UserSoftwareAuth> list = userMapper.getAvailableSoftware(userId);//获得授权过的软件
        List<UserBuySoftwareVO> listBuy = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            UserBuySoftwareVO userBuySoftwareVO = new UserBuySoftwareVO();
            BeanUtils.copyProperties(list.get(i),userBuySoftwareVO);//将usersoftwareauth对象封入userbuysoftwarevo内
            userBuySoftwareVO.setSoftwareName(userMapper.getSoftwareName(list.get(i).getSoftwareId()));//获取软件名
            listBuy.add(userBuySoftwareVO);
        }
        return listBuy;
    }

    @Override
    public boolean uploadNewUserInfo(NewUserInfoDTO dto) throws IOException {
        String url = "";
        MultipartFile headImage = dto.getHeadImage();
        //获取当前时间
        LocalDateTime localDatetime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String time = localDatetime.format(formatter);
        dto.setUpdateTime(time);
        if(!headImage.isEmpty()){
            String extension = Objects.requireNonNull(headImage.getOriginalFilename()).substring(headImage.getOriginalFilename().lastIndexOf("."));
            String fileName = UUID.randomUUID() + extension;
            byte[] bytes = headImage.getBytes();
            url = aliOssUtil.upload(bytes, fileName);//上传文件,获得url
            dto.setImage(url);
        }
        if(userMapper.updateUserInfo(dto)==1){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<Order> showUserOrder(String userId) {
        // 根据用户id查看order表格
        List<Order> orderList = userMapper.selectOrderByUserId(userId);
        return orderList;
    }
}
