package com.qg24.softwareplatform.mapper;

import com.qg24.softwareplatform.po.dto.NewUserInfoDTO;
import com.qg24.softwareplatform.po.entity.Order;
import com.qg24.softwareplatform.po.entity.UserSoftwareAuth;
import com.qg24.softwareplatform.po.vo.SimpleSoftwareVO;
import com.qg24.softwareplatform.po.vo.UserApplicationRecordVO;
import com.qg24.softwareplatform.po.vo.UserBuySoftwareVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select count(*) from software where user_id=#{userId}")
    int getTotalNumber(String userId);//获取总软件数

    List<SimpleSoftwareVO> getUserSoftware(@Param("userId") String userId, @Param("limit") int limit,@Param("offset") int offset);//分页查询用户开发的软件

    @Select("select * from software_info_temp where user_id = #{userId}")
    @Result(property = "tagsString", column = "tags")
    List<UserApplicationRecordVO> getApplicationRecord(String userId);//获取用户申请记录

    //查看用户购买过的软件
    List<UserBuySoftwareVO> getAvailableSoftware(String userId);//查询用户授权过的软件

    @Select("select software_name from software where software_id=#{softwareId}")
    String getSoftwareName(int softwareId);//获得软件名

    int updateUserInfo(NewUserInfoDTO newUserInfoDTO);//更新用户信息

    @Select("select * from order_tb where user_id = #{userId}")
    List<Order> selectOrderByUserId(String userId);
}
