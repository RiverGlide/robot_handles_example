package com.riverglide.demo.robot_handles;

import com.riverglide.demo.robot_handles.page_objects.ServicesPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TypicalExamples {
    private WebDriver user;

    @Before
    public void give_me_a_browser() {
        user = new ChromeDriver();
        user.get("http://localhost:8000/services.html");
    }

    @After
    public void let_me_see_the_results() throws Exception {
        Thread.sleep(2000);
        user.close();
    }

    @Test
    public void a_place_to_start() throws Exception {
        new Select(user.findElement(By.cssSelector("#services_select > select"))).selectByVisibleText("Groom");
        user.findElement(By.cssSelector("#services_radio > input[value='Groom']")).click();
        user.findElement(By.cssSelector("#services_text > input")).sendKeys("Groom");
    }

    @Test
    public void page_object_example() throws Exception {
        ServicesPage servicesPage = new ServicesPage(user);
        servicesPage.first_service("Groom");
        servicesPage.second_service("Groom");
        servicesPage.third_service("Groom");
    }
}