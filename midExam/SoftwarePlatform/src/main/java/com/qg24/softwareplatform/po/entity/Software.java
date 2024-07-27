package com.qg24.softwareplatform.po.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Software {

    private String author;

    private String description;

    private int downloadCount;

    private int softwareId;

    private String softwareName;

    private int status;

    private List<String> tags;


}
