package com.fine.xnode.factory;

import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.fine.xnode.TagConfigHelp;
import com.fine.xnode.XNode;
import com.fine.xnode.XNodeAdapter;
import com.fine.xnode.base.BaseFactory;
import com.fine.xnode.base.BaseModelData;
import com.fine.xnode.base.BseViewComponent;
import com.fine.xnode.livedata.MyLiveData;
import com.fine.xnode.livedata.Observer;
import com.fine.xnode.util.XNodeUtil;

import java.util.HashMap;
import java.util.Set;

import rx.subjects.PublishSubject;
import zz.base.lib.log.Finelog;


public class ViewFactory extends BaseFactory {

    private static ViewFactory viewFactory;

    public static ViewFactory get() {
        if (viewFactory == null)
            viewFactory = new ViewFactory();
        return viewFactory;
    }

    @Override
    public View createView(XNode XNode, ViewGroup parent, XNodeAdapter xNodeAdapter) {
        Finelog.d("createView node = " + XNode.tag);
        Finelog.d(XNode);
        String tag = XNode.tag;
        HashMap<String, Object> attr = XNode.attr;
        BseViewComponent bseViewComponent = TagConfigHelp.get(tag);

        final View view = bseViewComponent.initView(xNodeAdapter.getContext(), parent, attr);
        if (view.getLayoutParams() == null) {
            ViewGroup.LayoutParams params = XNodeUtil.getLayoutParams(parent, attr);
            view.setLayoutParams(params);
        }


        final Object modelData = xNodeAdapter.getModelData();
        if (view != null) {
            if (attr != null) {
                Set<String> keys = attr.keySet();
                for (final String key : keys) {
                    final Object value = attr.get(key);
                    if (key.startsWith("bind-")) {
                        Finelog.d("start get  bind- = " + key);
                        final String realKey = key.substring(5, key.length());
                        Finelog.d("start get fieldName= " + realKey);
                        try {
                            MyLiveData<String> livedata = getLiveData(modelData, value);
                            Finelog.d("start get livedata= " + livedata);
                            final BseViewComponent finalBseViewComponent = bseViewComponent;
                            livedata.observeForever(new Observer<String>() {
                                @Override
                                public void onChanged(@Nullable String realValue) {
                                    finalBseViewComponent.applyProperty(view, realKey, realValue,modelData );
                                }
                            });

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
//                else if (key.startsWith("prop-")) {
//                    Finelog.d("start get  bind- = " + key);
//                    final String realKey = key.substring(5, key.length());
//                    Finelog.d("start get fieldName= " + realKey);
//                    try {
//                        String realValue = modelData.propMap.get(value);
//                        bseViewComponent.applyProperty(view, realKey, realValue);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
                    else if (key.startsWith("data")) {
                        Finelog.d("start get  data = " + key);
                        try {
                            MyLiveData<String> livedata = getLiveData(modelData, value);

                            bseViewComponent.applyValue(view, livedata);
                            bseViewComponent.emitValue(view, livedata);


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (key.startsWith("vshow")) {
                        Finelog.d("start get  data = " + key);
                        try {
                            MyLiveData<Boolean> livedata = getLiveData(modelData, value);
                            livedata.observeForever(new Observer<Boolean>() {
                                @Override
                                public void onChanged(@Nullable Boolean aBoolean) {
                                    if (aBoolean) {
                                        view.setVisibility(View.VISIBLE);
                                    } else {
                                        view.setVisibility(View.GONE);

                                    }
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (key.startsWith("click")) {
                        try {
                            final PublishSubject subject = getSubject(modelData, value);
                            view.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    subject.onNext(v);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        bseViewComponent.applyProperty(view, key, (String) value, modelData);
                    }


                }
            }
            if (XNode.child != null) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (XNode child : XNode.child) {
                    View view1 = createView(child, viewGroup, xNodeAdapter);

                    if (view1 != null) {
                        if (view1.getLayoutParams() == null) {
                            ViewGroup.LayoutParams params3 = XNodeUtil.getLayoutParams(viewGroup, child.attr);
                            view1.setLayoutParams(params3);

                        }
                        viewGroup.addView(view1);


                    } else {
                        Finelog.e(child.tag + "  为空");
                    }

                }
            }


            return view;

        }
        return null;

    }

    private MyLiveData getLiveData(Object modelData, Object value) {
        if (value instanceof String) {
            String name = (String) value;
            Object object =  BaseModelData.getByReflect(modelData,name);
            MyLiveData o = (MyLiveData) object;

            return o;
        } else if (value instanceof MyLiveData) {
            MyLiveData value1 = (MyLiveData) value;
            return value1;
        }
        return null;
    }
    private PublishSubject getSubject(Object modelData, Object value) {
        if (value instanceof String) {
            String name = (String) value;
            PublishSubject object = (PublishSubject) BaseModelData.getByReflect(modelData,name);
            return object;
        }
        return null;
    }
}
