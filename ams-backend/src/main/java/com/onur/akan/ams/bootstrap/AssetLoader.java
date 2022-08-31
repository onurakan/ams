package com.onur.akan.ams.bootstrap;

import com.onur.akan.ams.services.AssetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@ConditionalOnProperty(
        prefix = "command.line.runner",
        value = "enabled",
        havingValue = "true",
        matchIfMissing = true)

@Component
@RequiredArgsConstructor
@Slf4j
public class AssetLoader implements CommandLineRunner {

    private final AssetService assetService;

    @Override
    public void run(String... args) throws Exception {

        /*Executors.newCachedThreadPool().submit(() -> {
            loadAssets(50, 10);
            return null;
        });*/

        assetService.insertInitialAssets(50, 10);
    }
}
