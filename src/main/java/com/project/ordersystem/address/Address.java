package com.project.ordersystem.address;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Address {

    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String country;
    private String postCode;
}
