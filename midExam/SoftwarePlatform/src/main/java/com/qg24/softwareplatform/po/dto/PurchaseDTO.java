package com.qg24.softwareplatform.po.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDTO {

   private String userId; //用户id
   private String fingerprint; //硬件指纹
   private double totalPrize; //总金额
   private List<AuthSoftwareDTO> authSoftwareDTOList; //软件信息列表

}
