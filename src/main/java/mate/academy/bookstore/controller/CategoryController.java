package mate.academy.bookstore.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import mate.academy.bookstore.dto.category.CategoryRequestDto;
import mate.academy.bookstore.dto.category.CategoryResponseDto;
import mate.academy.bookstore.service.CategoryService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public CategoryResponseDto createCategory(CategoryRequestDto requestDto) {
        return categoryService.save(requestDto);
    }

    @GetMapping
    public List<CategoryResponseDto> getAll(Pageable pageable) {
        return categoryService.getAll(pageable);
    }

    @GetMapping("/{id}")
    public CategoryResponseDto getCategoryById(@PathVariable Long id) {
        return categoryService.getById(id);
    }

    @PostMapping
    public CategoryResponseDto updateCategory(Long id, CategoryRequestDto requestDto) {
        return categoryService.update(id, requestDto);
    }

    @DeleteMapping
    public void deleteCategory(Long id) {
        categoryService.deleteById(id);
    }
}
