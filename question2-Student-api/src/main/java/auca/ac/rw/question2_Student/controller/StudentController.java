package auca.ac.rw.question2_Student.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import auca.ac.rw.question2_Student.modal.Student;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private List<Student> students = new ArrayList<>();

    public StudentController() {
        Student student1 = new Student();
        student1.setStudentId(1);
        student1.setFirstName("John");
        student1.setLastName("Doe");
        student1.setEmail("john@example.com");
        student1.setMajor("Computer Science");
        student1.setGpa(3.5);

        Student student2 = new Student();
        student2.setStudentId(2);
        student2.setFirstName("Jane");
        student2.setLastName("Smith");
        student2.setEmail("jane@example.com");
        student2.setMajor("Computer Science");
        student2.setGpa(3.8);

        Student student3 = new Student();
        student3.setStudentId(3);
        student3.setFirstName("Alice");
        student3.setLastName("Johnson");
        student3.setEmail("alice@example.com");
        student3.setMajor("Mathematics");
        student3.setGpa(2.9);

        Student student4 = new Student();
        student4.setStudentId(4);
        student4.setFirstName("Bob");
        student4.setLastName("Williams");
        student4.setEmail("bob@example.com");
        student4.setMajor("Physics");
        student4.setGpa(3.2);

        Student student5 = new Student();
        student5.setStudentId(5);
        student5.setFirstName("Charlie");
        student5.setLastName("Brown");
        student5.setEmail("charlie@example.com");
        student5.setMajor("Computer Science");
        student5.setGpa(3.9);

        students.add(student1);
        students.add(student2);
        students.add(student3);
        students.add(student4);
        students.add(student5);
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return students;
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable int studentId) {
        Student student = students.stream()
                .filter(s -> s.getStudentId() == studentId)
                .findFirst()
                .orElse(null);
        return student != null ? ResponseEntity.ok(student) : ResponseEntity.notFound().build();
    }

    @GetMapping("/major/{major}")
    public List<Student> getStudentsByMajor(@PathVariable String major) {
        return students.stream()
                .filter(s -> s.getMajor().equalsIgnoreCase(major))
                .collect(Collectors.toList());
    }

    @GetMapping("/filter")
    public List<Student> filterStudentsByGpa(@RequestParam double gpa) {
        return students.stream()
                .filter(s -> s.getGpa() >= gpa)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<Student> registerStudent(@RequestBody Student student) {
        students.add(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<Student> updateStudent(@PathVariable int studentId, @RequestBody Student updatedStudent) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getStudentId() == studentId) {
                updatedStudent.setStudentId(studentId);
                students.set(i, updatedStudent);
                return ResponseEntity.ok(updatedStudent);
            }
        }
        return ResponseEntity.notFound().build();
    }}