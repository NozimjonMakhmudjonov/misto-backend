package com.example.security3.mapper;

import com.example.security3.entity.EntAttachment;
import com.example.security3.payload.AttachmentCreateDTO;
import com.example.security3.payload.AttachmentDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AttachmentMapper {
    EntAttachment toEnt(AttachmentCreateDTO attachmentCreateDTO);
    AttachmentDTO toDto(EntAttachment entAttachment);


}
