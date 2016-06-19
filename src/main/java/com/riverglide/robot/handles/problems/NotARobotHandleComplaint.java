package com.riverglide.robot.handles.problems;

import com.riverglide.robot.handles.IAnswerQuestions;
import com.riverglide.robot.handles.RobotHandleFor;

import static java.lang.String.format;

public class NotARobotHandleComplaint extends RuntimeException {
    public NotARobotHandleComplaint(Class<?> handle, Exception e) {
        super(format("Looks like the class [%s] is annotated with @%s but doesn't implement %s",
            handle.getCanonicalName(),
            RobotHandleFor.class.getSimpleName(),
            IAnswerQuestions.class.getSimpleName()
        ), e);
    }
}