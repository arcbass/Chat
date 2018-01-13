package com.arnau.chat;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.arnau.chat.messages.WsMessage;
import com.arnau.chat.messages.WsMsg;
import com.arnau.chat.messages.WsMsgLogin;
import com.arnau.chat.messages.WsMsgLogout;
import com.arnau.chat.messages.WsMsgMessage;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

public class ChatroomActivity extends AppCompatActivity {

    private WebSocketClient webSocketClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatroom);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        if ("google_sdk".equals( Build.PRODUCT )) {
            // ... disable IPv6
            java.lang.System.setProperty("java.net.preferIPv6Addresses", "false");
            java.lang.System.setProperty("java.net.preferIPv4Stack", "true");
        }

        connectWebSocket("admin", "adsfasdfa");


    }

    private void connectWebSocket(String username, String signalcarrier) {
        URI uri;
        try {
            uri = new URI("ws://192.168.0.157:8080/wstest/Chat/" + signalcarrier + "/" + username);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return;
        }

        webSocketClient = new WebSocketClient(uri) {

            @Override
            public void onOpen(ServerHandshake handshakedata) {
                Log.i("Websocket", "Opened");
                String msg = "{\"type\":\"WsMsgLogin\",\"message\":{\"username\":\"admin\"}}";
                Log.i("Websocket", "Message on open: " + msg);
                webSocketClient.send(msg);
            }

            @Override
            public void onMessage(String message) {
                Log.i("Websocket", "Message: " + message);
            }

            @Override
            public void onClose(int code, String reason, boolean remote) {
                Log.i("Websocket", "Closed " + reason);
            }

            @Override
            public void onError(Exception ex) {
                Log.i("Websocket", "Error " + ex.getMessage());
                ex.printStackTrace();
            }
        };

            webSocketClient.connect();
/*
        while(!webSocketClient.isOpen()){
            webSocketClient.connect();
            System.out.println(webSocketClient.getReadyState());
        }*/
        //webSocketClient.send("{'type':'WsMsgLogin','message':{'username':'admin'}}");
    }

    public void processMessage(WsMessage message){

    }



}
