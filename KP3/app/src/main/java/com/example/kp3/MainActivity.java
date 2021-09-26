package com.example.kp3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.kp3.Models.User;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.api.Distribution;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

public class MainActivity extends AppCompatActivity {

    Button button_signin, button_register, button_guest_enter;
    SignInButton button_google_signin;
    FirebaseAuth auth;
    FirebaseUser user_find;
    FirebaseDatabase db_users_data;
    DatabaseReference users_data;

    RelativeLayout root_register_layout;
    RelativeLayout root_signin_layout;
    RelativeLayout registe_verify_layout;
    LinearLayout linear_wait;
    private GoogleSignInClient mGoogleSignInClient;
    private final static int RC_SIGN_IN = 123;

    public static boolean wait_verify = true;

    private boolean isGoodEmailFormat(String email_user){
        Task<SignInMethodQueryResult> methods = null;
        try {
            methods = auth.fetchSignInMethodsForEmail(email_user).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                @Override
                public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
//                if (!MainActivity.check_registered)
//                    Toast.makeText(MainActivity.this, "Пусто", Toast.LENGTH_LONG).show();
//                else
//                    Toast.makeText(MainActivity.this, "не пусто", Toast.LENGTH_LONG).show();
                }
            });

            while (!methods.isComplete()) { }
            boolean x = (methods.getResult().getSignInMethods().size() == 0);
        } catch (Exception ex){
            return false;
        }

        return true;
    }

    private boolean checkIfRegistered(final String email_user) {
        Task<SignInMethodQueryResult> methods = null;
        methods = auth.fetchSignInMethodsForEmail(email_user).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
            @Override
            public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
//                if (!MainActivity.check_registered)
//                    Toast.makeText(MainActivity.this, "Пусто", Toast.LENGTH_LONG).show();
//                else
//                    Toast.makeText(MainActivity.this, "не пусто", Toast.LENGTH_LONG).show();
            }
        });

        while (!methods.isComplete()) { }
        return !(methods.getResult().getSignInMethods().size() == 0);
    }

    private void showRegisterActivity() {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setCancelable(true)
                .setNegativeButton("Отменить", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .setPositiveButton("Зарегистрироваться", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                })
                .create();
        dialog.setTitle("Зарегистрироваться");
        dialog.setMessage("Введите данные для регистрации");

        LayoutInflater inflater = LayoutInflater.from(this);
        View register_view = inflater.inflate(R.layout.register_window, null);
        dialog.setView(register_view);

        final MaterialEditText email = register_view.findViewById(R.id.email_field_register);
        final MaterialEditText name = register_view.findViewById(R.id.name_field_register);
        final MaterialEditText pass = register_view.findViewById(R.id.pass_field_register);
        final MaterialEditText second_pass = register_view.findViewById(R.id.pass_apply_field_register);
//        final MaterialEditText login = register_view.findViewById(R.id.login_field_register);

        dialog.show();
        (dialog).getButton(dialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, email.getText().toString(), Toast.LENGTH_SHORT).show();
                boolean val_flg = false;
                //Проверка на пустое поле
                if(TextUtils.isEmpty(email.getText().toString())){
                    email.setError("Почта обзязательна для регистрации");
                    email.setErrorColor(Color.rgb(255,0,0));
                    val_flg = true;
                }
                if(TextUtils.isEmpty(name.getText().toString())){
//                    Toast.makeText(MainActivity.this, "Вы не ввели своё имя", Toast.LENGTH_SHORT).show();
                    name.setError("Имя обзязательно для регистрации");
                    name.setErrorColor(Color.rgb(255,0,0));
                    val_flg = true;
                }
//                else if(TextUtils.isEmpty(login.getText().toString())){
//                    Toast.makeText(MainActivity.this, "Вы не ввели Логин", Toast.LENGTH_SHORT).show();
//                    return;
//                }
                if(TextUtils.isEmpty(second_pass.getText().toString())){
//                    Toast.makeText(MainActivity.this, "Вы не ввели первый пароль", Toast.LENGTH_SHORT).show();
                    second_pass.setError("Повторный пароль обязателен для регистрации");
                    second_pass.setErrorColor(Color.rgb(255,0,0));
                    val_flg = true;
                }
                if(!isGoodEmailFormat(email.getText().toString())){
                    email.setError("Неверный формат адреса");
                    email.setErrorColor(Color.rgb(255,0,0));
                    return;
                }
                if(checkIfRegistered(email.getText().toString())){
                    email.setError("Данный почтовый адрес уже зарегестрирован");
                    email.setErrorColor(Color.rgb(255,0,0));
                    val_flg = true;
                }

                if(pass.getText().toString().length() < 6){
//                    Toast.makeText(MainActivity.this, "Пароль меньше 6 символов", Toast.LENGTH_LONG).show();
                    pass.setError("Пароль должен быть больше 6 символов");
                    pass.setErrorColor(Color.rgb(255,0,0));
                    val_flg = true;
                }
                //password equality check
                if(!(second_pass.getText().toString()).equals(pass.getText().toString())){
//                    Toast.makeText(MainActivity.this, "Повторный пароль не совпадает с первым", Toast.LENGTH_SHORT).show();
                    second_pass.setError("Пароли не совпадают");
                    second_pass.setErrorColor(Color.rgb(255,0,0));
                    val_flg = true;
                }
                ///
                if (val_flg) return;
                final boolean[] flg_good = {false};

                //user.setLogin(login.getText().toString());

                Task<AuthResult> authResultTask = auth.createUserWithEmailAndPassword(email.getText().toString(), pass.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
//                                Toast.makeText(MainActivity.this, "Тут", Toast.LENGTH_LONG).show();

                                Task<AuthResult> authResultTask = auth.signInWithEmailAndPassword(email.getText().toString(), pass.getText().toString())
                                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                            @Override
                                            public void onSuccess(AuthResult authResult) {
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                if (e.getMessage().contains("password is invalid"))
                                                    Toast.makeText(MainActivity.this, "Пароль или почта неверно введены", Toast.LENGTH_LONG).show();
                                                else
                                                    Toast.makeText(MainActivity.this, "Ошибка авторизации:" + e.getMessage(), Toast.LENGTH_LONG).show();
                                            }
                                        });

                                //                verification
                                {
                                    AlertDialog dialog_verify = new AlertDialog.Builder(MainActivity.this)
                                            .setCancelable(false)
                                            .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    dialogInterface.dismiss();
                                                }
                                            })
                                            .setPositiveButton("Подтверждён", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                }
                                            })
                                            .create();
                                    dialog_verify.setTitle("Подтверждение почты");

                                    LayoutInflater inflater2 = LayoutInflater.from(MainActivity.this);
                                    View register_view = inflater2.inflate(R.layout.verificattion_mail_messege, null);
                                    dialog_verify.setView(register_view);

                                    linear_wait = register_view.findViewById(R.id.grey_waiting_layout);

                                    Task<Void> task_verifysent = auth.getCurrentUser().sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(MainActivity.this, "Письмо с подтверждением было отправлено на ваш почтовый адрес", Toast.LENGTH_LONG).show();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(MainActivity.this, "Письмо не может быть отправлено в связи с ошибкой: " + e.getMessage(), Toast.LENGTH_LONG).show();
                                        }
                                    });
//                                    while (!task_verifysent.isComplete()){}

                                    dialog_verify.show();
                                    (dialog_verify).getButton(dialog_verify.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Task<Void> task_reload = auth.getCurrentUser().reload();
                                            while(!task_reload.isComplete()) {}
                                            if (!auth.getCurrentUser().isEmailVerified()) {
                                                wait_verify = false;
                                                Toast.makeText(MainActivity.this, "Вы ещё не подтвердили свой почтовый адрес.\nПроверьте почту ", Toast.LENGTH_LONG).show();
                                            } else {
//                                                linear_wait.setVisibility(View.VISIBLE);
                                                getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                                                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                                wait_verify = false;
                                                Toast.makeText(MainActivity.this, "Верификация прошла успешно", Toast.LENGTH_LONG).show();
                                                dialog_verify.dismiss();
                                                if (auth.getCurrentUser() != null) {
                                                    User user = new User();
                                                    user.setEmail(email.getText().toString());
                                                    user.setName(name.getText().toString());
                                                    user.setPassword(pass.getText().toString());
                                                    user.setPoints(0);
//                                                  not '.', '#', '$', '[', or ']'
                                                    users_data.child(user.email.replace("@", "____")
                                                            .replace(".", "____")
                                                            .replace("#", "____")
                                                            .replace("$", "____")
                                                            .replace("[", "____")
                                                            .replace("]", "____")).setValue(user)
                                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                @Override
                                                                public void onSuccess(Void unused) {
                                                                    Toast.makeText(MainActivity.this, "Успешная регистрация!", Toast.LENGTH_LONG).show();
//                                        Snackbar.make(findViewById(R.id.main_name_layout), "Успешная регистрация!", Snackbar.LENGTH_SHORT).show();
                                                                    dialog.dismiss();
                                                                    //вход после успешной регистрации
                                                                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                                                    startActivity(new Intent(MainActivity.this, ListExcercisesActivity.class));
                                                                    finish();
                                                                }
                                                            }).addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                                            Toast.makeText(MainActivity.this, "Провал" + e.getMessage(), Toast.LENGTH_LONG).show();
//                                        Snackbar.make(findViewById(R.id.main_name_layout), e.getMessage(), Snackbar.LENGTH_SHORT).show();
                                                            flg_good[0] = true;

                                                        }
                                                    });
                                                }
//                                                linear_wait.setVisibility(View.GONE);
                                            }
                                        }
                                    });
                                    (dialog_verify).getButton(dialog_verify.BUTTON_NEGATIVE).setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Task<Void> task_reload = auth.getCurrentUser().reload();
                                            while(!task_reload.isComplete()) {}
                                            if (auth.getCurrentUser().isEmailVerified()) {
//                                                linear_wait.setVisibility(View.VISIBLE);
                                                getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                                                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                                wait_verify = false;
                                                dialog_verify.dismiss();
                                                Toast.makeText(MainActivity.this, "Верификация прошла успешно", Toast.LENGTH_LONG).show();
                                                if (auth.getCurrentUser() != null) {
                                                    User user = new User();
                                                    user.setEmail(email.getText().toString());
                                                    user.setName(name.getText().toString());
                                                    user.setPassword(pass.getText().toString());
                                                    user.setPoints(0);
//                                                  not '.', '#', '$', '[', or ']'
                                                    users_data.child(user.email.replace("@", "____")
                                                            .replace(".", "____")
                                                            .replace("#", "____")
                                                            .replace("$", "____")
                                                            .replace("[", "____")
                                                            .replace("]", "____")).setValue(user)
                                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                @Override
                                                                public void onSuccess(Void unused) {
                                                                    Toast.makeText(MainActivity.this, "Успешная регистрация!", Toast.LENGTH_LONG).show();
//                                        Snackbar.make(findViewById(R.id.main_name_layout), "Успешная регистрация!", Snackbar.LENGTH_SHORT).show();
                                                                    dialog.dismiss();
                                                                    //вход после успешной регистрации
                                                                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                                                    startActivity(new Intent(MainActivity.this, ListExcercisesActivity.class));
                                                                    finish();
                                                                }
                                                            }).addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                                            Toast.makeText(MainActivity.this, "Провал" + e.getMessage(), Toast.LENGTH_LONG).show();
//                                        Snackbar.make(findViewById(R.id.main_name_layout), e.getMessage(), Snackbar.LENGTH_SHORT).show();
                                                            flg_good[0] = true;

                                                        }
                                                    });
                                                }
//                                                linear_wait.setVisibility(View.GONE);
                                            } else {
                                                FirebaseUser user_fire = auth.getCurrentUser();
                                                FirebaseAuth.getInstance().signOut();
                                                Task<Void> task_delete = user_fire.delete();
                                                while (!task_delete.isComplete()){}
                                                dialog_verify.dismiss();
                                            }
                                        }
                                    });
                                }
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                if (e.getMessage().contains("is already in use by another account"))
                                    Toast.makeText(MainActivity.this, "Аккаунт уже зарегистрирован", Toast.LENGTH_LONG).show();
                                else if (e.getMessage().contains("badly formatted"))
                                    Toast.makeText(MainActivity.this, "Почтовый адрес имеет неверный формат", Toast.LENGTH_LONG).show();
                                else
                                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                                flg_good[0] = true;
                            }
                        });

                while(!authResultTask.isComplete()){}
                if(flg_good[0]) return;
//
//                dialog.dismiss();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = auth.getCurrentUser();
        if (user!=null){
            Intent intent = new Intent(getApplicationContext(), ListExcercisesActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //init maps for point and mistakes count
//        ListExcercisesActivity obj = new ListExcercisesActivity();
        ListExcercisesActivity.setPoints(0);
//        {
//            String ex = "";
//            for (int k = 0; k < ListExcercisesActivity.getArray_of_exercises().length; k++) {
//                ex = ListExcercisesActivity.getArray_of_exercises()[k];
//                if (ex.contains("Упражнение")) {
//                    ListExcercisesActivity.ex_map.put(ex, k);
//                }
//            }
//            for (int k = 0; k < ListExcercisesActivity.getArray_of_exercises().length; k++) {
//                ex = ListExcercisesActivity.getArray_of_exercises()[k];
//                if (ex.contains("Упражнение")) {
//                    ListExcercisesActivity.points_map.put(k, 0);
//                }
//            }
//            for (int k = 0; k < ListExcercisesActivity.getArray_of_exercises().length; k++) {
//                ex = ListExcercisesActivity.getArray_of_exercises()[k];
//                if (ex.contains("Упражнение")) {
//                    ListExcercisesActivity.mistakes_map.put(k, 0);
//                }
//            }
//        }

        button_signin = findViewById(R.id.button_signin);
        button_register = findViewById(R.id.button_register);
        button_guest_enter = findViewById(R.id.button_enter_guest);
        button_google_signin = (SignInButton) findViewById(R.id.sign_in_button);

        root_register_layout = findViewById(R.id.root_register_layout);
        root_signin_layout = findViewById(R.id.root_signin_layout);
        registe_verify_layout = findViewById(R.id.register_verify_mail_layout);

        auth = FirebaseAuth.getInstance();
        db_users_data = FirebaseDatabase.getInstance("https://course-project-3-30a4b-default-rtdb.europe-west1.firebasedatabase.app/");
        users_data = db_users_data.getReference("users");

        createGoogleSiginRequest();

        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRegisterActivity();
            }
        });

        button_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSignINActivity();
            }
        });

        button_guest_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListExcercisesActivity.class);
                startActivity(intent);
                finish();
//                Snackbar.make(findViewById(R.id.main_name_layout), "Подсчёт очков не ведётся в гостевом режиме", Snackbar.LENGTH_LONG).show();
            }
        });

        button_google_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
    }

    private void createGoogleSiginRequest() {
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
        finish();
//        someActivityResultLauncher.launch(intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Toast.makeText(MainActivity.this, "Ошибка входа", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(MainActivity.this);

                            Task<DataSnapshot> isin = users_data.child(account.getEmail().replace("@", "____")
                                    .replace(".", "____")
                                    .replace("#", "____")
                                    .replace("$", "____")
                                    .replace("[", "____")
                                    .replace("]", "____")).child("points").get();

                            while (!isin.isComplete()){}

                            if (isin.getResult().getValue() == null) {
//                                                  not '.', '#', '$', '[', or ']'
                                User user = new User();
                                user.setEmail(account.getEmail().toString());
                                user.setName(account.getDisplayName().toString());
                                user.setPassword(account.getIdToken().toString());
                                user.setPoints(0);

                                users_data.child(user.email.replace("@", "____")
                                        .replace(".", "____")
                                        .replace("#", "____")
                                        .replace("$", "____")
                                        .replace("[", "____")
                                        .replace("]", "____")).setValue(user)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                Toast.makeText(MainActivity.this, "Успешная регистрация!", Toast.LENGTH_LONG).show();
//                                        Snackbar.make(findViewById(R.id.main_name_layout), "Успешная регистрация!", Snackbar.LENGTH_SHORT).show();
                                                //вход после успешной регистрации
                                                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                                startActivity(new Intent(MainActivity.this, ListExcercisesActivity.class));
                                                finish();
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                        Toast.makeText(MainActivity.this, "Провал" + e.getMessage(), Toast.LENGTH_LONG).show();
//                                        Snackbar.make(findViewById(R.id.main_name_layout), e.getMessage(), Snackbar.LENGTH_SHORT).show();
                                    }
                                });
//                            User new_user = new User();
//                            new_user.setEmail(account.getEmail());
//                            new_user.setName(account.getDisplayName());
//                            new_user.setPassword(account.getIdToken());
//                            new_user.setPoints(0);
//
//                            DatabaseReference user_reference = users_data.child(new_user.email.replace("@", "____")
//                                    .replace(".", "____")
//                                    .replace("#", "____")
//                                    .replace("$", "____")
//                                    .replace("[", "____")
//                                    .replace("]", "____"));
//
//                            user_reference.get()
//                                    .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//                                        @SuppressLint("SetTextI18n")
//                                        @Override
//                                        public void onComplete(@NonNull Task<DataSnapshot> task) {
//                                            if (!task.isSuccessful()) {
//                                                users_data.child(new_user.email.replace("@","____")
//                                                        .replace(".","____")
//                                                        .replace("#","____")
//                                                        .replace("$","____")
//                                                        .replace("[","____")
//                                                        .replace("]","____")).setValue(new_user)
//                                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
//                                                            @Override
//                                                            public void onSuccess(Void unused) {
//                                                                Toast.makeText(MainActivity.this, "Успешная регистрация!", Toast.LENGTH_SHORT).show();
//                                                            }
//                                                        }).addOnFailureListener(new OnFailureListener() {
//                                                    @Override
//                                                    public void onFailure(@NonNull Exception e) {
//                                                    }
//                                                });
//                                            }
//                                        }
//                                    });
                            }

                            FirebaseUser fb_user = auth.getCurrentUser();
                            Intent intent = new Intent(getApplicationContext(), ListExcercisesActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(MainActivity.this,"Ошибка входа", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void showSignINActivity() {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setCancelable(true)
                .setNegativeButton("Отменить", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .setPositiveButton("Войти", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                })
                .create();
        dialog.setTitle("Войти в аккаунт");
        dialog.setMessage("Введите данные для входа");
        dialog.setCancelable(true);

        LayoutInflater inflater = LayoutInflater.from(this);
        View signin_view = inflater.inflate(R.layout.signin_window, null);
        dialog.setView(signin_view);

        final MaterialEditText email = signin_view.findViewById(R.id.email_field_signin);
        final MaterialEditText pass = signin_view.findViewById(R.id.pass_field_signin);
//        final MaterialEditText login = register_view.findViewById(R.id.login_field_register);

        dialog.show();

        (dialog).getButton(dialog.BUTTON_NEGATIVE).setGravity(8388611);
        (dialog).getButton(dialog.BUTTON_POSITIVE).setGravity(8388613);

//        final Button positiveButton = dialog.getButton(dialog.BUTTON_POSITIVE);
//        LinearLayout.LayoutParams positiveButtonLL = (LinearLayout.LayoutParams) positiveButton.getLayoutParams();
//        positiveButtonLL.gravity = Gravity.END;
//        positiveButton.setLayoutParams(positiveButtonLL);

        (dialog).getButton(dialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, email.getText().toString(), Toast.LENGTH_SHORT).show();
                //Проверка на пустое поле
                final boolean[] val_flg = {false};
                if(TextUtils.isEmpty(email.getText().toString())){
                    email.setError("Введите почту");
                    email.setErrorColor(Color.rgb(255,0,0));
                    val_flg[0] = true;
                }
                if(TextUtils.isEmpty(pass.getText().toString())){
                    pass.setError("Введите пароль");
                    pass.setErrorColor(Color.rgb(255,0,0));
                    val_flg[0] = true;
                }
                if (val_flg[0]) return;
                ///

                Task<AuthResult> authResultTask = auth.signInWithEmailAndPassword(email.getText().toString(), pass.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                startActivity(new Intent(MainActivity.this, ListExcercisesActivity.class));
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                val_flg[0] = true;
                                pass.setErrorColor(Color.rgb(255,0,0));
                                if (e.getMessage().contains("password is invalid"))
                                    pass.setError("Пароль или почта неверно введены");
//                                    Toast.makeText(MainActivity.this, "Пароль или почта неверно введены", Toast.LENGTH_LONG).show();
                                else
                                    Toast.makeText(MainActivity.this, "Ошибка авторизации:" + e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });

                while(!authResultTask.isComplete()){}
                if (authResultTask.getException() != null) return;
//                if(!authResultTask.isSuccessful()){
//                    return;
//                }

                Toast.makeText(MainActivity.this, "Успешный вход!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }
}