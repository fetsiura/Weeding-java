package ua.weeding.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
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
public class DressController {
    private final DressService dressService;
    private final PictureService pictureService;

    @GetMapping("/dresses")
    public String adminDresses(Model model,
                               @AuthenticationPrincipal UserDetails customUser,
                               @Param("filter") Optional<String> filter){
        if (customUser != null) {
            model.addAttribute("admin", "admin");
        }
        model.addAttribute("dress", new Dress());
        List<Dress> dresses = dressService.dressesList(filter.orElse(""));
        model.addAttribute("dresses", dresses);
        return "admin/adminDresses";
    }

    @PostMapping("/add-dress")
    public String addDress(Model model,
                           Optional<Dress> dress,
                           @RequestParam("file") Optional<MultipartFile[]> file) throws IOException {
        if (file.isPresent()) {
            List<String> pictures = pictureService.savingPictures(file.get());
            dress.get().setPictures(pictures);
            dressService.save(dress.get());
            return "redirect:/admin/dresses";
        }
        return "admin/adminDresses";
    }


    @PostMapping("/deletedress")
    public String deleteDress(@RequestParam String id) {

        Optional<Dress> dress = dressService.findById(id);
        if (dress.get().getPictures() != null) {
            dress.get().getPictures().forEach(pictureService::delete);
        }
        dressService.delete(id);
        return "redirect:/admin/dresses";
    }


    @GetMapping("/editDress")
    public String editDress(@RequestParam String id,
                            Model model) {
        Optional<Dress> byId = dressService.findById(id);
        model.addAttribute("dress", byId.get());
        return "admin/editDress";
    }

    @PostMapping("/updateDress")
    public String updateDress(Dress dress) {
        dressService.update(dress);
        return "redirect:/admin/editDress?id="+dress.getId();
    }

}
