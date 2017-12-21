package com.xiaozhu.umeng;

import android.content.Context;

import com.umeng.analytics.MobclickAgent;

/**
 * @说明 友盟工具类
 * @作者 LY
 * @时间 2017/12/19 15:34
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2017 LY-版权所有
 * @备注
 */
public class UmengUtils {
    private static UmengUtils mInstance;

    public static UmengUtils getInstance() {
        if (mInstance == null) {
            synchronized (UmengUtils.class) {
                if (mInstance == null) {
                    mInstance = new UmengUtils();
                }
            }
        }
        return mInstance;
    }

    /**
     * 初始化数据统计
     *
     * @param context
     * @param appKey
     * @param channelId
     */
    public void init(Context context, String appKey, String channelId) {
        MobclickAgent.UMAnalyticsConfig config = new MobclickAgent.UMAnalyticsConfig(context, appKey, channelId);
        MobclickAgent.startWithConfigure(config);
    }

    /**
     * 数据统计
     *
     * @param context
     */
    public void onResume(Context context) {
        MobclickAgent.onResume(context);
    }

    /**
     * 数据统计
     *
     * @param context
     */
    public void onPause(Context context) {
        MobclickAgent.onPause(context);
    }

    /**
     * 错误日志统计
     *
     * @param context
     * @param error
     */
    public void sendError(Context context, String error) {
        MobclickAgent.reportError(context, error);
    }
}
