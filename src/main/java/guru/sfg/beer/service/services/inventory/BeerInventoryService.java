package guru.sfg.beer.service.services.inventory;

import java.util.UUID;

public interface BeerInventoryService {

  Integer getOnhandInventory(UUID beerId);
}
