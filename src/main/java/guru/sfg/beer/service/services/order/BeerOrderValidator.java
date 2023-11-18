package guru.sfg.beer.service.services.order;

import guru.sfg.beer.service.repositories.BeerRepository;
import guru.sfg.brewery.model.BeerOrderDto;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class BeerOrderValidator {

  private final BeerRepository beerRepository;

  public Boolean validateOrder(BeerOrderDto beerOrder) {
    AtomicInteger beersNotFound = new AtomicInteger();

    beerOrder
        .getBeerOrderLines()
        .forEach(
            orderLine -> {
              if (beerRepository.findByUpc(orderLine.getUpc()) == null) {
                beersNotFound.incrementAndGet();
              }
            });

    return beersNotFound.get() == 0;
  }
}
