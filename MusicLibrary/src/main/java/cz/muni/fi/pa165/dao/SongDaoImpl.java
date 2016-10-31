package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Song;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * JPA implementation of SongDao interface
 * @author David Pribula
 */
@Repository
@Transactional
public class SongDaoImpl implements SongDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Song song) {
        em.persist(song);
    }

    @Override
    public Song update(Song song) {
        return em.merge(song);
    }

    @Override
    public void delete(Song song) {
        em.remove(em.merge(song));
    }

    @Override
    public Song findById(Long id) {
        return em.find(Song.class, id);
    }

    @Override
    public List<Song> findAll() {
        return em.createQuery("SELECT s FROM Song s", Song.class).getResultList();
    }

}
