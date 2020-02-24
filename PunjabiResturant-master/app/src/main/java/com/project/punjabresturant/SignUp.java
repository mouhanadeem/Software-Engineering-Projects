package com.project.punjabresturant;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.punjabresturant.Model.User;

public class SignUp extends AppCompatActivity {
    EditText edtName,edtPhone, edtPassword;
    Button btnSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        edtName=findViewById(R.id.txtSignupName);
        edtPhone=findViewById(R.id.txtSignupPhone);
        edtPassword=findViewById(R.id.txtSignupPassword);
        btnSignUp=findViewById(R.id.btnSignUp);
       final DatabaseReference userdata=FirebaseDatabase.getInstance().getReference("User");

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog dialog=new ProgressDialog(SignUp.this);
                dialog.setMessage("Please wait");
                dialog.show();
                userdata.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(edtPhone.getText().toString()).exists()) {
                            dialog.dismiss();
                            Toast.makeText(SignUp.this, "Phone no already exist in database", Toast.LENGTH_SHORT).show();
                            } else {
                            dialog.dismiss();
                            User user= new User(edtName.getText().toString(),edtPassword.getText().toString());
                            userdata.child(edtPhone.getText().toString()).setValue(user);
                                Toast.makeText(SignUp.this, "User has been SignUp", Toast.LENGTH_SHORT).show();
                                finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
