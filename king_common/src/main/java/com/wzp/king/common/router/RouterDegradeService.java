package com.wzp.king.common.router;

import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.DegradeService;
import com.wzp.king.common.util.ToastUtil;

import androidx.annotation.Nullable;

/**
 * ARouter全局降级策略, @Route(path = "/router/degrade")中path内容随便填写
 *
 * @author wengzhipeng
 * @version v1.0, 2019/6/27
 */

@Route(path = "/router/degrade")
public class RouterDegradeService implements DegradeService {

    /**
     * 失败时处理, 注意: 如果在navigation()时候没有传递context, 这个方法的context会是null
     */
    @Override
    public void onLost(@Nullable Context context, Postcard postcard) {
        ToastUtil.showToast(context, "未找到目标页面");
    }

    @Override
    public void init(Context context) {

    }
}
