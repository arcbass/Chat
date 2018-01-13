package com.arnau.chat.messages;

/**
 * Created by Arnau on 13/01/2018.
 */

public class WsMsgLogin implements WsMsg{
    private String username;

    public WsMsgLogin(String username) {
        this.username = username;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
