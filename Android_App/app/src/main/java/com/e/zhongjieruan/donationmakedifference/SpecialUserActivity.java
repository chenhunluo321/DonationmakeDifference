package com.e.zhongjieruan.donationmakedifference;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SpecialUserActivity extends AppCompatActivity {
    Button list, post, mydonation, linkcard;
    User user;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_user);
        initialization();
        getUserData();
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SpecialUserActivity.this, DonationListActivity.class));
            }
        });
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SpecialUserActivity.this, PostActivity.class));
            }
        });
        mydonation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SpecialUserActivity.this, DonationDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("title", user.getDonation().getDonationName()); ;
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        linkcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SpecialUserActivity.this, LinkCardActivity.class));
            }
        });
    }
    public void initialization(){
        list = findViewById(R.id.btDonationList);
        post = findViewById(R.id.btPostDonate);
        mydonation = findViewById(R.id.btMyDonation);
        linkcard = findViewById(R.id.btLinkCard);
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
                Toast.makeText(SpecialUserActivity.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }

        });
    }
}
