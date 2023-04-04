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
import ua.weeding.core.accessory.Accessory;
import ua.weeding.core.accessory.AccessoryService;
import ua.weeding.core.dress.Dress;
import ua.weeding.core.picture.PictureService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin")
@Secured("ROLE_ADMIN")
public class AccessoryController {
    private final AccessoryService accessoryService;
    private final PictureService pictureService;

    @GetMapping("/accessory")
    public String adminAccessories(Model model,
                                   @AuthenticationPrincipal UserDetails customUser,
                                   @Param("filter") Optional<String> filter){
        if (customUser != null) {
            model.addAttribute("admin", "admin");
        }
        model.addAttribute("accessory", new Accessory());
        model.addAttribute("accessories", accessoryService.findAll());
        return "admin/adminAccessory";
    }
    @PostMapping("/add-accessory")
    public String addAccessory(Model model,
                               Optional<Accessory> accessory,
                               @RequestParam("file") Optional<MultipartFile[]> file) throws IOException {
        if (file.isPresent()) {
            List<String> pictures = pictureService.savingPictures(file.get());
            accessory.get().setPictures(pictures);
            accessoryService.save(accessory.get());
            return "redirect:/admin/accessory";
        }
        return "admin/adminAccessory";
    }
    @PostMapping("/deleteaccessory")
    public String deleteDress(@RequestParam String id) {

        Optional<Accessory> accessory = accessoryService.findById(id);
        if (accessory.isPresent() && accessory.get().getPictures() != null) {
            accessory.get().getPictures().forEach(pictureService::delete);
        }
        accessoryService.delete(id);
        return "redirect:/admin/accessory";
    }

    @GetMapping("/editAccessory")
    public String editAccessory(@RequestParam String id,
                                Model model) {
        Optional<Accessory> byId = accessoryService.findById(id);

        byId.ifPresent(accessory -> model.addAttribute("accessory", accessory));

        return "admin/editAccessory";
    }

    @PostMapping("/updateAccessory")
    public String updateAccessory(Accessory accessory) {
        accessoryService.update(accessory);
        return "redirect:/admin/editAccessory?id="+accessory.getId();

    }
}
