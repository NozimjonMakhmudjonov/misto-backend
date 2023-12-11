package com.example.security3.service;

import com.example.security3.entity.EntAttachment;
import com.example.security3.mapper.AttachmentMapper;
import com.example.security3.payload.AttachmentCreateDTO;
import com.example.security3.payload.AttachmentDTO;
import com.example.security3.repository.AttachmentRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AttachmentService {
    private final AttachmentRepository attachmentRepository;
    private final AttachmentMapper attachmentMapper;
    private static final String directory = "uploaded";

    public Long upload(MultipartFile file)  {
        AttachmentCreateDTO attachmentCreateDTO = new AttachmentCreateDTO();
        String[] split = Objects.requireNonNull(file.getOriginalFilename()).split("\\.");
        String contentType = split[split.length - 1];
        attachmentCreateDTO.setOriginalName(file.getOriginalFilename());
        attachmentCreateDTO.setSize(file.getSize());
        attachmentCreateDTO.setContentType(file.getContentType());
        String name = UUID.randomUUID() + "." + contentType;

        attachmentCreateDTO.setName(name);
        attachmentRepository.save(attachmentMapper.toEnt(attachmentCreateDTO));
        Path path = Paths.get(directory + "/" + name);
        try {
            Files.copy(file.getInputStream(), path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Optional<EntAttachment> optional = attachmentRepository.findByName(name);
        return optional.map(EntAttachment::getId).orElse(null);
    }

    public void download(Long id, HttpServletResponse response) {
        Optional<EntAttachment> optional = attachmentRepository.findById(id);
        if (optional.isPresent()) {
            AttachmentDTO attachmentDTO = attachmentMapper.toDto(optional.get());
            response.setHeader("Content-Disposition",
                    "attachment; filename=\"" + attachmentDTO.getOriginalName() + "\"");
            String[] split = attachmentDTO.getOriginalName().split("\\.");
            String contentType = split[split.length - 1];
            response.setContentType(contentType);
            try {
                FileInputStream fileInputStream = new FileInputStream(directory + "/" + attachmentDTO.getName());
                FileCopyUtils.copy(fileInputStream, response.getOutputStream());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void delete(Long id) throws IOException {
        Optional<EntAttachment> optional = attachmentRepository.findById(id);
        if (optional.isPresent()) {
            AttachmentDTO attachmentDTO = attachmentMapper.toDto(optional.get());
            String name = attachmentDTO.getName();
            Path path = Paths.get(directory + "/" + name);
            File file=new File(String.valueOf(path));
            if (file.exists()){
                attachmentRepository.deleteById(id);
                Files.delete(path);
            }
        }
    }

}
