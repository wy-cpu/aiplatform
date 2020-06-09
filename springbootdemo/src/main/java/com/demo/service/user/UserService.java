package com.demo.service.user;

import com.demo.model.user.UserInfo;
import com.demo.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /***
     * 根据 用户名 查询 相关的用户信息
     * @param username
     * @return
     */
    public UserInfo findUserId(String username) {
        UserInfo userInfo = userRepository.findUserId(username);
        return userInfo;
    }

    /***
     * 查询总条数
     * @return
     */
    public Long findUserCount(UserInfo userInfo) {
        Long total = userRepository.findUserCount(userInfo);
        return total;
    }

    /***
     * 获取分页列表
     * @param userInfo
     * @return
     */
    public List<UserInfo> findUser(UserInfo userInfo) {
        List<UserInfo> userInfoList = userRepository.findUser(userInfo);
        return userInfoList;
    }
}
