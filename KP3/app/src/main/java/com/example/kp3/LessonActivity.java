package com.example.kp3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class LessonActivity extends AppCompatActivity {

    static String name_of_lesson = "";
    Button back_button;
    TextView name_of_lesson_textview;

    public static void setName_of_lesson(String name_of_lesson) {
        LessonActivity.name_of_lesson = name_of_lesson;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);

        name_of_lesson_textview = findViewById(R.id.lesson_name);
        back_button = findViewById(R.id.back_to_list_button_lesson);

        name_of_lesson_textview.setText(name_of_lesson);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListExcercisesActivity.class);
                startActivity(intent);
            }
        });
    }
}