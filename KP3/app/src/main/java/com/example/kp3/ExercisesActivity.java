package com.example.kp3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.Random;

public class ExercisesActivity extends AppCompatActivity {

    static String name_of_exercise = "";
    static int id_of_exercise = 1;
    int points_in_session = 0;
    int mistakes_in_session = 0;
    Button back_button, button_answer, next_exercise_button, next_task_button, back_to_ex;
    TextView name_of_exercise_textview, exercise_text, result_text_view, last_answer_text_view, right_answer_text_view;
    Button button_1, button_2, button_3, button_4, button_5, button_6, button_7, button_8, button_9, button_0, button_delete;
    EditText answer_edittext;
    private int answer;
    private String string_answer;

    public static void setName_of_exercise(String name_of_exercise) {
        ExercisesActivity.name_of_exercise = name_of_exercise;
    }

    public static void setId_of_exercise(int id_of_exercise) {
        ExercisesActivity.id_of_exercise = id_of_exercise;
    }

    public int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    private void setExerciseText(){
        if(name_of_exercise.contains("Сложение") && !name_of_exercise.contains("Вычитание")) {
            answer_edittext.setRawInputType(InputType.TYPE_CLASS_NUMBER);
            if(name_of_exercise.contains("1")){
                int rnd_task_id = getRandomNumber(1,3);
                if (rnd_task_id == 1) {
                    int rnd_num_task_id = getRandomNumber(1,4);
                    if (rnd_num_task_id == 1) {
                        int number1 = getRandomNumber(0, 8);
                        int number2 = getRandomNumber(0, 9 - number1);
                        answer = number1 + number2;
                        string_answer = String.valueOf(number1)
                                + " + " + String.valueOf(number2)
                                + " = " + String.valueOf(answer) + "!";
                        exercise_text.setText(
                                "Сколько будет\n " + String.valueOf(number1)
                                        + " + " + String.valueOf(number2)
                                        + "?"
                        );
                    }
                    else if (rnd_num_task_id == 2) {
                        int number1 = getRandomNumber(0, 7);
                        int number2 = getRandomNumber(0, 8 - number1);
                        int number3 = getRandomNumber(0, 9 - number1 - number2);
                        answer = number1 + number2 + number3;
                        string_answer = String.valueOf(number1)
                                + " + " + String.valueOf(number2)
                                + " + " + String.valueOf(number3)
                                + " = " + String.valueOf(answer) + "!";
                        exercise_text.setText(
                                "Сколько будет\n " + String.valueOf(number1)
                                        + " + " + String.valueOf(number2)
                                        + " + " + String.valueOf(number3)
                                        + "?"
                        );
                    }
                    else {
                        int number1 = getRandomNumber(0, 6);
                        int number2 = getRandomNumber(0, 7 - number1);
                        int number3 = getRandomNumber(0, 8 - number1 - number2);
                        int number4 = getRandomNumber(0, 9 - number1 - number2 - number3);
                        answer = number1 + number2 + number3 + number4;
                        string_answer = String.valueOf(number1)
                                + " + " + String.valueOf(number2)
                                + " + " + String.valueOf(number3)
                                + " + " + String.valueOf(number4)
                                + " = " + String.valueOf(answer) + "!";
                        exercise_text.setText(
                                "Сколько будет\n " + String.valueOf(number1)
                                        + " + " + String.valueOf(number2)
                                        + " + " + String.valueOf(number3)
                                        + " + " + String.valueOf(number4)
                                        + "?"
                        );
                    }
                }
                else{
                    int number1 = getRandomNumber(1, 8);
                    int number2 = getRandomNumber(1, 9 - number1);
                    answer = number1 + number2;
                    int rnd_text_task_id = getRandomNumber(1,10);
                    if (rnd_text_task_id == 1) {
                        String right_form1 = " шарик. ";
                        String string_form_was = "было ";
                        if (number1 > 1 && number1 < 5) right_form1 = " шарика. ";
                        else if (number1 >= 5) right_form1 = " шариков. ";
                        else string_form_was = "был ";

                        String right_form2 = " шарик. \n";
                        if (number2 > 1 && number2 < 5) right_form2 = " шарика. \n";
                        if (number2 >= 5) right_form2 = " шариков. \n";

                        String answer_form = " шарик";
                        if (answer > 1 && answer < 5) answer_form = " шарика";
                        if (answer >= 5) answer_form = " шариков";
                        string_answer = "У Пети " + String.valueOf(number1)
                                + " + " + String.valueOf(number2) + " " + " = " + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("У Пети " + string_form_was + String.valueOf(number1) + right_form1 +
                                "Друзья подарили ему " + String.valueOf(number2) + right_form2
                                + "Сколько шариков у Пети теперь?");
                    }
                    else if (rnd_text_task_id == 2) {
                        String string_form_was = "было ";
                        String right_form1 = " персик. ";
                        if (number1 > 1 && number1 < 5) right_form1 = " персика. ";
                        else if (number1 >= 5) right_form1 = " персиков. ";
                        else string_form_was = "был ";

                        String right_form2 = " персик. \n";
                        if (number2 > 1 && number2 < 5) right_form2 = " персика. \n";
                        if (number2 >= 5) right_form2 = " персиков. \n";

                        String answer_form = " персик";
                        if (answer > 1 && answer < 5) answer_form = " персика";
                        if (answer >= 5) answer_form = " персиков";
                        string_answer = "У Васи " + String.valueOf(number1)
                                + " + " + String.valueOf(number2) + " " + " = " + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("У Васи " + string_form_was + String.valueOf(number1) + right_form1 +
                                "Друзья подарили ему " + String.valueOf(number2) + right_form2
                                + "Сколько персиков у Васи теперь?");
                    }
                    else if (rnd_text_task_id == 3) {
                        String right_form1 = " монету. ";
                        if (number1 > 1 && number1 < 5) right_form1 = " монеты. ";
                        if (number1 >= 5) right_form1 = " монет. ";

                        String right_form2 = " монету. \n";
                        if (number2 > 1 && number2 < 5) right_form2 = " монеты. \n";
                        if (number2 >= 5) right_form2 = " монет. \n";

                        String answer_form = " монета";
                        if (answer > 1 && answer < 5) answer_form = " монеты";
                        if (answer >= 5) answer_form = " монет";
                        string_answer = "У Нины " + String.valueOf(number1)
                                + " + " + String.valueOf(number2) + " " + " = " + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("Нина накопила " + String.valueOf(number1) + right_form1 +
                                "Родители дали ей в школу ещё " + String.valueOf(number2) + right_form2
                                + "Сколько монет Нина сможет потратить?");
                    }
                    else if (rnd_text_task_id == 4) {
                        answer = number1;
                        String right_form1 = " яблоко. ";
                        if (number1 > 1 && number1 < 5) right_form1 = " яблока. ";
                        if (number1 >= 5) right_form1 = " яблок. ";

                        String right_form2 = " грушу. \n";
                        if (number2 > 1 && number2 < 5) right_form2 = " груши. \n";
                        if (number2 >= 5) right_form2 = " груш. \n";

                        String answer_form = " яблоко";
                        if (answer > 1 && answer < 5) answer_form = " яблока";
                        if (answer >= 5) answer_form = " яблок";
                        string_answer = "У Лены до сих пор "  + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("У Лены было " + String.valueOf(number1) + right_form1 +
                                "Ещё она сорвала с дерева " + String.valueOf(number2) + right_form2
                                + "Сколько яблок у Лены?");
                    }
                    else if (rnd_text_task_id == 5) {
                        answer = number1;
                        String string_form_was = "было ";
                        String right_form1 = " персик. ";
                        if (number1 > 1 && number1 < 5) right_form1 = " персика. ";
                        else if (number1 >= 5) right_form1 = " персиков. ";
                        else string_form_was = "был ";

                        String right_form2 = " монету. \n";
                        if (number2 > 1 && number2 < 5) right_form2 = " монеты. \n";
                        if (number2 >= 5) right_form2 = " монет. \n";

                        String answer_form = " персик";
                        if (answer > 1 && answer < 5) answer_form = " персика";
                        if (answer >= 5) answer_form = " персиков";
                        string_answer = "У Васи до сих пор " + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("У Васи " + string_form_was + String.valueOf(number1) + right_form1 +
                                "Друзья подарили ему " + String.valueOf(number2) + right_form2
                                + "Сколько персиков у Васи теперь?");
                    }
                    else if (rnd_text_task_id == 6) {
                        answer = number2;
                        String string_form_was = "было ";
                        String right_form1 = " персик. ";
                        if (number1 > 1 && number1 < 5) right_form1 = " персика. ";
                        else if (number1 >= 5) right_form1 = " персиков. ";
                        else string_form_was = "был ";

                        String right_form2 = " монету. \n";
                        if (number2 > 1 && number2 < 5) right_form2 = " монеты. \n";
                        if (number2 >= 5) right_form2 = " монет. \n";

                        String answer_form = " монета";
                        if (answer > 1 && answer < 5) answer_form = " монеты";
                        if (answer >= 5) answer_form = " монет";
                        string_answer = "У Васи теперь " + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("У Васи " + string_form_was + String.valueOf(number1) + right_form1 +
                                "Друзья подарили ему " + String.valueOf(number2) + right_form2
                                + "Сколько монет у Васи теперь, если до этого у Васи не было монет?");
                    }
                    else if (rnd_text_task_id == 7) {
                        String right_form1 = " монету";
                        if (number1 > 1 && number1 < 5) right_form1 = " монеты";
                        if (number1 >= 5) right_form1 = " монет";

                        String right_form2 = " монету. \n";
                        if (number2 > 1 && number2 < 5) right_form2 = " монеты. \n";
                        if (number2 >= 5) right_form2 = " монет. \n";

                        String answer_form = " монета";
                        if (answer > 1 && answer < 5) answer_form = " монеты";
                        if (answer >= 5) answer_form = " монет";
                        string_answer = "У Вики " + String.valueOf(number1)
                                + " + " + String.valueOf(number2) + " = " + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("Вика накопила " + String.valueOf(number1) + right_form1 +
                                ", a потом ещё " + String.valueOf(number2) + right_form2
                                + "Сколько у Вики монет?");
                    }
                    else if (rnd_text_task_id == 8) {
                        answer = number1;
                        String string_form_was = "было ";
                        String right_form1 = " конфета. ";
                        if (number1 > 1 && number1 < 5) right_form1 = " конфеты. ";
                        else if (number1 >= 5) right_form1 = " конфет. ";
                        else string_form_was = "былa ";

                        String answer_form = " конфета";
                        if (answer > 1 && answer < 5) answer_form = " конфеты";
                        if (answer >= 5) answer_form = " конфет";
                        string_answer = "Ничего не изменилось. У Васи " + String.valueOf(number1) + " " + answer_form + "!";

                        exercise_text.setText("У Васи " + string_form_was + String.valueOf(number1) + right_form1 +
                                "Васе ничего не подарили на день рождения.\n"
                                + "Сколько конфет у Васи?");
                    }
                    else {
                        answer = getRandomNumber(1,9);
                        String right_form1 = " конфету.";
                        if (answer > 1 && answer < 5) right_form1 = " конфеты.";
                        else if (answer >= 5) right_form1 = " конфет.";

                        string_answer = "Ничего не изменилось. У Васи 0 монет.";

                        exercise_text.setText("У Васи не было денег." +
                                "Васе подарили на день рождения " + String.valueOf(answer) + right_form1 +"\n"
                                + "Сколько монет у Васи?");
                    }
                }
            }
            else if(name_of_exercise.contains("2")){
                int rnd_task_id = getRandomNumber(1,3);
                if (rnd_task_id == 1) {
                    int rnd_num_task_id = getRandomNumber(1,4);
                    if (rnd_num_task_id == 1) {
                        int number1 = getRandomNumber(0, 98);
                        int number2 = getRandomNumber(0, 99 - number1);
                        answer = number1 + number2;
                        string_answer = String.valueOf(number1)
                                + " + " + String.valueOf(number2)
                                + " = " + String.valueOf(answer) + "!";
                        exercise_text.setText(
                                "Сколько будет\n " + String.valueOf(number1)
                                        + " + " + String.valueOf(number2)
                                        + "?"
                        );
                    }
                    else if (rnd_num_task_id == 2) {
                        int number1 = getRandomNumber(0, 97);
                        int number2 = getRandomNumber(0, 98 - number1);
                        int number3 = getRandomNumber(0, 99 - number1 - number2);
                        answer = number1 + number2 + number3;
                        string_answer = String.valueOf(number1)
                                + " + " + String.valueOf(number2)
                                + " + " + String.valueOf(number3)
                                + " = " + String.valueOf(answer) + "!";
                        exercise_text.setText(
                                "Сколько будет\n " + String.valueOf(number1)
                                        + " + " + String.valueOf(number2)
                                        + " + " + String.valueOf(number3)
                                        + "?"
                        );
                    }
                    else {
                        int number1 = getRandomNumber(0, 96);
                        int number2 = getRandomNumber(0, 97 - number1);
                        int number3 = getRandomNumber(0, 98 - number1 - number2);
                        int number4 = getRandomNumber(0, 99 - number1 - number2 - number3);
                        answer = number1 + number2 + number3 + number4;
                        string_answer = String.valueOf(number1)
                                + " + " + String.valueOf(number2)
                                + " + " + String.valueOf(number3)
                                + " + " + String.valueOf(number4)
                                + " = " + String.valueOf(answer) + "!";
                        exercise_text.setText(
                                "Сколько будет\n " + String.valueOf(number1)
                                        + " + " + String.valueOf(number2)
                                        + " + " + String.valueOf(number3)
                                        + " + " + String.valueOf(number4)
                                        + "?"
                        );
                    }
                }
                else{
                    int number1 = getRandomNumber(1, 98);
                    int number2 = getRandomNumber(1, 99 - number1);
                    answer = number1 + number2;
                    int rnd_text_task_id = getRandomNumber(1,10);
                    if (rnd_text_task_id == 1) {
                        String right_form1 = " шарик. ";
                        String string_form_was = "было ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " шарика. ";
                        else if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " шариков. ";
                        else string_form_was = "был ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " шариков. ";

                        String right_form2 = " шарик. \n";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " шарика. \n";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " шариков. \n";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " шариков. \n";

                        String answer_form = " шарик";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " шарика";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " шариков";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " шариков";
                        string_answer = "У Пети " + String.valueOf(number1)
                                + " + " + String.valueOf(number2) + " " + " = " + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("У Пети " + string_form_was + String.valueOf(number1) + right_form1 +
                                "Друзья подарили ему " + String.valueOf(number2) + right_form2
                                + "Сколько шариков у Пети теперь?");
                    }
                    else if (rnd_text_task_id == 2) {
                        String string_form_was = "было ";
                        String right_form1 = " персик. ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " персика. ";
                        else if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " персиков. ";
                        else string_form_was = "был ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " персиков. ";

                        String right_form2 = " персик. \n";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " персика. \n";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " персиков. \n";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " персиков. \n";

                        String answer_form = " персик";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " персика";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " персиков";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " персиков";
                        string_answer = "У Васи " + String.valueOf(number1)
                                + " + " + String.valueOf(number2) + " " + " = " + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("У Васи " + string_form_was + String.valueOf(number1) + right_form1 +
                                "Друзья подарили ему " + String.valueOf(number2) + right_form2
                                + "Сколько персиков у Васи теперь?");
                    }
                    else if (rnd_text_task_id == 3) {
                        String right_form1 = " монету. ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " монеты. ";
                        if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " монет. ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " монет. ";

                        String right_form2 = " монету. \n";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " монеты. \n";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " монет. \n";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " монет. \n";

                        String answer_form = " монета";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " монеты";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " монет";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " монет";
                        string_answer = "У Нины " + String.valueOf(number1)
                                + " + " + String.valueOf(number2) + " " + " = " + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("Нина накопила " + String.valueOf(number1) + right_form1 +
                                "Родители дали ей в школу ещё " + String.valueOf(number2) + right_form2
                                + "Сколько монет Нина сможет потратить?");
                    }
                    else if (rnd_text_task_id == 4) {
                        answer = number1;
                        String right_form1 = " яблоко. ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " яблока. ";
                        if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " яблок. ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " яблок. ";

                        String right_form2 = " грушу. \n";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " груши. \n";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " груш. \n";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " груш. \n";

                        String answer_form = " яблоко";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " яблока";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " яблок";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " яблок";
                        string_answer = "У Лены до сих пор "  + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("У Лены было " + String.valueOf(number1) + right_form1 +
                                "Ещё она сорвала с дерева " + String.valueOf(number2) + right_form2
                                + "Сколько яблок у Лены?");
                    }
                    else if (rnd_text_task_id == 5) {
                        answer = number1;
                        String string_form_was = "было ";
                        String right_form1 = " персик. ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " персика. ";
                        else if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " персиков. ";
                        else string_form_was = "был ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " персиков. ";

                        String right_form2 = " монету. \n";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " монеты. \n";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " монет. \n";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " монет. \n";

                        String answer_form = " персик";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " персика";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " персиков";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " персиков";
                        string_answer = "У Васи до сих пор " + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("У Васи " + string_form_was + String.valueOf(number1) + right_form1 +
                                "Друзья подарили ему " + String.valueOf(number2) + right_form2
                                + "Сколько персиков у Васи теперь?");
                    }
                    else if (rnd_text_task_id == 6) {
                        answer = number2;
                        String string_form_was = "было ";
                        String right_form1 = " персик. ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " персика. ";
                        else if (number1 % 10 >= 5 || number1 % 10 ==0) right_form1 = " персиков. ";
                        else string_form_was = "был ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " персиков. ";


                        String right_form2 = " монету. \n";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " монеты. \n";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " монет. \n";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " монет. \n";

                        String answer_form = " монета";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " монеты";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " монет";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " монет";
                        string_answer = "У Васи теперь " + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("У Васи " + string_form_was + String.valueOf(number1) + right_form1 +
                                "Друзья подарили ему " + String.valueOf(number2) + right_form2
                                + "Сколько монет у Васи теперь, если до этого у Васи не было монет?");
                    }
                    else if (rnd_text_task_id == 7) {
                        String right_form1 = " монету";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " монеты";
                        if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " монет";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " монет";

                        String right_form2 = " монету. \n";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " монеты. \n";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " монет. \n";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " монет. \n";

                        String answer_form = " монета";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " монеты";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " монет";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " монет";
                        string_answer = "У Вики " + String.valueOf(number1)
                                + " + " + String.valueOf(number2) + " = " + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("Вика накопила " + String.valueOf(number1) + right_form1 +
                                ", a потом ещё " + String.valueOf(number2) + right_form2
                                + "Сколько у Вики монет?");
                    }
                    else if (rnd_text_task_id == 8) {
                        answer = number1;
                        String string_form_was = "было ";
                        String right_form1 = " конфета. ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " конфеты. ";
                        else if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " конфет. ";
                        else string_form_was = "былa ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " конфет.";

                        String answer_form = " конфета";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " конфеты";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " конфет";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " конфет.";

                        string_answer = "Ничего не изменилось. У Васи " + String.valueOf(number1) + " " + answer_form + "!";

                        exercise_text.setText("У Васи " + string_form_was + String.valueOf(number1) + right_form1 +
                                "Васе ничего не подарили на день рождения.\n"
                                + "Сколько конфет у Васи?");
                    }
                    else {
                        answer = getRandomNumber(1,9);
                        String right_form1 = " конфету.";
                        if (answer % 10 > 1 && answer % 10 < 5) right_form1 = " конфеты.";
                        else if (answer % 10 >= 5 || answer % 10 == 0) right_form1 = " конфет.";
                        if (answer % 100 >= 10 && answer % 100 <= 19) right_form1 = " конфет.";

                        string_answer = "Ничего не изменилось. У Васи 0 монет.";

                        exercise_text.setText("У Васи не было денег." +
                                "Васе подарили на день рождения " + String.valueOf(answer) + right_form1 +"\n"
                                + "Сколько монет у Васи?");
                    }
                }
            }
            else if(name_of_exercise.contains("3")){
                int rnd_task_id = getRandomNumber(1,3);
                if (rnd_task_id == 1) {
                    int rnd_num_task_id = getRandomNumber(1,4);
                    if (rnd_num_task_id == 1) {
                        int number1 = getRandomNumber(0, 998);
                        int number2 = getRandomNumber(0, 999 - number1);
                        answer = number1 + number2;
                        string_answer = String.valueOf(number1)
                                + " + " + String.valueOf(number2)
                                + " = " + String.valueOf(answer) + "!";
                        exercise_text.setText(
                                "Сколько будет\n " + String.valueOf(number1)
                                        + " + " + String.valueOf(number2)
                                        + "?"
                        );
                    }
                    else if (rnd_num_task_id == 2) {
                        int number1 = getRandomNumber(0, 997);
                        int number2 = getRandomNumber(0, 998 - number1);
                        int number3 = getRandomNumber(0, 999 - number1 - number2);
                        answer = number1 + number2 + number3;
                        string_answer = String.valueOf(number1)
                                + " + " + String.valueOf(number2)
                                + " + " + String.valueOf(number3)
                                + " = " + String.valueOf(answer) + "!";
                        exercise_text.setText(
                                "Сколько будет\n " + String.valueOf(number1)
                                        + " + " + String.valueOf(number2)
                                        + " + " + String.valueOf(number3)
                                        + "?"
                        );
                    }
                    else {
                        int number1 = getRandomNumber(0, 996);
                        int number2 = getRandomNumber(0, 997 - number1);
                        int number3 = getRandomNumber(0, 998 - number1 - number2);
                        int number4 = getRandomNumber(0, 999 - number1 - number2 - number3);
                        answer = number1 + number2 + number3 + number4;
                        string_answer = String.valueOf(number1)
                                + " + " + String.valueOf(number2)
                                + " + " + String.valueOf(number3)
                                + " + " + String.valueOf(number4)
                                + " = " + String.valueOf(answer) + "!";
                        exercise_text.setText(
                                "Сколько будет\n " + String.valueOf(number1)
                                        + " + " + String.valueOf(number2)
                                        + " + " + String.valueOf(number3)
                                        + " + " + String.valueOf(number4)
                                        + "?"
                        );
                    }
                }
                else{
                    int number1 = getRandomNumber(1, 998);
                    int number2 = getRandomNumber(1, 999 - number1);
                    answer = number1 + number2;
                    int rnd_text_task_id = getRandomNumber(1,10);
                    if (rnd_text_task_id == 1) {
                        String right_form1 = " шарик. ";
                        String string_form_was = "было ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " шарика. ";
                        else if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " шариков. ";
                        else string_form_was = "был ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " шариков. ";

                        String right_form2 = " шарик. \n";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " шарика. \n";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " шариков. \n";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " шариков. \n";

                        String answer_form = " шарик";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " шарика";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " шариков";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " шариков";
                        string_answer = "У Пети " + String.valueOf(number1)
                                + " + " + String.valueOf(number2) + " " + " = " + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("У Пети " + string_form_was + String.valueOf(number1) + right_form1 +
                                "Друзья подарили ему " + String.valueOf(number2) + right_form2
                                + "Сколько шариков у Пети теперь?");
                    }
                    else if (rnd_text_task_id == 2) {
                        String string_form_was = "было ";
                        String right_form1 = " персик. ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " персика. ";
                        else if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " персиков. ";
                        else string_form_was = "был ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " персиков. ";

                        String right_form2 = " персик. \n";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " персика. \n";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " персиков. \n";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " персиков. \n";

                        String answer_form = " персик";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " персика";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " персиков";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " персиков";
                        string_answer = "У Васи " + String.valueOf(number1)
                                + " + " + String.valueOf(number2) + " " + " = " + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("У Васи " + string_form_was + String.valueOf(number1) + right_form1 +
                                "Друзья подарили ему " + String.valueOf(number2) + right_form2
                                + "Сколько персиков у Васи теперь?");
                    }
                    else if (rnd_text_task_id == 3) {
                        String right_form1 = " монету. ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " монеты. ";
                        if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " монет. ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " монет. ";

                        String right_form2 = " монету. \n";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " монеты. \n";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " монет. \n";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " монет. \n";

                        String answer_form = " монета";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " монеты";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " монет";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " монет";
                        string_answer = "У Нины " + String.valueOf(number1)
                                + " + " + String.valueOf(number2) + " " + " = " + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("Нина накопила " + String.valueOf(number1) + right_form1 +
                                "Родители дали ей в школу ещё " + String.valueOf(number2) + right_form2
                                + "Сколько монет Нина сможет потратить?");
                    }
                    else if (rnd_text_task_id == 4) {
                        answer = number1;
                        String right_form1 = " яблоко. ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " яблока. ";
                        if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " яблок. ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " яблок. ";

                        String right_form2 = " грушу. \n";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " груши. \n";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " груш. \n";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " груш. \n";

                        String answer_form = " яблоко";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " яблока";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " яблок";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " яблок";
                        string_answer = "У Лены до сих пор "  + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("У Лены было " + String.valueOf(number1) + right_form1 +
                                "Ещё она сорвала с дерева " + String.valueOf(number2) + right_form2
                                + "Сколько яблок у Лены?");
                    }
                    else if (rnd_text_task_id == 5) {
                        answer = number1;
                        String string_form_was = "было ";
                        String right_form1 = " персик. ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " персика. ";
                        else if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " персиков. ";
                        else string_form_was = "был ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " персиков. ";

                        String right_form2 = " монету. \n";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " монеты. \n";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " монет. \n";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " монет. \n";

                        String answer_form = " персик";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " персика";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " персиков";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " персиков";
                        string_answer = "У Васи до сих пор " + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("У Васи " + string_form_was + String.valueOf(number1) + right_form1 +
                                "Друзья подарили ему " + String.valueOf(number2) + right_form2
                                + "Сколько персиков у Васи теперь?");
                    }
                    else if (rnd_text_task_id == 6) {
                        answer = number2;
                        String string_form_was = "было ";
                        String right_form1 = " персик. ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " персика. ";
                        else if (number1 % 10 >= 5 || number1 % 10 ==0) right_form1 = " персиков. ";
                        else string_form_was = "был ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " персиков. ";


                        String right_form2 = " монету. \n";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " монеты. \n";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " монет. \n";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " монет. \n";

                        String answer_form = " монета";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " монеты";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " монет";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " монет";
                        string_answer = "У Васи теперь " + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("У Васи " + string_form_was + String.valueOf(number1) + right_form1 +
                                "Друзья подарили ему " + String.valueOf(number2) + right_form2
                                + "Сколько монет у Васи теперь, если до этого у Васи не было монет?");
                    }
                    else if (rnd_text_task_id == 7) {
                        String right_form1 = " монету";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " монеты";
                        if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " монет";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " монет";

                        String right_form2 = " монету. \n";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " монеты. \n";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " монет. \n";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " монет. \n";

                        String answer_form = " монета";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " монеты";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " монет";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " монет";
                        string_answer = "У Вики " + String.valueOf(number1)
                                + " + " + String.valueOf(number2) + " = " + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("Вика накопила " + String.valueOf(number1) + right_form1 +
                                ", a потом ещё " + String.valueOf(number2) + right_form2
                                + "Сколько у Вики монет?");
                    }
                    else if (rnd_text_task_id == 8) {
                        answer = number1;
                        String string_form_was = "было ";
                        String right_form1 = " конфета. ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " конфеты. ";
                        else if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " конфет. ";
                        else string_form_was = "былa ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " конфет.";

                        String answer_form = " конфета";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " конфеты";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " конфет";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " конфет.";

                        string_answer = "Ничего не изменилось. У Васи " + String.valueOf(number1) + " " + answer_form + "!";

                        exercise_text.setText("У Васи " + string_form_was + String.valueOf(number1) + right_form1 +
                                "Васе ничего не подарили на день рождения.\n"
                                + "Сколько конфет у Васи?");
                    }
                    else {
                        answer = number1;
                        String right_form1 = " конфету.";
                        if (answer % 10 > 1 && answer % 10 < 5) right_form1 = " конфеты.";
                        else if (answer % 10 >= 5 || answer % 10 == 0) right_form1 = " конфет.";
                        if (answer % 100 >= 10 && answer % 100 <= 19) right_form1 = " конфет.";

                        string_answer = "Ничего не изменилось. У Васи 0 монет.";

                        exercise_text.setText("У Васи не было денег." +
                                "Васе подарили на день рождения " + String.valueOf(answer) + right_form1 +"\n"
                                + "Сколько монет у Васи?");
                    }
                }
            }
            else if(name_of_exercise.contains("4")){
                int rnd_task_id = getRandomNumber(1,3);
                if (rnd_task_id == 1) {
                    int rnd_num_task_id = getRandomNumber(1,4);
                    if (rnd_num_task_id == 1) {
                        int number1 = getRandomNumber(0, 9998);
                        int number2 = getRandomNumber(0, 9999 - number1);
                        answer = number1 + number2;
                        string_answer = String.valueOf(number1)
                                + " + " + String.valueOf(number2)
                                + " = " + String.valueOf(answer) + "!";
                        exercise_text.setText(
                                "Сколько будет\n " + String.valueOf(number1)
                                        + " + " + String.valueOf(number2)
                                        + "?"
                        );
                    }
                    else if (rnd_num_task_id == 2) {
                        int number1 = getRandomNumber(0, 9997);
                        int number2 = getRandomNumber(0, 9998 - number1);
                        int number3 = getRandomNumber(0, 9999 - number1 - number2);
                        answer = number1 + number2 + number3;
                        string_answer = String.valueOf(number1)
                                + " + " + String.valueOf(number2)
                                + " + " + String.valueOf(number3)
                                + " = " + String.valueOf(answer) + "!";
                        exercise_text.setText(
                                "Сколько будет\n " + String.valueOf(number1)
                                        + " + " + String.valueOf(number2)
                                        + " + " + String.valueOf(number3)
                                        + "?"
                        );
                    }
                    else {
                        int number1 = getRandomNumber(0, 9996);
                        int number2 = getRandomNumber(0, 9997 - number1);
                        int number3 = getRandomNumber(0, 9998 - number1 - number2);
                        int number4 = getRandomNumber(0, 9999 - number1 - number2 - number3);
                        answer = number1 + number2 + number3 + number4;
                        string_answer = String.valueOf(number1)
                                + " + " + String.valueOf(number2)
                                + " + " + String.valueOf(number3)
                                + " + " + String.valueOf(number4)
                                + " = " + String.valueOf(answer) + "!";
                        exercise_text.setText(
                                "Сколько будет\n " + String.valueOf(number1)
                                        + " + " + String.valueOf(number2)
                                        + " + " + String.valueOf(number3)
                                        + " + " + String.valueOf(number4)
                                        + "?"
                        );
                    }
                }
                else{
                    int number1 = getRandomNumber(1, 9998);
                    int number2 = getRandomNumber(1, 9999 - number1);
                    answer = number1 + number2;
                    int rnd_text_task_id = getRandomNumber(1,10);
                    if (rnd_text_task_id == 1) {
                        String right_form1 = " шарик. ";
                        String string_form_was = "было ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " шарика. ";
                        else if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " шариков. ";
                        else string_form_was = "был ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " шариков. ";

                        String right_form2 = " шарик. \n";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " шарика. \n";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " шариков. \n";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " шариков. \n";

                        String answer_form = " шарик";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " шарика";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " шариков";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " шариков";
                        string_answer = "У Пети " + String.valueOf(number1)
                                + " + " + String.valueOf(number2) + " " + " = " + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("У Пети " + string_form_was + String.valueOf(number1) + right_form1 +
                                "Друзья подарили ему " + String.valueOf(number2) + right_form2
                                + "Сколько шариков у Пети теперь?");
                    }
                    else if (rnd_text_task_id == 2) {
                        String string_form_was = "было ";
                        String right_form1 = " персик. ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " персика. ";
                        else if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " персиков. ";
                        else string_form_was = "был ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " персиков. ";

                        String right_form2 = " персик. \n";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " персика. \n";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " персиков. \n";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " персиков. \n";

                        String answer_form = " персик";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " персика";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " персиков";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " персиков";
                        string_answer = "У Васи " + String.valueOf(number1)
                                + " + " + String.valueOf(number2) + " " + " = " + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("У Васи " + string_form_was + String.valueOf(number1) + right_form1 +
                                "Друзья подарили ему " + String.valueOf(number2) + right_form2
                                + "Сколько персиков у Васи теперь?");
                    }
                    else if (rnd_text_task_id == 3) {
                        String right_form1 = " монету. ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " монеты. ";
                        if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " монет. ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " монет. ";

                        String right_form2 = " монету. \n";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " монеты. \n";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " монет. \n";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " монет. \n";

                        String answer_form = " монета";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " монеты";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " монет";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " монет";
                        string_answer = "У Нины " + String.valueOf(number1)
                                + " + " + String.valueOf(number2) + " " + " = " + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("Нина накопила " + String.valueOf(number1) + right_form1 +
                                "Родители дали ей в школу ещё " + String.valueOf(number2) + right_form2
                                + "Сколько монет Нина сможет потратить?");
                    }
                    else if (rnd_text_task_id == 4) {
                        answer = number1;
                        String right_form1 = " яблоко. ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " яблока. ";
                        if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " яблок. ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " яблок. ";

                        String right_form2 = " грушу. \n";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " груши. \n";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " груш. \n";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " груш. \n";

                        String answer_form = " яблоко";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " яблока";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " яблок";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " яблок";
                        string_answer = "У Лены до сих пор "  + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("У Лены было " + String.valueOf(number1) + right_form1 +
                                "Ещё она сорвала с дерева " + String.valueOf(number2) + right_form2
                                + "Сколько яблок у Лены?");
                    }
                    else if (rnd_text_task_id == 5) {
                        answer = number1;
                        String string_form_was = "было ";
                        String right_form1 = " персик. ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " персика. ";
                        else if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " персиков. ";
                        else string_form_was = "был ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " персиков. ";

                        String right_form2 = " монету. \n";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " монеты. \n";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " монет. \n";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " монет. \n";

                        String answer_form = " персик";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " персика";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " персиков";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " персиков";
                        string_answer = "У Васи до сих пор " + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("У Васи " + string_form_was + String.valueOf(number1) + right_form1 +
                                "Друзья подарили ему " + String.valueOf(number2) + right_form2
                                + "Сколько персиков у Васи теперь?");
                    }
                    else if (rnd_text_task_id == 6) {
                        answer = number2;
                        String string_form_was = "было ";
                        String right_form1 = " персик. ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " персика. ";
                        else if (number1 % 10 >= 5 || number1 % 10 ==0) right_form1 = " персиков. ";
                        else string_form_was = "был ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " персиков. ";


                        String right_form2 = " монету. \n";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " монеты. \n";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " монет. \n";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " монет. \n";

                        String answer_form = " монета";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " монеты";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " монет";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " монет";
                        string_answer = "У Васи теперь " + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("У Васи " + string_form_was + String.valueOf(number1) + right_form1 +
                                "Друзья подарили ему " + String.valueOf(number2) + right_form2
                                + "Сколько монет у Васи теперь, если до этого у Васи не было монет?");
                    }
                    else if (rnd_text_task_id == 7) {
                        String right_form1 = " монету";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " монеты";
                        if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " монет";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " монет";

                        String right_form2 = " монету. \n";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " монеты. \n";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " монет. \n";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " монет. \n";

                        String answer_form = " монета";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " монеты";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " монет";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " монет";
                        string_answer = "У Вики " + String.valueOf(number1)
                                + " + " + String.valueOf(number2) + " = " + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("Вика накопила " + String.valueOf(number1) + right_form1 +
                                ", a потом ещё " + String.valueOf(number2) + right_form2
                                + "Сколько у Вики монет?");
                    }
                    else if (rnd_text_task_id == 8) {
                        answer = number1;
                        String string_form_was = "было ";
                        String right_form1 = " конфета. ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " конфеты. ";
                        else if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " конфет. ";
                        else string_form_was = "былa ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " конфет.";

                        String answer_form = " конфета";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " конфеты";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " конфет";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " конфет.";

                        string_answer = "Ничего не изменилось. У Васи " + String.valueOf(number1) + " " + answer_form + "!";

                        exercise_text.setText("У Васи " + string_form_was + String.valueOf(number1) + right_form1 +
                                "Васе ничего не подарили на день рождения.\n"
                                + "Сколько конфет у Васи?");
                    }
                    else {
                        answer = getRandomNumber(1,9999);
                        String right_form1 = " конфету.";
                        if (answer % 10 > 1 && answer % 10 < 5) right_form1 = " конфеты.";
                        else if (answer % 10 >= 5 || answer % 10 == 0) right_form1 = " конфет.";
                        if (answer % 100 >= 10 && answer % 100 <= 19) right_form1 = " конфет.";

                        string_answer = "Ничего не изменилось. У Васи 0 монет.";

                        exercise_text.setText("У Васи не было денег." +
                                "Васе подарили на день рождения " + String.valueOf(answer) + right_form1 +"\n"
                                + "Сколько монет у Васи?");
                    }
                }
            }
        }
        else if (!name_of_exercise.contains("Сложение") && name_of_exercise.contains("Вычитание")){
            if(name_of_exercise.contains("1")){
                int rnd_task_id = getRandomNumber(1,3);
                if (rnd_task_id == 1) {
                    int rnd_num_task_id = getRandomNumber(1,3);
                    if (rnd_num_task_id == 1) {
                        int number1 = getRandomNumber(0, 9);
                        int number2 = getRandomNumber(0, number1);
                        answer = number1 - number2;
                        string_answer = String.valueOf(number1)
                                + " - " + String.valueOf(number2)
                                + " = " + String.valueOf(answer) + "!";
                        exercise_text.setText(
                                "Сколько будет\n " + String.valueOf(number1)
                                        + " - " + String.valueOf(number2)
                                        + "?"
                        );
                    }
                    else {
                        int number1 = getRandomNumber(0, 9);
                        int number2 = getRandomNumber(0, number1);
                        int number3 = getRandomNumber(0, number1 - number2);
                        answer = number1 - number2 - number3;
                        string_answer = String.valueOf(number1)
                                + " - " + String.valueOf(number2)
                                + " - " + String.valueOf(number3)
                                + " = " + String.valueOf(answer) + "!";
                        exercise_text.setText(
                                "Сколько будет\n " + String.valueOf(number1)
                                        + " - " + String.valueOf(number2)
                                        + " - " + String.valueOf(number3)
                                        + "?"
                        );
                    }
                }
                else{
                    int number1 = getRandomNumber(1, 9);
                    int number2 = getRandomNumber(1, number1);
                    answer = number1 - number2;
                    int rnd_text_task_id = getRandomNumber(1,9);
                    if (rnd_text_task_id == 1) {
                        String right_form1 = " шарик. ";
                        String string_form_was = "было ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " шарика. ";
                        else if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " шариков. ";
                        else string_form_was = "был ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " шариков. ";

                        String right_form2 = " шарик. \n";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " шарика. \n";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " шариков. \n";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " шариков. \n";

                        String answer_form = " шарик";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " шарика";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " шариков";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " шариков";
                        string_answer = "У Пети " + String.valueOf(number1)
                                + " - " + String.valueOf(number2) + " " + " = " + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("Петя купил " + String.valueOf(number1) + right_form1 +
                                "Петя подарил своему другу на день рождения " + String.valueOf(number2) + right_form2
                                + "Сколько шариков у Пети осталось?");
                    }
                    else if (rnd_text_task_id == 2) {
                        String string_form_was = "было ";
                        String right_form1 = " персик. ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " персика. ";
                        else if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " персиков. ";
                        else string_form_was = "был ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " персиков. ";

                        String right_form2 = " персик. ";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " персика. ";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " персиков. ";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " персиков. ";

                        String answer_form = " персик";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " персика";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " персиков";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " персиков";
                        string_answer = "У Васи " + String.valueOf(number1)
                                + " - " + String.valueOf(number2) + " " + " = " + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("У Васи " + string_form_was + String.valueOf(number1) + right_form1 +
                                "Вася решил отдать " + String.valueOf(number2) + right_form2 + " своему другу.\n"
                                + "Сколько персиков у Васи теперь?");
                    }
                    else if (rnd_text_task_id == 3) {
                        String right_form1 = " монету. ";
                        String string_form_was = "была ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " монеты. ";
                        if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " монет. ";
                        else string_form_was = "было ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " монет. ";

                        String right_form2 = " монету ";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " монеты ";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " монет ";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " монет ";

                        String answer_form = " монета";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " монеты";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " монет";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " монет";
                        string_answer = "У Нины " + String.valueOf(number1)
                                + " - " + String.valueOf(number2) + " " + " = " + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("У родителей " + string_form_was + String.valueOf(number1) + right_form1 +
                                "Родители решили оставить " + String.valueOf(number2) + right_form2 + " себе, а оставшиеся монеты отдать Нине\n"
                                + "Сколько монет достанется Нине?");
                    }
                    else if (rnd_text_task_id == 4) {
                        answer = number1;
                        String right_form1 = " яблоко. ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " яблока. ";
                        if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " яблок. ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " яблок. ";

                        String right_form2 = " грушу";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " груши";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " груш";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " груш";

                        String answer_form = " яблоко";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " яблока";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " яблок";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " яблок";
                        string_answer = "У Лены до сих пор "  + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("У Лены было " + String.valueOf(number1) + right_form1 +
                                "Лена решила поделиться своими фруктами и отдала " + String.valueOf(number2) + right_form2 + " соседу \n"
                                + "Сколько яблок осталось у Лены?");
                    }
                    else if (rnd_text_task_id == 5) {
                        answer = number1;
                        String string_form_was = "было ";
                        String right_form1 = " персик. ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " персика. ";
                        else if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " персиков. ";
                        else string_form_was = "был ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " персиков. ";

                        String right_form2 = " монету. \n";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " монеты. \n";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " монет. \n";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " монет. \n";

                        String answer_form = " персик";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " персика";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " персиков";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " персиков";
                        string_answer = "У Васи до сих пор " + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("У Васи " + string_form_was + String.valueOf(number1) + right_form1 +
                                "Вася отдал за них продавцу " + String.valueOf(number2) + right_form2
                                + "Сколько персиков у Васи теперь?");
                    }
                    else if (rnd_text_task_id == 6) {
                        String right_form1 = " монету";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " монеты";
                        if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " монет";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " монет";

                        String right_form2 = " монету. \n";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " монеты. \n";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " монет. \n";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " монет. \n";

                        String answer_form = " монета";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " монеты";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " монет";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " монет";
                        string_answer = "У Вики " + String.valueOf(number1)
                                + " - " + String.valueOf(number2) + " = " + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("Вика накопила " + String.valueOf(number1) + right_form1 +
                                ", a потом купилa себе новую куклу за " + String.valueOf(number2) + right_form2
                                + "Сколько у Вики осталось монет?");
                    }
                    else if (rnd_text_task_id == 7) {
                        answer = number1;
                        String string_form_was = "было ";
                        String right_form1 = " конфета. ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " конфеты. ";
                        else if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " конфет. ";
                        else string_form_was = "былa ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " конфет.";

                        String right_form2 = " конфету.";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " конфеты.";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " конфет.";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " конфет.";

                        String answer_form = " конфета";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " конфеты";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " конфет";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " конфет.";

                        string_answer = "Ничего не изменилось. У Васи " + String.valueOf(number1) + " " + answer_form + "!";

                        exercise_text.setText("У Васи " + string_form_was + String.valueOf(number1) + right_form1 +
                                "Вася решил поделиться ими с другом и отдать ему \n" + String.valueOf(number2) + right_form2 + " Но он передумал.\n"
                                + "Сколько конфет у Васи?");
                    }
                    else {
                        answer = 0;
                        String right_form1 = " монету.";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " монеты.";
                        else if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " монет.";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " монет.";

                        string_answer = "Вася отдал все деньги и у него теперь 0 монет.";

                        exercise_text.setText("Вася накопил " + String.valueOf(number1) + right_form1 +
                                "Затем, Вася потратил все деньги на новую игру." +"\n"
                                + "Сколько монет у Васи?");
                    }
                }
            }
            else if(name_of_exercise.contains("2")){
                int rnd_task_id = getRandomNumber(1,3);
                if (rnd_task_id == 1) {
                    int rnd_num_task_id = getRandomNumber(1,3);
                    if (rnd_num_task_id == 1) {
                        int number1 = getRandomNumber(0, 99);
                        int number2 = getRandomNumber(0, number1);
                        answer = number1 - number2;
                        string_answer = String.valueOf(number1)
                                + " - " + String.valueOf(number2)
                                + " = " + String.valueOf(answer) + "!";
                        exercise_text.setText(
                                "Сколько будет\n " + String.valueOf(number1)
                                        + " - " + String.valueOf(number2)
                                        + "?"
                        );
                    }
                    else {
                        int number1 = getRandomNumber(0, 99);
                        int number2 = getRandomNumber(0, number1);
                        int number3 = getRandomNumber(0, number1 - number2);
                        answer = number1 - number2 - number3;
                        string_answer = String.valueOf(number1)
                                + " - " + String.valueOf(number2)
                                + " - " + String.valueOf(number3)
                                + " = " + String.valueOf(answer) + "!";
                        exercise_text.setText(
                                "Сколько будет\n " + String.valueOf(number1)
                                        + " - " + String.valueOf(number2)
                                        + " - " + String.valueOf(number3)
                                        + "?"
                        );
                    }
                }
                else{
                    int number1 = getRandomNumber(1, 99);
                    int number2 = getRandomNumber(1, number1);
                    answer = number1 - number2;
                    int rnd_text_task_id = getRandomNumber(1,9);
                    if (rnd_text_task_id == 1) {
                        String right_form1 = " шарик. ";
                        String string_form_was = "было ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " шарика. ";
                        else if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " шариков. ";
                        else string_form_was = "был ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " шариков. ";

                        String right_form2 = " шарик. \n";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " шарика. \n";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " шариков. \n";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " шариков. \n";

                        String answer_form = " шарик";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " шарика";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " шариков";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " шариков";
                        string_answer = "У Пети " + String.valueOf(number1)
                                + " - " + String.valueOf(number2) + " " + " = " + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("Петя купил " + String.valueOf(number1) + right_form1 +
                                "Петя подарил своему другу на день рождения " + String.valueOf(number2) + right_form2
                                + "Сколько шариков у Пети осталось?");
                    }
                    else if (rnd_text_task_id == 2) {
                        String string_form_was = "было ";
                        String right_form1 = " персик. ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " персика. ";
                        else if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " персиков. ";
                        else string_form_was = "был ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " персиков. ";

                        String right_form2 = " персик. ";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " персика. ";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " персиков. ";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " персиков. ";

                        String answer_form = " персик";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " персика";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " персиков";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " персиков";
                        string_answer = "У Васи " + String.valueOf(number1)
                                + " - " + String.valueOf(number2) + " " + " = " + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("У Васи " + string_form_was + String.valueOf(number1) + right_form1 +
                                "Вася решил отдать " + String.valueOf(number2) + right_form2 + " своему другу.\n"
                                + "Сколько персиков у Васи теперь?");
                    }
                    else if (rnd_text_task_id == 3) {
                        String right_form1 = " монету. ";
                        String string_form_was = "была ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " монеты. ";
                        if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " монет. ";
                        else string_form_was = "было ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " монет. ";

                        String right_form2 = " монету ";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " монеты ";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " монет ";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " монет ";

                        String answer_form = " монета";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " монеты";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " монет";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " монет";
                        string_answer = "У Нины " + String.valueOf(number1)
                                + " - " + String.valueOf(number2) + " " + " = " + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("У родителей " + string_form_was + String.valueOf(number1) + right_form1 +
                                "Родители решили оставить " + String.valueOf(number2) + right_form2 + " себе, а оставшиеся монеты отдать Нине\n"
                                + "Сколько монет достанется Нине?");
                    }
                    else if (rnd_text_task_id == 4) {
                        answer = number1;
                        String right_form1 = " яблоко. ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " яблока. ";
                        if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " яблок. ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " яблок. ";

                        String right_form2 = " грушу";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " груши";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " груш";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " груш";

                        String answer_form = " яблоко";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " яблока";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " яблок";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " яблок";
                        string_answer = "У Лены до сих пор "  + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("У Лены было " + String.valueOf(number1) + right_form1 +
                                "Лена решила поделиться своими фруктами и отдала " + String.valueOf(number2) + right_form2 + " соседу \n"
                                + "Сколько яблок осталось у Лены?");
                    }
                    else if (rnd_text_task_id == 5) {
                        answer = number1;
                        String string_form_was = "было ";
                        String right_form1 = " персик. ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " персика. ";
                        else if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " персиков. ";
                        else string_form_was = "был ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " персиков. ";

                        String right_form2 = " монету. \n";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " монеты. \n";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " монет. \n";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " монет. \n";

                        String answer_form = " персик";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " персика";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " персиков";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " персиков";
                        string_answer = "У Васи до сих пор " + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("У Васи " + string_form_was + String.valueOf(number1) + right_form1 +
                                "Вася отдал за них продавцу " + String.valueOf(number2) + right_form2
                                + "Сколько персиков у Васи теперь?");
                    }
                    else if (rnd_text_task_id == 6) {
                        String right_form1 = " монету";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " монеты";
                        if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " монет";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " монет";

                        String right_form2 = " монету. \n";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " монеты. \n";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " монет. \n";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " монет. \n";

                        String answer_form = " монета";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " монеты";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " монет";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " монет";
                        string_answer = "У Вики " + String.valueOf(number1)
                                + " - " + String.valueOf(number2) + " = " + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("Вика накопила " + String.valueOf(number1) + right_form1 +
                                ", a потом купилa себе новую куклу за " + String.valueOf(number2) + right_form2
                                + "Сколько у Вики осталось монет?");
                    }
                    else if (rnd_text_task_id == 7) {
                        answer = number1;
                        String string_form_was = "было ";
                        String right_form1 = " конфета. ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " конфеты. ";
                        else if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " конфет. ";
                        else string_form_was = "былa ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " конфет.";

                        String right_form2 = " конфету.";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " конфеты.";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " конфет.";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " конфет.";

                        String answer_form = " конфета";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " конфеты";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " конфет";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " конфет.";

                        string_answer = "Ничего не изменилось. У Васи " + String.valueOf(number1) + " " + answer_form + "!";

                        exercise_text.setText("У Васи " + string_form_was + String.valueOf(number1) + right_form1 +
                                "Вася решил поделиться ими с другом и отдать ему \n" + String.valueOf(number2) + right_form2 + " Но он передумал.\n"
                                + "Сколько конфет у Васи?");
                    }
                    else {
                        answer = 0;
                        String right_form1 = " монету.";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " монеты.";
                        else if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " монет.";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " монет.";

                        string_answer = "Вася отдал все деньги и у него теперь 0 монет.";

                        exercise_text.setText("Вася накопил " + String.valueOf(number1) + right_form1 +
                                "Затем, Вася потратил все деньги на новую игру." +"\n"
                                + "Сколько монет у Васи?");
                    }
                }
            }
            else if(name_of_exercise.contains("3")){
                int rnd_task_id = getRandomNumber(1,3);
                if (rnd_task_id == 1) {
                    int rnd_num_task_id = getRandomNumber(1,3);
                    if (rnd_num_task_id == 1) {
                        int number1 = getRandomNumber(0, 999);
                        int number2 = getRandomNumber(0, number1);
                        answer = number1 - number2;
                        string_answer = String.valueOf(number1)
                                + " - " + String.valueOf(number2)
                                + " = " + String.valueOf(answer) + "!";
                        exercise_text.setText(
                                "Сколько будет\n " + String.valueOf(number1)
                                        + " - " + String.valueOf(number2)
                                        + "?"
                        );
                    }
                    else {
                        int number1 = getRandomNumber(0, 999);
                        int number2 = getRandomNumber(0, number1);
                        int number3 = getRandomNumber(0, number1 - number2);
                        answer = number1 - number2 - number3;
                        string_answer = String.valueOf(number1)
                                + " - " + String.valueOf(number2)
                                + " - " + String.valueOf(number3)
                                + " = " + String.valueOf(answer) + "!";
                        exercise_text.setText(
                                "Сколько будет\n " + String.valueOf(number1)
                                        + " - " + String.valueOf(number2)
                                        + " - " + String.valueOf(number3)
                                        + "?"
                        );
                    }
                }
                else{
                    int number1 = getRandomNumber(1, 999);
                    int number2 = getRandomNumber(1, number1);
                    answer = number1 - number2;
                    int rnd_text_task_id = getRandomNumber(1,9);
                    if (rnd_text_task_id == 1) {
                        String right_form1 = " шарик. ";
                        String string_form_was = "было ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " шарика. ";
                        else if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " шариков. ";
                        else string_form_was = "был ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " шариков. ";

                        String right_form2 = " шарик. \n";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " шарика. \n";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " шариков. \n";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " шариков. \n";

                        String answer_form = " шарик";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " шарика";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " шариков";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " шариков";
                        string_answer = "У Пети " + String.valueOf(number1)
                                + " - " + String.valueOf(number2) + " " + " = " + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("Петя купил " + String.valueOf(number1) + right_form1 +
                                "Петя подарил своему другу на день рождения " + String.valueOf(number2) + right_form2
                                + "Сколько шариков у Пети осталось?");
                    }
                    else if (rnd_text_task_id == 2) {
                        String string_form_was = "было ";
                        String right_form1 = " персик. ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " персика. ";
                        else if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " персиков. ";
                        else string_form_was = "был ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " персиков. ";

                        String right_form2 = " персик. ";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " персика. ";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " персиков. ";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " персиков. ";

                        String answer_form = " персик";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " персика";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " персиков";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " персиков";
                        string_answer = "У Васи " + String.valueOf(number1)
                                + " - " + String.valueOf(number2) + " " + " = " + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("У Васи " + string_form_was + String.valueOf(number1) + right_form1 +
                                "Вася решил отдать " + String.valueOf(number2) + right_form2 + " своему другу.\n"
                                + "Сколько персиков у Васи теперь?");
                    }
                    else if (rnd_text_task_id == 3) {
                        String right_form1 = " монету. ";
                        String string_form_was = "была ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " монеты. ";
                        if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " монет. ";
                        else string_form_was = "было ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " монет. ";

                        String right_form2 = " монету ";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " монеты ";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " монет ";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " монет ";

                        String answer_form = " монета";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " монеты";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " монет";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " монет";
                        string_answer = "У Нины " + String.valueOf(number1)
                                + " - " + String.valueOf(number2) + " " + " = " + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("У родителей " + string_form_was + String.valueOf(number1) + right_form1 +
                                "Родители решили оставить " + String.valueOf(number2) + right_form2 + " себе, а оставшиеся монеты отдать Нине\n"
                                + "Сколько монет достанется Нине?");
                    }
                    else if (rnd_text_task_id == 4) {
                        answer = number1;
                        String right_form1 = " яблоко. ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " яблока. ";
                        if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " яблок. ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " яблок. ";

                        String right_form2 = " грушу";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " груши";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " груш";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " груш";

                        String answer_form = " яблоко";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " яблока";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " яблок";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " яблок";
                        string_answer = "У Лены до сих пор "  + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("У Лены было " + String.valueOf(number1) + right_form1 +
                                "Лена решила поделиться своими фруктами и отдала " + String.valueOf(number2) + right_form2 + " соседу \n"
                                + "Сколько яблок осталось у Лены?");
                    }
                    else if (rnd_text_task_id == 5) {
                        answer = number1;
                        String string_form_was = "было ";
                        String right_form1 = " персик. ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " персика. ";
                        else if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " персиков. ";
                        else string_form_was = "был ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " персиков. ";

                        String right_form2 = " монету. \n";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " монеты. \n";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " монет. \n";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " монет. \n";

                        String answer_form = " персик";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " персика";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " персиков";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " персиков";
                        string_answer = "У Васи до сих пор " + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("У Васи " + string_form_was + String.valueOf(number1) + right_form1 +
                                "Вася отдал за них продавцу " + String.valueOf(number2) + right_form2
                                + "Сколько персиков у Васи теперь?");
                    }
                    else if (rnd_text_task_id == 6) {
                        String right_form1 = " монету";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " монеты";
                        if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " монет";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " монет";

                        String right_form2 = " монету. \n";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " монеты. \n";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " монет. \n";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " монет. \n";

                        String answer_form = " монета";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " монеты";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " монет";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " монет";
                        string_answer = "У Вики " + String.valueOf(number1)
                                + " - " + String.valueOf(number2) + " = " + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("Вика накопила " + String.valueOf(number1) + right_form1 +
                                ", a потом купилa себе новую куклу за " + String.valueOf(number2) + right_form2
                                + "Сколько у Вики осталось монет?");
                    }
                    else if (rnd_text_task_id == 7) {
                        answer = number1;
                        String string_form_was = "было ";
                        String right_form1 = " конфета. ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " конфеты. ";
                        else if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " конфет. ";
                        else string_form_was = "былa ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " конфет.";

                        String right_form2 = " конфету.";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " конфеты.";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " конфет.";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " конфет.";

                        String answer_form = " конфета";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " конфеты";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " конфет";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " конфет.";

                        string_answer = "Ничего не изменилось. У Васи " + String.valueOf(number1) + " " + answer_form + "!";

                        exercise_text.setText("У Васи " + string_form_was + String.valueOf(number1) + right_form1 +
                                "Вася решил поделиться ими с другом и отдать ему \n" + String.valueOf(number2) + right_form2 + " Но он передумал.\n"
                                + "Сколько конфет у Васи?");
                    }
                    else {
                        answer = 0;
                        String right_form1 = " монету.";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " монеты.";
                        else if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " монет.";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " монет.";

                        string_answer = "Вася отдал все деньги и у него теперь 0 монет.";

                        exercise_text.setText("Вася накопил " + String.valueOf(number1) + right_form1 +
                                "Затем, Вася потратил все деньги на новую игру." +"\n"
                                + "Сколько монет у Васи?");
                    }
                }
            }
            else if(name_of_exercise.contains("4")){
                int rnd_task_id = getRandomNumber(1,3);
                if (rnd_task_id == 1) {
                    int rnd_num_task_id = getRandomNumber(1,3);
                    if (rnd_num_task_id == 1) {
                        int number1 = getRandomNumber(0, 9999);
                        int number2 = getRandomNumber(0, number1);
                        answer = number1 - number2;
                        string_answer = String.valueOf(number1)
                                + " - " + String.valueOf(number2)
                                + " = " + String.valueOf(answer) + "!";
                        exercise_text.setText(
                                "Сколько будет\n " + String.valueOf(number1)
                                        + " - " + String.valueOf(number2)
                                        + "?"
                        );
                    }
                    else {
                        int number1 = getRandomNumber(0, 9999);
                        int number2 = getRandomNumber(0, number1);
                        int number3 = getRandomNumber(0, number1 - number2);
                        answer = number1 - number2 - number3;
                        string_answer = String.valueOf(number1)
                                + " - " + String.valueOf(number2)
                                + " - " + String.valueOf(number3)
                                + " = " + String.valueOf(answer) + "!";
                        exercise_text.setText(
                                "Сколько будет\n " + String.valueOf(number1)
                                        + " - " + String.valueOf(number2)
                                        + " - " + String.valueOf(number3)
                                        + "?"
                        );
                    }
                }
                else{
                    int number1 = getRandomNumber(1, 9999);
                    int number2 = getRandomNumber(1, number1);
                    answer = number1 - number2;
                    int rnd_text_task_id = getRandomNumber(1,9);
                    if (rnd_text_task_id == 1) {
                        String right_form1 = " шарик. ";
                        String string_form_was = "было ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " шарика. ";
                        else if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " шариков. ";
                        else string_form_was = "был ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " шариков. ";

                        String right_form2 = " шарик. \n";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " шарика. \n";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " шариков. \n";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " шариков. \n";

                        String answer_form = " шарик";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " шарика";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " шариков";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " шариков";
                        string_answer = "У Пети " + String.valueOf(number1)
                                + " - " + String.valueOf(number2) + " " + " = " + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("Петя купил " + String.valueOf(number1) + right_form1 +
                                "Петя подарил своему другу на день рождения " + String.valueOf(number2) + right_form2
                                + "Сколько шариков у Пети осталось?");
                    }
                    else if (rnd_text_task_id == 2) {
                        String string_form_was = "было ";
                        String right_form1 = " персик. ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " персика. ";
                        else if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " персиков. ";
                        else string_form_was = "был ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " персиков. ";

                        String right_form2 = " персик. ";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " персика. ";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " персиков. ";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " персиков. ";

                        String answer_form = " персик";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " персика";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " персиков";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " персиков";
                        string_answer = "У Васи " + String.valueOf(number1)
                                + " - " + String.valueOf(number2) + " " + " = " + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("У Васи " + string_form_was + String.valueOf(number1) + right_form1 +
                                "Вася решил отдать " + String.valueOf(number2) + right_form2 + " своему другу.\n"
                                + "Сколько персиков у Васи теперь?");
                    }
                    else if (rnd_text_task_id == 3) {
                        String right_form1 = " монету. ";
                        String string_form_was = "была ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " монеты. ";
                        if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " монет. ";
                        else string_form_was = "было ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " монет. ";

                        String right_form2 = " монету ";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " монеты ";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " монет ";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " монет ";

                        String answer_form = " монета";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " монеты";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " монет";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " монет";
                        string_answer = "У Нины " + String.valueOf(number1)
                                + " - " + String.valueOf(number2) + " " + " = " + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("У родителей " + string_form_was + String.valueOf(number1) + right_form1 +
                                "Родители решили оставить " + String.valueOf(number2) + right_form2 + " себе, а оставшиеся монеты отдать Нине\n"
                                + "Сколько монет достанется Нине?");
                    }
                    else if (rnd_text_task_id == 4) {
                        answer = number1;
                        String right_form1 = " яблоко. ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " яблока. ";
                        if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " яблок. ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " яблок. ";

                        String right_form2 = " грушу";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " груши";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " груш";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " груш";

                        String answer_form = " яблоко";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " яблока";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " яблок";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " яблок";
                        string_answer = "У Лены до сих пор "  + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("У Лены было " + String.valueOf(number1) + right_form1 +
                                "Лена решила поделиться своими фруктами и отдала " + String.valueOf(number2) + right_form2 + " соседу \n"
                                + "Сколько яблок осталось у Лены?");
                    }
                    else if (rnd_text_task_id == 5) {
                        answer = number1;
                        String string_form_was = "было ";
                        String right_form1 = " персик. ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " персика. ";
                        else if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " персиков. ";
                        else string_form_was = "был ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " персиков. ";

                        String right_form2 = " монету. \n";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " монеты. \n";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " монет. \n";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " монет. \n";

                        String answer_form = " персик";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " персика";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " персиков";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " персиков";
                        string_answer = "У Васи до сих пор " + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("У Васи " + string_form_was + String.valueOf(number1) + right_form1 +
                                "Вася отдал за них продавцу " + String.valueOf(number2) + right_form2
                                + "Сколько персиков у Васи теперь?");
                    }
                    else if (rnd_text_task_id == 6) {
                        String right_form1 = " монету";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " монеты";
                        if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " монет";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " монет";

                        String right_form2 = " монету. \n";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " монеты. \n";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " монет. \n";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " монет. \n";

                        String answer_form = " монета";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " монеты";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " монет";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " монет";
                        string_answer = "У Вики " + String.valueOf(number1)
                                + " - " + String.valueOf(number2) + " = " + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("Вика накопила " + String.valueOf(number1) + right_form1 +
                                ", a потом купилa себе новую куклу за " + String.valueOf(number2) + right_form2
                                + "Сколько у Вики осталось монет?");
                    }
                    else if (rnd_text_task_id == 7) {
                        answer = number1;
                        String string_form_was = "было ";
                        String right_form1 = " конфета. ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " конфеты. ";
                        else if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " конфет. ";
                        else string_form_was = "былa ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " конфет.";

                        String right_form2 = " конфету.";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " конфеты.";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " конфет.";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " конфет.";

                        String answer_form = " конфета";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " конфеты";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " конфет";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " конфет.";

                        string_answer = "Ничего не изменилось. У Васи " + String.valueOf(number1) + " " + answer_form + "!";

                        exercise_text.setText("У Васи " + string_form_was + String.valueOf(number1) + right_form1 +
                                "Вася решил поделиться ими с другом и отдать ему \n" + String.valueOf(number2) + right_form2 + " Но он передумал.\n"
                                + "Сколько конфет у Васи?");
                    }
                    else {
                        answer = 0;
                        String right_form1 = " монету.";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " монеты.";
                        else if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " монет.";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " монет.";

                        string_answer = "Вася отдал все деньги и у него теперь 0 монет.";

                        exercise_text.setText("Вася накопил " + String.valueOf(number1) + right_form1 +
                                "Затем, Вася потратил все деньги на новую игру." +"\n"
                                + "Сколько монет у Васи?");
                    }
                }
            }
        }
        else if (name_of_exercise.contains("Умножение")){
            if(name_of_exercise.contains("1")) {
                int number1 = getRandomNumber(1, 14);
                int number2 = getRandomNumber(0, 7);
                answer = number2;
                String format_raz = " раз";
                if (answer % 10 > 1 && answer % 10 < 5) format_raz = " раза";
                string_answer =
                        String.valueOf(number1) + " x " + String.valueOf(number2) + " = " + String.valueOf(number1*number2) + "!"
                        + "\nПо " + String.valueOf(number1) + " взять " + String.valueOf(number2) + format_raz +" получится " + String.valueOf(number1*number2)
                        + "\nИли же: " + String.valueOf(number1) + " умножить на " + String.valueOf(number2) + " получится " + String.valueOf(number1*number2);

                String text_multiply = String.valueOf(number1);
                for(int i = 0; i < number2 - 1; i++){
                    text_multiply = text_multiply + " + " + String.valueOf(number1);
                }
                text_multiply = text_multiply + " = " + String.valueOf(number1*number2);

                exercise_text.setText(
                        "Представь в виде умножения\n" + text_multiply
                        + "\nКакое число должно стоять вместо знака вопроса?"
                        + "\n" + String.valueOf(number1) + " x ? = " + String.valueOf(number1*number2)
                );
            }
            else if(name_of_exercise.contains("2")){
                int rnd_task_id = getRandomNumber(1, 4);
                if (rnd_task_id == 1) {

                    int number1 = getRandomNumber(0, 41);
                    int number2;
                    if (number1 == 0) number2 = getRandomNumber(0, 99);
                    else number2 = getRandomNumber(0, 100 / number1);

                    answer = number1 * number2;
                    string_answer = String.valueOf(number1)
                            + " x " + String.valueOf(number2)
                            + " = " + String.valueOf(answer) + "!";

                    exercise_text.setText(
                            "Сколько будет\n " + String.valueOf(number1)
                                    + " x " + String.valueOf(number2)
                                    + "?"
                    );
                }
                else{
                    int number1 = getRandomNumber(0, 41);
                    int number2;
                    if (number1 == 0) number2 = getRandomNumber(0, 99);
                    else number2 = getRandomNumber(0, 99 / number1);
                    answer = number1 * number2;

                    int rnd_text_task_id = getRandomNumber(1,5);
                    if (rnd_text_task_id == 1) {
                        String right_form1 = " шарик. ";
                        String string_form_was = "было ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " шарика. ";
                        else if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " шариков. ";
                        else string_form_was = "был ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " шариков. ";

                        String right_form2 = " шарик. \n";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " шарика. \n";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " шариков. \n";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " шариков. \n";

                        String answer_form = " шарик";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " шарика";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " шариков";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " шариков";
                        string_answer = "У Пети " + String.valueOf(number2)
                                + " * " + String.valueOf(number1) + " " + " = " + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("В магазине были шарики " + String.valueOf(number2) + " разных цветов. Петя купил по " + String.valueOf(number1) + right_form1 +
                                "каждого цвета." + "\nСколько всего шариков купил Петя?");
                    }
                    else if (rnd_text_task_id == 2) {
                        number1 = getRandomNumber(1, 49);
                        answer = number1*2;
                        String string_form_was = "было ";
                        String right_form1 = " дерево. ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " дерева. ";
                        else if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " деревьев. ";
                        else string_form_was = "было ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " деревьев. ";

                        String answer_form = " дерево";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " дерева";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " деревьев";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " деревьев";
                        string_answer = "Вася хочет, чтобы у него в саду было " + String.valueOf(number1)
                                + " * " + "2" + " " + " = " + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("У Васи в саду  " + String.valueOf(number1) + right_form1 +
                                "Вася хочет увеличить количество деревьев в своём саду вдвое\n"
                                + "Сколько деревьев хочет иметь Вася в своём саду?");
                    }
                    else if (rnd_text_task_id == 3) {
                        number1 = getRandomNumber(1, 33);
                        answer = number1*3;

                        String string_form_was = "было ";
                        String right_form1 = " дерево. ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " дерева. ";
                        else if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " деревьев. ";
                        else string_form_was = "было ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " деревьев. ";

                        String answer_form = " дерево";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " дерева";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " деревьев";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " деревьев";
                        string_answer = "Вася хочет, чтобы у него в саду было " + String.valueOf(number1)
                                + " * " + "3" + " " + " = " + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("У Васи в саду  " + String.valueOf(number1) + right_form1 +
                                "Вася хочет увеличить количество деревьев в своём саду втрое\n"
                                + "Сколько деревьев хочет иметь Вася в своём саду?");
                    }
                    else if (rnd_text_task_id == 4) {
                        number1 = getRandomNumber(1, 41);
                        if (number1 == 0) number2 = getRandomNumber(1, 99);
                        else number2 = getRandomNumber(1, 99 / number1);
                        answer = number1*number2;

                        String right_form1 = " тарелка. ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " тарелки. ";
                        if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " тарелок. ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " тарелок. ";

                        String right_form2 = " груше";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " груши";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " груш";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " груш";

                        int number3_trash = getRandomNumber(1, number2);
                        String right_form3 = " яблоку.";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form3 = " яблока.";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form3 = " яблок.";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form3 = " яблок.";

                        String answer_form = " груша";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " груши";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = "  груш";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = "  груш";
                        string_answer = "Всего: " + String.valueOf(number1)
                                + " * " + String.valueOf(number2) + " " + " = " + String.valueOf(answer) + answer_form + " в тарелках!";

                        exercise_text.setText("На столе лежит " + String.valueOf(number1) + right_form1 +
                                "в каждой из которых лежит по " + String.valueOf(number2) + right_form2 +
                                " и по " + String.valueOf(number3_trash) + right_form3 +
                                "\nСколько всего груш лежит в тарелках?");
                    }
                    else {
                        number1 = getRandomNumber(1, 41);
                        if (number1 == 0) number2 = getRandomNumber(1, 99);
                        else number2 = getRandomNumber(1, 99 / number1);
                        answer = number1*number2;

                        String right_form1 = " тарелка. ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " тарелки. ";
                        if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " тарелок. ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " тарелок. ";

                        String right_form2 = " груше";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " груши";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " груш";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " груш";

                        int number3_trash = getRandomNumber(1, number2);
                        String right_form3 = " яблоку.";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form3 = " яблока.";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form3 = " яблок.";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form3 = " яблок.";

                        String answer_form = " яблоко";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " яблока";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = "  яблок";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = "  яблок";
                        string_answer = "Всего: " + String.valueOf(number1)
                                + " * " + String.valueOf(number2) + " " + " = " + String.valueOf(answer) + answer_form + " в тарелках!";

                        exercise_text.setText("На столе лежит " + String.valueOf(number1) + right_form1 +
                                "в каждой из которых лежит по " + String.valueOf(number2) + right_form2 +
                                " и по " + String.valueOf(number3_trash) + right_form3 +
                                "\nСколько всего яблок лежит в тарелках?");
                    }
                }
            }
            else if(name_of_exercise.contains("3")){
                int rnd_task_id = getRandomNumber(1, 5);
                if (rnd_task_id < 3) {

                    int number1 = getRandomNumber(0, 410);
                    int number2;
                    if (number1 == 0) number2 = getRandomNumber(0, 999);
                    else number2 = getRandomNumber(0, 999 / number1);

                    answer = number1 * number2;
                    string_answer = String.valueOf(number1)
                            + " x " + String.valueOf(number2)
                            + " = " + String.valueOf(answer) + "!";

                    exercise_text.setText(
                            "Сколько будет\n " + String.valueOf(number1)
                                    + " x " + String.valueOf(number2)
                                    + "?"
                    );
                }
                else{
                    int number1 = getRandomNumber(0, 410);
                    int number2;
                    if (number1 == 0) number2 = getRandomNumber(0, 999);
                    else number2 = getRandomNumber(0, 999 / number1);
                    answer = number1 * number2;

                    int rnd_text_task_id = getRandomNumber(1,5);
                    if (rnd_text_task_id == 1) {
                        String right_form1 = " шарик. ";
                        String string_form_was = "было ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " шарика. ";
                        else if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " шариков. ";
                        else string_form_was = "был ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " шариков. ";

                        String right_form2 = " шарик. \n";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " шарика. \n";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " шариков. \n";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " шариков. \n";

                        String answer_form = " шарик";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " шарика";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " шариков";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " шариков";
                        string_answer = "У Пети " + String.valueOf(number2)
                                + " * " + String.valueOf(number1) + " " + " = " + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("В магазине были шарики " + String.valueOf(number2) + "разных цветов. Петя купил по " + String.valueOf(number1) + right_form1 +
                                "каждого цвета." + "\nСколько всего шариков купил Петя?");
                    }
                    else if (rnd_text_task_id == 2) {
                        number1 = getRandomNumber(1, 499);
                        answer = number1*2;
                        String string_form_was = "было ";
                        String right_form1 = " дерево. ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " дерева. ";
                        else if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " деревьев. ";
                        else string_form_was = "было ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " деревьев. ";

                        String answer_form = " дерево";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " дерева";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " деревьев";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " деревьев";
                        string_answer = "Вася хочет, чтобы у него в саду было " + String.valueOf(number1)
                                + " * " + "2" + " " + " = " + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("У Васи в саду  " + String.valueOf(number1) + right_form1 +
                                "Вася хочет увеличить количество деревьев в своём саду вдвое\n"
                                + "Сколько деревьев хочет иметь Вася в своём саду?");
                    }
                    else if (rnd_text_task_id == 3) {
                        number1 = getRandomNumber(1, 333);
                        answer = number1*3;

                        String string_form_was = "было ";
                        String right_form1 = " дерево. ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " дерева. ";
                        else if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " деревьев. ";
                        else string_form_was = "было ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " деревьев. ";

                        String answer_form = " дерево";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " дерева";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " деревьев";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " деревьев";
                        string_answer = "Вася хочет, чтобы у него в саду было " + String.valueOf(number1)
                                + " * " + "3" + " " + " = " + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("У Васи в саду  " + String.valueOf(number1) + right_form1 +
                                "Вася хочет увеличить количество деревьев в своём саду втрое\n"
                                + "Сколько деревьев хочет иметь Вася в своём саду?");
                    }
                    else if (rnd_text_task_id == 4) {
                        number1 = getRandomNumber(1, 410);
                        if (number1 == 0) number2 = getRandomNumber(1, 999);
                        else number2 = getRandomNumber(1, 999 / number1);
                        answer = number1*number2;

                        String right_form1 = " тарелка. ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " тарелки. ";
                        if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " тарелок. ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " тарелок. ";

                        String right_form2 = " груше";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " груши";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " груш";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " груш";

                        int number3_trash = getRandomNumber(1, number2);
                        String right_form3 = " яблоку.";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form3 = " яблока.";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form3 = " яблок.";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form3 = " яблок.";

                        String answer_form = " груша";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " груши";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = "  груш";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = "  груш";
                        string_answer = "Всего: " + String.valueOf(number1)
                                + " * " + String.valueOf(number2) + " " + " = " + String.valueOf(answer) + answer_form + " в тарелках!";

                        exercise_text.setText("На столе лежит " + String.valueOf(number1) + right_form1 +
                                "в каждой из которых лежит по " + String.valueOf(number2) + right_form2 +
                                " и по " + String.valueOf(number3_trash) + right_form3 +
                                "\nСколько всего груш лежит в тарелках?");
                    }
                    else {
                        number1 = getRandomNumber(1, 410);
                        if (number1 == 0) number2 = getRandomNumber(1, 999);
                        else number2 = getRandomNumber(1, 999 / number1);
                        answer = number1*number2;

                        String right_form1 = " тарелка. ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " тарелки. ";
                        if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " тарелок. ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " тарелок. ";

                        String right_form2 = " груше";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " груши";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " груш";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " груш";

                        int number3_trash = getRandomNumber(1, number2);
                        String right_form3 = " яблоку.";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form3 = " яблока.";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form3 = " яблок.";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form3 = " яблок.";

                        String answer_form = " яблоко";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " яблока";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = "  яблок";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = "  яблок";
                        string_answer = "Всего: " + String.valueOf(number1)
                                + " * " + String.valueOf(number2) + " " + " = " + String.valueOf(answer) + answer_form + " в тарелках!";

                        exercise_text.setText("На столе лежит " + String.valueOf(number1) + right_form1 +
                                "в каждой из которых лежит по " + String.valueOf(number2) + right_form2 +
                                " и по " + String.valueOf(number3_trash) + right_form3 +
                                "\nСколько всего яблок лежит в тарелках?");
                    }
                }
            }
            else if(name_of_exercise.contains("4")) {
                int rnd_task_id = getRandomNumber(1, 5);
                if (rnd_task_id < 3) {

                    int number1 = getRandomNumber(0, 4100);
                    int number2;
                    if (number1 == 0) number2 = getRandomNumber(0, 9999);
                    else number2 = getRandomNumber(0, 9999 / number1);

                    answer = number1 * number2;
                    string_answer = String.valueOf(number1)
                            + " x " + String.valueOf(number2)
                            + " = " + String.valueOf(answer) + "!";

                    exercise_text.setText(
                            "Сколько будет\n " + String.valueOf(number1)
                                    + " x " + String.valueOf(number2)
                                    + "?"
                    );
                }
                else{
                    int number1 = getRandomNumber(0, 4100);
                    int number2;
                    if (number1 == 0) number2 = getRandomNumber(0, 9999);
                    else number2 = getRandomNumber(0, 9999 / number1);
                    answer = number1 * number2;

                    int rnd_text_task_id = getRandomNumber(1,5);
                    if (rnd_text_task_id == 1) {
                        String right_form1 = " шарик. ";
                        String string_form_was = "было ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " шарика. ";
                        else if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " шариков. ";
                        else string_form_was = "был ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " шариков. ";

                        String right_form2 = " шарик. \n";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " шарика. \n";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " шариков. \n";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " шариков. \n";

                        String answer_form = " шарик";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " шарика";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " шариков";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " шариков";
                        string_answer = "У Пети " + String.valueOf(number2)
                                + " * " + String.valueOf(number1) + " " + " = " + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("В магазине были шарики " + String.valueOf(number2) + "разных цветов. Петя купил по " + String.valueOf(number1) + right_form1 +
                                "каждого цвета." + "\nСколько всего шариков купил Петя?");
                    }
                    else if (rnd_text_task_id == 2) {
                        number1 = getRandomNumber(1, 4999);
                        answer = number1*2;
                        String string_form_was = "было ";
                        String right_form1 = " дерево. ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " дерева. ";
                        else if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " деревьев. ";
                        else string_form_was = "было ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " деревьев. ";

                        String answer_form = " дерево";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " дерева";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " деревьев";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " деревьев";
                        string_answer = "Вася хочет, чтобы у него в саду было " + String.valueOf(number1)
                                + " * " + "2" + " " + " = " + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("У Васи в саду  " + String.valueOf(number1) + right_form1 +
                                "Вася хочет увеличить количество деревьев в своём саду вдвое\n"
                                + "Сколько деревьев хочет иметь Вася в своём саду?");
                    }
                    else if (rnd_text_task_id == 3) {
                        number1 = getRandomNumber(1, 3333);
                        answer = number1*3;

                        String string_form_was = "было ";
                        String right_form1 = " дерево. ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " дерева. ";
                        else if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " деревьев. ";
                        else string_form_was = "было ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " деревьев. ";

                        String answer_form = " дерево";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " дерева";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " деревьев";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " деревьев";
                        string_answer = "Вася хочет, чтобы у него в саду было " + String.valueOf(number1)
                                + " * " + "3" + " " + " = " + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("У Васи в саду  " + String.valueOf(number1) + right_form1 +
                                "Вася хочет увеличить количество деревьев в своём саду втрое\n"
                                + "Сколько деревьев хочет иметь Вася в своём саду?");
                    }
                    else if (rnd_text_task_id == 4) {
                        number1 = getRandomNumber(1, 4100);
                        if (number1 == 0) number2 = getRandomNumber(1, 9999);
                        else number2 = getRandomNumber(1, 9999 / number1);
                        answer = number1*number2;

                        String right_form1 = " тарелка. ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " тарелки. ";
                        if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " тарелок. ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " тарелок. ";

                        String right_form2 = " груше";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " груши";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " груш";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " груш";

                        int number3_trash = getRandomNumber(1, number2);
                        String right_form3 = " яблоку.";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form3 = " яблока.";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form3 = " яблок.";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form3 = " яблок.";

                        String answer_form = " груша";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " груши";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = "  груш";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = "  груш";
                        string_answer = "Всего: " + String.valueOf(number1)
                                + " * " + String.valueOf(number2) + " " + " = " + String.valueOf(answer) + answer_form + " в тарелках!";

                        exercise_text.setText("На столе лежит " + String.valueOf(number1) + right_form1 +
                                "в каждой из которых лежит по " + String.valueOf(number2) + right_form2 +
                                " и по " + String.valueOf(number3_trash) + right_form3 +
                                "\nСколько всего груш лежит в тарелках?");
                    }
                    else {
                        number1 = getRandomNumber(1, 4100);
                        if (number1 == 0) number2 = getRandomNumber(1, 9999);
                        else number2 = getRandomNumber(1, 9999 / number1);
                        answer = number1*number2;

                        String right_form1 = " тарелка. ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " тарелки. ";
                        if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " тарелок. ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " тарелок. ";

                        String right_form2 = " груше";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " груши";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " груш";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " груш";

                        int number3_trash = getRandomNumber(1, number2);
                        String right_form3 = " яблоку.";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form3 = " яблока.";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form3 = " яблок.";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form3 = " яблок.";

                        String answer_form = " яблоко";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " яблока";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = "  яблок";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = "  яблок";
                        string_answer = "Всего: " + String.valueOf(number1)
                                + " * " + String.valueOf(number2) + " " + " = " + String.valueOf(answer) + answer_form + " в тарелках!";

                        exercise_text.setText("На столе лежит " + String.valueOf(number1) + right_form1 +
                                "в каждой из которых лежит по " + String.valueOf(number2) + right_form2 +
                                " и по " + String.valueOf(number3_trash) + right_form3 +
                                "\nСколько всего яблок лежит в тарелках?");
                    }
                }
            }
        }
        else if (name_of_exercise.contains("Деление")){
            if(name_of_exercise.contains("1")) {
                int number1 = getRandomNumber(1, 14);
                int number2 = getRandomNumber(0, 7);
                answer = number2;

                string_answer = String.valueOf(number1*number2)
                        + " : " + String.valueOf(number1)
                        + " = " + String.valueOf(answer) + "!";

                exercise_text.setText(
                        "Cколько будет\n" + String.valueOf(number1*number2) + " : " + String.valueOf(number1) + "?"
                );
            }
            else if(name_of_exercise.contains("2")){
                int rnd_task_id = 1;
                if (rnd_task_id == 1) {
                    int number1 = getRandomNumber(0, 41);
                    int number2;
                    if (number1 == 0) number2 = getRandomNumber(0, 99);
                    else number2 = getRandomNumber(0, 100 / number1);

                    answer = number2;
                    string_answer = String.valueOf(number1*number2)
                            + " : " + String.valueOf(number1)
                            + " = " + String.valueOf(answer) + "!";

                    exercise_text.setText(
                            "Сколько будет\n " + String.valueOf(number1*number2)
                                    + " : " + String.valueOf(number1)
                                    + "?"
                    );
                }
                else{
                    int number1 = getRandomNumber(0, 41);
                    int number2;
                    if (number1 == 0) number2 = getRandomNumber(0, 99);
                    else number2 = getRandomNumber(0, 100 / number1);
                    answer = number2;

                    int rnd_text_task_id = getRandomNumber(1,5);
                    if (rnd_text_task_id == 1) {
                        String right_form1 = " шарик. ";
                        String string_form_was = "было ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " шарика. ";
                        else if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " шариков. ";
                        else string_form_was = "был ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " шариков. ";

                        String right_form2 = " шарик. \n";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " шарика. \n";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " шариков. \n";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " шариков. \n";

                        String answer_form = " шарик";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " шарика";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " шариков";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " шариков";
                        string_answer = "У Пети " + String.valueOf(number2)
                                + " * " + String.valueOf(number1) + " " + " = " + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("У Пети были шарики " + String.valueOf(number1) + "разных цветов, в равном количестве каждого цвета. "
                                + ". Всего у Пети " + String.valueOf(number1*number2) + right_form1 +
                                "." + "\nПо сколько шариков каждого цвета купил Петя?");
                    }
                    else if (rnd_text_task_id == 2) {
                        number1 = getRandomNumber(1, 49);
                        answer = number1*2;
                        String string_form_was = "было ";
                        String right_form1 = " дерево. ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " дерева. ";
                        else if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " деревьев. ";
                        else string_form_was = "было ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " деревьев. ";

                        String answer_form = " дерево";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " дерева";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " деревьев";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " деревьев";
                        string_answer = "Вася хочет, чтобы у него в саду было " + String.valueOf(number1)
                                + " * " + "2" + " " + " = " + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("У Васи в саду  " + String.valueOf(number1) + right_form1 +
                                "Вася хочет увеличить количество деревьев в своём саду вдвое\n"
                                + "Сколько деревьев хочет иметь Вася в своём саду?");
                    }
                    else if (rnd_text_task_id == 3) {
                        number1 = getRandomNumber(1, 33);
                        answer = number1*3;

                        String string_form_was = "было ";
                        String right_form1 = " дерево. ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " дерева. ";
                        else if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " деревьев. ";
                        else string_form_was = "было ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " деревьев. ";

                        String answer_form = " дерево";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " дерева";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " деревьев";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " деревьев";
                        string_answer = "Вася хочет, чтобы у него в саду было " + String.valueOf(number1)
                                + " * " + "3" + " " + " = " + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("У Васи в саду  " + String.valueOf(number1) + right_form1 +
                                "Вася хочет увеличить количество деревьев в своём саду втрое\n"
                                + "Сколько деревьев хочет иметь Вася в своём саду?");
                    }
                    else if (rnd_text_task_id == 4) {
                        number1 = getRandomNumber(1, 41);
                        if (number1 == 0) number2 = getRandomNumber(1, 99);
                        else number2 = getRandomNumber(1, 100 / number1);
                        answer = number1*number2;

                        String right_form1 = " тарелка. ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " тарелки. ";
                        if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " тарелок. ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " тарелок. ";

                        String right_form2 = " груше";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " груши";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " груш";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " груш";

                        int number3_trash = getRandomNumber(1, number2);
                        String right_form3 = " яблоку.";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form3 = " яблока.";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form3 = " яблок.";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form3 = " яблок.";

                        String answer_form = " груша";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " груши";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = "  груш";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = "  груш";
                        string_answer = "Всего: " + String.valueOf(number1)
                                + " * " + String.valueOf(number2) + " " + " = " + String.valueOf(answer) + answer_form + " в тарелках!";

                        exercise_text.setText("На столе лежит " + String.valueOf(number1) + right_form1 +
                                "в каждой из которых лежит по " + String.valueOf(number2) + right_form2 +
                                " и по " + String.valueOf(number3_trash) + right_form3 +
                                "\nСколько всего груш лежит в тарелках?");
                    }
                    else {
                        number1 = getRandomNumber(1, 41);
                        if (number1 == 0) number2 = getRandomNumber(1, 99);
                        else number2 = getRandomNumber(1, 100 / number1);
                        answer = number1*number2;

                        String right_form1 = " тарелка. ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " тарелки. ";
                        if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " тарелок. ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " тарелок. ";

                        String right_form2 = " груше";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " груши";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " груш";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " груш";

                        int number3_trash = getRandomNumber(1, number2);
                        String right_form3 = " яблоку.";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form3 = " яблока.";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form3 = " яблок.";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form3 = " яблок.";

                        String answer_form = " яблоко";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " яблока";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = "  яблок";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = "  яблок";
                        string_answer = "Всего: " + String.valueOf(number1)
                                + " * " + String.valueOf(number2) + " " + " = " + String.valueOf(answer) + answer_form + " в тарелках!";

                        exercise_text.setText("На столе лежит " + String.valueOf(number1) + right_form1 +
                                "в каждой из которых лежит по " + String.valueOf(number2) + right_form2 +
                                " и по " + String.valueOf(number3_trash) + right_form3 +
                                "\nСколько всего яблок лежит в тарелках?");
                    }
                }
            }
            else if(name_of_exercise.contains("3")){
                int rnd_task_id = 1;
                if (rnd_task_id == 1) {
                    int rnd_num_task_id = 1;
                    if (rnd_num_task_id == 1) {
                        int number1 = getRandomNumber(0, 410);
                        int number2;
                        if (number1 == 0) number2 = getRandomNumber(0, 999);
                        else number2 = getRandomNumber(0, 999 / number1);

                        answer = number2;
                        string_answer = String.valueOf(number1*number2)
                                + " : " + String.valueOf(number1)
                                + " = " + String.valueOf(answer) + "!";

                        exercise_text.setText(
                                "Сколько будет\n " + String.valueOf(number1*number2)
                                        + " : " + String.valueOf(number1)
                                        + "?"
                        );
                    }
                    else {
                        int number1 = getRandomNumber(0, 999);
                        int number2 = getRandomNumber(0, number1);
                        int number3 = getRandomNumber(0, number1 - number2);
                        answer = number1 - number2 - number3;
                        string_answer = String.valueOf(number1)
                                + " - " + String.valueOf(number2)
                                + " - " + String.valueOf(number3)
                                + " = " + String.valueOf(answer) + "!";
                        exercise_text.setText(
                                "Сколько будет\n " + String.valueOf(number1)
                                        + " - " + String.valueOf(number2)
                                        + " - " + String.valueOf(number3)
                                        + "?"
                        );
                    }
                }
                else{
                    int number1 = getRandomNumber(1, 999);
                    int number2 = getRandomNumber(1, number1);
                    answer = number1 - number2;
                    int rnd_text_task_id = getRandomNumber(1,9);
                    if (rnd_text_task_id == 1) {
                        String right_form1 = " шарик. ";
                        String string_form_was = "было ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " шарика. ";
                        else if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " шариков. ";
                        else string_form_was = "был ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " шариков. ";

                        String right_form2 = " шарик. \n";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " шарика. \n";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " шариков. \n";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " шариков. \n";

                        String answer_form = " шарик";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " шарика";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " шариков";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " шариков";
                        string_answer = "У Пети " + String.valueOf(number1)
                                + " - " + String.valueOf(number2) + " " + " = " + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("Петя купил " + String.valueOf(number1) + right_form1 +
                                "Петя подарил своему другу на день рождения " + String.valueOf(number2) + right_form2
                                + "Сколько шариков у Пети осталось?");
                    }
                    else if (rnd_text_task_id == 2) {
                        String string_form_was = "было ";
                        String right_form1 = " персик. ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " персика. ";
                        else if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " персиков. ";
                        else string_form_was = "был ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " персиков. ";

                        String right_form2 = " персик. ";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " персика. ";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " персиков. ";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " персиков. ";

                        String answer_form = " персик";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " персика";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " персиков";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " персиков";
                        string_answer = "У Васи " + String.valueOf(number1)
                                + " - " + String.valueOf(number2) + " " + " = " + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("У Васи " + string_form_was + String.valueOf(number1) + right_form1 +
                                "Вася решил отдать " + String.valueOf(number2) + right_form2 + " своему другу.\n"
                                + "Сколько персиков у Васи теперь?");
                    }
                    else if (rnd_text_task_id == 3) {
                        String right_form1 = " монету. ";
                        String string_form_was = "была ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " монеты. ";
                        if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " монет. ";
                        else string_form_was = "было ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " монет. ";

                        String right_form2 = " монету ";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " монеты ";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " монет ";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " монет ";

                        String answer_form = " монета";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " монеты";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " монет";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " монет";
                        string_answer = "У Нины " + String.valueOf(number1)
                                + " - " + String.valueOf(number2) + " " + " = " + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("У родителей " + string_form_was + String.valueOf(number1) + right_form1 +
                                "Родители решили оставить " + String.valueOf(number2) + right_form2 + " себе, а оставшиеся монеты отдать Нине\n"
                                + "Сколько монет достанется Нине?");
                    }
                    else if (rnd_text_task_id == 4) {
                        answer = number1;
                        String right_form1 = " яблоко. ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " яблока. ";
                        if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " яблок. ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " яблок. ";

                        String right_form2 = " грушу";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " груши";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " груш";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " груш";

                        String answer_form = " яблоко";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " яблока";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " яблок";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " яблок";
                        string_answer = "У Лены до сих пор "  + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("У Лены было " + String.valueOf(number1) + right_form1 +
                                "Лена решила поделиться своими фруктами и отдала " + String.valueOf(number2) + right_form2 + " соседу \n"
                                + "Сколько яблок осталось у Лены?");
                    }
                    else if (rnd_text_task_id == 5) {
                        answer = number1;
                        String string_form_was = "было ";
                        String right_form1 = " персик. ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " персика. ";
                        else if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " персиков. ";
                        else string_form_was = "был ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " персиков. ";

                        String right_form2 = " монету. \n";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " монеты. \n";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " монет. \n";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " монет. \n";

                        String answer_form = " персик";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " персика";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " персиков";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " персиков";
                        string_answer = "У Васи до сих пор " + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("У Васи " + string_form_was + String.valueOf(number1) + right_form1 +
                                "Вася отдал за них продавцу " + String.valueOf(number2) + right_form2
                                + "Сколько персиков у Васи теперь?");
                    }
                    else if (rnd_text_task_id == 6) {
                        String right_form1 = " монету";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " монеты";
                        if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " монет";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " монет";

                        String right_form2 = " монету. \n";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " монеты. \n";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " монет. \n";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " монет. \n";

                        String answer_form = " монета";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " монеты";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " монет";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " монет";
                        string_answer = "У Вики " + String.valueOf(number1)
                                + " - " + String.valueOf(number2) + " = " + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("Вика накопила " + String.valueOf(number1) + right_form1 +
                                ", a потом купилa себе новую куклу за " + String.valueOf(number2) + right_form2
                                + "Сколько у Вики осталось монет?");
                    }
                    else if (rnd_text_task_id == 7) {
                        answer = number1;
                        String string_form_was = "было ";
                        String right_form1 = " конфета. ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " конфеты. ";
                        else if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " конфет. ";
                        else string_form_was = "былa ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " конфет.";

                        String right_form2 = " конфету.";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " конфеты.";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " конфет.";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " конфет.";

                        String answer_form = " конфета";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " конфеты";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " конфет";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " конфет.";

                        string_answer = "Ничего не изменилось. У Васи " + String.valueOf(number1) + " " + answer_form + "!";

                        exercise_text.setText("У Васи " + string_form_was + String.valueOf(number1) + right_form1 +
                                "Вася решил поделиться ими с другом и отдать ему \n" + String.valueOf(number2) + right_form2 + " Но он передумал.\n"
                                + "Сколько конфет у Васи?");
                    }
                    else {
                        answer = 0;
                        String right_form1 = " монету.";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " монеты.";
                        else if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " монет.";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " монет.";

                        string_answer = "Вася отдал все деньги и у него теперь 0 монет.";

                        exercise_text.setText("Вася накопил " + String.valueOf(number1) + right_form1 +
                                "Затем, Вася потратил все деньги на новую игру." +"\n"
                                + "Сколько монет у Васи?");
                    }
                }
            }
            else if(name_of_exercise.contains("4")){
                int rnd_task_id = 1;
                if (rnd_task_id == 1) {
                    int rnd_num_task_id = getRandomNumber(1,3);
                    if (rnd_num_task_id == 1) {
                        int number1 = getRandomNumber(0, 4100);
                        int number2;
                        if (number1 == 0) number2 = getRandomNumber(0, 9999);
                        else number2 = getRandomNumber(0, 9999 / number1);

                        answer = number2;
                        string_answer = String.valueOf(number1*number2)
                                + " : " + String.valueOf(number1)
                                + " = " + String.valueOf(answer) + "!";

                        exercise_text.setText(
                                "Сколько будет\n " + String.valueOf(number1*number2)
                                        + " : " + String.valueOf(number1)
                                        + "?"
                        );
                    }
                    else {
                        int number1 = getRandomNumber(0, 9999);
                        int number2 = getRandomNumber(0, number1);
                        int number3 = getRandomNumber(0, number1 - number2);
                        answer = number1 - number2 - number3;
                        string_answer = String.valueOf(number1)
                                + " - " + String.valueOf(number2)
                                + " - " + String.valueOf(number3)
                                + " = " + String.valueOf(answer) + "!";
                        exercise_text.setText(
                                "Сколько будет\n " + String.valueOf(number1)
                                        + " - " + String.valueOf(number2)
                                        + " - " + String.valueOf(number3)
                                        + "?"
                        );
                    }
                }
                else{
                    int number1 = getRandomNumber(1, 9999);
                    int number2 = getRandomNumber(1, number1);
                    answer = number1 - number2;
                    int rnd_text_task_id = getRandomNumber(1,9);
                    if (rnd_text_task_id == 1) {
                        String right_form1 = " шарик. ";
                        String string_form_was = "было ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " шарика. ";
                        else if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " шариков. ";
                        else string_form_was = "был ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " шариков. ";

                        String right_form2 = " шарик. \n";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " шарика. \n";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " шариков. \n";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " шариков. \n";

                        String answer_form = " шарик";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " шарика";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " шариков";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " шариков";
                        string_answer = "У Пети " + String.valueOf(number1)
                                + " - " + String.valueOf(number2) + " " + " = " + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("Петя купил " + String.valueOf(number1) + right_form1 +
                                "Петя подарил своему другу на день рождения " + String.valueOf(number2) + right_form2
                                + "Сколько шариков у Пети осталось?");
                    }
                    else if (rnd_text_task_id == 2) {
                        String string_form_was = "было ";
                        String right_form1 = " персик. ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " персика. ";
                        else if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " персиков. ";
                        else string_form_was = "был ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " персиков. ";

                        String right_form2 = " персик. ";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " персика. ";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " персиков. ";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " персиков. ";

                        String answer_form = " персик";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " персика";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " персиков";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " персиков";
                        string_answer = "У Васи " + String.valueOf(number1)
                                + " - " + String.valueOf(number2) + " " + " = " + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("У Васи " + string_form_was + String.valueOf(number1) + right_form1 +
                                "Вася решил отдать " + String.valueOf(number2) + right_form2 + " своему другу.\n"
                                + "Сколько персиков у Васи теперь?");
                    }
                    else if (rnd_text_task_id == 3) {
                        String right_form1 = " монету. ";
                        String string_form_was = "была ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " монеты. ";
                        if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " монет. ";
                        else string_form_was = "было ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " монет. ";

                        String right_form2 = " монету ";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " монеты ";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " монет ";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " монет ";

                        String answer_form = " монета";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " монеты";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " монет";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " монет";
                        string_answer = "У Нины " + String.valueOf(number1)
                                + " - " + String.valueOf(number2) + " " + " = " + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("У родителей " + string_form_was + String.valueOf(number1) + right_form1 +
                                "Родители решили оставить " + String.valueOf(number2) + right_form2 + " себе, а оставшиеся монеты отдать Нине\n"
                                + "Сколько монет достанется Нине?");
                    }
                    else if (rnd_text_task_id == 4) {
                        answer = number1;
                        String right_form1 = " яблоко. ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " яблока. ";
                        if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " яблок. ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " яблок. ";

                        String right_form2 = " грушу";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " груши";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " груш";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " груш";

                        String answer_form = " яблоко";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " яблока";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " яблок";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " яблок";
                        string_answer = "У Лены до сих пор "  + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("У Лены было " + String.valueOf(number1) + right_form1 +
                                "Лена решила поделиться своими фруктами и отдала " + String.valueOf(number2) + right_form2 + " соседу \n"
                                + "Сколько яблок осталось у Лены?");
                    }
                    else if (rnd_text_task_id == 5) {
                        answer = number1;
                        String string_form_was = "было ";
                        String right_form1 = " персик. ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " персика. ";
                        else if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " персиков. ";
                        else string_form_was = "был ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " персиков. ";

                        String right_form2 = " монету. \n";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " монеты. \n";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " монет. \n";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " монет. \n";

                        String answer_form = " персик";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " персика";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " персиков";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " персиков";
                        string_answer = "У Васи до сих пор " + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("У Васи " + string_form_was + String.valueOf(number1) + right_form1 +
                                "Вася отдал за них продавцу " + String.valueOf(number2) + right_form2
                                + "Сколько персиков у Васи теперь?");
                    }
                    else if (rnd_text_task_id == 6) {
                        String right_form1 = " монету";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " монеты";
                        if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " монет";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " монет";

                        String right_form2 = " монету. \n";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " монеты. \n";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " монет. \n";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " монет. \n";

                        String answer_form = " монета";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " монеты";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " монет";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " монет";
                        string_answer = "У Вики " + String.valueOf(number1)
                                + " - " + String.valueOf(number2) + " = " + String.valueOf(answer) + answer_form + "!";

                        exercise_text.setText("Вика накопила " + String.valueOf(number1) + right_form1 +
                                ", a потом купилa себе новую куклу за " + String.valueOf(number2) + right_form2
                                + "Сколько у Вики осталось монет?");
                    }
                    else if (rnd_text_task_id == 7) {
                        answer = number1;
                        String string_form_was = "было ";
                        String right_form1 = " конфета. ";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " конфеты. ";
                        else if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " конфет. ";
                        else string_form_was = "былa ";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " конфет.";

                        String right_form2 = " конфету.";
                        if (number2 % 10 > 1 && number2 % 10 < 5) right_form2 = " конфеты.";
                        if (number2 % 10 >= 5 || number2 % 10 == 0) right_form2 = " конфет.";
                        if (number2 % 100 >= 10 && number2 % 100 <= 19) right_form2 = " конфет.";

                        String answer_form = " конфета";
                        if (answer % 10 > 1 && answer % 10 < 5) answer_form = " конфеты";
                        if (answer % 10 >= 5 || answer % 10 == 0) answer_form = " конфет";
                        if (answer % 100 >= 10 && answer % 100 <= 19) answer_form = " конфет.";

                        string_answer = "Ничего не изменилось. У Васи " + String.valueOf(number1) + " " + answer_form + "!";

                        exercise_text.setText("У Васи " + string_form_was + String.valueOf(number1) + right_form1 +
                                "Вася решил поделиться ими с другом и отдать ему \n" + String.valueOf(number2) + right_form2 + " Но он передумал.\n"
                                + "Сколько конфет у Васи?");
                    }
                    else {
                        answer = 0;
                        String right_form1 = " монету.";
                        if (number1 % 10 > 1 && number1 % 10 < 5) right_form1 = " монеты.";
                        else if (number1 % 10 >= 5 || number1 % 10 == 0) right_form1 = " монет.";
                        if (number1 % 100 >= 10 && number1 % 100 <= 19) right_form1 = " монет.";

                        string_answer = "Вася отдал все деньги и у него теперь 0 монет.";

                        exercise_text.setText("Вася накопил " + String.valueOf(number1) + right_form1 +
                                "Затем, Вася потратил все деньги на новую игру." +"\n"
                                + "Сколько монет у Васи?");
                    }
                }
            }
        }
        else if (name_of_exercise.contains("Единицы длины")){
            {
                {
                    int rnd_num_task_id = getRandomNumber(1,4);
                    if (rnd_num_task_id == 1) {
                        int number1 = getRandomNumber(0, 8);
                        int number2 = getRandomNumber(0, 9 - number1);
                        answer = number1 + number2*10;
                        string_answer = String.valueOf(number1) + " мм"
                                + " + " + String.valueOf(number2*10) + " мм"
                                + " = " + String.valueOf(answer) + " мм!";
                        exercise_text.setText(
                                "Сколько мм будет\n " + String.valueOf(number1) + " мм "
                                        + " + " + String.valueOf(number2) + " см"
                                        + "?"
                        );
                    }
                    else if (rnd_num_task_id == 2) {
                        int number1 = getRandomNumber(0, 7);
                        int number2 = getRandomNumber(0, 8 - number1);
                        int number3 = getRandomNumber(0, 9 - number1 - number2);
                        answer = number1 + number2*100 + number3*10;
                        string_answer = String.valueOf(number1) + " мм "
                                + " + " + String.valueOf(number2*100) + " мм "
                                + " + " + String.valueOf(number3*10) + " мм "
                                + " = " + String.valueOf(answer) + " мм" + "!";
                        exercise_text.setText(
                                "Сколько мм будет\n " + String.valueOf(number1) + " мм "
                                        + " + " + String.valueOf(number2)+ " м "
                                        + " + " + String.valueOf(number3)+ " cм "
                                        + "?"
                        );
                    }
                    else {
                        int number1 = getRandomNumber(0, 6);
                        int number2 = getRandomNumber(0, 7 - number1);
                        int number3 = getRandomNumber(0, 8 - number1 - number2);
                        int number4 = getRandomNumber(0, 9 - number1 - number2 - number3);
                        answer = number1 + number2*1000 + number3*10 + number4*100;
                        string_answer = String.valueOf(number1) + " мм "
                                + " + " + String.valueOf(number2*1000)+ " мм "
                                + " + " + String.valueOf(number3*10)+ " мм "
                                + " + " + String.valueOf(number4*100)+ " мм "
                                + " = " + String.valueOf(answer) + " мм" + "!";
                        exercise_text.setText(
                                "Сколько мм будет\n " + String.valueOf(number1) + " мм "
                                        + " + " + String.valueOf(number2)+ " м "
                                        + " + " + String.valueOf(number3)+ " см "
                                        + " + " + String.valueOf(number4)+ " дм "
                                        + "?"
                        );
                    }
                }
            }
        }
        else if (name_of_exercise.contains("Единицы времени")){
            {
                {
                    int rnd_num_task_id = getRandomNumber(1,4);
                    if (rnd_num_task_id == 1) {
                        int number1 = getRandomNumber(0, 8);
                        int number2 = getRandomNumber(0, 9 - number1);
                        answer = number1 + number2*60;
                        string_answer = String.valueOf(number1) + " минут"
                                + " + " + String.valueOf(number2*60) + " минут"
                                + " = " + String.valueOf(answer) + " минут!";
                        exercise_text.setText(
                                "Сколько минут будет\n " + String.valueOf(number1) + " минут "
                                        + " + " + String.valueOf(number2) + " час"
                                        + "?"
                        );
                    }
                    else if (rnd_num_task_id == 2) {
                        int number1 = getRandomNumber(0, 7);
                        int number2 = getRandomNumber(0, 8 - number1);
                        int number3 = getRandomNumber(0, 9 - number1 - number2);
                        answer = number1 + number2*60 + number3*60;
                        string_answer = String.valueOf(number1) + " минут "
                                + " + " + String.valueOf(number2*60) + " минут "
                                + " + " + String.valueOf(number3*60) + " минут "
                                + " = " + String.valueOf(answer) + " минут" + "!";
                        exercise_text.setText(
                                "Сколько минут будет\n " + String.valueOf(number1) + " минут "
                                        + " + " + String.valueOf(number2)+ " часа "
                                        + " + " + String.valueOf(number3)+ " часа "
                                        + "?"
                        );
                    }
                    else {
                        int number1 = getRandomNumber(0, 6);
                        int number2 = getRandomNumber(0, 7 - number1);
                        int number3 = getRandomNumber(0, 8 - number1 - number2);
                        int number4 = getRandomNumber(0, 9 - number1 - number2 - number3);
                        answer = number1 + number2*60 + number3 + number4*60;
                        string_answer = String.valueOf(number1) + " секунд "
                                + " + " + String.valueOf(number2*60)+ " секунд "
                                + " + " + String.valueOf(number3)+ " секунд "
                                + " + " + String.valueOf(number4*60)+ " секунд "
                                + " = " + String.valueOf(answer) + " секунд" + "!";
                        exercise_text.setText(
                                "Сколько секунд будет\n " + String.valueOf(number1) + " секунд "
                                        + " + " + String.valueOf(number2)+ " минут "
                                        + " + " + String.valueOf(number3)+ " секунд "
                                        + " + " + String.valueOf(number4)+ " минут "
                                        + "?"
                        );
                    }
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);

        name_of_exercise_textview = findViewById(R.id.exercise_name);
        back_button = findViewById(R.id.back_to_list_button);
        exercise_text = findViewById(R.id.exercise_text);
        result_text_view = findViewById(R.id.result_text_view);
        button_answer = findViewById(R.id.button_answer);
        answer_edittext = findViewById(R.id.answer_edittext);
        next_exercise_button = findViewById(R.id.next_to_list);
        next_task_button = findViewById(R.id.next_task);
        back_to_ex = findViewById(R.id.back_to_ex);
        last_answer_text_view = findViewById(R.id.last_answer_text_view);
        right_answer_text_view = findViewById(R.id.right_answer_text_view);

        //keyboard buttons init
        {
            button_1 = findViewById(R.id.button_1);
            button_2 = findViewById(R.id.button_2);
            button_3 = findViewById(R.id.button_3);
            button_4 = findViewById(R.id.button_4);
            button_5 = findViewById(R.id.button_5);
            button_6 = findViewById(R.id.button_6);
            button_7 = findViewById(R.id.button_7);
            button_8 = findViewById(R.id.button_8);
            button_9 = findViewById(R.id.button_9);
            button_0 = findViewById(R.id.button_0);
            button_delete = findViewById(R.id.delete_button);
        }
        setKeyBoardButtons();

//        right_answer_text_view.setVisibility(View.INVISIBLE);
        next_task_button.setVisibility(View.INVISIBLE);
        answer_edittext.setEnabled(false);

        name_of_exercise_textview.setText(name_of_exercise);
        setExerciseText();
        result_text_view.setText("");
        result_text_view.setVisibility(View.GONE);

        String next_ex = "";
        if (id_of_exercise == ListExcercisesActivity.getLast_ex_id()){
            next_exercise_button.setVisibility(View.INVISIBLE);
        } else{
            next_ex = ListExcercisesActivity.getExerciseByID(id_of_exercise + 1);
            next_exercise_button.setText(next_ex + " }");
            next_exercise_button.setVisibility(View.VISIBLE);
        }
        final String next_ex_final = next_ex;


        next_exercise_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (next_ex_final.contains("Урок")){
                    LessonActivity.setName_of_lesson(next_ex_final);
                    Intent intent = new Intent(getApplicationContext(), LessonActivity.class);
                    startActivity(intent);
                    finish();
                } else if (next_ex_final.contains("Упражнение")){
                    ExercisesActivity.setName_of_exercise(next_ex_final);
                    ExercisesActivity.setId_of_exercise(id_of_exercise + 1);
                    Intent intent = new Intent(getApplicationContext(), ExercisesActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });


        String back_ex = "";
        if (id_of_exercise == 0){
            back_to_ex.setVisibility(View.INVISIBLE);
        } else{
            back_ex = ListExcercisesActivity.getExerciseByID(id_of_exercise - 1);
            back_to_ex.setText("{ " + back_ex);
            back_to_ex.setVisibility(View.VISIBLE);
        }
        final String back_ex_final = back_ex;


        back_to_ex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (back_ex_final.contains("Урок")){
                    LessonActivity.setName_of_lesson(back_ex_final);
                    Intent intent = new Intent(getApplicationContext(), LessonActivity.class);
                    startActivity(intent);
                    finish();
                } else if (back_ex_final.contains("Упражнение")){
                    ExercisesActivity.setName_of_exercise(back_ex_final);
                    ExercisesActivity.setId_of_exercise(id_of_exercise - 1);
                    Intent intent = new Intent(getApplicationContext(), ExercisesActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListExcercisesActivity.class);
                startActivity(intent);
                finish();
            }
        });

        FirebaseUser user = ListExcercisesActivity.getFirebaseUser();


        button_answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result_text_view.setVisibility(View.VISIBLE);
                String user_answer = answer_edittext.getText().toString();
                if(user_answer.equals(String.valueOf(answer))) {
                    next_task_button.setVisibility(View.VISIBLE);

                    result_text_view.setText("Верно!\n" + string_answer);
                    result_text_view.setTextColor(Color.rgb(0,255,0));

                    long points = ListExcercisesActivity.getPoints() + 1;
                    ListExcercisesActivity.setPoints(points);
                    if (user != null){
                        DatabaseReference points_reference = ListExcercisesActivity.getPoints_reference();
                        points_reference.setValue(points);
                    }
                    right_answer_text_view.setText("Очков заработано:" + String.valueOf(++points_in_session) + "" +
                            "\nОшибок совершено: " + String.valueOf(mistakes_in_session));

//                    right_answer_text_view.setVisibility(View.VISIBLE);

//                    exercise_text.setText(exercise_text.getText().toString()
//                            .replace("?", " = " + answer + "!")
//                            .replace("Сколько будет ", ""));


                    button_answer.setVisibility(View.GONE);
//                    answer_edittext.setEnabled(false);
                    View view2 = getCurrentFocus();
                    if (view2 != null) {
                        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view2.getWindowToken(), 0);
                    }
                    answer_edittext.setHint("Твой ответ: " + user_answer);
                } else{
                    result_text_view.setText("Не верно.\n Не волнуйся, у тебя обязательно получится!!!");
                    result_text_view.setTextColor(Color.rgb(255,20,100));
                    right_answer_text_view.setText("Очков заработано:" + String.valueOf(points_in_session) + "" +
                            "\nОшибок совершено: " + String.valueOf(++mistakes_in_session));
                }
                last_answer_text_view.setText("Последний ответ: " + user_answer);
                answer_edittext.setText("");
            }
        });

        answer_edittext.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    String user_answer = answer_edittext.getText().toString();
                    if(user_answer.equals(String.valueOf(answer))) {
                        next_task_button.setVisibility(View.VISIBLE);
                        result_text_view.setText("Верно!");
                        result_text_view.setTextColor(Color.rgb(0,255,0));
                        long points = ListExcercisesActivity.getPoints() + 1;
                        ListExcercisesActivity.setPoints(points);
                        if (user != null){
                            DatabaseReference points_reference = ListExcercisesActivity.getPoints_reference();
                            points_reference.setValue(points);
                        }
                        right_answer_text_view.setText("Правильный ответ: " + String.valueOf(answer));
                        right_answer_text_view.setVisibility(View.VISIBLE);
                        button_answer.setVisibility(View.GONE);
//                        answer_edittext.setEnabled(false);
                        View view2 = getCurrentFocus();
                        if (view2 != null) {
                            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(view2.getWindowToken(), 0);
                        }
                    } else{
                        result_text_view.setText("Не верно.\n Не волнуйся, у тебя обязательно получится!!!");
                        result_text_view.setTextColor(Color.rgb(0,255,255));
                    }
                    last_answer_text_view.setText("Последний ответ: " + user_answer);
                    answer_edittext.setText("");
                    return true;
                }
                return false;
            }
        });

        next_task_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                next_task_button.setVisibility(View.INVISIBLE);
                result_text_view.setText("");
                result_text_view.setVisibility(View.GONE);
                setExerciseText();
//                right_answer_text_view.setVisibility(View.INVISIBLE);
                answer_edittext.setText("");
                last_answer_text_view.setText("Последний ответ: ");
                answer_edittext.setHint("Твой ответ:");
                button_answer.setVisibility(View.VISIBLE);
//                answer_edittext.setEnabled(true);

            }
        });
    }

    private void setKeyBoardButtons() {
        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer_edittext.setText(answer_edittext.getText() + "1");
            }
        });
        button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer_edittext.setText(answer_edittext.getText() + "2");
            }
        });
        button_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer_edittext.setText(answer_edittext.getText() + "3");
            }
        });
        button_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer_edittext.setText(answer_edittext.getText() + "4");
            }
        });
        button_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer_edittext.setText(answer_edittext.getText() + "5");
            }
        });
        button_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer_edittext.setText(answer_edittext.getText() + "6");
            }
        });
        button_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer_edittext.setText(answer_edittext.getText() + "7");
            }
        });
        button_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer_edittext.setText(answer_edittext.getText() + "8");
            }
        });
        button_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer_edittext.setText(answer_edittext.getText() + "9");
            }
        });
        button_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer_edittext.setText(answer_edittext.getText() + "0");
            }
        });
        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (answer_edittext.getText() == null || answer_edittext.getText().toString().equals("")) return;
                String new_text = answer_edittext.getText().toString().substring(0, answer_edittext.getText().toString().length() - 1);
                answer_edittext.setText(new_text);
            }
        });
    }
}