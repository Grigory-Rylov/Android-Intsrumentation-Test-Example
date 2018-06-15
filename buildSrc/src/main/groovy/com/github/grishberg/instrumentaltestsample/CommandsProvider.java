package com.github.grishberg.instrumentaltestsample;

import com.github.grishberg.tests.ConnectedDeviceWrapper;
import com.github.grishberg.tests.Environment;
import com.github.grishberg.tests.commands.DeviceRunnerCommand;
import com.github.grishberg.tests.commands.DeviceRunnerCommandProvider;
import com.github.grishberg.tests.commands.ExecuteCommandException;
import com.github.grishberg.tests.planner.InstrumentalTestPlanProvider;

import org.gradle.api.logging.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by grishberg on 27.04.18.
 */
public class CommandsProvider implements DeviceRunnerCommandProvider {
    private final Logger logger;

    public CommandsProvider(Logger logger) {
        this.logger = logger;
    }

    @Override
    public List<DeviceRunnerCommand> provideCommandsForDevice(ConnectedDeviceWrapper device,
                                                              InstrumentalTestPlanProvider testPlanProvider,
                                                              Environment environment) throws ExecuteCommandException {
        ArrayList<DeviceRunnerCommand> deviceRunnerCommands = new ArrayList<>();
        logger.info("provideCommandsForDevice  {}, density is: {}", device, device.getDensity());
        return deviceRunnerCommands;
    }
}
