package com.gottit.TCPProject.Service;

import com.gottit.TCPProject.Model.CompositeKey.DirectMessagePK;
import com.gottit.TCPProject.Model.DirectMessage;
import com.gottit.TCPProject.Model.Message;
import com.gottit.TCPProject.Repository.DirectMessageRepository;
import com.gottit.TCPProject.Repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DMService {
    @Autowired
    DirectMessageRepository DMRepo;
    @Autowired
    MessageRepository messageRepo;

    //can get channel
    public Message create(Message message, Long to_id){

        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        message.setTime(sdf.format(new Date()));

        Message temp = messageRepo.save(message);
        DirectMessagePK pk = new DirectMessagePK(to_id, temp.getMessage_id());

        DMRepo.save(new DirectMessage(pk));

        //return the newly created message
        return temp;
    }



    // show DM from other user
    // in this case, the other person wrote the message, which they owns the message
    // and the current user called will be the user in the DirectUserPK
    public Iterable<Message> show(Long from_id, Long to_id) {

        List<Long> resultMessageId = getFromToMessageId(from_id, to_id);
        resultMessageId.addAll(getFromToMessageId(to_id,from_id));
        Collections.sort(resultMessageId);
        // get all those message from messageRepo using the filtered message_ids
        return messageRepo.findAllById(resultMessageId);
    }

    public List<Long> getFromToMessageId(Long from_id, Long to_id) {

        // find all message_id user A created
        List<Long> fromMessageId =
                messageRepo.findAllByUserId(from_id)
                        .stream()
                        .map(Message::getMessage_id)
                        .collect(Collectors.toList());

        // find all message_id user B received,
        // in these message_ids, find all message_ids that create by user A
        // those message_ids will be the message user A sent to user B
        return DMRepo.findAllByDirectMessagePK_ToId(to_id)
                        .stream()
                        .map(DirectMessage::getDirectMessagePK)
                        .map(DirectMessagePK::getMessage_id)
                        .filter(fromMessageId::contains)
                        .collect(Collectors.toList());
    }
}
