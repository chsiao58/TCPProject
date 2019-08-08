package com.gottit.TCPProject.Controller;

import com.gottit.TCPProject.Model.Channel;
import com.gottit.TCPProject.Model.CompositeKey.UserChannelPK;
import com.gottit.TCPProject.Model.UserChannel;
import com.gottit.TCPProject.Repository.ChannelRepository;
import com.gottit.TCPProject.Repository.UserChannelRepository;
import com.gottit.TCPProject.Service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/channel")

public class ChannelController {

    @Autowired
    private ChannelRepository channelRepository;

    @Autowired
    private ChannelService channelService;

    @Autowired
    UserChannelRepository userChannelRepository;

    @CrossOrigin
    @PostMapping
    public ResponseEntity<Channel> create(@RequestBody Channel channel){
        return new ResponseEntity(channelRepository.save( channel), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping
    public ResponseEntity<Channel> show(){
        return new ResponseEntity(channelRepository.findAll(), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/channelsforuser/{userid}")
    public ResponseEntity<Channel> showbyUser(@PathVariable int userid){
        return new ResponseEntity(channelService.getChannelsByUserID(channelRepository.findAll(),userid), HttpStatus.OK);

    }

    @CrossOrigin
    @PostMapping("/channelusers")
    public ResponseEntity<UserChannel> addUser(@RequestBody UserChannel userChannel){
        return new ResponseEntity(userChannelRepository.save(userChannel), HttpStatus.OK);//add users to channel
    }

    @CrossOrigin
    @GetMapping("/userchannels/{user_id}")
    public ResponseEntity<Channel> userChannels(@PathVariable Long user_id){
        return  new ResponseEntity(userChannelRepository.findAllByUserChannelPK_UserId(user_id),HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/channel/getChannel/{channel_id}")
    public ResponseEntity<Channel> getByChannel(@PathVariable Long channel_id){
        return  new ResponseEntity(channelRepository.findById(channel_id),HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/getById/{user_id}")
    public ResponseEntity<Channel> getById(@PathVariable Long user_id){
        return new ResponseEntity(channelRepository.findChannelByUserId(user_id), HttpStatus.OK);
    }

}
