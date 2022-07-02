package com.app.riyazulquran;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import com.animation.ActivityAnimator;

public class VisitWebsite extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme_Dialog);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_visit_website);
        findViewById(R.id.browser1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                visitWeb("http://www.aajkasabaq.in");
            }
        });
        findViewById(R.id.browser2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                visitWeb("http://www.bayans.org");
            }
        });
        findViewById(R.id.browser3).setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                visitWeb("http://www.ibnekaseer.net");
            }
        });

        findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back(v);
            }
        });
    }
public void visitWeb(String url)
{
    Intent intent=new Intent(Intent.ACTION_VIEW);
    intent.setData(Uri.parse(url));
    startActivity(intent);


}

    public void back(View v)
    {

        this.finish();
        try
        {
            ActivityAnimator anim = new ActivityAnimator();
            anim.getClass().getMethod("appearBottomRight" + "Animation", Activity.class)
                    .invoke(anim, this);
        }
        catch (Exception e)
        {
System.out.println("Exception in back option"+e.getMessage());
        }
    }

    @Override
    public void onBackPressed()
    {
        back(null);
    }



}
