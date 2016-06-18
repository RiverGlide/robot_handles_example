package com.riverglide.demo.robot_handles;

import com.riverglide.demo.robot_handles.handles.IAnswerQuestions;
import com.riverglide.demo.robot_handles.handles.RobotHandleFor;
import com.riverglide.demo.robot_handles.problems.NotARobotHandleComplaint;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.lang.String.format;

public class Robot {
    private final Map<String, IAnswerQuestions> available_handles;

    public Robot() {
        available_handles = new HashMap<>();
        Set<Class<?>> known_handles = new Reflections().getTypesAnnotatedWith(RobotHandleFor.class);
        known_handles.forEach(
            handle -> available_handles.put(handle.getAnnotation(RobotHandleFor.class).value(), create(handle))
        );
    }

    public void exchange(Map<String, String> questions_and_answers, WebDriver with_user) {
        List<WebElement> questions_from_user = with_user.findElements(By.cssSelector("[data-question]"));
        for(WebElement question : questions_from_user) {
            String question_type = question.getAttribute("data-question-type");
            IAnswerQuestions question_handler = available_handles.get(question_type);
            if(question_handler == null) {
                System.out.println(format("I don't know how to answer a question of type [%s]", question_type));
                continue;
            }
            question_handler.answer(question, "Groom");
        }
    }

    private IAnswerQuestions create(Class<?> handle) {
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