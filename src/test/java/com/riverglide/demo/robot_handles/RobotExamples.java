package com.riverglide.demo.robot_handles;

import com.riverglide.robot.Robot;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

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
        Robot user = new Robot();
        user.knows("service-required", "Groom");

        user.exchange_knowledge_with(application);
    }

    @Test
    public void second_robot_example() {
        application.get("http://localhost:8000/robot_example2.html");
        Robot user = new Robot();
        user.knows("name", "Andy Palmer");
        user.knows("gender", "Male");
        user.knows("country", "India");
        user.wants_to_know("name-of-event");

        user.exchange_knowledge_with(application);

        assertThat(user.knows("name-of-event"), is(equalTo("Selenium Conf 2016")));
    }
}