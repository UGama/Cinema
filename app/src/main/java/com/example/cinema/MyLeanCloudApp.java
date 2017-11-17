package com.example.cinema;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;

/**
 * Created by Gama on 11/16/17.
 */

public class MyLeanCloudApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // 初始化参数依次为 this, AppId, AppKey
        AVOSCloud.initialize(this,"sCF9Y7kN6PEPPxecbe3pKOcR-gzGzoHsz","PNpugGJ2vXQYUNyGgPS2r8d8");
    }
}
