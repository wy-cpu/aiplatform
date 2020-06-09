package com.demo.repository.movie;

import com.demo.model.demo.DemoInfo;
import com.demo.model.movie.MovieInfo;
import com.demo.model.movie.MovieModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MovieRepository {

    /***
     * 按 id 查询 t_demo 的信息
     * @param id
     * @return
     */
    MovieModel queryMovieById(int id);

    /***
     * 向 t_demo 中添加信息
     * @param demoInfo
     * @return
     */
    Object addDemo(DemoInfo demoInfo);

    /***
     * 按 id 查询电影列表中的信息
     * @param id
     * @return
     */
    MovieInfo findMovieById(Integer id);
}
