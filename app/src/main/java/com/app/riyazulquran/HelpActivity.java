package com.app.riyazulquran;

import android.app.Activity;
import android.content.res.Configuration;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.animation.ActivityAnimator;
import com.utils.Constants;

public class HelpActivity extends Activity {
    WebView mWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme_Dialog);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_help);
        loadData();;

        findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back(v);
            }
        });
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
    public void loadData()
    {
        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.getSettings().setJavaScriptEnabled(true);

        mWebView.getSettings().setUseWideViewPort(false);

        mWebView.getSettings().setRenderPriority( WebSettings.RenderPriority.HIGH);

//		 mWebView.getSettings().setTextSize(TextSize.NORMAL);
        if ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE) {
//			    Toast.makeText(this, "Large screen", Toast.LENGTH_LONG).show();
            mWebView.getSettings().setTextSize(WebSettings.TextSize.LARGEST);

        }
        else if ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_XLARGE) {
//			    Toast.makeText(this, "Large screen", Toast.LENGTH_LONG).show();
            mWebView.getSettings().setTextSize(WebSettings.TextSize.LARGEST);

        }
        else if ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_NORMAL) {
//			    Toast.makeText(this, "Normal sized screen", Toast.LENGTH_LONG).show();

            mWebView.getSettings().setTextSize(WebSettings.TextSize.NORMAL);


        }
        else if ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_SMALL) {
//			    Toast.makeText(this, "Small sized screen", Toast.LENGTH_LONG).show();
            mWebView.getSettings().setTextSize(WebSettings.TextSize.NORMAL);

        }
        else {
            mWebView.getSettings().setTextSize(WebSettings.TextSize.NORMAL);


//			    Toast.makeText(this, "Screen size is neither large, normal or small", Toast.LENGTH_LONG).show();
        }

        String m="<html><head><title></title></head><style>@font-face { font-family: \"MyFont\"; src: url(\"file:///android_asset/Helvetica.otf\");}  body { font-family:\"MyFont\";height:\"100%\";width:\"100%\";padding:\"0\";margin:\"0\"; }</style><body background=\"bg.png\">";

        String m1="</body></head></html>";



        mWebView.getSettings().setJavaScriptEnabled(true);


        String		str= Constants.readRawTextFile(getApplicationContext(), R.raw.guide);



        mWebView.loadDataWithBaseURL("file:///android_res/drawable/", m+str.replaceAll("<ul>", "")
                +m1, "text/html", "utf-8", "");

    }

}
