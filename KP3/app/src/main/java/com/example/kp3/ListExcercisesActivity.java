package com.example.kp3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.collection.ArrayMap;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.errorprone.annotations.Var;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ListExcercisesActivity extends AppCompatActivity {

    TextView name_textview, mail_textview, points_textview;
    Button logout_button;
    FirebaseDatabase db_user_data;
    DatabaseReference reference_users_db;
    static private DatabaseReference points_reference;
    static long points;
    String user_email;
    private static FirebaseUser user;
    ListView list_view_exercises;
    static String[] array_of_exercises = new String[]{
//        "Числа 1. Урок",
            "Числа 1. Упражнение",
//                "Сложение 1. Урок",
            "Сложение 1. Упражнение",
//                "Вычитание 1. Урок",
            "Вычитание 1. Упражнение",
//                "Числа 2. Урок",
            "Числа 2. Упражнение",
//                "Сложение 2. Урок",
            "Сложение 2. Упражнение",
//                "Вычитание 2. Урок",
            "Вычитание 2. Упражнение",
//                "Умножение 1. Урок",Единицы времени
            "Умножение 1. Упражнение", "Умножение 2. Упражнение",
//                "Деление 1. Урок",
            "Деление 1. Упражнение", "Деление 2. Упражнение",
            "Единицы длины. Упражнение",
            "Единицы времени. Упражнение",
                "Числа 3. Упражнение",
                "Сложение 3. Упражнение",
                "Сложение 4. Упражнение",
                "Вычитание 3. Упражнение", "Вычитание 3. Упражнение",
                "Умножение 3. Упражнение", "Деление 3. Упражнение",
                "Умножение 4. Упражнение", "Деление 4. Упражнение"
    };
    static int last_ex_id = array_of_exercises.length - 1;

    public static FirebaseUser getFirebaseUser(){
        return user;
    }
    public static DatabaseReference getPoints_reference(){
        return points_reference;
    }
    public static int getLast_ex_id() {return last_ex_id;}
    public static String getExerciseByID(int i){
        return array_of_exercises[i];
    }

    public static void setPoints(long x) {
        points = x;
    }
    public static long getPoints() {
        return points;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_excercises);

        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        logout_button = findViewById(R.id.logout_button);
//        name_textview = findViewById(R.id.name_text_view);
        mail_textview = findViewById(R.id.mail_text_view);
        points_textview = findViewById(R.id.points_text_view);
        list_view_exercises = findViewById(R.id.list_view_exercises);
        EditText search_excercises = findViewById(R.id.search_excercises);

        search_excercises.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });

        user = FirebaseAuth.getInstance().getCurrentUser();
//        reference_users_db.child("points").setValue(reference_users_db.child("points"));

        //use auth account (if user didn't enter as guest)
        if (user != null){
            user_email = user.getEmail();
            mail_textview.setText(user_email);

            db_user_data = FirebaseDatabase.getInstance("https://course-project-3-30a4b-default-rtdb.europe-west1.firebasedatabase.app/");
            reference_users_db = db_user_data.getReference("users");

            points_reference = reference_users_db.child(user_email.replace("@","____")
                    .replace(".","____")
                    .replace("#","____")
                    .replace("$","____")
                    .replace("[","____")
                    .replace("]","____")).child("points");
//        points_reference.setValue(15);
            points_reference.get()
                    .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                        @SuppressLint("SetTextI18n")
                        @Override
                        public void onComplete(@NonNull Task<DataSnapshot> task) {
                            if (!task.isSuccessful()) {
                                Log.e("firebase", "Error getting data", task.getException());
                            }
                            else {
                                setPoints((long)task.getResult().getValue());
                                points_textview.setText("Очки: " + String.valueOf(getPoints()));
                                Log.d("firebase", String.valueOf(task.getResult().getValue()));
                            }
                        }
                    });
        } else {
            points_textview.setText("Очки: " + String.valueOf(getPoints()));
        }


//        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
//        if (signInAccount != null){
////            name_textview.setText(signInAccount.getDisplayName());
//            mail_textview.setText(signInAccount.getEmail());
//        } else

        logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (user != null) {
                    FirebaseAuth.getInstance().signOut();
                }
                Intent intent= new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        setListView(Arrays.asList(array_of_exercises));
    }

    private void setListView(List<String> list_of_string){
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list_of_string){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                // Get the Item from ListView
                View view = super.getView(position, convertView, parent);

                // Initialize a TextView for ListView each Item
                TextView tv = (TextView) view.findViewById(android.R.id.text1);

                // Set the text color of TextView (ListView Item)
                tv.setTextColor(Color.rgb(255,215,0));
                tv.setBackgroundColor(Color.TRANSPARENT);

                // Generate ListView Item using TextView
                return view;
            }
        };
        list_view_exercises.setAdapter(arrayAdapter);
        list_view_exercises.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (list_of_string.get(i).contains("Урок")){
                    LessonActivity.setName_of_lesson(list_of_string.get(i));
                    Intent intent = new Intent(getApplicationContext(), LessonActivity.class);
                    startActivity(intent);
                    finish();
                } else if (list_of_string.get(i).contains("Упражнение")){
                    ExercisesActivity.setName_of_exercise(list_of_string.get(i));
                    ExercisesActivity.setId_of_exercise(i);
                    Intent intent = new Intent(getApplicationContext(), ExercisesActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void filter(String text) {
        ArrayList<String> arrayList = new ArrayList<>();

        for (String item : array_of_exercises){
            if(item.toLowerCase().contains(text.toLowerCase())){
                arrayList.add(item);
            }

            setListView(arrayList);
        }
    }
}