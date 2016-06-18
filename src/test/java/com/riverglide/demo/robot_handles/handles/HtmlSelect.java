package com.riverglide.demo.robot_handles.handles;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class HtmlSelect {
    public void answer(WebElement question, String answer) {
        new Select(question.findElement(By.cssSelector("select"))).selectByVisibleText(answer);
    }
}
