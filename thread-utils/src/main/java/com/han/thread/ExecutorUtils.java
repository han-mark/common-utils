package com.han.thread;


import com.han.base.ThreadConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.ListUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * 异步执行并处理结果, 不需处理结果可直接用注解@Async
 *
 * @author han
 */
@Slf4j
@Component
public class ExecutorUtils {

    private int batchSize = ThreadConstant.BATCH_EXECUTOR_BATCH_SIZE;

    /**
     * 使用线程池异步执行任务， 最后统一同步处理返回结果
     *
     * @param list 要处理的数据列表
     * @param targetMethod 处理数据的方法
     * @param executorService 线程池
     * @param resultFunction 处理结果的方法
     * @param size 每次异步的数量级 默认1000 注意线程池的线程+ 队列需大于size
     * @param <T> 入参
     * @param <R> 出参
     */
    public <T, R> void  execute(List<T> list, Function<T, R> targetMethod, ExecutorService executorService, Consumer<List<R>> resultFunction, Integer size) {
        if (size != null) {
            batchSize = size;
        }
        if (!CollectionUtils.isEmpty(list)) {
            for (List<T> cList: ListUtils.partition(list, batchSize)) {
                execute(cList, targetMethod, executorService,resultFunction, false);
            }
        }
    }

    /**
     * 使用线程池异步执行任务， 最后统一返回结果
     *
     * @param list 要处理的数据列表
     * @param targetMethod 处理数据的方法
     * @param executorService 线程池
     * @param <T> 入参
     * @param <R> 出参
     * @return  <R> 出参
     */
    public static <T, R> List<R> execute(List<T> list, Function<T, R> targetMethod, ExecutorService executorService) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        List<InvokeAllThread<T, R>> invokeAllThreads = new ArrayList<>();
        list.forEach(e -> invokeAllThreads.add(new InvokeAllThread<>(targetMethod, e)));
        List<Future<R>> futureTaskList = null;
        try {
            futureTaskList = executorService.invokeAll(invokeAllThreads);
        } catch (InterruptedException e) {
            log.error("executorService.invokeAll 失败msg:", e);
        }

        if (!CollectionUtils.isEmpty(futureTaskList)) {
            List<R> resultList = new ArrayList<>();
            futureTaskList.forEach(e -> {
                try {
                    R r = e.get();
                    if (r != null) {
                        resultList.add(r);
                    }
                } catch (InterruptedException | ExecutionException e1) {
                    log.error("Future.get 失败msg:", e);
                }
            });
            return resultList;
        }
        return null;
    }

    /**
     * 使用线程池异步执行任务， 最后统一同步处理返回结果
     *
     * @param list 要处理的数据列表
     * @param targetMethod 处理数据的方法
     * @param executorService 线程池
     * @param resultFunction 处理结果的方法
     * @param resultAsync 是否异步处理结果
     * @param <T> 入参
     * @param <R> 出参
     */
    public static <T, R> void  execute(List<T> list, Function<T, R> targetMethod, ExecutorService executorService, Consumer<List<R>> resultFunction, boolean resultAsync) {
        List<R> result = execute(list, targetMethod, executorService);
        if (!CollectionUtils.isEmpty(result)) {
            if (!resultAsync) {
                resultFunction.accept(result);
            } else {
                // TODO 多线程异步处理结果
            }
        }
    }

    /**
     * 创建批量任务
     *
     * @param <T>
     * @param <R>
     */
    static class InvokeAllThread<T, R> implements Callable<R> {

        private final Function<T, R> targetMethod;
        private final T t;

        InvokeAllThread(Function<T, R> method, T t) {
            this.targetMethod = method;
            this.t = t;
        }

        @Override
        public R call() throws Exception {
            return targetMethod.apply(t);
        }
    }

}
