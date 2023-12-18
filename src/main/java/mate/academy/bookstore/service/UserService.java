package mate.academy.bookstore.service;

import mate.academy.bookstore.dto.UserRegistrationRequestDto;
import mate.academy.bookstore.dto.UserResponseDto;

public interface UserService {
    UserResponseDto save(UserRegistrationRequestDto requestDto);
}
