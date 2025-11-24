package com.cabinetcomptable.security.service;

import com.cabinetcomptable.security.dao.DocumentDAO;
import com.cabinetcomptable.security.dao.UserDAO;
import com.cabinetcomptable.security.dto.document.DocumentRequestDTO;
import com.cabinetcomptable.security.entity.Company;
import com.cabinetcomptable.security.entity.Document;
import com.cabinetcomptable.security.entity.User;
import com.cabinetcomptable.security.exception.UserNotFoundException;
import com.cabinetcomptable.security.mapper.DocumentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class DocumentService {

    private final DocumentDAO documentDAO;
    private final DocumentMapper documentMapper;
    private final CloudinaryService cloudinaryService;
    private final UserDAO userDAO;

    @Transactional
    public Document uploadDocument(MultipartFile file, DocumentRequestDTO dto, Long companyId) throws IOException {
        // upload file to cloudinary
        Map uploadResult = cloudinaryService.upload(file, Map.of("resource_type", "auto", "folder", "cabinet_documents"));

        String publicId = uploadResult.get("public_id") != null ? uploadResult.get("public_id").toString() : null;
        String secureUrl = uploadResult.get("secure_url") != null ? uploadResult.get("secure_url").toString() : null;

        Document document = documentMapper.toEntity(dto);
        document.setDocument(file.getOriginalFilename());
        document.setPublicId(publicId);
        document.setDocumentUrl(secureUrl);

        if (companyId != null) {
            Company company = new Company();
            company.setId(companyId);
            document.setCompany(company);
        }

        document = documentDAO.save(document);
        return document;
    }

    public List<Document> listByCompany(Long companyId) {
        if (companyId == null) return documentDAO.findAll();
        return documentDAO.findAll().stream().filter(d -> d.getCompany() != null && Objects.equals(d.getCompany().getId(), companyId)).toList();
    }

    public Optional<Document> findById(Long id) {
        return documentDAO.findById(id);
    }

    @Transactional
    public Document updateMetadata(Long id, DocumentRequestDTO dto) {
        Document document = documentDAO.findById(id).orElseThrow();
        documentMapper.updateEntityFromDTO(dto, document);
        return documentDAO.save(document);
    }

    @Transactional
    public void deleteDocument(Long id) throws IOException {
        Document document = documentDAO.findById(id).orElseThrow();
        if (document.getPublicId() != null) {
            cloudinaryService.destroy(document.getPublicId(), null);
        }
        documentDAO.delete(document);
    }
}
