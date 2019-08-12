package com.wzp.king.common.util;

import androidx.annotation.NonNull;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 任务工具类
 *
 * @author wengzhipeng
 * @version v1.0, 2019/6/19
 */
public abstract class TaskUtil<T> {
    public static final Scheduler IO = Schedulers.io();
    public static final Scheduler COMPUTATION = Schedulers.computation();
    public static final Scheduler SINGLE = Schedulers.newThread();
    public static final Scheduler MAIN = AndroidSchedulers.mainThread();

    public interface TaskStart<T> {
        Scheduler thread();

        T start();
    }

    public interface TaskFinish<T> {
        Scheduler thread();

        void finish(T data);
    }

    public static <T> void executeTask(@NonNull TaskStart<T> taskStart, @NonNull TaskFinish<T> taskFinish) {
        Observable.create((ObservableEmitter<T> emitter) -> emitter.onNext(taskStart.start()))
                .subscribeOn(taskStart.thread())
                .observeOn(taskFinish.thread())
                .subscribe(taskFinish::finish);
    }
}
