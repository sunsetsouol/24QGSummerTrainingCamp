package com.qg24.softwareplatform.service;

import com.qg24.softwareplatform.po.dto.PurchaseDTO;

public interface AuthorizationService {

    //确认购买授权
    public boolean purchaseAuth(PurchaseDTO purchaseDTO);



}
