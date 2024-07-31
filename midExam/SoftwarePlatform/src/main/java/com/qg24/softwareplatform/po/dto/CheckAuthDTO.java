package com.qg24.softwareplatform.po.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckAuthDTO {

   private int softwareId;
   private String userId;
   private int versionType;
   private String version;


}
