package domainLayer.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    public String name;
    public String nationality;
    public Date birthDate;
    	

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Book> books = new ArrayList<>();
    
    @ManyToMany(mappedBy = "authors")
    public List<Book> book = new ArrayList<>();
   
    public String getName() {
    	return name; 
    }
    
    public String getNationality() {
    	return nationality; 
    }
    
    public Date getBirthDate() {
    	return birthDate; 
    	
    }
    
    public int getId() {
    	return id; 
    }

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public List<Book> getBook() {
		return book;
	}

	public void setBook(List<Book> book) {
		this.book = book;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
    
    
}

