package com.qg24.softwareplatform.service.impl;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.qg24.softwareplatform.mapper.AuthorizationMapper;
import com.qg24.softwareplatform.po.dto.AuthSoftwareDTO;
import com.qg24.softwareplatform.po.dto.PurchaseDTO;
import com.qg24.softwareplatform.po.entity.Order;
import com.qg24.softwareplatform.po.entity.UserSoftwareAuth;
import com.qg24.softwareplatform.po.entity.UserSoftwareLicense;
import com.qg24.softwareplatform.service.AuthorizationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    @Autowired
    private AuthorizationMapper authorizationMapper;

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

        return true;
    }
}
