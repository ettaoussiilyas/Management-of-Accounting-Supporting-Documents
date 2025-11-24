package com.cabinetcomptable.security.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class CloudinaryService {

    private final Cloudinary cloudinary;

    public Map upload(MultipartFile file, Map options) throws IOException {
        return cloudinary.uploader().upload(file.getBytes(), options == null ? ObjectUtils.emptyMap() : options);
    }

    public Map destroy(String publicId, Map options) throws IOException {
        return cloudinary.uploader().destroy(publicId, options == null ? ObjectUtils.emptyMap() : options);
    }
}

