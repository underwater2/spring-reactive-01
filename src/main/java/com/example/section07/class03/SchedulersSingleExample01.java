package com.example.section07.class03;

import com.example.utils.Logger;
import com.example.utils.TimeUtils;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * Schedulers.single()을 적용할 경우,
 * Schedulers.single()에서 할당된 쓰레드를 재사용한다. (몇 번 구독해도 같은 쓰레드를 계속 재사용함)
 */
public class SchedulersSingleExample01 {
    public static void main(String[] args) {
        doTask("task1").subscribe(Logger::onNext);

        doTask("task2").subscribe(Logger::onNext);

        TimeUtils.sleep(200L);
    }

    private static Flux<Integer> doTask(String taskName) {
        return Flux.fromArray(new Integer[] {1, 3, 5, 7})
                .publishOn(Schedulers.single())
                .filter(data -> data > 3)
                .doOnNext(data -> Logger.doOnNext(taskName, "filter", data))
                .map(data -> data * 10)
                .doOnNext(data -> Logger.doOnNext(taskName, "map", data));
    }
}
