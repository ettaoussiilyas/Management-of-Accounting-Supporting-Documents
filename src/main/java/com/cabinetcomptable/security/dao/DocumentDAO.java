package com.cabinetcomptable.security.dao;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentDAO extends JpaRepository<com.cabinetcomptable.security.entity.Document, Long> {
}
