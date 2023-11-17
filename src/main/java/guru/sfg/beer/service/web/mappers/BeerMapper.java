package guru.sfg.beer.service.web.mappers;

import guru.sfg.beer.service.domain.Beer;
import guru.sfg.brewery.model.BeerDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(uses = {DateMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
@DecoratedWith(BeerMapperDecorator.class)
public interface BeerMapper {
  BeerDto beerToBeerDto(Beer beer);

  BeerDto beerToBeerDtoWithInventory(Beer beer);

  Beer beerDtoToBeer(BeerDto beerDto);
}
