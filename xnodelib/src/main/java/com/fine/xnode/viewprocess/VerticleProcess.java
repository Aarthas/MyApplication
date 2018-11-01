package com.fine.xnode.viewprocess;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.HashMap;


/**
 * Created by zhangyn on 17/4/5.
 */

public class VerticleProcess extends ViewProcess {


    @Override
    public View initView(Context context, ViewGroup parent, HashMap<String, Object> map) {
        LinearLayout hostview = new LinearLayout(context);
        hostview.setOrientation(LinearLayout.VERTICAL);

        return hostview;
    }



    @Override
    public void applyProperty(View hostview, String key, String value, Object modelData) {
        super.applyProperty(hostview, key, value, modelData);

    }
}
