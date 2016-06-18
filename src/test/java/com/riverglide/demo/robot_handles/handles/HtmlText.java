package com.riverglide.demo.robot_handles.handles;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HtmlText implements IAnswerQuestions {
    @Override
    public void answer(WebElement question, String answer) {
        question.findElement(By.cssSelector("input")).sendKeys(answer);
    }
}
