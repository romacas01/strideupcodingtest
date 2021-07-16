package com.strideup.codingexercise.service;

import com.strideup.codingexercise.model.Park;
import com.strideup.codingexercise.repository.ParkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;

@Service
public class ParkServiceImpl implements ParkService {

    @Autowired
    private ParkRepository parkRepository;

    public List<Park> getParks(){
        return parkRepository.findAll();
    }

    public void savePark(Park park){
        parkRepository.save(park);
    }

    public void saveAll(Set<Park> parks){
        parkRepository.saveAll(parks);
    }

    @Override
    public Park getByParkCode(String parkCode) {
        Park park = parkRepository.getByParkCode(parkCode);
        return park;
    }

}
