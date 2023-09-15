package com.example.section02;

import com.example.utils.Logger;
import reactor.core.publisher.Flux;

public class HelloReactorExample {
    public static void main(String[] args) {
        Flux<String> sequence = Flux.just("Hello", "Reactor");
        sequence
                .map(data -> data.toLowerCase())
                .subscribe(data -> Logger.onNext(data));
    }
}
