package com.e.zhongjieruan.donationmakedifference;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class ApplicationActivity extends AppCompatActivity {
    private EditText donationTitle, govId, donationDetail;
    private Button submitApplication;
    private Database database;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);
        if (database == null) {
            database = new Database(this);
        }
        initialization();
        submitApplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = donationTitle.getText().toString();
                int id = Integer.parseInt(govId.getText().toString());
                final String detail = donationDetail.getText().toString();

                firebaseAuth = FirebaseAuth.getInstance();
                ApplicationUser application = new ApplicationUser(firebaseAuth.getUid(),title,id,detail);
                long result = application.doApplication(getApplicationContext(),application);
                if (result != -1) {
                    Toast.makeText(ApplicationActivity.this, "Submit Success",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ApplicationActivity.this, "Submit Failed",
                            Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });
    }

    public void initialization(){
        donationTitle = findViewById(R.id.etPostDonationTitle);
        govId = findViewById(R.id.etGovId);
        donationDetail = findViewById(R.id.etPostDonationDetail);
        submitApplication = findViewById(R.id.btPostDonate);
    }
}
