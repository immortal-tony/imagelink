package com.tony.imagelink.mapper;

import com.tony.imagelink.mapper.entity.UserInfo;
import org.apache.ibatis.annotations.Param;

/**
 * @InterfaceName UserInfoMapper
 * Description TODO
 * @Author hzf
 * @Date 2021/2/4 19:05
 */
public interface UserInfoMapper {

    int insert(UserInfo user);

    UserInfo selectUserInfo(@Param("name") String name);

    // 插入个人喜欢的图集，个人喜欢的图片链接，最好根据id值进行。
}
