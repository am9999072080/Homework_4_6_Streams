package ru.skypro.homework_4_6_streams.services;

import ru.skypro.homework_4_6_streams.model.Student;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public interface StudentService {
    Student add(Student student);

    Student remove(Long id);

    Student update(Student student);

    Student get(Long id);

    Collection<Student> getByAge(Integer startAge, Integer endAge);

    Collection<Student> getAll();

    Integer getStudentsCount();

    Float getStudentsAverageAge();

    List<Student> getLastFiveStudents();

    List<Student> getByName(String name);

    List<String> getNamesByA();

    Double getStudentsAverageAgeByStream();

    Stream<Student> findStudentByNameWithInitial_(Character letter);

    void printStudents();

    void printStudentsSync();

}

