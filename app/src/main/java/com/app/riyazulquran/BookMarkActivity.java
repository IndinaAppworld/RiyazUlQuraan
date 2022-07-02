package com.app.riyazulquran;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
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
import android.widget.ListView;

import com.animation.ActivityAnimator;
import com.fonts.CipherNormal;
import com.fonts.HelviticaNormal;
import com.utils.Book;
import com.utils.Constants;
import com.utils.Contact;
import com.utils.DatabaseHandler;
import com.view.DragLayout;

import java.util.ArrayList;
import java.util.List;

public class BookMarkActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_book_mark);
        iv_icon = (ImageView) findViewById(R.id.iv_icon);
        initDragLayout();
        loadSideMenu();
        loadList();
    }
    ImageView iv_icon;
    public void loadSideMenu()
    {
        ArrayList<String> options=new ArrayList();

        if(Constants.LANGUAGE_ID==Constants.ENGLISH)
        {

            options.add("Resume");


                options.add("Juz Index");

                options.add("Surah Index");

            options.add("Go To Page#");
//				options.add("Bookmarks");

            options.add("Change Reading Mode");
            //options.add("HELP");


            options.add("Share");
            options.add("Rate Us");
//            options.add("About Developer");

//            options.add("Exit");
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

                if(position==0)
                {
                    Intent intent=new Intent(Intent.ACTION_VIEW);
                    intent.setClass(BookMarkActivity.this,CurlActivity.class);
                    intent.putExtra("page", Constants.sp.getInt("lastread", 0)-1);
                    startActivityForResult(intent,1000);
                    ActivityAnimator anim = new ActivityAnimator();

                    try {
                        anim.getClass().getMethod("appearTopLeft" + "Animation", Activity.class).invoke(anim, BookMarkActivity.this);
                    }
                    catch(Exception e ){

                    }
                }
                else if(position==1)
                {
                    Intent intent=new Intent(Intent.ACTION_VIEW);
                    intent.setClass(BookMarkActivity.this, SubMenuActivity.class);

                        intent.putExtra("reqid", 0);
                    startActivityForResult(intent,1000);
                    ActivityAnimator anim = new ActivityAnimator();

                    try {
                        anim.getClass().getMethod("appearTopLeft" + "Animation", Activity.class).invoke(anim, BookMarkActivity.this);
                    }
                    catch(Exception e ){

                    }
                }
                else if(position==2)
                {
                    Intent intent=new Intent(Intent.ACTION_VIEW);
                    intent.setClass(BookMarkActivity.this, SubMenuActivity.class);

                    intent.putExtra("reqid", 1);
                    startActivityForResult(intent,1000);
                    ActivityAnimator anim = new ActivityAnimator();

                    try {
                        anim.getClass().getMethod("appearTopLeft" + "Animation", Activity.class).invoke(anim, BookMarkActivity.this);
                    }
                    catch(Exception e ){

                    }
                }
//                if(position==1)
//                {
//                    Constants.startWebStiteAct(BookMarkActivity.this);
//                }
                else if(position==3)
                {
                    Intent intent=new Intent(Intent.ACTION_VIEW);
                    intent.setClass(BookMarkActivity.this, GotoPageActivity.class);
                    startActivityForResult(intent, 900);
                    ActivityAnimator anim = new ActivityAnimator();

                    try {
                        anim.getClass().getMethod("appearTopLeft" + "Animation", Activity.class).invoke(anim, BookMarkActivity.this);
                    }
                    catch(Exception e ){

                    }
                }

                else if(position==4)
                {
                    Intent intent=new Intent(Intent.ACTION_VIEW);
                    intent.setClass(BookMarkActivity.this, ChangeReadingMode.class);
                    startActivityForResult(intent, 900);
                    ActivityAnimator anim = new ActivityAnimator();

                    try {
                        anim.getClass().getMethod("appearTopLeft" + "Animation", Activity.class).invoke(anim, BookMarkActivity.this);
                    }
                    catch(Exception e ){

                    }
                }
                else if(position==5)
                {
                    Constants.startShareAct(BookMarkActivity.this);
                }
                else if(position==6)
                {
                    Constants.startRateUsAct(BookMarkActivity.this);
                }
//                else if(position==7)
//                {
//                    Intent intent=new Intent(Intent.ACTION_VIEW);
//                    intent.setClass(BookMarkActivity.this, AboutDeveloper.class);
//                    startActivityForResult(intent, 300);
//                    ActivityAnimator anim = new ActivityAnimator();
//
//                    try {
//                        anim.getClass().getMethod("appearTopLeft" + "Animation", Activity.class).invoke(anim, BookMarkActivity.this);
//                    }
//                    catch(Exception e ) {
//
//
//                    }
//                }
                else if(position==7)
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
    List<Contact> contact;
    ListView mListView;
    public void loadList()
    {
        DatabaseHandler db=new DatabaseHandler(this);
        contact= db.getAllContacts();
        for(int i=0;i<contact.size();i++)

        {
            System.out.println("contact"+contact.get(i).TITLE);
        }

        if(contact.size()==0)bookamrknotfound();

        final MyAdapter1 adapter = new MyAdapter1(this, contact);

        mListView = (ListView)findViewById(R.id.list);
        mListView.setAdapter(adapter);
        mListView.setCacheColorHint(Color.TRANSPARENT);
        // mListView.setDynamics(new SimpleDynamics(0.9f, 0.6f));

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(final AdapterView<?> parent, final View view,
                                           final int position, final long id) {

                remove(position);

                return true;
            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub

                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setClass(BookMarkActivity.this, CurlActivity.class);
                intent.putExtra("page", Constants.TOTAL_PAGES-Integer.parseInt(contact.get(arg2).PAGENO)-1);
                intent.putExtra("bookmarkPage", (contact.get(arg2).TITLE));

                startActivityForResult(intent,1000);
                ActivityAnimator anim = new ActivityAnimator();

                try {
                    anim.getClass().getMethod("appearTopLeft" + "Animation", Activity.class).invoke(anim, BookMarkActivity.this);
                }
                catch(Exception e ){
                }



            }});

    }
    public void bookamrknotfound()
    {
        String str="OK";

        AlertDialog.Builder builder=new AlertDialog.Builder(BookMarkActivity.this);
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setTitle("Bookmark Empty");
        builder.setMessage("Empty bookmark");

        if(Constants.LANGUAGE_ID==Constants.URDU)
        {
            str=""+Constants.urdu_array[33];
            builder.setTitle(Constants.urdu_array[10]);

            builder.setMessage(Constants.urdu_array[11]);

        }

        builder.setPositiveButton(str, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {


                finish();
            }

        })

                .show();
    }
    public void remove(final int position)
    {

        String yes="Yes",no="No";
        AlertDialog.Builder builder=new AlertDialog.Builder(this);

        builder.setIcon(android.R.drawable.ic_dialog_alert);

        if(Constants.LANGUAGE_ID==Constants.ENGLISH)
        {
            builder.setTitle("Remove Confirmation");
            builder.setMessage("Are you sure you want to delete this entry from Bookmarks?");
        }
        else
        {
            builder.setTitle(Constants.urdu_array[36]);
            builder.setMessage(Constants.urdu_array[35]);
            yes=Constants.urdu_array[33];
            no=Constants.urdu_array[34];
        }
        builder.setPositiveButton(yes, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                //Stop the activity

                String r=contact.get(position).ROWID;
                DatabaseHandler db = new DatabaseHandler(BookMarkActivity.this);
                db.deleteContact(r);


                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setClass(BookMarkActivity.this,BookMarkActivity.class);
                startActivityForResult(intent,100);
                finish();
            }

        })
                .setNegativeButton(no, null)
                .show();
    }

    private  class MyAdapter1 extends ArrayAdapter<Contact> {

        /** Re-usable contact image drawable */


        /**
         * Constructor
         *
         * @param context The context
         * @param contacts The list of contacts
         */
        public MyAdapter1(final Context context, final List<Contact> contacts) {
            super(context, 0, contacts);

        }

        @Override
        public View getView(final int position, final View convertView, final ViewGroup parent) {
            View view = convertView;
            if (view == null) {

                view = LayoutInflater.from(getContext()).inflate(R.layout.list_item_bookmarks, null);

            }

            Contact b=(Contact)getItem(position);


            final HelviticaNormal name_english = (HelviticaNormal)view.findViewById(R.id.name_english);
            final HelviticaNormal name_arabic = (HelviticaNormal)view.findViewById(R.id.name_arabic);


            name_english.setText(b.TITLE);
            name_arabic.setText("Page No:- "+(Constants.TOTAL_PAGES-Integer.parseInt(b.PAGENO)));


            ImageView delete=(ImageView)view.findViewById(R.id.delete);
            delete.setOnClickListener(new MyDeleteListener(position));

            return view;
        }
    }

    public class MyDeleteListener implements  android.view.View.OnClickListener
    {
        int pos;
        public MyDeleteListener(int pos)
        {
            this.pos=pos;
        }
        public void onClick(View view)
        {
remove(pos);
        }


    }


    public void back(View v)
    {

        finish();

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
