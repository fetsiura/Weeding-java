package ua.weeding.core.token;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.weeding.core.user.User;
import ua.weeding.core.user.UserService;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final UserService userService;

    public void save(ConfirmationToken token){
        confirmationTokenRepository.save(token);
        log.info("Token saved {}",token);
    }
    public void delete(Long token){
        confirmationTokenRepository.deleteById(token);
        log.info("Token {} deleted",token);
    }

    public ConfirmationToken findByConfirmationToken(String token){
        return confirmationTokenRepository.findByConfirmationToken(token);
    }

    //token methods
    public boolean ifTokenNotExist(String token){
        ConfirmationToken byConfirmationToken = confirmationTokenRepository.findByConfirmationToken(token);
        if (byConfirmationToken == null) {
            return true;
        }
        return false;
    }

    public boolean ifTokenUserIdEqualsCorrectUser(String id, String token){
        ConfirmationToken byConfirmationToken = confirmationTokenRepository.findByConfirmationToken(token);
        Optional<User> byId = userService.findById(id);
        if(byConfirmationToken.getUserId() != byId.get().getId()){
            return false;
        }
        return true;
    }

    public boolean ifTokenExpired(String token) {
        ConfirmationToken byConfirmationToken = confirmationTokenRepository.findByConfirmationToken(token);
        if(byConfirmationToken.getExpireTime().isAfter(LocalDateTime.now())){
            return false;
        }
        return true;
    }
}
