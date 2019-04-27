package miaosha.demo.dao;

import miaosha.demo.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDao {

    @Select("select * from user where id = #{id}")   //这样写和写xml配置文件是一样的
    public User getById(@Param("id") long id);
}
