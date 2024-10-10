package ru.practicum.user;

import ru.practicum.dto.user.NewUserRequest;
import ru.practicum.dto.user.UserDto;

public class TestObjectsUser {
    public NewUserRequest newUserRequest;
    public UserDto userDto;

    public TestObjectsUser() {
        newUserRequest = new NewUserRequest();
        newUserRequest.setName("Name");
        newUserRequest.setEmail("name@ya.ru");

        userDto = new UserDto();
        userDto.setId(1L);
        userDto.setName(newUserRequest.getName());
        userDto.setEmail(newUserRequest.getEmail());
    }
}
