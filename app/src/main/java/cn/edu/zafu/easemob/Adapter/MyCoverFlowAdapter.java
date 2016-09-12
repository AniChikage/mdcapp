package cn.edu.zafu.easemob.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import cn.edu.zafu.easemob.imagecoverflow.CoverFlowAdapter;
import cn.edu.zafu.easemob.imagecoverflow.CoverFlowView;

import cn.edu.zafu.easemob.R;

public class MyCoverFlowAdapter extends CoverFlowAdapter {

    private boolean dataChanged;

    public MyCoverFlowAdapter(Context context) {

        image1 = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.footprint_header_bg1);

        image2 = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.anli);
    }

    public void changeBitmap() {
        dataChanged = true;

        notifyDataSetChanged();
    }

    private Bitmap image1 = null;

    private Bitmap image2 = null;

    @Override
    public int getCount() {
        return dataChanged ? 3 : 8;
    }

    @Override
    public Bitmap getImage(final int position) {
        return (dataChanged && position == 0) ? image2 : image1;
    }
}
