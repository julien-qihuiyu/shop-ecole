package com.qihui.o2o;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * configure spring and junit, junit loads IOC containers when launch 
 * @author qihuiyu
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
// tell junit where spring file locate
@ContextConfiguration({"classpath:spring/spring-dao.xml",
	"classpath:spring/spring-service.xml"})
public class BaseTest {

}
