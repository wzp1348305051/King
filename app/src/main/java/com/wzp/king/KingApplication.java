package com.wzp.king;

import com.wzp.king.common.app.AppApplication;

/**
 * 项目Application
 *
 * @author wengzhipeng
 * @version v1.0, 2019-10-22
 */
public class KingApplication extends AppApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        initLibrary();
        initThirdLibrary();
    }

    /**
     * 初始化项目库
     */
    private void initLibrary() {

    }

    /**
     * 初始化三方库
     */
    private void initThirdLibrary() {

    }
}
