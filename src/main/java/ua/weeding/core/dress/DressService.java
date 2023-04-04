package ua.weeding.core.dress;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.weeding.core.picture.PictureService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class DressService {
    private final PictureService pictureService;
    private final DressRepository dressRepository;

    public void save(Dress dress) {
        dressRepository.save(dress);
        log.info("Dress with id {} created", dress.getId());

    }

    public void update(Dress dress) {
        dressRepository.save(dress);
        log.info("Updated dress {}", dress);
    }

    public void delete(String id) {
        List<String> pictures = dressRepository.findById(id).get()
                .getPictures();
        if (pictures != null) {
            pictures.forEach(pictureService::delete);
        }
        dressRepository.deleteById(id);
        log.info("Dress with id {} deleted", id);
    }

    public List<Dress> findAll() {
        return dressRepository.findAll().stream().sorted((dress1, dress2) -> dress1.getName().compareTo(dress2.getName())).collect(Collectors.toList());
    }

    public Optional<Dress> findById(String id) {
        log.info("Dress with id {} found", id);
        return dressRepository.findById(id);
    }

    public List<Dress> bestsellers() {
        return dressRepository.findByIfBestsellersTrue();
    }

    public List<Dress> priceDESC() {
        return dressRepository.findAll().stream().sorted(Comparator.comparingInt(Dress::getPrice).reversed()).collect(Collectors.toList());
    }

    public List<Dress> priceASC() {
        return dressRepository.findAll().stream().sorted(Comparator.comparingInt(Dress::getPrice)).collect(Collectors.toList());

    }

    public List<Dress> discount() {
        return dressRepository.findByIfDiscountTrue();
    }

    public List<Dress> novelties() {
        return dressRepository.findByIfNewTrue();
    }

    public List<Dress> dressesList(String filter) {
        List<Dress> dresses = new ArrayList<>();
        if (filter.isEmpty()) {
            dresses = findAll();
        }
        if (filter.equals("priceDESC")) {
            dresses = priceDESC();
        }
        if (filter.equals("priceASC")) {
            dresses = priceASC();
        }
        if (filter.equals("bestseller")) {
            dresses = bestsellers();
        }
        if (filter.equals("discount")) {
            dresses = discount();
        }
        if (filter.equals("newGood")) {
            dresses = novelties();
        }
        return dresses;
    }

    public long count() {
        return dressRepository.count();
    }

    public String сonvertFilterFromEngToUa(String filter) {

        if (filter.equals("priceDESC")) {
            filter = "Ціна за спаданням";
        } else if (filter.equals("priceASC")) {
            filter = "Ціна за зростанням";
        } else if (filter.equals("bestseller")) {
            filter = "Бестселлери";
        } else if (filter.equals("discount")) {
            filter = "Розпродаж";
        } else if (filter.equals("newGood")) {
            filter = "Новинки";
        }
        return filter;
    }
}
