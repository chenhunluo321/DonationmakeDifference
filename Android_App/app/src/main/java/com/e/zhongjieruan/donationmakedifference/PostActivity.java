package com.e.zhongjieruan.donationmakedifference;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PostActivity extends AppCompatActivity {
    EditText posttitle, postdescription, amount;
    Button post;
    Database database;
    User user;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        if (database == null) {
            database = new Database(this);
        }
        initialization();
        getUserData();

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DonationSpecialUser donation = new DonationSpecialUser(posttitle.getText().toString(),Integer.parseInt(amount.getText().toString()),postdescription.getEditableText().toString());
                User updatapost = user.linkUserPost(donation);

                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid());
                myRef.setValue(updatapost);

                long rowId = database.insertDonation(donation);
                if (rowId != -1) {
                    Toast.makeText(PostActivity.this, "Post Success",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(PostActivity.this, "Post Failed",
                            Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });
    }
    public void initialization(){
        posttitle=findViewById(R.id.etPostTitle);
        postdescription=findViewById(R.id.etPostDescription);
        amount=findViewById(R.id.etDonationamount);
        post=findViewById(R.id.btpost);
    }

    private void getUserData(){
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                user = dataSnapshot.getValue(User.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(PostActivity.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }

        });
    }
}
