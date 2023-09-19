package com.example.section07.class01;

import com.example.utils.Logger;
import com.example.utils.TimeUtils;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * parallel()만 사용할 경우에는 병렬로 작업을 수행하지 않는다.
 * runOn()을 사용하여 Scheduler를 할당해줘야 병렬로 작업을 수행한다.
 * **** CPU 코어 갯수 내에서 worker thread를 할당한다. (코어 수만큼 스레드 생김?) ****
 */
public class ParallelExample04 {
    public static void main(String[] args) {
        Flux.fromArray(new Integer[]{1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23})
                .parallel(4) //스레드의 개수 설정 (논리적인 코어 갯수의 내에서 선택 가능)
                .runOn(Schedulers.parallel())
                .subscribe(Logger::onNext);

        TimeUtils.sleep(100L);
    }
}
