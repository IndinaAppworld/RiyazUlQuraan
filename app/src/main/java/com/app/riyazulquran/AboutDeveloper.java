package com.app.riyazulquran;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import com.animation.ActivityAnimator;

public class AboutDeveloper extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme_Dialog);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_about_developer);


        finish();
    }

    public void back(View v)
    {

        this.finish();
        try
        {
            ActivityAnimator anim = new ActivityAnimator();
            anim.getClass().getMethod("appearBottomRight" + "Animation", Activity.class).invoke(anim, this);
        }
        catch (Exception e) {

        }
    }

    @Override
    public void onBackPressed()
    {
        back(null);
    }

}
