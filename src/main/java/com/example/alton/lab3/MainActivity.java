package com.example.alton.lab3;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button b1,b2;
    EditText e1,e2;
    SQLiteDatabase mydatabase;
    Cursor c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            mydatabase=this.openOrCreateDatabase("student",MODE_PRIVATE,null);
            mydatabase.execSQL("CREATE TABLE IF NOT EXISTS user(name VARCHAR,password VARCHAR )");
            mydatabase.execSQL("INSERT INTO user(name,password) VALUES ('Alton','armourofgod') ");
        Log.i("Table created","True");
        c=mydatabase.rawQuery("SELECT *FROM user",null);
        b1=(Button) findViewById(R.id.button);
        b2=(Button) findViewById(R.id.button2);
        e1=(EditText) findViewById(R.id.editText) ;
        e2=(EditText) findViewById(R.id.editText2) ;
        final int name=c.getColumnIndex("name");
        final int password=c.getColumnIndex("password");
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c.moveToFirst();
                int flag=0;
                while(c.moveToNext())
                {
                    Log.i("Name:",c.getString(name));
                    Log.i("Password",c.getString(password));
                    Log.i("Editable:",e1.getText().toString());
                    if(e1.getText().toString().equals(c.getString(name)))
                    {
                        flag=1;
                        break;
                    }
                }
                if(flag==1)
                {
                    if(e2.getText().toString().equals(c.getString(password)))
                    {
                        Log.i("Authenticated:","True");
                        Intent i = new Intent(getApplicationContext(), Main2Activity.class);
                        i.putExtra("name", c.getString(name));
                        startActivity(i);
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this,"User Password is Wrong",+Toast.LENGTH_SHORT).show();
                    }
                }
                if(flag==0)
                {
                    Log.i("User Not Found","True");
                    Toast.makeText(MainActivity.this,"User not Found",+Toast.LENGTH_SHORT).show();
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                e1.setText("Username");
                e2.setText("");
            }
        });
    }
}
