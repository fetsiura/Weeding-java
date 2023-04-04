package ua.weeding.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ua.weeding.core.accessory.Accessory;
import ua.weeding.core.accessory.AccessoryService;
import ua.weeding.core.dress.Dress;
import ua.weeding.core.dress.DressService;
import ua.weeding.core.picture.PictureService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin")
@Secured("ROLE_ADMIN")
public class PictureController {
    private final DressService dressService;
    private final PictureService pictureService;
    private final AccessoryService accessoryService;

    @PostMapping("/addPicture")
    public String addPicture(@RequestParam Optional<String> placeOfUse,
                                      @RequestParam("file") Optional<MultipartFile> file,
                             @RequestParam("files") Optional<MultipartFile[]> files) throws IOException {
        if(file.isPresent()){
            pictureService.saveOnePicture(file.get(),placeOfUse.get());
            return "redirect:/admin/customizePictures";

        }
        if (files.isPresent()){
            pictureService.savingPicturesForGallery(files.get(),placeOfUse.get());
            return "redirect:/admin/gallery";

        }
        return "";
    }

    @PostMapping("/deletePicture")
    public String deletePicture(@RequestParam Optional<String> id,
                                @RequestParam Optional<String> gallery){
        pictureService.delete(id.get());
        if(gallery.isPresent()){
            return "redirect:/admin/gallery";
        }
        return "redirect:/admin/customizePictures";
    }

    @PostMapping("/updateDressPictures")
    public String updateDressPictures(@RequestParam Optional<String> id,
                                      @RequestParam("file") Optional<MultipartFile[]> file) throws IOException {
        Optional<Dress> dress = dressService.findById(id.get());
        List<String> pictures = dress.get().getPictures();
        List<String> pictureAfterUpdating = pictureService.updatingPictures(file.get(), pictures);
        dressService.update(dress.get());
        return "redirect:/admin/editDress?id=" + id.get();
    }

    @PostMapping("/deletePictureFromDress")
    public String deletePictureFromDress(@RequestParam Optional<String> pictureName,
                                         @RequestParam Optional<String> id){
        Optional<Dress> dress = dressService.findById(id.get());
        List<String> pictures = dress.get().getPictures();
        pictures.remove(pictureName.get());
        log.info("Picture {} deleted from dress {}", pictureName.get(), id.get());
        pictureService.delete(pictureName.get());
        dressService.update(dress.get());
        return "redirect:/admin/editDress?id=" + id.get();
    }
    @PostMapping("/updateAccessoryPictures")
    public String updateAccessoryPictures(@RequestParam Optional<String> id,
                                          @RequestParam("file") Optional<MultipartFile[]> file) throws IOException {
        Optional<Accessory> accessory = accessoryService.findById(id.get());
        List<String> pictures = accessory.get().getPictures();
        List<String> pictureAfterUpdating = pictureService.updatingPictures(file.get(), pictures);
        accessoryService.update(accessory.get());
        return "redirect:/admin/editAccessory?id=" + id.get();
    }

    @PostMapping("/deletePictureFromAccessory")
    public String deletePictureFromAccessory(@RequestParam Optional<String> pictureName,
                                             @RequestParam Optional<String> id){
        Optional<Accessory> accessory = accessoryService.findById(id.get());
        List<String> pictures = accessory.get().getPictures();
        pictures.remove(pictureName.get());
        log.info("Picture {} deleted from ccessory {}", pictureName.get(), id.get());
        pictureService.delete(pictureName.get());
        accessoryService.update(accessory.get());
        return "redirect:/admin/editAccessory?id=" + id.get();
    }
}
