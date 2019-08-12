package com.wzp.king.common.util.iconfont;

import com.joanzapata.iconify.Icon;

/**
 * 自定义IconFont集合
 *
 * @author wengzhipeng
 * @version v1.0, 2019/6/29
 */
public enum IconFont implements Icon {

    yit_menu('\ue656'),
    yit_back('\ue657'),
    yit_next('\ue661'),
    yit_qr_code('\ue62e'),
    yit_setting('\ue658'),
    yit_scan('\ue65a'),
    yit_search('\ue644'),
    yit_delete('\ue65f'),
    yit_pwd_visible('\ue65b'),
    yit_pwd_invisible('\ue659');

    char character;

    IconFont(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name();
    }

    @Override
    public char character() {
        return character;
    }
}
