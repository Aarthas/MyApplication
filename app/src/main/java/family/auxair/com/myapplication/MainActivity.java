package family.auxair.com.myapplication;

import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;


import trikita.anvil.Anvil;
import trikita.anvil.DSL;
import trikita.anvil.RenderableView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RenderableView renderableView = new RenderableView(this) {
            @Override
            public void view() {

            }
        };
        RenderableView view = new RenderableView(this) {
            @Override
            public void view() {
                 DSL.relativeLayout(new Anvil.Renderable() {
                    @Override
                    public void view() {
                        DSL.size(DSL.MATCH, DSL.WRAP);
                        DSL.imageView(new Anvil.Renderable() {
                            @Override
                            public void view() {
                                DSL.id(1);
                                DSL.imageResource(R.mipmap.ic_launcher);
                                DSL.centerVertical();
                            }
                        });
                        DSL.textView(() -> {
                            DSL.text("aaa");
                            DSL.centerVertical();
                            DSL.toRightOf(1);
                        });
                        DSL.xml(R.layout.popup_custom3);
                    }
                });
//                linearLayout(new Anvil.Renderable() {
//                    @Override
//                    public void view() {
//                        size(MATCH, MATCH);
//                        padding(dip(8));
//                        orientation(LinearLayout.VERTICAL);
//                        textView(() -> {
//                            size(MATCH, WRAP);
//                            text("Tick-tock: " + 1);
//
//                        });
//
//                        button(() -> {
//                            size(MATCH, WRAP);
//                            text("Close");
//                            // Finish current activity when the button is clicked
//                            onClick(v -> finish());
//                        });
//                    }
//                });
            }
        };


        setContentView(view);
    }

}
