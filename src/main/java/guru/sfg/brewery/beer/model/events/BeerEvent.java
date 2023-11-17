package guru.sfg.brewery.beer.model.events;

import guru.sfg.brewery.beer.model.BeerDto;
import java.io.Serial;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class BeerEvent implements Serializable {

  @Serial private static final long serialVersionUID = -1321L;

  private BeerDto beerDto;

  public BeerEvent() {
    super();
  }
}
