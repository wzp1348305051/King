package com.wzp.king.common.bean;

import android.os.Bundle;

/**
 * 元组
 *
 * @author wengzhipeng
 * @version v1.0, 2019-11-25
 */
public class Tuple<A, B> {
    public final A mFirst;
    public final B mSecond;
    public final Bundle mBundle;

    public Tuple(A first, B second) {
        this(first, second, null);
    }

    public Tuple(A first, B second, Bundle bundle) {
        mFirst = first;
        mSecond = second;
        mBundle = bundle;
    }
}
