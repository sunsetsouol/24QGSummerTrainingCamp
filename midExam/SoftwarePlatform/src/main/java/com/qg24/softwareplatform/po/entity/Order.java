package com.qg24.softwareplatform.po.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class Order {

    private String createTime;

    private int orderId;

    private double price;

    private String softwareInfo;

    private String userId;
}
