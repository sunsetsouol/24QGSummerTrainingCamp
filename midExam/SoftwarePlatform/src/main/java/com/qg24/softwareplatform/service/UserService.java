package com.qg24.softwareplatform.service;

import com.qg24.softwareplatform.po.dto.HomePageShowSoftwareDTO;
import com.qg24.softwareplatform.po.dto.UserHomePageShowSoftwareDTO;
import com.qg24.softwareplatform.po.vo.SimpleSoftwareVO;
import com.qg24.softwareplatform.po.vo.UserApplicationRecordVO;
import com.qg24.softwareplatform.po.vo.UserBuySoftwareVO;

import java.util.List;
import java.util.SimpleTimeZone;

public interface UserService {
    int getTotalSoftware(String userId);//获得用户开发的软件数(已通过的)

    List<SimpleSoftwareVO> getHomePageShowSoftware(UserHomePageShowSoftwareDTO dto);//分页查询用户开发的软件

    List<UserApplicationRecordVO> getApplicationRecord(String userId);//查询用户申请记录

    List<UserBuySoftwareVO> getAvailableSoftware(String userId);//获取用户购买过的软件
}
