package com.riverglide.robot.handles;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@RobotHandleFor("html-text")
public class HtmlText implements IAnswerQuestions {
    @Override
    public void answer(WebElement question, String answer) {
        question.findElement(By.cssSelector("input")).sendKeys(answer);
    }
}