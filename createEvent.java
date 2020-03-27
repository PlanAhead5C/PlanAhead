package com.example.planahead5c;

import android.content.*;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class createEvent extends AppCompatActivity
{
    DatabaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        myDb = new DatabaseHelper(this);

    }

    public void saveEvent(View view)
    {
        TextView e_Name = (TextView) findViewById(R.id.addTitle);
        boolean isInserted = myDb.insertEvents(e_Name.getText().toString());
        if(isInserted == true)
            Toast.makeText(createEvent.this,"Data Inserted",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(createEvent.this,"Data not Inserted",Toast.LENGTH_LONG).show();
        /*Intent openMain = new Intent(createEvent.this, MainActivity.class);
        startActivity(openMain);
        finish();*/
    }
}
