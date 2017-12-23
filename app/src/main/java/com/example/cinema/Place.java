package com.example.cinema;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;

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
    private List<Cinema> cinemaList2;
    private Cinema[] cinemas;

    private List<Schedule> scheduleList;

    private ConstraintLayout loadingLayout;
    private ImageView loadingCircle;
    private ObjectAnimator loading;
    private int SupportNumber = 0;

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

        cinemaList = new ArrayList<>();
        cinemaList2 = new ArrayList<>();

        loadingLayout = findViewById(R.id.loading);
        loadingCircle = findViewById(R.id.loadingCircle);
        loading = ObjectAnimator.ofFloat(loadingCircle, "rotation", 0, 360);
        loading.setDuration(1200);
        loading.setRepeatCount(-1);

        initCinemaData();
    }

    public void initCinemaData() {
        loadingLayout.setVisibility(View.VISIBLE);
        loading.start();
        Log.i("initCinemaData", "Start");
        cinemaList.clear();
        AVQuery<AVObject> query = new AVQuery<>("Cinema");
        query.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                Log.i("list", String.valueOf(list.size()));
                for (AVObject avObject : list) {
                    Log.i("Name", avObject.getString("Name"));
                    Log.i("PositionDescription", avObject.getString("PositionDescription"));
                    Cinema cinema = new Cinema(avObject.getString("Name"),
                            avObject.getString("PositionDescription"),
                            String.valueOf(avObject.getNumber("Distance")));
                    cinemaList.add(cinema);
                }
                Log.i("Cinema", "Add Success");
                cinemaRecyclerView = findViewById(R.id.CinemaRecyclerView);
                LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getBaseContext());
                cinemaRecyclerView.setLayoutManager(linearLayoutManager1);
                sort();
                CinemaAdapter cinemaAdapter = new CinemaAdapter(cinemaList2);
                cinemaRecyclerView.setAdapter(cinemaAdapter);
                loadingLayout.setVisibility(View.GONE);
                loading.end();
            }
        });
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
                        initCinemaData();
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
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TextView tempCinemaName = view.findViewById(R.id.cinemaName);
                    String cinemaName = tempCinemaName.getText().toString();
                    TextView tempCinemaPosition = view.findViewById(R.id.position);
                    String cinemaPosition = tempCinemaPosition.getText().toString();
                    TextView tempDistance = view.findViewById(R.id.distance);
                    String distance = tempDistance.getText().toString();
                    Intent intent = new Intent(Place.this, Screenings.class);
                    intent.putExtra("CinemaName", cinemaName);
                    intent.putExtra("CinemaPosition", cinemaPosition);
                    intent.putExtra("Distance", distance);
                    intent.putExtra("FilmName", filmNameString);
                    intent.putExtra("Date", currentDate.getText().toString());
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Cinema cinema = cinemaList.get(position);
            holder.cinemaName.setText(cinema.getName());
            holder.distance.setText(cinema.getDistance() + "km");
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
            private View itemView;
            private ViewHolder(View view) {
                super(view);
                cinemaName = view.findViewById(R.id.cinemaName);
                distance = view.findViewById(R.id.distance);
                position = view.findViewById(R.id.position);
                scheduleRecyclerView = view.findViewById(R.id.scheduleRecyclerView);
                itemView = view.findViewById(R.id.cinemaItemView);
            }
        }
    }

    public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder>
    {

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

    public void sort() {
        cinemaList2.clear();
        Log.i("cinemaList2.size()", String.valueOf(cinemaList2.size()));
        cinemas = new Cinema[cinemaList.size()];
        for (int i = 0; i < cinemaList.size(); i++) {
            cinemas[i] = cinemaList.get(i);
        }
        for (int i = 0; i < cinemaList.size() - 1; i++) {
            Double min = Double.valueOf(cinemas[i].getDistance());
            int temp = i;
            for (int j = i + 1; j < cinemaList.size(); j++) {
                Cinema cinema = cinemas[j];
                if (Double.valueOf(cinema.getDistance()) < min) {
                    temp = j;
                    min = Double.valueOf(cinema.getDistance());
                }
            }
            Cinema cinemaTemp = cinemas[i];
            cinemas[i] = cinemas[temp];
            cinemas[temp] = cinemaTemp;
            cinemaList2.add(cinemas[i]);
        }

    }
}
