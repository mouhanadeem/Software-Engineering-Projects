package com.project.punjabresturant;

import android.app.ProgressDialog;
import android.content.Intent;
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
import com.project.punjabresturant.Shared.Shared;

public class SignIn extends AppCompatActivity {
private EditText edtPhone, edtPassword;
private Button btnConfirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        edtPhone=findViewById(R.id.txtSigninPhone);
        edtPassword=findViewById(R.id.txtSigninPassword);
        btnConfirm=findViewById(R.id.btnConfirm);
        FirebaseDatabase database= FirebaseDatabase.getInstance();
        final DatabaseReference user=database.getReference("User");
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog dialog=new ProgressDialog(SignIn.this);
                dialog.setMessage("Please wait");
                dialog.show();

                user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(edtPhone.getText().toString()).exists()) {
                            dialog.dismiss();
                            User user = dataSnapshot.child(edtPhone.getText().toString()).getValue(User.class);
                            user.setPhone(edtPhone.getText().toString());
                            if (user.getPassword().equals(edtPassword.getText().toString())) {
                                Intent nav= new Intent(SignIn.this,Navigation.class);
                                Shared.sharedUser=user;
                                startActivity(nav);
                                finish();
                            } else {
                                Toast.makeText(SignIn.this, "SignIn failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(SignIn.this,"User not exist",Toast.LENGTH_SHORT).show();
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
