package com.demo.repository.movie;

import com.demo.model.movie.MovieInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface DemoRepository extends JpaRepository<MovieInfo, Integer> {

}
