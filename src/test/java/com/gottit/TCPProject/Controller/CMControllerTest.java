package com.gottit.TCPProject.Controller;
import com.gottit.TCPProject.Model.CompositeKey.ChannelMessagePK;
import com.gottit.TCPProject.Model.Message;
import com.gottit.TCPProject.Repository.ChannelMessageRepository;
import com.gottit.TCPProject.Repository.MessageRepository;
import com.gottit.TCPProject.Service.CMService;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@AutoConfigureMockMvc
//@RunWith(SpringRunner.class)
@RunWith(MockitoJUnitRunner.class)
public class CMControllerTest {

    @Autowired
    private MockMvc mvc;

    @Mock
   ChannelMessageRepository CMRepository;
    @Mock
   MessageRepository messageRepository;
    @Mock
    @Autowired
    private CMService cmService;
    @InjectMocks
    private CMController cmController;

//    @Test
//    public void testShow() throws Exception{
////        List<Message> finallist = new ArrayList<>();
////        finallist.add(new Message(4L, 1L, "timeWorking?", "2019-07-14 15:12:45"));
////        finallist.add(new Message(8L, 1L, "testing againa?", "2019-07-14 15:03:43"));
//        List<ChannelMessage> finalList =  new ArrayList<>();
//        finalList.add(new ChannelMessage(new ChannelMessagePK(1L, 4L)));
//        finalList.add(new ChannelMessage(new ChannelMessagePK(1L, 8L)));
//        finalList.stream().forEach(System.out::println);
//        Long givenId = 1L;
//        BDDMockito
//                .given(CMRepository.findAllByChannelMessagePK_ChannelID(1L))
//                .willReturn(finalList);
//
//        String expected = "ChannelMessage{channelMessagePK=ChannelMessagePK{channelID=1, message_id=4}}\n" +
//                "ChannelMessage{channelMessagePK=ChannelMessagePK{channelID=1, message_id=8}}";
//
//        String notExpected = "[]";
//
//        this.mvc.perform(MockMvcRequestBuilders
//                .get("/channel/" + givenId))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().string(notExpected));
//
//
//    }
 //   @Test
//    public void testCreate() throws Exception{
//        Message message = new Message(1L, 1L,"testMEssage", "asd");
//        BDDMockito
//                .given(MessageRepository.save(message))
//                .willReturn(message);
//
//        String expected = message.toString();
//        this.mvc.perform(MockMvcRequestBuilders
//        .post("/channel/1/message")
////                .param("message_id", "1")
////                .param("user_id", "1")
////                .param("message", "testMEssage")
////                .param("time", "asd")
//        .contentType(MediaType.APPLICATION_JSON)
//        .accept(MediaType.APPLICATION_JSON)
//        .content(expected))
//                .andExpect(MockMvcResultMatchers.status().isCreated())
//                .andExpect(MockMvcResultMatchers.content().string(expected));
//    }

 @Test
 public void testShow1() throws Exception{
     LocalDateTime now = LocalDateTime.now();
     Message message=new Message(1L, 123L, "hi", now.toString());
    // Message expected =new Message(1L, 123L, "hi", now.toString());
     //Mockito.when(cmService.create(any(Message.class),anyLong())).thenReturn(expected);
     Assert.assertEquals(cmController.show(123l).getStatusCodeValue(), 200);
     Assert.assertEquals(cmController.show(123l).getStatusCode(), HttpStatus.OK);
 }

    @Test
    public void testCreate() throws Exception{
        LocalDateTime now = LocalDateTime.now();
        Message message=new Message(1L, 123L, "hi", now.toString());
        Message expected =new Message(1L, 123L, "hi", now.toString());
        ChannelMessagePK channelMessagePK=new ChannelMessagePK(123l, 1l);
        Mockito.when(cmService.create(any(Message.class),anyLong())).thenReturn(expected);
        ResponseEntity<Message> actual = cmController.create(message, 123L);
        Assertions.assertThat(actual.getBody().getMessage_id()).isEqualTo(expected.getMessage_id());
        Assertions.assertThat(actual.getBody().getMessage()).isEqualTo(expected.getMessage());
    }
          //  verify(cmService,times(1)).create(message, 123l);

    private ResponseEntity<Message> createMsgmock(){return new ResponseEntity<Message>(new Message(), HttpStatus.CREATED);}

}