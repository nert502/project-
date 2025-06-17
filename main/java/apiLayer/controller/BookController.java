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
import BusinessLogicLayer.Dto.BookDto;
import BusinessLogicLayer.Service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/get")
    public ResponseEntity<ResponseContainer<?>> getBooks() {
        try {
            List<BookDto> response = bookService.getBooks();
            return new ResponseEntity<>(
                new ResponseContainer<Object>() {{ data = response; }},
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
    public ResponseEntity<ResponseContainer<?>> getBookById(@PathVariable("id") Integer id) {
        try {
            BookDto response = bookService.getBookById(id);
            return new ResponseEntity<>(
                new ResponseContainer<Object>() {{ data = response; }},
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
    public ResponseEntity<ResponseContainer<?>> createBook(@RequestBody BookDto dto) {
        try {
            int id = bookService.createBook(dto);
            return new ResponseEntity<>(
                new ResponseContainer<Object>() {{ data = id; }},
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
    public ResponseEntity<ResponseContainer<?>> updateBook(@RequestBody BookDto dto) {
        try {
            BookDto updated = bookService.updateBook(dto);
            return new ResponseEntity<>(
                new ResponseContainer<Object>() {{ data = updated; }},
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
    public ResponseEntity<ResponseContainer<?>> deleteBook(@RequestBody BookDto dto) {
        try {
            bookService.deleteBook(dto);
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
