package org.zyx.vue_server.entity;

import lombok.Data;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;

/** JPA:
 *  添加@Entity 生命实体类,与数据库表映射
 */

@Entity(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column   //可写可不写
    private String name;
    private String pwd;
    private String sex;
    private String address;


}
