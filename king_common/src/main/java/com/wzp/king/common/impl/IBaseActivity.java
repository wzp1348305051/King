package com.wzp.king.common.impl;

import android.os.Bundle;

/**
 * 初始化Activity View相关方法实现
 *
 * @author wengzhipeng
 * @version v1.0, 2019/6/26
 */

public interface IBaseActivity {

    /**
     * 获取活动对应布局ID
     */
    int getContentView();

    /**
     * 初始化布局元素
     */
    void initContentView();

    /**
     * 初始化类成员变量
     */
    void initMemberData();

    /**
     * 绑定布局元素
     */
    void bindContentView(Bundle savedInstanceState);
}
