package com.qg24.softwareplatform.service.impl;

import com.qg24.softwareplatform.mapper.AdminMapper;
import com.qg24.softwareplatform.po.dto.UpdateSoftwareLatestInfoDTO;
import com.qg24.softwareplatform.po.entity.Software;
import com.qg24.softwareplatform.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    //管理员更新软件基本信息
    @Override
    public boolean updateSoftwareBasicInfo(Software software) {
        software.TagsToString();
        if(adminMapper.updateSoftWareBasicInfo(software)==1){
            return true;
        }else{
            return false;
        }
    }


    //管理员更新最新软件信息
    @Override
    public boolean updateSoftwareLatestInfo(UpdateSoftwareLatestInfoDTO softwareLatestInfoDTO) {
        if(adminMapper.updateSoftwareLatestInfo(softwareLatestInfoDTO)==1){
            return true;
        }else{
            return false;
        }
    }
}
