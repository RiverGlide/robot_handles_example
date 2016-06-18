package com.riverglide.demo.robot_handles;

import com.riverglide.demo.robot_handles.handles.HtmlSelect;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;

public class Robot {
    private static Map<String, HtmlSelect> available_handles = new HashMap<String, HtmlSelect>() {{
        put("html-select", new HtmlSelect());
    }};

    public void exchange(Map<String, String> questions_and_answers, WebDriver with_user) {
        List<WebElement> questions_from_user = with_user.findElements(By.cssSelector("[data-question]"));
        for(WebElement question : questions_from_user) {
            String question_type = question.getAttribute("data-question-type");
            HtmlSelect question_handler = available_handles.get(question_type);
            if(question_handler == null) {
                System.out.println(format("I don't know how to answer a question of type [%s]", question_type));
                continue;
            }
            question_handler.answer(question, "Groom");
        }
    }
}