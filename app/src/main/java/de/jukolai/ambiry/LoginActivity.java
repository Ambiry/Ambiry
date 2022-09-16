package de.jukolai.ambiry;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    ImageButton ImageButtonBackButton;
    Button loginButton;
    EditText editTextPassword, editTextEmailAdress;
    FirebaseAuth mAuth;
    TextView textViewForgotPassword;
    ConstraintLayout constraintLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ImageButtonBackButton = findViewById(R.id.imageButtonBackbutton);
        editTextEmailAdress = findViewById(R.id.editTextEmailAdress);
        editTextPassword = findViewById(R.id.editTextPassword);
        loginButton = findViewById(R.id.buttonLogin);
        textViewForgotPassword = findViewById(R.id.textViewForgotPassword);
        constraintLogin = findViewById(R.id.constraintLogin);


        textViewForgotPassword.setOnClickListener(v -> {

            AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
            LinearLayout mLayout = new LinearLayout(this);
            TextView textView  = new TextView(this);
            final EditText editText = new EditText(this);
            editText.setTextColor(Color.BLACK);

            textView.setTextColor(Color.BLACK);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f);
            textView.setSingleLine();
            mLayout.setOrientation(LinearLayout.VERTICAL);
            mLayout.addView(textView);
            mLayout.addView(editText);
            mLayout.setPadding(50, 40, 50, 10);

            mBuilder.setView(mLayout);

            mBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(LoginActivity.this, "Canceled", Toast.LENGTH_SHORT).show();
                }
            });
            mBuilder.setPositiveButton("senden", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    String setMailAdress = editText.getText().toString();

                    if (TextUtils.isEmpty(setMailAdress)){

                        // send ResetMail
                    }

                }
            });

            mBuilder.create().show();

        });


        loginButton.setOnClickListener(v -> userLogin());


        ImageButtonBackButton.setOnClickListener(v -> {
            //Back to RegisterActivity
            onBackPressed();
        });
    }


    private void userLogin() {

        if (editTextEmailAdress.getText().toString().isEmpty() || editTextPassword.getText().toString().isEmpty()) {

            Snackbar.make(constraintLogin, "Bitte alle Felder ausfüllen!",Snackbar.LENGTH_LONG).show();

            return;

        }

        String password = editTextPassword.getText().toString();
        String email = editTextEmailAdress.getText().toString().trim();

        // Get Firebase Auth-instance
        mAuth = FirebaseAuth.getInstance();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(LoginActivity.this, task -> {
                    if (task.isSuccessful()) {

                        Intent intentHome = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intentHome);
                        finish();

                    } else {

                        // If sign in fails, display a message to the user.
                        Log.w("LOG", "signInWithEmail:failure", task.getException());

                        Snackbar.make(constraintLogin, "Die Anmeldung war nicht erfolgreich!",Snackbar.LENGTH_LONG).show();
                    }

                });


    }

}


