package com.gottit.TCPProject.Model;

import org.hibernate.annotations.Type;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;

@Entity
public class Channel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long channel_id;
    @Column
    private String channel_name;

    @Column
    private Long user_id; //creator of channel

    @Column(name = "public")
    private Boolean isPublic = true;

    public Channel(Long channel_id, String channel_name, Long user_id, Boolean isPublic) {
        this.channel_id = channel_id;
        this.channel_name = channel_name;
        this.user_id = user_id;
        this.isPublic = isPublic;
    }

    public Channel(){}

    public Long getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(Long channel_id) {
        this.channel_id = channel_id;
    }

    public String getChannel_name() {
        return channel_name;
    }

    public void setChannel_name(String channel_name) {
        this.channel_name = channel_name;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "channel_id=" + channel_id +
                ", channel_name='" + channel_name + '\'' +
                ", user_id=" + user_id +
                ", isPublic=" + isPublic +
                '}';
    }
}
