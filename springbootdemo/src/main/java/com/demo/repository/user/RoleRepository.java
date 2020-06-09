package com.demo.repository.user;

import com.demo.model.user.Roleinfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleRepository {
    /***
     * 获取总条数
     * @param roleinfo
     * @return
     */
    Long findRoleCount(Roleinfo roleinfo);

    /***
     * 获取分页列表
     * @param roleinfo
     * @return
     */
    List<Roleinfo> findRole(Roleinfo roleinfo);

    /**
     * 新增
     * @param roleinfo
     * @return
     */
    void addRole(Roleinfo roleinfo);

    /***
     * 单个删除
     * @param id
     */
    void deleteById(Integer id);
}
