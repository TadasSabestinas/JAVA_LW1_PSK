package lt.vu.persistence;

import lt.vu.entities.Team;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import javax.persistence.TypedQuery;

@ApplicationScoped
public class TeamsDAO {

    @Inject
    private EntityManager em;

    public List<Team> loadAll() {
        return em.createNamedQuery("Team.findAll", Team.class).getResultList();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(Team team){
        this.em.persist(team);
    }

    public Team findOne(Integer id) {
        return em.find(Team.class, id);
    }

    public List loadTeam(String s) {
        s = s.toUpperCase();
        return em.createQuery(
                        "select t " +
                                "from Team as t " +
                                "where upper(t.name) like :teamName")
                .setParameter("teamName", s == null ? "" : "%" + s + "%").
                getResultList();
    }

    public Team findTeamByName(String teamName) {
        TypedQuery<Team> query = em.createQuery(
                "SELECT t FROM Team t WHERE t.name = :teamName", Team.class);
        query.setParameter("teamName", teamName);
        List<Team> teams = query.getResultList();
        if (!teams.isEmpty()) {
            return teams.get(0);
        }
        return null; // Or throw an exception if team not found
    }
}
