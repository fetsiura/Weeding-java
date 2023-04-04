package ua.weeding.core.accessory;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.PropertySource;
import org.springframework.stereotype.Service;
import ua.weeding.core.picture.PictureService;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccessoryService {

    private final AccessoryRepository accessoryRepository;
    private final PictureService pictureService;

    public void save(Accessory accessory){

        accessoryRepository.save(accessory);
        log.info("accessory with id {} created",accessory.getId());

    }

    public void delete(String id){
        List<String> pictures = accessoryRepository.findById(id).get()
                .getPictures();
        if(pictures!=null){
            pictures.forEach(pictureService::delete);
        }
        accessoryRepository.deleteById(id);
        log.info("Accessory with id {} deleted",id);
    }

    public void update(Accessory accessory) {
        accessoryRepository.save(accessory);
        log.info("Updated accessory {}",accessory);
    }

    public List<Accessory> findAll(){
        return  accessoryRepository.findAll().stream().sorted( (accessory1, accessory2) -> accessory1.getName().compareTo(accessory2.getName())).collect(Collectors.toList());
    }

    public List<Accessory> discount(){
        return accessoryRepository.findByIfDiscountTrue();
    }

    public Optional<Accessory> findById(String id) {
        return accessoryRepository.findById(id);
    }

    public long count() {
        return accessoryRepository.count();
    }

    public String сonvertFilterFromEngToUa(String filter) {

        if (filter.equals("boots")) {
            filter = "Туфлі";
        }
        return filter;
    }
}
