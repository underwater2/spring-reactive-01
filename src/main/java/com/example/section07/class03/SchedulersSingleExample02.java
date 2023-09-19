package com.example.section07.class03;

import com.example.utils.Logger;
import com.example.utils.TimeUtils;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * Schedulers.newSingle()을 적용할 경우,
 * .single()과 다르게 구독할 때 마다 새로운 쓰레드를 생성한다.
 */
public class SchedulersSingleExample02 {
    public static void main(String[] args) {
        doTask("task1").subscribe(Logger::onNext);

        doTask("task2").subscribe(Logger::onNext);

        TimeUtils.sleep(200L);
    }

    private static Flux<Integer> doTask(String taskName) {
        return Flux.fromArray(new Integer[] {1, 3, 5, 7})
                .publishOn(Schedulers.newSingle("new-single", true))
                .filter(data -> data > 3)
                .doOnNext(data -> Logger.doOnNext(taskName, "filter", data))
                .map(data -> data * 10)
                .doOnNext(data -> Logger.doOnNext(taskName, "map", data));
    }
}
