package com.vois.iotdeviceinventory.repository;


import com.vois.iotdeviceinventory.model.TrackingDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackingDeviceRepository extends JpaRepository<TrackingDevice , Long> {


    TrackingDevice findTrackingDeviceById(Long id);



}
