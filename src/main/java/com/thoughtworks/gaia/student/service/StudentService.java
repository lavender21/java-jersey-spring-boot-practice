package com.thoughtworks.gaia.student.service;

import com.exmertec.yaz.query.EqualQuery;
import com.thoughtworks.gaia.common.Loggable;
import com.thoughtworks.gaia.common.exception.ErrorCode;
import com.thoughtworks.gaia.common.exception.NotFoundException;
import com.thoughtworks.gaia.student.ArticalMapper;
import com.thoughtworks.gaia.student.dao.ArticalDao;
import com.thoughtworks.gaia.student.dao.StudentDao;
import com.thoughtworks.gaia.student.StudentMapper;
import com.thoughtworks.gaia.student.entity.Artical;
import com.thoughtworks.gaia.student.entity.Student;
import com.thoughtworks.gaia.student.model.ArticalModel;
import com.thoughtworks.gaia.student.model.StudentModel;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.sql.SQLException;

/**
 * Created by lavender on 17-4-24.
 */
@Component
@Transactional
public class StudentService implements Loggable {
    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private ArticalMapper articalMapper;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private ArticalDao articalDao;

    public Student getStudent(Long id) {
        StudentModel studentModel = studentDao.idEquals(id).querySingle();
        if (studentModel == null) {
            error("student is not find with" + id);
            throw new NotFoundException(ErrorCode.RESOURCE_NOT_FOUND);
        }
        return studentMapper.map(studentModel, Student.class);
    }

    public Student addStudent(Student student) {
        StudentModel studentModel = studentMapper.map(student, StudentModel.class);
        studentDao.save(studentModel);
        return studentMapper.map(studentModel, Student.class);
    }

    public void deleteStudent(Long id) {
        StudentModel studentModel = studentDao.idEquals(id).querySingle();
        if (studentModel == null) {
            error("student is not exist" + id);
            throw new NotFoundException();
        }
        studentDao.remove(studentModel);
    }

    public Student updateStudent(Student student) {
        StudentModel studentModel = studentMapper.map(student, StudentModel.class);
        studentModel = studentDao.update(studentModel);
        return studentMapper.map(studentModel, Student.class);
    }

    public Artical addArticalToStudent(Long studentId, Artical artical) {
        artical.setStudent_id(studentId);
        ArticalModel articalModel = articalMapper.map(artical, ArticalModel.class);
        articalDao.save(articalModel);
        return articalMapper.map(articalModel, Artical.class);
    }

    public Artical getArtical(Long studentId, Long articalId) {
        StudentModel studentModel = studentDao.idEquals(studentId).querySingle();
        if (studentModel == null) {
            error("not found student");
            throw new NotFoundException();
        }
        ArticalModel articalModel = articalDao
                .where(new EqualQuery("id",articalId),new EqualQuery("student_id",studentId))
                .querySingle();
        if (articalModel == null) {
            error("not found artical");
            throw new NotFoundException();
        }
        return articalMapper.map(articalModel,Artical.class);
    }

    public Artical updateArtical(Long id, Artical artical) {
        ArticalModel articalModel = articalDao
                .where(new EqualQuery("id",artical.getId()),new EqualQuery("student_id",id))
                .querySingle();
        StudentModel studentModel = studentDao.idEquals(id).querySingle();
        if (studentModel == null || articalModel == null)
        {
            error("not found student or artical");
            throw new NotFoundException();
        }
        artical.setStudent_id(id);
        articalModel = articalDao.update(articalMapper.map(artical,ArticalModel.class));
        return articalMapper.map(articalModel,Artical.class);
    }

    public void deleteArtical(Long id) {
        ArticalModel articalModel = articalDao.idEquals(id).querySingle();
        if (articalModel == null){
            error("not found artical");
            throw new NotFoundException();
        }
        articalDao.remove(articalModel);
    }
}
