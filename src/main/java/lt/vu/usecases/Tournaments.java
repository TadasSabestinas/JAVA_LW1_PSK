package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Tournament;
import lt.vu.entities.Tournament;
import lt.vu.persistence.TournamentsDAO;
import lt.vu.persistence.TournamentsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Tournaments {

    @Inject
    private TournamentsDAO tournamentsDAO;

    @Getter
    @Setter
    private Tournament tournamentToCreate = new Tournament();

    @Getter
    private List<Tournament> allTournaments;
    @Getter
    private List<Tournament> loadedTournaments;
    @Getter @Setter
    private String tournamentToSearch = "";

    @PostConstruct
    public void init(){
        loadAllTournaments();
    }

    @Transactional
    public void createTournament(){
        this.tournamentsDAO.persist(tournamentToCreate);
    }

    private void loadAllTournaments(){
        this.allTournaments = tournamentsDAO.loadAll();
    }
    @Transactional
    public void loadTournamentLike(){
        this.loadedTournaments = tournamentsDAO.loadTournament(tournamentToSearch);
    }
}
