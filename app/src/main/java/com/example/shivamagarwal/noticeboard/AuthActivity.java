package com.example.shivamagarwal.noticeboard;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shivamagarwal.noticeboard.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthActivity extends AppCompatActivity {

    ImageView iv1,iv2;
    EditText t1,t2,t3,t4;
    Button b1,b2;
    TextView tv1,tv2,tv3;
    private FirebaseAuth mAuth;

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            t1.setText(currentUser.getDisplayName());
            t2.setText(currentUser.getEmail());
            findViewById(R.id.l_tv3).setVisibility(View.VISIBLE);
            findViewById(R.id.l_tv3).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mAuth.signOut();
                    findViewById(R.id.l_tv3).setVisibility(View.GONE);
                    startActivity(new Intent(AuthActivity.this,AuthActivity.class));
                    finish();
                }
            });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        mAuth = FirebaseAuth.getInstance();
        iv1 = findViewById(R.id.s_img);
        iv2 = findViewById(R.id.l_img);
        t1 = findViewById(R.id.s_t1);
        t2 = findViewById(R.id.a1_t2);
        t3 = findViewById(R.id.a1_t3);
        t4 = findViewById(R.id.s_t4);
        b1= findViewById(R.id.s_b);
        b2= findViewById(R.id.l_b);
        tv1 = findViewById(R.id.s_tv);
        tv2 = findViewById(R.id.l_tv1);
        tv3 = findViewById(R.id.l_tv2);

        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateTOLoginUI();
            }
        });
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateTOSignupUI();
            }
        });
        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AuthActivity.this, "Not Working!!", Toast.LENGTH_SHORT).show();
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(t2.getText().toString())&&!TextUtils.isEmpty(t3.getText().toString())&&!TextUtils.isEmpty(t4.getText().toString()))
                    if(t3.getText().toString().equals(t4.getText().toString()))
                        createAccount(t1.getText().toString(),t2.getText().toString(),t3.getText().toString());
                    else
                        Toast.makeText(AuthActivity.this, "Password Mismatch!!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(AuthActivity.this, "Required Fields are Empty!!", Toast.LENGTH_SHORT).show();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(t2.getText().toString())&&!TextUtils.isEmpty(t3.getText().toString()))
                    signIn(t2.getText().toString(),t3.getText().toString());
                else
                    Toast.makeText(AuthActivity.this, "Empty Fields!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(AuthActivity.this, "Authentication Successful!!", Toast.LENGTH_SHORT).show();
                            SharedPreferences sp=getSharedPreferences("mysp", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor=sp.edit();
                            editor.putString("postedby",mAuth.getCurrentUser().getDisplayName()+"<"+mAuth.getCurrentUser().getEmail()+">").commit();
                            startActivity(new Intent(AuthActivity.this,MainActivity.class));
                        }else {
                            Toast.makeText(AuthActivity.this, "Authentication Unsuccessful Invalid Credentials!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void updateTOSignupUI() {
        iv1.setVisibility(View.VISIBLE);
        iv2.setVisibility(View.GONE);
        t1.setVisibility(View.VISIBLE);
        t4.setVisibility(View.VISIBLE);
        b1.setVisibility(View.VISIBLE);
        b2.setVisibility(View.GONE);
        tv1.setVisibility(View.VISIBLE);
        tv2.setVisibility(View.GONE);
        tv3.setVisibility(View.GONE);
    }

    private void updateTOLoginUI() {
        iv1.setVisibility(View.GONE);
        iv2.setVisibility(View.VISIBLE);
        t1.setVisibility(View.GONE);
        t4.setVisibility(View.GONE);
        b1.setVisibility(View.GONE);
        b2.setVisibility(View.VISIBLE);
        tv1.setVisibility(View.GONE);
        tv2.setVisibility(View.VISIBLE);
        tv3.setVisibility(View.VISIBLE);
    }

    private void createAccount(final String username, String email, String password) {
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(AuthActivity.this, "Account Created Successfully!!", Toast.LENGTH_SHORT).show();
                            updateTOLoginUI();
                        }else {
                            Toast.makeText(AuthActivity.this, "Task Failure Occured!! Either account already exists or password is too small (less than 6 characters)", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
