package com.demo.repository.user;

import com.demo.model.user.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface UserRepositoryJpa extends JpaRepository<UserInfo, Integer> {
}
