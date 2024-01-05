package mate.academy.bookstore.mapper;

import mate.academy.bookstore.config.MapperConfig;
import mate.academy.bookstore.dto.order.OrderResponseDto;
import mate.academy.bookstore.model.Order;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(config = MapperConfig.class, uses = OrderItemMapper.class)
public interface OrderMapper {
    @Named("toDto")
    @Mapping(target = "userId", source = "user.id")
    OrderResponseDto toDto(Order order);

    @IterableMapping(qualifiedByName = "toDto")
    List<OrderResponseDto> toDtos(List<Order> orders);
}
