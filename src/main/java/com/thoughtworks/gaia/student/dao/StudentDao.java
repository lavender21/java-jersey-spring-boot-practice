package com.thoughtworks.gaia.student.dao;

import com.thoughtworks.gaia.common.jpa.BaseDaoWrapper;
import com.thoughtworks.gaia.student.model.StudentModel;
import org.springframework.stereotype.Component;

/**
 * Created by lavender on 17-4-24.
 */
@Component
public class StudentDao extends BaseDaoWrapper<StudentModel> {
    public StudentDao() {super(StudentModel.class);}
}
