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

package com.example.android.displayingbitmaps.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.android.displayingbitmaps.R;
import com.example.android.displayingbitmaps.provider.Images;

import sigseg.displayingbitmaps.ui.DisplayingBitmapsView;

public class ImageDetailActivity extends FragmentActivity {

    private DisplayingBitmapsView pager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_detail_pager);

        // Set up ViewPager
        pager = (DisplayingBitmapsView) findViewById(R.id.pager);
        pager.setPageMargin((int) getResources().getDimension(R.dimen.horizontal_page_margin));
        pager.setFragmentManager(getSupportFragmentManager());
        pager.addImageUrls(Images.imageUrls);
    }

}
