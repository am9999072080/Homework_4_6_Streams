package ru.skypro.homework_4_6_streams.services.impl;

import liquibase.repackaged.org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.skypro.homework_4_6_streams.exceptions.EntityNotFoundException;
import ru.skypro.homework_4_6_streams.model.Student;
import ru.skypro.homework_4_6_streams.repository.StudentRepository;
import ru.skypro.homework_4_6_streams.services.StudentService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

@Service
public class StudentServiceImpl  {

}