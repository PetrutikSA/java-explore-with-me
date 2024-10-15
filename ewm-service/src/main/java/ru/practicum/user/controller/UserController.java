package ru.practicum.user.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.StatsClient;
import ru.practicum.dto.user.NewUserRequest;
import ru.practicum.dto.user.UserDto;
import ru.practicum.ewm.stats.dto.EndpointHitDto;
import ru.practicum.user.service.UserService;

import java.util.List;

import static ru.practicum.config.EWMServiceAppConfig.APP_NAME;

@RestController
@RequestMapping("/admin/users")
@RequiredArgsConstructor
@Validated
public class UserController {
    private final UserService userService;
    private final StatsClient statsClient;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody @Valid NewUserRequest newUserRequest, HttpServletRequest request) {
        statsClient.createRecord(new EndpointHitDto(APP_NAME, request.getRequestURI(), request.getRemoteAddr()));
        return userService.createUser(newUserRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getUsers(
            @RequestParam(name = "ids", required = false) List<@Positive Long> ids,
            @RequestParam(name = "from", required = false, defaultValue = "0") @PositiveOrZero Integer from,
            @RequestParam(name = "size", required = false, defaultValue = "10") @Positive Integer size,
            HttpServletRequest request) {
        statsClient.createRecord(new EndpointHitDto(APP_NAME, request.getRequestURI(), request.getRemoteAddr()));
        return userService.getUsers(ids, from, size);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable @Positive Long userId, HttpServletRequest request) {
        statsClient.createRecord(new EndpointHitDto(APP_NAME, request.getRequestURI(), request.getRemoteAddr()));
        userService.deleteUser(userId);
    }
}
