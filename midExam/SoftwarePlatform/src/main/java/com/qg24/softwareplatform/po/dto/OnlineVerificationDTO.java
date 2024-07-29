package com.qg24.softwareplatform.po.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OnlineVerificationDTO {
   private String fingerprint;
   private int versionType;
   private String softwareName;

}
