package com.qg24.softwareplatform.mapper;

import com.qg24.softwareplatform.po.entity.UserHardware;
import com.qg24.softwareplatform.po.entity.UserSoftwareLicense;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface HardwareMapper {

    //根据用户ID查询用户的硬件指纹信息
    @Select("select * from user_hardware where user_id = #{userId}")
    public List<UserHardware> selectFingerprintsByUserId(String userId);

//    //上传用户指纹信息
//    @Insert("insert into user_hardware (user_id, fingerprint, hardware_name) values (#{userId}, #{fingerprint}, #{hardwareName})")
//    public int insertFingerprint(UserHardware userHardware);
//
//    //根据主键id查询该指纹信息
//    @Select("select * from user_hardware where user_hardware_id = #{useHardwareId}")
//    public UserHardware selectByUseHardwareId(int useHardwareId);
//
//    //根据用户id和指纹信息查找该指纹信息是否至少绑定一个授权许可
//    @Select("select * from user_software_auth where user_id = #{userId} And fingerprint = #{fingerprint}")
//    public List<UserSoftwareLicense> selectUserSoftwareLicensesByUserIdAndFingerprint(@Param("userId") String userId, @Param("fingerprint") String fingerprint);
//
//    //根据主键id删除用户指纹信息
//    @Delete("delete from user_hardware where user_hardware_id = #{useHardwareId}")
//    public int deleteUserHardwareByUseHardwareId(int useHardwareId);


}
