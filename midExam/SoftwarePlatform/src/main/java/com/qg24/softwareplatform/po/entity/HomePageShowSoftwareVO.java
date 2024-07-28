package com.qg24.softwareplatform.po.entity;

import com.qg24.softwareplatform.po.vo.SimpleSoftwareVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomePageShowSoftwareVO {
    private int total;
    private List<SimpleSoftwareVO> list;
}
