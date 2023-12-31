package guru.sfg.beer.service.services.brewing;

import guru.sfg.beer.service.config.JmsConfig;
import guru.sfg.beer.service.domain.Beer;
import guru.sfg.beer.service.repositories.BeerRepository;
import guru.sfg.beer.service.services.inventory.BeerInventoryService;
import guru.sfg.beer.service.web.mappers.BeerMapper;
import guru.sfg.brewery.model.events.BrewBeerEvent;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BrewingService {

  private final BeerRepository beerRepository;
  private final BeerInventoryService beerInventoryService;
  private final JmsTemplate jmsTemplate;
  private final BeerMapper beerMapper;

  @Scheduled(fixedRate = 5000) // every 5s
  public void checkForLowInventory() {
    List<Beer> beers = beerRepository.findAll();

    beers.forEach(
        beer -> {
          Integer invQOH = beerInventoryService.getOnhandInventory(beer.getId());

          log.debug("Min OnHand is: " + beer.getMinOnHand());
          log.debug("Inventory is: " + invQOH);

          if (beer.getMinOnHand() >= invQOH) {
            jmsTemplate.convertAndSend(
                JmsConfig.BREWING_REQUEST_QUEUE, new BrewBeerEvent(beerMapper.beerToBeerDto(beer)));
          }
        });
  }
}
