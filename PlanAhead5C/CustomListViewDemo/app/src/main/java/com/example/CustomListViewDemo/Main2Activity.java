package com.example.CustomListViewDemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    ImageView imageView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        imageView = findViewById(R.id.image_view);
        textView = findViewById(R.id.text_view);

        Intent intent = getIntent();
        int numberImage = intent.getIntExtra( "image", 0);
        String numberWord = intent.getStringExtra("word");

        imageView.setImageResource(numberImage);
        textView.setText(numberWord);
    }
}
