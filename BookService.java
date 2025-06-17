package BusinessLogicLayer.Service;


import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import BusinessLogicLayer.Dto.BookDto;
import domainLayer.entities.Author;
import domainLayer.entities.Book;
import domainLayer.repositoty.BookRepository;

@Service
public class BookService {

    @Autowired 
    private BookRepository bookRepository;

    @Autowired
    private Validator validator;
    
    public int getBookId() {
        return 42;
    }

    
    public List<BookDto> getBooks() throws Exception {
        return bookRepository
                .get()
                .stream()
                .map(b -> new BookDto() {{
                    id = b.id;
                    title = b.title;
                    isbn = b.isbn;
                    category = b.category;
                    publicationYear = b.publicationYear;
                    authorId = b.author != null ? b.author.id : 0;
                    authorName = b.author != null ? b.author.name : null;
                }})
                .collect(Collectors.toList());
    }

    public BookDto getBookById(int id) throws Exception {
        Book b = bookRepository.getById(id);

        return new BookDto() {{
            id = b.id;
            title = b.title;
            isbn = b.isbn;
            category = b.category;
            publicationYear = b.publicationYear;
            authorId = b.author != null ? b.author.id : 0;
            authorName = b.author != null ? b.author.name : null;
        }};
    }

    public int createBook(BookDto dto) throws Exception {
        List<String> validationViolationMessages = validator.validate(dto)
                .stream()
                .map(v -> v.getMessage())
                .collect(Collectors.toList());

        if (!validationViolationMessages.isEmpty())
            throw new Exception(String.join("\n", validationViolationMessages));

        Book b = new Book() {{
            title = dto.title;
            isbn = dto.isbn;
            category = dto.category;
            publicationYear = dto.publicationYear;
            author = new Author() {{ id = dto.authorId; }};
        }};

        return bookRepository.create(b);
    }

    public BookDto updateBook(BookDto dto) throws Exception {
        List<String> validationViolationMessages = validator.validate(dto)
                .stream()
                .map(v -> v.getMessage())
                .collect(Collectors.toList());

        if (!validationViolationMessages.isEmpty())
            throw new Exception(String.join("\n", validationViolationMessages));

        bookRepository.update(new Book() {{
            id = dto.id;
            title = dto.title;
            isbn = dto.isbn;
            category = dto.category;
            publicationYear = dto.publicationYear;
            author = new Author() {{ id = dto.authorId; }};
        }});

        return dto;
    }

    public void deleteBook(BookDto dto) throws Exception {
        bookRepository.delete(dto.id);
    }
}
