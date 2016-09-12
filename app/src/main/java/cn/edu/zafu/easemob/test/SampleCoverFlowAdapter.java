package cn.edu.zafu.easemob.test;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.edu.zafu.easemob.CoverFlowLib.AbstractCoverFlowAdapter;
import cn.edu.zafu.easemob.R;

public class SampleCoverFlowAdapter extends AbstractCoverFlowAdapter<SampleItem> {

    public SampleCoverFlowAdapter(Context context, List<SampleItem> sampleItems) {
        super(context, sampleItems);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        SampleItem item = items.get(position);

        View v = LayoutInflater.from(context).inflate(R.layout.cover_item, null);
        ImageView coverImage = (ImageView) v.findViewById(R.id.cover_image);
        coverImage.setImageResource(item.imageResourceId);
        //coverImage.setImageDrawable(item.imageResourceId);

        TextView title = (TextView) v.findViewById(R.id.title);
        title.setText(item.title);

        ImageView reflectionImage = (ImageView) v.findViewById(R.id.reflection_image);
        reflectionImage.setImageBitmap(getReflectionBitmap(((BitmapDrawable) coverImage.getDrawable()).getBitmap()));

        container.addView(v);

        return v;
    }
}