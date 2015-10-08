package com.piyush.tick.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import com.piyush.tick.R;


import org.json.JSONObject;


import java.util.ArrayList;

import io.socket.IOAcknowledge;
import com.piyush.tick.base.BaseActivity;
import com.piyush.tick.server.SocketConnection;
import com.piyush.tick.utils.TicTackToeConstants;


/**
 * Created by rajesh on 20/6/15.
 */
public class LoginScreen extends BaseActivity implements View.OnClickListener {
    private EditText email_edt, paswd_edt, mobile_edt;
    private ImageView register;
    public IOAcknowledge ac;
    private ArrayList<String> ls;
    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.loginscreen_layout);
        super.onCreate(savedInstanceState);
        // CONNECTION WITH SOCKET
    }

    @Override
    protected void initOthers() {

    }

    @Override
    protected void initViews() {
        email_edt = (EditText) findViewById(R.id.emai_edt);
        paswd_edt = (EditText) findViewById(R.id.paswd_edt);
        mobile_edt = (EditText) findViewById(R.id.mobile_edt);
        register = (ImageView) findViewById(R.id.signin);
    }

    @Override
    protected void initMembers() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListeners() {
        register.setOnClickListener(this);
    }


    private void attemptLogin() {

        Log.d("Login", "IN");
        // Reset errors.
        email_edt.setError(null);
        paswd_edt.setError(null);
        mobile_edt.setError(null);

        // Store values at the time of the login attempt.
        String email = email_edt.getText().toString().trim();
        String password = paswd_edt.getText().toString();
        String mobile = mobile_edt.getText().toString();


        // Check for a valid username.
        if (TextUtils.isEmpty(email)) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            email_edt.setError(getString(R.string.error_field_required));
            email_edt.requestFocus();
            return;
        } else if (TextUtils.isEmpty(password)) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            paswd_edt.setError(getString(R.string.error_field_required));
            paswd_edt.requestFocus();
            return;
        } else if (TextUtils.isEmpty(mobile)) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            mobile_edt.setError(getString(R.string.error_field_required));
            mobile_edt.requestFocus();
            return;
        }
        try {
            JSONObject obj = new JSONObject();
            obj.put("name", "piyush");
            obj.put("email", email);
            obj.put("password", password);

            if(TicTackToeConstants.mConstSocketIO == null) {
                SocketConnection socket = new SocketConnection(this);
                TicTackToeConstants.mConstSocketIO = socket.getConnection();
            }
            TicTackToeConstants.mConstSocketIO.emit(TicTackToeConstants.FUNCTION_LOGIN, obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signin:
                Log.d("Click", "Button 1 Click");
                attemptLogin();
                break;
        }
    }
}
