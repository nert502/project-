package BusinessLogicLayer.Service;


import java.util.List;
import java.util.stream.Collectors;
import java.time.ZoneId;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import BusinessLogicLayer.Dto.AuthorDto;
import domainLayer.entities.Author;
import domainLayer.repositoty.AuthorRepository;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private Validator validator;

    public List<AuthorDto> getAuthors() throws Exception {
            return authorRepository
                       .get()
                       .stream()
                       .map(a -> new AuthorDto() {{
                               id = a.id;
                              name = a.name;
                             nationality = a.nationality;
                             birthDate = a.birthDate.toInstant()
                                                    .atZone(ZoneId.systemDefault())
                                                   .toLocalDate(); 
                             }})
                                   .collect(Collectors.toList());
    }

    public AuthorDto getAuthorById(int id) throws Exception {
          Author a = authorRepository.getById(id);

            return new AuthorDto() {{
                   id = a.id;
                   name = a.name;
                   nationality = a.nationality;
                   birthDate = a.birthDate.toInstant()
                                          .atZone(ZoneId.systemDefault())
                                          .toLocalDate(); 
                  }};
    }

    

    public int createAuthor(AuthorDto dto) throws Exception {
        List<String> validationViolationMessages = validator.validate(dto)
                .stream()
                .map(v -> v.getMessage())
                .collect(Collectors.toList());

        if (!validationViolationMessages.isEmpty())
            throw new Exception(String.join("\n", validationViolationMessages));

        int id = authorRepository.create(new Author() {{
            name = dto.name;
            nationality = dto.nationality;
            birthDate = dto.birthDate == null ? null : java.sql.Date.valueOf(dto.birthDate);

        }});

        return id;
    }
    
    
    public AuthorDto updateAuthor(AuthorDto dto) throws Exception {
        List<String> validationViolationMessages = validator.validate(dto)
                .stream()
                .map(v -> v.getMessage())
                .collect(Collectors.toList());

         if (!validationViolationMessages.isEmpty())
            throw new Exception(String.join("\n", validationViolationMessages));

        authorRepository.update(new Author() {{
            id = dto.id;
            name = dto.name;
            nationality = dto.nationality;
            birthDate = dto.birthDate == null ? null : java.sql.Date.valueOf(dto.birthDate);

        }});

        return dto;
    }

    public void deleteAuthor(AuthorDto dto) throws Exception {
        authorRepository.delete(dto.id);
    }

	public AuthorDto getAuthorById(Long id) { 
		return null;
	}
    
    

}

 


