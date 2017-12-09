package com.example.cinema;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Gama on 11/18/17.
 */

public class Mine extends AppCompatActivity implements View.OnClickListener {

    private Button mine;
    private TextView mineText;
    private Button recommend;
    private Button find;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);

        mine = findViewById(R.id.mine);
        mineText = findViewById(R.id.mineText);
        mine.setBackgroundResource(R.drawable.mine2);
        mineText.setTextColor(this.getResources().getColor(R.color.colorBase));

        recommend = findViewById(R.id.recommend);
        recommend.setOnClickListener(this);
        find = findViewById(R.id.find);
        find.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.recommend:
                Intent intent1 = new Intent(Mine.this, MainActivity.class);
                startActivity(intent1);
                overridePendingTransition(0, 0);
                break;
            case R.id.find:
                Intent intent2 = new Intent(Mine.this, Find.class);
                startActivity(intent2);
                overridePendingTransition(0, 0);
                break;
        }
    }
}
