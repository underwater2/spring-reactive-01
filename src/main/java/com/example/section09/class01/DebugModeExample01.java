package com.example.section09.class01;

import com.example.utils.Logger;
import reactor.core.publisher.Flux;

/**
 * Non-Debug mode
 * - 오류난 라인은 알려주지만 구체적인 내용은 없음
 */
public class DebugModeExample01 {
    public static void main(String[] args) {
        Flux.just(2, 4, 6, 8)
                .zipWith(Flux.just(1, 2, 3, 0), (x, y) -> x/y)
                .subscribe(Logger::onNext, Logger::onError);
    }
}
