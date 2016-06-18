package com.riverglide.robot.handles;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

@RobotHandleFor("html-select")
public class HtmlSelect implements IAnswerQuestions {
    @Override
    public void answer(WebElement question, String answer) {
        new Select(question.findElement(By.cssSelector("select"))).selectByVisibleText(answer);
    }
}
