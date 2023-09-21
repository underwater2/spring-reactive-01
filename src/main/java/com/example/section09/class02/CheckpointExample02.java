package com.example.section09.class02;

import com.example.utils.Logger;
import reactor.core.publisher.Flux;

/**
 * checkpoint() Operator 를 사용한 예제
 * - 발생한 에러는 Operator 체인에 전파가 되기때문에 에러가 전파된 지점의 checkpoint()에서 확인할 수 있다.
 * - 에러가 어디서 발생했든, 결과에는 checkpoint()를 추가한 지점의 traceback 이 추가된다.
 */
public class CheckpointExample02 {
    public static void main(String[] args) {
        Flux.just(2, 4, 6, 8)
                .zipWith(Flux.just(1, 2, 3, 0), (x, y) -> x/y)
                .map(num -> num + 2)
                .checkpoint()
                .subscribe(Logger::onNext, Logger::onError);
    }
}
