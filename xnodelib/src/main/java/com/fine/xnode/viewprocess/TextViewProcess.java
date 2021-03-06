package com.fine.xnode.viewprocess;

import android.content.Context;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.fine.xnode.util.XNodeUtil;

import java.util.HashMap;


/**
 * Created by zhangyn on 17/4/5.
 */

public class TextViewProcess extends ViewProcess {


    public View initView(Context context, ViewGroup parent, HashMap<String, Object> map) {
        TextView hostview = new TextView(context);


        return hostview;
    }


    @Override
    public void applyProperty(View hostview, String key, String value, Object modelData) {
        super.applyProperty(hostview, key, value, modelData);
        TextView textView = (TextView) hostview;
        switch (key) {
            case "text":
                textView.setText(value);
                break;
            case "textColor":
                textView.setTextColor(XNodeUtil.parseColor(value));
                break;
            case "textSize":
                if (value.endsWith("sp")) {
                    String substring = value.substring(0, value.length() - 2);
                    float textSize = Float.parseFloat(substring);
                    textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
                }

                break;
            case "hint":
                textView.setHint(value);
                break;
            case "gravity":
                if (value.equals("center"))
                    textView.setGravity(Gravity.CENTER);
                else if (value.equals("center_vertical"))
                    textView.setGravity(Gravity.CENTER_VERTICAL);
                else if (value.equals("center_horizontal"))
                    textView.setGravity(Gravity.CENTER_HORIZONTAL);
                else if (value.equals("top"))
                    textView.setGravity(Gravity.TOP);
                else if (value.equals("bottom"))
                    textView.setGravity(Gravity.BOTTOM);
                else if (value.equals("left"))
                    textView.setGravity(Gravity.LEFT);
                else if (value.equals("right"))
                    textView.setGravity(Gravity.RIGHT);
                break;
        }
    }


}
