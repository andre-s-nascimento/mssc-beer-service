package guru.sfg.msscbeerservice.services;

import guru.sfg.msscbeerservice.domain.Beer;
import guru.sfg.msscbeerservice.repositories.BeerRepository;
import guru.sfg.msscbeerservice.web.controller.NotFoundException;
import guru.sfg.msscbeerservice.web.mappers.BeerMapper;
import guru.sfg.brewery.model.BeerDto;
import guru.sfg.brewery.model.BeerPagedList;
import guru.sfg.brewery.model.BeerStyleEmum;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
@Slf4j
public class BeerServiceImpl implements BeerService {

  private final BeerRepository beerRepository;
  private final BeerMapper beerMapper;

  @Cacheable(cacheNames = "beerCache", key = "#beerId", condition = "#showInventoryOnHand == false")
  @Override
  public BeerDto getById(UUID beerId, Boolean showInventoryOnHand) {
    log.debug("Passed here on: " + getClass().getName());
    if (showInventoryOnHand) {
      return beerMapper.beerToBeerDtoWithInventory(
          beerRepository.findById(beerId).orElseThrow(NotFoundException::new));
    } else {
      return beerMapper.beerToBeerDto(
          beerRepository.findById(beerId).orElseThrow(NotFoundException::new));
    }
  }

  @Override
  public BeerDto saveNewBeer(BeerDto beerDto) {
    return beerMapper.beerToBeerDto(beerRepository.save(beerMapper.beerDtoToBeer(beerDto)));
  }

  @Override
  public BeerDto updateBeer(UUID beerId, BeerDto beerDto) {
    Beer beer = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);

    beer.setBeerName(beerDto.getBeerName());
    beer.setBeerStyle(beerDto.getBeerStyle().name());
    beer.setPrice(beerDto.getPrice());
    beer.setUpc(beerDto.getUpc());
    beer.setLastModifiedDate(Timestamp.valueOf(LocalDateTime.now()));

    return beerMapper.beerToBeerDto(beerRepository.save(beer));
  }

  @Cacheable(cacheNames = "beerListCache", condition = "#showInventoryOnHand == false")
  @Override
  public BeerPagedList listBeers(
      String beerName,
      BeerStyleEmum beerStyle,
      Boolean showInventoryOnHand,
      PageRequest pageRequest) {
    BeerPagedList beerPagedList;
    Page<Beer> beerPage;

    log.debug("Passed here on: " + getClass().getName());

    if (!ObjectUtils.isEmpty(beerName) && !ObjectUtils.isEmpty(beerStyle)) {
      // search both
      beerPage = beerRepository.findAllByBeerNameAndBeerStyle(beerName, beerStyle, pageRequest);
    } else if (!ObjectUtils.isEmpty(beerName) && ObjectUtils.isEmpty(beerStyle)) {
      beerPage = beerRepository.findAllByBeerName(beerName, pageRequest);
    } else if (ObjectUtils.isEmpty(beerName) && !ObjectUtils.isEmpty(beerStyle)) {
      beerPage = beerRepository.findAllByBeerStyle(beerStyle, pageRequest);
    } else {
      beerPage = beerRepository.findAll(pageRequest);
    }

    if (showInventoryOnHand) {
      beerPagedList =
          new BeerPagedList(
              beerPage.getContent().stream().map(beerMapper::beerToBeerDtoWithInventory).toList(),
              PageRequest.of(
                  beerPage.getPageable().getPageNumber(), beerPage.getPageable().getPageSize()),
              beerPage.getTotalElements());
    } else {
      beerPagedList =
          new BeerPagedList(
              beerPage.getContent().stream().map(beerMapper::beerToBeerDto).toList(),
              PageRequest.of(
                  beerPage.getPageable().getPageNumber(), beerPage.getPageable().getPageSize()),
              beerPage.getTotalElements());
    }

    return beerPagedList;
  }

  @Cacheable(cacheNames = "beerUpcCache")
  @Override
  public BeerDto getByUpc(String upc) {
    return beerMapper.beerToBeerDto(beerRepository.findByUpc(upc));
  }
}
