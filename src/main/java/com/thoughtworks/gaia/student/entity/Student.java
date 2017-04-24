package com.thoughtworks.gaia.student.entity;

import java.util.Date;

public class Student {
    private Long id;

    private String name;

    private String klass;

    private int age;

    private Date birth;

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getKlass() {return klass;}

    public void setKlass(String klass) {this.klass = klass;}

    public int getAge() {return age;}

    public void setAge(int age) {this.age = age;}

    public Date getBirth() {return birth;}

    public void setBirth(Date birth) {this.birth = birth;}

}
