package com.qg24.softwareplatform.po.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckLastestSoftwareVO {

   private int softwareId;
   private String softwareName;
   private String version;
   private int versionType;
   private String softwareImage;

}
