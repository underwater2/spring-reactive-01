package com.example.section07.class03;

import com.example.utils.Logger;
import com.example.utils.TimeUtils;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

/**
 * Schedulers.newParallel()을 적용
 */
public class SchedulersNewParallelExample01 {
    public static void main(String[] args) {
        Scheduler scheduler = Schedulers.newParallel("Parallel Thread", 4, true); //스레드 이름 prefix, 스레드 수, 데몬스레드 여부
        Mono<Integer> flux =
                Mono
                    .just(1)
                    .subscribeOn(scheduler);

        flux.subscribe(data -> {
            TimeUtils.sleep(5000L);
            Logger.onNext("subscribe 1", data);
        });

        flux.subscribe(data -> {
            TimeUtils.sleep(4000L);
            Logger.onNext("subscribe 2", data);
        });

        flux.subscribe(data -> {
            TimeUtils.sleep(3000L);
            Logger.onNext("subscribe 3", data);
        });

        flux.subscribe(data -> {
            TimeUtils.sleep(2000L);
            Logger.onNext("subscribe 4", data);
        });

        TimeUtils.sleep(6000L);
    }
}
