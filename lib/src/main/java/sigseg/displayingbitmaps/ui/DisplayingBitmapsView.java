/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sigseg.displayingbitmaps.ui;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.List;

public class DisplayingBitmapsView extends android.support.v4.view.ViewPager{
    private final FragmentActivity activity;
    private final List<String> imageUrls = new ArrayList<>();
    private final ImagePagerAdapter adapter;

    public DisplayingBitmapsView(Context context) {
        this(context, null);
    }

    public DisplayingBitmapsView(Context context, AttributeSet attrs) {
        super(context, attrs);

        activity = (FragmentActivity)context;

        adapter = new ImagePagerAdapter();
        setAdapter(adapter);
        setOffscreenPageLimit(2);

    }

    public void addImageUrls(String[] imageUrls){
        for(String s : imageUrls)
            this.imageUrls.add(s);
        adapter.notifyDataSetChanged();
    }

    /**
     * The main adapter that backs the ViewPager. A subclass of FragmentStatePagerAdapter as there
     * could be a large number of items in the ViewPager and we don't want to retain them all in
     * memory at once but create/destroy them on the fly.
     */
    private class ImagePagerAdapter extends FragmentStatePagerAdapter {

        public ImagePagerAdapter() {
            super(activity.getSupportFragmentManager());
        }

        @Override
        public int getCount() {
            return imageUrls.size();
        }

        @Override
        public Fragment getItem(int position) {
            return DisplayingBitmapsFragment.newInstance(imageUrls.get(position));
        }
    }

}
