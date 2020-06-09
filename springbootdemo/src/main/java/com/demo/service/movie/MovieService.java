package com.demo.service.movie;

import com.demo.model.demo.DemoInfo;
import com.demo.model.movie.MovieInfo;
import com.demo.model.movie.MovieModel;
import com.demo.repository.movie.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    /***
     * 按 id 查询 t_demo 的信息
     * @param id
     * @return
     */
    public MovieModel queryMovieById(int id) {
        MovieModel movieModel = movieRepository.queryMovieById(id);
        return movieModel;
    }

    /***
     * 向 t_demo 中添加信息
     * @param demoInfo
     * @return
     */
    public Object addDemo(DemoInfo demoInfo) {
        Object obj = null;
        if (demoInfo != null) {
            for (int i = 1; i <= 900; i++) {
                demoInfo.setName(demoInfo.getName() + i);
                obj = movieRepository.addDemo(demoInfo);
            }
        }
        return obj;
    }

    /***
     * 按 id 查询电影列表中的信息
     * @param id
     * @return
     */
    public MovieInfo findMovieById(Integer id) {
        MovieInfo movieInfo = movieRepository.findMovieById(id);
        return movieInfo;
    }
}
