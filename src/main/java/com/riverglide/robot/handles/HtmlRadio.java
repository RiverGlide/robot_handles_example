package com.riverglide.robot.handles;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static java.lang.String.format;

@RobotHandleFor("html-radio")
public class HtmlRadio implements IAnswerQuestions {
    @Override
    public void answer(WebElement question, String answer) {
        question.findElement(By.cssSelector(format("input[value='%s']",answer))).click();
    }
}
