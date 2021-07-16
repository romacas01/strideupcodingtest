package com.strideup.codingexercise.Service;


import com.strideup.codingexercise.model.Park;
import com.strideup.codingexercise.repository.ParkRepository;
import com.strideup.codingexercise.service.ParkService;
import com.strideup.codingexercise.service.ParkServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class ParksServiceTest {

    @Mock
    private ParkRepository parkRepository;

    @Autowired
    @InjectMocks
    private ParkServiceImpl parkService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getParks_returns_parks(){

        Park park1 = new Park.Builder("Park Testing1")
                .withParkCode("T1")
                .withDescription("park testing 1")
                .build();

        Park park2 = new Park.Builder("Park Testing2")
                .withParkCode("T2")
                .withDescription("park testing 2")
                .build();

        when(parkRepository.findAll()).thenReturn(Arrays.asList(park1,park2));

        List<Park> parks = parkService.getParks();

        Assert.assertEquals(2, parks.size());
    }

    @Test
    public void getByParkCode_returns_parks_by_code(){

        Park park1 = new Park.Builder("Park Testing1")
                .withParkCode("T1")
                .withDescription("park testing 1")
                .build();

        Park park2 = new Park.Builder("Park Testing2")
                .withParkCode("T2")
                .withDescription("park testing 2")
                .build();

        when(parkRepository.getByParkCode("t1")).thenReturn(park1);

        Park park = parkService.getByParkCode("t1");

        Assert.assertNotNull(park);
        Assert.assertEquals("park testing 1", park1.getDescription());
    }

    @Test
    public void savePark_saves_park(){

        Park park1 = new Park.Builder("Park Testing1")
                .withParkCode("T1")
                .withDescription("park testing 1")
                .build();

        parkService.savePark(park1);

        verify(parkRepository, times(1)).save(park1);
    }
}