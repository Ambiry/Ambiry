package de.jukolai.ambiry;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    Button registerButton;
    TextView textviewExistingAccount;
    EditText editTextPassword, editTextEmailAdress;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private String password, email;
    ConstraintLayout constraintLayoutRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerButton = findViewById(R.id.registerButton);
        textviewExistingAccount = findViewById(R.id.textviewExistingAccount);
        editTextEmailAdress = findViewById(R.id.editTextEmailAdress);
        editTextPassword = findViewById(R.id.editTextPassword);
        constraintLayoutRegister = findViewById(R.id.constraintRegister);


        // if the user has a account then goto login page
        textviewExistingAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = editTextEmailAdress.getText().toString();
                password = editTextPassword.getText().toString();

                // if a edittext is empty or the password is too short, then get the user a warnung
                if (!TextUtils.isEmpty(editTextEmailAdress.getText()) || !TextUtils.isEmpty(editTextPassword.getText())) {

                    if (!(password.length() >= 8) || !email.contains("@") ) {

                        Snackbar.make(constraintLayoutRegister, "E-Mail-Adresse richtig oder Passwort zu klein?",Snackbar.LENGTH_LONG).show();

                        return;
                    }

                    createUserAccount(email, password);
                }
            }
        });


    }


    @Override
    protected void onStart() {

        isUserLoggedin();

        super.onStart();

    }

    private void isUserLoggedin() {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {

            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void createUserAccount(String email, String password) {

        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    Intent intentHome = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intentHome);
                    finish();

                } else {

                    Toast.makeText(RegisterActivity.this, "Die Registrierung war nicht möglich!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}