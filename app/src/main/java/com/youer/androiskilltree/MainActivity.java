package com.youer.androiskilltree;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.orhanobut.logger.Logger;
import com.youer.reflection.Hook;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Logger.d("test");
        Hook.test(this);
    }
}