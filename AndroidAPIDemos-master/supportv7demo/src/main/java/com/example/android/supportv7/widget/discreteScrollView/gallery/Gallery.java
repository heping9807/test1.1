package com.example.android.supportv7.widget.discreteScrollView.gallery;

import com.example.android.supportv7.R;

import java.util.Arrays;
import java.util.List;

public class Gallery {
    public static Gallery get() {
        return new Gallery();
    }

    private Gallery() {
    }

    public List<Image> getData() {
        return Arrays.asList(
                new Image(R.drawable.shop1),
                new Image(R.drawable.shop2),
                new Image(R.drawable.shop3),
                new Image(R.drawable.shop4),
                new Image(R.drawable.shop5),
                new Image(R.drawable.shop6));
    }
}
