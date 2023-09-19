package com.example.section06;

import com.example.utils.Logger;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

public class SinkOneExample02 {
    public static void main(String[] args) {
        //emit된 데이터 중에서 단 하나의 데이터만 Subscriber에게 전달한다. 나머지 데이터는 Drop됨
        Sinks.One<Object> sinkOne = Sinks.one();
        Mono<Object> mono = sinkOne.asMono();

        sinkOne.emitValue("Hello Reactor", Sinks.EmitFailureHandler.FAIL_FAST);

        //Sinks.One은 단 한 개의 데이터를 emit할 수 있기 때문에 두 번째 emit한 데이터는 drop된다.
        sinkOne.emitValue("Hi Reactor", Sinks.EmitFailureHandler.FAIL_FAST);

        mono.subscribe(data -> Logger.onNext("Subscriber1 ", data));
        mono.subscribe(data -> Logger.onNext("Subscriber2 ", data));
    }
}
