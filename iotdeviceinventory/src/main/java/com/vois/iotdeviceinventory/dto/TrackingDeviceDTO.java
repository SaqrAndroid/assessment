package com.vois.iotdeviceinventory.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TrackingDeviceDTO {



    private Long id;
    private Long deviceId;
    private int temperature;
    private String status;
    private boolean configured;


}
