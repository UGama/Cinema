package com.example.cinema;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.GetCallback;
import com.avos.avoscloud.GetDataCallback;

import java.util.ArrayList;
import java.util.HashMap;
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

    private ConstraintLayout loadingLayout;
    private ImageView loadingCircle1;
    private ImageView loadingCircle2;
    private ImageView loadingCircle3;
    private AnimatorSet animatorSet1;
    private AnimatorSet animatorSet2;
    private AnimatorSet animatorSet3;

    private List<Film> filmList;
    private List<String> urlList;
    private List<Bitmap> bitmapList;

    private List<String> supportList1;
    private List<String> supportList2;
    private List<String> supportList3;

    private int SupportNumber;
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

        find = findViewById(R.id.find);
        find.setOnClickListener(this);
        recommend = findViewById(R.id.recommend);
        mine = findViewById(R.id.mine);
        mine.setOnClickListener(this);

        recommend.setBackgroundResource(R.drawable.recommend2);
        recommendText = findViewById(R.id.recommendText);
        recommendText.setTextColor(this.getResources().getColor(R.color.colorBase));

        loadingLayout = findViewById(R.id.loadingLayout);
        loadingCircle1 = findViewById(R.id.loadingCircle1);
        loadingCircle2 = findViewById(R.id.loadingCircle2);
        loadingCircle3 = findViewById(R.id.loadingCircle3);

        filmList = new ArrayList<>();
        urlList = new ArrayList<>();
        bitmapList = new ArrayList<>();
        SupportNumber = 0;

        Loading();
        initData();
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
        AVQuery<AVObject> query = new AVQuery<>("ScheduleMap");
        query.whereEqualTo("StartTime", "9:30");
        query.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                for (AVObject avObject : list) {
                    AVObject avObject1 = AVObject.createWithoutData("ScheduleMap", avObject.getObjectId());
                    avObject1.put("StartTime", "09:30");
                    avObject1.saveInBackground();
                }
            }
        });
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
       /* Cinema cinema = new Cinema("聚空间私人影院HEY JUICE茶桔便奶茶（下沙宝龙店）", "江干区下沙宝龙二期31幢103（永辉超市向北100米", 0.9);
        Cinema cinema1 = new Cinema("YHOUSE专属影院私人影院", "江干区金沙湖1号万亚生活广场地下一层海品码头—2号（万亚美食街，天街）", 5.9);
        Cinema cinema2 = new Cinema("来吧私人影院", "江干区下沙学源街1158号文创大厦711室（浙江传媒学院100米）", 5.4);
        Cinema cinema3 = new Cinema("爆米花影院酒店（杭州高沙商业街店）", "江干区学林街新元金沙城1261—5号", 5.7);
        Cinema cinema4 = new Cinema("品锐创意酒店", "江干区下沙天城东路77号创意大厦A座（东沙商业街东50米", 5.4);
        Cinema cinema5 = new Cinema("疯潮主题日租房·暮光星辰轰趴馆", "江干区天城东路创意大厦B座（杭州东沙商业中心东侧）", 5.4);
        Cinema cinema6 = new Cinema("疯潮主题日租房·工业心脏轰趴馆", "江干区天城东路创意大厦B座（东沙商业中心东侧）", 5.4);
        Cinema cinema7 = new Cinema("onego别墅轰趴日租房", "江干区宋都东郡国际家湾南门对面", 1.6);
        Cinema cinema8 = new Cinema("水草之家日租公寓", "江干区伊萨卡国际小区内", 2.4);
        List<Cinema> cinemas = new ArrayList<>();
        cinemas.add(cinema);
        cinemas.add(cinema2);
        cinemas.add(cinema3);
        cinemas.add(cinema4);
        cinemas.add(cinema5);
        cinemas.add(cinema6);
        cinemas.add(cinema7);
        cinemas.add(cinema8);
        cinemas.add(cinema1);
        for (Cinema cinema0 : cinemas) {
            AVObject avObject = new AVObject("Cinema");
            avObject.put("Name", cinema0.getName());
            avObject.put("PositionDescription", cinema0.getPosition());
            avObject.put("Distance", cinema0.getDistance());
            avObject.saveInBackground();
        }*/
        /*supportList1 = new ArrayList<>();
        supportList2 = new ArrayList<>();
        supportList3 = new ArrayList<>();
        supportList2.add("9:30");
        supportList2.add("10:35");
        supportList2.add("11:20");
        supportList2.add("14:55");
        supportList2.add("16:20");
        supportList2.add("18:50");
        supportList2.add("19:50");
        supportList2.add("21:20");
        supportList3.add("01-01");
        supportList3.add("01-02");
        supportList3.add("01-03");
        supportList3.add("01-04");
        supportList3.add("01-05");
        supportList3.add("01-06");
        supportList3.add("01-07");
        AVQuery<AVObject> avObjectAVQuery = new AVQuery<>("Cinema");
        avObjectAVQuery.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                for (AVObject avObject : list) {
                    Log.i("CinemaName", avObject.getString("Name"));
                    supportList1.add(avObject.getString("Name"));
                }
                for (int i = 0; i < supportList1.size(); i++) {
                    for (int j = 0; j < filmList.size(); j++) {
                        for (int l = 0; l < supportList2.size(); l++) {
                            for (int m = 0; m < supportList3.size(); m++) {
                                AVObject avObject = new AVObject("ScheduleMap");
                                avObject.put("Cinema", supportList1.get(i));
                                avObject.put("Film", filmList.get(j).getName());
                                avObject.put("StartTime", supportList2.get(l));
                                avObject.put("Date", supportList3.get(m));
                                avObject.put("Price", "39");
                                avObject.put("Type", "英语3D");

                                avObject.saveInBackground();
                            }
                        }

                    }
                }
            }
        });
*/

    }

    public void Loading() {
        Log.i("Loading", "Start");
        loadingLayout.setVisibility(View.VISIBLE);

        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(loadingCircle1, "alpha", 1, 0);
        objectAnimator1.setStartDelay(600);
        objectAnimator1.start();
        objectAnimator1.addListener(new Animator.AnimatorListener() {
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
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(loadingCircle2, "alpha", 1, 0);
        objectAnimator2.setStartDelay(600);
        objectAnimator2.start();
        objectAnimator2.addListener(new Animator.AnimatorListener() {
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
        ObjectAnimator objectAnimator3 = ObjectAnimator.ofFloat(loadingCircle3, "alpha", 1, 0);
        objectAnimator3.setStartDelay(600);
        objectAnimator3.start();
        objectAnimator3.addListener(new Animator.AnimatorListener() {
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

    public void initData() {
        Log.i("initDate", "Start");
        AVQuery<AVObject> query = new AVQuery<>("Week");
        query.limit(3);
        query.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                for (AVObject avObject : list) {
                    Log.i("Film", avObject.getString("Name"));
                    Film film = new Film(avObject.getString("Name"));
                    filmList.add(film);
                }
                getUrl();
            }
        });
    }

    public void getUrl() {
        Log.i("SupportNumber", String.valueOf(SupportNumber));
        AVQuery<AVObject> query = new AVQuery<>("_File");
        Log.i("Name", filmList.get(SupportNumber).getName());
        query.whereEqualTo("name", filmList.get(SupportNumber++).getName() + ".jpg");
        query.getFirstInBackground(new GetCallback<AVObject>() {
            @Override
            public void done(AVObject avObject, AVException e) {
                Log.i("url", avObject.getString("url"));
                urlList.add(avObject.getString("url"));
                if (SupportNumber == 3) {
                    SupportNumber = 0;
                    getBitmap();

                } else {
                    getUrl();
                }
            }
        });
    }

    public void getBitmap() {
        AVFile avFile = new AVFile("WeeklyFilmPic", urlList.get(SupportNumber++), new HashMap<String, Object>());
        avFile.getDataInBackground(new GetDataCallback() {
            @Override
            public void done(byte[] bytes, AVException e) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                bitmapList.add(bitmap);
                Log.i("SupportNumber2", String.valueOf(SupportNumber));
                if (SupportNumber == 3) {
                    SupportNumber = 0;
                    setBitmap();
                } else {
                    getBitmap();
                }
            }
        });
    }

    public void setBitmap() {
        film1 = findViewById(R.id.film1);
        film1.setOnClickListener(this);
        film1.setImageBitmap(bitmapList.get(SupportNumber++));
        film2 = findViewById(R.id.film2);
        film2.setOnClickListener(this);
        film2.setImageBitmap(bitmapList.get(SupportNumber++));
        film3 = findViewById(R.id.film3);
        film3.setOnClickListener(this);
        film3.setImageBitmap(bitmapList.get(SupportNumber));
        SupportNumber = 0;
        filmText1 = findViewById(R.id.film1Text);
        filmText1.setText(filmList.get(SupportNumber++).getName());
        filmText2 = findViewById(R.id.film2Text);
        filmText2.setText(filmList.get(SupportNumber++).getName());
        filmText3 = findViewById(R.id.film3Text);
        filmText3.setText(filmList.get(SupportNumber).getName());
        SupportNumber = 0;
        animatorSet1.end();
        animatorSet2.end();
        animatorSet3.end();
        loadingLayout.setVisibility(View.GONE);
        CloudServerOperate();
    }

}
