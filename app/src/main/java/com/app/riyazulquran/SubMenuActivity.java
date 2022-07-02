package com.app.riyazulquran;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
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
import android.widget.TextView;
import android.widget.Toast;

import com.animation.ActivityAnimator;
import com.fonts.CipherBold;
import com.fonts.CipherNormal;
import com.fonts.QuraanicNormal;
import com.utils.Book;
import com.utils.Constants;
import com.utils.Contact;
import com.utils.DatabaseHandler;
import com.view.DragLayout;

import java.util.ArrayList;

public class SubMenuActivity extends Activity {
    String titles[]={"JUZ INDEX","ALL SURAH","FAVOURITE"};

    int reqid=0;

    CipherBold tabtextview[]=new CipherBold[2];
    LinearLayout line[]=new LinearLayout[2];
    ArrayList<Book> contacts = new ArrayList();
    ListView mListView;
    LinearLayout mla,mla2;
    int sid=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sub_menu);
        if(getIntent().getExtras()!=null)
        {
            reqid=getIntent().getExtras().getInt("reqid");
        }

        line[0]=(LinearLayout)findViewById(R.id.line1);//Constants.setFont(line[0],this);
        line[1]=(LinearLayout)findViewById(R.id.line2);//Constants.setFont(line[0],this);

        mla=(LinearLayout)findViewById(R.id.mla);
        mla2=(LinearLayout)findViewById(R.id.mla2);

        if(reqid==0){
            mla.setVisibility(View.VISIBLE);
            mla2.setVisibility(View.GONE);
        }
        tabtextview[0]=(CipherBold)findViewById(R.id.grid);tabtextview[0].setOnClickListener(new TabTextClick(0));
        tabtextview[1]=(CipherBold)findViewById(R.id.listv);tabtextview[1].setOnClickListener(new TabTextClick(1));
        tabclick(0);


//        inistView();
        initDragLayout();
        loadSideMenu();


        if(Constants.sp.getBoolean("autofav", false)==false)
        {
            DatabaseHandler db=new DatabaseHandler(this);
            db.  addContact_Fav(new Contact("", "Al-Mulk |  سورة الملك   |787","Al-Mulk |  سورة الملك   |787"));
            db.  addContact_Fav(new Contact("", "Ar-Rahman |  سورة الرحمن   |740","Ar-Rahman |  سورة الرحمن   |740"));
            db.  addContact_Fav(new Contact("", "Ya Seen |  يٰس   |611","Ya Seen |  يٰس   |611"));
            db. addContact_Fav(new Contact("", "Al-Kahf |  سورة الكهف   |408","Al-Kahf |  سورة الكهف   |408"));
            db.addContact_Fav(new Contact("", "Al-Fatiha |  سورة الفاتحة   |2","Al-Fatiha |  سورة الفاتحة   |2"));




            Constants.editor.putBoolean("autofav", true);
            Constants.editor.commit();
        }

    }
    public void loadSideMenu()
    {
        ArrayList<String> options=new ArrayList();

        if(Constants.LANGUAGE_ID==Constants.ENGLISH)
        {

            options.add("Resume");
            options.add("Bookmark");

            if(reqid==1)
                options.add("Juz Index");
            else
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
                    intent.setClass(SubMenuActivity.this,CurlActivity.class);
                    intent.putExtra("page", Constants.sp.getInt("lastread", 0)-1);
                    startActivityForResult(intent,1000);
                    ActivityAnimator anim = new ActivityAnimator();

                    try {
                        anim.getClass().getMethod("appearTopLeft" + "Animation", Activity.class).invoke(anim, SubMenuActivity.this);
                    }
                    catch(Exception e ){

                    }

                }
               else  if(position==1)
                {

                    Intent intent=new Intent(Intent.ACTION_VIEW);
                    intent.setClass(SubMenuActivity.this,BookMarkActivity.class);
//                    intent.putExtra("page", Constants.sp.getInt("lastread", 0)+1);
                    startActivityForResult(intent,1000);
                    ActivityAnimator anim = new ActivityAnimator();

                    try {
                        anim.getClass().getMethod("appearTopLeft" + "Animation", Activity.class).invoke(anim, SubMenuActivity.this);
                    }
                    catch(Exception e ){

                    }
//                    Constants.startWebStiteAct(SubMenuActivity.this);
                }
                else if(position==2)
                {
                    finish();
                    Intent intent=new Intent(Intent.ACTION_VIEW);
                    intent.setClass(SubMenuActivity.this, SubMenuActivity.class);
                    if(reqid==0)
                    intent.putExtra("reqid", 1);
                    else
                    intent.putExtra("reqid", 0);
                    startActivityForResult(intent,1000);
                    ActivityAnimator anim = new ActivityAnimator();

                    try {
                        anim.getClass().getMethod("appearTopLeft" + "Animation", Activity.class).invoke(anim, SubMenuActivity.this);
                    }
                    catch(Exception e ){

                    }
                }
                else if(position==3)
                {
                    Intent intent=new Intent(Intent.ACTION_VIEW);
                    intent.setClass(SubMenuActivity.this, GotoPageActivity.class);
                    startActivityForResult(intent, 900);
                    ActivityAnimator anim = new ActivityAnimator();

                    try {
                        anim.getClass().getMethod("appearTopLeft" + "Animation", Activity.class).invoke(anim, SubMenuActivity.this);
                    }
                    catch(Exception e ){

                    }
                }

                else if(position==4)
                {
                    Intent intent=new Intent(Intent.ACTION_VIEW);
                    intent.setClass(SubMenuActivity.this, ChangeReadingMode.class);
                    startActivityForResult(intent, 900);
                    ActivityAnimator anim = new ActivityAnimator();

                    try {
                        anim.getClass().getMethod("appearTopLeft" + "Animation", Activity.class).invoke(anim, SubMenuActivity.this);
                    }
                    catch(Exception e ){

                    }
                }
                else if(position==5)
                {
                    Constants.startShareAct(SubMenuActivity.this);
                }
                else if(position==6)
                {
                    Constants.startRateUsAct(SubMenuActivity.this);
                }
//                else if(position==7)
//                {
//                    Intent intent=new Intent(Intent.ACTION_VIEW);
//                    intent.setClass(SubMenuActivity.this, AboutDeveloper.class);
//                    startActivityForResult(intent, 300);
//                    ActivityAnimator anim = new ActivityAnimator();
//
//                    try {
//                        anim.getClass().getMethod("appearTopLeft" + "Animation", Activity.class).invoke(anim, SubMenuActivity.this);
//                    }
//                    catch(Exception e ) {
//
//
//                    }
//                }
                else if(position==6)
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


    }
    public void loadList()
    {
        contacts = new ArrayList();


        if(reqid==1)
        {


            if(sid==0)
            {
                for(int i=0;i<Constants.surehname_arabic.length;i++)
                {
                    contacts.add(new Book(Constants.surehname_english[i],Constants.surehname_arabic[i], ""+Constants.surehindex[i]));
                }
            }
            else
            {
                DatabaseHandler db=new DatabaseHandler(this);
//				 if(db.)
                contacts= db.getAllContacts_Fav();
            }
        }
        else
        {

            for(int i=0;i<Constants.paraname_english.length;i++)
            {
                contacts.add(new Book("Para "+(i+1), (i+1)+"  . "+ Constants.paraname_arabic[i], ""+Constants.paraIndex[i]));
            }
        }
        final MyAdapter1 adapter = new MyAdapter1(this, contacts);

        mListView = (ListView)findViewById(R.id.list);
        mListView.setAdapter(adapter);
        mListView.setCacheColorHint(Color.TRANSPARENT);
        // mListView.setDynamics(new SimpleDynamics(0.9f, 0.6f));

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(final AdapterView<?> parent, final View view,
                                    final int position, final long id) {
//
//	            	if(reqid==0)
                {
                    Book b=(Book)contacts.get(position);
//	            		Toast.makeText(getApplicationContext(),""+Integer.parseInt(b.page_no),Toast.LENGTH_SHORT).show();

                    Intent intent=new Intent(Intent.ACTION_VIEW);
                    intent.setClass(SubMenuActivity.this, CurlActivity.class);
                    intent.putExtra("page", Integer.parseInt(b.page_no)-1);
                    startActivityForResult(intent,1000);
                    ActivityAnimator anim = new ActivityAnimator();

                    try {
                        anim.getClass().getMethod("appearTopLeft" + "Animation", Activity.class).invoke(anim, SubMenuActivity.this);
                    }
                    catch(Exception e ){

                    }
                }


            }
        });

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(final AdapterView<?> parent, final View view,
                                           final int position, final long id) {
//	                final String message = "OnLongClick: " + contacts.get(position).mName;
//	                Toast.makeText(TestActivity.this, message, Toast.LENGTH_SHORT).show();

                return true;
            }
        });

    }
    private  class MyAdapter1 extends ArrayAdapter<Book> {

        /** Re-usable contact image drawable */
//        private final Drawable contactImage;

        /**
         * Constructor
         *
         * @param context The context
         * @param contacts The list of contacts
         */
        public MyAdapter1(final Context context, final ArrayList<Book> contacts) {
            super(context, 0, contacts);
//            contactImage = context.getResources().getDrawable(R.drawable.arrow);
        }

        @Override
        public View getView(final int position, final View convertView, final ViewGroup parent) {
            View view = convertView;
            if (view == null) {

                view = LayoutInflater.from(getContext()).inflate(R.layout.list_item, null);

            }

            Book b=(Book)getItem(position);


            final CipherNormal name_english = (CipherNormal)view.findViewById(R.id.name_english);
            final QuraanicNormal name_arabic = (QuraanicNormal)view.findViewById(R.id.name_arabic);
            final ImageView fav=(ImageView)view.findViewById(R.id.fav);
            if(reqid==1 )
            {

                if(true)//sid==0)
                {

                    if(sid==0)
                    {
                        fav.setOnClickListener(new FavClick(b.name_english+"|"+b.name_arabic+"|"+Constants.surehindex[position],fav));
                        if(checkFav(b.name_english+"|"+b.name_arabic+"|"+Constants.surehindex[position])>0)fav.setImageResource(R.drawable.faxsel);
                        else fav.setImageResource(R.drawable.favunsel);
                    }
                    else
                    {
                        fav.setOnClickListener(new FavClick(b.name_english+"|"+b.name_arabic+"|"+b.page_no,fav));
                        if(checkFav(b.name_english+"|"+b.name_arabic+"|"+b.page_no)>0)fav.setImageResource(R.drawable.faxsel);
                    }

                }
                else
                {
                    fav.setVisibility(View.GONE);
                }


            }
            else fav.setVisibility(View.GONE);

            if(reqid==0)
                name_english.setText(b.name_english);
            else  name_english.setText(""+(position+1)+". "+b.name_english);


            if(b.name_arabic.length()>0)
                name_arabic.setText(b.name_arabic);
            else name_arabic.setVisibility(View.GONE);


            return view;
        }
    }

    public int checkFav(String positoin)
    {
        DatabaseHandler db = new DatabaseHandler(SubMenuActivity.this);
        return db.getContactsCount_Fav(""+positoin);
//	       if(db.getContactsCount_Fav(""+position)==0)
//	    	{
//
//	    	}
    }
    public class FavClick implements View.OnClickListener
    {
        String position;
        ImageView img;
        public FavClick(String position,ImageView img)
        {
            this.position=position;
            this.img=img;
        }
        public void onClick(View view1)
        {
            DatabaseHandler db = new DatabaseHandler(SubMenuActivity.this);
            if(db.getContactsCount_Fav(""+position)==0)
            {
                db = new DatabaseHandler(SubMenuActivity.this);
                db.addContact_Fav(new Contact("", ""+position,""+position));
                System.out.println("RESULT-->"+position);


                Toast toast = Toast.makeText(getApplicationContext(),"Added to favourite",Toast.LENGTH_LONG);
                View view = toast.getView();
                view.setBackgroundColor(Color.parseColor(Constants.TOASTCOLOR));
                TextView text = (TextView) view.findViewById(android.R.id.message);
//				text.setTextColor(Color.parseColor(Constants.TOASTCOLOR_TEXT));

                text.setTextColor(Color.parseColor(Constants.TOASTCOLOR_TEXT));
                text.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
                toast.show();

                img.setImageResource(R.drawable.faxsel);
            }
            else
            {


                db = new DatabaseHandler(SubMenuActivity.this);
                db.deleteContact_Fav((""+position));



                Toast toast = Toast.makeText(getApplicationContext(),"Removed from Favourites",Toast.LENGTH_LONG);
                View view = toast.getView();
                view.setBackgroundColor(Color.parseColor(Constants.TOASTCOLOR));
                TextView text = (TextView) view.findViewById(android.R.id.message);
//				text.setTextColor(Color.parseColor(Constants.TOASTCOLOR_TEXT));

                text.setTextColor(Color.parseColor(Constants.TOASTCOLOR_TEXT));
                text.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
                toast.show();

                img.setImageResource(R.drawable.favunsel);

                if(sid==1)loadList();
                /**
                 * CRUD Operations
                 * */
                // Inserting Contacts

            }
        }
    }
    public class TabTextClick implements View.OnClickListener
    {
        int position=0;
        public TabTextClick(int position)
        {
            this.position=position;
        }
        public void onClick(View view)
        {
            tabclick(position);
        }
    }
    public void tabclick(int m)
    {

        line[0].setVisibility(View.INVISIBLE);
        line[1].setVisibility(View.INVISIBLE);
        line[m].setVisibility(View.VISIBLE);
        sid=m;
        loadList();
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
