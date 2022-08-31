package com.onur.akan.ams.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.onur.akan.ams.controllers.mapper.AssetMapper;
import com.onur.akan.ams.controllers.model.AssetDto;
import com.onur.akan.ams.domain.AmsEntityStatus;
import com.onur.akan.ams.domain.AssetEntity;
import com.onur.akan.ams.domain.SpecificationEntity;
import com.onur.akan.ams.repositories.AssetRepository;
import com.onur.akan.ams.services.implementations.AssetServiceImpl;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
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
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Do not use: import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
 *
 */

@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureRestDocs(uriScheme = "http", uriHost = "localhost", uriPort = 8080)

@WebMvcTest(AssetController.class)
@ComponentScan(basePackages = {"com.onur.akan.ams.services", "com.onur.akan.ams.controllers.mapper"})
@AutoConfigureDataJpa
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = AmsApplication.class)
//@AutoConfigureMockMvc

@WithMockUser(username = "user", roles = {"USER", "ADMIN"})
@RequiredArgsConstructor
public class AssetControllerTest extends BaseTest {
    
    private String API_V1_ASSET = AssetController.API_V_1_ASSET;

    @Autowired
    private AssetMapper assetMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AssetRepository assetRepository;

    private static Gson gson;

    @Autowired
    private ObjectMapper objectMapper;

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
        AssetEntity assetEntity  = aAsset(assetId);
        when(assetRepository.readByAssetId(assetId)).thenReturn(Optional.of(assetEntity));

        AssetDto assetDto = assetMapper.assetEntityToAsset(assetEntity);

        this.mockMvc.perform(get(API_V1_ASSET + "/{assetId}", assetId))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().json(objectMapper.writeValueAsString(assetDto)))
                    .andDo(document("v1/asset-read",
                            pathParameters(
                                    parameterWithName("assetId").description("UUID of desired asset to get.")
                            ),
                            responseFields(
                                    fieldWithPath("assetId").description("UUID of asset."),
                                    fieldWithPath("status").description("Status of asset."),
                                    fieldWithPath("assetTag").description("Tag of asset."),
                                    fieldWithPath("classification").description("Classification of asset."),
                                    fieldWithPath("description").description("Description of asset."),
                                    fieldWithPath("price").description("Price of asset."),
                                    fieldWithPath("createDate").description("Create date of asset.")
                            )));
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

        AssetEntity in_assetEntity = newAsset(assetId);
        AssetEntity out_assetEntity = aAsset(assetId);

        when(assetRepository.save(in_assetEntity)).thenReturn(out_assetEntity);

        ConstrainedFields fields = new ConstrainedFields(AssetDto.class);

        mockMvc.perform(post(API_V1_ASSET)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(assetMapper.assetEntityToAsset(in_assetEntity))))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(assetMapper.assetEntityToAssetIgnoreSpecificationList(out_assetEntity))))
                .andDo(document("v1/asset-create",
                        requestFields(
                                //fields.withPath("assetId").ignored(),
                                fields.withPath("assetTag").description("Tag of asset."),
                                fields.withPath("classification").description("Classification of asset."),
                                fields.withPath("description").description("Description of asset."),
                                fields.withPath("price").description("Price of asset.")
                        ),
                        responseFields(
                                fieldWithPath("assetId").description("UUID of asset."),
                                fieldWithPath("status").description("Status of asset."),
                                fieldWithPath("assetTag").description("Tag of asset."),
                                fieldWithPath("classification").description("Classification of asset."),
                                fieldWithPath("description").description("Description of asset."),
                                fieldWithPath("price").description("Price of asset."),
                                fieldWithPath("createDate").description("Create date of asset.")
                        )));;
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
