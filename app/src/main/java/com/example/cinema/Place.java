package com.example.cinema;

import android.content.Intent;
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
    private String filmNameString;
    private TextView filmName;

    private RecyclerView dateRecyclerView;
    private List<String> dateList;
    private ImageView currentRemind;
    private TextView currentDate;
    private View currentView;

    private RecyclerView cinemaRecyclerView;
    private List<Cinema> cinemaList;

    private List<Schedule> scheduleList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);

        Intent intent = getIntent();
        filmNameString = intent.getStringExtra("filmName");
        filmName = findViewById(R.id.filmName);
        filmName.setText(filmNameString);

        dateRecyclerView = findViewById(R.id.DateRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        dateRecyclerView.setLayoutManager(linearLayoutManager);
        dateList = new ArrayList<>();
        dateList.add("今天 12-01");
        dateList.add("明天 12-02");
        dateList.add("后天 12-03");
        dateList.add("周四 12-04");
        dateList.add("周五 12-05");
        dateList.add("周六 12-06");
        dateList.add("周日 12-07");
        DateAdapter dateAdapter = new DateAdapter(dateList);
        dateRecyclerView.setAdapter(dateAdapter);

        cinemaRecyclerView = findViewById(R.id.CinemaRecyclerView);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this);
        cinemaRecyclerView.setLayoutManager(linearLayoutManager1);
        cinemaList = new ArrayList<>();
        Cinema cinema = new Cinema("杭州蓝钻时光影城", "江干区下沙听涛路157号源隆商业大厦2幢五楼", "NULL", "1.2km");
        cinemaList.add(cinema);
        cinemaList.add(cinema);
        cinemaList.add(cinema);
        cinemaList.add(cinema);
        cinemaList.add(cinema);
        cinemaList.add(cinema);
        CinemaAdapter cinemaAdapter = new CinemaAdapter(cinemaList);
        cinemaRecyclerView.setAdapter(cinemaAdapter);
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

            holder.dateView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (view != currentView) {
                        currentRemind.setVisibility(View.GONE);
                        currentDate.setTextColor(getResources().getColor(R.color.colorTextGray));
                        currentRemind = view.findViewById(R.id.remind);
                        currentDate = view.findViewById(R.id.date);
                        currentRemind.setVisibility(View.VISIBLE);
                        currentDate.setTextColor(getResources().getColor(R.color.colorRemind));
                        currentView = view;
                    }
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            String date = dateList.get(position);
            if (position == 0) {
                holder.date.setText(date);
                holder.date.setTextColor(getResources().getColor(R.color.colorRemind));
                holder.remind.setVisibility(View.VISIBLE);
                currentView = holder.dateView;
                currentRemind = holder.remind;
                currentDate = holder.date;
            } else {
                holder.date.setText(date);
            }
        }

        @Override
        public int getItemCount() {
            return dateList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            private TextView date;
            private ImageView remind;
            private View dateView;

            private ViewHolder(View view) {
                super(view);
                date = view.findViewById(R.id.date);
                remind = view.findViewById(R.id.remind);
                dateView = view.findViewById(R.id.dateView);
            }
        }
    }
    private class CinemaAdapter extends RecyclerView.Adapter<CinemaAdapter.ViewHolder> {
        private List<Cinema> cinemaList;

        private CinemaAdapter(List<Cinema> cinemaList) {
            this.cinemaList = cinemaList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.cinema_item, parent, false);
            final ViewHolder holder = new ViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Cinema cinema = cinemaList.get(position);
            holder.cinemaName.setText(cinema.getName());
            holder.distance.setText(cinema.getDistance());
            holder.position.setText(cinema.getPosition());
            LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getBaseContext());
            linearLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
            holder.scheduleRecyclerView.setLayoutManager(linearLayoutManager2);
            scheduleList = new ArrayList<>();
            scheduleList.add(new Schedule("09:30", "28元", "英语 3D"));
            scheduleList.add(new Schedule("10:35", "28元", "英语 3D"));
            scheduleList.add(new Schedule("11:25", "28元", "英语 3D"));
            scheduleList.add(new Schedule("11:25", "28元", "英语 3D"));
            scheduleList.add(new Schedule("11:25", "28元", "英语 3D"));
            scheduleList.add(new Schedule("11:25", "28元", "英语 3D"));
            scheduleList.add(new Schedule("11:25", "28元", "英语 3D"));
            ScheduleAdapter scheduleAdapter = new ScheduleAdapter(scheduleList);
            holder.scheduleRecyclerView.setAdapter(scheduleAdapter);
        }

        @Override
        public int getItemCount() {
            return cinemaList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            private TextView cinemaName;
            private TextView distance;
            private TextView position;
            private RecyclerView scheduleRecyclerView;
            private ViewHolder(View view) {
                super(view);
                cinemaName = view.findViewById(R.id.cinemaName);
                distance = view.findViewById(R.id.distance);
                position = view.findViewById(R.id.position);
                scheduleRecyclerView = view.findViewById(R.id.scheduleRecyclerView);
            }
        }
    }

    public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {

        public List<Schedule> scheduleList;

        public ScheduleAdapter(List<Schedule> scheduleList) {
            this.scheduleList = scheduleList;
        }
        @Override
        public ScheduleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.schedule_item, parent, false);
            final ViewHolder holder = new ViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(ScheduleAdapter.ViewHolder holder, int position) {
            Schedule schedule = scheduleList.get(position);
            holder.time.setText(schedule.getTime());
            holder.price.setText(schedule.getPrice());
            holder.type.setText(schedule.getType());
        }

        @Override
        public int getItemCount() {
            return scheduleList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            private TextView time;
            private TextView price;
            private TextView type;
            private ViewHolder(View view) {
                super(view);
                time = view.findViewById(R.id.time);
                price = view.findViewById(R.id.price);
                type = view.findViewById(R.id.type);
            }
        }
    }
}
