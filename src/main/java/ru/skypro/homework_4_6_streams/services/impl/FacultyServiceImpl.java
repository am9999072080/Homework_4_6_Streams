package ru.skypro.homework_4_6_streams.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.skypro.homework_4_6_streams.exceptions.EntityNotFoundException;
import ru.skypro.homework_4_6_streams.exceptions.IncorrectArgumentException;
import ru.skypro.homework_4_6_streams.model.Faculty;
import ru.skypro.homework_4_6_streams.repository.FacultyRepository;
import ru.skypro.homework_4_6_streams.services.FacultyService;

import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;

@Service
public class FacultyServiceImpl implements FacultyService {
    private final Logger logger = LoggerFactory.getLogger(FacultyServiceImpl.class);
    private final FacultyRepository repository;

    public FacultyServiceImpl(FacultyRepository repository) {
        this.repository = repository;
    }

    @Override
    public Faculty add(Faculty faculty) {
        logger.info("Creation of a faculty: {}", faculty);
        logger.info("Faculty creation: {}", faculty);
        return repository.save(faculty);
    }

    @Override
    public Faculty remove(Long id) {
        logger.info("Deleting a faculty by id: {}", id);
        Faculty faculty = get(id);
        repository.deleteById(id);
        logger.info("Faculty removed by id: {}", id);
        return faculty;
    }

    @Override
    public Faculty update(Faculty faculty) {
        logger.info("Faculty renewal: {}", faculty);
        Faculty existedFaculty = get(faculty.getId());
        logger.info("Faculty Updated: {}", faculty);
        return repository.save(faculty);
    }

    @Override
    public Faculty get(Long id) {
        logger.info("Getting a faculty by ID: {}", id);
        Optional<Faculty> faculty = repository.findById(id);

        if (faculty.isPresent()) {
            logger.info("Obtained by faculty by ID: {}", id);
            return faculty.get();
        } else {
            logger.error("Incorrect id of faculty {} ", id);
            throw new EntityNotFoundException();
        }
    }

    @Override
    public Collection<Faculty> getByNameOrColor(String name, String color) {
        logger.info("Getting faculties by name or by color: {}, {}", name, color);
        if (!StringUtils.hasText(name) && !StringUtils.hasText(color)) {
            logger.error("Incorrect name and color of faculty {}, {} ", name, color);
            throw new IncorrectArgumentException("Некорректное наименование и цвет факультета");
        }
        logger.debug("Faculty obtained (name, color): {}, {}", name, color);
        return repository.findFacultiesByNameIgnoreCaseOrColorIgnoreCase(name, color);
    }

    @Override
    public Collection<Faculty> getAll() {
        logger.info("Getting all faculties: ");
        logger.info("List of all faculties: ");
        return repository.findAll();
    }

    public String findLongestFacultyName() {
        logger.info("Search for the longest department name");
        logger.info("Longest faculty name: ");
        return repository.findAll().stream()
                .map(Faculty::getName)
                .max(Comparator.comparingInt(String::length))
                .orElse("");
    }
}
