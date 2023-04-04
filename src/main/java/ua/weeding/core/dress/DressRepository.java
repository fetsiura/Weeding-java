package ua.weeding.core.dress;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DressRepository extends MongoRepository<Dress,String> {

    List<Dress> findByIfBestsellersTrue();
    List<Dress> findByIfDiscountTrue();

    List<Dress> findByIfNewTrue();
}
