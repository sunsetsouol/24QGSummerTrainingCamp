package com.qg24.softwareplatform.service.impl;

import com.qg24.softwareplatform.mapper.SoftwareMapper;
import com.qg24.softwareplatform.po.dto.HistorySoftwareVersionDTO;
import com.qg24.softwareplatform.po.dto.HomePageShowSoftwareDTO;
import com.qg24.softwareplatform.po.dto.UploadNewSoftwareDTO;
import com.qg24.softwareplatform.po.dto.UserDownloadSoftwareDTO;
import com.qg24.softwareplatform.po.entity.Software;
import com.qg24.softwareplatform.po.entity.SoftwareInfoTemp;
import com.qg24.softwareplatform.po.entity.SoftwareVersionDownload;
import com.qg24.softwareplatform.po.entity.UserSoftwareDownload;
import com.qg24.softwareplatform.po.result.PageBean;
import com.qg24.softwareplatform.po.vo.*;
import com.qg24.softwareplatform.service.SoftwareService;
import com.qg24.softwareplatform.util.AliOssUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class SoftwareServiceImpl implements SoftwareService {

    @Autowired
    private SoftwareMapper softwareMapper;
    @Autowired
    private AliOssUtil aliOssUtil;

    @Override
    public PageBean<SimpleSoftwareVO> homePageShowSoftware(HomePageShowSoftwareDTO homePageShowSoftwareDTO) {
        PageBean<SimpleSoftwareVO> pageBean = new PageBean<>();

        int offset = (homePageShowSoftwareDTO.getPage() - 1) * homePageShowSoftwareDTO.getPageSize();
        List<SimpleSoftwareVO> softwareList = softwareMapper.pagedQuerySoftwareBySoftNameAndTags(homePageShowSoftwareDTO.getSoftwareName(), offset, homePageShowSoftwareDTO.getPageSize(), homePageShowSoftwareDTO.getTags());
        int total = softwareMapper.getTotal(homePageShowSoftwareDTO.getSoftwareName(), homePageShowSoftwareDTO.getTags());

        // 将tagString转换为List类型的tags
        softwareList.forEach(SimpleSoftwareVO::TagsToList);

        pageBean.setTotal((long) total);
        pageBean.setData(softwareList);

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
            detailedSoftwareVersionTypeVO.setDescription(ordinarySoftwareVersionInfo.getDetailedDescription());
            //添加到list集合中
            detailedSoftwareVersionTypeVOList.add(detailedSoftwareVersionTypeVO);
        }
        //根据软件id 查找最新专业版本的详细软件信息
        SoftwareVersionDownload professionalSoftwareVersionInfo = softwareMapper.queryLastestProfessionalDetaliedSoftware(softwareId);
        if (professionalSoftwareVersionInfo != null) {
            //查找为非空则进行entity类包装为vo类
            DetailedSoftwareVersionTypeVO detailedSoftwareVersionTypeVO = new DetailedSoftwareVersionTypeVO();
            BeanUtils.copyProperties(professionalSoftwareVersionInfo, detailedSoftwareVersionTypeVO);
            detailedSoftwareVersionTypeVO.setDescription(professionalSoftwareVersionInfo.getDetailedDescription());
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
    public int uploadNewSoftware(UploadNewSoftwareDTO uploadNewSoftwareDTO, MultipartFile winPackage, MultipartFile linuxPackage, MultipartFile macPackage, MultipartFile softwareImage) throws IOException {
        SoftwareInfoTemp softwareInfoTemp = new SoftwareInfoTemp();
        BeanUtils.copyProperties(uploadNewSoftwareDTO, softwareInfoTemp, "price");

        if (!Objects.isNull(uploadNewSoftwareDTO.getPrice())) {
            softwareInfoTemp.setPrice(uploadNewSoftwareDTO.getPrice());
        }
        String winUrl = "";
        String linuxUrl = "";
        String macUrl = "";
        String softwareImageUrl = "";


        // win
        if (Objects.nonNull(winPackage)) {
            String extension = Objects.requireNonNull(winPackage.getOriginalFilename()).substring(winPackage.getOriginalFilename().lastIndexOf("."));
            String fileName = UUID.randomUUID() + extension;
            byte[] bytes = winPackage.getBytes();
            winUrl = aliOssUtil.upload(bytes, fileName);
        }

        // linux
        if (Objects.nonNull(linuxPackage)) {
            String extension = Objects.requireNonNull(linuxPackage.getOriginalFilename()).substring(linuxPackage.getOriginalFilename().lastIndexOf("."));
            String fileName = UUID.randomUUID() + extension;
            byte[] bytes = linuxPackage.getBytes();
            linuxUrl = aliOssUtil.upload(bytes, fileName);
        }

        // mac
        if (Objects.nonNull(macPackage)) {
            String extension = Objects.requireNonNull(macPackage.getOriginalFilename()).substring(macPackage.getOriginalFilename().lastIndexOf("."));
            String fileName = UUID.randomUUID() + extension;
            byte[] bytes = macPackage.getBytes();
            macUrl = aliOssUtil.upload(bytes, fileName);
        }

        if (Objects.nonNull(softwareImage)) {
            String extension = Objects.requireNonNull(softwareImage.getOriginalFilename()).substring(softwareImage.getOriginalFilename().lastIndexOf("."));
            String fileName = UUID.randomUUID() + extension;
            byte[] bytes = softwareImage.getBytes();
            softwareImageUrl = aliOssUtil.upload(bytes, fileName);
        }

        softwareInfoTemp.setWinUrl(winUrl);
        softwareInfoTemp.setLinuxUrl(linuxUrl);
        softwareInfoTemp.setMacUrl(macUrl);
        softwareInfoTemp.setSoftwareImage(softwareImageUrl);

        //设置通过状态码为0(0代办/1通过/2拒绝)
        softwareInfoTemp.setPassedStatus(0);
        //将list集合转化为string存入数据库
        softwareInfoTemp.setTagsString(softwareInfoTemp.getTags().toString());

        return softwareMapper.addSoftwareInfoTemp(softwareInfoTemp);
    }

    @Override
    public List<ShowRequiredAuthSoftwareVO> showRequiredAuthSoftware(String userId,int page) {
        int offset = (page - 1) * 24;
        //使用联表查询，使用外连方式
        List<ShowRequiredAuthSoftwareVO> showRequiredAuthSoftwareVOList = softwareMapper.querySoftwareVersionDownloadUserNoAuth(userId,offset);

        return showRequiredAuthSoftwareVOList;
    }

    /**
     * 用户下载的话即添加下载记录，更新就更新版本号
     * @param userDownloadSoftwareDTO
     * @return
     */
    @Override
    public int addOrUpdateUserSoftwareDownload(UserDownloadSoftwareDTO userDownloadSoftwareDTO) {
        UserSoftwareDownload userSoftwareDownload = new UserSoftwareDownload();
        BeanUtils.copyProperties(userDownloadSoftwareDTO, userSoftwareDownload);
        //先判断是否有些条下载记录
        UserSoftwareDownload userSoftwareDownload1 = softwareMapper.selectByThreeConditions(userSoftwareDownload);
        if (userSoftwareDownload1 == null){
            //添加此下载记录
            return softwareMapper.addUserSoftwareDownload(userSoftwareDownload);
        }else {
            //有此记录，更新新版本号即可
            return softwareMapper.updateUserSoftwareDownload(userSoftwareDownload);
        }
    }

    /**
     * 判断用户是否有可更新的软件
     * @param userId
     * @return
     */
    @Override
    public List<CheckLastestSoftwareVO> checkLatestSoftware(String userId) {
        List<CheckLastestSoftwareVO> checkLastestSoftwareVOS = softwareMapper.selectSoftwareUserCanUpdate(userId);
        //判断是否有东西
        if (checkLastestSoftwareVOS.size() == 0){
            return null;
        }else {
            return checkLastestSoftwareVOS;
        }
    }

}
