package com.example.alton.lab3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    TextView e;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Bundle extras = getIntent().getExtras();
        String name = extras.getString("name");
        Log.i("Name:",name);
        e=(TextView) findViewById(R.id.textView3) ;
        e.setText(name);
    }
}
