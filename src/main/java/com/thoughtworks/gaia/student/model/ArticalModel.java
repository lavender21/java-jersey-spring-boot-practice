package com.thoughtworks.gaia.student.model;

import com.thoughtworks.gaia.common.jpa.IdBaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by lavender on 17-4-25.
 */
@Entity
@Table(name = "ARTICAL")
public class ArticalModel extends IdBaseModel {
    @Column(name = "title", length = 100)
    private String title;

    @Column(name="content",columnDefinition = "text")
    @Lob
    private String content;

    @Column(name = "publish_time")
    private Date publish_time;

    @Column(name = "student_id")
    private Long student_id;

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
