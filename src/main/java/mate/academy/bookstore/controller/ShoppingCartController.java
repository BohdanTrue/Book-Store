package mate.academy.bookstore.controller;

import jakarta.validation.Valid;
import mate.academy.bookstore.dto.category.CategoryRequestDto;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class ShoppingCartController {

    @PostMapping
    public ShoppingCartResponseDto createShoppingCart(ShoppingCartRequestDto requestDto) {
        return null;
    }

    @GetMapping("/{id}")
    public ShoppingCartResponseDto getShoppingCartById(@PathVariable Long id) {
        return null;
    }

    @GetMapping
    public List<ShoppingCartResponseDto> getShoppingCartById(Pageable pageable) {
        return null;
    }

    @PutMapping("/{id}")
    public ShoppingCartResponseDto updateShoppingCart(
            @PathVariable Long id,
            @RequestBody @Valid ShoppingCartRequestDto requestDto) {
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteShoppingCartById(@PathVariable Long id) {
        return;
    }

}
