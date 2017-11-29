package com.mycompany.firstmaven.bookwebapp.Model;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author jordanrehbein
 */
@Stateless
public class AuthorServiceOriginal implements Serializable{
    private final String AUTHOR_TBL = "author";
    private final String AUTHOR_PK = "author_id";
    
    @PersistenceContext(unitName = "book_PU")
    private EntityManager em;

    public AuthorServiceOriginal() {
    }

    public void updateAuthorById(String id, String name, String date) throws ClassNotFoundException, SQLException, ParseException {
        Author author = new Author();
        Integer value = Integer.parseInt(id);
        author.setAuthorId(value);
        author.setAuthorName(name);
        Date d = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        author.setDateAdded(d);
        
        getEm().merge(author);
    }

    public void addAuthor(String name, Date date) throws ClassNotFoundException, SQLException, ParseException {
        Author author = new Author();
        author.setAuthorName(name);
        author.setDateAdded(date);
        
        getEm().persist(author);
    }

    public void removeAuthorById(String id) throws Exception{
        Integer value = Integer.parseInt(id);
        String jpql = "DELETE FROM Author a WHERE a.authorId = :id";
        Query q = getEm().createQuery(jpql, Author.class);
        q.setParameter("id", value);
    }

    public Author getAuthor(String id) throws Exception {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("id is required");
        }

        Integer value = Integer.parseInt(id);
        Author author = getEm().find(Author.class, value);
        

        return author;
    }

    public List<Author> getAuthorList() throws Exception{
        String jpql = "SELECT a FROM Author a";
        TypedQuery<Author> q = getEm().createQuery(jpql, Author.class);
        
        return q.getResultList();
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
}
