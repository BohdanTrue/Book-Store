package mate.academy.bookstore.service.impl;

import lombok.RequiredArgsConstructor;
import mate.academy.bookstore.dto.user.UserRegistrationRequestDto;
import mate.academy.bookstore.dto.user.UserResponseDto;
import mate.academy.bookstore.exception.RegistrationException;
import mate.academy.bookstore.mapper.UserMapper;
import mate.academy.bookstore.model.User;
import mate.academy.bookstore.repository.UserRepository;
import mate.academy.bookstore.service.UserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private static final String USER_ALREADY_REGISTERED_MESSAGE = "User is already registered";
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto requestDto) {
        if (userRepository.findUserByEmail(requestDto.getEmail()).isPresent()) {
            throw new RegistrationException(USER_ALREADY_REGISTERED_MESSAGE);
        }
        User savedUser = userRepository.save(userMapper.toModel(requestDto));
        return userMapper.toUserResponseDto(savedUser);
    }
}