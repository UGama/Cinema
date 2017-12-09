package com.example.cinema;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gama on 12/4/17.
 */

public class Place extends AppCompatActivity {
    private RecyclerView dateRecyclerView;
    private List<String> dateList;
    private RecyclerView cinemaRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place);

        dateRecyclerView = findViewById(R.id.DateRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        dateRecyclerView.setLayoutManager(linearLayoutManager);
        dateList = new ArrayList<>();
        dateList.add("今天12月1日");
        dateList.add("明天12月2日");
        dateList.add("后天12月3日");
        dateList.add("周四12月4日");
        dateList.add("周五12月5日");
        dateList.add("周六12月6日");
        dateList.add("周日12月7日");
        DateAdapter dateAdapter = new DateAdapter(dateList);
        dateRecyclerView.setAdapter(dateAdapter);

        cinemaRecyclerView = findViewById(R.id.CinemaRecyclerView);
    }

    private class DateAdapter extends RecyclerView.Adapter<DateAdapter.ViewHolder> {

        private List<String> dateList;

        private DateAdapter(List<String> dateList) {
            this.dateList = dateList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.date_item, parent, false);
            final ViewHolder holder = new ViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            String Date = dateList.get(position);
            holder.Date.setText(Date);
        }

        @Override
        public int getItemCount() {
            return dateList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            private TextView Date;
            private ImageView Remind;
            private ViewHolder(View view) {
                super(view);
                Date = view.findViewById(R.id.Date);
                Remind = view.findViewById(R.id.Remind);
            }
        }
    }
}
