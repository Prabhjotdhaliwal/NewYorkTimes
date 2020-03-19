package com.example.newyorktimes;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity {


  Intent i;
  EditText NameEd,EmailEd,Phone,Password;
  TextView loginhere;
  Button signupButton1;
 private FirebaseAuth fireBaseAuth;
  FirebaseFirestore fstore;
  ProgressBar progressBar;
  String userId;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = fireBaseAuth.getCurrentUser();
      //  updateUI(currentUser);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState){
     super.onCreate(savedInstanceState);
     setContentView(R.layout.activity_sign_up);
     //Assign Id's
     NameEd = (EditText) findViewById(R.id.NameeditText1);
     EmailEd = (EditText) findViewById(R.id.EmaileditText1);
     Phone = (EditText) findViewById(R.id.Phone);
     Password = (EditText) findViewById(R.id.Password1);
     progressBar = findViewById(R.id.progressBar);

//if (fireBaseAuth.getCurrentUser() !=null)
//{
//
//}
    signupButton1 = findViewById(R.id.SignUpbutton);
    loginhere=findViewById(R.id.logintxt);
        fireBaseAuth = FirebaseAuth.getInstance();
    // findViewById(R.id.SignUpbutton).setOnClickListener(this);
   //  findViewById(R.id.Loginbutton3).setOnClickListener(this);
signupButton1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v)
    {
        registerUser();
    }
});


loginhere.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(getApplicationContext(), Main1Activity.class));

    }
});
    }




 private  void registerUser() {

     String namestr, Emailstr, phonestr, passwordstr;

     namestr = NameEd.getText().toString().trim();
     Emailstr = EmailEd.getText().toString().trim();
     phonestr = Phone.getText().toString().trim();
     passwordstr = Password.getText().toString().trim();


     if (TextUtils.isEmpty(namestr)) {
         NameEd.setError("Name is Required ");
     }
     if (TextUtils.isEmpty(Emailstr)) {
         EmailEd.setError("Email is Required ");

     }
     if (!Patterns.EMAIL_ADDRESS.matcher(Emailstr).matches()) {
         EmailEd.setError("Enter a valid Email");

     }
     if (TextUtils.isEmpty(phonestr)) {
         Phone.setError("Phone is Required ");

     }
     if (TextUtils.isEmpty(passwordstr)) {
         Password.setError("Password is Required ");

     }
     if (Password.length() < 8) {
         Password.setError("Password must be >= 6 Characters");
     }
     else {
         //create the user with email and password
         fireBaseAuth.createUserWithEmailAndPassword(Emailstr, passwordstr)
                 .addOnCompleteListener(this, new OnCompleteListener<AuthResult> () {
                     @Override
                     public void onComplete(@NonNull Task<AuthResult> task) {
                         if (task.isSuccessful()) {
                             // Sign in success, update UI with the signed-in user's information
                             Log.d("TAG", "createUserWithEmail:success");
                             Toast.makeText(getApplicationContext(), "User Created", Toast.LENGTH_SHORT).show();
                             FirebaseFirestore db = FirebaseFirestore.getInstance();

                             String name=NameEd.getText().toString();
                             String price=EmailEd.getText().toString();
                             String qty=Phone.getText().toString();

                             HashMap<String,String> user=new HashMap<>();
                             user.put("name",name);
                             user.put("price",price);
                             user.put("qty",qty);

     db.collection("Users").add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference> ()
   {
    @Override
    public void onSuccess(DocumentReference documentReference)
    {
        Toast.makeText(SignUpActivity.this,"Added",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(SignUpActivity.this, Main1Activity.class));


    }
}).addOnFailureListener(new OnFailureListener () {
         @Override
         public void onFailure(@NonNull Exception e)
         {
             Toast.makeText(SignUpActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
             e.printStackTrace();


         }
     });

                             // FirebaseUser user = fireBaseAuth.getCurrentUser();
                             // updateUI(user);
                         }
                     }
                 });
     }
 }














    }
/*
    signupButton1.setOnClickListener(new View.OnClickListener()
{
    @Override
    public void onClick(View v) {
        namestr = NameEd.getText().toString().trim();
        Emailstr = EmailEd.getText().toString().trim();
        phonestr = Phone.getText().toString().trim();
        passwordstr = Password.getText().toString().trim();


        if (TextUtils.isEmpty(namestr)) {
            NameEd.setError("Name is Required ");
        }
        if (TextUtils.isEmpty(Emailstr)) {
            EmailEd.setError("Email is Required ");

        }
        if (TextUtils.isEmpty(phonestr)) {
            Phone.setError("Phone is Required ");

        }
        if (TextUtils.isEmpty(passwordstr)) {
            Password.setError("Password is Required ");

        }
        if (Password.length() < 8) {
            Password.setError("Password must be >= 6 Characters");
        }
        progressBar.setVisibility(View.VISIBLE);
        System.out.println("SignHi");
         startActivity(new Intent(getApplicationContext(),MainActivity.class));

    }
});


        loginButton1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                i = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(i);

            }
        });

    }


         public void registerUser(  )
         {

//register user
             fireBaseAuth.createUserWithEmailAndPassword(Emailstr, passwordstr).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                 @Override
                 public void onComplete(@NonNull Task<AuthResult> task)
                 {

                     if (task.isSuccessful())
                     {

                         Toast.makeText(SignUpActivity.this,"Successfully user has been created",Toast.LENGTH_SHORT).show();
                         startActivity(new Intent(getApplicationContext(),Main2Activity.class));
                     }
                     else
                     {
                         Toast.makeText(SignUpActivity.this," Error!!!"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                         // startActivity(new Intent(getApplicationContext(),MainActivity.class));

                     }
                 }
             });

         }


      /*  fireBaseAuth.createUserWithEmailAndPassword(Emailstr, passwordstr)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(SignUpActivity.this, "User crested", Toast.LENGTH_LONG).show();
                            userId = fireBaseAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fstore.collection("Users").document(userId);
                            Map<String, Object> user = new HashMap<>();
                            user.put("Name", namestr);
                            user.put("Email", Emailstr);
                            user.put("Phone", phonestr);
                            // user.put("Password",phonestr);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d("TAG", "onSuccess: user profile is created for " + userId);
                                }
                            });
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else {
                            Toast.makeText(SignUpActivity.this, "Error!!" + task.getException().getMessage(), Toast.LENGTH_LONG).show();

                        }

                })
}
            */




       /*    private void registerUser(String email1 , String password1)
           {

               //This method will create new User on firebase console...
               fireBaseAuth.createUserWithEmailAndPassword(email1, password1).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {

                       if (task.isSuccessful()) {
                           // SignUp in success, Direct to the dashboard Page...
                           Toast.makeText(SignUpActivity.this, "User crested", Toast.LENGTH_LONG).show();
                           userId = fireBaseAuth.getCurrentUser().getUid();
                           DocumentReference documentReference = fstore.collection("Users").document(userId);
                           Map<String, Object> user = new HashMap<>();
                           user.put("Name", namestr);
                           user.put("Email", Emailstr);
                           user.put("Phone", phonestr);
                           documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                               @Override
                               public void onSuccess(Void aVoid) {
                                   Log.d("TAG", "onSuccess: user profile is created for " + userId);
                                   Toast.makeText(SignUpActivity.this, "sucessssss", Toast.LENGTH_LONG).show();

                               }
                           }).addOnFailureListener(new OnFailureListener()
                           {
                               @Override
                               public void onFailure(@NonNull Exception e)
                               {
                                   Log.d("TAG", "onFailure:  " + e.toString());

                                   Toast.makeText(SignUpActivity.this, "failedddd", Toast.LENGTH_LONG).show();

                               }
                           });
                           startActivity(new Intent(getApplicationContext(), MainActivity.class));
                       } else {
                           Toast.makeText(SignUpActivity.this, "Error!!" + task.getException().getMessage(), Toast.LENGTH_LONG).show();

                       }

                   }
               });*/




