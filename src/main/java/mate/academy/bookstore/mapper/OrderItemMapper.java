package mate.academy.bookstore.mapper;

import mate.academy.bookstore.config.MapperConfig;
import mate.academy.bookstore.dto.order.OrderItemResponseDto;
import mate.academy.bookstore.model.OrderItem;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(config = MapperConfig.class)
public interface OrderItemMapper {
    @Named("toDto")
    @Mapping(target = "bookId", source = "book.id")
    OrderItemResponseDto toDto(OrderItem orderItem);

    @IterableMapping(qualifiedByName = "toDto")
    List<OrderItemResponseDto> toDtos(List<OrderItem> orderItems);
}
