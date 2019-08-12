package com.wzp.king.common.router;

import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.PretreatmentService;

/**
 * 全局预处理服务, @Route(path = "/service/pretreatment")中path内容随便填写
 *
 * @author wengzhipeng
 * @version v1.0, 2019/6/27
 */

@Route(path = "/service/pretreatment")
public class PretreatmentServiceImpl implements PretreatmentService {

    /**
     * 跳转前预处理, 如需拦截服务, 该方法返回 false 即可
     */
    @Override
    public boolean onPretreatment(Context context, Postcard postcard) {
        return true;
    }

    @Override
    public void init(Context context) {

    }
}
