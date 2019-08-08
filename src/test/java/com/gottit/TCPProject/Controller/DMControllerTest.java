package com.gottit.TCPProject.Controller;

import com.gottit.TCPProject.Model.CompositeKey.ChannelMessagePK;
import com.gottit.TCPProject.Model.Message;
import com.gottit.TCPProject.Repository.ChannelMessageRepository;
import com.gottit.TCPProject.Repository.DirectMessageRepository;
import com.gottit.TCPProject.Repository.MessageRepository;
import com.gottit.TCPProject.Service.DMService;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.http.HttpStatus.OK;

@SpringBootTest
@AutoConfigureMockMvc
//@RunWith(SpringRunner.class)
@RunWith(MockitoJUnitRunner.class)
public class DMControllerTest {

    @Autowired
    private MockMvc mvc;

    @Mock
    private MessageRepository messageRepository;

    @Mock
    private DirectMessageRepository dmRepo;
    @Mock
    @Autowired
    private DMService dmService;

@InjectMocks
private  DMController dmController;

    @Test
    public void testCreate() throws Exception{
        LocalDateTime now = LocalDateTime.now();
        Message message=new Message(1L, 123L, "hi", now.toString());
        Message expected =new Message(1L, 123L, "hi", now.toString());
        Mockito.when(dmService.create(any(Message.class),anyLong())).thenReturn(expected);
        ResponseEntity<Message> actual = dmController.create(message, 123L);
        Assertions.assertThat(actual.getBody().getMessage_id()).isEqualTo(expected.getMessage_id());
        Assertions.assertThat(actual.getBody().getMessage()).isEqualTo(expected.getMessage());
    }
    @Test
    public void testShow() throws Exception{
        LocalDateTime now = LocalDateTime.now();
       // Message message=new Message(1L, 123L, "hi", now.toString());
       // Message expected =new Message(1L, 123L, "hi", now.toString());
       // Mockito.when(dmService.create(any(Message.class),anyLong())).thenReturn(expected);
        Assert.assertEquals(dmController.showFromTo(123l, 125l).getStatusCodeValue(), 200);
        System.out.println(dmController.showFromTo(123l, 125l).getStatusCode());
       Assert.assertEquals(dmController.showFromTo(123l, 125l).getStatusCode(), OK);
    }




}
