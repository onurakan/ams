package com.onur.akan.ams.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.onur.akan.ams.AmsApplication;
import com.onur.akan.ams.controllers.model.SpecificationMapper;
import com.onur.akan.ams.domain.SpecificationEntity;
import com.onur.akan.ams.repositories.SpecificationRepository;
import com.onur.akan.ams.services.SpecificationService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
public class SpecificationControllerTest {

    private String API_V1_SPECIFICATION = SpecificationController.API_V_1_SPECIFICATION;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SpecificationRepository specificationRepository;

    private static Gson gson;

    @BeforeAll
    public static void setup () {
        gson = new GsonBuilder().create();
    }

    @Test
    public void specificationRepository_is_initialized() {
        assertThat(specificationRepository, is(not(nullValue())));
    }

    @Test
    public void should_read_specification_non() throws Exception {
        UUID specificationId = UUID.randomUUID();
        when(specificationRepository.findBySpecificationId(specificationId)).thenReturn(Optional.ofNullable(null));

        this.mockMvc.perform(get(API_V1_SPECIFICATION+"/"+specificationId)).andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    public void should_read_specification_one() throws Exception {
        UUID specificationId = UUID.randomUUID();
        SpecificationEntity specificationEntity  = SpecificationEntity.builder().specificationId(specificationId).build();
        when(specificationRepository.findBySpecificationId(specificationId)).thenReturn(Optional.of(specificationEntity));

        this.mockMvc.perform(get(API_V1_SPECIFICATION+"/"+specificationId)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(gson.toJson(SpecificationMapper.INSTANCE.specificationEntityToSpecification(specificationEntity))));
    }

    @Test
    public void should_read_specification_all() throws Exception {
        SpecificationEntity specificationEntity1 = SpecificationEntity.builder().specificationId(UUID.randomUUID()).build();
        SpecificationEntity specificationEntity2 = SpecificationEntity.builder().specificationId(UUID.randomUUID()).build();
        SpecificationEntity specificationEntity3 = SpecificationEntity.builder().specificationId(UUID.randomUUID()).build();
        SpecificationEntity specificationEntity4 = SpecificationEntity.builder().specificationId(UUID.randomUUID()).build();
        when(specificationRepository.findAll()).thenReturn(Arrays.asList(specificationEntity1,specificationEntity2,specificationEntity3,specificationEntity4));

        String out_asset = gson.toJson(Arrays.asList(SpecificationMapper.INSTANCE.specificationEntityToSpecification(specificationEntity1),
                SpecificationMapper.INSTANCE.specificationEntityToSpecification(specificationEntity2),
                SpecificationMapper.INSTANCE.specificationEntityToSpecification(specificationEntity3),
                SpecificationMapper.INSTANCE.specificationEntityToSpecification(specificationEntity4)));

        this.mockMvc.perform(get(API_V1_SPECIFICATION)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(out_asset));
    }
}
