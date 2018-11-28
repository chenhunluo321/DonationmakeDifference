package com.e.zhongjieruan.donationmakedifference;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class DonationListActivity extends AppCompatActivity {
    private Database sqliteHelper;
    private SpotAdapter spotAdapter;
    private RecyclerView rvSpots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_list);
        if (sqliteHelper == null) {
            sqliteHelper = new Database(this);
        }
        rvSpots = (RecyclerView) findViewById(R.id.rvDonations);
        rvSpots.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();
        List<DonationSpecialUser> spotList = getSpotList();
        if (spotList.size() <= 0) {
            Toast.makeText(DonationListActivity.this, "No data", Toast.LENGTH_SHORT).show();
        }

        if (spotAdapter == null) {
            spotAdapter = new SpotAdapter(this, spotList);
            rvSpots.setAdapter(spotAdapter);
        } else {
            spotAdapter.setSpotList(spotList);
            spotAdapter.notifyDataSetChanged();
        }

    }

    public List<DonationSpecialUser> getSpotList() {
        return sqliteHelper.getAllDonation();
    }

//    public void onInsertClick(View view) {
//        Intent intent = new Intent(this, InsertActivity.class);
//        startActivity(intent);
//    }

    private class SpotAdapter extends RecyclerView.Adapter<SpotAdapter.SpotViewHolder> {
        Context context;
        List<DonationSpecialUser> spotList;

        void setSpotList(List<DonationSpecialUser> spotList) {
            this.spotList = spotList;
        }

        SpotAdapter(Context context, List<DonationSpecialUser> spotList) {
            this.context = context;
            this.spotList = spotList;
        }

        class SpotViewHolder extends RecyclerView.ViewHolder {
            TextView tvDonation;

            SpotViewHolder(View itemView) {
                super(itemView);
                tvDonation = (TextView) itemView.findViewById(R.id.tvdonations);
            }
        }

        @Override
        public int getItemCount() {
            return spotList.size();
        }

        @Override
        public SpotViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            View itemView = layoutInflater.inflate(R.layout.donation_list_view, parent, false);
            return new SpotViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(SpotViewHolder holder, int position) {
            final DonationSpecialUser spot = spotList.get(position);
            holder.tvDonation.setText(spot.getDonationName());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DonationDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("title", spot.getDonationName()); ;
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (sqliteHelper != null) {
            sqliteHelper.close();
        }
    }

}


