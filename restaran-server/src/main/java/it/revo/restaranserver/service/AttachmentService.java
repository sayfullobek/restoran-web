package it.revo.restaranserver.service;

import it.revo.restaranserver.entity.Attachment;
import it.revo.restaranserver.entity.AttachmentContent;
import it.revo.restaranserver.payload.ApiResponse;
import it.revo.restaranserver.repository.AttachmentContentRepository;
import it.revo.restaranserver.repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.*;

@Service
public class AttachmentService {

    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    AttachmentContentRepository attachmentContentRepository;

    public UUID upload(MultipartHttpServletRequest request) {
        try {
            Iterator<String> fileNames = request.getFileNames();
            MultipartFile file = request.getFile(fileNames.next());
            Attachment attachment = new Attachment(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getSize());
            Attachment savedAttachment = attachmentRepository.save(attachment);

            AttachmentContent attachmentContent = new AttachmentContent(
                    savedAttachment,
                    file.getBytes());
            attachmentContentRepository.save(attachmentContent);
            return savedAttachment.getId();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public HttpEntity<?> getOneFile(UUID id) {
        Attachment attachment = (Attachment) attachmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Attachment"));
        AttachmentContent attachmentContent = attachmentContentRepository.findByAttachmentId(attachment.getId());
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(attachment.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + attachment.getName() + "\"")
                .body(attachmentContent.getBytes());
    }

    public HttpEntity<?> getFile() {
        List<Attachment> all = attachmentRepository.findAll();
        List<Attachment> res = new ArrayList<>();
        for (Attachment attachment : all) {
            AttachmentContent attachmentContent = attachmentContentRepository.findByAttachmentId(attachment.getId());
            ResponseEntity.ok()
                    .contentType(MediaType.valueOf(attachment.getContentType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + attachment.getName() + "\"")
                    .body(attachmentContent.getBytes());
            res.add(attachment);
        }
        return ResponseEntity.ok(res);
    }

    public ApiResponse deleteAtt(@PathVariable UUID id) {
        Optional<Attachment> byId = attachmentRepository.findById(id);
        if (byId.isPresent()) {
            Attachment attachment = byId.get();
            attachmentRepository.delete(attachment);
            return new ApiResponse("successfully deleted attachment", true);
        }
        return new ApiResponse("bunday file mavjud emas", false);
    }

}
