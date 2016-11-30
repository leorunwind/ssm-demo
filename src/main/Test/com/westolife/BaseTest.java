package com.westolife;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by saigao on 16/8/6.
 */
@ContextConfiguration(locations = {"classpath:spring-mvc.xml"})
@RunWith(SpringJUnit4ClassRunner.class) //告诉JUnit使用Spring TestRunner
public class BaseTest extends AbstractJUnit4SpringContextTests {
}
