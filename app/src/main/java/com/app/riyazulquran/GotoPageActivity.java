package com.app.riyazulquran;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.animation.ActivityAnimator;
import com.utils.Constants;

public class GotoPageActivity extends Activity {
CheckBox chk;
    EditText pageno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme_Dialog);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_goto_page);
        chk=(CheckBox)findViewById(R.id.chk);
        Typeface  tf= Typeface.createFromAsset(getAssets(), "Helvetica.otf");
        chk.setTypeface(tf, Typeface.BOLD);

        pageno=(EditText)findViewById(R.id.pageno);
        pageno.setTypeface(tf,Typeface.BOLD);
        chk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                // TODO Auto-generated method stub

                if (arg1) {
                    pageno.setEnabled(false);
                    pageno.setText("" + Constants.sp.getInt("lastread", 1));

                } else {
                    pageno.setEnabled(true);
                    pageno.setText("");
                }
            }
        });

        (findViewById(R.id.submit)).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

if(pageno.getText().toString().trim().length()>0) {
    int pageno1 = Integer.parseInt(pageno.getText().toString().trim());
    if (pageno1 <= Constants.TOTAL_PAGES) {
        Intent intent=new Intent(Intent.ACTION_VIEW);
        intent.setClass(GotoPageActivity.this, CurlActivity.class);
        intent.putExtra("page", pageno1 - 1);
        startActivityForResult(intent,1000);
        ActivityAnimator anim = new ActivityAnimator();

        try {
            anim.getClass().getMethod("appearTopLeft" + "Animation", Activity.class).invoke(anim, GotoPageActivity.this);
        }
        catch(Exception e ){

        }
finish();

    } else {
        Toast toast = Toast.makeText(getApplicationContext(), "Page number can not be greater than " + Constants.TOTAL_PAGES, Toast.LENGTH_LONG);
//        View view = toast.getView();
//        view.setBackgroundColor(Color.parseColor(Constants.TOASTCOLOR));
//        TextView text = (TextView) view.findViewById(android.R.id.message);
////    				text.setTextColor(Color.parseColor(Constants.TOASTCOLOR_TEXT));
//
//        text.setTextColor(Color.parseColor(Constants.TOASTCOLOR_TEXT));
//        text.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
        toast.show();
    }
}
                else
{
    Toast toast = Toast.makeText(getApplicationContext(), "Please enter page number", Toast.LENGTH_LONG);
//    View view = toast.getView();
//    view.setBackgroundColor(Color.parseColor(Constants.TOASTCOLOR));
//    TextView text = (TextView) view.findViewById(android.R.id.message);
////    				text.setTextColor(Color.parseColor(Constants.TOASTCOLOR_TEXT));
//
//    text.setTextColor(Color.parseColor(Constants.TOASTCOLOR_TEXT));
//    text.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
    toast.show();
}

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
