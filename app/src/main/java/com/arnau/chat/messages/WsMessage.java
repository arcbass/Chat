package com.arnau.chat.messages;

/**
 * Created by Arnau on 13/01/2018.
 */

public class WsMessage {

    private final String type;
    private final WsMsg message;

    public WsMessage(String type, WsMsg message) {
        this.type = type;
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public WsMsg getMessage() {
        return message;
    }
}
