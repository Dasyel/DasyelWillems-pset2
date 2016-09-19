package com.dasyel.dasyelwillems_pset2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ShowTextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_text);

        Intent intent = getIntent();
        Story s = (Story) intent.getSerializableExtra(MainActivity.STORY_KEY);

        TextView tv = (TextView) findViewById(R.id.finalTextView);
        tv.setText(s.toString());
    }

    public void restart(View v){
        finish();
    }
}
