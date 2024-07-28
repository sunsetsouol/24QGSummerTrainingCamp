package com.qg24.softwareplatform.service.impl;

import com.qg24.softwareplatform.mapper.SoftwareMapper;
import com.qg24.softwareplatform.po.dto.HistorySoftwareVersionDTO;
import com.qg24.softwareplatform.po.dto.HomePageShowSoftwareDTO;
import com.qg24.softwareplatform.po.dto.UploadNewSoftwareDTO;
import com.qg24.softwareplatform.po.entity.Software;
import com.qg24.softwareplatform.po.entity.SoftwareInfoTemp;
import com.qg24.softwareplatform.po.entity.SoftwareVersionDownload;
import com.qg24.softwareplatform.po.vo.DetailedSoftwareVersionTypeVO;
import com.qg24.softwareplatform.po.vo.SimpleSoftwareVO;
import com.qg24.softwareplatform.po.vo.SoftwareHistoryVersionDownloadVO;
import com.qg24.softwareplatform.service.SoftwareService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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
        //根据下载量降序排序查找下载量最多的十个软件
        List<Software> softwareList = softwareMapper.querySoftwareRankByDownload();
        List<SimpleSoftwareVO> softwareVOList = new ArrayList<>();
        for (Software software : softwareList) {
            //进行entity包装成vo
            SimpleSoftwareVO softwareVO = new SimpleSoftwareVO();
            BeanUtils.copyProperties(software, softwareVO);
            softwareVOList.add(softwareVO);
        }
        return softwareVOList;
    }

    @Override
    public Software basicSoftwareInfo(int softwareId) {
        //根据软件id查找软件的基本信息
        Software software = softwareMapper.querySoftwareInfoBySoftId(softwareId);
        return software;
    }

    @Override
    public List<DetailedSoftwareVersionTypeVO> detailedSoftwareInfo(int softwareId) {
        List<DetailedSoftwareVersionTypeVO> detailedSoftwareVersionTypeVOList = new ArrayList<>();
        //根据软件id 查找最新普通版本的详细软件信息
        SoftwareVersionDownload ordinarySoftwareVersionInfo = softwareMapper.queryLastestOrdinaryDetaliedSoftware(softwareId);
        if (ordinarySoftwareVersionInfo != null) {
            //查找为非空则进行entity类包装为vo类
            DetailedSoftwareVersionTypeVO detailedSoftwareVersionTypeVO = new DetailedSoftwareVersionTypeVO();
            BeanUtils.copyProperties(ordinarySoftwareVersionInfo, detailedSoftwareVersionTypeVO);
            //添加到list集合中
            detailedSoftwareVersionTypeVOList.add(detailedSoftwareVersionTypeVO);
        }
        //根据软件id 查找最新专业版本的详细软件信息
        SoftwareVersionDownload professionalSoftwareVersionInfo = softwareMapper.queryLastestProfessionalDetaliedSoftware(softwareId);
        if (professionalSoftwareVersionInfo != null) {
            //查找为非空则进行entity类包装为vo类
            DetailedSoftwareVersionTypeVO detailedSoftwareVersionTypeVO = new DetailedSoftwareVersionTypeVO();
            BeanUtils.copyProperties(professionalSoftwareVersionInfo, detailedSoftwareVersionTypeVO);
            //添加到list集合中
            detailedSoftwareVersionTypeVOList.add(detailedSoftwareVersionTypeVO);
        }
        return detailedSoftwareVersionTypeVOList;
    }

    @Override
    public List<SoftwareHistoryVersionDownloadVO> historySoftwareVersion(HistorySoftwareVersionDTO historySoftwareVersionDTO) {
        //根据软件id和软件版本(普0/专1)查找软件历史版本下载地址
        List<SoftwareVersionDownload> softwareVersionDownloadList = softwareMapper.querySoftwareVersionDownloadBySoftwareIdAndVersionType(historySoftwareVersionDTO);
        List<SoftwareHistoryVersionDownloadVO> SoftwareHistoryVersionDownloadVOList = new ArrayList<>();
        for (SoftwareVersionDownload softwareVersionDownload : softwareVersionDownloadList) {
            //将entity类包装成vo类
            SoftwareHistoryVersionDownloadVO SoftwareHistoryVersionDownloadVO = new SoftwareHistoryVersionDownloadVO();
            BeanUtils.copyProperties(softwareVersionDownload, SoftwareHistoryVersionDownloadVO);
            SoftwareHistoryVersionDownloadVOList.add(SoftwareHistoryVersionDownloadVO);
        }
        return SoftwareHistoryVersionDownloadVOList;
    }

    @Override
    public int uploadNewSoftware(UploadNewSoftwareDTO uploadNewSoftwareDTO) {
        SoftwareInfoTemp softwareInfoTemp = new SoftwareInfoTemp();
        BeanUtils.copyProperties(uploadNewSoftwareDTO, softwareInfoTemp);
        //DOTO 模拟文件上传后返回了url实现数据库操作逻辑（并没有实现文件上传到云端服务器的逻辑）
        String winUrl = "www.WindowsDownload.com";
        String linuxUrl = "www.LinuxDownload.com";
        String macUrl = "www.MacDownload.com";
        softwareInfoTemp.setWinUrl(winUrl);
        softwareInfoTemp.setLinuxUrl(linuxUrl);
        softwareInfoTemp.setMacUrl(macUrl);

        //设置通过状态码为0(0代办/1通过/2拒绝)
        softwareInfoTemp.setPassedStatus(0);
        //将list集合转化为string存入数据库
        softwareInfoTemp.setTagsToString(softwareInfoTemp.getTags().toString());

        return softwareMapper.addSoftwareInforTemp(softwareInfoTemp);
    }


}
