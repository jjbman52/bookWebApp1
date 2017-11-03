package com.mycompany.firstmaven.bookwebapp.Model;

import java.io.Serializable;
import java.sql.SQLException;
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
public class AuthorService implements Serializable{
    private final String AUTHOR_TBL = "author";
    private final String AUTHOR_PK = "author_id";
    
    @PersistenceContext(unitName = "book_PU")
    private EntityManager em;

    public AuthorService() {
    }

    public final int updateAuthorById(List<String> colNames, List<Object> colValues, String id) throws ClassNotFoundException, SQLException {
        if (colNames == null || colNames.isEmpty() || colValues == null || colValues.isEmpty() || id == null || id.isEmpty()) {
            throw new IllegalArgumentException("colNames and colValues are required");
        } else {
            return 0;
        }
    }

    public final int addAuthor(List<String> colNames, List<Object> colValues) throws ClassNotFoundException, SQLException {
        String jpql = "delete a from Author a where a.authorId = :id";
//        Query q = getEm().createQuery(jpql);
//        q.setParameter("id", new Integer());
        return 0;
    }

    public final int removeAuthorById(String id) throws Exception{

        String jpql = "delete a from Author a where a.authorId = :id";
        Query q = getEm().createQuery(jpql);
        q.setParameter("id", new Integer(id));
        
        return q.executeUpdate();
    }

    public final Author getAuthor(String id) throws Exception {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("id is required");
        }

        String jpql = "select a from Author a where a.authorId = :id";
        Query q = getEm().createQuery(jpql);
        q.setParameter("id", new Integer(id));
        q.executeUpdate();

        return null;
    }

    public final List<Author> getAuthorList() throws Exception{
        String jpql = "select a from Author a";
        TypedQuery<Author> q = getEm().createQuery(jpql, Author.class);
        
        return q.getResultList();
    }

    public final EntityManager getEm() {
        return em;
    }

    public final void setEm(EntityManager em) {
        this.em = em;
    }
}
