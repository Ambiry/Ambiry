package de.jukolai.ambiry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    ImageButton ImageButtonBackButton;
    Button loginButton;
    EditText editTextPassword, editTextEmailAdress;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ImageButtonBackButton = findViewById(R.id.imageButtonBackbutton);
        editTextEmailAdress = findViewById(R.id.editTextEmailAdress);
        editTextPassword = findViewById(R.id.editTextPassword);
        loginButton = findViewById(R.id.buttonLogin);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });


        ImageButtonBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Back to RegisterActivity
                onBackPressed();
            }
        });
    }

    private void userLogin() {


        if (editTextEmailAdress.getText().toString().isEmpty() | editTextPassword.getText().toString().isEmpty()) {
            Toast.makeText(LoginActivity.this, "Bitte alle Felder ausfüllen", Toast.LENGTH_LONG).show();

            return;

        }

        String password = editTextPassword.getText().toString();
        String email = editTextEmailAdress.getText().toString().trim();

        // Get Firebase auth instance
        mAuth = FirebaseAuth.getInstance();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Intent intentHome = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intentHome);
                            finish();

                        } else {

                            // If sign in fails, display a message to the user.
                            Log.w("LOG", "signInWithEmail:failure", task.getException());

                            Toast.makeText(LoginActivity.this, "Das Anmelden war nicht erfolgreich!", Toast.LENGTH_SHORT).show();
                        }

                    }
                });


    }
}