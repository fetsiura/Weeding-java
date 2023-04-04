package ua.weeding.core.picture;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ua.weeding.core.dress.Dress;

import java.util.List;
import java.util.Optional;

@Repository
public interface PictureRepository extends MongoRepository<Picture,String> {
    Optional<Picture> findByPlaceOfUse(String place);
    List<Picture> findByPlaceOfUseAndFilenameIsNotNull(String place);
}
