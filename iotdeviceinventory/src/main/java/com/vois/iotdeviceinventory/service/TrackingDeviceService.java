package com.vois.iotdeviceinventory.service;

import com.vois.iotdeviceinventory.dto.TrackingDeviceDTO;
import com.vois.iotdeviceinventory.model.TrackingDevice;
import com.vois.iotdeviceinventory.repository.TrackingDeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class TrackingDeviceService {

    @Autowired
    private TrackingDeviceRepository trackingDeviceRepository;

    public TrackingDeviceService(TrackingDeviceRepository trackingDeviceRepository) {
        this.trackingDeviceRepository = trackingDeviceRepository;
    }

    public ResponseEntity<?> addIoTDevice(TrackingDeviceDTO trackingDeviceDTO){

        TrackingDevice trackingDevice = new TrackingDevice();

            trackingDevice.setDeviceId(trackingDeviceDTO.getDeviceId());
            trackingDevice.setTemperature(trackingDeviceDTO.getTemperature());
            trackingDevice.setStatus(trackingDeviceDTO.getStatus());
            trackingDevice.setConfigured(trackingDeviceDTO.isConfigured());

        trackingDeviceRepository.save(trackingDevice);

        return new ResponseEntity<>(trackingDevice, HttpStatus.OK);
    }


    public List<TrackingDeviceDTO> getAllDevices(){

        List<TrackingDevice> trackingDevices = trackingDeviceRepository.findAll();
        return trackingDevices.stream().map(trackingDevice -> mapToDTO(trackingDevice)).collect(Collectors.toList());
    }


    public ResponseEntity<?> removeDevice(Long id){
        TrackingDevice trackingDevice = trackingDeviceRepository.findTrackingDeviceById(id);
        if(trackingDevice == null)
            return new ResponseEntity<>("DEVICE DOESN'T EXIST !",HttpStatus.BAD_REQUEST);
        return  new ResponseEntity<>("Deleted!", HttpStatus.OK);
    }

    public ResponseEntity<?>  updateTrackingDevice(long id ,TrackingDeviceDTO trackingDeviceDTO ){
        TrackingDevice trackingDevice = trackingDeviceRepository.findTrackingDeviceById(id);
        if(trackingDevice == null)
            return new ResponseEntity<>("device not found !", HttpStatus.BAD_REQUEST);

        trackingDevice.setTemperature(trackingDeviceDTO.getTemperature());
        trackingDevice.setConfigured(trackingDeviceDTO.isConfigured());
        trackingDevice.setStatus(trackingDeviceDTO.getStatus());
        trackingDevice.setDeviceId(trackingDeviceDTO.getDeviceId());
        trackingDeviceRepository.save(trackingDevice);

        return new ResponseEntity<>(trackingDevice , HttpStatus.OK);
    }




    public ResponseEntity<?> deviceConfigurationService(long id){

        TrackingDevice trackingDevice = trackingDeviceRepository.findTrackingDeviceById(id);
        if(trackingDevice == null) {
            return new ResponseEntity<>("device not found !", HttpStatus.BAD_REQUEST);
        }
        int min = 0;
        int max = 10;
        Random rand = new Random();
        int randomTempo = rand.nextInt(max - min) + min;
        if(trackingDevice.isConfigured() == false)
            trackingDevice.setStatus("ACTIVE");
            trackingDevice.setTemperature(randomTempo);

        trackingDeviceRepository.save(trackingDevice);
        return new ResponseEntity<>(trackingDevice , HttpStatus.OK);

    }


    private TrackingDeviceDTO mapToDTO(TrackingDevice trackingDevice){

        TrackingDeviceDTO trackingDeviceDTO = new TrackingDeviceDTO();
        trackingDeviceDTO.setId(trackingDevice.getId());
        trackingDeviceDTO.setConfigured(trackingDevice.isConfigured());
        trackingDeviceDTO.setDeviceId(trackingDevice.getDeviceId());
        trackingDeviceDTO.setStatus(trackingDevice.getStatus());
        trackingDeviceDTO.setTemperature(trackingDevice.getTemperature());

        return trackingDeviceDTO;

    }



//    Random rand = new Random();
//    long num = rand.nextInt(9000000) + 1000000;
//        System.out.println("Device Id :: " + num);
//        trackingDevice.setDeviceId(num);





}
