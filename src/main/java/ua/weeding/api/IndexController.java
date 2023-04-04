package ua.weeding.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.weeding.core.accessory.Accessory;
import ua.weeding.core.accessory.AccessoryService;
import ua.weeding.core.dress.Dress;
import ua.weeding.core.dress.DressService;
import ua.weeding.core.picture.PictureService;
import ua.weeding.core.response.ResponseService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@Slf4j
public class IndexController {


    private final DressService dressService;
    private final ResponseService responseService;

    private final AccessoryService accessoryService;
    private final PictureService pictureService;
    @GetMapping("/")
    public String home(@AuthenticationPrincipal UserDetails customUser,
                       Model model){
        if(customUser!=null){
            model.addAttribute("admin","admin");
        }
        pictureService.findByPlaceOfUse("mainDiscount").ifPresent(picture -> model.addAttribute("mainDiscount", picture));
        pictureService.findByPlaceOfUse("weedingDress").ifPresent(picture -> model.addAttribute("weedingDress", picture));
        pictureService.findByPlaceOfUse("nightDress").ifPresent(picture -> model.addAttribute("nightDress", picture));
        pictureService.findByPlaceOfUse("mostPopular").ifPresent(picture -> model.addAttribute("mostPopular", picture));
        pictureService.findByPlaceOfUse("accessory").ifPresent(picture -> model.addAttribute("accessory", picture));
        model.addAttribute("novelties",dressService.novelties());
        model.addAttribute("gallery",pictureService.findGallery().stream().limit(6).collect(Collectors.toList()));
        return "index";
    }

    @GetMapping("/some")
    public String some (){
        return "some";
    }

    @GetMapping("/sykni")
    public String dresses(@AuthenticationPrincipal UserDetails customUser,
                          Model model,
                          @Param("filter") Optional<String> filter){
        if(customUser!=null){
            model.addAttribute("admin","admin");
        }
        List<Dress> dresses = dressService.dressesList(filter.orElse(""));
        if(filter.isPresent()){
            model.addAttribute("filterText",dressService.сonvertFilterFromEngToUa(filter.get()));
            model.addAttribute("filterLink",filter.get());

        }
        model.addAttribute("dresses",dresses);
        model.addAttribute("bestsellers",dressService.bestsellers());
        model.addAttribute("accessories", accessoryService.discount());
        return "goods/dresses";
    }

    @GetMapping("/sykni/{id}")
    public String dress(@AuthenticationPrincipal UserDetails customUser,
                        Model model,
                        @PathVariable("id") Optional<String> id){
        if(customUser!=null){
            model.addAttribute("admin","admin");
        }
        Optional<Dress> dress = dressService.findById(id.orElseThrow());
        if(dress.isEmpty()){
            model.addAttribute("message","Перепрошуємо, сукні не знайдено");
        } else {
            model.addAttribute("dress",dress.get());
        }
        model.addAttribute("bestsellers",dressService.bestsellers());

        return "goods/singleDress";
    }

    @GetMapping("/accessory")
    public String accessories(@AuthenticationPrincipal UserDetails customUser,
                              Model model,
                              @Param("filter") Optional<String> filter){
        if(customUser!=null){
            model.addAttribute("admin","admin");
        }
        if(filter.isPresent()){
            model.addAttribute("filterText",accessoryService.сonvertFilterFromEngToUa(filter.get()));
            model.addAttribute("filterLink",filter.get());
        }
//        List<Accessory> accessories = accessoryService.dressesList(filter.orElse(""));
        model.addAttribute("accessories",accessoryService.findAll());
        model.addAttribute("bestsellers",dressService.bestsellers());
        model.addAttribute("accessoriesSpot", accessoryService.discount());
        return "goods/accessories";
    }

    @GetMapping("/accessory/{id}")
    public String accessory(@AuthenticationPrincipal UserDetails customUser,
                        Model model,
                        @PathVariable("id") Optional<String> id){
        if(customUser!=null){
            model.addAttribute("admin","admin");
        }
        Optional<Accessory> accessory = accessoryService.findById(id.orElseThrow());
        if(accessory.isEmpty()){
            model.addAttribute("message","Перепрошуємо, аксесуар не знайдено");
        } else {
            model.addAttribute("accessory",accessory.get());
        }
        model.addAttribute("bestsellers",dressService.bestsellers());

        return "goods/singleAccessory";
    }

    @GetMapping("/contact")
    public String contact(Model model,
                          @AuthenticationPrincipal UserDetails customUser){

        if(customUser!=null){
            model.addAttribute("admin","admin");
        }
        return "contact";
    }
    @GetMapping("/gallery")
    public String galley(Model model,
                          @AuthenticationPrincipal UserDetails customUser){

        if(customUser!=null){
            model.addAttribute("admin","admin");
        }
        model.addAttribute("gallery",pictureService.findGallery());
        return "gallery";
    }

    @GetMapping("/response")
    public String response(Model model){
        model.addAttribute("responses",responseService.findAll());
        return "responses";
    }
    @GetMapping("/response/add")
    public String addResponse(@RequestParam Optional<String> name,
                              @RequestParam Optional<String> description,
                              @RequestParam Optional<String> rating,
                              Model model){
        if(name.isPresent() && description.isPresent() && rating.isPresent()) {
            responseService.add(name.get(), description.get(), rating.get());
        }
        return "redirect:/";
    }
}
