package guru.sfg.msscbeerservice.repositories;

import guru.sfg.msscbeerservice.domain.Beer;
import guru.sfg.brewery.model.BeerStyleEmum;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeerRepository extends JpaRepository<Beer, UUID> {

  Page<Beer> findAllByBeerNameAndBeerStyle(String beerName, BeerStyleEmum beerStyle, Pageable pageable);

  Page<Beer> findAllByBeerName(String beerName, Pageable pageable);

  Page<Beer> findAllByBeerStyle(BeerStyleEmum beerStyle, Pageable pageable);

  Beer findByUpc(String upc);
}
