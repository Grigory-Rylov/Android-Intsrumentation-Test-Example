package com.github.grishberg.instrumentaltestsample;

import com.github.grishberg.tests.ConnectedDeviceWrapper;
import com.github.grishberg.tests.InstrumentationArgsProvider;

import org.gradle.api.logging.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by grishberg on 27.04.18.
 */

public class ArgsProvider implements InstrumentationArgsProvider {
    private static final String TABLET_ANNOTATION = "com.github.grishberg.instrumentaltestsample.TabletOnly";
    private final Logger logger;

    public ArgsProvider(Logger logger) {
        this.logger = logger;
    }

    @Override
    public Map<String, String> provideInstrumentationArgs(ConnectedDeviceWrapper device) {
        logger.info("provideCommandsForDevice  {}, density is: {}, width = {}, width in dp = {}",
                device, device.getDensity(), device.getWidth(), device.getWidthInDp());

        HashMap<String, String> argsMap = new HashMap<>();

        if (device.getWidthInDp() >= 600) {
            argsMap.put("annotation", TABLET_ANNOTATION);
        } else {
            argsMap.put("notAnnotation", TABLET_ANNOTATION);
        }
        return argsMap;
    }
}
