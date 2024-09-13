package com.example.StudentCompetency.mapper;

import com.example.StudentCompetency.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;


public interface UserMapper {
    @Select("SELECT* FROM f_user")
    ArrayList<User> findAll();
    @Insert("INSERT INTO `f_user`(`userName`, `password`,`email`,`salt`,`role`) VALUES (#{userName},#{password},#{email},#{salt},#{role})")
    @Transactional
    void save(User user);
    @Update("UPDATE f_user set gender=#{gender},age=#{age},avatar=#{avatar,jdbcType=BLOB, typeHandler=org.apache.ibatis.type.BlobTypeHandler},email=#{email} WHERE id=#{id}")
    @Transactional
    void updateById(User user);
    @Delete("DELETE FROM f_user WHERE id=#{id}")
    void deletebyId(Long id);
    @Select("SELECT * FROM f_user WHERE id=#{id} LIMIT 1")
    User findById(Long id);
    @Select("SELECT * FROM f_user WHERE userName=#{userName} LIMIT 1")
    User findByUserName(String userName);
    @Select("SELECT `id_friend` FROM friend_list WHERE id=#{id}")
    ArrayList<Integer> getFriendList(Long id);
    @Select("SELECT * FROM f_user WHERE (userName LIKE CONCAT('%', #{str}, '%') OR id = #{str} AND id != #{id}) AND role != 'a' ")
    ArrayList<User> searchFriendA(Long id,String str);
    @Select("SELECT * FROM f_user WHERE (userName LIKE CONCAT('%', #{str}, '%') OR id = #{str} AND id != #{id})")
    ArrayList<User> searchFriendB(Long id,String str);
    @Insert("INSERT INTO friend_list(`id`,`id_friend`,`date`) VALUES (#{id1},#{id2},NOW())")
    void addFriend(Long id1, Long id2);
    @Insert("INSERT INTO friend_list(`id`,`id_friend`,`date`) VALUES (#{id2},#{id1},NOW())")
    void antiAddFriend(Long id1, Long id2);
    @Insert("INSERT INTO friend_request(`id`,`id_friend`,`date`) VALUES (#{id2},#{id1},NOW())")
    void friendRequest(Long id1, Long id2);
    @Select("SELECT id_friend FROM friend_request WHERE id= #{id}")
    ArrayList<Long> getFriendRequest(Long id);
    @Select("SELECT id_friend FROM friend_request WHERE id=#{id2} AND id_friend=#{id1}")
    ArrayList<Long> checkFriendRequest(Long id1,Long id2);
    @Delete("DELETE FROM friend_list WHERE id=#{id1} AND id_friend=#{id2}")
    void deleteFriend(Long id1, Long id2);
    @Delete("DELETE FROM friend_list WHERE id=#{id2} AND id_friend=#{id1}")
    void antiAeleteFriend(Long id1, Long id2);
    @Delete("DELETE FROM friend_request WHERE id=#{id1} AND id_friend=#{id2}")
    void deleteFriendRequest(Long id1, Long id2);

}
