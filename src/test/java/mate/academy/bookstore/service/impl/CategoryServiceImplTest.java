package mate.academy.bookstore.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;
import mate.academy.bookstore.dto.category.CategoryResponseDto;
import mate.academy.bookstore.mapper.CategoryMapper;
import mate.academy.bookstore.model.Category;
import mate.academy.bookstore.repository.category.CategoryRepository;
import org.apache.commons.lang3.builder.EqualsBuilder;
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

    @Test
    void getCategory_WithValidId_ReturnOk() {
        Long id = 1L;

        Category category = new Category()
                .setId(id)
                .setName("Technical")
                .setDescription("Technical book");

        CategoryResponseDto expected = new CategoryResponseDto(
                category.getId(),
                category.getName(),
                category.getDescription()
        );

        when(categoryRepository.findById(id)).thenReturn(Optional.of(category));
        when(categoryMapper.toDto(category)).thenReturn(expected);

        CategoryResponseDto actual = categoryService.getById(id);

        assertNotNull(actual);
        EqualsBuilder.reflectionEquals(expected, actual);
    }
}
