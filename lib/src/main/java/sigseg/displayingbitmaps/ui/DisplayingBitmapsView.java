package sigseg.displayingbitmaps.ui;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.AttributeSet;
import android.util.DisplayMetrics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DisplayingBitmapsView extends android.support.v4.view.ViewPager{
    private static final String IMAGE_CACHE_DIR = "images";
    private final FragmentActivity activity;
    private final List<String> imageUrls = new ArrayList<>();
    private ImagePagerAdapter adapter;

    public DisplayingBitmapsView(Context context) {
        this(context, null);
    }

    public DisplayingBitmapsView(Context context, AttributeSet attrs) {
        super(context, attrs);

        activity = (FragmentActivity)context;

        adapter = new ImagePagerAdapter(activity.getSupportFragmentManager());
        setAdapter(adapter);
        setOffscreenPageLimit(2);

    }

    public void addImageUrls(String[] imageUrls){
        for(String s : imageUrls)
            this.imageUrls.add(s);
        adapter.notifyDataSetChanged();
    }

    public void addImageUrls(Collection<? extends String> imageUrls){
        this.imageUrls.addAll(imageUrls);
        adapter.notifyDataSetChanged();
    }

    /**
     * The main adapter that backs the ViewPager. A subclass of FragmentStatePagerAdapter as there
     * could be a large number of items in the ViewPager and we don't want to retain them all in
     * memory at once but create/destroy them on the fly.
     */
    private class ImagePagerAdapter extends FragmentStatePagerAdapter {

        public ImagePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return imageUrls.size();
        }

        @Override
        public Fragment getItem(int position) {
            return ImageDetailFragment.newInstance(imageUrls.get(position));
        }
    }

}
