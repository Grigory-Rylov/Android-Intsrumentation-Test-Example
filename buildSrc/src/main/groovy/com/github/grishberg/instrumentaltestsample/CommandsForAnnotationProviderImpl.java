package com.github.grishberg.instrumentaltestsample;

import com.github.grishberg.tests.CommandsForAnnotationProvider;
import com.github.grishberg.tests.planner.AnnotationInfo;
import com.github.grishberg.tests.commands.DeviceRunnerCommand;

import java.util.List;

/**
 * Created by grishberg on 27.04.18.
 */

public class CommandsForAnnotationProviderImpl implements CommandsForAnnotationProvider {
    @Override
    public List<DeviceRunnerCommand> provideCommand(List<AnnotationInfo> annotation) {
        return null;
    }
}
