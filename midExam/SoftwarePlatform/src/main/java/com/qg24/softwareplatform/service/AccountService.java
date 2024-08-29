package com.qg24.softwareplatform.service;

import com.qg24.softwareplatform.po.dto.LoginDTO;
import com.qg24.softwareplatform.po.dto.RegisterDTO;
import com.qg24.softwareplatform.po.dto.UpdatePasswordDTO;
import com.qg24.softwareplatform.po.dto.UpdateUserInfoDTO;
import com.qg24.softwareplatform.po.entity.UserAndToken;
import com.qg24.softwareplatform.po.vo.HomePageUserInfoVO;

import java.io.IOException;

public interface AccountService {
    UserAndToken login(LoginDTO loginDTO);

    int register(RegisterDTO registerDTO);

    void sendVerificationCodeAndSaveCode(String email);

    HomePageUserInfoVO getHomePageUserInfo(String userId);

    void updatePassword(UpdatePasswordDTO updatePasswordDTO);

    void updateUserInfo(UpdateUserInfoDTO updateUserInfoDTO) throws IOException;
}
