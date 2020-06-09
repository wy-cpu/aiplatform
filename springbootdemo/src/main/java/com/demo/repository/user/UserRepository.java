package com.demo.repository.user;

import com.demo.model.user.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserRepository {
    /***
     * 根据 用户名 查询 相关的用户信息
     * @param username
     * @return
     */
    UserInfo findUserId(String username);

    /**
     * 查询总条数
     * @return
     */
    Long findUserCount(UserInfo userInfo);

    /***
     * 获取分页列表
     * @param userInfo
     * @return
     */
    List<UserInfo> findUser(UserInfo userInfo);
}
