package com.qg24.softwareplatform.mapper;

import com.qg24.softwareplatform.po.entity.UserSoftwareAuth;
import com.qg24.softwareplatform.po.vo.SimpleSoftwareVO;
import com.qg24.softwareplatform.po.vo.UserApplicationRecordVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select count(*) from software where user_id=#{userId}")
    int getTotalNumber(String userId);//获取总软件数

    List<SimpleSoftwareVO> getUserSoftware(String userId,int limit,int offset);//分页查询用户开发的软件

    List<UserApplicationRecordVO> getApplicationRecord(String userId);//获取用户申请记录

    @Select("select * from user_software_auth where user_id=#{userId}")
    List<UserSoftwareAuth> getAvailableSoftware(String userId);//查询用户授权过的软件

    @Select("select software_name from software where software_id=#{softwareId}")
    String getSoftwareName(int softwareId);//获得软件名
}
