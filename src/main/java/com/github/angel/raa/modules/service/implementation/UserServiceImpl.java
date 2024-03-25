package com.github.angel.raa.modules.service.implementation;

import com.github.angel.raa.modules.exception.UserNotFoundExceptionHandler;
import com.github.angel.raa.modules.persistence.modesl.Users;
import com.github.angel.raa.modules.persistence.repository.UserRepository;
import com.github.angel.raa.modules.service.intefaces.UserService;
import com.github.angel.raa.modules.utils.dto.InfoDTO;
import com.github.angel.raa.modules.utils.api.Message;
import com.github.angel.raa.modules.utils.api.Response;
import com.github.angel.raa.modules.utils.dto.UserDTO;
import com.github.angel.raa.modules.utils.jwt.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final JwtTokenUtils tokenUtils;
    @Transactional(readOnly = true)
    @Override
    public Set<InfoDTO> getAllUsers() {
        return repository.findAll()
                .stream().map(UserServiceImpl::infoToDto)
                .collect(Collectors.toSet());
    }

    @Transactional(readOnly = true)
    @Override
    public InfoDTO getUserById(Long userId) {
        return repository.findById(userId)
                .stream()
                .findFirst()
                .map(UserServiceImpl::infoToDto)
                .orElseThrow(() -> new UserNotFoundExceptionHandler(
                        Message.USER_NOT_FOUND_USERNAME, 404
                ));
    }
    @Transactional(readOnly = true)
    @Override
    public InfoDTO getUserByUsername(String username) {
        return repository.findByUsername(username)
                .stream()
                .findFirst()
                .map(UserServiceImpl::infoToDto)
                .orElseThrow(() -> new UserNotFoundExceptionHandler(
                        Message.USER_NOT_FOUND_USERNAME, 404
                ));
    }
    @Transactional
    @Override
    public Response<UserDTO> create(UserDTO dto, String token) {
        String username = tokenUtils.getUsernameFromToke(token);
        Users users = repository.findByUsername(username).orElseThrow(() -> new UserNotFoundExceptionHandler(Message.USER_NOT_FOUND_USERNAME, 404) );
        users.setBio(dto.bio());
        users.setName(dto.name());
        repository.save(users);
        UserDTO data = userToDto(users);
        return new Response<>(Message.USER_CREATE, HttpStatus.CREATED, true, LocalDateTime.now(), data);
    }
    @Transactional
    @Override
    public Response<UserDTO> update(@NotNull UserDTO dto, String token) {
        String username = tokenUtils.getUsernameFromToke(token);
        Users users = repository.findByUsername(username).orElseThrow(() -> new UserNotFoundExceptionHandler(Message.USER_NOT_FOUND_USERNAME, 404) );
        users.setBio(dto.bio());
        users.setName(dto.name());
        repository.save(users);
        UserDTO data = userToDto(users);
        return new Response<>(Message.USER_UPDATE, HttpStatus.CREATED, true, LocalDateTime.now(), data);
    }

    @Contract("_ -> new")
    private static @NotNull InfoDTO infoToDto(@NotNull Users users){
        return new InfoDTO(
                users.getUserId(),
                users.getUsername(),
                users.getName(),
                users.getEmail(),
                users.getImage().getUrl(),
                users.getBio(),
                users.getCreatedAt(),
                users.getUpdateAt()

        );
    }

    @Contract("_ -> new")
    private static @NotNull UserDTO userToDto(@NotNull Users users){
        return new UserDTO(
                users.getName(),
                users.getBio()


        );
    }

}
