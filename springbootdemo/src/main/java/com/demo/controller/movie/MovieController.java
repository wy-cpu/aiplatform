package com.demo.controller.movie;

import cn.hutool.log.LogFactory;
import com.alibaba.fastjson.JSON;
import com.demo.model.movie.MovieInfo;
import com.demo.model.movie.MovieModel;
import com.demo.repository.movie.DemoRepository;
import com.demo.service.movie.MovieService;
import com.demo.util.ConstantUtil;
import com.demo.util.QueryUtil;
import com.demo.util.WebMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/movieController")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private DemoRepository demoRepository;

    /***
     *  JPA 按条件分页查询
     * @param jsonFilter
     * @param jsonPage
     * @return
     */
    @RequestMapping(value = "/queryMovie", method = RequestMethod.GET)
    public WebMessage queryMovie(@RequestParam(value = "filter", required = false) String jsonFilter,
                                 @RequestParam(value = "page", required = false) String jsonPage) {
        PageRequest page = QueryUtil.convertToPage(jsonPage);

        Page<MovieInfo> pageResult = null;
        if (StringUtils.isNotEmpty(jsonFilter)) {
            MovieInfo filter = JSON.parseObject(jsonFilter, MovieInfo.class);
            ExampleMatcher matcher = QueryUtil.matcherEntityLike(jsonFilter);
            Example<MovieInfo> example = Example.of(filter, matcher);
            pageResult = demoRepository.findAll(example, page);
        } else {
            pageResult = demoRepository.findAll(page);
        }

        LogFactory.get().info(JSON.toJSONString(pageResult.getContent()));
        return new WebMessage("success", "查询成功").put("page", pageResult);
    }

    /***
     * mybatis 按id查询
     * @param id
     * @return
     */
    @RequestMapping(value = "/queryMovieById", method = RequestMethod.GET)
    public MovieModel queryMovieById(int id){
        MovieModel movieModel = movieService.queryMovieById(id);
        return movieModel;
    }

    /***
     * 上传
     * @param multipartFile
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/movieUpload", method = RequestMethod.POST)
    public String movieUpload (@RequestParam("file") MultipartFile multipartFile) throws IOException {
        return getUpload(multipartFile);
    }

    /***
     * 上传
     * @param multipartFile
     * @return
     * @throws IOException
     */
    private String getUpload(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date());

        // 获取原始名称
        String originalFilename = multipartFile.getOriginalFilename();
        // 组成新的文件名称
        int index = originalFilename.lastIndexOf(".");
        String suffix = originalFilename.substring(index);
        String fileName = UUID.randomUUID().toString() + suffix;

        if (new File(ConstantUtil.LOCAL_FILE_PATH + date).exists()) {
            multipartFile.transferTo(new File(ConstantUtil.LOCAL_FILE_PATH + date + "\\" + fileName));
        } else {
            new File(ConstantUtil.LOCAL_FILE_PATH + date).mkdir();
            multipartFile.transferTo(new File(ConstantUtil.LOCAL_FILE_PATH + date + "\\" + fileName));
        }
        return ConstantUtil.LOCAL_FILE_PATH + date + "\\" + fileName;
    }

    /**
     * jpa 新增
     * @param movieInfo
     * @return
     */
    @RequestMapping(value = "/addMovie", method = RequestMethod.POST)
    public WebMessage addMovie (@RequestBody MovieInfo movieInfo) {
        if (movieInfo != null) {
            movieInfo.setCreateTime(new Date());
            movieInfo.setEntryTime(new Date());
            movieInfo.setPosterOldPath(movieInfo.getPosterPath());
            WebMessage create = WebMessage.create().put("create", demoRepository.save(movieInfo));
            LogFactory.get().info(JSON.toJSONString(create));
            return create;
        }
        return WebMessage.create().put("create", null);
    }

    /***
     *  jpa 单个删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteMovieById/{id}", method = RequestMethod.POST)
    public WebMessage deleteMovieById(@PathVariable("id") Integer id) {
        WebMessage webMessage = new WebMessage();
        if (id != null) {
            // 按 id 查询电影列表中的信息
            MovieInfo movieInfo = movieService.findMovieById(id);
            String posterPath = movieInfo.getPosterPath();

            File file = new File(posterPath);
            if (file.exists()) {
                file.delete();
                demoRepository.deleteById(id);
                return webMessage.put("success", "删除成功");
            }
        }
        return webMessage.put("success", "删除失败 或者是 该文件不存在 请仔细检查");
    }

    /***
     * jpa 修改
     * @return
     */
    @RequestMapping(value = "/updateMovie", method = RequestMethod.POST)
    public WebMessage updateMovie (@RequestBody MovieInfo movieInfo) {
        WebMessage webMessage = new WebMessage();

        String posterOldPath = movieInfo.getPosterOldPath();
        File file = new File(posterOldPath);
        if (file.exists()) {
            file.delete();
            if (demoRepository.existsById(movieInfo.getId())){
                demoRepository.save(movieInfo);
            }
        }
        return webMessage.put("success", "删除成功");
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test (){
       return "成功";
    }
}
