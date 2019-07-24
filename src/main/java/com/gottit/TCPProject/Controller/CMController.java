package com.gottit.TCPProject.Controller;

import com.gottit.TCPProject.Model.Message;
import com.gottit.TCPProject.Service.CMService;
import com.gottit.TCPProject.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CMController {

    @Autowired
    private CMService cmService;

    @PostMapping("/channel/{channel_id}/message")
    public ResponseEntity<Message> create(@RequestBody Message message, @PathVariable Long channel_id){
        return new ResponseEntity<>(cmService.create(message, channel_id), HttpStatus.CREATED);
    }

    @GetMapping("/channel/{channel_id}")
    public ResponseEntity<Iterable<Message>> show(@PathVariable Long channel_id){
        return new ResponseEntity<>(cmService.show(channel_id), HttpStatus.OK);
    }
//
//    @GetMapping("/channel/{user_id}")
//    public ResponseEntity<Iterable<Message>> showUser(@PathVariable Long user_id){
//        return new ResponseEntity<>(cmService.show(user_id), HttpStatus.OK);
//    }

}
