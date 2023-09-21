package com.example.section08.class03;

import com.example.utils.Logger;
import com.example.utils.TimeUtils;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * Context의 특징
 *  - 동일한 키에 대해서 write 할 경우, 해당 키에 대한 값을 덮어 쓴다.
 *  => upstream 쪽에서 write한 값으로 덮어쓰게 된다.
 */
public class ContextFeatureExample03 {
    public static void main(String[] args) {
        String key1 = "id";

        Mono.deferContextual(ctx ->
                        Mono.just("ID: " + " " + ctx.get(key1))
                )
                .publishOn(Schedulers.parallel())
                .contextWrite(context -> context.put(key1, "itWorld"))
                .contextWrite(context -> context.put(key1, "itVillage"))
                .subscribe(Logger::onNext);

        TimeUtils.sleep(100L);
    }
}
