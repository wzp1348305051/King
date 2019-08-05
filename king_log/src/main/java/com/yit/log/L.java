package com.yit.log;

import android.text.TextUtils;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.LogcatLogStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 日志工具类, 使用Logger作为默认日志处理工具, BugLy作为默认日志上传工具, 可按需替换Logger, BugLy.
 *
 * @author wengzhipeng
 * @version v1.0, 2019/6/18
 */

public class L {
    static {
        FormatStrategy FORMAT_STRATEGY = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)// (Optional) Whether to show thread info or not. Default true
                .methodCount(0)// (Optional) How many method line to show. Default 2
                .methodOffset(8)// (Optional) Hides internal method calls up to offset. Default 5
                .logStrategy(new LogcatLogStrategy())// (Optional) Changes the log strategy to print out. Default LogCat
                .tag("YIT_MOBILE")// (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build();
        AndroidLogAdapter LOG_ADAPTER = new AndroidLogAdapter(FORMAT_STRATEGY) {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return BuildConfig.DEBUG;
            }
        };

        Logger.addLogAdapter(LOG_ADAPTER);
    }

    private L() {
        throw new UnsupportedOperationException("INVALID INIT EXCEPTION");
    }

    public static void log(int priority, @NonNull String tag, @NonNull String message) {
        log(priority, tag, message, null);
    }

    /**
     * General log function that accepts all configurations as parameter
     */
    public static void log(int priority, @Nullable String tag, @Nullable String message,
                           @Nullable Throwable throwable) {
        Logger.log(priority, tag, message, throwable);
    }

    /**
     * @param object 消息体, 支持STRING, MAP, SET, LIST, ARRAY
     */
    public static void d(@Nullable Object object) {
        Logger.d(object);
    }

    /**
     * 示例：d("msg: %s, %s", arg1, arg2)
     *
     * @param message 消息体
     * @param args    消息体中占位符代表的参数
     */
    public static void d(@NonNull String message, @Nullable Object... args) {
        Logger.d(message, args);
    }

    /**
     * @param object 消息体, 支持STRING, MAP, SET, LIST, ARRAY
     */
    public static void dTag(@NonNull String tag, @Nullable Object object) {
        log(Logger.DEBUG, tag, toString(object));
    }

    /**
     * 示例：dTag("msg: %s, %s", arg1, arg2)
     *
     * @param message 消息体
     * @param args    消息体中占位符代表的参数
     */
    public static void dTag(@NonNull String tag, @NonNull String message, @Nullable Object... args) {
        log(Logger.DEBUG, tag, createMessage(message, args));
    }

    public static void e(@NonNull Throwable throwable) {
        e(throwable, throwable.getMessage());
    }

    /**
     * 示例：e("msg: %s, %s", arg1, arg2)
     *
     * @param message 消息体
     * @param args    消息体中占位符代表的参数
     */
    public static void e(@NonNull String message, @Nullable Object... args) {
        e(new RuntimeException(createMessage(message, args)), message, args);
    }

    /**
     * 示例：e("msg: %s, %s", arg1, arg2)
     *
     * @param throwable 异常
     * @param message   消息体
     * @param args      消息体中占位符代表的参数
     */
    public static void e(@Nullable Throwable throwable, @NonNull String message, @Nullable Object... args) {
        Logger.e(throwable, message, args);
    }

    public static void eTag(@NonNull String tag, @NonNull Throwable throwable) {
        eTag(tag, throwable, throwable.getMessage());
    }

    /**
     * 示例：eTag("msg: %s, %s", arg1, arg2)
     *
     * @param message 消息体
     * @param args    消息体中占位符代表的参数
     */
    public static void eTag(@NonNull String tag, @NonNull String message, @Nullable Object... args) {
        eTag(tag, new RuntimeException(createMessage(message, args)), message, args);
    }

    /**
     * 示例：eTag("msg: %s, %s", arg1, arg2)
     *
     * @param message 消息体
     * @param args    消息体中占位符代表的参数
     */
    public static void eTag(@NonNull String tag, @Nullable Throwable throwable, @NonNull String message,
                            @Nullable Object... args) {
        log(Logger.ERROR, tag, createMessage(message, args), throwable);
    }

    /**
     * 示例：i("msg: %s, %s", arg1, arg2)
     *
     * @param message 消息体
     * @param args    消息体中占位符代表的参数
     */
    public static void i(@NonNull String message, @Nullable Object... args) {
        Logger.i(message, args);
    }

    /**
     * 示例：iTag("msg: %s, %s", arg1, arg2)
     *
     * @param message 消息体
     * @param args    消息体中占位符代表的参数
     */
    public static void iTag(@NonNull String tag, @NonNull String message, @Nullable Object... args) {
        log(Logger.INFO, tag, createMessage(message, args));
    }

    /**
     * 示例：v("msg: %s, %s", arg1, arg2)
     *
     * @param message 消息体
     * @param args    消息体中占位符代表的参数
     */
    public static void v(@NonNull String message, @Nullable Object... args) {
        Logger.v(message, args);
    }

    /**
     * 示例：vTag("msg: %s, %s", arg1, arg2)
     *
     * @param message 消息体
     * @param args    消息体中占位符代表的参数
     */
    public static void vTag(@NonNull String tag, @NonNull String message, @Nullable Object... args) {
        log(Logger.VERBOSE, tag, createMessage(message, args));
    }

    /**
     * 示例：w("msg: %s, %s", arg1, arg2)
     *
     * @param message 消息体
     * @param args    消息体中占位符代表的参数
     */
    public static void w(@NonNull String message, @Nullable Object... args) {
        Logger.w(message, args);
    }

    /**
     * 示例：wTag("msg: %s, %s", arg1, arg2)
     *
     * @param message 消息体
     * @param args    消息体中占位符代表的参数
     */
    public static void wTag(@NonNull String tag, @NonNull String message, @Nullable Object... args) {
        log(Logger.WARN, tag, createMessage(message, args));
    }

    /**
     * 示例：wtf("msg: %s, %s", arg1, arg2)
     *
     * @param message 消息体
     * @param args    消息体中占位符代表的参数
     */
    public static void wtf(@NonNull String message, @Nullable Object... args) {
        Logger.wtf(message, args);
    }

    /**
     * 示例：wtfTag("msg: %s, %s", arg1, arg2)
     *
     * @param message 消息体
     * @param args    消息体中占位符代表的参数
     */
    public static void wtfTag(@NonNull String tag, @NonNull String message, @Nullable Object... args) {
        log(Logger.ASSERT, tag, createMessage(message, args));
    }

    /**
     * output will be in debug level
     */
    public static void json(@Nullable String json) {
        Logger.json(json);
    }

    public static void jsonTag(@NonNull String tag, @NonNull String json) {
        log(Logger.DEBUG, tag, toJsonString(json));
    }

    /**
     * output will be in debug level
     */
    public static void xml(@Nullable String xml) {
        Logger.xml(xml);
    }

    public static void xmlTag(@NonNull String tag, @NonNull String xml) {
        log(Logger.DEBUG, tag, toXmlString(xml));
    }

    @NonNull
    public static String createMessage(@NonNull String message, @Nullable Object... args) {
        return args == null || args.length == 0 ? message : String.format(message, args);
    }

    public static String toString(Object object) {
        if (object == null) {
            return "null";
        }
        if (!object.getClass().isArray()) {
            return object.toString();
        }
        if (object instanceof boolean[]) {
            return Arrays.toString((boolean[]) object);
        }
        if (object instanceof byte[]) {
            return Arrays.toString((byte[]) object);
        }
        if (object instanceof char[]) {
            return Arrays.toString((char[]) object);
        }
        if (object instanceof short[]) {
            return Arrays.toString((short[]) object);
        }
        if (object instanceof int[]) {
            return Arrays.toString((int[]) object);
        }
        if (object instanceof long[]) {
            return Arrays.toString((long[]) object);
        }
        if (object instanceof float[]) {
            return Arrays.toString((float[]) object);
        }
        if (object instanceof double[]) {
            return Arrays.toString((double[]) object);
        }
        if (object instanceof Object[]) {
            return Arrays.deepToString((Object[]) object);
        }
        return "Couldn't find a correct type for the object";
    }

    @NonNull
    public static String toJsonString(@NonNull String json) {
        if (TextUtils.isEmpty(json)) {
            return "Empty/Null json content";
        }

        try {
            json = json.trim();
            if (json.startsWith("{")) {
                return new JSONObject(json).toString(4);
            }
            if (json.startsWith("[")) {
                return new JSONArray(json).toString(4);
            }
            return json;
        } catch (JSONException e) {
            return e.getMessage() + ": " + json;
        }
    }

    @NonNull
    public static String toXmlString(@NonNull String xml) {
        if (TextUtils.isEmpty(xml)) {
            return "Empty/Null xml content";
        }

        try {
            Source xmlInput = new StreamSource(new StringReader(xml));
            StreamResult xmlOutput = new StreamResult(new StringWriter());
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            transformer.transform(xmlInput, xmlOutput);
            return xmlOutput.getWriter().toString().replaceFirst(">", ">\n");
        } catch (TransformerException e) {
            return e.getMessage() + ": " + xml;
        }
    }
}
