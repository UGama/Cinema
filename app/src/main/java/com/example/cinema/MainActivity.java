package com.example.cinema;

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

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private posterPagerAdapter pagerAdapter;
    private List<Poster> posterList;
    private ViewPagerIndicator indicator;

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
        viewPager = findViewById(R.id.viewPager);
        pagerAdapter = new posterPagerAdapter(posterList);
        viewPager.setAdapter(pagerAdapter);
        indicator = findViewById(R.id.indicator);
        indicator.setLength(posterList.size());
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                indicator.setSelected(position);
                position = position % 4;
                /*switch (position) {
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

        find = findViewById(R.id.find);
        recommend = findViewById(R.id.recommend);
        mine = findViewById(R.id.mine);

        recommend.setBackgroundResource(R.drawable.recommend2);
        recommendText = findViewById(R.id.recommendText);
        recommendText.setTextColor(this.getResources().getColor(R.color.colorBase));
    }
    private class posterPagerAdapter extends android.support.v4.view.PagerAdapter {

        List<Poster> posterList = new ArrayList<>();

        public posterPagerAdapter(List<Poster> posterList) {
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
}
