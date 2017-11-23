package com.example.cinema;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Gama on 11/21/17.
 */

public class Find extends AppCompatActivity implements View.OnClickListener {

    private Button mine;
    private Button recommend;
    private Button find;
    private TextView findText;

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
}
