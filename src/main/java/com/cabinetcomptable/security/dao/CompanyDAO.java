package com.cabinetcomptable.security.dao;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyDAO extends JpaRepository<com.cabinetcomptable.security.entity.Company, Long> {
}
