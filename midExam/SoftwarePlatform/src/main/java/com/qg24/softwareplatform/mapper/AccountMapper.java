package com.qg24.softwareplatform.mapper;

import com.qg24.softwareplatform.po.dto.LoginDTO;
import com.qg24.softwareplatform.po.dto.RegisterDTO;
import com.qg24.softwareplatform.po.dto.UpdatePasswordDTO;
import com.qg24.softwareplatform.po.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface AccountMapper {

    /**
     * 用户登录使用的登陆mapper
     * @return User对象
     */
    @Select("select * from user where email = #{email} and password = #{password}")
    User selectByAccountAndPassword(LoginDTO loginDTO);

    /**
     * 管理员登陆使用的登陆mapper
     * @return User对象
     */
    @Select("select * from user where user_id = #{userId} and password = #{password}")
    User selectByUserIdAndPassword(LoginDTO loginDTO);

    /**
     * 注册防重
     * @return User对象
     */
    @Select("select * from user where email = #{email}")
    User selectByEmail(RegisterDTO registerDTO);

    /**
     * 注册新用户
     * @param newUser 新用户对象
     */
    @Insert("insert into user (user_id, password, email, create_time, update_time) VALUES " +
            "(#{userId}, #{password}, #{email}, #{createTime}, #{updateTime}) ")
    void registerNewUser(User newUser);

    /**
     * 根据ID查询用户
     * @param userId 用户id
     * @return 用户对象
     */
    @Select("select * from user where user_id = #{userId}")
    User selectById(String userId);

    /**
     * 更新用户密码信息
     */
    @Update("update user set password = #{password} where email = #{email}")
    void updatePassword(UpdatePasswordDTO updatePasswordDTO);

    /**
     * 更新用户信息
     */
    void updateUserInfo(User user);
}
