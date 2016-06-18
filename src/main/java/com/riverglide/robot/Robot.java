package com.riverglide.robot;

import com.riverglide.robot.handles.RobotHandle;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

public class Robot {
    public void exchange(Map<String, String> questions_and_answers, WebDriver with_user) {
        List<WebElement> questions_from_user = with_user.findElements(By.cssSelector("[data-question]"));
        for(WebElement question : questions_from_user) {
            String question_type = question.getAttribute("data-question-type");
            String answer = questions_and_answers.get(question.getAttribute("data-question"));

            RobotHandle.for_(question_type).answer(question, answer);
        }
    }
}