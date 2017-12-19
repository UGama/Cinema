package com.example.cinema;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.GetCallback;
import com.avos.avoscloud.GetDataCallback;
import com.avos.avoscloud.ProgressCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Gama on 11/21/17.
 */

public class Find extends AppCompatActivity implements View.OnClickListener {

    private ImageView mine;
    private ImageView recommend;
    private ImageView find;
    private TextView findText;

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;

    private ConstraintLayout loadingLayout;
    private ImageView loadingCircle1;
    private ImageView loadingCircle2;
    private ImageView loadingCircle3;
    private AnimatorSet animatorSet0;
    private AnimatorSet animatorSet1;
    private AnimatorSet animatorSet2;
    private AnimatorSet animatorSet3;

    private List<Theme> themeList;
    private List<String> stringList;
    private List<String> urlList;
    private List<byte[]> byteList;
    private int SupportNumber;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);

        loadingLayout = findViewById(R.id.loadingLayout);
        loadingCircle1 = findViewById(R.id.loadingCircle1);
        loadingCircle2 = findViewById(R.id.loadingCircle2);
        loadingCircle3 = findViewById(R.id.loadingCircle3);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        themeList = new ArrayList<>();
        urlList = new ArrayList<>();
        byteList = new ArrayList<>();
        SupportNumber = 0;
        Loading();
        InitData();

        mine = findViewById(R.id.mine);
        mine.setOnClickListener(this);
        recommend = findViewById(R.id.recommend);
        recommend.setOnClickListener(this);
        find = findViewById(R.id.find);
        find.setBackgroundResource(R.drawable.find2);
        findText = findViewById(R.id.findText);
        findText.setTextColor(this.getResources().getColor(R.color.colorBase));

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
            holder.themeImage.setImageBitmap(theme.getBitmap());
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

    public void InitData() {

        stringList = new ArrayList<>();
        AVQuery<AVObject> avObjectAVQuery = new AVQuery<>("Theme");
        avObjectAVQuery.limit(5);
        avObjectAVQuery.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(final List<AVObject> list, AVException e) {
                for (final AVObject avObject : list) {
                    Log.i("avObject", avObject.getString("ThemeName"));
                    stringList.add(avObject.getString("ThemeName"));
                    Theme theme = new Theme(avObject.getString("ThemeName"),
                            avObject.getString("Subhead"));
                    themeList.add(theme);
                }
                getUrl();
            }
        });
    }

    public void getUrl() {
        SupportNumber = 0;
        Log.i("stringsList", String.valueOf(stringList.size()));
        for (int i = 0; i < stringList.size(); i++) {
            AVQuery<AVObject> avObjectAVQuery1 = new AVQuery<>("_File");
            avObjectAVQuery1.whereEqualTo("name", stringList.get(i) + ".jpg");
            avObjectAVQuery1.getFirstInBackground(new GetCallback<AVObject>() {
                @Override
                public void done(AVObject avObject1, AVException e) {
                    Log.i("url", avObject1.getString("url"));
                    urlList.add(avObject1.getString("url"));
                    SupportNumber++;
                    if (SupportNumber == stringList.size()) {
                        getBitmap();
                    }

                            /*if (integer == 100 && avObject == list.get(list.size() - 1)) {
                                Log.i("List", String.valueOf(list.size()));

                                });
                            }*/

                }
            });
        }

    }

    public void getBitmap() {
        SupportNumber = 0;
        for (int i = 0; i < urlList.size(); i++) {
            Log.i("Number", String.valueOf(i));
            SupportNumber++;
            AVFile avFile = new AVFile("ThemePic", urlList.get(i), new HashMap<String, Object>());
            if (SupportNumber != urlList.size()) {
                avFile.getDataInBackground(new GetDataCallback() {
                    @Override
                    public void done(byte[] bytes, AVException e) {
                        byteList.add(bytes);
                    }
                });
            } else {
                avFile.getDataInBackground(new GetDataCallback() {
                    @Override
                    public void done(byte[] bytes, AVException e) {
                        byteList.add(bytes);
                    }
                }, new ProgressCallback() {
                    @Override
                    public void done(Integer integer) {
                        if (integer == 100) {
                            getBitmap2();
                        }
                    }
                });
            }

        }
    }

    public void getBitmap2() {
        for (int i = 0; i < byteList.size(); i++) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(byteList.get(i), 0, byteList.get(i).length);
            loadingLayout.setVisibility(View.GONE);
            recyclerView = findViewById(R.id.recyclerView);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext());
            recyclerView.setLayoutManager(linearLayoutManager);
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
    }

    public void Loading() {
        loadingLayout.setVisibility(View.VISIBLE);

        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(loadingCircle1, "alpha", 1, 0);
        objectAnimator1.setStartDelay(600);
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(loadingCircle2, "alpha", 1, 0);
        objectAnimator2.setStartDelay(600);
        ObjectAnimator objectAnimator3 = ObjectAnimator.ofFloat(loadingCircle3, "alpha", 1, 0);
        objectAnimator3.setStartDelay(600);
        animatorSet0 = new AnimatorSet();
        animatorSet0.play(objectAnimator1).with(objectAnimator2).with(objectAnimator3);
        animatorSet0.start();
        animatorSet0.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                animatorSet1.start();
                animatorSet2.start();
                animatorSet3.start();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

        ObjectAnimator objectAnimator11 = ObjectAnimator.ofFloat(loadingCircle1, "alpha", 0, 1);
        objectAnimator11.setDuration(100);
        objectAnimator11.setStartDelay(300);
        ObjectAnimator objectAnimator12 = ObjectAnimator.ofFloat(loadingCircle1, "alpha", 1, 0);
        objectAnimator12.setDuration(100);
        objectAnimator12.setStartDelay(1900);
        animatorSet1 = new AnimatorSet();
        animatorSet1.play(objectAnimator12).after(objectAnimator11);
        animatorSet1.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                animatorSet1.start();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

        ObjectAnimator objectAnimator21 = ObjectAnimator.ofFloat(loadingCircle2, "alpha", 0, 1);
        objectAnimator21.setDuration(100);
        objectAnimator21.setStartDelay(1000);
        ObjectAnimator objectAnimator22 = ObjectAnimator.ofFloat(loadingCircle2, "alpha", 1, 0);
        objectAnimator22.setDuration(100);
        objectAnimator22.setStartDelay(1200);
        animatorSet2 = new AnimatorSet();
        animatorSet2.play(objectAnimator22).after(objectAnimator21);
        animatorSet2.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                animatorSet2.start();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

        ObjectAnimator objectAnimator31 = ObjectAnimator.ofFloat(loadingCircle3, "alpha", 0, 1);
        objectAnimator31.setDuration(100);
        objectAnimator31.setStartDelay(1700);
        ObjectAnimator objectAnimator32 = ObjectAnimator.ofFloat(loadingCircle3, "alpha", 1, 0);
        objectAnimator32.setDuration(100);
        objectAnimator32.setStartDelay(500);
        animatorSet3 = new AnimatorSet();
        animatorSet3.play(objectAnimator32).after(objectAnimator31);
        animatorSet3.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                animatorSet3.start();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }
}
