package lt.vu.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Tournament.findAll", query = "select t from Tournament as t")
})
public class Tournament {
    private Long id;

    @GeneratedValue
    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Basic(optional = false)
    @Column(name = "NAME", nullable = false)
    private String name;

    @Basic
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    private List<Team> teams;
    @ManyToMany(mappedBy = "tournaments")
    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tournament tournament = (Tournament) o;
        return id.equals(tournament.id) && Objects.equals(name, tournament.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
