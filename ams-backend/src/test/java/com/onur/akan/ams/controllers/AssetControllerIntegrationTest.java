package com.onur.akan.ams.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.onur.akan.ams.AmsApplication;
import com.onur.akan.ams.controllers.model.AssetMapper;
import com.onur.akan.ams.domain.AmsEntityStatus;
import com.onur.akan.ams.domain.AssetEntity;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = AmsApplication.class)
@AutoConfigureMockMvc
@WithMockUser(username = "user", roles = {"USER", "ADMIN"})
@Setter
@Slf4j
@RequiredArgsConstructor
public class AssetControllerIntegrationTest {

    private String API_V1_ASSET = AssetController.API_V_1_ASSET;

    @Autowired
    private MockMvc mockMvc;

    private static Gson gson;

    @BeforeAll
    public static void setup () {
        gson = new GsonBuilder().create();
    }

    @Test
    public void should_read_asset_count_50() throws Exception {
        //Given AssetLoader creates 50 assets.

        this.mockMvc.perform(get(API_V1_ASSET))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(50)));
    }

    @Test
    public void should_read_asset_by_filter() throws Exception {
        //Given AssetLoader creates 50 assets.

        AssetEntity in_assetEntity = AssetEntity.builder().status(AmsEntityStatus.ACTIVE).build();

        mockMvc.perform(post(API_V1_ASSET+"/1/20")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(AssetMapper.INSTANCE.assetEntityToAsset(in_assetEntity)))
                        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", hasSize(20)))
                .andExpect(jsonPath("$.previousPage", is(nullValue())))
                .andExpect(jsonPath("$.nextPage", is("/asset/2/20")));
    }
}
