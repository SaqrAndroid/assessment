package com.vois.iotdeviceinventory.controller;


import com.vois.iotdeviceinventory.dto.TrackingDeviceDTO;
import com.vois.iotdeviceinventory.service.TrackingDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/inventory")
public class TrackingDeviceController {

    @Autowired
    private TrackingDeviceService trackingDeviceService;


    @PostMapping("/add")
    @ResponseBody
    public  ResponseEntity<?> addTrackingDevice(@RequestBody TrackingDeviceDTO trackingDeviceDTO){

        return trackingDeviceService.addIoTDevice(trackingDeviceDTO);
    }


    @PostMapping("/find-all")
    @ResponseBody
    public List<TrackingDeviceDTO> findAllTrackingDevices(){

        return trackingDeviceService.getAllDevices();
    }

    @GetMapping("/dcs")
    @ResponseBody
    public ResponseEntity<?> deviceConfiguration(@RequestParam long id){

        return trackingDeviceService.deviceConfigurationService(id);
    }



    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> updateTrackingDevice(@PathVariable(name = "id") long id , @RequestBody TrackingDeviceDTO trackingDeviceDTO){

        return trackingDeviceService.updateTrackingDevice(id, trackingDeviceDTO);
    }


    @GetMapping("/remove")
    @ResponseBody
    public ResponseEntity<?> RemoveTrackingDevices(@RequestParam long id){

        return trackingDeviceService.removeDevice(id);
    }







}
