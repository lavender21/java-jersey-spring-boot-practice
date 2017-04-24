package com.thoughtworks.gaia.student.service;

import com.thoughtworks.gaia.common.Loggable;
import com.thoughtworks.gaia.common.exception.NotFoundException;
import com.thoughtworks.gaia.student.dao.StudentDao;
import com.thoughtworks.gaia.student.StudentMapper;
import com.thoughtworks.gaia.student.entity.Student;
import com.thoughtworks.gaia.student.model.StudentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 * Created by lavender on 17-4-24.
 */
@Component
@Transactional
public class StudentService implements Loggable{
    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private StudentDao studentDao;

    public Student getStudent(Long id)
    {
        StudentModel studentModel = studentDao.idEquals(id).querySingle();
        if (studentModel == null)
        {
            error("student is not find with" + id);
            throw new NotFoundException();
        }
        return studentMapper.map(studentModel,Student.class);
    }
}
