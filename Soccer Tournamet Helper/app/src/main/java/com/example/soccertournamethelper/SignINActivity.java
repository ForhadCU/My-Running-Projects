package com.example.soccertournamethelper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class SignINActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView textView_reg;
    private Button button_signIN;
    private EditText editText_email, editText_pass;
    private TextInputLayout textInputLayout_emailError, textInputLayout_passError;
    private Boolean isEmailValid, isPassValid;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;
    private long backPressedTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        Objects.requireNonNull(getSupportActionBar()).hide();
        mAuth = FirebaseAuth.getInstance();

        textView_reg = findViewById(R.id.tv_registerID);
        button_signIN = findViewById(R.id.btn_loginID);
        editText_email = findViewById(R.id.eTxt_emailID);
        editText_pass = findViewById(R.id.eTxt_passwordID);
        textInputLayout_passError = findViewById(R.id.passError);
        textInputLayout_emailError = findViewById(R.id.emailError);

        textView_reg.setOnClickListener(this);
        button_signIN.setOnClickListener(this);
        progressDialog = new ProgressDialog(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.tv_registerID:
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
                break;

            case R.id.btn_loginID:
                String email = editText_email.getText().toString().trim();
                String pass = editText_pass.getText().toString().trim();

                boolean getValidation = mSetValidation(email, pass);
                if (getValidation)
                    mSignIN(email, pass);
                break;

        }
    }

    //BackPress Process
/*    @Override
    public void onBackPressed() {
        backPressedTime = (backPressedTime+1);
        Toast.makeText(getApplicationContext(), "Press back again to Exit", Toast.LENGTH_SHORT).show();

        if (backPressedTime > 1){
            this.finish();
        }
    }//END*/

    //BackPress Process 2
    @Override
    public void onBackPressed() {
        long t = System.currentTimeMillis();
        if (t - backPressedTime > 2000) //2 sec
        {
            backPressedTime = t;
            Toast.makeText(getApplicationContext(), "Press back again to exit", Toast.LENGTH_SHORT).show();
        }
        else {
            //clean up
            super.onBackPressed();
        }
    }

    private void mSignIN(String email, String pass) {
        progressDialog.setMessage("Signing in..");
        progressDialog.show();

        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    progressDialog.dismiss();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private boolean mSetValidation(String email, String pass) {

        //email validity
        if (TextUtils.isEmpty(email))
        {
            textInputLayout_emailError.setError(getResources().getString(R.string.email_error));
            isEmailValid = false;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            textInputLayout_emailError.setError(getResources().getString(R.string.error_invalid_email));
            isEmailValid = false;
        }
        else
        {
            isEmailValid = true;
            textInputLayout_emailError.setErrorEnabled(false);
        }

        //pass validity
        if (TextUtils.isEmpty(pass))
        {
            textInputLayout_passError.setError(getResources().getString(R.string.password_error));
            isPassValid = false;
        }
        else if (pass.length() < 6)
        {
            textInputLayout_passError.setError(getResources().getString(R.string.error_invalid_password));
            isPassValid = false;
        }
        else {
            textInputLayout_passError.setErrorEnabled(false);
            isPassValid = true;
        }

        //return result true
        if (isEmailValid && isPassValid)
            return true;
        else
            return false;
    }
}
