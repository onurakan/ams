package com.onur.akan.ams.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.onur.akan.ams.AmsApplication;
import com.onur.akan.ams.bootstrap.AssetLoader;
import com.onur.akan.ams.controllers.mapper.AssetMapper;
import com.onur.akan.ams.domain.AmsEntityStatus;
import com.onur.akan.ams.domain.AssetEntity;
import com.onur.akan.ams.domain.SpecificationEntity;
import com.onur.akan.ams.repositories.AssetRepository;
import com.onur.akan.ams.services.implementations.AssetServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = AmsApplication.class)
@AutoConfigureMockMvc
@WithMockUser(username = "user", roles = {"USER", "ADMIN"})
@Setter
@Slf4j
@RequiredArgsConstructor
public class AssetControllerTest {
    
    private String API_V1_ASSET = AssetController.API_V_1_ASSET;

    @Autowired
    private AssetMapper assetMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AssetRepository assetRepository;

    @MockBean
    private AssetLoader assetLoader;//in order to ignore this loader, mock it out.

    private static Gson gson;

    @BeforeAll
    public static void setup () {
        gson = new GsonBuilder().create();
    }

    @Test
    public void assetRepository_is_initialized() {
        assertThat(assetRepository, is(not(nullValue())));
    }

    @Test
    public void should_read_asset_non() throws Exception {
        UUID assetId = UUID.randomUUID();
        when(assetRepository.readByAssetId(assetId)).thenReturn(Optional.ofNullable(null));

        this.mockMvc.perform(get(API_V1_ASSET+"/"+assetId)).andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    public void should_read_asset_one() throws Exception {
        UUID assetId = UUID.randomUUID();
        AssetEntity assetEntity  = AssetEntity.builder().assetId(assetId).build();
        when(assetRepository.readByAssetId(assetId)).thenReturn(Optional.of(assetEntity));

        this.mockMvc.perform(get(API_V1_ASSET + "/" + assetId))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().json(gson.toJson(assetMapper.assetEntityToAsset(assetEntity))));
    }

    @Test
    public void should_read_asset_all() throws Exception {
        AssetEntity assetEntity1 = AssetEntity.builder().assetId(UUID.randomUUID()).build();
        AssetEntity assetEntity2 = AssetEntity.builder().assetId(UUID.randomUUID()).build();
        AssetEntity assetEntity3 = AssetEntity.builder().assetId(UUID.randomUUID()).build();
        AssetEntity assetEntity4 = AssetEntity.builder().assetId(UUID.randomUUID()).build();
        when(assetRepository.findAll()).thenReturn(Arrays.asList(assetEntity1,assetEntity2,assetEntity3,assetEntity4));

        String out_asset = gson.toJson(Arrays.asList(assetMapper.assetEntityToAsset(assetEntity1),
                                                    assetMapper.assetEntityToAsset(assetEntity2),
                                                    assetMapper.assetEntityToAsset(assetEntity3),
                                                    assetMapper.assetEntityToAsset(assetEntity4)));

        this.mockMvc.perform(get(API_V1_ASSET))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(out_asset));
    }


    @Test
    public void should_read_asset_by_filter() throws Exception {
        UUID assetId = UUID.randomUUID();
        AssetEntity in_assetEntity = AssetEntity.builder().status(AmsEntityStatus.ACTIVE.toString()).classification("NEW").description("New Asset Creation").assetTag("A_TAG").build();
        AssetEntity out_assetEntity = AssetEntity.builder().assetId(assetId).status(AmsEntityStatus.ACTIVE.toString()).classification("NEW").description("New Asset Creation").assetTag("A_TAG").build();

        when(assetRepository.findAll(Example.of(in_assetEntity, AssetServiceImpl.CUSTOM_EXAMPLE_MATCHER), PageRequest.of(0, 100, Sort.by("id").ascending()))).thenReturn(new PageImpl(Arrays.asList(out_assetEntity)));

        JsonObject jsonObject = new JsonObject();
        jsonObject.add("data", gson.toJsonTree(Arrays.asList(assetMapper.assetEntityToAsset(out_assetEntity))));
        jsonObject.add("previousPage", null);
        jsonObject.add("nextPage", null);

        mockMvc.perform(post(API_V1_ASSET+"/1/100")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(assetMapper.assetEntityToAsset(in_assetEntity))))
                .andExpect(status().isOk())
                .andExpect(content().json(gson.toJson(jsonObject)));
    }

    @Test
    public void should_create_asset() throws Exception {
        UUID assetId = UUID.randomUUID();

        AssetEntity in_assetEntity = AssetEntity.builder().classification("NEW").description("New Asset Creation").assetTag("A_TAG").build();
        AssetEntity out_assetEntity = AssetEntity.builder().assetId(assetId).status(AmsEntityStatus.ACTIVE.toString()).classification("NEW").description("New Asset Creation").assetTag("A_TAG").build();
        when(assetRepository.save(in_assetEntity)).thenReturn(out_assetEntity);

        mockMvc.perform(post(API_V1_ASSET)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(assetMapper.assetEntityToAsset(in_assetEntity))))
                .andExpect(status().isCreated())
                .andExpect(content().json(gson.toJson(assetMapper.assetEntityToAssetIgnoreSpecificationList(out_assetEntity))));
    }

    @Test
    public void should_create_asset_and_specification() throws Exception {
        UUID assetId = UUID.randomUUID();
        UUID specificationId = UUID.randomUUID();

        List<SpecificationEntity> in_specificationEntities = Arrays.asList(SpecificationEntity.builder().attribute("AN_ATTR").attributeDescription("AN_ATTR_DESC").dataType("A_DT").alphanumericValue("A_NV").alphanumericDescription("A_AD").numericValue("A_NV").numericDescription("A_ND").unitOfMeasure("A_UD").tableValue("A_TV").build());
        List<SpecificationEntity> out_specificationEntities = Arrays.asList(SpecificationEntity.builder().specificationId(specificationId).attribute("AN_ATTR").attributeDescription("AN_ATTR_DESC").dataType("A_DT").alphanumericValue("A_NV").alphanumericDescription("A_AD").numericValue("A_NV").numericDescription("A_ND").unitOfMeasure("A_UD").tableValue("A_TV").build());
        AssetEntity in_assetEntity = AssetEntity.builder().classification("NEW").description("New Asset Creation").assetTag("A_TAG").specificationList(in_specificationEntities).build();
        AssetEntity out_assetEntity = AssetEntity.builder().assetId(assetId).status(AmsEntityStatus.ACTIVE.toString()).classification("NEW").description("New Asset Creation").assetTag("A_TAG").specificationList(out_specificationEntities).build();
        when(assetRepository.save(in_assetEntity)).thenReturn(out_assetEntity);


        mockMvc.perform(post(API_V1_ASSET)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(assetMapper.assetEntityToAsset(in_assetEntity))))
                .andExpect(status().isCreated())
                .andExpect(content().json(gson.toJson(assetMapper.assetEntityToAsset(out_assetEntity))));
    }
}
