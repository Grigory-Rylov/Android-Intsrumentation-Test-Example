package com.github.grishberg.instrumentaltestsample;

import com.android.ddmlib.testrunner.TestIdentifier;
import com.github.grishberg.tests.ConnectedDeviceWrapper;
import com.github.grishberg.tests.Environment;
import com.github.grishberg.tests.InstrumentationArgsProvider;
import com.github.grishberg.tests.XmlReportGeneratorDelegate;
import com.github.grishberg.tests.commands.CommandExecutionException;
import com.github.grishberg.tests.commands.DeviceRunnerCommand;
import com.github.grishberg.tests.commands.DeviceRunnerCommandProvider;
import com.github.grishberg.tests.commands.SingleInstrumentalTestCommand;
import com.github.grishberg.tests.planner.InstrumentalTestPlanProvider;
import com.github.grishberg.tests.planner.TestPlanElement;

import org.gradle.api.logging.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by grishberg on 27.04.18.
 */
public class CustomCommandsProvider implements DeviceRunnerCommandProvider {
    private final String projectName;
    private final Logger logger;
    private final InstrumentationArgsProvider argsProvider;

    public CustomCommandsProvider(String projectName, Logger logger,
                                  InstrumentationArgsProvider argsProvider) {
        this.projectName = projectName;
        this.logger = logger;
        this.argsProvider = argsProvider;
    }

    @Override
    public List<DeviceRunnerCommand> provideCommandsForDevice(ConnectedDeviceWrapper device,
                                                              InstrumentalTestPlanProvider testPlanProvider,
                                                              Environment environment) throws CommandExecutionException {
        ArrayList<DeviceRunnerCommand> deviceRunnerCommands = new ArrayList<>();
        Map<String, String> args = argsProvider.provideInstrumentationArgs(device);
        logger.info("provideCommandsForDevice  {}, density is: {}", device, device.getDensity());
        List<TestPlanElement> testPlanElements = testPlanProvider
                .provideInstrumentalTests(device, args)
                .provideCompoundTestPlan();
        SingleInstrumentalTestCommand command = new SingleInstrumentalTestCommand(
                projectName, "pref", args, testPlanElements, new XmlReportDelegate(),
                SingleInstrumentalTestCommand.RetryHandler.NOOP);
        deviceRunnerCommands.add(command);
        return deviceRunnerCommands;
    }

    private static class XmlReportDelegate implements XmlReportGeneratorDelegate {
        @NotNull
        @Override
        public Map<String, String> provideProperties() {
            HashMap<String, String> properties = new HashMap<>();
            properties.put("enabledFeatures", "someEmptyFeature");
            return properties;
        }

        @NotNull
        @Override
        public Map<String, String> provideAdditionalAttributesForTest(@NotNull TestIdentifier testIdentifier) {
            Map<String, String> additionalParams = new HashMap<>();
            additionalParams.put("customAttribute", "testName=" + testIdentifier.getTestName());
            return additionalParams;
        }
    }
}
