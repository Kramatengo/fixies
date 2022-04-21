package ru.fixies.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.fixies.repositories.OrdersRepository;

@Service
@RequiredArgsConstructor
public class OrderService {

    public final OrdersRepository ordersRepository;

}
