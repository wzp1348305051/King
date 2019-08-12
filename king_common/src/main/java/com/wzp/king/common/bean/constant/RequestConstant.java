package com.wzp.king.common.bean.constant;

import androidx.annotation.IntRange;

/**
 * startActivityForResult的RequestCode常量
 *
 * @author wengzhipeng
 * @version v1.0, 2018/3/1
 */

public interface RequestConstant {
    /**
     * 权限请求
     */
    @IntRange(from = 0)
    int REQUEST_PERMISSION = 10000;

    /**
     * Intent请求
     */
    @IntRange(from = 0)
    int REQUEST_INTENT_OPEN_APP_SETTING = 20000;
}
