package com.example.StudentCompetency.mapper;

import com.example.StudentCompetency.entity.Video;
import org.apache.ibatis.annotations.*;
import java.util.ArrayList;

public interface VideoMapper {

    @Select("SELECT* FROM f_video")
    ArrayList<Video> findAll();
    @Select("SELECT * FROM f_video WHERE id=#{id} LIMIT 1")
    Video findById(Long id);
    @Select("SELECT * FROM f_video WHERE title=#{title} LIMIT 1")
    Video findByTitle(String title);

    @Select("SELECT publisher FROM f_video WHERE id=#{id} LIMIT 1")
    Integer findPublisherId(Long id);

    @Insert("INSERT INTO f_video (`title`,`date`,`publisher`,`url`) VALUES (#{title},NOW(),#{publisher},#{url})")
    void save(Video video);
    @Update("UPDATE f_video set title=#{title} WHERE id=#{id}")
    void updateById(Video video);
    @Update("UPDATE f_video set title=#{title}, url=#{url} WHERE id=#{id}")
    void updateURL(Video video);
    @Delete("DELETE FROM f_video WHERE id=#{id}")
    void deleteById(Long id);
    @Select("SELECT vv.videoId \n" +
            "FROM video_viewer vv\n" +
            "JOIN f_video fv ON vv.videoId = fv.id\n" +
            "WHERE vv.viewer = #{id}\n" +
            "ORDER BY fv.date DESC")
    ArrayList<Long> getVideoListByUserId(Long id);
    @Insert("INSERT INTO video_viewer (`videoId`,`videotitle`,`viewer`) VALUES  (#{videoId},#{title},#{id})")
    void addViewer(Long videoId,String title,Long id);
    @Select("SELECT * FROM f_video WHERE publisher=#{id} ORDER BY date DESC")
    ArrayList<Video> getRecordedListByUserId(Long id);

    @Update("UPDATE f_video set cover=#{cover, jdbcType=BLOB, typeHandler=org.apache.ibatis.type.BlobTypeHandler} WHERE id=#{id}")
    void updateCover(Video video);
}
