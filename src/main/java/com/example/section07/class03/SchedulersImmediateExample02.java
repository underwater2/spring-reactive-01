package com.example.section07.class03;

import com.example.utils.Logger;
import com.example.utils.TimeUtils;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * Schedulers.immediate()을 적용 후,
 * 현재 존재하는 쓰레드가 할당된다.
 */
public class SchedulersImmediateExample02 {
    public static void main(String[] args) {
        Flux.fromArray(new Integer[]{1, 3, 5, 7})
                .publishOn(Schedulers.parallel())
                .filter(data -> data > 3)
                .doOnNext(data -> Logger.doOnNext("filter", data))
                .publishOn(Schedulers.immediate()) //쓰레드 생성x, 이미 있는 쓰레드를 사용
                .map(data -> data * 10)
                .doOnNext(data -> Logger.doOnNext("map", data))
                .subscribe(Logger::onNext);

        TimeUtils.sleep(500L);
    }
}
