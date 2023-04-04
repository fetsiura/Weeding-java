package ua.weeding.api;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.weeding.core.token.ConfirmationTokenService;
import ua.weeding.core.user.User;
import ua.weeding.core.user.UserService;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class LoginNRegistrController {
    private final UserService userService;
    private final ConfirmationTokenService tokenService;


    @GetMapping("/login")
    public String login(@AuthenticationPrincipal UserDetails customUser,
                        Model model) {
        return "user/login";
    }

    @GetMapping("/reset")
    public String repair(Model model,
                         @Param("email") Optional<String> email) {
        if (email.isPresent()) {
            Optional<User> existingUser = userService.findByEmail(email.orElseThrow());
            if (existingUser.isPresent()) {
//                ConfirmationToken confirmationToken = new ConfirmationToken(existingUser.get().getId());
//                tokenService.save(confirmationToken);
//                emailSenderService.sendSimpleEmail(existingUser.get().getEmail(), "Відновлення пароля", "Привіт для того щоб відновити Ваш пароль перейдіть за посиланням http://localhost:8080/changepassword?token=" + confirmationToken.getConfirmationToken());
//                model.addAttribute("restoreOk", "Посилання з відновленням було вислано на Вашу поштову скриньку.");
                return "redirect:/login";
            } else {
                model.addAttribute("errorAdmin", "Такий адміністратор не зареєстрований, ми проінформуємо адміністратора про не санкціоновану спробу входу.");
            }
        }
        return "user/login";
    }


    //    @GetMapping("/registration")
//    public String getRegistration(Model model) {
//        model.addAttribute("userDto", new UserDto());
//        return "user/registration";
//    }
//
//    @PostMapping("/registration")
//    public String postRegistration(Model model, @ModelAttribute("userDto") @Valid UserDto userDto,
//                                   BindingResult result) {
//        boolean emailConfirm = userService.checkEmail(userDto.getEmail());
//        if(!emailConfirm){
//            model.addAttribute("error","Не коректний емайл.");
//            return "user/registration";
//        }
//        boolean passwordLength = userService.ifLengthGrater(userDto.getPassword(),userDto.getConfirmPassword());
//        if(!passwordLength){
//            model.addAttribute("error","Пароль короткий (мін 8 символів).");
//            return "user/registration";
//        }
//        boolean passwordConfirm = userService.checkConfirming(userDto.getPassword(), userDto.getConfirmPassword());
//        if(!passwordConfirm){
//            model.addAttribute("error","Паролі не збігаються.");
//            return "user/registration";
//        }
//        boolean emailExists = userService.emailExists(userDto.getEmail());
//        if(emailExists){
//            model.addAttribute("error","Користувач з такою поштою вже існує.");
//            return "user/registration";
//        }
//
//        if (result.hasErrors()) {
//            return "user/registration";
//        }
//
//        userService.save(userDto);
//        return "redirect:/login";
//    }

}
