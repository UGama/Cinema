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
    private String date;

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
    private int supportNumber;
    private TextView date1;
    private TextView date2;
    private List<View> viewList1;
    private List<View> viewList2;

    private RecyclerView screeningsRecyclerView;
    private List<Screening> screeningList;
    private ImageView cutLine0;
    private View midPanel;
    private View topPanel;
    private ImageView cutLine00;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screenings);

        Intent intent = getIntent();
        cinemaNameString = intent.getStringExtra("CinemaName");
        cinemaPosition = intent.getStringExtra("CinemaPosition");
        cinemaDistance = intent.getStringExtra("Distance");
        filmNameString = intent.getStringExtra("FilmName");
        date = intent.getStringExtra("Date");

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
        date1 = findViewById(R.id.date);
        date1.setText(date);
        viewList1 = new ArrayList<>();
        viewList2 = new ArrayList<>();

        observableScrollView = findViewById(R.id.scrollView);
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
        cutLine0 = findViewById(R.id.cutLine0);
        cutLine00 = findViewById(R.id.cutLine00);
        filmName2 = topPanel.findViewById(R.id.filmName);
        information2 = topPanel.findViewById(R.id.information);
        date2 = topPanel.findViewById(R.id.date);
        date2.setText(date);

        filmName2.setText(filmNameString);
        information2.setText("120分钟|动作|亨利·卡维尔 本·阿弗莱克 盖尔·加朵");

        observableScrollView.setScrollViewListener(new ObservableScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(ScrollView scrollView, int x, int y, int oldX, int oldY) {
                int[] panelLocation = new int[2];
                midPanel.getLocationOnScreen(panelLocation);
                int midPanelY = panelLocation[1];
                int statusBarHeight = getStatusBarHeight();
                if (midPanelY <= statusBarHeight + cutLine0.getBottom()) {
                    topPanel.setVisibility(View.VISIBLE);
                    cutLine00.setVisibility(View.VISIBLE);
                } else {
                    topPanel.setVisibility(View.GONE);
                    cutLine00.setVisibility(View.GONE);
                }
            }
        });
        observableScrollView.smoothScrollTo(0, 20);
    }
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
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

    /*private class DateAdapter extends RecyclerView.Adapter<DateAdapter.ViewHolder> {

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
                        currentDate.setTextColor(getResources().getColor(R.color.colorTextGray));
                        currentRemind.setVisibility(View.GONE);
                        currentDate = view.findViewById(R.id.date);
                        currentRemind = view.findViewById(R.id.remind);
                        currentDate.setTextColor(getResources().getColor(R.color.colorRemind));
                        currentRemind.setVisibility(View.VISIBLE);
                        currentView = view;
                        currentRemind2.setVisibility(View.GONE);
                        currentDate2.setTextColor(getResources().getColor(R.color.colorTextGray));
                        for (int i = 0; i < viewList1.size(); i++) {
                            if (viewList1.get(i) == holder.dateView) {
                                supportNumber = i;
                                break;
                            }
                        }
                        Log.i("ViewList1", String.valueOf(viewList1.size()));
                        Log.i("Number", String.valueOf(supportNumber));
                        currentView2 = viewList2.get(supportNumber);
                        currentDate2 = currentView2.findViewById(R.id.date);
                        currentRemind2 = currentView2.findViewById(R.id.remind);
                        currentDate2.setTextColor(getResources().getColor(R.color.colorRemind));
                        currentRemind2.setVisibility(View.VISIBLE);
                    }
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(DateAdapter.ViewHolder holder, int position) {
            viewList1.add(holder.dateView);
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

    private class DateAdapter2 extends RecyclerView.Adapter<DateAdapter2.ViewHolder> {

        private List<String> dateList;

        private DateAdapter2(List<String> dateList) {
            this.dateList = dateList;
        }

        @Override
        public DateAdapter2.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.date_item, parent, false);
            final DateAdapter2.ViewHolder holder = new DateAdapter2.ViewHolder(view);

            holder.dateView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (view != currentView && view != currentView2) {
                        currentDate2.setTextColor(getResources().getColor(R.color.colorTextGray));
                        currentRemind2.setVisibility(View.GONE);
                        currentDate2 = view.findViewById(R.id.date);
                        currentRemind2 = view.findViewById(R.id.remind);
                        currentDate2.setTextColor(getResources().getColor(R.color.colorRemind));
                        currentRemind2.setVisibility(View.VISIBLE);
                        currentView2 = view;
                        currentDate.setTextColor(getResources().getColor(R.color.colorTextGray));
                        currentRemind.setVisibility(View.GONE);
                        currentDate2.setTextColor(getResources().getColor(R.color.colorRemind));
                        for (int i = 0; i < viewList2.size(); i++) {
                            if (viewList2.get(i) == holder.dateView) {
                                supportNumber = i;
                                break;
                            }
                        }
                        Log.i("ViewList2", String.valueOf(viewList2.size()));
                        Log.i("Number", String.valueOf(supportNumber));
                        currentView = viewList1.get(supportNumber);
                        currentDate = currentView.findViewById(R.id.date);
                        currentRemind = currentView.findViewById(R.id.remind);
                        currentDate.setTextColor(getResources().getColor(R.color.colorRemind));
                        currentRemind.setVisibility(View.VISIBLE);
                    }
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(DateAdapter2.ViewHolder holder, int position) {
            viewList2.add(holder.dateView);
            String date = dateList.get(position);
            if (position == 0) {
                holder.date.setText(date);
                holder.date.setTextColor(getResources().getColor(R.color.colorRemind));
                holder.remind.setVisibility(View.VISIBLE);
                currentView2 = holder.dateView;
                currentRemind2 = holder.remind;
                currentDate2 = holder.date;
                topPanel.setVisibility(View.GONE);
                cutLine00.setVisibility(View.GONE);
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
    }*/


}
