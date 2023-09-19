package com.example.section06;

import com.example.utils.Logger;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class SinkManyExample01 {
    public static void main(String[] args) {
        //unicast(): 단 하나의 Subscriber에게만 데이터를 emit할 수 있다.
        Sinks.Many<Integer> unicastSink = Sinks.many().unicast().onBackpressureBuffer();
        Flux<Integer> fluxView = unicastSink.asFlux();

        unicastSink.emitNext(1, Sinks.EmitFailureHandler.FAIL_FAST);
        unicastSink.emitNext(2, Sinks.EmitFailureHandler.FAIL_FAST);

        fluxView.subscribe(data -> Logger.onNext("Subscriber1", data));

        unicastSink.emitNext(3, Sinks.EmitFailureHandler.FAIL_FAST);

        //java.lang.IllegalStateException: UnicastProcessor allows only a single Subscriber
        //주석 풀면 위 오류 뜸
//        fluxView.subscribe(data -> Logger.onNext("Subscriber2", data));
    }
}
