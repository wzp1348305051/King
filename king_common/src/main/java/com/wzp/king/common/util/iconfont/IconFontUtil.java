package com.wzp.king.common.util.iconfont;

import com.joanzapata.iconify.Iconify;

/**
 * IconFont工具类
 *
 * @author wengzhipeng
 * @version v1.0, 2019/6/29
 */
public class IconFontUtil {

    public static void init() {
        Iconify.with(new IconFontModule());
    }
}
