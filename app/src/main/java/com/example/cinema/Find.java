package com.example.cinema;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gama on 11/21/17.
 */

public class Find extends AppCompatActivity implements View.OnClickListener {

    private Button mine;
    private Button recommend;
    private Button find;
    private TextView findText;

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);

        mine = findViewById(R.id.mine);
        mine.setOnClickListener(this);
        recommend = findViewById(R.id.recommend);
        recommend.setOnClickListener(this);
        find = findViewById(R.id.find);
        find.setBackgroundResource(R.drawable.find2);
        findText = findViewById(R.id.findText);
        findText.setTextColor(this.getResources().getColor(R.color.colorBase));

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);

        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        List<String> stringList = new ArrayList<>();
        Theme theme = new Theme(R.drawable.themetest1, "小人物也有大梦想", "别不服，我们'低端人物'的人生也同样精彩", stringList);
        Theme theme1 = new Theme(R.drawable.themetest2, "暖暖的，很贴心", "冬日治愈系电影，有它们我再也不怕冷啦", stringList);
        List<Theme> themeList = new ArrayList<>();
        themeList.add(theme);
        themeList.add(theme1);
        final ThemeAdapter themeAdapter = new ThemeAdapter(themeList);
        recyclerView.setAdapter(themeAdapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                themeAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.recommend:
                Intent intent1 = new Intent(Find.this, MainActivity.class);
                startActivity(intent1);
                overridePendingTransition(0, 0);
                break;
            case R.id.mine:
                Intent intent2 = new Intent(Find.this, Mine.class);
                startActivity(intent2);
                overridePendingTransition(0, 0);
                break;
        }
    }

    private class ThemeAdapter extends RecyclerView.Adapter<ThemeAdapter.ViewHolder> {
        private List<Theme> themeList;

        private ThemeAdapter(List<Theme> themeList) {
            this.themeList = themeList;
        }

        @Override
        public ThemeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.find_item, parent, false);
            final ViewHolder holder = new ViewHolder(view);
            return holder;
        }
        @Override
        public void onBindViewHolder(ThemeAdapter.ViewHolder holder, int position) {
            Theme theme = themeList.get(position);
            holder.themeImage.setBackgroundResource(themeList.get(position).getImageId());
            holder.title.setText(theme.getTitle());
            holder.subhead.setText(theme.getSubhead());
            LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getBaseContext());
            linearLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
            holder.filmsList.setLayoutManager(linearLayoutManager1);
            Film filmTest = new Film(R.drawable.film11, "test", "test");
            Film filmTest1 = new Film(R.drawable.film11, "test", "test");
            Film filmTest2 = new Film(R.drawable.film11, "test", "test");
            Film filmTest3 = new Film(R.drawable.film11, "test", "test");
            List<Film> films = new ArrayList<>();
            films.add(filmTest);
            films.add(filmTest1);
            films.add(filmTest2);
            films.add(filmTest3);
            FilmAdapter filmAdapter = new FilmAdapter(films);
            holder.filmsList.setAdapter(filmAdapter);
        }

        @Override
        public int getItemCount() {
            return themeList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            ImageView themeImage;
            TextView title;
            TextView subhead;
            RecyclerView filmsList;

            private ViewHolder(View view) {
                super(view);
                themeImage = view.findViewById(R.id.themePic);
                title = view.findViewById(R.id.title);
                subhead = view.findViewById(R.id.subhead);
                filmsList = view.findViewById(R.id.filmsList);
            }
        }
    }

    private class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.ViewHolder2> {
        private List<Film> filmList;

        private FilmAdapter(List<Film> filmList) {
            this.filmList= filmList;
        }

        @Override
        public FilmAdapter.ViewHolder2 onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.find_item_item, parent, false);
            final ViewHolder2 holder2 = new ViewHolder2(view);
            return holder2;
        }
        @Override
        public void onBindViewHolder(FilmAdapter.ViewHolder2 holder2, int position) {
            Film film = filmList.get(position);
            holder2.FilmImage.setBackgroundResource(film.getSourceId());
            holder2.FilmName.setText(" " + film.getName() + " ");
            holder2.Subhead.setText(" " + film.getSubhead() + " ");
        }

        @Override
        public int getItemCount() {
            return filmList.size();
        }

        class ViewHolder2 extends RecyclerView.ViewHolder {

            ImageView FilmImage;
            TextView FilmName;
            TextView Subhead;

            private ViewHolder2(View view) {
                super(view);
                FilmImage = view.findViewById(R.id.filmPoster);
                FilmName = view.findViewById(R.id.filmName);
                Subhead = view.findViewById(R.id.subhead);
            }

        }
    }
}
