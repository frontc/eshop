package cn.lefer.eshop.user;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Mapper
@Repository
public interface UserRepository {
    @Results(id = "userResult", value = {
            @Result(property = "userID", column = "user_id", id = true),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "password", column = "password"),
            @Result(property = "salt", column = "salt"),
            @Result(property = "userStatus", column = "user_status"),
            @Result(property = "createDate", column = "create_date"),
            @Result(property = "updateDate", column = "update_date"),
            @Result(property = "creator", column = "creator"),
            @Result(property = "updater", column = "updater"),
            @Result(property = "deleteFlag", column = "delete_flag")
    })
    @Select("select user_id,user_name,password,salt,user_status,create_date,update_date,creator,updater,delete_flag " +
            "from t_user " +
            "where username = #{userName} " +
            "and delete_flag = #{deleteFlag}")
    User getUserByUserName(String userName, boolean deleteFlag);

    @Select("select tr.role_name " +
            "from t_role tr , t_user_role tur " +
            "where tr.role_id=tur.role_id and " +
            "      tr.delete_flag=#{deleteFlag} and " +
            "      tur.user_id=#{userID} ")
    HashSet<String> getRoleByUserID(Long userID, boolean deleteFlag);

    @Select("select tp.permission_name " +
            "from t_role tr , t_user_role tur, t_permission tp,t_role_permission trp " +
            "where tur.user_id=#{userID} and tr.role_id=tur.role_id and tr.delete_flag=#{deleteFlag} " +
            "and trp.role_id=tr.role_id and tp.delete_flag=#{deleteFlag} and trp.permission_id=tp.permission_id")
    HashSet<String> getPermissionByUserID(Long userID,boolean deleteFlag);
}
