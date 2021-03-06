package test.functional.product;

import com.thoughtworks.gaia.GaiaApplication;
import com.thoughtworks.gaia.common.constant.EnvProfile;
import com.thoughtworks.gaia.common.exception.NotFoundException;
import com.thoughtworks.gaia.student.ArticalMapper;
import com.thoughtworks.gaia.student.StudentMapper;
import com.thoughtworks.gaia.student.dao.ArticalDao;
import com.thoughtworks.gaia.student.dao.StudentDao;
import com.thoughtworks.gaia.student.entity.Artical;
import com.thoughtworks.gaia.student.entity.Student;
import com.thoughtworks.gaia.student.model.ArticalModel;
import com.thoughtworks.gaia.student.model.StudentModel;
import com.thoughtworks.gaia.student.service.StudentService;
import jdk.nashorn.internal.runtime.ECMAException;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    ArticalDao articalDao;

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    ArticalMapper articalMapper;

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

    @Test
    public void should_add_artical_with_student_id() throws Exception {
        StudentModel studentModel = createStudentModel();
        studentDao.save(studentModel);
        Long studentId = studentModel.getId();
        Artical artical = createArticalEntity();

        Artical actual = studentService.addArticalToStudent(studentId, artical);
        artical = articalMapper.map(articalDao.idEquals(actual.getId()).querySingle(), Artical.class);

        assertThat(actual).isEqualToComparingFieldByField(artical);
    }

    @Test
    public void should_get_artical_with_student_id_and_artical_id() throws Exception {
        StudentModel studentModel = createStudentModel();
        ArticalModel articalModel = createArticalModel();
        studentDao.save(studentModel);
        articalModel.setStudent_id(studentModel.getId());
        articalDao.save(articalModel);
        Artical expect = articalMapper.map(articalModel, Artical.class);

        Artical actual = studentService.getArtical(studentModel.getId(), articalModel.getId());

        assertThat(actual).isEqualToComparingFieldByField(expect);
    }

    @Test(expected = Exception.class)
    public void should_throw_exception_when_artical_not_exist_in_student() {
        StudentModel studentModel = createStudentModel();
        ArticalModel articalModel = createArticalModel();
        studentDao.save(studentModel);
        articalDao.save(articalModel);

        studentService.getArtical(studentModel.getId(), articalModel.getStudent_id());
    }

    @Test(expected = Exception.class)
    public void should_throw_exception_when_student_not_exist() {
        studentService.getArtical(-1L, 0L);
    }

    @Test
    public void should_update_artical_with_student_id_and_artical(){
        StudentModel studentModel = createStudentModel();
        ArticalModel articalModel = createArticalModel();
        studentDao.save(studentModel);
        articalModel.setStudent_id(studentModel.getId());
        articalDao.save(articalModel);

        articalModel.setContent("modify");
        Artical actual = studentService.updateArtical(studentModel.getId(),articalMapper.map(articalModel,Artical.class));

        assertThat(actual).isEqualToComparingFieldByField(articalMapper.map(articalModel,Artical.class));
    }


    @Test(expected = Exception.class)
    public void should_exception_when_artical_is_not_exist() {
        ArticalModel articalModel = createArticalModel();
        articalModel.setId(1L);
        StudentModel studentModel = createStudentModel();
        studentDao.save(studentModel);

        studentService.updateArtical(studentModel.getId(),articalMapper.map(articalModel,Artical.class));
    }

    @Test
    public void should_delete_artical_with_aritcal_id(){
        ArticalModel articalModel = createArticalModel();
        StudentModel studentModel = createStudentModel();
        studentDao.save(studentModel);
        articalModel.setStudent_id(studentModel.getId());
        articalDao.save(articalModel);

        Long id = articalModel.getId();
        studentService.deleteArtical(id);
        articalModel = articalDao.idEquals(id).querySingle();

        assertThat(articalModel).isEqualTo(null);
    }

    @Test(expected = Exception.class)
    public void should_exception_not_found_when_artical_not_exist(){
        studentService.deleteArtical(-1L);
    }

    @Test
    public void should_get_all_artical_list_with_student_id(){
        StudentModel studentModel = createStudentModel();
        studentDao.save(studentModel);
        ArticalModel articalModel = createArticalModel();
        articalModel.setStudent_id(studentModel.getId());
        articalDao.save(articalModel);
        ArticalModel articalModel2 = createArticalModel();
        articalModel2.setStudent_id(studentModel.getId());
        articalDao.save(articalModel2);
        List<Artical> expect = new ArrayList<Artical>();
        expect.add(articalMapper.map(articalModel,Artical.class));
        expect.add(articalMapper.map(articalModel2,Artical.class));

        List<Artical> actual= studentService.getAllArtical(studentModel.getId());

        assertThat(actual.size()).isEqualTo(expect.size());
        assertThat(actual.get(0)).isEqualToComparingFieldByField(expect.get(0));
        assertThat(actual.get(1)).isEqualToComparingFieldByField(expect.get(1));
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

    private Artical createArticalEntity() {
        Artical artical = new Artical();
        artical.setContent("safasfdsfsdaffdsafdf");
        artical.setTitle("aaa");
        artical.setPublish_time(formateDate("2017-04-24"));
        return artical;
    }

    private ArticalModel createArticalModel() {
        ArticalModel articalModel = new ArticalModel();
        articalModel.setTitle("hhhh");
        articalModel.setContent("hhahahahah");
        return articalModel;
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
