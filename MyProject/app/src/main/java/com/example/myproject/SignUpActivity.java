package com.example.myproject;
//Firebase Project: My-Project-Notifly

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private EditText usernameEditText, emailEditText, phoneEditText, passEditText;
    private Button signUpButton;
    private TextView signInTextView;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseRef;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        this.setTitle("Create Account");

        //set toolbar
        toolbar = findViewById(R.id.toolbarId);
        setSupportActionBar(toolbar);
        //add back up button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        usernameEditText = findViewById(R.id.usernameEditTextId);
        emailEditText = findViewById(R.id.emailEditTextId);
        phoneEditText = findViewById(R.id.phoneEditTextId);
        passEditText = findViewById(R.id.passEditTextId);
        signUpButton = findViewById(R.id.signUpButtonId);
        signInTextView = findViewById(R.id.signInTextId);

        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("USERS");
        mAuth = FirebaseAuth.getInstance();

        signInTextView.setOnClickListener(this);
        signUpButton.setOnClickListener(this);
        progressDialog = new ProgressDialog(this);


    }

    //add back up button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            this.finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.signUpButtonId:

                String username = usernameEditText.getText().toString().trim();
                String email = emailEditText.getText().toString().trim();
                String phoneNum = phoneEditText.getText().toString().trim();
                String password = passEditText.getText().toString().trim();

                if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(phoneNum) && !TextUtils.isEmpty(password))
                {

                    signUpMethod(username, email, phoneNum, password);
                }
                else
                    Toast.makeText(getApplicationContext(), "Fill up all fields", Toast.LENGTH_SHORT).show();
                break;

            case R.id.signInTextId:
                Intent signInIntent = new Intent(SignUpActivity.this, SignInActivity.class);
                signInIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(signInIntent);
        }
    }

    //signUp method
    private void signUpMethod(final String username, final String email, final String phoneNum, final String password) {
        progressDialog.setMessage("Signing Up...");
        progressDialog.show();
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    String user_id = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();   //String user_id = mAuth.getCurrentUser().getUid();

                   DatabaseReference mUserDatatbaseRef = mDatabaseRef.child(user_id);

                   mUserDatatbaseRef.child("username").setValue(username);
                   mUserDatatbaseRef.child("email").setValue(email);
                   mUserDatatbaseRef.child("phone num").setValue(phoneNum);
                   mUserDatatbaseRef.child("password").setValue(password);

                    Toast.makeText(getApplicationContext(), "Registration successful", Toast.LENGTH_SHORT ).show();
                    Intent mainIntent = new Intent(SignUpActivity.this, MainActivity.class);
                    mainIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(mainIntent);

                }
                else
                    Toast.makeText(getApplicationContext(), "Error in Signing Up", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        });
    }
}
