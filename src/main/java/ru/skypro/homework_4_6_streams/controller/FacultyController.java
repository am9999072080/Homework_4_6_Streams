package ru.skypro.homework_4_6_streams.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework_4_6_streams.model.Faculty;
import ru.skypro.homework_4_6_streams.model.Student;
import ru.skypro.homework_4_6_streams.services.FacultyService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("faculty")
public class FacultyController {
    private final FacultyService service;

    public FacultyController(FacultyService service) {
        this.service = service;
    }


    @PostMapping
    @Operation(summary = "Создание факультета")
    public ResponseEntity<Faculty> create(@RequestBody Faculty faculty) {
        Faculty addedFaculty = service.add(faculty);
        return ResponseEntity.ok(addedFaculty);
    }

    @PutMapping
    @Operation(summary = "Обновление факультета")
    @ApiResponse(responseCode = "404", description = "Запрос некорректный")
    public ResponseEntity<Faculty> update(@RequestBody Faculty faculty) {
        Faculty updatedFaculty = service.update(faculty);


        return ResponseEntity.ok(updatedFaculty);
    }

    @DeleteMapping({"{id}"})
    @ApiResponse(responseCode = "404", description = "Запрос некорректный")
    @Operation(summary = "Удаление факультета")
    public ResponseEntity<Faculty> delete(@PathVariable Long id) {
        Faculty deletedFaculty = service.remove(id);

        return ResponseEntity.ok(deletedFaculty);
    }

    @GetMapping({"{id}"})
    @ApiResponse(responseCode = "404", description = "Запрос некорректный")
    @Operation(summary = "Получение факультета по ID")
    public ResponseEntity<Faculty> get(@PathVariable Long id) {
        Faculty faculty = service.get(id);

        return ResponseEntity.ok(faculty);
    }

    @GetMapping({"all"})
    @Operation(summary = "Получение всех факультетов")
    public ResponseEntity<Collection<Faculty>> getAll() {
        Collection<Faculty> faculties = service.getAll();
        return ResponseEntity.ok(faculties);
    }

    @GetMapping("filter")
    @Operation(summary = "Получение факультетов по наименовании и по цвету")
    public ResponseEntity<Collection<Faculty>> getByNameOrColor(@RequestParam(required = false) String name, @RequestParam(required = false) String color) {
        Collection<Faculty> faculties = service.getByNameOrColor(name, color);
        return ResponseEntity.ok(faculties);
    }

    @GetMapping("students/{facultyId}")
    @Operation(summary = "Получения студентов по факультету")
    public ResponseEntity<List<Student>> getStudents(@PathVariable Long facultyId) {
        List<Student> students = service.get(facultyId).getStudents();//вызываем service
        return ResponseEntity.ok(students);
    }

    @GetMapping("longest-name")
    @Operation(summary = "получение самого длинного наименование факультета")
    public ResponseEntity<String> gettingTheLongestFacultyName() {
        return ResponseEntity.ok(service.findLongestFacultyName());
    }
}
