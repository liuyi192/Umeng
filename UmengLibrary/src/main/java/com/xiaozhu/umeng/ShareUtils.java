package com.xiaozhu.umeng;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;

import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

/**
 * @说明 分享工具类
 * @作者 LY
 * @时间 2017/12/21 13:55
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2017 LY-版权所有
 * @备注
 */
public class ShareUtils {
    private static ShareUtils instance;

    public static ShareUtils getInstance() {
        if (instance == null) {
            synchronized (ShareUtils.class) {
                if (instance == null) {
                    instance = new ShareUtils();
                }
            }
        }
        return instance;
    }

    /**
     * 初始化分享平台
     *
     * @param context
     */
    public void initShare(Context context) {
        if (!TextUtils.isEmpty(context.getResources().getString(R.string.wx_app_id))) {
            PlatformConfig.setWeixin(context.getResources().getString(R.string.wx_app_id), context.getResources().getString(R.string.wx_app_key));
        }
        UMShareAPI.get(context);
    }

    /**
     * 分享工具类
     *
     * @param activity
     * @param title           标题
     * @param content         内容
     * @param url             链接地址
     * @param resId           图片
     * @param share_media     分享平台
     * @param umShareListener 回调监听
     */
    public void shareUtils(Activity activity, String title, String content, String url, String imgUrl, int resId, SHARE_MEDIA share_media, UMShareListener umShareListener) {
        ShareAction shareAction = new ShareAction(activity);
        if (!TextUtils.isEmpty(url)) {
            UMWeb umWeb = new UMWeb(url);
            if (!TextUtils.isEmpty(title)) {
                umWeb.setTitle(title);
            }
            if (!TextUtils.isEmpty(content)) {
                umWeb.setDescription(content);
            }
            if (resId > 0) {
                UMImage image = new UMImage(activity, resId);
                umWeb.setThumb(image);
            }
            if (!TextUtils.isEmpty(imgUrl)) {
                UMImage image = new UMImage(activity, imgUrl);
                umWeb.setThumb(image);
            }
            shareAction.withMedia(umWeb);
        }
        shareAction.setPlatform(share_media).setCallback(umShareListener).share();
    }
}
