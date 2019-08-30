package com.wzp.king.common.bean.constant;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 默认空值常量
 *
 * @author wengzhipeng
 * @version v1.0, 2018/3/1
 */

public class EmptyConstant {
    public static final String EMPTY_STRING = "";
    public static final String[] EMPTY_STRING_ARRAY = new String[]{};
    public static final Set<String> EMPTY_STRING_SET = new HashSet<>();
    public static final byte EMPTY_BYTE = 0;
    public static final byte[] EMPTY_BYTE_ARRAY = new byte[]{};
    public static final int EMPTY_INT = 0;
    public static final int[] EMPTY_INT_ARRAY = new int[]{};
    public static final long EMPTY_LONG = 0L;
    public static final float EMPTY_FLOAT = 0F;
    public static final double EMPTY_DOUBLE = 0D;
    public static final boolean EMPTY_BOOLEAN = false;
    public static final Set EMPTY_SET = Collections.EMPTY_SET;
    public static final List EMPTY_LIST = Collections.EMPTY_LIST;
    public static final Map EMPTY_MAP = Collections.EMPTY_MAP;
}
