package com.example.section05;

import com.example.utils.Logger;
import com.example.utils.TimeUtils;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

public class BackpressureExample01 {
    public static void main(String[] args) {
        Flux.range(1, 5) // 1부터 5까지 emit
                .doOnNext(Logger::doOnNext)
                .doOnRequest(Logger::doOnRequest) // 받은 요청의 개수 출력
                .subscribe(new BaseSubscriber<Integer>() {
                    @Override
                    protected void hookOnSubscribe(Subscription subscription) {
                        request(1); // 구독 시점에 데이터 요청 개수 지정
                    }

                    @Override
                    protected void hookOnNext(Integer value) {
                        TimeUtils.sleep(2000L); // 데이터 처리에 2초 걸리도록
                        Logger.onNext(value); // 받은 데이터 출력
                        request(1); // 데이터 1개 요청
                    }
                });
    }
}
