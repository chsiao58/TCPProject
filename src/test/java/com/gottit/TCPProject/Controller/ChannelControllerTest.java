package com.gottit.TCPProject.Controller;

import com.gottit.TCPProject.Model.Channel;
import com.gottit.TCPProject.Model.CompositeKey.UserChannelPK;
import com.gottit.TCPProject.Model.UserChannel;
import com.gottit.TCPProject.Repository.ChannelRepository;
import com.gottit.TCPProject.Repository.UserChannelRepository;
import com.mysql.cj.x.protobuf.Mysqlx;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.http.HttpStatus.OK;


@SpringBootTest
@AutoConfigureMockMvc
//@RunWith(SpringRunner.class)
@RunWith(MockitoJUnitRunner.class)
public class ChannelControllerTest {

    @Autowired
    private MockMvc mvc;

    @Mock
    private ChannelRepository channelRepository;
    @Mock
    private UserChannelRepository userChannelRepository;
    @InjectMocks
    private ChannelController channelController;


    @Test
    public void create() {

        Channel inputChannel = new Channel(123L, "testchannel", 234L, true);

        Channel expectedChannel = new Channel(123L, "testchannel", 234L, true);

        Mockito.when(channelRepository.save(Mockito.any(Channel.class))).thenReturn(expectedChannel);
        ResponseEntity<Channel> actualChannel;
        actualChannel = channelController.create(inputChannel);
        Assertions.assertThat(actualChannel.getBody().getUser_id()).isEqualTo(expectedChannel.getUser_id());
        Assertions.assertThat(actualChannel.getBody().getChannel_name()).isEqualTo(expectedChannel.getChannel_name());
        Assertions.assertThat(actualChannel.getBody().getChannel_id()).isEqualTo(expectedChannel.getChannel_id());
        Assertions.assertThat(actualChannel.getBody().getPublic()).isEqualTo(expectedChannel.getPublic());

    }
    @Test
    public void create1() {

        Channel inputChannel = new Channel(3L, "channel", 4L, false);

        Channel expectedChannel = new Channel(3L, "channel", 4L, false);

        Mockito.when(channelRepository.save(Mockito.any(Channel.class))).thenReturn(inputChannel);
        ResponseEntity<Channel> actualChannel;
        actualChannel = channelController.create(inputChannel);
        Assertions.assertThat(actualChannel.getBody().getUser_id()).isEqualTo(expectedChannel.getUser_id());
        Assertions.assertThat(actualChannel.getBody().getChannel_name()).isEqualTo(expectedChannel.getChannel_name());
        Assertions.assertThat(actualChannel.getBody().getChannel_id()).isEqualTo(expectedChannel.getChannel_id());
    }

        @Test
    public void show() throws Exception {
        Channel inputChannel = new Channel(123L, "testchannel", 234L, true);

        Channel expectedChannel = new Channel(123L, "testchannel", 234L, true);

        Mockito.when(channelRepository.save(Mockito.any(Channel.class))).thenReturn(expectedChannel);
       channelController.create(inputChannel);
        System.out.println("OUTPUTttt" + channelController.show().getStatusCodeValue());

        Assert.assertEquals(channelController.show().getStatusCodeValue(), 200);
            Assert.assertEquals(channelController.show().getStatusCode(), OK);
}


    @Test
    public void addUser() {
        UserChannelPK userChannelPK=new UserChannelPK(23L,123L);
        UserChannel userChannel=new UserChannel(userChannelPK);
        UserChannel expectedUser=new UserChannel(userChannelPK);
        Mockito.when(userChannelRepository.save(Mockito.any(UserChannel.class))).thenReturn(expectedUser);
        ResponseEntity<UserChannel> actualUser;
        actualUser=channelController.addUser(userChannel);
        Assertions.assertThat(actualUser.getBody().getUserChannelPK().getUserId()).isEqualTo(expectedUser.getUserChannelPK().getUserId());
        Assertions.assertThat(actualUser.getBody().getUserChannelPK().getChannelID()).isEqualTo(expectedUser.getUserChannelPK().getChannelID());
    }

    @Test
    public void userChannels() {
        UserChannelPK userChannelPK=new UserChannelPK(23L,123L);
        UserChannel userChannel=new UserChannel(userChannelPK);
        ResponseEntity<Channel> actualChannel= channelController.userChannels(123l);
        Assert.assertEquals(channelController.userChannels(123l).getStatusCodeValue(), 200);
        Assert.assertEquals(channelController.userChannels(123l).getStatusCode(), OK);
    }

}