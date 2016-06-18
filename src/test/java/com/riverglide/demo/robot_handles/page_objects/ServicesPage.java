package com.riverglide.demo.robot_handles.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import static java.lang.String.format;

public class ServicesPage {
    private final WebDriver user;

    public ServicesPage(WebDriver driver) {
        this.user = driver;
    }

    public void first_service(String service_choice) {
        new Select(user.findElement(By.cssSelector("#services_select > select"))).selectByVisibleText(service_choice);
    }

    public void second_service(String service_choice) {
        user.findElement(By.cssSelector(format("#services_radio > input[value='%s']",service_choice))).click();
    }

    public void third_service(String service_choice) {
        user.findElement(By.cssSelector("#services_text > input")).sendKeys(service_choice);
    }
}
