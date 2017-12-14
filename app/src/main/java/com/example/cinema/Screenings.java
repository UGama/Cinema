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
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gama on 12/10/17.
 */

public class Screenings extends AppCompatActivity {
    private TextView cinemaName;
    private String cinemaNameString;

    private TextView cinemaName2;
    private TextView position;
    private String cinemaPosition;
    private TextView distance;
    private String cinemaDistance;

    private ObservableScrollView observableScrollView;

    private TextView filmName;
    private TextView filmName2;
    private String filmNameString;
    private TextView information;
    private TextView information2;
    private RecyclerView dateRecyclerView;
    private RecyclerView dateRecyclerView2;
    private List<String> dateList;
    private ImageView currentRemind;
    private TextView currentDate;
    private View currentView;
    private ImageView currentRemind2;
    private TextView currentDate2;
    private View currentView2;

    private RecyclerView screeningsRecyclerView;
    private List<Screening> screeningList;
    private ImageView cutLine0;
    private View midPanel;
    private View topPanel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screenings);

        Intent intent = getIntent();
        cinemaNameString = intent.getStringExtra("CinemaName");
        cinemaPosition = intent.getStringExtra("CinemaPosition");
        cinemaDistance = intent.getStringExtra("Distance");
        filmNameString = intent.getStringExtra("FilmName");

        cinemaName = findViewById(R.id.cinemaName);
        cinemaName2 = findViewById(R.id.cinemaName2);
        cinemaName.setText(cinemaNameString);
        cinemaName2.setText(cinemaNameString);

        position = findViewById(R.id.position);
        position.setText(cinemaPosition);
        distance = findViewById(R.id.distance);
        distance.setText(cinemaDistance);

        filmName = findViewById(R.id.filmName);
        filmName.setText(filmNameString);
        information = findViewById(R.id.information);
        information.setText("120分钟|动作|亨利·卡维尔 本·阿弗莱克 盖尔·加朵");

        dateRecyclerView = findViewById(R.id.dateRecyclerView);
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

        screeningsRecyclerView = findViewById(R.id.screeningsRecyclerView);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this);
        screeningsRecyclerView.setLayoutManager(linearLayoutManager1);
        screeningList = new ArrayList<>();
        screeningList.add(new Screening("11:50", "13:50 散场", "39元", "英语 3D"));
        screeningList.add(new Screening("11:50", "13:50 散场", "39元", "英语 3D"));
        screeningList.add(new Screening("11:50", "13:50 散场", "39元", "英语 3D"));
        screeningList.add(new Screening("11:50", "13:50 散场", "39元", "英语 3D"));
        screeningList.add(new Screening("11:50", "13:50 散场", "39元", "英语 3D"));
        screeningList.add(new Screening("11:50", "13:50 散场", "39元", "英语 3D"));
        screeningList.add(new Screening("11:50", "13:50 散场", "39元", "英语 3D"));
        screeningList.add(new Screening("11:50", "13:50 散场", "39元", "英语 3D"));
        ScreeningsAdapter screeningsAdapter = new ScreeningsAdapter(screeningList);
        screeningsRecyclerView.setAdapter(screeningsAdapter);

        midPanel = findViewById(R.id.midPanel);
        topPanel = findViewById(R.id.topPanel);
        filmName2 = topPanel.findViewById(R.id.filmName);
        information2 = topPanel.findViewById(R.id.information);
        dateRecyclerView2 = topPanel.findViewById(R.id.dateRecyclerView);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this);
        linearLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        dateRecyclerView2.setLayoutManager(linearLayoutManager2);
        dateRecyclerView2.setAdapter(dateAdapter);
        filmName2.setText(filmNameString);
        information2.setText("120分钟|动作|亨利·卡维尔 本·阿弗莱克 盖尔·加朵");

        observableScrollView = findViewById(R.id.scrollView);
        observableScrollView.setScrollViewListener(new ObservableScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(ScrollView scrollView, int x, int y, int oldX, int oldY) {
                int[] panelLocation = new int[2];
                midPanel.getLocationOnScreen(panelLocation);
                cutLine0 = findViewById(R.id.cutLine0);
                int midPanelX = panelLocation[0];
                int midPanelY = panelLocation[1];
                int statusBarHeight = getStatusBarHeight();
                if (midPanelY <= statusBarHeight + cutLine0.getBottom()) {
                    topPanel.setVisibility(View.VISIBLE);
                } else {
                    topPanel.setVisibility(View.GONE);
                }
            }
        });
    }
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
    private class DateAdapter extends RecyclerView.Adapter<DateAdapter.ViewHolder> {

        private List<String> dateList;

        private DateAdapter(List<String> dateList) {
            this.dateList = dateList;
        }

        @Override
        public DateAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.date_item, parent, false);
            final DateAdapter.ViewHolder holder = new DateAdapter.ViewHolder(view);

            holder.dateView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (view != currentView && view != currentView2) {
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
        public void onBindViewHolder(DateAdapter.ViewHolder holder, int position) {
            String date = dateList.get(position);
            if (position == 0) {
                holder.date.setText(date);
                holder.date.setTextColor(getResources().getColor(R.color.colorRemind));
                holder.remind.setVisibility(View.VISIBLE);
                holder.number = position;
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
            private int number;

            private ViewHolder(View view) {
                super(view);
                date = view.findViewById(R.id.date);
                remind = view.findViewById(R.id.remind);
                dateView = view.findViewById(R.id.dateView);
            }
        }
    }

    private class ScreeningsAdapter extends RecyclerView.Adapter<ScreeningsAdapter.ViewHolder> {
        private List<Screening> screeningList;

        private ScreeningsAdapter(List<Screening> screeningList) {
            this.screeningList = screeningList;
        }

        @Override
        public ScreeningsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.screenings_item, parent, false);
            final ScreeningsAdapter.ViewHolder holder = new ScreeningsAdapter.ViewHolder(view);
            holder.screeningView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(ScreeningsAdapter.ViewHolder holder, int position) {
            Screening screening = screeningList.get(position);
            holder.startTime.setText(screening.getStartTime());
            holder.endTime.setText(screening.getEndTime());
            holder.price.setText(screening.getPrice());
            holder.type.setText(screening.getType());
        }

        @Override
        public int getItemCount() {
            return screeningList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private TextView startTime;
            private TextView endTime;
            private TextView price;
            private TextView type;
            private View screeningView;

            private ViewHolder(View view) {
                super(view);
                startTime = view.findViewById(R.id.startTime);
                endTime = view.findViewById(R.id.endTime);
                price = view.findViewById(R.id.price);
                type = view.findViewById(R.id.type);
                screeningView = view.findViewById(R.id.screeningView);
            }
        }
    }
}
