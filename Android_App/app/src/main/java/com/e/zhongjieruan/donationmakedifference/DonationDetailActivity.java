package com.e.zhongjieruan.donationmakedifference;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DonationDetailActivity extends AppCompatActivity {

    private TextView title;
    private TextView description;
    private TextView amountRequired;
    private EditText userInput;
    private Button donateButton;
    private Database sqliteHelper;
    private DonationSpecialUser donationSpecialUser;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    private boolean validOrNot;
    DonationFacade donationFacade;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_detail);
        if (sqliteHelper == null) {
            sqliteHelper = new Database(this);
        }
        findViews();
        getUserData();
        donateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                donationFacade = new DonationFacade(user.getPayment().getCardHolderName(),user.getPayment().getCardNumber(),user.getPayment().getCardType(),Integer.parseInt(userInput.getText().toString()),Integer.parseInt(amountRequired.getText().toString()),user);
                String result =donationFacade.donate();
                if (result.equals("Success")) {
                    Toast.makeText(DonationDetailActivity.this, "Successfully Donated", Toast.LENGTH_SHORT).show();
                    sqliteHelper.updateDonationAmount(donationSpecialUser,donationFacade.updateNewAmount());
                }
                else if (result.equals("Donate too much"))
                    Toast.makeText(DonationDetailActivity.this,"Donate too much",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(DonationDetailActivity.this,"Card not valid/No Card Linked",Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void findViews() {
        title = (TextView) findViewById(R.id.tvDonationTitle);
        description = (TextView) findViewById(R.id.tvDonationDetail);
        amountRequired = (TextView) findViewById(R.id.tvDonationAmountRequire);
        userInput = (EditText) findViewById(R.id.etUserDonationAmount);
        donateButton = (Button) findViewById(R.id.btDonateMoney);
        String donName = getIntent().getExtras().getString("title");
        donationSpecialUser = sqliteHelper.findByTitle(donName);
        if (donationSpecialUser == null) {
            Toast.makeText(this,"No Item Found",Toast.LENGTH_SHORT).show();
        }
        title.setText(donationSpecialUser.getDonationName());
        description.setText(donationSpecialUser.getDonationDetail());
        amountRequired.setText(Integer.toString(donationSpecialUser.getDonationAmount()));
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
                Toast.makeText(DonationDetailActivity.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }

        });
    }
}
