package com.tony.imagelink.mapper;

import com.tony.imagelink.mapper.entity.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @InterfaceName UserInfoMapper
 * Description TODO
 * @Author hzf
 * @Date 2021/2/4 19:05
 */
public interface UserInfoMapper {

    int insert(UserInfo user);

    /**
     * 查询一个用户信息，查询包含用户id值
     * @param name
     * @return
     */
    UserInfo selectUserInfo(@Param("name") String name, @Param("id") Integer id);

    List<UserInfo> checkNameExists(@Param("name") String name, @Param("id") Integer id);

    // 插入个人喜欢的图集，个人喜欢的图片链接，根据id值进行;会员信息0普通，1会员,根据id值修改。
    int updateUserCollections(UserInfo userInfo);
}
