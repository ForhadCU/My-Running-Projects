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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    private Button button_reg;
    private EditText editText_name, editText_email, editText_phn, editText_pass;
    private TextInputLayout textInputLayout_nameError, textInputLayout_emailError, textInputLayout_phnError, textInputLayout_passError;
    private TextView textView_alreadyReg;
    private boolean isNameValid, isEmailValid, isPhnValid, isPassValid;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Objects.requireNonNull(getSupportActionBar()).hide();
        mAuth = FirebaseAuth.getInstance();

        button_reg = findViewById(R.id.btn_registerID);
        editText_name = findViewById(R.id.eTxt_nameRegID);
        editText_email = findViewById(R.id.eTxt_emailRegID);
        editText_phn = findViewById(R.id.eTxt_phoneRegID);
        editText_pass = findViewById(R.id.eTxt_passwordRegID);
        textInputLayout_nameError = findViewById(R.id.nameError);
        textInputLayout_emailError = findViewById(R.id.emailError);
        textInputLayout_phnError = findViewById(R.id.phoneError);
        textInputLayout_passError = findViewById(R.id.passError);
        textView_alreadyReg = findViewById(R.id.tv_alreadyRegID);

        button_reg.setOnClickListener(this);
        textView_alreadyReg.setOnClickListener(this);
        progressDialog = new ProgressDialog(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_registerID:
                String name = editText_name.getText().toString().trim();
                String email = editText_email.getText().toString().trim();
                String phone = editText_phn.getText().toString().trim();
                String pass = editText_pass.getText().toString().trim();

                boolean getValidation = mSetValidation(name, email, phone, pass);
                if (getValidation)
                    mSignUp(email, pass);
                break;

            case R.id.tv_alreadyRegID:
                Intent intent1 = new Intent(getApplicationContext(), SignINActivity.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent1);
        }
    }







    private void mSignUp(String email, String pass) {
        progressDialog.setMessage("Signing Up....");
        progressDialog.show();

        mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Registration successful", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), SignINActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }
                else{
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private boolean mSetValidation(String name, String email, String phone, String pass) {
        //name validity
        if (TextUtils.isEmpty(name))
        {
            textInputLayout_nameError.setError(getResources().getString(R.string.name_error));
            isNameValid = false;
        }
        else{
            isNameValid = true;
            textInputLayout_nameError.setErrorEnabled(false);
        }

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
        else {
            isEmailValid = true;
            textInputLayout_emailError.setErrorEnabled(false);
        }

        //phone validity
        if (TextUtils.isEmpty(phone))
        {
            textInputLayout_phnError.setError(getResources().getString(R.string.phone_error));
            isPhnValid = false;
        }
        else {
            textInputLayout_phnError.setErrorEnabled(false);
            isPhnValid = true;
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
            isPassValid = true;
            textInputLayout_passError.setErrorEnabled(false);
        }

        //return result true
        if (isNameValid && isEmailValid && isPhnValid && isPassValid)
        {
            return true;
        }
        else
            return false;
    }
}
