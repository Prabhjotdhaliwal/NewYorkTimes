package com.example.newyorktimes;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Main1Activity extends AppCompatActivity
{
    LinearLayout first;
    Button login;
    TextView forgotPass,registernow;
    EditText username,password;
    ImageView img;
    Intent i;
FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        first=findViewById(R.id.firstLayout);
        forgotPass=findViewById(R.id.ForgotpasstextView);
        username=(EditText)findViewById(R.id.UsereditText);
        password=(EditText)findViewById(R.id.passwordeditText4);
        img=(ImageView)findViewById(R.id.LogoimageView2);


        login=findViewById(R.id.Loginbutton);
        registernow=findViewById(R.id.registernow);
        firebaseAuth= FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                //save user's Information
            LoginUser();

            }

        });


        //Forgot password
        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(Main1Activity.this,"Enter Your email",Toast.LENGTH_LONG).show();

            }

        });



        //SignUP
        registernow.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                i = new Intent(Main1Activity.this, SignUpActivity.class);
                startActivity(i);
            }
        });





    }
    //SignInUser
       public  void  LoginUser() {
           final String usernameStr, passwordStr;

           usernameStr = username.getText().toString();
           passwordStr = password.getText().toString();

           if (TextUtils.isEmpty(usernameStr)) {
               username.setError("Email is Required ");

           }
           if (TextUtils.isEmpty(passwordStr)) {
               password.setError("Password is Required ");

           }
           if (passwordStr.length() < 8) {
               password.setError("Password must be >= 6 Characters");
           } else {
               //Authenticate the user
               firebaseAuth.signInWithEmailAndPassword(usernameStr, passwordStr).addOnCompleteListener(new OnCompleteListener<AuthResult> () {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       if (task.isSuccessful()) {
                           Toast.makeText(getApplicationContext(), "User has successfully logged in", Toast.LENGTH_SHORT).show();

                           startActivity(new Intent(Main1Activity.this, MainActivity.class));


                       } else {
                           Toast.makeText(getApplicationContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                       }
                   }

               });

           }
       }

}
