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


    /**
     * Save all the parks on the DB and returns all that parks that were saved
     * If the request params are passed it will fetch and save only the parks related to the param
     * @param parkCode (not required)
     * @param stateCode (not required)
     * @return a list of all parks saved on the DB
     */
    @GetMapping("/parks")
    public List<Park> getParks(@RequestParam(name = "parkCode",required = false) String parkCode, @RequestParam(name = "stateCode",required = false) String stateCode){

        String parksRes = null;
        String npsUrl = "https://developer.nps.gov/api/v1/parks?api_key=" + apiKey;

        //TODO: Use a Map to get the pamaters instead?
        //If the parameters are passed we will fetch and save onlythe parks related to the park code
        if(parkCode != null && stateCode != null){
            npsUrl = "https://developer.nps.gov/api/v1/parks?api_key=" + apiKey + "&parkCode=" + parkCode + "&stateCode=" + stateCode;
        }else if(parkCode != null){
            npsUrl = "https://developer.nps.gov/api/v1/parks?api_key=" + apiKey + "&parkCode=" + parkCode;
        }else if(stateCode != null){
            npsUrl = "https://developer.nps.gov/api/v1/parks?api_key=" + apiKey + "&stateCode=" + stateCode;
        }

        parksRes = restTemplate.getForObject(
                npsUrl, String.class);

        //Parse the Json Object and get the data field containing the park fields
        JsonParser parser = new JsonParser();
        JsonObject jSonObj = parser.parse(parksRes).getAsJsonObject();
        JsonArray jSonArray = (JsonArray) jSonObj.get("data");

        Set<Park> parksSet = new HashSet<>(parkService.getParks());

        Park parkObj = null;

        //Get each park object from the JSON and create a park object
        for(int i = 0; i < jSonArray.size(); i++){
            String obj = jSonArray.get(i).toString();
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

            try {
                parkObj = objectMapper.readValue(obj, Park.class);
                //If the park already exists on the set it will not be added
                parksSet.add(parkObj);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        parkService.saveAll(parksSet);

        //Will display all the parks that are saved on the db
        return parkService.getParks();
    }

    /**
     * Retrieve the park according to the parkCode
     * If the park does not exist on the db it will make call to nps, retrieve the park information and save on the db
     * @param parkCode
     * @return the park according to the parkCode
     */
    @GetMapping("/park/{parkCode}")
    public Park getParkByParkCode(@PathVariable String parkCode){
       Park park = parkService.getByParkCode(parkCode);
       if(park == null){
           String parksRes = restTemplate.getForObject(
                   "https://developer.nps.gov/api/v1/parks?parkCode=" + parkCode + "&api_key=" + apiKey, String.class);

           //Get all the parks first
           Set<Park> parksSet = new HashSet<>(parkService.getParks());

           JsonParser parser = new JsonParser();
           JsonObject jSonObj = parser.parse(parksRes).getAsJsonObject();
           JsonArray jSonArray = (JsonArray) jSonObj.get("data");
           String obj = jSonArray.get(0).toString();
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

           parkService.savePark(parkObj);
       }
       return parkService.getByParkCode(parkCode);
    }

    /**
     * Saves the park on the DB.
     * @param park
     */
    @PostMapping(path="/parks", consumes = "application/json", produces = "application/json")
    public void addPark(@RequestBody Park park){

        //Avoid duplicated park being added
        Set<Park> parks = new HashSet<>(parkService.getParks());
        parks.add(park);

        parkService.saveAll(parks);
    }

    /**
     * Updates the park related to the parkCode
     * @param parkCode
     * @param park
     */
    @PutMapping("/parks/{parkCode}")
    public void updatePark(@PathVariable("parkCode") String parkCode, @RequestBody Park park){
        Park parkObj = parkService.getByParkCode(parkCode);

        if(parkObj != null) {
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

            //Avoid duplicated park being added
            Set<Park> parks = new HashSet<>(parkService.getParks());
            parks.add(parkObj);

            parkService.saveAll(parks);
        }
    }


}
