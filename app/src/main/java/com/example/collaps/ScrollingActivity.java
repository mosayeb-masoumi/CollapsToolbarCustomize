package com.example.collaps;

import android.os.Bundle;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import static com.google.android.material.appbar.AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED;
import static com.google.android.material.appbar.AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL;

public class ScrollingActivity extends AppCompatActivity {
    AppBarLayout appBarLayout;

    RelativeLayout rl_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbar);
        toolBarLayout.setTitle(getTitle());


        appBarLayout = findViewById(R.id.app_bar);
        rl_toolbar = findViewById(R.id.rl_toolbar);


        TextView textView = findViewById(R.id.txtNested);




        // to prevent collapsing
        AppBarLayout.LayoutParams p = (AppBarLayout.LayoutParams) toolBarLayout.getLayoutParams();
        p.setScrollFlags(0);
        toolBarLayout.setLayoutParams(p);


        // to enable collapsing again
        AppBarLayout.LayoutParams p2 = (AppBarLayout.LayoutParams) toolBarLayout.getLayoutParams();
        p.setScrollFlags(SCROLL_FLAG_SCROLL| SCROLL_FLAG_EXIT_UNTIL_COLLAPSED);
        toolBarLayout.setLayoutParams(p2);



        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                if (Math.abs(verticalOffset)-appBarLayout.getTotalScrollRange() == 0)
                {
                    //  Collapsed
                    Log.i("TAG", "onOffsetChanged: ");
                    Toast.makeText(ScrollingActivity.this, "collapsed", Toast.LENGTH_SHORT).show();

                    rl_toolbar.setVisibility(View.VISIBLE);
                }
                else
                {
                    //Expanded

                    rl_toolbar.setVisibility(View.GONE);

                }
            }
        });








        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}