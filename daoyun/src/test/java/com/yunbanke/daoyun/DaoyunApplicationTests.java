package com.yunbanke.daoyun;
import com.yunbanke.daoyun.Domain.entity.User;
import com.yunbanke.daoyun.Persistence.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DaoyunApplicationTests {

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void TestLogin() throws Exception{
        RequestBuilder requestBuilder = null;
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("email", "429101060@qq.com");
        map.add("password", "123");
        requestBuilder = get("/login").params(map);



        mockMvc.perform(requestBuilder);
    }

    @Test
    public void TestRegister() throws Exception{
        RequestBuilder requestBuilder = null;
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("email","429101060@qq.com");
        map.add("password", "123");
        requestBuilder = get("/resister").params(map);

        mockMvc.perform(requestBuilder);

    }
    @Test
    public void contextLoads() {
    }

}
