package com.e.zhongjieruan.donationmakedifference;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class EditUserDonationActivity extends AppCompatActivity {

    private EditText title;
    private EditText description;
    private EditText amountRequired;
    private Button save;
    private Button delete;
    private Database sqliteHelper;
    private DonationSpecialUser donationSpecialUser;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_donation);
        if (sqliteHelper == null) {
            sqliteHelper = new Database(this);
        }
        initialization();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = title.getText().toString();
                String detail = description.getText().toString();
                int amount = Integer.parseInt(amountRequired.getText().toString());
                donationSpecialUser.setDonationName(name);
                donationSpecialUser.setDonationDetail(detail);
                donationSpecialUser.setDonationAmount(amount);
                long result = donationSpecialUser.editPost(getApplicationContext(),donationSpecialUser);
                if (result != -1) {
                    Toast.makeText(EditUserDonationActivity.this, "Edit Success",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EditUserDonationActivity.this, "Edit Failed",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                donationSpecialUser.deletePost(getApplicationContext(),title.getText().toString());
                Toast.makeText(EditUserDonationActivity.this, "DELETED", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void initialization() {
        title = findViewById(R.id.etDonationTitle);
        description = findViewById(R.id.etDonationDetail);
        amountRequired = findViewById(R.id.etDonationAmountRequire);
        delete = findViewById(R.id.btDeleteDonation);
        save = findViewById(R.id.btSaveChange);
        String donName = getIntent().getExtras().getString("title");
        donationSpecialUser = sqliteHelper.findByTitle(donName);
        if (donationSpecialUser != null) {
            title.setText(donationSpecialUser.getDonationName());
            description.setText(donationSpecialUser.getDonationDetail());
            amountRequired.setText(Integer.toString(donationSpecialUser.getDonationAmount()));

        }
        else{
            Toast.makeText(this,"No Item Found",Toast.LENGTH_SHORT).show();
        }
    }
}
