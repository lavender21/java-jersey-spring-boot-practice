package com.thoughtworks.gaia.student.entity;

import java.util.Date;

/**
 * Created by lavender on 17-4-25.
 */
public class Artical {
    private Long id;

    private String title;

    private String content;

    private Date publish_time;

    private Long student_id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setPublish_time(Date publish_time) {
        this.publish_time = publish_time;
    }

    public Date getPublish_time() {
        return publish_time;
    }

    public void setStudent_id(Long student_id) {
        this.student_id = student_id;
    }

    public Long getStudent_id() {
        return student_id;
    }
}
