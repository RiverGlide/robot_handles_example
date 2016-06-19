package com.riverglide.demo.robot_handles;

import com.riverglide.robot.Robot;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashMap;
import java.util.Map;

public class RobotExamples {
    private WebDriver application;

    @Before
    public void give_me_a_browser() {
        application = new ChromeDriver();
        application.get("http://localhost:8000/services.html");
    }

    @After
    public void let_me_see_the_results() throws Exception {
        Thread.sleep(2000);
        application.close();
    }

    @Test
    public void robot_handles_example() {
        application.get("http://localhost:8000/services2.html");
        Map<String,String> knowledge = new HashMap<>();
        knowledge.put("service-required", "Groom");

        Robot user = new Robot();
        user.exchange(knowledge, with(application));
    }

    @Test
    public void second_robot_example() {
        application.get("http://localhost:8000/robot_example2.html");
        Map<String,String> knowledge = new HashMap<>();
        knowledge.put("name", "Andy Palmer");
        knowledge.put("country", "India");
        knowledge.put("gender", "Male");

        Robot user = new Robot();
        user.exchange(knowledge, with(application));
    }

    private <T> T with(T something) {
        return something;
    }
}
