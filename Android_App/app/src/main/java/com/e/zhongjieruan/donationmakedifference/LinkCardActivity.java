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

import java.util.Random;

public class LinkCardActivity extends AppCompatActivity {
    private EditText cardname, cardtype, cardnumber;
    private Button linkcard;
    User user;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_card);
        cardname = findViewById(R.id.etCardName);
        cardtype = findViewById(R.id.etCardType);
        cardnumber = findViewById(R.id.etCardNumber);
        linkcard = findViewById(R.id.btLinkCard);

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
            }
        });

        linkcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = cardname.getText().toString();
                String type = cardtype.getText().toString();
                Double number = Double.parseDouble(cardnumber.getText().toString());
                int intValue = number.intValue();
                Payment payment = new Payment(name,type,intValue);


                User updateUser = payment.linkCard(user);

                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid());
                myRef.setValue(updateUser);
            }
        });
    }
}
