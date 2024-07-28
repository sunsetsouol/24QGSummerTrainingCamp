package com.qg24.softwareplatform.po.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OnlineVertificationDTO {
   private String fingerprint;
   private String versionType;
   private String softwareName;

}
