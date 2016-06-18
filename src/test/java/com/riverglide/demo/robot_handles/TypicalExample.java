package com.riverglide.demo.robot_handles;

import com.riverglide.robot.Robot;
import com.riverglide.demo.robot_handles.page_objects.ServicesPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.HashMap;
import java.util.Map;

public class TypicalExample {
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

    @Test
    public void robot_handles_example() {
        user.get("http://localhost:8000/services2.html");
        Map<String,String> questions_and_answers = new HashMap<>();
        questions_and_answers.put("service-required", "Groom");

        Robot robot = new Robot();
        robot.exchange(questions_and_answers, with(user));
    }

    @Test
    public void second_robot_example() {
        user.get("http://localhost:8000/robot_example2.html");
        Map<String,String> questions_and_answers = new HashMap<>();
        questions_and_answers.put("name", "Andy Palmer");
        questions_and_answers.put("country", "India");
        questions_and_answers.put("gender", "Male");

        Robot robot = new Robot();
        robot.exchange(questions_and_answers, with(user));
    }

    private <T> T with(T something) {
        return something;
    }
}