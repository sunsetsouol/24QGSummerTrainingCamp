package com.qg24.softwareplatform.service.impl;

import com.qg24.softwareplatform.mapper.AuthorizationMapper;
import com.qg24.softwareplatform.po.dto.AuthSoftwareDTO;
import com.qg24.softwareplatform.po.dto.CheckAuthDTO;
import com.qg24.softwareplatform.po.dto.PurchaseDTO;
import com.qg24.softwareplatform.po.entity.Order;
import com.qg24.softwareplatform.po.entity.SoftwareVersionDownload;
import com.qg24.softwareplatform.po.entity.UserSoftwareAuth;
import com.qg24.softwareplatform.po.entity.UserSoftwareLicense;
import com.qg24.softwareplatform.po.vo.DownloadUrlsVO;
import com.qg24.softwareplatform.service.AuthorizationService;
import com.qg24.softwareplatform.util.TimeUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    public boolean purchaseAuth(PurchaseDTO purchaseDTO) {
        //把软件列表转化为字符串
        String s = purchaseDTO.getAuthSoftwareDTOList().toString();

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
        authorizationMapper.addUserSoftwareLicense(userSoftwareLicense);

        //添加用户软件授权表
        List<AuthSoftwareDTO> authSoftwareDTOList = purchaseDTO.getAuthSoftwareDTOList();
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
    public String checkAuth(CheckAuthDTO checkAuthDTO) {
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
    public DownloadUrlsVO getDownloadUrls(CheckAuthDTO checkAuthDTO) {
        SoftwareVersionDownload softVersionDownload = new SoftwareVersionDownload();
        BeanUtils.copyProperties(checkAuthDTO, softVersionDownload);
        //查找下载地址
        SoftwareVersionDownload softwareVersionDownload = authorizationMapper.selectByThreeInfo(softVersionDownload);
        //返回地址
        DownloadUrlsVO downloadUrlsVO = new DownloadUrlsVO();
        BeanUtils.copyProperties(softwareVersionDownload, downloadUrlsVO);
        return downloadUrlsVO;
    }


}
