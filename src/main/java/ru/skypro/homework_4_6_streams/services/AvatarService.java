package ru.skypro.homework_4_6_streams.services;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework_4_6_streams.model.Avatar;

import java.io.IOException;
import java.util.List;

public interface AvatarService {
    Avatar findAvatar(Long studentId);

    Avatar findOrCreateAvatar(Long studentId);

    void uploadAvatar(Long studentId, MultipartFile avatarFile) throws IOException;

    List<Avatar> getPage(Integer pageNumber, Integer pageSize);
}
