package com.example.graphic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText etLoginEmail, etLoginPassword;
    TextView tvRegisterHere;
    Button btnLogin, btnLaterLogin;
    String ManagerEmail = "vvv@vvv.vvv";

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etLoginEmail = findViewById(R.id.etLoginEmail);
        etLoginPassword = findViewById(R.id.etLoginPassword);
        tvRegisterHere = findViewById(R.id.tvRegisterHere);
        btnLogin = findViewById(R.id.btnLogin);
        btnLaterLogin = findViewById(R.id.btnLaterLogin);

        mAuth = FirebaseAuth.getInstance();

        btnLaterLogin.setOnClickListener(view -> {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        });
        btnLogin.setOnClickListener(view -> {
            Loginuser();
        });

        tvRegisterHere.setOnClickListener(view -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });

    }

    public void Loginuser(){
        String email = etLoginEmail.getText().toString();
        String password = etLoginPassword.getText().toString();
        if (TextUtils.isEmpty(email)){
            etLoginEmail.setError("שדה זה לא יכול להיות ריק");
            etLoginEmail.requestFocus();
        }
        else if(TextUtils.isEmpty(password)){
            etLoginPassword.setError("שדה זה לא יכול להיות ריק");
            etLoginPassword.requestFocus();
        }
        else if (etLoginEmail.getText().toString().equals(ManagerEmail)){
            etLoginEmail.setText("");
            etLoginPassword.setText("");
            startActivity(new Intent(LoginActivity.this, AllOrders.class));
        }
        else{
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(LoginActivity.this, "התחברות בוצעה בהצלחה", Toast.LENGTH_SHORT).show();
                        etLoginEmail.setText("");
                        etLoginPassword.setText("");
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }
                    else{
                        Toast.makeText(LoginActivity.this, "משתמש לא רשום", Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }
    }

}