package it.revo.restaranserver.controller;

import it.revo.restaranserver.entity.Attachment;
import it.revo.restaranserver.payload.ApiResponse;
import it.revo.restaranserver.repository.AttachmentRepository;
import it.revo.restaranserver.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/attachment")
public class AttachmentController {
    @Autowired
    AttachmentService attachmentService;

    @Autowired
    AttachmentRepository attachmentRepository;

    @PostMapping("/upload")
    public HttpEntity<?> upload(MultipartHttpServletRequest request) {
        UUID upload = attachmentService.upload(request);
        return ResponseEntity.ok(upload);
    }

    @GetMapping("/download")
    public HttpEntity<?> getFile(@RequestParam(name = "id", required = false) UUID id) {
        return attachmentService.getOneFile(id);
    }

    @GetMapping
    public HttpEntity<?> getAll() {
        return attachmentService.getFile();
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteAttachment(@PathVariable UUID id) {
        ApiResponse apiResponse = attachmentService.deleteAtt(id);
        return ResponseEntity.ok(apiResponse);
    }
}
