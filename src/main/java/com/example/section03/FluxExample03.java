package com.example.section03;

import com.example.utils.Logger;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FluxExample03 {
    public static void main(String[] args) {
        Flux<Object> flux = Mono.justOrEmpty(null)
                .concatWith(Mono.justOrEmpty("Jobs"));
        flux.subscribe(data -> Logger.info("# result: {}", data));
    }
}
