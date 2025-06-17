package ApiLayer.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ApiLayer.Container.ResponseContainer;
import BusinessLogicLayer.Dto.AuthorDto;
import BusinessLogicLayer.Service.AuthorService;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("/get")
    public ResponseEntity<ResponseContainer<?>> getAuthors() {
        try {
            List<AuthorDto> response = authorService.getAuthors();
            return new ResponseEntity<>(
                new ResponseContainer<Object>() {{
                    data = response;
                }},
                HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                new ResponseContainer<Object>() {{
                    data = null;
                    message = e.getMessage();
                }},
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseContainer<?>> getAuthorById(@PathVariable("id") Long id) {
        try {
            AuthorDto response = authorService.getAuthorById(id); 
            return new ResponseEntity<>(
                new ResponseContainer<Object>() {{
                    data = response;
                }},
                HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                new ResponseContainer<Object>() {{
                    data = null;
                    message = e.getMessage();
                }},
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseContainer<?>> createAuthor(@RequestBody AuthorDto dto) {
        try {
             int id = authorService.createAuthor(dto);  
            return new ResponseEntity<>(
                new ResponseContainer<Object>() {{
                    data = id;
                }},
                HttpStatus.CREATED
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                new ResponseContainer<Object>() {{
                    data = null;
                    message = e.getMessage();
                }},
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @PutMapping("/save")
    public ResponseEntity<ResponseContainer<?>> updateAuthor(@RequestBody AuthorDto dto) {
        try {
            AuthorDto updated = authorService.updateAuthor(dto);
            return new ResponseEntity<>(
                new ResponseContainer<Object>() {{
                    data = updated;
                }},
                HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                new ResponseContainer<Object>() {{
                    data = null;
                    message = e.getMessage();
                }},
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseContainer<?>> deleteAuthor(@RequestBody AuthorDto dto) {
        try {
            authorService.deleteAuthor(dto);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(
                new ResponseContainer<Object>() {{
                    data = null;
                    message = e.getMessage();
                }},
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
