package com.example.section05;

import com.example.utils.Logger;
import com.example.utils.TimeUtils;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

public class BackpressureStrategyErrorExample {
    public static void main(String[] args) {
        Flux
                .interval(Duration.ofMillis(1L)) // 1밀리세컨에 한번씩 0에서부터 emit
                .onBackpressureError() // error 전략 적용
                .doOnNext(Logger::doOnNext) // emit한 데이터 로깅
                .publishOn(Schedulers.parallel()) // 쓰레드 생성
                .subscribe(data -> {
                            TimeUtils.sleep(5L);
                            Logger.onNext(data); // 받은 데이터 처리 완료 -> 로깅
                        },
                        error -> Logger.onError(error));

        TimeUtils.sleep(2000L);
    }
}
