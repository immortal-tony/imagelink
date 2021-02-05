package com.tony.imagelink.Service;

import com.tony.imagelink.mapper.UserInfoMapper;
import com.tony.imagelink.mapper.entity.UserInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName Description
 * TODO
 * @Author hzf
 * @Date 2021/2/5 9:19
 */
@Service
public class UserService {
    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserInfoMapper userInfoMapper;

    /**
     * 验证用户名是否有存在
     *
     * @param name
     * @param id
     * @return
     */
    public Boolean checkNameExists(String name, Integer id) {
        if (StringUtils.isBlank(name) || null == id) {
            return null;
        }
        if (userInfoMapper.checkNameExists(name, id).size() > 0) {
            return false;
        }
        return true;
    }

    /**
     * 修改用户收藏夹子
     *
     * @param userInfo
     * @return
     */
    public UserInfo UpdateUserCollection(UserInfo userInfo) throws Exception {
        int ret = 1;
        if (StringUtils.isBlank(userInfo.getName()) || 0 != userInfo.getId()) {
            ret = 0; // 参数异常
            throw new Exception("参数异常！");
        }
        if (userInfoMapper.checkNameExists(userInfo.getName(), userInfo.getId()).size() > 0) {
            ret =  userInfoMapper.updateUserCollections(userInfo);
        } else {
            ret = 0;
        }
        if (ret == 0) {
            logger.info("个人收藏内容修改失败！");
        }
        return userInfoMapper.selectUserInfo(userInfo.getName(), userInfo.getId());
    }
}
