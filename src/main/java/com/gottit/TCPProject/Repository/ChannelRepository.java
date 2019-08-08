package com.gottit.TCPProject.Repository;

import com.gottit.TCPProject.Model.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, Long > {
    @Query(value="Select c.channel_id, c.channel_name, c.user_id, c.public  from channel c inner join user_channel uc on c.channel_id = uc.channel_id where uc.user_id = ?1", nativeQuery=true)
    public List<Channel> findChannelByUserId(Long userId);

}
