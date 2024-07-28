package com.qg24.softwareplatform.service;

import com.qg24.softwareplatform.po.dto.CheckAuthDTO;
import com.qg24.softwareplatform.po.dto.PurchaseDTO;
import com.qg24.softwareplatform.po.vo.DownloadUrlsVO;

public interface AuthorizationService {

    //用户购买授权
    public boolean purchaseAuth(PurchaseDTO purchaseDTO);

    //检验授权
    public String checkAuth(CheckAuthDTO checkAuthDTO);

    //成功验证授权放回url下载质地
    public DownloadUrlsVO getDownloadUrls(CheckAuthDTO checkAuthDTO);


}
