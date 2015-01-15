/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.amu.dji.jms.lab10.books.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.amu.dji.jms.lab10.books.model.Book;
import pl.edu.amu.dji.jms.lab10.books.repository.BookRepository;

/**
 *
 * @author Szymon
 */
@RestController
public class BookService {

    @Autowired
    private BookRepository bookRepo;

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    @ResponseBody
    public List<Book> getAllBooks() {
        return (List<Book>) bookRepo.findAll();
    }
    
    @RequestMapping(value = "/books/add", method = RequestMethod.POST)
    @ResponseBody
    public void addBook(@RequestBody Book book) {
        bookRepo.save(book);
    }
    
    @RequestMapping(value = "/books/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Book getBook(@PathVariable("id") String id) {
        return bookRepo.findOne(id);
    }
    
    @RequestMapping(value = "/books/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteBook(@PathVariable("id") String id, @RequestBody Book book) {
        bookRepo.delete(book);
        return "gitara";
    }
}
