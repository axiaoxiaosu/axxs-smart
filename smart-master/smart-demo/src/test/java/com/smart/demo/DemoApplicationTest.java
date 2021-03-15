package com.smart.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;


//    @Before
//    public void before() {
//        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
//    }

    @Test
    public void actTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/demo/list")).andReturn();
        System.out.println("输出 " + mvcResult.getResponse().getContentAsString());
    }
}
