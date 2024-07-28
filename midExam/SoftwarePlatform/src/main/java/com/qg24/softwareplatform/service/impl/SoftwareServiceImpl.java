package com.qg24.softwareplatform.service.impl;

import com.qg24.softwareplatform.mapper.SoftwareMapper;
import com.qg24.softwareplatform.po.dto.HistorySoftwareVersionDTO;
import com.qg24.softwareplatform.po.dto.HomePageShowSoftwareDTO;
import com.qg24.softwareplatform.po.dto.UploadNewSoftwareDTO;
import com.qg24.softwareplatform.po.entity.Software;
import com.qg24.softwareplatform.po.entity.SoftwareInfoTemp;
import com.qg24.softwareplatform.po.entity.SoftwareVersionDownload;
import com.qg24.softwareplatform.po.result.PageBean;
import com.qg24.softwareplatform.po.vo.DetailedSoftwareVersionTypeVO;
import com.qg24.softwareplatform.po.vo.SimpleSoftwareVO;
import com.qg24.softwareplatform.po.vo.SoftwareHistoryVersionDownloadVO;
import com.qg24.softwareplatform.service.SoftwareService;
import com.qg24.softwareplatform.util.AliOssUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class SoftwareServiceImpl implements SoftwareService {

    @Autowired
    private SoftwareMapper softwareMapper;
    @Autowired
    private AliOssUtil aliOssUtil;

    @Override
    public PageBean<SimpleSoftwareVO> homePageShowSoftware(HomePageShowSoftwareDTO homePageShowSoftwareDTO) {
        PageBean<SimpleSoftwareVO> pageBean = new PageBean<>();
        List<SimpleSoftwareVO> simpleSoftwareVOS = new ArrayList<>();

        int offset = (homePageShowSoftwareDTO.getPage() - 1) * homePageShowSoftwareDTO.getPageSize();
        List<Software> softwareList = softwareMapper.pagedQuerySoftwareBySoftNameAndTags(homePageShowSoftwareDTO.getSoftwareName(), offset, homePageShowSoftwareDTO.getPageSize());
        int total = softwareMapper.getTotal(homePageShowSoftwareDTO.getSoftwareName(), homePageShowSoftwareDTO.getTags());
        List<String> tags = homePageShowSoftwareDTO.getTags();
        // 将tagString转换为List类型的tags
        softwareList.forEach(Software::TagsToList);

        // 进行子集的判断
        softwareList.forEach(software -> {
            if (new HashSet<>(tags).containsAll(software.getTags())) {
                // tags为用户想要软件拥有的标签, 查询软件时软件的标签应该为tags的子集
                SimpleSoftwareVO simpleSoftwareVO = new SimpleSoftwareVO();
                BeanUtils.copyProperties(software, simpleSoftwareVO);
                simpleSoftwareVOS.add(simpleSoftwareVO);
            }
        });

        pageBean.setTotal((long) total);
        pageBean.setData(simpleSoftwareVOS);

        return pageBean;
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
    public int uploadNewSoftware(@ModelAttribute UploadNewSoftwareDTO uploadNewSoftwareDTO) throws IOException {
        SoftwareInfoTemp softwareInfoTemp = new SoftwareInfoTemp();
        BeanUtils.copyProperties(uploadNewSoftwareDTO, softwareInfoTemp);

        String winUrl = "";
        String linuxUrl = "";
        String macUrl = "";


        // win
        MultipartFile winPackage = uploadNewSoftwareDTO.getWinPackage();
        if (!winPackage.isEmpty()) {
            String extension = Objects.requireNonNull(winPackage.getOriginalFilename()).substring(winPackage.getOriginalFilename().lastIndexOf("."));
            String fileName = UUID.randomUUID() + extension;
            byte[] bytes = winPackage.getBytes();
            winUrl = aliOssUtil.upload(bytes, fileName);
        }

        // linux
        MultipartFile linuxPackage = uploadNewSoftwareDTO.getLinuxPackage();
        if (!linuxPackage.isEmpty()) {
            String extension = Objects.requireNonNull(linuxPackage.getOriginalFilename()).substring(linuxPackage.getOriginalFilename().lastIndexOf("."));
            String fileName = UUID.randomUUID() + extension;
            byte[] bytes = linuxPackage.getBytes();
            linuxUrl = aliOssUtil.upload(bytes, fileName);
        }

        // mac
        MultipartFile macPackage = uploadNewSoftwareDTO.getMacPackage();
        if (!macPackage.isEmpty()) {
            String extension = Objects.requireNonNull(macPackage.getOriginalFilename()).substring(macPackage.getOriginalFilename().lastIndexOf("."));
            String fileName = UUID.randomUUID() + extension;
            byte[] bytes = macPackage.getBytes();
            macUrl = aliOssUtil.upload(bytes, fileName);
        }


        // TODO 模拟文件上传后返回了url实现数据库操作逻辑（并没有实现文件上传到云端服务器的逻辑）

        softwareInfoTemp.setWinUrl(winUrl);
        softwareInfoTemp.setLinuxUrl(linuxUrl);
        softwareInfoTemp.setMacUrl(macUrl);

        //设置通过状态码为0(0代办/1通过/2拒绝)
        softwareInfoTemp.setPassedStatus(0);
        //将list集合转化为string存入数据库
        softwareInfoTemp.setTagsString(softwareInfoTemp.getTags().toString());

        return softwareMapper.addSoftwareInfoTemp(softwareInfoTemp);
    }


}
