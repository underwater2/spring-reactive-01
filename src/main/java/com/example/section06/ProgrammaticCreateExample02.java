package com.example.section06;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.stream.IntStream;

@Slf4j
public class ProgrammaticCreateExample02 {
    public static void main(String[] args) throws InterruptedException {
        int tasks = 6;

        Sinks.Many<Object> unicastSink = Sinks.many().unicast().onBackpressureBuffer();
        Flux<Object> fluxView = unicastSink.asFlux();
        IntStream
                .range(1, tasks)
                .forEach(n -> {
                    try {
                        new Thread(() -> {
                            unicastSink.emitNext(doTask(n), Sinks.EmitFailureHandler.FAIL_FAST); //멀티스레드 환경에서 에려가 발생할때 실패하도록 함
                            log.info("# emitted: {}", n);
                        }).start();
                        Thread.sleep(100L);
                    } catch (InterruptedException e) {}
                });
        fluxView
                .subscribe(data -> log.info("# onNext: {}", data));
        Thread.sleep(200L);
    }

    private static String doTask(int taskNumber) {
        return "task " + taskNumber + " result";
    }
}
