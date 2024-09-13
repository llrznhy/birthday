package com.example.StudentCompetency.vo;

import lombok.Data;

@Data
public class VideoVO {
    private Integer id;
    private String title;
    private String date;
    private String publisher;
    private String url;
    private byte[] cover;
}
