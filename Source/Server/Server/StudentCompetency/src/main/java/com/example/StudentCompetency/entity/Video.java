package com.example.StudentCompetency.entity;

import lombok.Data;
@Data
public class Video {
    private Integer id;
    private String title;
    private String date;
    private Integer publisher;
    private String url;
    private byte[] cover;

}
