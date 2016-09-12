package cn.edu.zafu.easemob.test;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TabHost;

import cn.edu.zafu.easemob.R;

/**
 * Created by AniChikage on 2016/7/13.
 */
public class Mpcontent extends Activity{

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mpcontent);

        init();
    }

    private void init(){
        TabHost tabHost = (TabHost) findViewById(R.id.mpmyTabHost);
        tabHost.setup();
        tabHost.addTab(tabHost.newTabSpec("tab1")
                .setIndicator("首页")
                .setContent(R.id.mponemp));
        tabHost.addTab(tabHost.newTabSpec("tab2")
                .setIndicator("咨询师")
                .setContent(R.id.mponeconsuler));
        tabHost.addTab(tabHost.newTabSpec("tab3")
                .setIndicator("问答")
                .setContent(R.id.mponeqanda));
        tabHost.addTab(tabHost.newTabSpec("tab4")
                .setIndicator("我的")
                .setContent(R.id.mponeperson));
    }
}
