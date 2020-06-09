package com.demo.controller.demo;

import cn.hutool.log.LogFactory;
import com.alibaba.fastjson.JSON;
import com.demo.model.demo.DemoInfo;
import com.demo.model.movie.MovieInfo;
import com.demo.repository.demo.DemoOneRepository;
import com.demo.repository.movie.DemoRepository;
import com.demo.service.movie.MovieService;
import com.demo.util.FileExceUtil;
import com.demo.util.QueryUtil;
import com.demo.util.WebMessage;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/demoController")
public class DemoController {

    @Autowired
    private DemoOneRepository demoRepository;

    @Autowired
    private MovieService movieService;

    @Autowired
    private DemoRepository demoMovieRepository;

    @RequestMapping(value = "/queryDemo", method = RequestMethod.GET)
    public WebMessage queryDemo(@RequestParam(value = "filter", required = false) String jsonFilter,
                                 @RequestParam(value = "page", required = false) String jsonPage) {
        PageRequest page = QueryUtil.convertToPage(jsonPage);

        Page<DemoInfo> pageResult = null;
        if (StringUtils.isNotEmpty(jsonFilter)) {
            DemoInfo filter = JSON.parseObject(jsonFilter, DemoInfo.class);
            ExampleMatcher matcher = QueryUtil.matcherEntityLike(jsonFilter);
            Example<DemoInfo> example = Example.of(filter, matcher);
            pageResult = demoRepository.findAll(example, page);
        } else {
            pageResult = demoRepository.findAll(page);
        }

        LogFactory.get().info(JSON.toJSONString(pageResult.getContent()));
        return new WebMessage("success", "查询成功").put("page", pageResult);
    }

    @RequestMapping(value = "/addDemo", method = RequestMethod.POST)
    public WebMessage addDemo (@RequestBody DemoInfo demoInfo) {
        if (demoInfo != null) {
            WebMessage create = WebMessage.create().put("create", movieService.addDemo(demoInfo));
            LogFactory.get().info(JSON.toJSONString(create));
            return create;
        }
        return WebMessage.create().put("create", null);
    }

    @RequestMapping(value = "/queryDemoExcel", method = RequestMethod.POST)
    public void queryDemoExcel (HttpServletResponse response) {
        // 查询全部的数据
        List<MovieInfo> all = demoMovieRepository.findAll();

        // 创建工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建表
        HSSFSheet sheet = workbook.createSheet("提现信息");
        // 创建行
        HSSFRow row = sheet.createRow(0);
        // 创建单元格样式
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        // 表头
        String[] head = {"id", "电影名称", "电影评分", "描述", "海报路径", "上线时间", "录入时间", "修改时间", "创建时间"};
        HSSFCell cell;
        // 设置表头
        for (int iHead = 0; iHead < head.length; iHead++) {
            cell = row.createCell(iHead);
            cell.setCellValue(head[iHead]);
            cell.setCellStyle(cellStyle);
        }
        // 设置表格内容
        for (int iBody = 0; iBody < all.size(); iBody++) {
            row = sheet.createRow(iBody + 1);
            MovieInfo u = all.get(iBody);
            String[] userArray = new String[11];
            userArray[0] = u.getId() + "";
            userArray[1] = u.getMovieName();
            userArray[2] = u.getScore() + "";
            userArray[3] = u.getMovieDescribe();
            userArray[4] = u.getPosterPath();

            Date entryTime = u.getEntryTime();
            String entryTimeStr = String.format("%tY-%tm-%td %tH:%tM:%tS",entryTime,entryTime,entryTime,entryTime,entryTime,entryTime);
            userArray[5] = entryTimeStr ;

            Date onlineTime = u.getOnlineTime();
            String onlineTimeStr = String.format("%tY-%tm-%td %tH:%tM:%tS",onlineTime,onlineTime,onlineTime,onlineTime,onlineTime,onlineTime);
            userArray[6] = onlineTimeStr ;

            Date updateTime = u.getUpdateTime();
            String updateTimeStr = String.format("%tY-%tm-%td %tH:%tM:%tS",updateTime,updateTime,updateTime,updateTime,updateTime,updateTime);
            userArray[7] = updateTimeStr ;

            Date createTime = u.getCreateTime();
            String createTimeStr = String.format("%tY-%tm-%td %tH:%tM:%tS",createTime,createTime,createTime,createTime,createTime,createTime);
            userArray[8] = createTimeStr ;

            for (int iArray = 0; iArray < userArray.length; iArray++) {
                row.createCell(iArray).setCellValue(userArray[iArray]);
            }
        }
        // 生成Excel文件
        FileExceUtil.createFile(response, workbook);
    }
}
