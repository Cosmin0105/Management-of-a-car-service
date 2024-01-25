package com.serviceproject.web.repository;
import com.serviceproject.web.models.Role;
import com.serviceproject.web.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);

}
