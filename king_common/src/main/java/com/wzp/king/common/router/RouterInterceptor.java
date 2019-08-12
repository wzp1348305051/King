package com.wzp.king.common.router;

import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;

/**
 * ARouter全局拦截器, priority值越小, 权重越高
 *
 * @author wengzhipeng
 * @version v1.0, 2019/6/27
 */

@Interceptor(priority = 8, name = "GlobalRouterInterceptor")
public class RouterInterceptor implements IInterceptor {

    /**
     * callback.onContinue 处理完成, 交还控制权
     * callback.onInterrupt 中断路由流程
     */
    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        // todo 自定义拦截处理
        callback.onContinue(postcard);// 处理完成, 交还控制权
    }

    /**
     * 拦截器初始化, 会在sdk初始化时调用该方法, 仅会调用一次
     */
    @Override
    public void init(Context context) {

    }
}
