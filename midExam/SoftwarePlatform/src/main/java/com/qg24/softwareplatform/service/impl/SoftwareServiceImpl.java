package com.qg24.softwareplatform.service.impl;

import com.qg24.softwareplatform.mapper.SoftwareMapper;
import com.qg24.softwareplatform.po.dto.HomePageShowSoftwareDTO;
import com.qg24.softwareplatform.po.entity.Software;
import com.qg24.softwareplatform.po.vo.SimpleSoftwareVO;
import com.qg24.softwareplatform.service.SoftwareService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SoftwareServiceImpl implements SoftwareService {
    @Autowired
    SoftwareMapper softwareMapper;
    @Override
    public List<SimpleSoftwareVO> homePageShowSoftware(HomePageShowSoftwareDTO homePageShowSoftwareDTO) {
        //DOTO
        return new ArrayList<>();
    }

    @Override
    public List<SimpleSoftwareVO> softwareRanking() {
        List<Software> softwareList = softwareMapper.querySoftwareRankByDownload();
        List<SimpleSoftwareVO> softwareVOList = new ArrayList<>();
        for (Software software : softwareList) {
            SimpleSoftwareVO softwareVO = new SimpleSoftwareVO();
            BeanUtils.copyProperties(software, softwareVO);
            softwareVOList.add(softwareVO);
        }
        return softwareVOList;
    }
}
