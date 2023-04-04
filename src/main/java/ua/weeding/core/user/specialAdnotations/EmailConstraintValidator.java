//package ua.weeding.user.specialAdnotations;
//
//import javax.validation.ConstraintValidator;
//import javax.validation.ConstraintValidatorContext;
//
//public class EmailConstraintValidator implements ConstraintValidator<EmailUserValid, String> {
//
//
//    @Override
//    public void initialize( EmailUserValid email) {
//        //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    @Override
//    public boolean isValid(String emailField, ConstraintValidatorContext cxt) {
//        if(emailField == null) {
//            return false;
//        }
//        return emailField.matches("[_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.([a-zA-Z]{2,}){1}");
//    }
//}
