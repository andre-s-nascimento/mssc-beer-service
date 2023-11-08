package guru.springframework.msscbeerservice.services;

import guru.springframework.msscbeerservice.web.model.BeerDto;
import guru.springframework.msscbeerservice.web.model.BeerPagedList;
import guru.springframework.msscbeerservice.web.model.BeerStyleEmum;
import java.util.UUID;
import org.springframework.data.domain.PageRequest;

public interface BeerService {

  BeerDto getById(UUID beerId, Boolean showInventoryOnHand);

  BeerDto saveNewBeer(BeerDto beerDto);

  BeerDto updateBeer(UUID beerId, BeerDto beerDto);

  BeerPagedList listBeers(
      String beerName,
      BeerStyleEmum beerStyle,
      Boolean showInventoryOnHand,
      PageRequest pageRequest);
}
