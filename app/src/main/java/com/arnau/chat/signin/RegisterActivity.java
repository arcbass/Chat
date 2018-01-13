package com.arnau.chat.signin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.arnau.chat.R;
import com.arnau.chat.signin.request.SendPostRequest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class RegisterActivity extends AppCompatActivity {

    EditText registerUser, registerEmail, registerPass;
    private HashMap<String, String> params = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerUser = findViewById(R.id.text_register_user);
        registerEmail = findViewById(R.id.text_register_mail);
        registerPass = findViewById(R.id.text_register_pass);

        Button registerBtn = findViewById(R.id.register_button);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                params = new HashMap<>();
                params.put("user", registerUser.getText().toString());
                params.put("password", registerPass.getText().toString());
                params.put("email", registerEmail.getText().toString());



                String response = null;

                try {
                    SendPostRequest sendPostRequest = new SendPostRequest(params, new URL("http://192.168.0.157:8080/wstest/RegisterMobile"));
                    response = sendPostRequest.execute().get();

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }


                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
            }
        });




    }
}
