package Rest.Services.rest.services.learning;

import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class UserController {
    private  List<User> userList;

    @PostConstruct
    public void createUsers(){
        userList =new ArrayList<>();
        userList.add(new User(1,"kiran","kiran@gmail.com"));
        userList.add(new User(2,"pooja","pooja@gmail.com"));
        userList.add(new User(3,"laxmi","laxmi@gmail.com"));
    }

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers(){
        return userList;
    }

    @GetMapping("/getUser/{id}")
    public User getUserById(@PathVariable int id){
        if(id<0 || id>userList.size())
            throw new UserNotFoundException("user not found with id - "+id);
        return userList.get(id-1);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex ){
        ErrorResponse er=new ErrorResponse();
        er.setErrorCode(HttpStatus.NOT_FOUND.value());
        er.setErrorMessage(ex.getMessage());
        er.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<> (er,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex){
        ErrorResponse er=new ErrorResponse();
        er.setErrorMessage("You made an invalid request. Please check your URL again!");
        er.setErrorCode(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(er,HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(Exception ex){
        ErrorResponse er=new ErrorResponse();
        er.setErrorCode(HttpStatus.BAD_REQUEST.value());
        er.setErrorMessage(ex.getMessage());
        er.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(er,HttpStatus.BAD_REQUEST);
    }

}

