package com.example.demo;

import com.example.demo.infrastructure.Repository.PasswordRepository;
import com.example.demo.infrastructure.Repository.RoleRepository;
import com.example.demo.infrastructure.Repository.UserRepository;
import net.bytebuddy.asm.Advice;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.apache.shiro.web.subject.WebSubject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest
public class DemoApplicationTests {

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordRepository passwordRepository;
    @Resource
    private SecurityManager securityManager;

    @Before
    public void setUp(){
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest(webApplicationContext.getServletContext());
        MockHttpServletResponse mockHttpServletResponse = new MockHttpServletResponse();
        MockHttpSession mockHttpSession = new MockHttpSession(webApplicationContext.getServletContext());
        SecurityUtils.setSecurityManager(securityManager);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//        Subject subject = new WebSubject.Builder(mockHttpServletRequest,mockHttpServletResponse).buildWebSubject();
//        UsernamePasswordToken token = new UsernamePasswordToken("100@qq.com", "123456");
//        token.setRememberMe(true);
//        subject.login(token);
//        ThreadContext.bind(subject);
    }
    String getReturnValue(String uri) throws Exception{
        return mockMvc.perform(MockMvcRequestBuilders.post(uri))
                .andReturn()
                .getResponse()
                .getContentAsString()
                ;

    }

    @Test
    public void TestLogin() throws Exception{
        getReturnValue("/login");
    }
    @Test
    public void TestCreateClass()throws Exception{
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("name","test");
        RequestBuilder requestBuilder = get("/teacher/createClass").params(map);
        mockMvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse()
                .getContentAsString()
        ;
    }
    @Test
    public void TestRegister() throws Exception{
        RequestBuilder requestBuilder = null;
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("username", "100@qq.com");
        map.add("password", "123456");
        map.add("role_type", "3");
        requestBuilder = post("/register").params(map);
        mockMvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void contextLoads() {
        int n = 5;
        int c;
        for(c=0;n>0;++c){
            n &= (n -1);
        }
        System.out.println(c);
    }

}
