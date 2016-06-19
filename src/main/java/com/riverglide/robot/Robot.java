package com.riverglide.robot;

import com.riverglide.robot.handles.RobotHandle;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.*;

import static java.util.stream.Collectors.toMap;

public class Robot {
    private Map<String, String> known_questions_and_answers = new HashMap<>();
    private Set<String> questions_to_be_answered = new HashSet<>();

    public void knows(String question, String answer) {
        known_questions_and_answers.put(question, answer);
    }

    public String knows(String question) {
        return known_questions_and_answers.getOrDefault(question, "SORRY - I don't have the answer");
    }

    public void wants_to_know(String question) {
        questions_to_be_answered.add(question);
    }

    public void exchange_knowledge_with(WebDriver application) {
        query_the(application);
        inform_the(application);
    }

    private void query_the(WebDriver application) {
        Map<String,String> answers_from_application =
        application.findElements(By.cssSelector("[data-knowledge]")).stream().collect(
            toMap(e -> e.getAttribute("data-knowledge"), e -> e.getText())
        );

        for (String question_for_application : questions_to_be_answered) {
            if(answers_from_application.containsKey(question_for_application)) {
                known_questions_and_answers.put(question_for_application, answers_from_application.get(question_for_application));
            }
        }
    }

    private void inform_the(WebDriver application) {
        List<WebElement> questions_from_application = application.findElements(By.cssSelector("[data-question]"));
        for(WebElement question : questions_from_application) {
            String question_type = question.getAttribute("data-question-type");
            String answer = known_questions_and_answers.get(question.getAttribute("data-question"));

            RobotHandle.for_(question_type).answer(question, answer);
        }
    }
}