package lt.vu.rest;

import lombok.*;
import lt.vu.rest.contracts.PlayerDto;
import lt.vu.entities.Player;
import lt.vu.persistence.PlayersDAO;
import lt.vu.persistence.TeamsDAO;
import lt.vu.entities.Team;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/players")
public class PlayersController {

    @Inject
    @Setter @Getter
    private PlayersDAO playersDAO;

    @Inject
    @Setter @Getter
    private TeamsDAO teamsDAO;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Integer id) {
        Player player = playersDAO.findOne(id);
        if (player == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        PlayerDto playerDto = new PlayerDto();
        playerDto.setName(player.getName());

        if (player.getTeam() != null) {
            playerDto.setTeamName(player.getTeam().getName());
        }

        playerDto.setRole(player.getRole());

        return Response.ok(playerDto).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response add(PlayerDto playerData){
        Player newPlayer = new Player();
        newPlayer.setName(playerData.getName());

        // Resolve team by name
        if (playerData.getTeamName() != null) {
            Team team = teamsDAO.findTeamByName(playerData.getTeamName());
            newPlayer.setTeam(team);
        }

        newPlayer.setRole(playerData.getRole());

        playersDAO.persist(newPlayer);

        return Response.ok().build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(
            @PathParam("id") final Integer playerId,
            PlayerDto playerData) {
        try {
            Player existingPlayer = playersDAO.findOne(playerId);
            if (existingPlayer == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            existingPlayer.setName(playerData.getName());

            if (playerData.getTeamName() != null) {
                Team team = teamsDAO.findTeamByName(playerData.getTeamName());
                existingPlayer.setTeam(team);
            }

            existingPlayer.setRole(playerData.getRole());

            playersDAO.update(existingPlayer);
            return Response.ok().build();
        } catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }
}
