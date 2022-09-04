package it.revo.restaranserver.controller;

import it.revo.restaranserver.entity.Aware;
import it.revo.restaranserver.payload.ApiResponse;
import it.revo.restaranserver.payload.AwareDto;
import it.revo.restaranserver.repository.AwareRepository;
import it.revo.restaranserver.service.AwareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/aware")
public class AwareController {

    @Autowired
    AwareService awareService;

    @Autowired
    AwareRepository awareRepository;

    @PostMapping
    public HttpEntity<?> addAware(@RequestBody AwareDto awareDto) {
        ApiResponse apiResponse = awareService.addAware(awareDto);
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editAware(@PathVariable UUID id, @RequestBody AwareDto awareDto) {
        ApiResponse apiResponse = awareService.editAware(id, awareDto);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping
    public HttpEntity<?> getAware() {
        List<Aware> all = awareRepository.findAll();
        return ResponseEntity.ok(all);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteAware(@PathVariable UUID id) {
        ApiResponse apiResponse = awareService.deleteAware(id);
        return ResponseEntity.ok(apiResponse);
    }
}
