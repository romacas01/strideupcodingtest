package com.strideup.codingexercise.service;

import com.strideup.codingexercise.model.Park;

import java.util.List;
import java.util.Set;

public interface ParkService {

    public List<Park> getParks();
    public void savePark(Park park);
    public void saveAll(Set<Park> parks);
    public Park getByParkCode(String parkCode);
}
