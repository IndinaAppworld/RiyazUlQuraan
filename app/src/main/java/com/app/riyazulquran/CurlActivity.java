package com.app.riyazulquran;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.com.page.CurlPage;
import com.com.page.CurlView;
import com.touch.ExtendedViewPager;
import com.touch.TouchImageView;
import com.utils.Constants;
import com.utils.Contact;
import com.utils.DatabaseHandler;

/**
 * Simple Activity for curl testing.
 * 
 * @author harism
 * 15 para ka 755 page missing hai
 */
public class CurlActivity extends Activity {

	
	ViewFlipper flipper;
	private CurlView mCurlView;

	public static int[] mBitmapIds1 = {};

public static	int []mBitmapIds=new int[mBitmapIds1.length];
	int lastread=0;
	ExtendedViewPager mViewPager;
	String bookmarkPage="";
	TextView pagenotxt;
	String mynames[];
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN); 
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);		
  		setContentView(R.layout.paging);
		flipper=(ViewFlipper)findViewById(R.id.flipper);
//		Constants.loadBitmap();
		int index = 0;
		if (getLastNonConfigurationInstance() != null) {
			index = (Integer) getLastNonConfigurationInstance();
		}
		mynames=new String[Constants.TOTAL_PAGES];
		int counter=0;
		for(int i=Constants.TOTAL_PAGES-1;i>=0;i--)
		{

			mynames[counter]="image"+(i+1)+".png";
			counter++;
		}
		mCurlView = (CurlView) findViewById(R.id.curl);
		mCurlView.setPageProvider(new PageProvider());
		mCurlView.setSizeChangedObserver(new SizeChangedObserver());





		int pageno=Constants.TOTAL_PAGES-1;
		if(getIntent().getExtras()!=null)
		{
			int m=getIntent().getExtras().getInt("page");
			pageno=Constants.TOTAL_PAGES-m;
			
			
//			bookmarkPage=""+pageno;
			if(getIntent().getExtras().containsKey("bookmarkPage"))
			bookmarkPage=getIntent().getExtras().getString("bookmarkPage");
			
//			Toast.makeText(getApplicationContext(),""+pageno+","+m,Toast.LENGTH_SHORT).show();
		}
		
		mCurlView.setCurrentIndex(pageno);
		mCurlView.setBackgroundColor(0xFF202830);
		

		// This is something somewhat experimental. Before uncommenting next
		// line, please see method comments in CurlView.
		// mCurlView.setEnableTouchPressure(true);
		
		if(Constants.sp.getBoolean("bookmark", false)==false)
		showBookMarkDialog();
		
		
		
//		if(Constants.sp.getBoolean("readingmode", false)==false)
//			showBookMarkDialog();
		
		
		
		
		 mViewPager = (ExtendedViewPager) findViewById(R.id.view_pager);
	    TouchImageAdapter t=  new TouchImageAdapter();
	      mViewPager.setAdapter(t);
	      mViewPager.setCurrentItem(pageno-1);
	      mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				lastread=arg0;
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	      
	      
//	      ;
	      
	      flipper.setDisplayedChild(Constants.sp.getInt("readType", 0));
	      if(flipper.getDisplayedChild()==0)
	      lastread=pageno;
	      else lastread=pageno-1;
		
		
	      pagenotxt=(TextView)findViewById(R.id.pageno);
	       Timer timer = new Timer();
	       TimerTask timerTask;
	      timerTask = new TimerTask() {
	       @Override
	       public void run() {
	          //refresh your textview
	    	   runOnUiThread(new Runnable() {
                 @Override
                 public void run() {
              	   
//              	   if(flipper.getDisplayedChild()==0)
//              	   pagenotxt.setText(""+(mBitmapIds1.length-mCurlView.getCurrentIndex()+1));
//              	   else
//              	   {
//              		   pagenotxt.setText(""+(mBitmapIds1.length-mViewPager.getCurrentItem()));
//              	   }
                 }
             });
	    	
	       }
	      };
	      timer.schedule(timerTask, 0, 100);


		findViewById(R.id.bookmarkadd).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				back("Bookmark", "Do you want to add this page to bookmark");
			}
		});
	}
	
	public void onConfigurationChanged(Configuration newConfig) {
	    super.onConfigurationChanged(newConfig);
//Toast.makeText(getApplicationContext(), "CALL", Toast.LENGTH_LONG).show();
	    // Checks the orientation of the screen
	    if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
	    
	    	
//	    	if(Constants.sp.getInt("readType", 0)==1)
	    	{
	    		
	    			flipper.setDisplayedChild(1);
	    			mViewPager.invalidate();
	    			mViewPager.refreshDrawableState();
	    			mViewPager.setCurrentItem(lastread);

	    			
	    	}
	    	
	    	
//	    	Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
	    } 
	    else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
	    	
	    	if(Constants.sp.getInt("readType", 0)==1)
	    	{
	    		flipper.setDisplayedChild(1);
	    	}
	    	else
	    	{
    			mCurlView.setCurrentIndex(lastread+1);
	    		flipper.setDisplayedChild(0);
	    	}
	    	
	    	
	    	
	    	
//	        Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
	    }
	}

public void orientationChanged()
{
//	flipper.setDisplayedChild(Constants.sp.getInt("readType", 0));
//    if(flipper.getDisplayedChild()==0)
//    lastread=pageno;
//    else lastread=pageno-1;
	
}
   class TouchImageAdapter extends PagerAdapter {

//      private  int[] images = { R.drawable.para10_1, R.drawable.para10_1, R.drawable.para10_1, R.drawable.para10_1, R.drawable.para10_1 };
            @Override
      public int getCount() {
      	return Constants.TOTAL_PAGES;
      }

      @Override
      public View instantiateItem(ViewGroup container, int position) {
          TouchImageView img = new TouchImageView(container.getContext());
//          img.setScaleType(ScaleType);
//          img.setImageResource(CurlActivity.mBitmapIds[position]);
          
          img.setImageBitmap(loadBitmap(Constants.width,Constants.height, position));
          
          container.addView(img, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
          return img;
      }

      @Override
      public void destroyItem(ViewGroup container, int position, Object object) {
          container.removeView((View) object);
      }

      @Override
      public boolean isViewFromObject(View view, Object object) {
          return view == object;
      }

  }

  private Bitmap loadBitmap(int width, int height, int index)
  {
	  try
	  {

		  Bitmap b = Bitmap.createBitmap(width, height,
				  Bitmap.Config.ARGB_8888);


		  b.eraseColor(0xFFFFFFFF);


		  Canvas c = new Canvas(b);
		  Drawable d = Drawable.createFromStream(getAssets().open(mynames[index]), null);


		  int margin = 0;
		  int border = 3;
		  Rect r = new Rect(margin, margin, width - margin, height - margin);

		  int imageWidth = r.width() - (border * 2);
		  int imageHeight = imageWidth * d.getIntrinsicHeight()
				  / d.getIntrinsicWidth();
		  if (imageHeight > r.height() - (border * 2)) {
			  imageHeight = r.height() - (border * 2);
			  imageWidth = imageHeight * d.getIntrinsicWidth()
					  / d.getIntrinsicHeight();
		  }

		  //	r.left += ((r.width() - imageWidth) / 2) - border;
		  //	r.right = r.left + imageWidth + border + border;
		  //	r.top += ((r.height() - imageHeight) / 2) - border;
		  //	r.bottom = r.top + imageHeight + border + border;

		  Paint p = new Paint();
		  p.setColor(Color.parseColor("#FFFFFF"));
		  c.drawRect(r, p);
		  r.left += border;
		  r.right -= border;
		  r.top += border;
		  r.bottom -= border;

		  d.setBounds(r);
		  d.draw(c);


		  Matrix m = new Matrix();
		  m.preScale(-1, 1);
		  //    Bitmap src = d.getBitmap();
//	    b = Bitmap.createBitmap(b, 0, 0, b.getWidth(), b.getHeight(), m, false);


		  return b;
	  }
	  catch(Exception e)
	  {
		  Toast.makeText(getApplicationContext(),"Error "+e,Toast.LENGTH_SHORT).show();
		  return  null;
	  }
	}

	@Override
	public void onPause() {
		super.onPause();
		mCurlView.onPause();

		
	}

	@Override
	public void onResume() {
		super.onResume();
		mCurlView.onResume();
	}

	@Override
	public Object onRetainNonConfigurationInstance() {
		return mCurlView.getCurrentIndex();
	}

	/**
	 * Bitmap provider.
	 */
	private class PageProvider implements CurlView.PageProvider {

		// Bitmap resources.
		

		@Override
		public int getPageCount() {
			return Constants.TOTAL_PAGES;
		}

		private Bitmap loadBitmap(int width, int height, int index) {
			try {
				Bitmap b = Bitmap.createBitmap(width, height,
						Bitmap.Config.ARGB_8888);


				b.eraseColor(0xFFFFFFFF);


				Canvas c = new Canvas(b);
				Drawable d = Drawable.createFromStream(getAssets().open(mynames[index]), null);

				int margin = 0;
				int border = 3;
				Rect r = new Rect(margin, margin, width - margin, height - margin);

				int imageWidth = r.width() - (border * 2);
				int imageHeight = imageWidth * d.getIntrinsicHeight()
						/ d.getIntrinsicWidth();
				if (imageHeight > r.height() - (border * 2)) {
					imageHeight = r.height() - (border * 2);
					imageWidth = imageHeight * d.getIntrinsicWidth()
							/ d.getIntrinsicHeight();
				}

				//	r.left += ((r.width() - imageWidth) / 2) - border;
				//	r.right = r.left + imageWidth + border + border;
				//	r.top += ((r.height() - imageHeight) / 2) - border;
				//	r.bottom = r.top + imageHeight + border + border;

				Paint p = new Paint();
				p.setColor(Color.parseColor("#FFFFFF"));
				c.drawRect(r, p);
				r.left += border;
				r.right -= border;
				r.top += border;
				r.bottom -= border;

				d.setBounds(r);
				d.draw(c);


				Matrix m = new Matrix();
				m.preScale(-1, 1);
				//    Bitmap src = d.getBitmap();
				b = Bitmap.createBitmap(b, 0, 0, b.getWidth(), b.getHeight(), m, false);


				return b;
			}
			catch(Exception e)
			{
				Toast.makeText(getApplicationContext(),"Errro"+e,Toast.LENGTH_SHORT).show();
				return null;
			}

		}

		@Override
		public void updatePage(CurlPage page, int width, int height, int index) {

//			switch (index) {
			// First case is image on front side, solid colored back.
//			case 0: 
			{
//				Bitmap front = loadBitmap(width, height, 0);
//				page.setTexture(front, CurlPage.SIDE_FRONT);
//				page.setColor(Color.rgb(180, 180, 180), CurlPage.SIDE_BACK);
//				break;
			}
//			// Second case is image on back side, solid colored front.
//			case 1: {
				Bitmap back = loadBitmap(width, height, index);
//				back=flip(back);
				page.setTexture(back, CurlPage.SIDE_BACK);
				page.setColor(Color.rgb(255,255,255), CurlPage.SIDE_FRONT);
				
				
				lastread=index;
		}

	}

	/**
	 * CurlView size changed observer.
	 */
	private class SizeChangedObserver implements CurlView.SizeChangedObserver {
		@Override
		public void onSizeChanged(int w, int h) {
			if (w > h) {
				mCurlView.setViewMode(CurlView.SHOW_TWO_PAGES);
				mCurlView.setMargins(0f, 0f, 0f, 0f);
			} else {
				mCurlView.setViewMode(CurlView.SHOW_ONE_PAGE);
				mCurlView.setMargins(0f, 0f, 0f, 0f);
			}
		}
	}
	Bitmap flip(Bitmap src)
	{
	    Matrix m = new Matrix();
	    m.preScale(-1, 1);
	//    Bitmap src = d.getBitmap();
	    Bitmap dst = Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), m, false);
//	    dst.setDensity(DisplayMetrics.DENSITY_DEFAULT);
	    return  (dst);
	}
	public void back(final String title,final String message)
	{
		
    AlertDialog.Builder builder=	new AlertDialog.Builder(CurlActivity.this);
    builder.setIcon(android.R.drawable.ic_dialog_alert);

    String yes="Yes",no="No";
int temp,temp1;
		if(flipper.getDisplayedChild()==0)
		{
			temp=(-1)*(mCurlView.getCurrentIndex()-Constants.TOTAL_PAGES);
			temp1=temp+1;
		}
		else
		{
			temp=(-1)*(mViewPager.getCurrentItem()-1-Constants.TOTAL_PAGES);
			temp1=temp-1;
		}
    if(Constants.LANGUAGE_ID==Constants.ENGLISH)

    {
        builder.setTitle(title);

        builder.setMessage(message);
    }
    else
    {
    	yes=Constants.urdu_array[33];
    	no=Constants.urdu_array[34];
    	builder.setTitle("تلاوت والے صفحہ کو محفوظ کریں");
    
    builder.setMessage("کیا آپ اس صفحہ کو محفوظ کرنا چاہتے ہیں؟");
    	
    }
        
        builder.setPositiveButton(yes, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                //Stop the activity


//                finish();
                
                
                if(flipper.getDisplayedChild()==0)
        		{
        			lastread=mCurlView.getCurrentIndex()-1;
        		}
        		else
        		{
        			lastread=mViewPager.getCurrentItem();
        		}
                
//                Toast.makeText(getApplicationContext(),""+lastread,Toast.LENGTH_SHORT).show();
                
                if(flipper.getDisplayedChild()==0)
                Constants.editor.putInt("lastread", Constants.TOTAL_PAGES-lastread);
                else Constants.editor.putInt("lastread", Constants.TOTAL_PAGES-lastread);
                
                
                Constants.editor.commit();

                DatabaseHandler db = new DatabaseHandler(CurlActivity.this);
				
				String str="";
				 if(flipper.getDisplayedChild()==0)
				        str=""+lastread;// (mBitmapIds1.length-mCurlView.getCurrentIndex());
				 else str=""+mViewPager.getCurrentItem();
				
				 
				 
				 if(bookmarkPage.trim().length()!=0)
				 {
					 
					
					 db.deleteExistingBookmarkByName(bookmarkPage);
				 }
//				 else
//				 {
//					
//				 }
				 
				 db.deleteExistingBookmark(str);
					 
				       if(true)//db.getContactsCount(""+str)==0)
				    	{
				    	    db = new DatabaseHandler(CurlActivity.this);
					        db.addContact(new Contact("", ""+str,Constants.getCurrentTimeStamp()));
					        db.close();
				    	}

                
        Toast.makeText(getApplicationContext(),"Added to bookmark",Toast.LENGTH_SHORT).show();
                
                
                
                
                
                
                
                
            	
            }

        })
       .setNegativeButton(no, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                //Stop the activity

            	finish();
            }

        })
        
        .show();
		
		
//		try
//		{
//			ActivityAnimator anim = new ActivityAnimator();
//			anim.getClass().getMethod("appearBottomRight" + "Animation", Activity.class).invoke(anim, this);
//		}
//		catch (Exception e) {
//			 
//			}
		
	}
     
    /**
     * Event Handling for Individual menu item selected
     * Identify single menu item by it's id
     * */
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item)
//    {
//
//        switch (item.getItemId())
//        {
//        case R.id.menu_bookmark:
//            // Single menu item is selected do something
//            // Ex: launching new activity/screen or show alert message
////            Toast.makeText(CurlActivity.this, "Bookmark is Selected", Toast.LENGTH_SHORT).show();
//            bookmark();
//            return true;
//
//        case R.id.menu_changemode:
//
//        	Intent intent=new Intent(Intent.ACTION_VIEW);
//        	intent.setClass(getApplicationContext(), ChangeReadMode.class);
//        	startActivityForResult(intent, 1);
//        	return true;
//
//        default:
//            return super.onOptionsItemSelected(item);
//        }
//    }
//
    public void bookmark()
    {
//    	final Dialog dialog = new Dialog(this);
//    	dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//    	dialog.setContentView(R.layout.entertitle);
//    	dialog.show();
//
//    	final EditText pageno=(EditText)dialog.findViewById(R.id.text);
//    	final TextView title=(TextView)dialog.findViewById(R.id.title);
//    	final TextView pgnotext=(TextView)dialog.findViewById(R.id.pgnotext);
//    	Constants.setFont_cipher(pageno, this);
//    	Constants.setFont_cipher(title, this);
//    	Constants.setFont_cipher(pgnotext, this);
//
//    	Button cancel=(Button)dialog.findViewById(R.id.cancel);
//    	Button apply=(Button)dialog.findViewById(R.id.apply);
//    	Constants.setFont_cipher(cancel, this);
//    	Constants.setFont_cipher(apply, this);
//    	pageno.setText(Constants.getCurrentTimeStamp());
//    cancel.setOnClickListener(new OnClickListener() {
//
//    		@Override
//    		public void onClick(View arg0) {
//    			// TODO Auto-generated method stub
//    			dialog.cancel();
//    		}
//    	});
//    apply.setOnClickListener(new OnClickListener() {
//
//    		@Override
//    		public void onClick(View arg0) {
//    			// TODO Auto-generated method stub
//
//    			if(pageno.getText().toString().trim().length()>0)
//    			{
//    				DatabaseHandler db = new DatabaseHandler(CurlActivity.this);
//
//    				String str="";
//    				 if(flipper.getDisplayedChild()==0)
//    				        str=""+lastread;// (mBitmapIds1.length-mCurlView.getCurrentIndex());
//    				 else str=""+mViewPager.getCurrentItem();
//
//
//
//
//
//
//
//
//
//    		       if(true)//db.getContactsCount(""+str)==0)
//    		    	{
//
//
//
//
//
//
//
//
//
//    		    	    db = new DatabaseHandler(CurlActivity.this);
//
//    		    	 if(bookmarkPage.trim().length()!=0)
//       				 {
//       					 db.deleteExistingBookmarkByName(bookmarkPage);
//       				 }
//
//       				 db.deleteExistingBookmark(str);
//
//
//
//
//
//    			        db.addContact(new Contact("", ""+str,pageno.getText().toString()));
//    			        dialog.cancel();
//
//
//    			        Toast toast = Toast.makeText(getApplicationContext(),"Added to bookmark",Toast.LENGTH_LONG);
//    					View view = toast.getView();
//    					view.setBackgroundColor(Color.parseColor(Constants.TOASTCOLOR));
//    					TextView text = (TextView) view.findViewById(android.R.id.message);
////    					text.setTextColor(Color.parseColor(Constants.TOASTCOLOR_TEXT));
//
//    					text.setTextColor(Color.parseColor(Constants.TOASTCOLOR_TEXT));
//    					text.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
//    					toast.show();
//
//    					finish();
//    		    	}
//    		       else
//    		       {
//
//
//
//    		    	   Toast toast = Toast.makeText(getApplicationContext(),"Already available in bookmark",Toast.LENGTH_LONG);
//    					View view = toast.getView();
//    					view.setBackgroundColor(Color.parseColor(Constants.TOASTCOLOR));
//    					TextView text = (TextView) view.findViewById(android.R.id.message);
////    					text.setTextColor(Color.parseColor(Constants.TOASTCOLOR_TEXT));
//
//    					text.setTextColor(Color.parseColor(Constants.TOASTCOLOR_TEXT));
//    					text.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
//    					toast.show();
//    		        /**
//    		         * CRUD Operations
//    		         * */
//    		        // Inserting Contacts
//
//    		       }
//
//    			}
//    			else
//    			{
//    				Toast toast = Toast.makeText(getApplicationContext(),"Please enter title",Toast.LENGTH_LONG);
//    				View view = toast.getView();
//    				view.setBackgroundColor(Color.parseColor(Constants.TOASTCOLOR));
//    				TextView text = (TextView) view.findViewById(android.R.id.message);
////    				text.setTextColor(Color.parseColor(Constants.TOASTCOLOR_TEXT));
//
//    				text.setTextColor(Color.parseColor(Constants.TOASTCOLOR_TEXT));
//    				text.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
//    				toast.show();
//
//    			}
//    		}
//    	});
    }
    public void showBookMarkDialog()
    {
    	
    	
//   AlertDialog.Builder builder= 	new AlertDialog.Builder(CurlActivity.this);
//   builder.setIcon(android.R.drawable.ic_dialog_alert);
//   if(Constants.LANGUAGE_ID==Constants.ENGLISH)
//   {
//       builder .setTitle("Please read before proceeding");
//       builder .setMessage(Constants.bookmark_text1);
//   }
//   else
//   {
//	   builder .setTitle("تلاوت والے صفحہ کو محفوظ کریں");
//       builder .setMessage(" مینو کی بٹن یا آواز کی بٹن کا استعمال کرکے سیٹنگ مینو کو کھول سکتے ہیں درج ذیل آپشن کے لئے"+
//"پڑھنے کا انداز تبدیل کریں\n"+
//"دوبارہ شروع کریں اور نشاندہی \n\n"+
//"آپ تلاوت والے صفحہ کو محفوظ کرسکتے ہیں اور جو سائڈ مینو کے دوبارہ شروع کریں والے آپشن میں مل جائے گا۔");
//   }
//      builder  .setPositiveButton("Got It", new DialogInterface.OnClickListener() {
//
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//                //Stop the activity
//
//            	Constants.editor.putBoolean("bookmark", true);
//            	Constants.editor.commit();
//
//            }
//
//        });
//       builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//                //Stop the activity
//
//
//            }
//
//        });
//
//        builder.show();

    }
    public void showreadingModeDialog()
    {
//    	new AlertDialog.Builder(CurlActivity.this)
//        .setIcon(android.R.drawable.ic_dialog_alert)
//        .setTitle("Reading Mode Setting")
//        .setMessage(Constants.bookmark_text1)
//        .setPositiveButton("Got It", new DialogInterface.OnClickListener() {
//
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//                //Stop the activity
//
//            	Constants.editor.putBoolean("bookmark", true);
//            	Constants.editor.commit();
//
//            }
//
//        })
//       .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//                //Stop the activity
//
//
//            }
//
//        })
//
//        .show();

    }

    public void showSettingDialog()
    {
//    	new AlertDialog.Builder(CurlActivity.this)
//        .setIcon(android.R.drawable.ic_dialog_alert)
//        .setTitle("Setting")
//        .setMessage("Please select an option")
//        .setPositiveButton("Add Bookmark", new DialogInterface.OnClickListener() {
//
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//                //Stop the activity
//
//            	  bookmark();
//
//
//            }
//
//        })
//         .setNeutralButton("Change Reading Mode", new DialogInterface.OnClickListener() {
//
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//
//
//            	Intent intent=new Intent(Intent.ACTION_VIEW);
//            	intent.setClass(getApplicationContext(), ChangeReadMode.class);
//            	startActivityForResult(intent, 1);
//
//            }
//
//        })
//       .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//                //Stop the activity
//
//
//            }
//
//        })
//
//        .show();
    }
    @Override
    public void onActivityResult(int req,int res,Intent data)
    {
    	if(res==100)
    	{
    		
//    		if(flipper.getDisplayedChild()==0)
//    	        Constants.editor.putInt("lastread", mBitmapIds1.length-lastread+1);
//    	        else Constants.editor.putInt("lastread", mBitmapIds1.length-lastread-1);
//    	        
    		if(flipper.getDisplayedChild()==0)
    		{
    			lastread=mCurlView.getCurrentIndex();
    		}
    		else
    		{
    			lastread=mViewPager.getCurrentItem()+1;
    		}
    		
    		
    		
//    		if(flipper.getDisplayedChild()==0)
//    		{
    			mCurlView.setCurrentIndex(lastread);
//    		}
//    		else
    			mViewPager.setCurrentItem(lastread);
    		
    		flipper.setDisplayedChild(Constants.sp.getInt("readType", 0));
    	}
    	
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) 
    { 
       if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN || keyCode == KeyEvent.KEYCODE_VOLUME_UP) { 
           // Do your thing 
    	   
//    	   showSettingDialog();

		   finish();
           return true;
       } else {
           return super.onKeyDown(keyCode, event); 
       }
    }	
	@Override
	public void onBackPressed()
	{
//	back(null);

		finish();


		if(flipper.getDisplayedChild()==0)
		{
			lastread=mCurlView.getCurrentIndex()-1;
		}
		else
		{
			lastread=mViewPager.getCurrentItem();
		}

//        Toast.makeText(getApplicationContext(),""+lastread,Toast.LENGTH_SHORT).show();

		if(flipper.getDisplayedChild()==0)
			Constants.editor.putInt("lastread", Constants.TOTAL_PAGES-lastread);
		else Constants.editor.putInt("lastread", Constants.TOTAL_PAGES-lastread);


		Constants.editor.commit();
		finish();

	}
}