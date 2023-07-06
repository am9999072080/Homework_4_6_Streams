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
public class AvatarServiceImpl  {

}
