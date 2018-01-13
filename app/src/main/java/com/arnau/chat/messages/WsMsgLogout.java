package com.arnau.chat.messages;

/**
 * Created by NitroPc on 13/01/2018.
 */

public class WsMsgLogout implements WsMsg {
    private String username;

    public WsMsgLogout(String username) {
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
