package com.qg24.softwareplatform.service.impl;

import com.qg24.softwareplatform.mapper.AdminMapper;
import com.qg24.softwareplatform.po.dto.HomePageShowSoftwareDTO;
import com.qg24.softwareplatform.po.dto.UpdateSoftwareLatestInfoDTO;
import com.qg24.softwareplatform.po.entity.Software;
import com.qg24.softwareplatform.po.entity.SoftwareInfoTemp;
import com.qg24.softwareplatform.po.entity.SoftwareVersionDownload;
import com.qg24.softwareplatform.po.result.PageBean;
import com.qg24.softwareplatform.po.vo.AdminShowAllSoftwareVO;
import com.qg24.softwareplatform.po.vo.SimpleSoftwareVO;
import com.qg24.softwareplatform.service.AdminService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

    //管理员查看审核记录
    @Override
    public List<SoftwareInfoTemp> getSoftwareInfoTempList(int page, int pageSize) {
        List<SoftwareInfoTemp> softwareInfoTempList =  adminMapper.getSoftwareInfoTempPages(pageSize,(page-1)*pageSize);
        for (SoftwareInfoTemp softwareInfoTemp : softwareInfoTempList) {
            int passedStatus = softwareInfoTemp.getPassedStatus();
            if (passedStatus == 0) {
                softwareInfoTemp.setPassedStringStatus("代办");
            }else if (passedStatus == 1) {
                softwareInfoTemp.setPassedStringStatus("已通过");
            }else{
                softwareInfoTemp.setPassedStringStatus("已拒绝");
            }
        }
        return softwareInfoTempList;
    }


    //管理员更改审核通过状态码
    @Override
    @Transactional
    public boolean updateSoftwareInfoTempStatus(int softwareInfoTempId, int status) {
        if(adminMapper.updateSoftwareInfoTemp(softwareInfoTempId,status)==1){
            if(status==1){
                //通过
                SoftwareInfoTemp softwareInfoTemp = adminMapper.getSoftwareInfoTemp(softwareInfoTempId);//获得记录信息
                Software software = new Software();
                BeanUtils.copyProperties(softwareInfoTemp,software);
                //将简要描述存入软件表中
                software.setDescription(softwareInfoTemp.getBriefDescription());
                if(softwareInfoTemp.getTypeStatus()==0){
                    //上传新软件(即第一次上传,不是迭代)
                    software.setCreateTime(softwareInfoTemp.getApplicationTime());
                    int count = adminMapper.insertNewSoftwareTable(software);
                    if(count!=0){
                        //将新软件信息存入软件下载表中
                        SoftwareVersionDownload softwareVersionDownload = new SoftwareVersionDownload();
                        BeanUtils.copyProperties(softwareInfoTemp,softwareVersionDownload);
                        softwareVersionDownload.setSoftwareId(software.getSoftwareId());
                        softwareVersionDownload.setCreateTime(softwareInfoTemp.getApplicationTime());
                        if(adminMapper.insertNewSoftwareDownloadTable(softwareVersionDownload)==1){
                            return true;
                        }else{
                            return false;
                        }
                    }else {
                        return false;
                    }
                }else{
                    //迭代软件版本
                    //判断是否为上传新版本
                    if(softwareInfoTemp.getPrice()==0){
                        //上传新版本号
                        //获得软件id
                        int softwareId = adminMapper.getSoftwareId(softwareInfoTemp.getSoftwareName());
                        //获得价格
                        float price = adminMapper.getPrice(software.getSoftwareName(),softwareId);
                        //将新数据插入软件下载表
                        softwareInfoTemp.setPrice(price);
                        SoftwareVersionDownload softwareVersionDownload = new SoftwareVersionDownload();
                        BeanUtils.copyProperties(softwareInfoTemp,softwareVersionDownload);
                        softwareVersionDownload.setSoftwareId(softwareId);
                        softwareVersionDownload.setCreateTime(softwareInfoTemp.getApplicationTime());
                        if(adminMapper.insertNewSoftwareDownloadTable(softwareVersionDownload)==1){
                            return true;
                        }else{
                            return false;
                        }
                    }else{
                        //上传新版本
                        //将新数据插入软件下载表
                        SoftwareVersionDownload softwareVersionDownload = new SoftwareVersionDownload();
                        BeanUtils.copyProperties(softwareInfoTemp,softwareVersionDownload);
                        //获得软件id
                        int softwareId = adminMapper.getSoftwareId(softwareInfoTemp.getSoftwareName());
                        softwareVersionDownload.setSoftwareId(softwareId);
                        softwareVersionDownload.setCreateTime(softwareInfoTemp.getApplicationTime());
                        if(adminMapper.insertNewSoftwareDownloadTable(softwareVersionDownload)==1){
                            return true;
                        }else{
                            return false;
                        }
                    }
                }
            }else{
                return true;
            }
        }else{
            return false;
        }
    }

    /**
     * 管理员查看所有软件，包括上架和下架
     * @param homePageShowSoftwareDTO
     * @return
     */
    @Override
    public PageBean<AdminShowAllSoftwareVO> homePageShowAllSoftware(HomePageShowSoftwareDTO homePageShowSoftwareDTO) {
        PageBean<AdminShowAllSoftwareVO> pageBean = new PageBean<>();

        int offset = (homePageShowSoftwareDTO.getPage() - 1) * homePageShowSoftwareDTO.getPageSize();
        List<AdminShowAllSoftwareVO> softwareList = adminMapper.homePageShowAllSoftware(homePageShowSoftwareDTO.getSoftwareName(), offset, homePageShowSoftwareDTO.getPageSize(), homePageShowSoftwareDTO.getTags());
        int total = adminMapper.getTotal(homePageShowSoftwareDTO.getSoftwareName(), homePageShowSoftwareDTO.getTags());

        // 将tagString转换为List类型的tags
        softwareList.forEach(AdminShowAllSoftwareVO::TagsToList);

        pageBean.setTotal((long) total);
        pageBean.setData(softwareList);

        return pageBean;
    }
}
