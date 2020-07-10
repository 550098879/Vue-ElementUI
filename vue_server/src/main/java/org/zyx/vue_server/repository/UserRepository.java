package org.zyx.vue_server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zyx.vue_server.entity.User;

/**
 *  jpa类似mybatis plus 都自动封装了数据操作层的方法和借口
 *  继承接口
 *
 */

public interface UserRepository extends JpaRepository<User,Integer> {


}
