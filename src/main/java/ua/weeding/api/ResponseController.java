package ua.weeding.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.weeding.core.response.ResponseService;

import java.util.Optional;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin")
@Secured("ROLE_ADMIN")
public class ResponseController {
    private final ResponseService responseService;

    @GetMapping("/response")
    public String response(@AuthenticationPrincipal UserDetails customUser,
                           Model model){

        model.addAttribute("responses",responseService.findAll());
        return "admin/adminResponses";
    }

    @PostMapping("/responseDelete")
    public String responseDelete(@RequestParam Optional<String> id){

        responseService.delete(id.get());
        return "redirect:/admin/response";
    }
}
