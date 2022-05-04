package ru.fixies.fixies.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.fixies.fixies.repositories.StockRepository;


@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;

}
