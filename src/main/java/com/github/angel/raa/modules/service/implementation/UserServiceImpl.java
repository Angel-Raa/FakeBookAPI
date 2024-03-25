package com.github.angel.raa.modules.service.implementation;

import com.github.angel.raa.modules.exception.UserNotFoundExceptionHandler;
import com.github.angel.raa.modules.persistence.modesl.Users;
import com.github.angel.raa.modules.persistence.repository.UserRepository;
import com.github.angel.raa.modules.service.intefaces.UserService;
import com.github.angel.raa.modules.utils.DTO.UserDTO;
import com.github.angel.raa.modules.utils.api.Message;
import com.github.angel.raa.modules.utils.api.Response;
import com.github.angel.raa.modules.utils.jwt.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final JwtTokenUtils tokenUtils;
    @Transactional(readOnly = true)
    @Override
    public Set<UserDTO> getAllUsers() {
        return repository.findAll()
                .stream().map(UserServiceImpl::userToDto)
                .collect(Collectors.toSet());
    }

    @Transactional(readOnly = true)
    @Override
    public UserDTO getUserById(Long userId) {
        return repository.findById(userId)
                .stream()
                .findFirst()
                .map(UserServiceImpl::userToDto)
                .orElseThrow(() -> new UserNotFoundExceptionHandler(
                        Message.USER_NOT_FOUND_USERNAME, 404
                ));
    }
    @Transactional(readOnly = true)
    @Override
    public UserDTO getUserByUsername(String username) {
        return repository.findByUsername(username)
                .stream()
                .findFirst()
                .map(UserServiceImpl::userToDto)
                .orElseThrow(() -> new UserNotFoundExceptionHandler(
                        Message.USER_NOT_FOUND_USERNAME, 404
                ));
    }

    @Override
    public Response<UserDTO> create(UserDTO dto, String token) {
        return null;
    }

    @Override
    public Response<UserDTO> update(UserDTO dto, String token) {
        return null;
    }

    @Contract("_ -> new")
    private static @NotNull UserDTO userToDto(@NotNull Users users){
        return new UserDTO(
                users.getUserId(),
                users.getUsername(),
                users.getEmail(),
                users.getImage().getUrl(),
                users.getBio(),
                users.getCreatedAt(),
                users.getUpdateAt()

        );
    }
}
