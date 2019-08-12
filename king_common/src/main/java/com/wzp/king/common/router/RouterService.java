package com.wzp.king.common.router;

import com.alibaba.android.arouter.facade.template.IProvider;

/**
 * 服务声明, 使用方式
 * <p>
 * 方式一: ARouter.getInstance().navigation(RouterService.class).executeService();
 * <p>
 * 方式二: ((RouterService) ARouter.getInstance().build(RouterPath.SERVICE_ROUTER).navigation()).executeService();
 *
 * @author wengzhipeng
 * @version v1.0, 2019/6/27
 */

public interface RouterService extends IProvider {
    void executeService();
}
