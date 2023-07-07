package ru.skypro.homework_4_6_streams.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework_4_6_streams.model.Avatar;
import ru.skypro.homework_4_6_streams.model.Student;
import ru.skypro.homework_4_6_streams.repository.AvatarRepository;
import ru.skypro.homework_4_6_streams.services.AvatarService;
import ru.skypro.homework_4_6_streams.services.StudentService;

import javax.persistence.EntityNotFoundException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
public class AvatarServiceImpl implements AvatarService {
    private final Logger logger = LoggerFactory.getLogger(AvatarServiceImpl.class);
    private final AvatarRepository avatarRepository;
    private final StudentService studentService;
    @Value("${avatars.dir.path}")
    private String avatarsDir;

    public AvatarServiceImpl(AvatarRepository avatarRepository, StudentService studentService) {
        this.avatarRepository = avatarRepository;
        this.studentService = studentService;
    }

    @Override
    public Avatar findAvatar(Long studentId) {
        logger.info("Downloading a student avatar from the database: {}", studentId);

        logger.info("Loaded student avatar from database: {}", studentId);
        return avatarRepository.findByStudentId(studentId).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Avatar findOrCreateAvatar(Long studentId) {
        logger.info("Find Or Create Avatar: {}", studentId);
        return avatarRepository.findByStudentId(studentId).orElse(new Avatar());
    }

    @Override
    public void uploadAvatar(Long studentId, MultipartFile avatarFile) throws IOException {
        logger.info("Uploading a student avatar: {}, {}", studentId, avatarFile);
        Student student = studentService.get(studentId);
        Path filePath = buildPath(student, avatarFile.getOriginalFilename());
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (
                InputStream is = avatarFile.getInputStream();
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
        }
        Avatar avatar = findOrCreateAvatar(studentId);

        avatar.setStudent(student);
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(avatarFile.getSize());
        avatar.setMediaType(avatarFile.getContentType());
        avatar.setData(avatarFile.getBytes());
        logger.info("Student avatar upload completed: {}, {}", studentId, avatarFile);
        avatarRepository.save(avatar);
    }

    private String getExtensions(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    private Path buildPath(Student student, String fileName) {
        return Path.of(avatarsDir, student.getId() + "-" + student.getName() + "-" + getExtensions(fileName));
    }

    @Override
    public List<Avatar> getPage(Integer pageNumber, Integer pageSize) {
        logger.info("Get Page Avatars: {}, {}", pageNumber, pageSize);
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
        return avatarRepository.findAll(pageRequest).getContent();
    }
}
