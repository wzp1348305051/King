package com.wzp.king.common.util.iconfont;

import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.IconFontDescriptor;

/**
 * IconFont文件模块
 *
 * @author wengzhipeng
 * @version v1.0, 2019/6/29
 */
public class IconFontModule implements IconFontDescriptor {

    @Override
    public String ttfFileName() {
        return "font/icon_font.ttf";
    }

    @Override
    public Icon[] characters() {
        return IconFont.values();
    }
}
