package ru.skypro.homework_4_6_streams.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.skypro.homework_4_6_streams.services.InfoService;

import java.util.stream.LongStream;
import java.util.stream.Stream;

@Service
public class InfoServiceImpl implements InfoService {
    private final Logger logger = LoggerFactory.getLogger(InfoServiceImpl.class);

    @Override
    public void calculateWithStream() {
        int limit = 1_000_000;
        calculateWithStream(limit);
        calculateWithStreamParallel(limit);
        calculateWithLongStream(limit);

    }

    private void calculateWithStream(int limit) {
        long start = System.currentTimeMillis();

        int sum = Stream.iterate(1, a -> a + 1)
                .limit(limit)
                .reduce(0, (a, b) -> a + b);

        long end = System.currentTimeMillis();


        logger.info("Time 1: " + (end - start));
    }

    private void calculateWithStreamParallel(int limit) {
        long start = System.currentTimeMillis();

        int sum = Stream.iterate(1, a -> a + 1)
                .limit(limit)
                .reduce(0, (a, b) -> a + b);

        long end = System.currentTimeMillis();


        logger.info("Time 2: " + (end - start));

    }

    private void calculateWithLongStream(int limit) {
        long start = System.currentTimeMillis();

        long sum = LongStream
                .range(1, limit)
                .sum();

        long end = System.currentTimeMillis();


        logger.info("Time 3: " + (end - start));
    }
}
