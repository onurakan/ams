package com.onur.akan.ams.web;

import com.onur.akan.ams.AmsBusinessApiFactory;
import com.onur.akan.ams.business.asset.AmsAsset;
import com.onur.akan.ams.business.asset.AmsAssetBuilder;
import com.onur.akan.ams.business.asset.AmsAssetRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class AssetReadControllerTest {

    @Mock
    private AmsAssetRepository amsAssetRepository;

    @InjectMocks
    private AssetReadController assetReadController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(assetReadController).build();
    }


    @Test
    public void readAllAssets_success() throws Exception{
        AmsBusinessApiFactory.setAmsAssetRepository(amsAssetRepository);

        List<AmsAsset> amsAssetList = new ArrayList<>();
        amsAssetList.add(AmsAssetBuilder.aAmsAsset().withAssetId(1L).withStatus(0).build());
        amsAssetList.add(AmsAssetBuilder.aAmsAsset().withAssetId(2L).withStatus(0).build());
        when(amsAssetRepository.readAssetAll()).thenReturn(amsAssetList);

        mockMvc.perform(get("/api/assets/"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{assetId : 1, status=0}, {assetId : 2, status=0}]"));
    }
}
