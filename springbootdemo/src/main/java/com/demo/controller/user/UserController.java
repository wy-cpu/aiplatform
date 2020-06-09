package com.demo.controller.user;

import cn.hutool.log.LogFactory;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.demo.model.user.UserInfo;
import com.demo.repository.user.UserRepositoryJpa;
import com.demo.service.user.UserService;
import com.demo.util.QueryUtil;
import com.demo.util.WebMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("/userController")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepositoryJpa userRepositoryJpa;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public WebMessage login(@RequestBody String user, HttpServletRequest request){
        JSONObject jsonObject = JSONObject.parseObject(user);
        String username = jsonObject.get("username").toString();
        String password = jsonObject.get("password").toString();
        if (username.equals("") || password.equals("")) {
            return WebMessage.create().put("user", "账号或密码为空");
        }
        UserInfo userInfo = userService.findUserId(username);
        if (userInfo == null) {
            return WebMessage.create().put("user", "没有此账号");
        }
        String userName = userInfo.getUserName();
        String userPassword = userInfo.getUserPassword();
        if (!userName.equals(username) || !userPassword.equals(password)) {
            return WebMessage.create().put("user", "账号或密码错误");
        }
        request.getSession().setAttribute("userInfo", userInfo);
        return WebMessage.create().put("user", "登陆成功");
    }

    /***
     * 新增
     */
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public WebMessage addUser (@RequestBody UserInfo userInfo) {
        if (userInfo != null) {
            userInfo.setCreateTime(new Date());
            userInfo.setLoginTime(new Date());
            WebMessage create = WebMessage.create().put("create", userRepositoryJpa.save(userInfo));

            Map<String, String> map = new HashMap();
            String userName = userInfo.getUserName();
            map.put("user", userName);
            // 将用户信息添加到redis缓存
            Jedis jedis = new Jedis("192.168.163.128",6379);
            jedis.hset("user", map);

            LogFactory.get().info(JSON.toJSONString(create));
            return create;
        }
        return WebMessage.create().put("create", null);
    }

    /***
     *  JPA 按条件分页查询
     * @param jsonFilter
     * @param jsonPage
     * @return
     */
    @RequestMapping(value = "/queryUser", method = RequestMethod.GET)
    public WebMessage queryUser(@RequestParam(value = "filter", required = false) String jsonFilter,
                                 @RequestParam(value = "page", required = false) String jsonPage) {
        PageRequest page = QueryUtil.convertToPage(jsonPage);

        Page<UserInfo> pageResult = null;
        if (StringUtils.isNotEmpty(jsonFilter)) {
            UserInfo filter = JSON.parseObject(jsonFilter, UserInfo.class);
            ExampleMatcher matcher = QueryUtil.matcherEntityLike(jsonFilter);
            Example<UserInfo> example = Example.of(filter, matcher);
            pageResult = userRepositoryJpa.findAll(example, page);
        } else {
            pageResult = userRepositoryJpa.findAll(page);
        }

        LogFactory.get().info(JSON.toJSONString(pageResult.getContent()));
        return new WebMessage("success", "查询成功").put("page", pageResult);
    }


    /***
     *  JPA 按条件分页查询
     * @param jsonFilter
     * @param jsonPage
     * @return
     */
    @RequestMapping(value = "/findUser", method = RequestMethod.GET)
    public WebMessage findUser(@RequestParam(value = "filter", required = false) String jsonFilter,
                                @RequestParam(value = "page", required = false) String jsonPage) {

        UserInfo user = JSONObject.parseObject(jsonFilter, UserInfo.class);

        String userName = user.getUserName();
        String userPassword = user.getUserPassword();

        JSONObject page = JSONObject.parseObject(jsonPage);
        // 当前页
        String pageNum = page.get("pageNum").toString();
        // 每页的条数
        String pageSize = page.get("pageSize").toString();

        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(userName);
        userInfo.setUserPassword(userPassword);

        // 获取总条数
        Long total = userService.findUserCount(userInfo);

        // 设置总条数 每页的条数 当前页
        userInfo.setTotalCount(total);
        userInfo.setPageSize(Long.parseLong(pageSize));
        userInfo.setPageIndex(Long.parseLong(pageNum));

        // 计算分页信息
        userInfo.calculatePage();

        // 获取分页列表
        List<UserInfo> userInfoList = userService.findUser(userInfo);

        Map map = new HashMap();
        map.put("content", userInfoList);
        map.put("totalElements", total);

        LogFactory.get().info(JSON.toJSONString(map));
        return new WebMessage("success", "查询成功").put("page", map);
    }


    /*public static void main(String[] args) {
        int res = RedisUtils.getRedisIsOk("192.168.163.128", 6379);
        if (res == 0) {
            System.out.println("redis缓存有效！" + res);
        } else {
            System.out.println("redis缓存失败！" + res);
        }
    }*/

    public static void main(String[] args) {
         Jedis jedis = new Jedis("192.168.163.128",6379);
         Set<String> str =jedis.keys("*");
         System.out.println(str);
    }
}
