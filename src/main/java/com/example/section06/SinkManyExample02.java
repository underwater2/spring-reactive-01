package com.example.section06;

import com.example.utils.Logger;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class SinkManyExample02 {
    public static void main(String[] args) {
        //multicast(): 하나 이상의 Subscriber에게 데이터를 emit할 수 있다.
        Sinks.Many<Integer> multicastSink = Sinks.many().multicast().onBackpressureBuffer();
        Flux<Integer> fluxView = multicastSink.asFlux();

        multicastSink.emitNext(1, Sinks.EmitFailureHandler.FAIL_FAST);
        multicastSink.emitNext(2, Sinks.EmitFailureHandler.FAIL_FAST);

        fluxView.subscribe(data -> Logger.onNext("Subscriber1", data));

        fluxView.subscribe(data -> Logger.onNext("Subscriber2", data));

        multicastSink.emitNext(3, Sinks.EmitFailureHandler.FAIL_FAST);
    }
}
