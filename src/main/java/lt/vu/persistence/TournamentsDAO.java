package lt.vu.persistence;

import lt.vu.entities.Tournament;
import lt.vu.entities.Team;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class TournamentsDAO {

    @Inject
    private EntityManager em;

    public List<Tournament> loadAll() {
        return em.createNamedQuery("Tournament.findAll", Tournament.class).getResultList();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(Tournament tournament){
        this.em.persist(tournament);
    }

    public Tournament findOne(Integer id) {
        return em.find(Tournament.class, id);
    }
    public List loadTournament(String s) {
        s = s.toUpperCase();
        return em.createQuery(
                        "select l " +
                                "from Tournament as l " +
                                "where upper(l.name) like :tournamentName")
                .setParameter("tournamentName", s == null ? "" : "%" + s + "%").
                getResultList();
    }
}