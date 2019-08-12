package com.wzp.king.common.util;

import com.wzp.king.common.bean.constant.ExceptionConstant;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import androidx.annotation.NonNull;

/**
 * 价格工具类
 *
 * @author wengzhipeng
 * @version v1.0, 2018/3/24
 */

public class MoneyUtil {
    // 人民币符号
    public static final String SYMBOL_RMB = "¥";
    // 保留两位小数，补上0
    private static final String PATTERN_WITH_ZERO = "0.00";
    // 保留两位小数，去除多余的0
    private static final String PATTERN_WITHOUT_ZERO = "0.##";

    private MoneyUtil() {
        throw new UnsupportedOperationException(ExceptionConstant.EXCEPTION_INVALID_INIT);
    }

    /**
     * 格式化金额，截取两位小数，去掉结尾的0
     */
    @NonNull
    public static String symbolMoneyWithoutZero(int fen) {
        return SYMBOL_RMB + formatMoneyWithoutZero(fen);
    }

    /**
     * 格式化金额，截取两位小数，去掉结尾的0
     */
    @NonNull
    public static String symbolMoneyWithoutZero(double yuan) {
        return SYMBOL_RMB + formatMoneyWithoutZero(yuan);
    }

    /**
     * 格式化金额，截取两位小数，去掉结尾的0
     */
    @NonNull
    public static String formatMoneyWithoutZero(int fen) {
        return formatMoneyWithoutZero(ArithmeticUtil.div(fen, 100.0D));
    }

    /**
     * 格式化金额，截取两位小数，去掉结尾的0
     */
    @NonNull
    public static String formatMoneyWithoutZero(double yuan) {
        return formatMoney(yuan, PATTERN_WITHOUT_ZERO);
    }

    /**
     * 格式化金额，截取两位小数，不足补0
     */
    @NonNull
    public static String symbolMoneyWithZero(int fen) {
        return SYMBOL_RMB + formatMoneyWithZero(fen);
    }

    /**
     * 格式化金额，截取两位小数，不足补0
     */
    @NonNull
    public static String symbolMoneyWithZero(double yuan) {
        return SYMBOL_RMB + formatMoneyWithZero(yuan);
    }

    /**
     * 格式化金额，截取两位小数，不足补0
     */
    @NonNull
    public static String formatMoneyWithZero(int fen) {
        return formatMoneyWithZero(ArithmeticUtil.div(fen, 100.0D));
    }

    /**
     * 格式化金额，截取两位小数，不足补0
     */
    @NonNull
    public static String formatMoneyWithZero(double yuan) {
        return formatMoney(yuan, PATTERN_WITH_ZERO);
    }

    /**
     * 将元转成分
     */
    public static int transYuanToFen(double yuan) {
        BigDecimal decimalYuan = new BigDecimal(Double.toString(yuan));
        BigDecimal decimalRatio = new BigDecimal(Double.toString(100D));
        return decimalYuan.multiply(decimalRatio).intValue();
    }

    /**
     * 将分转成元
     */
    public static double transFenToYuan(int fen) {
        BigDecimal decimalFen = new BigDecimal(Integer.toString(fen));
        BigDecimal decimalRatio = new BigDecimal(Double.toString(100D));
        return decimalFen.divide(decimalRatio, 2, RoundingMode.CEILING).doubleValue();
    }

    @NonNull
    private static String formatMoney(double price, String pattern) {
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        decimalFormat.setRoundingMode(RoundingMode.DOWN);
        return decimalFormat.format(price);
    }
}

