package guru.springframework.msscbeerservice.web.mappers;

import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.services.inventory.BeerInventoryService;
import guru.sfg.brewery.beer.model.BeerDto;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BeerMapperDecorator implements BeerMapper {
  private BeerInventoryService beerInventoryService;
  private BeerMapper mapper;

  @Override
  public BeerDto beerToBeerDtoWithInventory(Beer beer) {
    BeerDto beerDto = mapper.beerToBeerDto(beer);
    beerDto.setQuantityOnHand(beerInventoryService.getOnhandInventory(beer.getId()));
    return beerDto;
  }

  @Autowired
  public void setBeerInventoryService(BeerInventoryService beerInventoryService) {
    this.beerInventoryService = beerInventoryService;
  }

  @Autowired
  public void setMapper(BeerMapper beerMapper) {
    this.mapper = beerMapper;
  }

  @Override
  public BeerDto beerToBeerDto(Beer beer) {
    return mapper.beerToBeerDto(beer);
  }

  @Override
  public Beer beerDtoToBeer(BeerDto beerDto) {
    return mapper.beerDtoToBeer(beerDto);
  }
}
