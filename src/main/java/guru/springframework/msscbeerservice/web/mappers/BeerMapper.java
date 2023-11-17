package guru.springframework.msscbeerservice.web.mappers;

import guru.springframework.msscbeerservice.domain.Beer;
import guru.sfg.brewery.model.BeerDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(uses = {DateMapper.class})
@DecoratedWith(BeerMapperDecorator.class)
@Component
public interface BeerMapper {
  BeerDto beerToBeerDto(Beer beer);

  BeerDto beerToBeerDtoWithInventory(Beer beer);

  Beer beerDtoToBeer(BeerDto beerDto);
}
