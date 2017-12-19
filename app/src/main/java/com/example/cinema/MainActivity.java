package com.example.cinema;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager viewPager;
    private PosterPagerAdapter posterPagerAdapter;
    private List<Poster> posterList;
    private ViewPagerIndicator indicator;

    private ImageView film1;
    private ImageView film2;
    private ImageView film3;
    private TextView filmText1;
    private TextView filmText2;
    private TextView filmText3;

    private Button find;
    private Button recommend;
    private Button mine;
    private TextView recommendText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        posterList = new ArrayList<>();
        posterList.add(new Poster(R.drawable.adtest));
        posterList.add(new Poster(R.drawable.adtest));
        posterList.add(new Poster(R.drawable.adtest));
        posterList.add(new Poster(R.drawable.adtest));
        posterList.add(new Poster(R.drawable.adtest));
        posterList.add(new Poster(R.drawable.adtest));
        viewPager = findViewById(R.id.viewPager);
        posterPagerAdapter = new PosterPagerAdapter(posterList);
        viewPager.setAdapter(posterPagerAdapter);
        indicator = findViewById(R.id.indicator);
        indicator.setLength(posterList.size());
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                indicator.setSelected(position);
                /*position = position % 4;
                switch (position) {
                    case 0:
                        Item.setImageResource(v1.SourceId1);
                        Bag_Pic.setImageResource(v1.SourceId2);
                        Item_name.setText(" ? ? ? ");
                        PagesCount = 1;
                        break;
                    case 1:
                        Item.setImageResource(v2.SourceId1);
                        Bag_Pic.setImageResource(v2.SourceId2);
                        Item_name.setText(" ? ? ? ");
                        PagesCount = 2;
                        break;
                    case 2:
                        Item.setImageResource(v3.SourceId1);
                        Bag_Pic.setImageResource(v3.SourceId2);
                        Item_name.setText(" ? ? ? ");
                        PagesCount = 3;
                        break;
                    case 3:
                        Item.setImageResource(v4.SourceId1);
                        Bag_Pic.setImageResource(v4.SourceId2);
                        Item_name.setText(" ? ? ? ");
                        PagesCount = 4;
                        break;
                }*/
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        film1 = findViewById(R.id.film1);
        film1.setOnClickListener(this);
        film2 = findViewById(R.id.film2);
        film2.setOnClickListener(this);
        film3 = findViewById(R.id.film3);
        film3.setOnClickListener(this);
        filmText1 = findViewById(R.id.film1Text);
        filmText2 = findViewById(R.id.film2Text);
        filmText3 = findViewById(R.id.film3Text);

        find = findViewById(R.id.find);
        find.setOnClickListener(this);
        recommend = findViewById(R.id.recommend);
        mine = findViewById(R.id.mine);
        mine.setOnClickListener(this);

        recommend.setBackgroundResource(R.drawable.recommend2);
        recommendText = findViewById(R.id.recommendText);
        recommendText.setTextColor(this.getResources().getColor(R.color.colorBase));

        CloudServerOperate();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.find:
                Intent intent1 = new Intent(MainActivity.this, Find.class);
                startActivity(intent1);
                overridePendingTransition(0, 0);
                break;
            case R.id.mine:
                Intent intent2 = new Intent(MainActivity.this, Mine.class);
                startActivity(intent2);
                overridePendingTransition(0, 0);
                break;
            case R.id.film1:
                Intent intent3 = new Intent(MainActivity.this, Place.class);
                intent3.putExtra("filmName", filmText1.getText().toString());
                startActivity(intent3);
                break;
            case R.id.film2:
                Intent intent4 = new Intent(MainActivity.this, Place.class);
                intent4.putExtra("filmName", filmText2.getText().toString());
                startActivity(intent4);
                break;
            case R.id.film3:
                Intent intent5 = new Intent(MainActivity.this, Place.class);
                intent5.putExtra("filmName", filmText3.getText().toString());
                startActivity(intent5);
                break;
        }
    }

    private class PosterPagerAdapter extends android.support.v4.view.PagerAdapter {

        List<Poster> posterList = new ArrayList<>();

        public PosterPagerAdapter(List<Poster> posterList) {
            this.posterList = posterList;
        }

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            position = position % posterList.size();
            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.poster_item, null);
            ImageView poster = view.findViewById(R.id.poster);
            poster.setImageResource(posterList.get(position).getSourceId());
            /*switch (List.get(position).Number) {
                case 1:
                    List<OwnItem> ownItems1 = new ArrayList<>();
                    for (OwnItem ownItem : ownItems) {
                        if (ownItem.getType() == 1) {
                            ownItems1.add(ownItem);
                        }
                    }
                    LinearLayoutManager layoutManager1 = new LinearLayoutManager(Bag.this);
                    recyclerView.setLayoutManager(layoutManager1);
                    ItemAdapter adapter1 = new ItemAdapter(ownItems1);
                    recyclerView.setAdapter(adapter1);
                    break;
                case 2:
                    List<OwnItem> ownItems2 = new ArrayList<>();
                    for (OwnItem ownItem : ownItems) {
                        if (ownItem.getType() == 2) {
                            ownItems2.add(ownItem);
                        }
                    }
                    LinearLayoutManager layoutManager2 = new LinearLayoutManager(Bag.this);
                    recyclerView.setLayoutManager(layoutManager2);
                    ItemAdapter adapter2 = new ItemAdapter(ownItems2);
                    recyclerView.setAdapter(adapter2);
                    break;
                case 3:
                    List<OwnItem> ownItems3 = new ArrayList<>();
                    for (OwnItem ownItem : ownItems) {
                        if (ownItem.getType() == 3) {
                            ownItems3.add(ownItem);
                        }
                    }
                    LinearLayoutManager layoutManager3 = new LinearLayoutManager(Bag.this);
                    recyclerView.setLayoutManager(layoutManager3);
                    ItemAdapter adapter3 = new ItemAdapter(ownItems3);
                    recyclerView.setAdapter(adapter3);
                    break;
                case 4:
                    List<OwnItem> ownItems4 = new ArrayList<>();
                    for (OwnItem ownItem : ownItems) {
                        if (ownItem.getType() == 4) {
                            ownItems4.add(ownItem);
                        }
                    }
                    LinearLayoutManager layoutManager4 = new LinearLayoutManager(Bag.this);
                    recyclerView.setLayoutManager(layoutManager4);
                    ItemAdapter adapter4 = new ItemAdapter(ownItems4);
                    recyclerView.setAdapter(adapter4);
                    break;
            }*/
            container.addView(view);
            return view;
        }
    }
    public void CloudServerOperate() {
        /*final List<AVObject> avObjectList = new ArrayList<>();
        AVObject Film1 = new AVObject("Film");// 学生 Tom
        Film1.put("Name", "东方快车谋杀案");
        Film1.put("Subhead", "大侦探波洛巧破东方快车谜案");
        avObjectList.add(Film1);
        AVObject Film2 = new AVObject("Film");// 学生 Tom
        Film2.put("Name", "死侍2");
        Film2.put("Subhead", "嘴碎的蜘蛛侠电影");
        avObjectList.add(Film2);
        AVObject Film3 = new AVObject("Film");// 学生 Tom
        Film3.put("Name", "牌皇");
        Film3.put("Subhead", "肌肉版卡牌");
        avObjectList.add(Film3);
        AVObject Film4 = new AVObject("Film");// 学生 Tom
        Film4.put("Name", "阿凡达2");
        Film4.put("Subhead", "人类重返潘多拉星球");
        avObjectList.add(Film4);
        AVObject Film5 = new AVObject("Film");// 学生 Tom
        Film5.put("Name", "移动迷宫：死亡解药");
        Film5.put("Subhead", "移动迷宫系列影片的第三部");
        avObjectList.add(Film5);

        AVQuery<AVObject> avQuery = new AVQuery<>("Theme");
        avQuery.getInBackground("5a33998cd50eee007872a2c1", new GetCallback<AVObject>() {
            @Override
            public void done(AVObject avObject, AVException e) {
                for (AVObject avObject0 : avObjectList) {
                    AVObject ThemeFilmMap = new AVObject("ThemeFilmMap");
                    ThemeFilmMap.put("Film", avObject0);
                    ThemeFilmMap.put("Theme", avObject);
                    ThemeFilmMap.saveInBackground();
                }
            }
        });*/


    }
}
