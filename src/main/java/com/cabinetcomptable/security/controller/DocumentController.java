package com.cabinetcomptable.security.controller;

import com.cabinetcomptable.security.dto.auth.ApiResponse;
import com.cabinetcomptable.security.dto.document.DocumentRequestDTO;
import com.cabinetcomptable.security.dto.document.DocumentResponseDTO;
import com.cabinetcomptable.security.entity.Document;
import com.cabinetcomptable.security.mapper.DocumentMapper;
import com.cabinetcomptable.security.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/documents")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService documentService;
    private final DocumentMapper documentMapper;

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<ApiResponse<DocumentResponseDTO>> uploadDocument(
            @RequestPart("file") MultipartFile file,
            @RequestPart("metadata") DocumentRequestDTO dto,
            @RequestParam(value = "companyId", required = false) Long companyId
    ) throws IOException {
        Document saved = documentService.uploadDocument(file, dto, companyId);
        DocumentResponseDTO response = documentMapper.toResponseDTO(saved);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(response, "Document uploaded"));
    }

    @GetMapping("/company/{companyId}")
    public ResponseEntity<ApiResponse<List<DocumentResponseDTO>>> listByCompany(@PathVariable Long companyId){
        List<DocumentResponseDTO> list = documentService.listByCompany(companyId).stream().map(documentMapper::toResponseDTO).collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(list, "Documents list"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DocumentResponseDTO>> getById(@PathVariable Long id){
        Document document = documentService.findById(id).orElseThrow();
        return ResponseEntity.ok(ApiResponse.success(documentMapper.toResponseDTO(document), "Document details"));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ACCOUNTANT') or hasRole('SOCIETE')")
    public ResponseEntity<ApiResponse<DocumentResponseDTO>> updateMetadata(@PathVariable Long id, @RequestBody DocumentRequestDTO dto){
        Document updated = documentService.updateMetadata(id, dto);
        return ResponseEntity.ok(ApiResponse.success(documentMapper.toResponseDTO(updated), "Document updated"));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ACCOUNTANT') or hasRole('SOCIETE')")
    public ResponseEntity<ApiResponse<?>> deleteDocument(@PathVariable Long id) throws IOException {
        documentService.deleteDocument(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Document deleted"));
    }

    @GetMapping("/{id}/download")
    public ResponseEntity<Void> download(@PathVariable Long id){
        Document document = documentService.findById(id).orElseThrow();
        String url = document.getDocumentUrl();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", url);
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }
}

