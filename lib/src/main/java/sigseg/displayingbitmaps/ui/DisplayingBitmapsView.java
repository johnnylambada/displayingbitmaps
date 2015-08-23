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

import sigseg.displayingbitmaps.util.ImageCache;
import sigseg.displayingbitmaps.util.ImageFetcher;

public class DisplayingBitmapsView extends android.support.v4.view.ViewPager{
    private static final String IMAGE_CACHE_DIR = "images";
    private final FragmentActivity activity;
    private final List<String> imageUrls = new ArrayList<>();
    private ImagePagerAdapter adapter;
    private ImageFetcher imageFetcher;

    public DisplayingBitmapsView(Context context) {
        this(context, null);
    }

    public DisplayingBitmapsView(Context context, AttributeSet attrs) {
        super(context, attrs);

        activity = (FragmentActivity)context;

        ImageCache.ImageCacheParams cacheParams =
                new ImageCache.ImageCacheParams(context, IMAGE_CACHE_DIR);
        cacheParams.setMemCacheSizePercent(0.25f); // Set memory cache to 25% of app memory
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        final int height = displayMetrics.heightPixels;
        final int width = displayMetrics.widthPixels;
        final int longest = (height > width ? height : width) / 2;


        // The ImageFetcher takes care of loading images into our ImageView children asynchronously
        imageFetcher = new ImageFetcher(context, longest);
        imageFetcher.addImageCache(activity.getSupportFragmentManager(), cacheParams);
        imageFetcher.setImageFadeIn(false);

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

    public ImageFetcher getImageFetcher(){
        return imageFetcher;
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
