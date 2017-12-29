package example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import example.model.Student;
import example.model.Course;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTest {

    @Autowired
    private TestRestTemplate restTestTemplate;

    private ObjectMapper objectMapper;

    @Before
    public void setUp() throws Exception{
        objectMapper = new ObjectMapper();
    }

    @After
    public void tearDown() throws Exception{
        objectMapper = null;
    }

    @LocalServerPort
    private int port;

    @Test
    public void testCreateStudent() {
        List<String> steps = new ArrayList<String>();
        steps.add("1");
        Course course1 = new Course("1", "Programming", "Language 1", steps);

        List<Course> courses = new ArrayList<Course>();
        courses.add(course1);

        Student student = new Student("1", "Nhat Thai", "IT", courses);

        // post data from api
        HttpEntity<Student> request = new HttpEntity<>(student);
        String response = restTestTemplate.postForObject(
            "http://localhost:" + port + "/student", request, String.class);

        System.out.println("Result Response");
        System.out.println(response);

        // get data from api
        Student studentObj = restTestTemplate.getForObject(
            "http://localhost:" + port + "/students/1", Student.class);
        assertEquals(studentObj.getName(), "Nhat Thai");
    }

	@Test
	public void testGetStudents() {
        ResponseEntity<List> responseEntity = restTestTemplate.getForEntity(
            "http://localhost:" + port + "/students", List.class);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);

        System.out.println("List Response");
        List<Student> students = responseEntity.getBody();

        System.out.println(students);
        assertEquals(students.size(), 1);
    }

    @Test
	public void testGetStudentsByName() {
        ResponseEntity<List> responseEntity = restTestTemplate.getForEntity(
            "http://localhost:" + port + "/students?name=Nhat", List.class);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);

        System.out.println("List Response");
        List<Student> students = responseEntity.getBody();

        System.out.println(students);
        assertEquals(students.size(), 1);
    }

}