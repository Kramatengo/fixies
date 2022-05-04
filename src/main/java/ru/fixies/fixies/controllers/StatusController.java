package ru.fixies.fixies.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.fixies.fixies.dtos.StatusDto;
import ru.fixies.fixies.services.StatusService;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/status")
@RequiredArgsConstructor
public class StatusController {

    private final StatusService statusService;

    @GetMapping("/all_statuses_for_order_page")
    public List<StatusDto> findAllStatusesForOrderPage() {
        return statusService.findAll().stream().map(StatusDto::new).collect(Collectors.toList());
    }


}
