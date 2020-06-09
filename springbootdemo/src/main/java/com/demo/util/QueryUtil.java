package com.demo.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class QueryUtil {
    /**
     * 将分页json字符串转换为分页对象
     *
     * @param jsonPage
     * @return
     */
    public static PageRequest convertToPage(String jsonPage) {
        PageRequest page = null;
        //StringUtils.isNotEmpty(jsonPage)
        if (jsonPage!=null) {
            JSONObject pageObj = JSON.parseObject(jsonPage);
            page = PageRequest.of(pageObj.getIntValue("pageNum") - 1, pageObj.getIntValue("pageSize"));
        } else {
            page = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "createTime"));
        }
        return page;
    }

    /**
     * 根据对象的json字符串构造对象的查询比较器 以contains作为条件
     *
     * @param jsonEntity
     * @return
     */
    public static ExampleMatcher matcherEntityLike(String jsonEntity) {
        JSONObject jsonProp = JSONObject.parseObject(jsonEntity);
        ExampleMatcher matcher = ExampleMatcher.matching();
        for (String prop : jsonProp.keySet()) {
        	//StringUtils.isNotEmpty(jsonProp.getString(prop))
            if (jsonProp.getString(prop)!=null) {
                // ExampleMatcher.GenericPropertyMatchers.contains()
                matcher = matcher.withMatcher(prop, ExampleMatcher.GenericPropertyMatchers.contains());
            }
        }
        return matcher;
    }
}
