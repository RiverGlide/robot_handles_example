package com.riverglide.robot.handles;

import com.riverglide.robot.handles.problems.NotARobotHandleComplaint;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static java.lang.String.format;

public class RobotHandle {
    private static final IAnswerQuestions DONT_KNOW_HOW_TO_ANSWER =
            (question, answer) -> System.out.println(format("I don't know how to answer a question in the form [%s]", question.getAttribute("data-question-type")));
    private static Map<String, IAnswerQuestions> available_handles = new HashMap<>();

    public static IAnswerQuestions for_(String question_type) {
        if (available_handles.isEmpty()) { available_handles = findKnownRobotHandles(); }

        return available_handles.getOrDefault(question_type, DONT_KNOW_HOW_TO_ANSWER);

    }

    private static Map<String, IAnswerQuestions> findKnownRobotHandles() {
        System.out.println("Searching for robot handles");
        Map<String, IAnswerQuestions> available_handles = new HashMap<>();
        Set<Class<?>> known_handles = new Reflections().getTypesAnnotatedWith(RobotHandleFor.class);
        known_handles.forEach(
                handle -> available_handles.put(handle.getAnnotation(RobotHandleFor.class).value(), create(handle))
        );
        return available_handles;
    }

    private static IAnswerQuestions create(Class<?> handle) {
        try {
            return (IAnswerQuestions) handle.newInstance();
        }
        catch (ClassCastException e) {
            throw new NotARobotHandleComplaint(handle, e);
        }
        catch (Exception e) {
            throw new UnsupportedOperationException("TODO - Handle this", e);
        }
    }
}