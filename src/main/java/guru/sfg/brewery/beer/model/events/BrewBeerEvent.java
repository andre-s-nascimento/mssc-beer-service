package guru.sfg.brewery.beer.model.events;

import guru.sfg.brewery.beer.model.BeerDto;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BrewBeerEvent extends BeerEvent {
  public BrewBeerEvent(BeerDto beerDto) {
    super(beerDto);
  }
}
