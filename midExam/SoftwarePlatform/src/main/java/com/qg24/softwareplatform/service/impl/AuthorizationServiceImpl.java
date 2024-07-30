package com.qg24.softwareplatform.service.impl;

import com.alibaba.fastjson2.JSON;
import com.qg24.softwareplatform.mapper.AuthorizationMapper;
import com.qg24.softwareplatform.po.dto.*;
import com.qg24.softwareplatform.po.entity.Order;
import com.qg24.softwareplatform.po.entity.SoftwareVersionDownload;
import com.qg24.softwareplatform.po.entity.UserSoftwareAuth;
import com.qg24.softwareplatform.po.entity.UserSoftwareLicense;
import com.qg24.softwareplatform.po.vo.DownloadUrlsVO;
import com.qg24.softwareplatform.po.vo.ShowLicenseVO;
import com.qg24.softwareplatform.service.AuthorizationService;
import com.qg24.softwareplatform.util.TimeUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    @Autowired
    private AuthorizationMapper authorizationMapper;

    /**
     * 用户购买授权
     * @param purchaseDTO
     * @return
     */
    @Override
    public boolean purchaseAuth(@RequestBody PurchaseDTO purchaseDTO) {
        //把软件列表转化为字符串
        String s = JSON.toJSONString(purchaseDTO.getSoftwareList());

        //添加订单信息先
        Order order = new Order();
        order.setPrice(purchaseDTO.getTotalPrize());
        order.setUserId(purchaseDTO.getUserId());
        order.setSoftwareInfo(s);
        authorizationMapper.addOrder(order);

        //添加用户购买的新的许可
        UserSoftwareLicense userSoftwareLicense = new UserSoftwareLicense();
        BeanUtils.copyProperties(purchaseDTO, userSoftwareLicense);
        userSoftwareLicense.setSoftwareList(s);

        LocalDate currentDate = LocalDate.now();
        LocalDate futureDate = currentDate.plusYears(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
        String formattedDate = futureDate.format(formatter);
        userSoftwareLicense.setExpiredTime(formattedDate);
        // TODO 实现将数据保存文件并加密上上传到云端服务器，返回url
        System.out.println(userSoftwareLicense);

        String licenseUrl = "www.baidu.com";
        userSoftwareLicense.setLicenseUrl(licenseUrl);



        authorizationMapper.addUserSoftwareLicense(userSoftwareLicense);


        //添加用户软件授权表
        List<AuthSoftwareDTO> authSoftwareDTOList = purchaseDTO.getSoftwareList();
        //遍历购买的每一个软件
        for (AuthSoftwareDTO authSoftwareDTO : authSoftwareDTOList) {
            //一条一条添加到用户软件授权表
            UserSoftwareAuth userSoftwareAuth = new UserSoftwareAuth();
            userSoftwareAuth.setUserId(purchaseDTO.getUserId());
            userSoftwareAuth.setSoftwareId(authSoftwareDTO.getSoftwareId());
            userSoftwareAuth.setVersionType(authSoftwareDTO.getVersionType());
            authorizationMapper.addUserSoftwareAuth(userSoftwareAuth);
        }
        //购买完成
        return true;
    }

    /**
     * 检验授权
     * @param checkAuthDTO
     * @return
     */
    @Override
    public String checkAuth(@RequestBody CheckAuthDTO checkAuthDTO) {
        //搜索用户软件授权表
        UserSoftwareAuth userSoftwareAuth = new UserSoftwareAuth();
        BeanUtils.copyProperties(checkAuthDTO, userSoftwareAuth);
        UserSoftwareAuth userSoftwareAuth1 = authorizationMapper.selectByAllInfo(userSoftwareAuth);
        if(userSoftwareAuth1 == null){
            return "none"; //没有此授权信息
        }else {
            //比对是否过期
            if (TimeUtils.parseTime(userSoftwareAuth1.getExpiredTime()).isBefore(LocalDateTime.now())){
                return "outdated"; //授权已过期
            }
            return "success"; //可以下载
        }
    }

    /**
     * 传回下载地址
     * @param checkAuthDTO
     * @return
     */
    @Override
    public DownloadUrlsVO getDownloadUrls(@RequestBody CheckAuthDTO checkAuthDTO) {
        SoftwareVersionDownload softVersionDownload = new SoftwareVersionDownload();
        BeanUtils.copyProperties(checkAuthDTO, softVersionDownload);
        //查找下载地址
        SoftwareVersionDownload softwareVersionDownload = authorizationMapper.selectByThreeInfo(softVersionDownload);
        //返回地址
        if(softwareVersionDownload == null){
            return null;
        }else {
            DownloadUrlsVO downloadUrlsVO = new DownloadUrlsVO();
            BeanUtils.copyProperties(softwareVersionDownload, downloadUrlsVO);
            return downloadUrlsVO;
        }
    }

    /**
     * (非前端)本地软件发送信息进行服务器比对接口
     * @param onlineVerificationDTO
     * @return
     */
    @Override
    public boolean onlineVertification(OnlineVerificationDTO onlineVerificationDTO) {
        List<UserSoftwareLicense> userSoftwareLicenses = authorizationMapper.selectByFingerprint(onlineVerificationDTO.getFingerprint());
        //遍历每一个许可文件
        for (UserSoftwareLicense userSoftwareLicense : userSoftwareLicenses) {
            //先判断这条许可有无过期
            if(TimeUtils.parseTime(userSoftwareLicense.getExpiredTime()).isAfter(LocalDateTime.now())){
                //没过期，获取当时买的软件的授权信息
                List<SoftwareSimpleInfoDTO> array = JSON.parseArray(userSoftwareLicense.getSoftwareList(), SoftwareSimpleInfoDTO.class);
                for (SoftwareSimpleInfoDTO softwareSimpleInfoDTO : array) {
                        //判断是否有这个信息匹配
                    if(onlineVerificationDTO.getSoftwareName().equals(softwareSimpleInfoDTO.getSoftwareName())
                    && onlineVerificationDTO.getVersionType() == (softwareSimpleInfoDTO.getVersionType())) {
                        //两个信息都匹配则为成功
                        return true;
                    }
                }
            }
        }
        return false; //失败
    }

    /**
     *  用户查看自己购买的授权许可
     * @param userId
     * @return
     */
    @Override
    public List<ShowLicenseVO> getLicense(String userId) {
        // 根据用户id查找用户授权许可表
        List<UserSoftwareLicense> userSoftwareLicenseList = authorizationMapper.selectLicenseByUserId(userId);
        List<ShowLicenseVO> showLicenseVOList = new ArrayList<>();

        // 遍历每条数据
        for (UserSoftwareLicense userSoftwareLicense : userSoftwareLicenseList) {
            ShowLicenseVO showLicenseVO = new ShowLicenseVO();
            // 将entity包装成VO类
            BeanUtils.copyProperties(userSoftwareLicense, showLicenseVO);
            String softwareList = userSoftwareLicense.getSoftwareList();
            // 将字符串形式转化为List集合
            List<AuthSoftwareDTO> softwareDTOList = JSON.parseArray(softwareList, AuthSoftwareDTO.class);
            // 进行包装
            showLicenseVO.setSoftwareList(softwareDTOList);
            // 将这个对象封装到集合中
            showLicenseVOList.add(showLicenseVO);
        }
        return showLicenseVOList;
    }


}
