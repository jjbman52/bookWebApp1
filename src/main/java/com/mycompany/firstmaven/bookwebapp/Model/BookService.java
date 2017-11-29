/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.firstmaven.bookwebapp.Model;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author jordanrehbein
 */
@Stateless
public class BookService extends AbstractService<Book> {

    @PersistenceContext(unitName = "book_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEm() {
        return em;
    }

    public BookService() {
        super(Book.class);
    }
    
    public void addNewBook(String title, String isbn, String authorId){
        Book book = new Book();
        
        book.setTitle(title);
        book.setIsbn(isbn);
        Author author = getEm().find(Author.class, new Integer(authorId));
        book.setAuthor(author);
        
        getEm().persist(book);
    }
    
    public void updateBook(String bookId, String title, String isbn, String authorId){
        Book book = this.find(new Integer(bookId));
        
        book.setTitle(title);
        book.setIsbn(isbn);
        Author author = getEm().find(Author.class, new Integer(authorId));
        book.setAuthor(author);
        
        getEm().merge(book);
    }
    
    public Book getBook(String id) throws Exception {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("id is required");
        }

        Integer value = Integer.parseInt(id);
        Book book = getEm().find(Book.class, value);
        

        return book;
    }
    
    public void removeBookById(String id) throws Exception{
        Integer value = Integer.parseInt(id);
        String jpql = "DELETE FROM Book b WHERE b.bookId = :id";
        Query q = getEm().createQuery(jpql, Author.class);
        q.setParameter("id", value);
    }
}
