package it.revo.restaranserver.controller;

import it.revo.restaranserver.entity.Zakaz;
import it.revo.restaranserver.payload.ApiResponse;
import it.revo.restaranserver.payload.ZakazDto;
import it.revo.restaranserver.repository.ZakazRepository;
import it.revo.restaranserver.service.ZakazService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/zakaz")
public class ZakazController {
    @Autowired
    ZakazService zakazService;

    @Autowired
    ZakazRepository zakazRepository;

    @PostMapping
    public HttpEntity<?> sakazQil(@RequestBody ZakazDto zakazDto) {
        ApiResponse apiResponse = zakazService.zakazQilish(zakazDto);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping
    public HttpEntity<?> getZakaz() {
        List<Zakaz> all = zakazRepository.findAll();
        return ResponseEntity.ok(all);
    }
}
