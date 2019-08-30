package com.wzp.king.common.util;

import com.wzp.king.common.bean.constant.EmptyConstant;
import com.wzp.king.common.bean.constant.ExceptionConstant;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 文件处理工具类
 *
 * @author wengzhipeng
 * @version v1.0, 2019/6/17
 */

public class FileUtil {
    private static final String TAG = FileUtil.class.getSimpleName();

    private FileUtil() {
        throw new UnsupportedOperationException(ExceptionConstant.EXCEPTION_INVALID_INIT);
    }

    /**
     * 检测外置存储是否存在
     */
    public static boolean isExtAvailable() {
        return android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
    }

    public static boolean isFileAvailable(@Nullable String path) {
        return isFileExists(path, false) || isDirExists(path, false);
    }

    public static boolean isExtFileAvailable(@Nullable String path) {
        if (!isExtAvailable()) {
            return false;
        }

        return isFileAvailable(path);
    }

    /**
     * 文件是否存在
     */
    public static boolean isFileExists(@Nullable String path, boolean createIfNotExist) {
        if (EmptyUtil.isEmptyText(path)) {
            return false;
        }

        File file = new File(path);
        if (file.exists()) {
            return file.isFile();
        }

        if (createIfNotExist) {
            boolean parentExists = file.getParentFile().exists();
            if (!parentExists) {
                parentExists = file.getParentFile().mkdirs();
            }
            if (parentExists) {
                try {
                    return file.createNewFile();
                } catch (Exception e) {
                    L.eTag(TAG, e);
                }
            }
        }
        return false;
    }

    public static boolean isExtFileExists(@Nullable String path, boolean createIfNotExist) {
        if (!isExtAvailable()) {
            return false;
        }

        return isFileExists(path, createIfNotExist);
    }

    /**
     * 文件夹是否存在
     */
    public static boolean isDirExists(@Nullable String path, boolean createIfNotExist) {
        if (EmptyUtil.isEmptyText(path)) {
            return false;
        }

        File file = new File(path);
        if (file.exists()) {
            return file.isDirectory();
        }

        if (createIfNotExist) {
            boolean parentExists = file.getParentFile().exists();
            if (!parentExists) {
                parentExists = file.getParentFile().mkdirs();
            }
            if (parentExists) {
                try {
                    return file.mkdir();
                } catch (Exception e) {
                    L.eTag(TAG, e);
                }
            }
        }
        return false;
    }

    public static boolean isExtDirExists(@Nullable String path, boolean createIfNotExist) {
        if (!isExtAvailable()) {
            return false;
        }

        return isDirExists(path, createIfNotExist);
    }

    /**
     * 获取文件输入流
     */
    @Nullable
    public static InputStream readFileToStream(@NonNull String path) {
        try {
            return new FileInputStream(new File(path));
        } catch (Exception e) {
            L.eTag(TAG, e);
        }
        return null;
    }

    /**
     * 读取文件字节数组
     */
    @NonNull
    public static byte[] readFileToBytes(@NonNull String path) {
        BufferedInputStream inputStream = null;
        ByteArrayOutputStream outputStream = null;
        try {
            inputStream = new BufferedInputStream(readFileToStream(path));
            outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer, 0, 1024)) != -1) {
                outputStream.write(buffer, 0, len);
            }
            return outputStream.toByteArray();
        } catch (Exception e) {
            L.eTag(TAG, e);
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception e) {
                L.eTag(TAG, e);
            }
        }
        return EmptyConstant.EMPTY_BYTE_ARRAY;
    }

    /**
     * 读取文件内容
     */
    @NonNull
    public static String readFileToString(@NonNull String path) {
        try {
            return new String(readFileToBytes(path), StandardCharsets.UTF_8);
        } catch (Exception e) {
            L.eTag(TAG, e);
        }
        return EmptyConstant.EMPTY_STRING;
    }

    /**
     * 字符串写入文件
     */
    public static void writeStringToFile(@NonNull String path, @NonNull String content, boolean append) {
        OutputStream outputStream = null;
        OutputStreamWriter streamWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            outputStream = new FileOutputStream(path, append);
            streamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
            bufferedWriter = new BufferedWriter(streamWriter);
            bufferedWriter.write(content);
            bufferedWriter.flush();
        } catch (Exception e) {
            L.eTag(TAG, e);
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
                if (streamWriter != null) {
                    streamWriter.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (Exception e) {
                L.eTag(TAG, e);
            }
        }
    }

    /**
     * 根据路径自动删除文件或文件夹
     */
    public static boolean deleteFile(@Nullable String path) {
        if (!isFileAvailable(path)) {
            return true;
        }

        File file = new File(path);
        if (file.isFile()) {
            return file.delete();
        } else {
            File[] subFileArray = file.listFiles();
            for (File subFile : subFileArray) {
                if (subFile == null) {
                    continue;
                }
                deleteFile(subFile.getAbsolutePath());// 删除当前目录下的子目录
            }
            return file.delete();// 删除当前目录
        }
    }
}
