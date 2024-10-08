package ru.practicum.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.dto.user.NewUserRequest;
import ru.practicum.dto.user.UserDto;
import ru.practicum.user.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceBase implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserDto createUser(NewUserRequest newUserRequest) {
        return null;
    }

    @Override
    public List<UserDto> getUsers(List<Long> ids, Integer from, Integer size) {
        return List.of();
    }

    @Override
    public void deleteUser(Long userId) {

    }
}
