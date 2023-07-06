package ru.skypro.homework_4_6_streams.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.skypro.homework_4_6_streams.model.Avatar;

import java.util.Optional;

public interface AvatarRepository extends PagingAndSortingRepository<Avatar, Long> {


}
