package ua.weeding.core.response;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.datetime.standard.DateTimeFormatterFactory;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.SimpleFormatter;

@Service
@Slf4j
@RequiredArgsConstructor
public class ResponseService {

    private final ResponseRepository responseRepository;

    public void add(String name, String description,String rating){

        Response response = new Response();
        response.setName(name);
        int ratingInt = 0;
        try {
            ratingInt = Integer.parseInt(rating);
        } catch (NumberFormatException e){
            ratingInt =5;
        }
        response.setDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        response.setRating(ratingInt);
        response.setDescription(description);
        responseRepository.save(response);
        log.info("Response {} saved",response);
    }

    public void update(Response response){

    }

    public void delete(String id){
        responseRepository.deleteById(id);
    }

    public List<Response> findAll(){
        return responseRepository.findAll();
    }

}
