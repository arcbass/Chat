package com.arnau.chat.tools;

import android.os.AsyncTask;

import com.arnau.chat.messages.WsMessage;
import com.arnau.chat.messages.WsMsg;
import com.arnau.chat.messages.WsMsgLogin;
import com.arnau.chat.messages.WsMsgLogout;
import com.arnau.chat.messages.WsMsgMessage;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Created by Arnau on 13/01/2018.
 */

public class MessageProcesser extends AsyncTask<String, Void, WsMessage>{
    @Override
    protected WsMessage doInBackground(String... strings) {
        decodeMessge(strings[0]);


        return null;
    }

    public WsMessage decodeMessge(String message) {
        //parse the message
        JsonParser parser = new JsonParser();
        JsonElement jse = parser.parse(message);
        //create a json object to obtain the information of the message
        JsonObject jso = jse.getAsJsonObject();
        //get the type of message
        String messageType = jso.get("type").getAsString();

        //gson to create objects from json
        Gson gson = new Gson();
        //create a Json element to contain the object of the json
        JsonElement content;
        //process the messages by the type of message

        WsMsg messageContent;
        switch (messageType) {
            case "WsMsgLogin":
                System.out.println("WsMsgLogin");
                //create an object in function of the type of message
                content = jso.get("message");
                messageContent = gson.fromJson(content, WsMsgLogin.class);
                break;

            case "WsMsgMessage":
                System.out.println("WsMsgLogin");
                //create an object in function of the type of message
                content = jso.get("message");
                messageContent = gson.fromJson(content, WsMsgMessage.class);
                break;

            case "WsMsgLogout":
                System.out.println("WsMsgLogout");
                content = jso.get("message");
                messageContent = gson.fromJson(content, WsMsgLogout.class);
                break;

            default:
                throw new RuntimeException("Tipo de mensaje desconocido");
        }
        return new WsMessage(messageType, messageContent);
    }
}
