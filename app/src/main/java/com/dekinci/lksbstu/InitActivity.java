package com.dekinci.lksbstu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class InitActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        proceed();
    }

    private void proceed() {
        Class c = PolyApp.isLoggedIn() ? MainActivity.class : LoginActivity.class;
        Intent i = new Intent(InitActivity.this, c);
        startActivity(i);
        finish();
    }
}
