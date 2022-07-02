package com.fonts;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by admin on 7/26/2016.
 */
public class AlviNastaLiqBold extends TextView
{
    static Typeface mTypeFace = null;


    public AlviNastaLiqBold(Context context, AttributeSet attrs, int defStyleAttr)
    {
        // Return Super
        super(context, attrs, defStyleAttr);

        // Load and Set Font
        initTypeFace();
    }

    public AlviNastaLiqBold(Context context, AttributeSet attrs)
    {
        // Return Super
        super(context, attrs);

        // Load and Set Font
        initTypeFace();
    }

    public AlviNastaLiqBold(Context context)
    {
        // Return Super
        super(context);

        // Load and Set Font
        initTypeFace();
    }

    private void initTypeFace()
    {
        if(!isInEditMode() && mTypeFace == null)
        {
            mTypeFace = Typeface.createFromAsset(getContext().getAssets(), "alvi_nastaleeq.ttf");
        }

        if(!isInEditMode())
        {
            setTypeface(mTypeFace, Typeface.BOLD);
        }
    }

}

