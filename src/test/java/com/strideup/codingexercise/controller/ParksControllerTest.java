package com.strideup.codingexercise.controller;

import com.strideup.codingexercise.CodingexerciseApplication;
import com.strideup.codingexercise.model.Park;
import com.strideup.codingexercise.repository.ParkRepository;
import com.strideup.codingexercise.service.ParkService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.util.Arrays;

import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.MOCK, classes = CodingexerciseApplication.class)
public class ParksControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @MockBean
    private ParkService parkServiceMock;

    @MockBean
    private ParkRepository parkRepository;

    @InjectMocks
    private ParksController parksController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        Mockito.reset(parkServiceMock);
        this.mockMvc = webAppContextSetup(wac).build();
    }

    @Test
    public void getParks_should_return_all_parks() throws Exception{

        Park park1 = new Park.Builder("Park Testing1")
                .withParkCode("T1")
                .withDescription("park testing 1")
                .build();

        Park park2 = new Park.Builder("Park Testing2")
                .withParkCode("T2")
                .withDescription("park testing 2")
                .build();

        when(parkServiceMock.getParks()).thenReturn(Arrays.asList(park1, park2));

        mockMvc.perform(get("/api/parks")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$[0].parkCode", Matchers.is("T1")))
                .andExpect(jsonPath("$[1].parkCode", Matchers.is("T2")));
        verify(parkServiceMock, times(2)).getParks();
    }

    @Test
    public void get_park_by_code_should_return_park() throws Exception{
        Park park1 = new Park.Builder("Park Testing1")
                .withParkCode("t1")
                .withDescription("park testing 1")
                .build();

        when(parkServiceMock.getByParkCode(any(String.class))).thenReturn(park1);

        mockMvc.perform(get("/api/parks/t1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.parkCode", Matchers.is("t1")));

        verify(parkServiceMock, times(1)).getByParkCode(any(String.class));
    }

    @Test
    public void add_park_should_add_new_park()throws Exception{
        Park park1 = new Park.Builder("Park Testing1")
                .withParkCode("t3")
                .withDescription("park testing 3")
                .build();

        when(parkRepository.save(park1)).thenReturn(park1);

        mockMvc.perform(post("/api/parks")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"parCode\": \"t3\", \"description\": \"park testing 3\" }") )
                .andExpect(status().isOk());

        verify(parkServiceMock, times(1)).savePark(any(Park.class));
    }
}
