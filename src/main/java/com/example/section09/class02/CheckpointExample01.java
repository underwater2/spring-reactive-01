package com.example.section09.class02;

import com.example.utils.Logger;
import reactor.core.publisher.Flux;

/**
 * checkpoint() Operator 를 이용한 예제
 * - 에러가 예상되는 assembly 지점에 checkpoint()를 사용해서 에러 발생 지점을 확인할 수 있다.
 * - checkpoint()는 에러 발생 시, traceback 이 추가된다.
 * => 걸린 checkpoint()의 위쪽에서 에러가 발생했다는 걸 유추 가능함
 */
public class CheckpointExample01 {
    public static void main(String[] args) {
        Flux.just(2, 4, 6, 8)
                .zipWith(Flux.just(1, 2, 3, 0), (x, y) -> x/y)
                .checkpoint()
                .map(num -> num + 2)
                .subscribe(Logger::onNext, Logger::onError);
    }
}
