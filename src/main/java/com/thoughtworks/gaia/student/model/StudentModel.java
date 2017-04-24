package com.thoughtworks.gaia.student.model;

import com.thoughtworks.gaia.common.jpa.IdBaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by lavender on 17-4-24.
 */
@Entity
@Table(name = "student")
public class StudentModel extends IdBaseModel{
    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Column(name = "klass", length = 20)
    private String klass;

    @Column(name = "age", length = 11)
    private int age;

    @Column(name = "birth")
    private Date birth;

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getKlass() {return klass;}

    public void setKlass(String klass) {this.klass = klass;}

    public int getAge() {return age;}

    public void setAge(int age) {this.age = age;}

    public Date getBirth() {return birth;}

    public void setBirth(Date birth) {this.birth = birth;}
}
