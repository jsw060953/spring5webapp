package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }


    @Override
    public void run(String... args) throws Exception {

        Publisher scribner = new Publisher("Charles Scribner", "101 Park Avenue", "New York", "NY", "10128");
        publisherRepository.save(scribner);
        Publisher gothamBooks = new Publisher("Gotham Books", "200 Fifth Avenue", "New York", "NY", "10028");
        publisherRepository.save(gothamBooks);
        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123123");
        ddd.setPublisher(scribner);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        scribner.getBooks().add(ddd);

        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(scribner);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "393539459459");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        noEJB.setPublisher(gothamBooks);
        authorRepository.save(rod);
        bookRepository.save(noEJB);
        gothamBooks.getBooks().add(noEJB);
        publisherRepository.save(gothamBooks);




        System.out.println("Hoo Hah");
        System.out.println("Started in Bootstrap");
        System.out.println ("Number of Books: " + bookRepository.count());
        System.out.println ("Number of Authors: " + authorRepository.count());
        System.out.println ("Number of Publishers: " + publisherRepository.count());
    }
}
