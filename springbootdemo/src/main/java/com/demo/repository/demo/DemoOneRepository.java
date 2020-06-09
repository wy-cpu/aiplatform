package com.demo.repository.demo;

import com.demo.model.demo.DemoInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface DemoOneRepository extends JpaRepository<DemoInfo, Integer> {

}
