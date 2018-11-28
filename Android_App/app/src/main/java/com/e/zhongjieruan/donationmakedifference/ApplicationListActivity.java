package com.e.zhongjieruan.donationmakedifference;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class ApplicationListActivity extends AppCompatActivity {

    private Database database;
    private SpotAdapter spotAdapter;
    private RecyclerView rvSpots;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_list);
        if (database == null) {
            database = new Database(this);
        }
        rvSpots = (RecyclerView) findViewById(R.id.rvApplications);
        rvSpots.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();
        List<ApplicationUser> spotList = getSpotList();
        if (spotList.size() <= 0) {
            Toast.makeText(
                    this, "No Data", Toast.LENGTH_SHORT
            ).show();
        }

        if (spotAdapter == null) {
            spotAdapter = new SpotAdapter(this, spotList);
            rvSpots.setAdapter(spotAdapter);
        } else {
            spotAdapter.setSpotList(spotList);
            spotAdapter.notifyDataSetChanged();
        }

    }

    public List<ApplicationUser> getSpotList() {
        return database.getAllApplication();
    }

    private class SpotAdapter extends RecyclerView.Adapter<SpotAdapter.SpotViewHolder> {
        Context context;
        List<ApplicationUser> spotList;

        void setSpotList(List<ApplicationUser> spotList) {
            this.spotList = spotList;
        }

        SpotAdapter(Context context, List<ApplicationUser> spotList) {
            this.context = context;
            this.spotList = spotList;
        }

        class SpotViewHolder extends RecyclerView.ViewHolder {
            TextView tvappname, tvappid;

            SpotViewHolder(View itemView) {
                super(itemView);
                tvappname=(TextView)itemView.findViewById(R.id.tvApptitle);
                tvappid=(TextView)itemView.findViewById(R.id.tvgovid);
            }
        }

        @Override
        public int getItemCount() {
            return spotList.size();
        }

        @Override
        public SpotViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            View itemView = layoutInflater.inflate(R.layout.application_list_view, parent, false);
            return new SpotViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(SpotViewHolder holder, int position) {
            final ApplicationUser spot = spotList.get(position);
            holder.tvappname.setText(spot.getDonationTitle());
            holder.tvappid.setText(spot.getDonationDetail());

            firebaseAuth = FirebaseAuth.getInstance();
            firebaseDatabase = FirebaseDatabase.getInstance();

            DatabaseReference databaseReference = firebaseDatabase.getReference(spot.getUserId());
//                    Log.d("Testadmin",spot.getUserId());
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    user = dataSnapshot.getValue(User.class);
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    User updateUser = user.updateUserStatus();

                    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = firebaseDatabase.getReference(spot.getUserId());
                    myRef.setValue(updateUser);

                    database.deleteApplication(spot.getDonationTitle());
                    Toast.makeText(ApplicationListActivity.this,"Proved",Toast.LENGTH_SHORT).show();

                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (database != null) {
            database.close();
        }
    }
}
