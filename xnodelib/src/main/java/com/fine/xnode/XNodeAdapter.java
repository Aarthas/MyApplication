package com.fine.xnode;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.fine.xnode.base.BaseModelData;

public class XNodeAdapter {
    private final Context context;
    public Object modelData;

    public void setModelData(Object modelData) {
        this.modelData = modelData;
    }

    public Context getContext() {
        return context;
    }

    public Object getModelData() {
        return modelData;
    }

    public XNodeAdapter(Context context) {
        this.context=context;
    }

    public View parse(XNode xNode, ViewGroup parent) {
        XNodeService xNodeService = new XNodeService(this);
        View contentView = xNodeService.createView(xNode,parent);
        parent.addView(contentView);
        return contentView;
    }
}
