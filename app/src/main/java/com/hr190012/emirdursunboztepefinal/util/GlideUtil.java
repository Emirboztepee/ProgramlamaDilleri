package com.hr190012.emirdursunboztepefinal.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hr190012.emirdursunboztepefinal.R;


public class GlideUtil {

    public  static  void resmiIndiripGoster(Context context, String url, ImageView img)
    {
        Glide.with(context)
                .load(url)
                .centerCrop()
                .into(img);
    }

}
