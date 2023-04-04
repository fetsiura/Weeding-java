package ua.weeding.core.picture;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PictureService {
    @Value("${upload.path}")
    private String uploadPath;
    private final PictureRepository pictureRepository;

    public Optional<Picture> findByPlaceOfUse(String place){
        return pictureRepository.findByPlaceOfUse(place);
    }
    public List<Picture> findGallery(){
        return pictureRepository.findByPlaceOfUseAndFilenameIsNotNull("gallery");
    }
    public void save(String filename){
        Picture picture = new Picture();
        picture.setFilename(filename);
        pictureRepository.save(picture);
        log.info("Picture with name {} saved to database",filename);
    }

    public void update(Picture picture) {
        pictureRepository.save(picture);
        log.info("Updated picture {}",picture);
    }

    public void delete(String id) {
        pictureRepository.deleteById(id);
        Path path = FileSystems.getDefault().getPath(uploadPath+"/"+id);
        try {
            Files.delete(path);
            log.info("Picture with id {} deleted",id);
        } catch (IOException ignored) {
        }
    }

    public void saveOnePicture(MultipartFile file,String placeOfUse) throws  IOException {
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        String uuidFile = UUID.randomUUID().toString();
        String resultFileName = uuidFile + file.getOriginalFilename().replaceAll(" ", "");
        file.transferTo(new File(uploadPath + "/" + resultFileName));
        Picture picture = new Picture();
        picture.setFilename(resultFileName);
        picture.setPlaceOfUse(placeOfUse);
        pictureRepository.save(picture);
        log.info("Picture for place of use {} saved",placeOfUse);
    }
    public void savingPicturesForGallery(MultipartFile[] file, String placeOfUse) throws IOException {
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        for (MultipartFile multipartFile : file) {
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + multipartFile.getOriginalFilename().replaceAll(" ", "");
            multipartFile.transferTo(new File(uploadPath + "/" + resultFileName));
            Picture picture = new Picture();
            picture.setFilename(resultFileName);
            picture.setPlaceOfUse(placeOfUse);
            pictureRepository.save(picture);
        }
    }
    public List<String> savingPictures(MultipartFile[] file) throws IOException {
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        List<String> pictures = new ArrayList<>();
        for (MultipartFile multipartFile : file) {
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + multipartFile.getOriginalFilename().replaceAll(" ", "");
            multipartFile.transferTo(new File(uploadPath + "/" + resultFileName));
            save(resultFileName);
            pictures.add(resultFileName);
        }
        return pictures;
    }
    public List<String> updatingPictures(MultipartFile[] file,List<String> pictures) throws IOException {
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        for (MultipartFile multipartFile : file) {
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + multipartFile.getOriginalFilename().replaceAll(" ", "");
            multipartFile.transferTo(new File(uploadPath + "/" + resultFileName));
            save(resultFileName);
            pictures.add(resultFileName);
        }
        return pictures;
    }

}
