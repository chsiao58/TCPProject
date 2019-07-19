package com.gottit.TCPProject.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gottit.TCPProject.Model.User;
import com.gottit.TCPProject.Repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
//@RunWith(SpringRunner.class)
@RunWith(MockitoJUnitRunner.class)
//@WebMvcTest(UserController.class)
public class UserControllerTest {
//
    @Autowired
    private MockMvc mvc;

    @InjectMocks
    @Autowired
    private UserController userController;

    @Mock
    private UserRepository userRepository;

    @Mock
    User user;


//    @Test
//    public void createid() throws Exception {
//        User expectedUser = new User(1l, "John", "xyz");
//        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(ex);
//        ResponseEntity<User> actualUser = userController.create(user);
//        //this.mvc.perform(get("/user").accept(MediaType.APPLICATION_JSON_UTF8)).andExpect((ResultMatcher) content().string("{\"user_Id\":\"1l\",\"username\":\"John\",\"password\":\"xyz}"));
//        //Assert.assertEquals(1l, actualUser.getBody().getUser_id());
//        Assert.assertEquals(java.util.Optional.of(1l), java.util.Optional.of(actualUser.getBody().getUser_id()));
//        Assert.assertEquals("John", actualUser.getBody().getUsername());
//
//    }
//}
//@Test
//public void createid() throws Exception {
//    User expectedUser = new User(1l, "John", "xyz");
//    // ResponseEntity<User> responseEntity = new ResponseEntity<>(expectedUser,HttpStatus.OK);
//    // mockedresponseEntity
//    Mockito.when(userRepository.save(any(User.class))).thenReturn(expectedUser);
//    //  Mockito.when(mockedresponseEntity.getBody()).thenReturn(expectedUser);
//    ResponseEntity<User> actualUser = userController.create(user);
//    //this.mvc.perform(get("/user").accept(MediaType.APPLICATION_JSON_UTF8)).andExpect((ResultMatcher) content().string("{\"user_Id\":\"1l\",\"username\":\"John\",\"password\":\"xyz}"));
//    //Assert.assertEquals(1l, actualUser.getBody().getUser_id());
//    Assert.assertEquals(java.util.Optional.of(1l), java.util.Optional.of(actualUser.getBody().getUser_id()));
//    Assert.assertEquals("John", actualUser.getBody().getUsername());
//
//}

// @Test
//     public void toDo()throws Exception{
//        String content="{\" {\n" +
//                "      \n" +
//                "            \"user_Id\": 1234,\n" +
//                "            \"user_name\":\"xyz\",\n" +
//                "            \"password\":\"abc\"\n" +
//                "        \n" +
//                "}";
//        this.mockServer.expect(requestTo("/user/user_name"))
//                .andRespond(withSuccess(content,MediaType.APPLICATION_JSON_UTF8));
//        ResponseEntity<User> result=this.userController.show("xyz");
//        assertThat(result).isNotNull();
//
//
//
// }
    @Test
    public void create(){
        User user = new User(1l, "John", "xyz");
        User expected = new User(1l, "John", "xyz");
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(expected);
        ResponseEntity<User> actualUser = userController.create(user);
        assertThat(actualUser.getBody().getPassword()).isEqualTo(expected.getPassword());

    }

    @Test
    public void create1() {
        User user = new User(1l, "John", "xyz");
        User expected = new User(1l, "John", "xyz");
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(expected);
        ResponseEntity<User> actualUser = userController.create(user);
        assertThat(actualUser.getBody().getUsername()).isEqualTo(expected.getUsername());

    }

    @Test
    public void show() {
        User user = new User(1l, "John", "xyz");
        User expected = new User(1l, "John", "xyz");
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(expected);
        userController.create(user);
        Assert.assertEquals(userController.show("John").getStatusCodeValue(), 200);
        Assert.assertEquals(userController.show("John").getStatusCode(), OK);

    }
    @Test
    public void showall() {
        User user = new User(1l, "John", "xyz");
        User expected = new User(1l, "John", "xyz");
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(expected);
        userController.create(user);
      List<User> actualUser=new LinkedList<>();
      actualUser.add(expected);
      // Assert.assertEquals(userController.show("John").getBody().toString(),actualUser.toString() );
        Assert.assertEquals(userController.show("John").getStatusCodeValue(), 200);
        Assert.assertEquals(userController.show("John").getStatusCode(), OK);

    }
//
//    @Test
//    public void show1() throws URISyntaxException, JsonProcessingException {
//
//        User user = new User(1l, "John", "xyz");
//        //User expected = new User(1l, "John", "xyz");
//        mockServer.expect(ExpectedCount.once(),
//                requestTo(new URI("http://localhost:8080/user/John")))
//                .andExpect(method(HttpMethod.GET))
//                .andRespond(withStatus(HttpStatus.OK)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .body(mapper.writeValueAsString(user))
//                );
//        userController.create(user);
//        ResponseEntity<User> user1 = userController.show("John");
//        mockServer.verify();
//        Assert.assertEquals(user1.getBody().getUser_id(), user.getUser_id());
//    }
}