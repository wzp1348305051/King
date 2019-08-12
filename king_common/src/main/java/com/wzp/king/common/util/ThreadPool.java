package com.wzp.king.common.util;

import android.os.Handler;
import android.os.Looper;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 线程切换工具类
 *
 * @author wengzhipeng
 * @version v1.0, 2019/6/19
 */

public class ThreadPool {
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_SIZE = Math.max(2, Math.min(CPU_COUNT - 1, 4));
    private static final int BIGGER_CORE_POOL_SIZE = CPU_COUNT * 4;
    private static final int MAXIMUM_CORE_POOL_SIZE = CPU_COUNT * 8;
    private static final int MAXIMUM_POOL_SIZE = 2147483647;
    private static final long KEEP_ALIVE_SECONDS = 30L;
    private static final int MAX_QUEUE_SIZE = 10;
    private static final ThreadPoolExecutor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(CORE_POOL_SIZE,
            MAXIMUM_POOL_SIZE, KEEP_ALIVE_SECONDS, TimeUnit.SECONDS, new LinkedBlockingDeque<>(MAX_QUEUE_SIZE),
            newThreadFactory("CachedThread"));
    private static final ExecutorService sDefaultExecutor = new ThreadPool.BufferExecutor();
    private static final Handler MAIN_THREAD_HANDLER = new Handler(Looper.getMainLooper());
    private static final ScheduledThreadPoolExecutor TIMER_THREAD_POOL_EXECUTOR =
            new ScheduledThreadPoolExecutor(CORE_POOL_SIZE, newThreadFactory("TimerThread"));

    static {
        THREAD_POOL_EXECUTOR.allowCoreThreadTimeOut(true);
    }

    private ThreadPool() {

    }

    @NonNull
    public static ThreadFactory newThreadFactory(@NonNull String threadName) {
        return new ThreadFactory() {
            private final AtomicInteger mCount = new AtomicInteger(1);

            public Thread newThread(@NonNull Runnable runnable) {
                return new Thread(runnable, threadName + " #" + mCount.getAndIncrement());
            }
        };
    }

    public static ExecutorService getExecutor() {
        return sDefaultExecutor;
    }

    public static void execute(@NonNull Runnable runnable) {
        try {
            sDefaultExecutor.execute(runnable);
        } catch (Exception e) {
            L.e(e);
        }
    }

    @NonNull
    public static Handler getMain() {
        return MAIN_THREAD_HANDLER;
    }

    public static void executeInMainThread(@NonNull Runnable runnable) {
        if(Looper.getMainLooper().equals(Looper.myLooper())) {
            runnable.run();
        } else {
            MAIN_THREAD_HANDLER.post(runnable);
        }
    }

    public static void scheduleInMainThread(@NonNull Runnable runnable, long delayMillis) {
        MAIN_THREAD_HANDLER.postDelayed(runnable, delayMillis);
    }

    @Nullable
    public static ScheduledFuture<?> schedule(@NonNull Runnable command, long delay, @NonNull TimeUnit unit) {
        try {
            return TIMER_THREAD_POOL_EXECUTOR.schedule(command, delay, unit);
        } catch (Exception e) {
            L.e(e);
        }
        return null;
    }

    @Nullable
    public static ScheduledFuture<?> scheduleWithFixedDelay(@NonNull Runnable command, long initialDelay, long delay,
                                                            @NonNull TimeUnit unit) {
        try {
            return TIMER_THREAD_POOL_EXECUTOR.scheduleWithFixedDelay(command, initialDelay, delay, unit);
        } catch (Exception e) {
            L.e(e);
        }
        return null;
    }

    @Nullable
    public static ScheduledFuture<?> scheduleAtFixedRate(@NonNull Runnable command, long initialDelay, long period,
                                                         @NonNull TimeUnit unit) {
        try {
            return TIMER_THREAD_POOL_EXECUTOR.scheduleAtFixedRate(command, initialDelay, period, unit);
        } catch (Exception e) {
            L.e(e);
        }
        return null;
    }

    public static ScheduledThreadPoolExecutor newTimerExecutor(int corePoolSize) {
        return new ScheduledThreadPoolExecutor(corePoolSize, newThreadFactory("TimerExecutor"));
    }

    private static class BufferExecutor implements ExecutorService, Executor {
        final ArrayDeque<Runnable> mTasks;
        private Runnable mActive;

        private BufferExecutor() {
            mTasks = new ArrayDeque<>();
        }

        public synchronized void execute(@NonNull final Runnable r) {
            mTasks.offer(() -> {
                try {
                    r.run();
                } finally {
                    BufferExecutor.this.scheduleNext();
                }
            });
            if (mActive == null) {
                scheduleNext();
            }
        }

        private synchronized void scheduleNext() {
            int activeCount = ThreadPool.THREAD_POOL_EXECUTOR.getActiveCount();
            int queueSize = ThreadPool.THREAD_POOL_EXECUTOR.getQueue().size();
            if (mTasks.size() > 1000) {
                ThreadPool.THREAD_POOL_EXECUTOR.setCorePoolSize(ThreadPool.MAXIMUM_CORE_POOL_SIZE);
            } else if (mTasks.size() > 100) {
                ThreadPool.THREAD_POOL_EXECUTOR.setCorePoolSize(ThreadPool.BIGGER_CORE_POOL_SIZE);
            } else {
                ThreadPool.THREAD_POOL_EXECUTOR.setCorePoolSize(ThreadPool.CORE_POOL_SIZE);
            }

            if (queueSize <= 10 && activeCount < ThreadPool.THREAD_POOL_EXECUTOR.getCorePoolSize() && (mActive =
                    mTasks.poll()) != null) {
                ThreadPool.THREAD_POOL_EXECUTOR.execute(mActive);
                mActive = null;
            }

        }

        public void shutdown() {
            ThreadPool.THREAD_POOL_EXECUTOR.shutdown();
        }

        public List<Runnable> shutdownNow() {
            return ThreadPool.THREAD_POOL_EXECUTOR.shutdownNow();
        }

        public boolean isShutdown() {
            return ThreadPool.THREAD_POOL_EXECUTOR.isShutdown();
        }

        public boolean isTerminated() {
            return ThreadPool.THREAD_POOL_EXECUTOR.isTerminated();
        }

        public boolean awaitTermination(long timeout, @NonNull TimeUnit unit) throws InterruptedException {
            return ThreadPool.THREAD_POOL_EXECUTOR.awaitTermination(timeout, unit);
        }

        public <T> Future<T> submit(@NonNull Callable<T> task) {
            return ThreadPool.THREAD_POOL_EXECUTOR.submit(task);
        }

        public <T> Future<T> submit(@NonNull Runnable task, T result) {
            return ThreadPool.THREAD_POOL_EXECUTOR.submit(task, result);
        }

        public Future<?> submit(@NonNull Runnable task) {
            return ThreadPool.THREAD_POOL_EXECUTOR.submit(task);
        }

        public <T> List<Future<T>> invokeAll(@NonNull Collection<? extends Callable<T>> tasks) throws InterruptedException {
            return ThreadPool.THREAD_POOL_EXECUTOR.invokeAll(tasks);
        }

        public <T> List<Future<T>> invokeAll(@NonNull Collection<? extends Callable<T>> tasks, long timeout, @NonNull TimeUnit unit) throws InterruptedException {
            return ThreadPool.THREAD_POOL_EXECUTOR.invokeAll(tasks, timeout, unit);
        }

        public <T> T invokeAny(@NonNull Collection<? extends Callable<T>> tasks) throws ExecutionException,
                InterruptedException {
            return ThreadPool.THREAD_POOL_EXECUTOR.invokeAny(tasks);
        }

        public <T> T invokeAny(@NonNull Collection<? extends Callable<T>> tasks, long timeout, @NonNull TimeUnit unit) throws ExecutionException, InterruptedException, TimeoutException {
            return ThreadPool.THREAD_POOL_EXECUTOR.invokeAny(tasks, timeout, unit);
        }
    }

}
