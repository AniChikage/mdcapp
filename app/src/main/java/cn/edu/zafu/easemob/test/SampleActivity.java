package cn.edu.zafu.easemob.test;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import cn.edu.zafu.easemob.CoverFlowLib.CoverFlowContainer;
import cn.edu.zafu.easemob.R;

public class SampleActivity extends Activity {

    private final List<SampleItem> sampleItems = Arrays.asList(
    );

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cfmain);

        CoverFlowContainer mContainer = (CoverFlowContainer) findViewById(R.id.pager_container);

        ViewPager pager = mContainer.getViewPager();

        PagerAdapter adapter = new SampleCoverFlowAdapter(SampleActivity.this, sampleItems);
        final TextView selectedTitle = (TextView) findViewById(R.id.selectedTitle);

        pager.setAdapter(adapter);
        pager.setOffscreenPageLimit(adapter.getCount());
        pager.setClipChildren(false);
        selectedTitle.setText(sampleItems.get(0).title);

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                selectedTitle.setText(sampleItems.get(position).title);
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });
    }


}