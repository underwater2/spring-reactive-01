package com.example.section07.class03;

import com.example.utils.Logger;
import com.example.utils.TimeUtils;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

/**
 * Schedulers.newBoundedElastic()을 적용
 */
public class SchedulersNewBoundedElasticExample01 {
    public static void main(String[] args) {
        Scheduler scheduler = Schedulers.newBoundedElastic(2, 2, "I/O-Thread"); //스레드 수, 스레드당 큐 크기, 스레드 이름 prefix
        Mono<Integer> mono =
                Mono
                    .just(1)
                    .subscribeOn(scheduler);

        Logger.info("# Start");

        mono.subscribe(data -> {
            Logger.onNext("subscribe 1 doing", data);
            TimeUtils.sleep(3000L);
            Logger.onNext("subscribe 1 done", data);
        });

        mono.subscribe(data -> {
            Logger.onNext("subscribe 2 doing", data);
            TimeUtils.sleep(3000L);
            Logger.onNext("subscribe 2 done", data);
        });

        //3초동안 아래 4개 작업들은 쓰레드 큐에 들어가있다.
        mono.subscribe(data -> {
            Logger.onNext("subscribe 3 doing", data);
        });

        mono.subscribe(data -> {
            Logger.onNext("subscribe 4 doing", data);
        });

        mono.subscribe(data -> {
            Logger.onNext("subscribe 5 doing", data);
        });

        mono.subscribe(data -> {
            Logger.onNext("subscribe 6 doing", data);
        });

//        TimeUtils.sleep(4000L);
//        scheduler.dispose(); //유저스레드이기 때문에 바로 종료되지 않는다. 바로 종료하려면 이 코드를 쓰면 된다.
    }
}
