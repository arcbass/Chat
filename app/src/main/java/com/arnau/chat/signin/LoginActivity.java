package com.arnau.chat.signin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.arnau.chat.ChatroomActivity;
import com.arnau.chat.R;
import com.arnau.chat.signin.request.SendPostRequest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;


public class LoginActivity extends AppCompatActivity {

    private EditText loginName, loginPass;
    private TextView signUp;
    private HashMap<String, String> params = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginName = findViewById(R.id.text_login_user);
        loginPass = findViewById(R.id.text_login_pass);

        params = new HashMap<>();

        final Button loginBtn = findViewById(R.id.login_button);
        loginBtn.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View view) {


                /*
                params.put("user", loginName.getText().toString());
                params.put("password", loginPass.getText().toString());
                */
                params.put("user", "admin");
                params.put("password", "1234");
                String response = "";

                try {
                    SendPostRequest sendPostRequest = new SendPostRequest(params, new URL("http://192.168.0.157:8080/wstest/LoginMobile"));
                    response = sendPostRequest.execute().get();
                    
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                if(response.equals("Connected")){
                    Intent i = new Intent(getBaseContext(), ChatroomActivity.class);
                    startActivity(i);
                }
            }
        });

        signUp = findViewById(R.id.sign_in_text);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(), RegisterActivity.class);
                startActivity(i);
            }
        });
    }


}
