package com.example.section03;

import com.example.utils.Logger;
import reactor.core.publisher.Flux;

public class FluxExample01 {
    public static void main(String[] args) {
        Flux.just(6, 9, 13)
                .map(num -> num % 2)
                .subscribe(remainder -> Logger.info("# remainder: {}", remainder));
    }
}
