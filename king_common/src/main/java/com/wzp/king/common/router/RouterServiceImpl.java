package com.wzp.king.common.router;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;

/**
 * 服务实现
 *
 * @author wengzhipeng
 * @version v1.0, 2019/6/27
 */

@Route(path = RouterPath.SERVICE_ROUTER, name = "路由服务")
public class RouterServiceImpl implements RouterService {

    @Override
    public void executeService() {

    }

    @Override
    public void init(Context context) {

    }
}
