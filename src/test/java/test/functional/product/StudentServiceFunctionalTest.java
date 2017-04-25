package test.functional.product;

import com.thoughtworks.gaia.GaiaApplication;
import com.thoughtworks.gaia.common.constant.EnvProfile;
import com.thoughtworks.gaia.common.exception.NotFoundException;
import com.thoughtworks.gaia.student.StudentMapper;
import com.thoughtworks.gaia.student.dao.StudentDao;
import com.thoughtworks.gaia.student.entity.Student;
import com.thoughtworks.gaia.student.model.StudentModel;
import com.thoughtworks.gaia.student.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created by lavender on 17-4-25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(GaiaApplication.class)
@Rollback
@Transactional
@ActiveProfiles({EnvProfile.TEST})
public class StudentServiceFunctionalTest {
    @Autowired
    StudentService studentService;

    @Autowired
    StudentDao studentDao;

    @Autowired
    StudentMapper studentMapper;

    @Test
    public void should_get_student_with_id() throws Exception {
        StudentModel studentModel = createStudentModel();
        studentDao.save(studentModel);
        Long studentId = studentModel.getId();

        Student student = studentService.getStudent(studentId);

        assertThat(student.getId()).isEqualTo(studentId);
    }

    @Test(expected = NotFoundException.class)
    public void should_throw_exception_when_id_not_exist() {
        studentService.getStudent(-1L);
    }

    @Test
    public void should_add_student_with_student() throws Exception {
        Student student = createStudentEntity();

        student = studentService.addStudent(student);

        Student newStudent = studentMapper.map(
                studentDao.idEquals(student.getId()).querySingle(), Student.class);
        assertThat(student).isEqualToComparingFieldByField(newStudent);
    }

    @Test
    public void should_update_student_with_newstudnet() throws Exception {
        StudentModel studentModel = createStudentModel();
        studentDao.save(studentModel);
        Student student = createStudentEntity();
        student.setId(studentModel.getId());

        Student actual = studentService.updateStudent(student);

        assertThat(actual).isEqualToComparingFieldByField(student);
    }

    private StudentModel createStudentModel() {
        StudentModel studentModel = new StudentModel();
        studentModel.setName("nrt");
        studentModel.setAge(21);
        studentModel.setKlass("313");
        studentModel.setBirth(formateDate("1995-10-21"));
        return studentModel;
    }

    private Student createStudentEntity() {
        Student student = new Student();
        student.setName("aaa");
        student.setAge(22);
        student.setKlass("313");
        student.setBirth(formateDate("1995-10-21"));
        return student;
    }

    private Date formateDate(String str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
