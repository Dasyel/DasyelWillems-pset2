package com.dasyel.dasyelwillems_pset2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public final static String STORY_KEY = "com.dasyel.dasyelwillems_pset2.STORY";
    Integer[] storyResources = {
            R.raw.madlib0_simple,
            R.raw.madlib1_tarzan,
            R.raw.madlib2_university,
            R.raw.madlib3_clothes,
            R.raw.madlib4_dance
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void start(View v){
        Integer storyRes = storyResources[new Random().nextInt(storyResources.length)];
        InputStream is = getResources().openRawResource(storyRes);
        Story s = new Story(is);

        Intent intent = new Intent(this, FillWordsActivity.class);
        intent.putExtra(STORY_KEY, s);
        startActivity(intent);
    }
}
