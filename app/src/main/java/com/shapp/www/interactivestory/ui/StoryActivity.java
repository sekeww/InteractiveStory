package com.shapp.www.interactivestory.ui;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.shapp.www.interactivestory.R;
import com.shapp.www.interactivestory.model.Page;
import com.shapp.www.interactivestory.model.Story;

import java.util.Stack;

public class StoryActivity extends AppCompatActivity {

    private static final String TAG = StoryActivity.class.getSimpleName();

    private String mName;
    private Story mStory;
    private ImageView mStoryImageView;
    private TextView mStoryTextView;
    private Button mChoice1Button;
    private Button mChoice2Button;
    private Stack<Integer> mPageStack = new Stack<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        mStoryImageView = (ImageView) findViewById(R.id.storyImageView);
        mStoryTextView = (TextView) findViewById(R.id.storyTextView);
        mChoice1Button = (Button) findViewById(R.id.choice1Button);
        mChoice2Button = (Button) findViewById(R.id.choice2Button);

        Intent intent = getIntent();
        mName = intent.getStringExtra(getString(R.string.key_name));
        Log.d(TAG, mName);

        mStory = new Story();
        loadPage(0);
    }

    private void loadPage(int pageNumber) {
        mPageStack.push(pageNumber);
        final Page page = mStory.getPage(pageNumber);

        Drawable image = ContextCompat.getDrawable(this, page.getImageId());
        mStoryImageView.setImageDrawable(image);

        String pageText = getString(page.getTextId());
        //Add name if placeholder included. Won't add if not
        pageText = String.format(pageText, mName);
        mStoryTextView.setText(pageText);

        if(page.isFinalPage()){
            mChoice1Button.setVisibility(View.INVISIBLE);
            mChoice2Button.setText(R.string.play_again_button_text);
            mChoice2Button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    loadPage(0);
                }
            });
        } else {
            loadButtons(page);
        }
    }

    private void loadButtons(final Page page) {
        mChoice1Button.setVisibility(View.VISIBLE);
        mChoice1Button.setText(page.getChoice1().getTextId());
        mChoice1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int nextPage = page.getChoice1().getNextPage();
                loadPage(nextPage);
            }
        });
        mChoice2Button.setVisibility(View.VISIBLE);
        mChoice2Button.setText(page.getChoice2().getTextId());
        mChoice2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int nextPage = page.getChoice2().getNextPage();
                loadPage(nextPage);
            }
        });
    }

    @Override
    public void onBackPressed() {
        mPageStack.pop();
        if(mPageStack.empty()) {
            super.onBackPressed();
        } else {
            loadPage(mPageStack.pop());
        }
    }
}
