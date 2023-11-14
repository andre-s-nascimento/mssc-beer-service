package guru.springframework.msscbeerservice.events;

import guru.springframework.msscbeerservice.web.model.BeerDto;
import java.io.Serial;
import java.io.Serializable;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Builder
public class BeerEvent implements Serializable {

  @Serial private static final long serialVersionUID = -1321L;

  private final BeerDto beerDto;
}
