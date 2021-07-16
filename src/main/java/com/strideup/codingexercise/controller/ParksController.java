package com.strideup.codingexercise.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.strideup.codingexercise.model.Park;
import com.strideup.codingexercise.service.ParkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class ParksController {

    @Autowired
    private ParkService parkService;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${nps.api.key}")
    private String apiKey;

    @GetMapping("/parks")
    public List<Park> getParks(@RequestParam(name = "parkCode",required = false) String parkCode, @RequestParam(name = "stateCode",required = false) String stateCode){

        String parksRes = null;

        if(parkCode != null && stateCode != null){
            parksRes = restTemplate.getForObject(
                    "https://developer.nps.gov/api/v1/parks?parkCode=" + parkCode + "&stateCode=" + stateCode + "&api_key=" + apiKey, String.class);
        }else if(parkCode != null){
            parksRes = restTemplate.getForObject(
                    "https://developer.nps.gov/api/v1/parks?parkCode=" + parkCode + "&api_key=" + apiKey, String.class);
        }else if(stateCode != null){
            parksRes = restTemplate.getForObject(
                    "https://developer.nps.gov/api/v1/parks?stateCode=" + stateCode + "&api_key=" + apiKey, String.class);
        }else {
            parksRes = restTemplate.getForObject(
                    "https://developer.nps.gov/api/v1/parks?api_key=" + apiKey, String.class);
        }

        //Parse the Json Object and get the data field containing the park fields
        JsonParser parser = new JsonParser();
        JsonObject jSonObj = parser.parse(parksRes).getAsJsonObject();
        JsonArray jSonArray = (JsonArray) jSonObj.get("data");

        Set<Park> parksSet = new HashSet<>(parkService.getParks());

        //Get each park object from the JSON and create a park object
        for(int i = 0; i < jSonArray.size(); i++){
            String obj = jSonArray.get(i).toString();
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
            Park parkObj = null;
            try {
                parkObj = objectMapper.readValue(obj, Park.class);
                //If the park already exists on the set it will not be added
                parksSet.add(parkObj);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        parkService.saveAll(parksSet);

        return parkService.getParks();
    }

    @GetMapping("/park/{parkCode}")
    public Park getParkByParkCode(@PathVariable String parkCode){
       Park park = parkService.getByParkCode(parkCode);
       if(park == null){
           String parksRes = restTemplate.getForObject(
                   "https://developer.nps.gov/api/v1/parks?parkCode=" + parkCode + "&api_key=" + apiKey, String.class);

           JsonParser parser = new JsonParser();
           JsonObject jSonObj = parser.parse(parksRes).getAsJsonObject();
           JsonArray jSonArray = (JsonArray) jSonObj.get("data");
           String obj = jSonArray.get(0).toString();
           ObjectMapper objectMapper = new ObjectMapper();
           objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
           Park parkObj = null;
           try {
               parkObj = objectMapper.readValue(obj, Park.class);
           } catch (JsonProcessingException e) {
               e.printStackTrace();
           }
           parkService.savePark(parkObj);
           park = parkService.getByParkCode(parkCode);
       }
       return park;
    }

    @PostMapping(path="/parks", consumes = "application/json", produces = "application/json")
    public void addPark(@RequestBody Park park){
        parkService.savePark(park);
    }

    @PutMapping("/parks/{parkCode}")
    public void updatePark(@PathVariable("parkCode") String parkCode, @RequestBody Park park){
        Park parkObj = parkService.getByParkCode(parkCode);

        parkObj.setDescription(park.getDescription());
        parkObj.setDesignation(park.getDesignation());
        parkObj.setDirectionsInfo(park.getDirectionsInfo());
        parkObj.setDirectionsUrl(park.getDirectionsUrl());
        parkObj.setFullName(park.getFullName());
        parkObj.setLatLong(park.getLatLong());
        parkObj.setLatitude(park.getLatitude());
        parkObj.setLongitude(park.getLongitude());
        parkObj.setName(park.getName());
        parkObj.setParkCode(park.getParkCode());
        parkObj.setStates(park.getStates());
        parkObj.setUrl(park.getUrl());
        parkObj.setWeatherInfo(park.getWeatherInfo());
        parkObj.setActivities(park.getActivities());
        parkObj.setAddresses(park.getAddresses());
        parkObj.setContacts(park.getContacts());
        parkObj.setEntranceFees(park.getEntranceFees());
        parkObj.setEntrancePasses(park.getEntrancePasses());
        parkObj.setImages(park.getImages());
        parkObj.setTopics(park.getTopics());
        parkObj.setOperatingHours(park.getOperatingHours());

        parkService.savePark(parkObj);
    }


}
