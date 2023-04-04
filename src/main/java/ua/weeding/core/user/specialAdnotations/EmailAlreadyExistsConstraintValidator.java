//package ua.weeding.user.specialAdnotations;
//
//import lombok.RequiredArgsConstructor;
//import ua.weeding.core.user.UserService;
//
//import javax.validation.ConstraintValidator;
//import javax.validation.ConstraintValidatorContext;
//
//@RequiredArgsConstructor
//public class EmailAlreadyExistsConstraintValidator implements ConstraintValidator<EmailAlreadyExists, String> {
//
//    private final UserService userService;
//
//    @Override
//    public boolean isValid(String emailField, ConstraintValidatorContext cxt) {
//        if(emailField == null) {
//            return false;
//        }
//
//        if(userService.findByEmail(emailField).isPresent()){
//            return false;
//        }
//        return true;
//    }
//}
