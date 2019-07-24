package com.gottit.TCPProject.Repository;

import com.gottit.TCPProject.Model.ChannelMessage;
import com.gottit.TCPProject.Model.CompositeKey.ChannelMessagePK;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChannelMessageRepository extends CrudRepository<ChannelMessage, ChannelMessagePK> {
    //When querying by composite key you can concatnate calls, ChannelMessagePK.Channel_id
    //https://stackoverflow.com/questions/43865079/spring-jpa-find-by-embeddedid-partially/43882807
    public List<ChannelMessage> findAllByChannelMessagePK_ChannelID(Long channel_id); //findAllByChannelMessagePK_Channel_id

}
