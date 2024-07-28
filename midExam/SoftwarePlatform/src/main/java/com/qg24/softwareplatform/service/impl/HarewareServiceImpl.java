package com.qg24.softwareplatform.service.impl;

import com.qg24.softwareplatform.mapper.HardwareMapper;
import com.qg24.softwareplatform.po.entity.UserHardware;
import com.qg24.softwareplatform.po.entity.UserSoftwareLicense;
import com.qg24.softwareplatform.po.vo.FingerprintVO;
import com.qg24.softwareplatform.service.HardwareService;
import com.qg24.softwareplatform.util.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class HarewareServiceImpl implements HardwareService {

    @Autowired
    private HardwareMapper hardwareMapper;

    /**
     * 根据用户id查询用户的指纹信息
     * @param userId
     * @return
     */
    @Override
    public List<FingerprintVO> selectFingerprintsByUserId(String userId) {
        //查询
        List<UserHardware> userHardwares = hardwareMapper.selectFingerprintsByUserId(userId);
        //需要返回的内容
        List<FingerprintVO> list = new ArrayList<>();
        //若长度为0，则表示该用户还没有录入指纹信息
        if(userHardwares.size() != 0){
            for (UserHardware userHardware : userHardwares) {
                FingerprintVO fingerprintVO = new FingerprintVO();
                //返回VO的需要的数据
                fingerprintVO.setUserFingerprintId(userHardware.getUserHardwareId());
                fingerprintVO.setHardwareName(userHardware.getHardwareName());
                list.add(fingerprintVO);
            }
        }
        //直接返回空集合
        return null;
    }

    /**
     * 用户上传新的硬件指纹信息
     * @param userHardware
     * @return
     */
    @Override
    public int insertFingerprint(UserHardware userHardware) {
        //用不用限制说用户有多少个指纹就不能再上传了

        //添加指纹
        int i = hardwareMapper.insertFingerprint(userHardware);
        return i;
    }

    /**
     * 用户删除指纹信息
     * @param useHardwareId
     * @return
     */
    @Override
    public boolean deleteFingerprintByUseHardwareId(int useHardwareId) {
        //查询到该指纹信息
        UserHardware userHardware = hardwareMapper.selectByUseHardwareId(useHardwareId);
        //查看用户是否有绑定一个授权许可
        List<UserSoftwareLicense> userSoftwareLicenses = hardwareMapper.selectUserSoftwareLicensesByUserIdAndFingerprint(userHardware.getUserId(), userHardware.getFingerprint());
        if (userSoftwareLicenses.size() == 0){
            int i = hardwareMapper.deleteUserHardwareByUseHardwareId(useHardwareId);
            return true; //删除成功
        }else {
            //用户有绑定授权许可
            for (UserSoftwareLicense userSoftwareLicense : userSoftwareLicenses) {
                //比较时间判断是否这个许可已经过期
                //只要出来一个是还没有到过期时间，就直接退出循环，不能删除
                if(!TimeUtils.parseTime(userSoftwareLicense.getExpiredTime()).isBefore(LocalDateTime.now())){
                    return false; //不能删除
                }
            }
            //所有的授权许可都已经过期，可以删除该绑定的指纹信息
            int i = hardwareMapper.deleteUserHardwareByUseHardwareId(useHardwareId);
            return true; //删除成功
        }
    }


}
