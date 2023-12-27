package mate.academy.bookstore.service;

import java.util.List;

import mate.academy.bookstore.dto.category.CategoryRequestDto;
import mate.academy.bookstore.dto.category.CategoryResponseDto;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    List<CategoryResponseDto> getAll(Pageable pageable);

    CategoryResponseDto getById(Long id);

    CategoryResponseDto save(CategoryRequestDto requestDto);

    CategoryResponseDto update(Long id, CategoryRequestDto requestDto);

    void deleteById(Long id);
}
