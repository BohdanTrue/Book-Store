package mate.academy.bookstore.repository.book;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mate.academy.bookstore.dto.BookSearchParametersDto;
import mate.academy.bookstore.model.Book;
import mate.academy.bookstore.repository.SpecificationBuilder;
import mate.academy.bookstore.repository.SpecificationProviderManager;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BookSpecificationBuilder implements SpecificationBuilder<Book> {
    private final SpecificationProviderManager<Book> bookSpecificationProviderManager;

    @Override
    public Specification<Book> build(BookSearchParametersDto searchParameters) {
        Specification<Book> spec = Specification.where(null);
        return Optional.of(spec)
                .map(specification -> specification
                        .and(addAuthorSpecification(specification, searchParameters)))
                .map(specification -> specification
                        .and(addTitleSpecification(specification, searchParameters)))
                .orElse(Specification.where(null));
    }

    private Specification<Book> addAuthorSpecification(
            Specification<Book> spec,
            BookSearchParametersDto parameters
    ) {
        return Optional.ofNullable(parameters.authors())
                .filter(authors -> authors.length > 0)
                .map(authors -> spec.and(bookSpecificationProviderManager
                        .getSpecificationProvider("author").getSpecification(authors)))
                .orElse(spec);
    }

    private Specification<Book> addTitleSpecification(
            Specification<Book> spec,
            BookSearchParametersDto parameters
    ) {
        return Optional.ofNullable(parameters.titles())
                .filter(titles -> titles.length > 0)
                .map(titles -> spec.and(bookSpecificationProviderManager
                        .getSpecificationProvider("author").getSpecification(titles)))
                .orElse(spec);
    }
}
