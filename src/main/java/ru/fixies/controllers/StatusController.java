package ru.fixies.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.fixies.dtos.StatusDto;
import ru.fixies.mapper.ModelMapper;
import ru.fixies.services.StatusService;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/statuses")
@RequiredArgsConstructor
public class StatusController {

    private final StatusService statusService;

    @GetMapping
    public List<StatusDto> findAllStatusesForOrderPage() {
        return statusService.findAll().stream().map(ModelMapper.INSTANCE::statusToStatusDto).collect(Collectors.toList());
    }


}
