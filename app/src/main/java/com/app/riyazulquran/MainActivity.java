package com.app.riyazulquran;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.animation.ActivityAnimator;
import com.fonts.CipherNormal;
//import com.nineoldandroids.view.ViewHelper;
import com.utils.Book;
import com.utils.Constants;
import com.view.DragLayout;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);



        setContentView(R.layout.activity_main);
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        Constants.width = metrics.widthPixels;
        Constants.height = metrics.heightPixels;

        Constants.sp=getSharedPreferences(Constants.prefName, Activity.MODE_PRIVATE);
        Constants.editor=Constants.sp.edit();

        inistView();
initDragLayout();
loadSideMenu();
    }
    public void loadSideMenu()
    {
        ArrayList<String> options=new ArrayList();

        if(Constants.LANGUAGE_ID==Constants.ENGLISH)
        {

            options.add("Change Reading Mode");

            options.add("Visit Website");
            options.add("Share");
            options.add("Rate Us");
//            options.add("About Developer");
            options.add("Exit");
        }
//        else
//        {
//            options.add(Constants.urdu_array[0]);
//            options.add(Constants.urdu_array[20]);
//            options.add(Constants.urdu_array[23]);
//            options.add(Constants.urdu_array[24]);
//            options.add(Constants.urdu_array[25]);
//            options.add(Constants.urdu_array[26]);
//            options.add(Constants.urdu_array[27]);
//        }


        MyAdapterSideMenu ad=new MyAdapterSideMenu(this, options);
        ListView mListView = (ListView)findViewById(R.id.lv);
        mListView.setAdapter(ad);
        mListView.setCacheColorHint(Color.TRANSPARENT);
        // mListView.setDynamics(new SimpleDynamics(0.9f, 0.6f));

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(final AdapterView<?> parent, final View view,
                                    final int position, final long id) {

//                if(position==0)
//                {
//                    languageSelect(0);
//                }
//                else

                if(position==0)
                {
                    Intent intent=new Intent(Intent.ACTION_VIEW);
                    intent.setClass(MainActivity.this, ChangeReadingMode.class);
                    startActivityForResult(intent, 900);
                    ActivityAnimator anim = new ActivityAnimator();

                    try {
                        anim.getClass().getMethod("appearTopLeft" + "Animation", Activity.class).invoke(anim, MainActivity.this);
                    }
                    catch(Exception e ){

                    }

                }
                else if(position==1)
                {
//Constants.startWebStiteAct(MainActivity.this);

                    Intent intent=new Intent(Intent.ACTION_VIEW);
                    intent.setClass(MainActivity.this,VisitWebsite.class);
                    intent.putExtra("reqid", 0);
                    startActivityForResult(intent, 1000);
                    ActivityAnimator anim = new ActivityAnimator();

                    try {
                        anim.getClass().getMethod("appearTopLeft" + "Animation", Activity.class).invoke(anim, MainActivity.this);
                    }
                    catch(Exception e ){

                    }

                }
                else if(position==2)
                {
                    Constants.startShareAct(MainActivity.this);
                }
                else if(position==3)
                {
                    Constants.startRateUsAct(MainActivity.this);
                }
                else if(position==4)
                {
//                    Intent intent=new Intent(Intent.ACTION_VIEW);
//                    intent.setClass(MainActivity.this, AboutDeveloper.class);
//                    startActivityForResult(intent, 300);
//                    ActivityAnimator anim = new ActivityAnimator();
//
//                    try
//                    {
//                        anim.getClass().getMethod("appearTopLeft" + "Animation", Activity.class).invoke(anim, MainActivity.this);
//                    }
//                    catch(Exception e )
//                    {
//
//                    }


                    finish();
                }
                else if(position==4)
                {
//                    finish();
//                    Intent intent = new Intent(Intent.ACTION_SEND);
//                    intent.setType("text/plain");
//                    intent.putExtra(android.content.Intent.EXTRA_TEXT, Constants.share_data);
//                    startActivity(intent);
                }
//                else if(position==5)
//                {
////                    try {
////                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+Constants.RATE_ID)));
////                    } catch (android.content.ActivityNotFoundException anfe) {
////                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?"+Constants.RATE_ID)));
////                    }
//
//                }
                else if(position==5)
                {
                    finish();
                }



            }
        });

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(final AdapterView<?> parent, final View view,
                                           final int position, final long id) {
//                final String message = "OnLongClick: " + contacts.get(position).mName;
//                Toast.makeText(TestActivity.this, message, Toast.LENGTH_SHORT).show();

                return true;
            }
        });

    }
    private  class MyAdapterSideMenu extends ArrayAdapter<String> {

        /** Re-usable contact image drawable */
        private Drawable contactImage;

        /**
         * Constructor
         *
         * @param context The context
         * @param contacts The list of contacts
         */
        public MyAdapterSideMenu( Context context, final ArrayList<String> contacts) {
            super(context, 0, contacts);

        }

        @Override
        public View getView(final int position, final View convertView, final ViewGroup parent) {
            View view = convertView;
//            if (view == null) {
//
//            		view = LayoutInflater.from(getContext()).inflate(R.layout.item_text, null);
//
//            }
            if(Constants.LANGUAGE_ID==Constants.ENGLISH)
                view = LayoutInflater.from(getContext()).inflate(R.layout.item_text, null);
//            else view = LayoutInflater.from(getContext()).inflate(R.layout.item_text_urdu, null);



            CipherNormal text1=(CipherNormal)view.findViewById(R.id.text1);
            text1.setText(getItem(position).toString());

//            if(Constants.LANGUAGE_ID==Constants.ENGLISH)
//                Constants.setFont(text1, MenuScreen.this);
//            else Constants.setFont_Urdu(text1, MenuScreen.this);

            return view;
        }
    }

    DragLayout dl;
    private void initDragLayout() {
        dl = (DragLayout) findViewById(R.id.dl);
        dl.setDragListener(new DragLayout.DragListener() {
            @Override
            public void onOpen() {
//                lv.smoothScrollToPosition(new Random().nextInt(30));
            }

            @Override
            public void onClose() {
//
//              shake();
            }

            @Override
            public void onDrag(float percent) {
//                ViewHelper.setAlpha(iv_icon, 1 - percent);
            }
        });
        iv_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dl.open();
            }
        });
    }
    ImageView iv_icon;
    View view[]=new View[3];
    ImageView icons_image[]=new ImageView[6];
    LinearLayout menu_linear[]=new LinearLayout[6];
CipherNormal menu_titles[]=new CipherNormal[6];
    LinearLayout menu_content;
    String titles[]={"Resume","Juzz Index","Surah Index","Go to page","Bookmark","User Guide"};
    int icons[]={R.drawable.resume,R.drawable.juzindex,R.drawable.surahindex,R.drawable.gotopage,R.drawable.bookmark,R.drawable.help} ;
    int counter=0;
    public void inistView()
    {
        iv_icon = (ImageView) findViewById(R.id.iv_icon);
        menu_content=(LinearLayout)findViewById(R.id.menu_content);
        for(int i=0;i<3;i++)
        {
            view[i]=View.inflate(this,R.layout.inflate_menu,null);
            menu_linear[counter]=(LinearLayout)view[i].findViewById(R.id.menu1);
            icons_image[counter]=(ImageView)view[i].findViewById(R.id.icon1);icons_image[counter].setImageResource(icons[counter]);
            menu_titles[counter]=(CipherNormal)view[i].findViewById(R.id.title1);menu_titles[counter].setText(titles[counter]);
            menu_linear[counter].setOnClickListener(new MyListener(counter));


counter++;
            menu_linear[counter]=(LinearLayout)view[i].findViewById(R.id.menu2);
            icons_image[counter]=(ImageView)view[i].findViewById(R.id.icon2);icons_image[counter].setImageResource(icons[counter]);
            menu_titles[counter]=(CipherNormal)view[i].findViewById(R.id.title2);menu_titles[counter].setText(titles[counter]);
            menu_linear[counter].setOnClickListener(new MyListener(counter));
            menu_content.addView(view[i]);
            counter++;
        }

    }
public class MyListener implements android.view.View.OnClickListener
{
    int pos;
    MyListener(int pos)
    {
        this.pos=pos;
    }
    public void onClick(View view)
    {
        if(pos==0)
        {
            Intent intent=new Intent(Intent.ACTION_VIEW);
            intent.setClass(MainActivity.this,CurlActivity.class);
            intent.putExtra("page", Constants.sp.getInt("lastread", 0)-1);
            startActivityForResult(intent,1000);
            ActivityAnimator anim = new ActivityAnimator();

            try {
                anim.getClass().getMethod("appearTopLeft" + "Animation", Activity.class).invoke(anim, MainActivity.this);
            }
            catch(Exception e ){

            }
        }
        else if(pos==1)
        {
            Intent intent=new Intent(Intent.ACTION_VIEW);
            intent.setClass(MainActivity.this,SubMenuActivity.class);
            intent.putExtra("reqid", 0);
            startActivityForResult(intent,1000);
            ActivityAnimator anim = new ActivityAnimator();

            try {
                anim.getClass().getMethod("appearTopLeft" + "Animation", Activity.class).invoke(anim, MainActivity.this);
            }
            catch(Exception e ){

            }
        }
    else if(pos==2)
        {
            Intent intent=new Intent(Intent.ACTION_VIEW);
            intent.setClass(MainActivity.this,SubMenuActivity.class);
            intent.putExtra("reqid", 1);
            startActivityForResult(intent,1000);
            ActivityAnimator anim = new ActivityAnimator();

            try {
                anim.getClass().getMethod("appearTopLeft" + "Animation", Activity.class).invoke(anim, MainActivity.this);
            }
            catch(Exception e ){

            }
        }
        else if(pos==3)
        {
            Intent intent=new Intent(Intent.ACTION_VIEW);
            intent.setClass(MainActivity.this, GotoPageActivity.class);
            startActivityForResult(intent, 300);
            ActivityAnimator anim = new ActivityAnimator();

            try {
                anim.getClass().getMethod("appearTopLeft" + "Animation", Activity.class).invoke(anim, MainActivity.this);
            }
            catch(Exception e ){

            }
        }
else if(pos==4)
        {
            Intent intent=new Intent(Intent.ACTION_VIEW);
            intent.setClass(MainActivity.this,BookMarkActivity.class);

            startActivityForResult(intent,1000);
            ActivityAnimator anim = new ActivityAnimator();

            try {
                anim.getClass().getMethod("appearTopLeft" + "Animation", Activity.class).invoke(anim, MainActivity.this);
            }
            catch(Exception e ){

            }
        }
        else if(pos==5)
        {
            Intent intent=new Intent(Intent.ACTION_VIEW);
            intent.setClass(MainActivity.this, HelpActivity.class);
            startActivityForResult(intent, 500);
            ActivityAnimator anim = new ActivityAnimator();

            try {
                anim.getClass().getMethod("appearTopLeft" + "Animation", Activity.class).invoke(anim, MainActivity.this);
            }
            catch(Exception e ){

            }
        }
        }

    }






}
