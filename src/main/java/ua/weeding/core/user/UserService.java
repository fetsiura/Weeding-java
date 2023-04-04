package ua.weeding.core.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ua.weeding.core.user.role.Role;
import ua.weeding.core.user.role.RoleRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }
    public Optional<User> findById(String id){
        return userRepository.findById(id);
    }

    public void save(UserDto userDto){
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEnabled(true);
        if(roleRepository.findByName("ROLE_USER").isEmpty()){
            createRole();
        }
        Optional<Role> userRole = roleRepository.findByName("ROLE_USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole.get())));
        userRepository.save(user);
        log.info("User with email {} created",user.getEmail());
    }

    public boolean checkEmail(String email){
        return email.matches("[_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.([a-zA-Z]{2,}){1}");
    }

    public boolean ifLengthGrater(String password, String confirm){
        if(password.length()<8 || confirm.length()<8){
            return false;
        }
        return true;
    }

    public boolean checkConfirming(String password,String confirm){
        if(!password.equals(confirm)){
            return false;
        }
        return true;
    }

    public void createRole(){
        Role role = new Role();
        role.setName("ROLE_USER");
        roleRepository.save(role);
        log.info("Role USER created");
        Role roleAdmin = new Role();
        roleAdmin.setName("ROLE_ADMIN");
        roleRepository.save(roleAdmin);
        log.info("Role ADMIN created");
    }

    public boolean emailExists(String email) {
        Optional<User> byEmail = userRepository.findByEmail(email);
        if(byEmail.isPresent()){
            return true;
        }
        return false;
    }
}
