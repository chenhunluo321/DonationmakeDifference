package com.e.zhongjieruan.donationmakedifference;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {
    Button donationList;
    Button linkCard;
    Button donationApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        donationList = findViewById(R.id.btDonationList);
        linkCard = findViewById(R.id.btLinkCard);
        donationApplication = findViewById(R.id.btApplication);

        donationList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, DonationListActivity.class));
            }
        });

        linkCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, LinkCardActivity.class));
            }
        });
        donationApplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, ApplicationActivity.class));
            }
        });

    }
}
