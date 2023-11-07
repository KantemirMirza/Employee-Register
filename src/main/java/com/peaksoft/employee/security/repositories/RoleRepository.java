package com.peaksoft.employee.security.repositories;

import com.peaksoft.employee.parameter.service.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
}
