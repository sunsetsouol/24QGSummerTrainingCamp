package com.qg24.softwareplatform.service.impl;

import com.qg24.softwareplatform.mapper.UserMapper;
import com.qg24.softwareplatform.po.dto.HomePageShowSoftwareDTO;
import com.qg24.softwareplatform.po.entity.UserSoftwareAuth;
import com.qg24.softwareplatform.po.result.Result;
import com.qg24.softwareplatform.po.vo.SimpleSoftwareVO;
import com.qg24.softwareplatform.po.vo.UserApplicationRecordVO;
import com.qg24.softwareplatform.po.vo.UserBuySoftwareVO;
import com.qg24.softwareplatform.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public int getTotalSoftware(String userId) {
        return userMapper.getTotalNumber(userId);
    }

    @Override
    public List<SimpleSoftwareVO> getHomePageShowSoftware(HomePageShowSoftwareDTO dto) {
        return userMapper.getUserSoftware(dto.getUserId(),dto.getPageSize(),(dto.getPage()-1)* dto.getPageSize());
    }

    @Override
    public List<UserApplicationRecordVO> getApplicationRecord(String userId) {
        return userMapper.getApplicationRecord(userId);
    }

    @Override
    public List<UserBuySoftwareVO> getAvailableSoftware(String userId) {
        List<UserSoftwareAuth> list = userMapper.getAvailableSoftware(userId);//获得用户授权过的软件
        List<UserBuySoftwareVO> listBuy = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            UserBuySoftwareVO userBuySoftwareVO = new UserBuySoftwareVO();
            BeanUtils.copyProperties(list.get(i),userBuySoftwareVO);//将usersoftwareauth对象封入userbuysoftwarevo内
            userBuySoftwareVO.setSoftwareName(userMapper.getSoftwareName(list.get(i).getSoftwareId()));//获取软件名
            listBuy.add(userBuySoftwareVO);
        }
        return listBuy;
    }
}
