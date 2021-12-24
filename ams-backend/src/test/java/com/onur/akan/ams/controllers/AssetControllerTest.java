package com.onur.akan.ams.controllers;

import com.onur.akan.ams.domain.AssetEntity;
import com.onur.akan.ams.repositories.AssetRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class AssetControllerTest {

    @Mock
    private AssetRepository assetRepository;

    @InjectMocks
    private AssetController assetController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(assetController).build();
    }

    @Test
    public void readAllAssets_success() throws Exception{

        when(assetRepository.findAll()).thenReturn(Arrays.asList(AssetEntity.builder().id(1L).build(),
                                                    AssetEntity.builder().id(1L).build(),
                                                    AssetEntity.builder().id(3L).build(),
                                                    AssetEntity.builder().id(4L).build()));

        mockMvc.perform(get("/api/assets/"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{assetId : 1, status=0}, {assetId : 2, status=0}, {assetId : 3, status=0}, {assetId : 4, status=0}]"));
    }

}
