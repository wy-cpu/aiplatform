package com.demo.service.user;

import com.demo.model.user.Roleinfo;
import com.demo.repository.user.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    /***
     * 获取总条数
     * @param roleinfo
     * @return
     */
    public Long findRoleCount(Roleinfo roleinfo) {
        Long total = roleRepository.findRoleCount(roleinfo);
        return total;
    }

    /***
     * 获取分页列表
     * @param roleinfo
     * @return
     */
    public List<Roleinfo> findRole(Roleinfo roleinfo) {
        List<Roleinfo> userInfoList = roleRepository.findRole(roleinfo);
        return userInfoList;
    }

    /***
     * 新增
     * @param roleinfo
     * @return
     */
    public void addRole(Roleinfo roleinfo) {
        roleRepository.addRole(roleinfo);
    }

    /***
     * 单个删除
     * @param id
     */
    public void deleteById(Integer id) {
        roleRepository.deleteById(id);
    }
}
