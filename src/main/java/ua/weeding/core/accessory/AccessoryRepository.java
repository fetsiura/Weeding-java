package ua.weeding.core.accessory;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccessoryRepository extends MongoRepository<Accessory,String> {

    List<Accessory> findByIfDiscountTrue();
}
