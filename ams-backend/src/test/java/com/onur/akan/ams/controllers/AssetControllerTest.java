package com.onur.akan.ams.controllers;

import com.onur.akan.ams.AmsApplication;
import com.onur.akan.ams.domain.AssetEntity;
import com.onur.akan.ams.domain.SpecificationEntity;
import com.onur.akan.ams.repositories.AssetRepository;
import com.onur.akan.ams.services.AssetService;
import com.onur.akan.ams.services.implementations.AssetServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = AmsApplication.class)
@AutoConfigureMockMvc
@WithMockUser(username = "user", roles = {"USER", "ADMIN"})
@Setter
@Slf4j
@RequiredArgsConstructor
public class AssetControllerTest {

    @Autowired
    private AssetController assetController;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AssetRepository assetRepository;

    private AssetService assetService;

    @Test
    public void assetController_is_initialized() {
        assertThat(assetRepository, is(not(nullValue())));
    }

    @Test
    public void should_read_asset_non() throws Exception {
        when(assetRepository.findById(1L)).thenReturn(Optional.ofNullable(null));

        this.mockMvc.perform(get("/api/asset/read/1")).andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    public void should_read_asset_one() throws Exception {
        when(assetRepository.findById(1L)).thenReturn(Optional.of(AssetEntity.builder().id(1L).build()));

        this.mockMvc.perform(get("/api/asset/read/1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().json("{assetId:1,status:null,classification:null,description:null,assetTag:null,specificationList:null}"));//;
    }

    @Test
    public void should_read_all_assets() throws Exception {
        when(assetRepository.findAll()).thenReturn(Arrays.asList(AssetEntity.builder().id(1L).build(),
                AssetEntity.builder().id(2L).build(),
                AssetEntity.builder().id(3L).build(),
                AssetEntity.builder().id(4L).build()
        ));
        String an_Asset = "{assetId:%s,status:null,classification:null,description:null,assetTag:null,specificationList:null}";
        this.mockMvc.perform(get("/api/asset/read")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(new StringBuilder().append("[")
                        .append(String.format(an_Asset, "1")).append(",")
                        .append(String.format(an_Asset, "2")).append(",")
                        .append(String.format(an_Asset, "3")).append(",")
                        .append(String.format(an_Asset, "4")).append("]").toString()));//;
    }

    /**
     {
         "assetId" : null,
         "status"  : 0,
         "classification" : "NEW",
         "description" : "New Asseet Creation",
         "assetTag": "new assetTag",
         "specificationList" : [
             {
                 "attribute" : "new_attribute",
                 "attributeDescription" : "new_attribute_description",
                 "dataType" : "new_dataType",
                 "alphanumericValue" : "new_aplhanumericValue",
                 "alphanumericDescription" : "new_alphanumeric_description",
                 "numericValue" : "new_numericValue",
                 "numericDescription" : "new_numericDescription",
                 "unitOfMeasure" : "new_unitOfMeasue",
                 "tableValue" : "new_tableValue"
             }
         ]
     }
     */
    @Test
    public void should_read_asset_by_filter() throws Exception {
        List<SpecificationEntity> in_specificationEntities = Arrays.asList(SpecificationEntity.builder().attribute("AN_ATTR").attributeDescription("AN_ATTR_DESC").dataType("A_DT").alphanumericValue("A_NV").alphanumericDescription("A_AD").numericValue("A_NV").numericDescription("A_ND").unitOfMeasure("A_UD").tableValue("A_TV").build());
        List<SpecificationEntity> out_specificationEntities = Arrays.asList(SpecificationEntity.builder().id(2L).attribute("AN_ATTR").attributeDescription("AN_ATTR_DESC").dataType("A_DT").alphanumericValue("A_NV").alphanumericDescription("A_AD").numericValue("A_NV").numericDescription("A_ND").unitOfMeasure("A_UD").tableValue("A_TV").build());
        AssetEntity in_assetEntity = AssetEntity.builder().status(0).classification("NEW").description("New Asset Creation").assetTag("A_TAG").specificationList(in_specificationEntities).build();
        AssetEntity out_assetEntity = AssetEntity.builder().id(1L).status(0).classification("NEW").description("New Asset Creation").assetTag("A_TAG").specificationList(out_specificationEntities).build();


        when(assetRepository.findAll(Example.of(in_assetEntity, AssetServiceImpl.CUSTOM_EXAMPLE_MATCHER), PageRequest.of(0, 100, Sort.by("id").ascending()))).thenReturn(new PageImpl(Arrays.asList(out_assetEntity)));

        String inAsset = "{\"assetId\":null,\"status\":0,\"classification\":\"NEW\",\"description\":\"New Asset Creation\",\"assetTag\":\"A_TAG\",\"specificationList\":[" +
                "{\"id\":null,\"attribute\":\"AN_ATTR\",\"attributeDescription\":\"AN_ATTR_DESC\",\"dataType\":\"A_DT\",\"alphanumericValue\":\"A_NV\",\"alphanumericDescription\":\"A_AD\",\"numericValue\":\"A_NV\",\"numericDescription\":\"A_ND\",\"unitOfMeasure\":\"A_UD\",\"tableValue\":\"A_TV\"}" +
                "]}";
        String assetTemplate = "{\"data\":[{\"assetId\":%s,\"status\":0,\"classification\":\"NEW\",\"description\":\"New Asset Creation\",\"assetTag\":\"A_TAG\",\"specificationList\":[" +
                "{\"id\":%s,\"attribute\":\"AN_ATTR\",\"attributeDescription\":\"AN_ATTR_DESC\",\"dataType\":\"A_DT\",\"alphanumericValue\":\"A_NV\",\"alphanumericDescription\":\"A_AD\",\"numericValue\":\"A_NV\",\"numericDescription\":\"A_ND\",\"unitOfMeasure\":\"A_UD\",\"tableValue\":\"A_TV\"}" +
                "]}],\"previousPage\":null,\"nextPage\":null}";

        String outAsset = String.format(assetTemplate, 1, 2);

        mockMvc.perform(post("/api/asset/read/filter/1/100").contentType(MediaType.APPLICATION_JSON).content(inAsset))
                .andExpect(status().isOk())
                .andExpect(content().json(outAsset));
    }


    /**
    {
        "assetId" : null,
            "status"  : 0,
            "classification" : "NEW",
            "description" : "New Asseet Creation",
            "assetTag": "new assetTag",
            "specificationList" : null
    }
     */
    @Test
    public void should_create_asset() throws Exception {
        AssetEntity in_assetEntity = AssetEntity.builder().status(0).classification("NEW").description("New Asset Creation").assetTag("A_TAG").build();
        AssetEntity out_assetEntity = AssetEntity.builder().id(1L).status(0).classification("NEW").description("New Asset Creation").assetTag("A_TAG").build();
        when(assetRepository.save(in_assetEntity)).thenReturn(out_assetEntity);

        String assetTemplate = "{\"assetId\":%s,\"status\":0,\"classification\":\"NEW\",\"description\":\"New Asset Creation\",\"assetTag\":\"A_TAG\",\"specificationList\":null}";
        String inAsset = String.format(assetTemplate, "null");
        String outAsset = String.format(assetTemplate, "1");


        mockMvc.perform(post("/api/asset/create").contentType(MediaType.APPLICATION_JSON).content(inAsset))
                .andExpect(status().isCreated())
                .andExpect(content().json(outAsset));
    }

    /**
     {
        "assetId" : null,
            "status"  : 0,
            "classification" : "NEW",
            "description" : "New Asseet Creation",
            "assetTag": "new assetTag",
            "specificationList" : [
                                    {
                                        "attribute" : "new_attribute",
                                            "attributeDescription" : "new_attribute_description",
                                            "dataType" : "new_dataType",
                                            "alphanumericValue" : "new_aplhanumericValue",
                                            "alphanumericDescription" : "new_alphanumeric_description",
                                            "numericValue" : "new_numericValue",
                                            "numericDescription" : "new_numericDescription",
                                            "unitOfMeasure" : "new_unitOfMeasue",
                                            "tableValue" : "new_tableValue"
                                    }
                                ]
    }
     */
    @Test
    public void should_create_asset_and_specification() throws Exception {
        List<SpecificationEntity> in_specificationEntities = Arrays.asList(SpecificationEntity.builder().attribute("AN_ATTR").attributeDescription("AN_ATTR_DESC").dataType("A_DT").alphanumericValue("A_NV").alphanumericDescription("A_AD").numericValue("A_NV").numericDescription("A_ND").unitOfMeasure("A_UD").tableValue("A_TV").build());
        List<SpecificationEntity> out_specificationEntities = Arrays.asList(SpecificationEntity.builder().id(2L).attribute("AN_ATTR").attributeDescription("AN_ATTR_DESC").dataType("A_DT").alphanumericValue("A_NV").alphanumericDescription("A_AD").numericValue("A_NV").numericDescription("A_ND").unitOfMeasure("A_UD").tableValue("A_TV").build());
        AssetEntity in_assetEntity = AssetEntity.builder().status(0).classification("NEW").description("New Asset Creation").assetTag("A_TAG").specificationList(in_specificationEntities).build();
        AssetEntity out_assetEntity = AssetEntity.builder().id(1L).status(0).classification("NEW").description("New Asset Creation").assetTag("A_TAG").specificationList(out_specificationEntities).build();
        when(assetRepository.save(in_assetEntity)).thenReturn(out_assetEntity);

        String assetTemplate = "{\"assetId\":%s,\"status\":0,\"classification\":\"NEW\",\"description\":\"New Asset Creation\",\"assetTag\":\"A_TAG\",\"specificationList\":[" +
        "{\"id\":%s,\"attribute\":\"AN_ATTR\",\"attributeDescription\":\"AN_ATTR_DESC\",\"dataType\":\"A_DT\",\"alphanumericValue\":\"A_NV\",\"alphanumericDescription\":\"A_AD\",\"numericValue\":\"A_NV\",\"numericDescription\":\"A_ND\",\"unitOfMeasure\":\"A_UD\",\"tableValue\":\"A_TV\"}" +
                "]}";
        String inAsset = String.format(assetTemplate, "null", "null");
        String outAsset = String.format(assetTemplate, 1, 2);

        mockMvc.perform(post("/api/asset/create").contentType(MediaType.APPLICATION_JSON).content(inAsset))
                .andExpect(status().isCreated())
                .andExpect(content().json(outAsset));
    }
}
