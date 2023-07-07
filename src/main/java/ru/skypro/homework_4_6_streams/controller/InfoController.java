package ru.skypro.homework_4_6_streams.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework_4_6_streams.services.InfoService;

@RestController
@RequestMapping("/info")
@Tag(name = "API для получении информации о приложении")
public class InfoController {
    private final InfoService service;

    @Value("${server.port}")
    private Integer port;

    public InfoController(InfoService service) {
        this.service = service;
    }

    @GetMapping("/getPort")
    public ResponseEntity<Integer> getPort() {
        return ResponseEntity.ok(port);
    }

    @GetMapping("/stream-calculate")
    @Operation(summary = "Получение значение за меньшее количество времени")
    public ResponseEntity<Void> calculateWithStream() {
        service.calculateWithStream();
        return ResponseEntity.ok().build();
    }
}
