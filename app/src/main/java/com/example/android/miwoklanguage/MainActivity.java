package com.example.android.miwoklanguage;

import android.media.AudioManager;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        //to remove the shade under the action bar
        getSupportActionBar().setElevation(0);

        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        MiwokFragmentAdapter adapter = new MiwokFragmentAdapter(getSupportFragmentManager(), MainActivity.this);
        viewPager.setAdapter(adapter);

        TabLayout myTabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        myTabLayout.setupWithViewPager(viewPager);

//        String [] words = new String[10];
//        words[0] = "One";
//        words[1] = "Two";
//        words[2] = "Three";
//        words[3] = "Four";
//        words[4] = "Five";
//        words[5] = "Six";
//        words[6] = "Seven";
//        words[7] = "Eight";
//        words[8] = "Nine";
//        words[9] = "Ten";

//        OR simply
//        String [] words = new String[]{"one","two","three","four","five","six","seven","eight","nine","ten"};
    }

}