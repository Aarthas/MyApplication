package com.fine.xnode.viewprocess;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import java.util.HashMap;

/**
 * Created by zhangyn on 17/4/5.
 */

public class ScrollViewProcess extends ViewProcess {


    @Override
    public View initView(Context context, ViewGroup parent, HashMap<String, Object> map) {
        ScrollView hostview = new ScrollView(context);
        return hostview;
    }



    @Override
    public void applyProperty(View hostview, String key, String value, Object modelData) {
        super.applyProperty(hostview,key,value, modelData);
    }
}
