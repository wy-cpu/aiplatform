package com.demo.controller.user;

import cn.hutool.log.LogFactory;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.demo.model.user.Roleinfo;
import com.demo.service.user.RoleService;
import com.demo.util.WebMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/roleController")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /***
     *  JPA 按条件分页查询
     * @param jsonFilter
     * @param jsonPage
     * @return
     */
    @RequestMapping(value = "/findRole", method = RequestMethod.GET)
    public WebMessage findRole(@RequestParam(value = "filter", required = false) String jsonFilter,
                               @RequestParam(value = "page", required = false) String jsonPage) {

        Roleinfo role = JSONObject.parseObject(jsonFilter, Roleinfo.class);

        String roleTypeName = role.getRoleTypeName();

        JSONObject page = JSONObject.parseObject(jsonPage);
        // 当前页
        String pageNum = page.get("pageNum").toString();
        // 每页的条数
        String pageSize = page.get("pageSize").toString();

        Roleinfo roleinfo = new Roleinfo();
        roleinfo.setRoleTypeName(roleTypeName);

        // 获取总条数
        Long total = roleService.findRoleCount(roleinfo);

        // 设置总条数 每页的条数 当前页
        roleinfo.setTotalCount(total);
        roleinfo.setPageSize(Long.parseLong(pageSize));
        roleinfo.setPageIndex(Long.parseLong(pageNum));

        // 计算分页信息
        roleinfo.calculatePage();

        // 获取分页列表
        List<Roleinfo> userInfoList = roleService.findRole(roleinfo);

        Map map = new HashMap();
        map.put("content", userInfoList);
        map.put("totalElements", total);

        LogFactory.get().info(JSON.toJSONString(map));
        return new WebMessage("success", "查询成功").put("page", map);
    }

    /***
     * 新增
     */
    @RequestMapping(value = "/addRole", method = RequestMethod.POST)
    public WebMessage addRole (@RequestBody Roleinfo roleinfo) {
        if (roleinfo != null) {
            roleinfo.setCreateTime(new Date());
            roleinfo.setLoginTime(new Date());
            roleService.addRole(roleinfo);
            WebMessage create = WebMessage.create().put("create", "新增成功");
            LogFactory.get().info(JSON.toJSONString(create));
            return create;
        }
        return WebMessage.create().put("create", null);
    }

    /***
     *  单个删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteRoleById/{id}", method = RequestMethod.POST)
    public WebMessage deleteRoleById(@PathVariable("id") Integer id) {
        WebMessage webMessage = new WebMessage();
        if (id != null) {
            roleService.deleteById(id);
            return webMessage.put("success", "删除成功");
        }
        return webMessage.put("success", "删除失败 请仔细检查");
    }

}
