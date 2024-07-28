package com.qg24.softwareplatform.service;

import com.qg24.softwareplatform.po.entity.UserHardware;
import com.qg24.softwareplatform.po.vo.FingerprintVO;

import java.util.List;

public interface HardwareService {

    //根据用户ID查看用户指纹信息
    public List<FingerprintVO> selectFingerprintsByUserId(String userId);

    //添加用户指纹信息
    public int insertFingerprint(UserHardware userHardware);

    //用户删除指纹信息
    public boolean deleteFingerprintByUseHardwareId(int useHardwareId);

}
