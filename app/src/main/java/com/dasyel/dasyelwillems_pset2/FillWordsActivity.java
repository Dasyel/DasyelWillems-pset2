package com.dasyel.dasyelwillems_pset2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;

public class FillWordsActivity extends AppCompatActivity {
    private final String STRING_ARRAY_KEY = "com.dasyel.dasyelwillems_pset2.STRING_ARRAY";
    private final String FOCUSED_KEY = "com.dasyel.dasyelwillems_pset2.FOCUSED";
    Story s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_words);

        Intent intent = getIntent();
        s = (Story) intent.getSerializableExtra(MainActivity.STORY_KEY);

        LinearLayout container = (LinearLayout) findViewById(R.id.editTextBox);
        for(int i = 0; i < s.getPlaceholderRemainingCount(); i++){
            EditText et = new EditText(this);
            et.setHint(s.getPlaceholders()[i]);
            container.addView(et);
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(MainActivity.STORY_KEY, s);

        LinearLayout container = (LinearLayout) findViewById(R.id.editTextBox);
        ArrayList<String> sal = new ArrayList<>();
        for(int i = 0; i < container.getChildCount(); i++){
            EditText et = (EditText) container.getChildAt(i);
            String text = et.getText().toString();
            sal.add(text);
            if(et.hasFocus()){
                outState.putInt(FOCUSED_KEY, i);
            }
        }
        outState.putStringArrayList(STRING_ARRAY_KEY, sal);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        s = (Story) savedInstanceState.getSerializable(MainActivity.STORY_KEY);

        LinearLayout container = (LinearLayout) findViewById(R.id.editTextBox);
        ArrayList<String> sal = savedInstanceState.getStringArrayList(STRING_ARRAY_KEY);
        int focusedIndex = savedInstanceState.getInt(FOCUSED_KEY, -1);
        for(int i = 0; i < container.getChildCount(); i++){
            EditText et = (EditText) container.getChildAt(i);
            et.setText(sal.get(i));
            if(i == focusedIndex){
                et.requestFocus();
            }
        }
    }

    public void nextActivity(View v){
        LinearLayout container = (LinearLayout) findViewById(R.id.editTextBox);
        for(int i = 0; i < container.getChildCount(); i++){
            EditText et = (EditText) container.getChildAt(i);
            String text = et.getText().toString();
            if(text.trim().isEmpty()){
                Toast.makeText(this, R.string.not_filled_message, Toast.LENGTH_SHORT).show();
                s.clear();
                return;
            }
            s.fillInPlaceholder(text.trim());
        }
        Intent intent = new Intent(this, ShowTextActivity.class);
        intent.putExtra(MainActivity.STORY_KEY, s);
        startActivity(intent);
        finish();
    }
}
