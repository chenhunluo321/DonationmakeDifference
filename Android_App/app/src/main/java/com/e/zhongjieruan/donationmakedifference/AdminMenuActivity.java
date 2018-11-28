package com.e.zhongjieruan.donationmakedifference;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminMenuActivity extends AppCompatActivity {
    private Button applications;
    private Button donations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);

        applications = findViewById(R.id.btCheckApplicationList);
        donations = findViewById(R.id.btAdminDonation);
        applications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminMenuActivity.this, ApplicationListActivity.class));
            }
        });
        donations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminMenuActivity.this, DonationListActivity.class));
            }
        });
    }
}
