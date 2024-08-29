package com.qg24.softwareplatform.mapper;

import com.qg24.softwareplatform.po.entity.Order;
import com.qg24.softwareplatform.po.entity.SoftwareVersionDownload;
import com.qg24.softwareplatform.po.entity.UserSoftwareAuth;
import com.qg24.softwareplatform.po.entity.UserSoftwareLicense;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AuthorizationMapper {

    //添加订单
    @Insert("insert into order_tb (user_id, software_info, price) values (#{userId}, #{softwareInfo}, #{price})")
    public void addOrder (Order order);

    //添加用户新的购买许可
    @Insert("insert into user_software_license (user_id, software_list, fingerprint, license_url) values (#{userId}, #{softwareList}, #{fingerprint}, #{licenseUrl})")
    public void addUserSoftwareLicense(UserSoftwareLicense usersoftwareLicense);

    //添加用户软件授权表
    @Insert("insert into user_software_auth (user_id, software_id, version_type) values (#{userId}, #{softwareId}, #{versionType})")
    public void addUserSoftwareAuth(UserSoftwareAuth userSoftwareAuth);


    //搜索是否有授权信息
    @Select("select * from user_software_auth where user_id = #{userId} And software_id = #{softwareId} And version_type = #{versionType}")
    public UserSoftwareAuth selectByAllInfo(UserSoftwareAuth userSoftwareAuth);


    //获取软件的三个下载地址
    @Select("select * from software_version_download where software_id = #{softwareId} And version_type = #{versionType} And version = #{version}")
    public SoftwareVersionDownload selectByThreeInfo(SoftwareVersionDownload softVersionDownload);

    //根据指纹信息查询用户购买的许可
    @Select("select * from user_software_license where fingerprint = #{fingerprint}")
    public List<UserSoftwareLicense> selectByFingerprint(String fingerprint);


    // 根据用户id查找用户软件授权许可表
    @Select("select * from user_software_license where user_id = #{userId}")
    List<UserSoftwareLicense> selectLicenseByUserId(String userId);
}
