package ua.weeding.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.weeding.core.accessory.AccessoryService;
import ua.weeding.core.dress.DressService;
import ua.weeding.core.picture.Picture;
import ua.weeding.core.picture.PictureService;
import ua.weeding.core.response.ResponseService;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin")
@Secured("ROLE_ADMIN")
public class AdminController {
    private final DressService dressService;
    private final AccessoryService accessoryService;
    private final PictureService pictureService;
    private final ResponseService responseService;

    @GetMapping
    public String adminDashboard(@AuthenticationPrincipal UserDetails customUser,
                                 Model model) {
        if (customUser != null) {
            model.addAttribute("admin", "admin");
        }
        model.addAttribute("dressQuantity",dressService.count());
        model.addAttribute("accessoryQuantity",accessoryService.count());
        return "admin/admin";
    }


    @GetMapping("/customizePictures")
    public String customizePictures(@AuthenticationPrincipal UserDetails customUser,
                                    Model model){
        if (customUser != null) {
            model.addAttribute("admin", "admin");
        }
        pictureService.findByPlaceOfUse("mainDiscount").ifPresent(picture -> model.addAttribute("mainDiscount", picture));
        pictureService.findByPlaceOfUse("weedingDress").ifPresent(picture -> model.addAttribute("weedingDress", picture));
        pictureService.findByPlaceOfUse("nightDress").ifPresent(picture -> model.addAttribute("nightDress", picture));
        pictureService.findByPlaceOfUse("mostPopular").ifPresent(picture -> model.addAttribute("mostPopular", picture));
        pictureService.findByPlaceOfUse("accessory").ifPresent(picture -> model.addAttribute("accessory", picture));

        return "admin/customizePictures";
    }

    @GetMapping("/gallery")
    public String gallery(@AuthenticationPrincipal UserDetails customUser,
                          Model model){
        List<Picture> gallery = pictureService.findGallery();
        if(gallery.size()>0){
            model.addAttribute("gallery",gallery);

        }
        return "admin/adminGallery";
    }




}
