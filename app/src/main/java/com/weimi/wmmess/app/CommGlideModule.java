package com.weimi.wmmess.app;

import android.content.Context;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;
import com.weimi.wmmess.R;

/**
 * Created by Jason on 2018/8/10.
 */
@GlideModule
public final class CommGlideModule extends AppGlideModule {

    /**
     * MemorySizeCalculator类通过考虑设备给定的可用内存和屏幕大小想出合理的默认大小.
     * 通过LruResourceCache进行缓存。
     *
     * @param context
     * @param builder
     */
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        int diskCacheSizeBytes = 1024 * 1024 * 100; // 100 MB
        // MemorySizeCalculator类通过考虑设备给定的可用内存和屏幕大小想出合理的默认大小
        MemorySizeCalculator calculator = new MemorySizeCalculator.Builder(context)
                .setMemoryCacheScreens(2)
                .build();

        builder.setMemoryCache(new LruResourceCache(calculator.getMemoryCacheSize()))
                .setDiskCache(new InternalCacheDiskCacheFactory(context, diskCacheSizeBytes))
                .setDefaultRequestOptions(
                        new RequestOptions()
                                .centerCrop()
                                .placeholder(R.color.bg)
                                .error(R.color.bg));
    }
}