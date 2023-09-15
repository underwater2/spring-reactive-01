package com.example.section05;

import com.example.utils.Logger;
import com.example.utils.TimeUtils;
import reactor.core.publisher.BufferOverflowStrategy;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

public class BackpressureStrategyBufferDropLatestExample {
    public static void main(String[] args) {
        Flux
                .interval(Duration.ofMillis(300L))
                .doOnNext(data -> Logger.info("# emitted by original Flux: {}", data))
                .onBackpressureBuffer(2, //버퍼 사이즈
                        dropped -> Logger.info("# Overflow & dropped: {}", dropped), //드랍된 데이터 로깅
                        BufferOverflowStrategy.DROP_LATEST)
                .doOnNext(data -> Logger.info("# emitted by Buffer: {}", data)) //버퍼에서 나와 subscriber로 전달되는 데이터 로깅
                .publishOn(Schedulers.parallel(), false, 1) //쓰레드 추가, prefetch는 쓰레드에서 사용되는 버퍼
                .subscribe(data -> {
                        TimeUtils.sleep(1000L);
                        Logger.onNext(data);
                    },
                    error -> Logger.onError(error));

        TimeUtils.sleep(3000L);
    }
}
