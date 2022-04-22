package ru.fixies.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.fixies.repositories.OrderRepository;

@Service
@RequiredArgsConstructor
public class OrderService {

    public final OrderRepository orderRepository;

}
