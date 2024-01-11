package mate.academy.bookstore.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import mate.academy.bookstore.dto.category.CategoryResponseDto;
import mate.academy.bookstore.mapper.CategoryMapper;
import mate.academy.bookstore.model.Category;
import mate.academy.bookstore.repository.category.CategoryRepository;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {
    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryMapper categoryMapper;
    private Category category;
    private CategoryResponseDto categoryResponseDto;

    @BeforeEach
    void setUp() {
        category = new Category()
                .setId(1L)
                .setName("Technical")
                .setDescription("Technical book");

        categoryResponseDto = new CategoryResponseDto(
                category.getId(),
                category.getName(),
                category.getDescription());
    }

    @Test
    void getCategory_WithValidId_ReturnOk() {
        CategoryResponseDto expected = categoryResponseDto;

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(categoryMapper.toDto(category)).thenReturn(expected);

        CategoryResponseDto actual = categoryService.getById(1L);

        assertNotNull(actual);
        EqualsBuilder.reflectionEquals(expected, actual);
    }
}
