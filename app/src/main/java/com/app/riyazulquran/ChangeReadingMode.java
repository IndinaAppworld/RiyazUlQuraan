package com.app.riyazulquran;

import android.app.Activity;
import android.graphics.Color;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.animation.ActivityAnimator;
import com.utils.Constants;

public class ChangeReadingMode extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme_Dialog);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_change_reading_mode);
        final		RadioButton r1=(RadioButton)findViewById(R.id.r1);
        Constants.setFont_cipher(r1, this);
        final RadioButton r2=(RadioButton)findViewById(R.id.r2);Constants.setFont_cipher(r2, this);

        if(Constants.sp.getInt("readType", 0)==0)
        {
            r1.setChecked(true);
        }
        else r2.setChecked(true);

        (findViewById(R.id.submit)).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub


                int i=0;
                if(r1.isChecked())
                {
                    i=0;
                }
                else
                {
                    i=1;
                    Toast toast = Toast.makeText(getApplicationContext(),"Zoom IN/OUT option is enable in gallery mode",Toast.LENGTH_LONG);
//                    View view = toast.getView();
//                    view.setBackgroundColor(Color.parseColor(Constants.TOASTCOLOR));
//                    TextView text = (TextView) view.findViewById(android.R.id.message);
////    				text.setTextColor(Color.parseColor(Constants.TOASTCOLOR_TEXT));
//
//                    text.setTextColor(Color.parseColor(Constants.TOASTCOLOR_TEXT));
//                    text.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
                    toast.show();
                }
                Constants.editor.putInt("readType", i);
                Constants.editor.commit();
                setResult(100);
                finish();

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

}
