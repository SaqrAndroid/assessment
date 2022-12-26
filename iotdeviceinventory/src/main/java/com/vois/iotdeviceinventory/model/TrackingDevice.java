package com.vois.iotdeviceinventory.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Device")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TrackingDevice {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "DeviceId", nullable = false)
    private Long deviceId;
    @Column(name = "Temperature", nullable = false)
    private int temperature;
    @Column(name = "Status", nullable = false)
    private String status;
    @Column(name = "Configured", nullable = false)
    private boolean configured;


}
