package com.gottit.TCPProject.Repository;

import com.gottit.TCPProject.Model.CompositeKey.UserChannelPK;
import com.gottit.TCPProject.Model.UserChannel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserChannelRepository extends CrudRepository<UserChannel, UserChannelPK> {
    public List<UserChannel> findAllByUserChannelPK_UserId(Long user_id);
}

