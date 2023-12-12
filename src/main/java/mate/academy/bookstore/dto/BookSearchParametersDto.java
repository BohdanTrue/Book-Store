package mate.academy.bookstore.dto;

import java.math.BigDecimal;

public record BookSearchParametersDto(String[] titles, String[] authors) {
}
