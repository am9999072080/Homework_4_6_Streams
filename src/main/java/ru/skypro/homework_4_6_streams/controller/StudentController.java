package ru.skypro.homework_4_6_streams.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework_4_6_streams.model.Faculty;
import ru.skypro.homework_4_6_streams.model.Student;
import ru.skypro.homework_4_6_streams.services.StudentService;

import java.util.Collection;
import java.util.stream.Stream;

@RestController
@RequestMapping("student")
@Tag(name = "Api: Для работы со студентами")
public class StudentController {
    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Создание студента")
    public ResponseEntity<Student> create(@RequestBody Student student) {
        Student addedStudent = service.add(student);
        return ResponseEntity.ok(addedStudent);
    }

    @PutMapping
    @Operation(summary = "Обновление студента")
    @ApiResponse(responseCode = "404", description = "Запрос некорректный")
    public ResponseEntity<Student> update(@RequestBody Student student) {
        Student updatedStudent = service.update(student);

        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping({"{id}"})
    @ApiResponse(responseCode = "404", description = "Запрос некорректный")
    @Operation(summary = "Удаление студента")
    public ResponseEntity<Student> delete(@PathVariable Long id) {
        Student deletedStudent = service.remove(id);

        return ResponseEntity.ok(deletedStudent);
    }

    @GetMapping({"{id}"})
    @ApiResponse(responseCode = "404", description = "Запрос некорректный")
    @Operation(summary = "Получение студента по ID")
    public ResponseEntity<Student> get(@PathVariable Long id) {
        Student student = service.get(id);

        return ResponseEntity.ok(student);
    }

    @GetMapping({"all"})
    @Operation(summary = "Получение списка всех студентов")
    public ResponseEntity<Collection<Student>> getAll() {
        Collection<Student> students = service.getAll();
        return ResponseEntity.ok(students);
    }

    @GetMapping("age")
    @Operation(summary = "Получение списка всех студентов по возрасту")
    public ResponseEntity<Collection<Student>> getByAge(@RequestParam Integer startAge,
                                                        @RequestParam Integer endAge) {
        Collection<Student> students = service.getByAge(startAge, endAge);
        return ResponseEntity.ok(students);
    }

    @GetMapping({"faculty/{studentId}"})
    @Operation(summary = "Получение факультет по студенту")
    public ResponseEntity<Faculty> getFaculty(@PathVariable Long studentId) {
        Faculty faculty = service.get(studentId).getFaculty();//вызываем service
        return ResponseEntity.ok(faculty);
    }

    @GetMapping({"count"})
    @Operation(summary = "Получение общее количество студентов")
    public ResponseEntity<Integer> getStudentsCount() {
        Integer count = service.getStudentsCount();
        return ResponseEntity.ok(count);
    }

    @GetMapping("age/average")
    @Operation(summary = "Получение среднего возраста студентов")
    public ResponseEntity<Float> getStudentsAverageAge() {
        Float averageAge = service.getStudentsAverageAge();
        return ResponseEntity.ok(averageAge);
    }

    @GetMapping("last")
    @Operation(summary = "Получение 5 последних студентов")
    public ResponseEntity<Collection<Student>> getLastFiveStudents() {
        Collection<Student> students = service.getLastFiveStudents();
        return ResponseEntity.ok(students);
    }

    @GetMapping({"byName{studentName}"})
    @Operation(summary = "Получение студента по имени")
    public ResponseEntity<Collection<Student>> getByName(@RequestParam String name) {
        Collection<Student> students = service.getByName(name);
        return ResponseEntity.ok(students);
    }

    @GetMapping({"Name-by-A"})
    @Operation(summary = "Получение имена студентов на букву А")
    public ResponseEntity<Collection<String>> getNameByA() {
        Collection<String> students = service.getNamesByA();
        return ResponseEntity.ok(students);
    }

    @GetMapping("age/average-stream")
    @Operation(summary = "Получение среднего возраста студентов (стрим)")
    public ResponseEntity<Double> getStudentsAverageAgeByStream() {
        Double averageAge = service.getStudentsAverageAgeByStream();
        return ResponseEntity.ok(averageAge);
    }

    @GetMapping("/получение студентов, у которых имена начинаются с буквы/{letter}")
    @Operation(summary = "Получение студентов по первой букве")
    public Stream<Student> getStudentsWithNameStartsWithLetter(@PathVariable Character letter) {
        return service.findStudentByNameWithInitial_(letter);
    }

    @GetMapping("print-students-name")
    @Operation(summary = "Вывод имен студентов")
    public ResponseEntity<Void> printStudents() {
        service.printStudents();
        return ResponseEntity.ok().build();
    }

    @GetMapping("print-students-name-synchronise")
    @Operation(summary = "Синхронизированный вывод имен студентов")
    public ResponseEntity<Void> printStudentsSync() {
        service.printStudentsSync();
        return ResponseEntity.ok().build();
    }
}
