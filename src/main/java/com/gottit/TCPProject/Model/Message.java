package com.gottit.TCPProject.Model;

import javax.persistence.*;

@Entity
@Table(name = "message")
public class Message{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long message_id;

    @ManyToOne
    private User user;
    
    private String message;
    private String time; //datetime data structure

    public Message(Long message_id, User user, String message) {
        this.message_id = message_id;
        this.user = user;
        this.message = message;
    }

    //shouldnt use this as db inputs datetime automaticaly on insert.
    public Message(Long message_id, User user, String message, String time) {
        this.message_id = message_id;
        this.user = user;
        this.message = message;
        this.time = time; //set this to null ?
    }

    public Message(User user, String message) {
        this.user = user;
        this.message = message;
    }

    public Message(){
    }

    public Long getMessage_id() {
        return message_id;
    }

    public void setMessage_id(Long message_id) {
        this.message_id = message_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Message{" +
                "message_id=" + message_id +
                ", user=" + user.getUsername() +
                ", message='" + message + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
