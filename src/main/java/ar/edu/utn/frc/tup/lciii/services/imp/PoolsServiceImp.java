package ar.edu.utn.frc.tup.lciii.services.imp;

import ar.edu.utn.frc.tup.lciii.dtos.common.requests.MatchRequestDTO;
import ar.edu.utn.frc.tup.lciii.dtos.common.requests.TeamRequestDTO;
import ar.edu.utn.frc.tup.lciii.dtos.common.requests.TemMatchesRequestDTO;
import ar.edu.utn.frc.tup.lciii.dtos.common.respones.PoolResponseDTO;
import ar.edu.utn.frc.tup.lciii.dtos.common.respones.TeamResponseDTO;
import ar.edu.utn.frc.tup.lciii.services.PoolsService;
import ar.edu.utn.frc.tup.lciii.services.RestServices;
import org.hibernate.annotations.NaturalId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PoolsServiceImp implements PoolsService {

    @Autowired
    RestServices restServices;

    @Autowired
    ModelMapper modelMapper;
    @Override
    public PoolResponseDTO getPool(String id) {
       PoolResponseDTO responseDTO = new PoolResponseDTO();
       responseDTO.setPoolId(id);
       List<TeamRequestDTO> teams = restServices.getTeamsByPool(id);
       List<TeamResponseDTO> listOfTeams = new ArrayList<>();
        for (TeamRequestDTO team: teams
             ) {
            TeamResponseDTO teamToAdd = new TeamResponseDTO();
            teamToAdd.setTeamId(team.getId().intValue());
            teamToAdd.setTeamName(team.getName());
            teamToAdd.setCountry(team.getCountry());
            teamToAdd.setMatchesPlayed(this.getMatchesPlayed(teamToAdd.getTeamId()));
            teamToAdd.setWins(this.wins(teamToAdd.getTeamId()));
            teamToAdd.setDraws(this.draws(teamToAdd.getTeamId()));
            teamToAdd.setLosses(this.loses(teamToAdd.getTeamId()));
            teamToAdd.setPointsDifferential(this.pointsDifferential(teamToAdd.getTeamId()));
            teamToAdd.setPoints(this.getPoints(teamToAdd.getTeamId()));
            teamToAdd.setTriesMade(this.triesMade(teamToAdd.getTeamId()));
            teamToAdd.setBonusPoints(this.getBonusPoints(teamToAdd.getTeamId()));
            teamToAdd.setTotalRedCards(this.getRedCards(teamToAdd.getTeamId()));
            teamToAdd.setTotalYellowCards(this.getYellowCards(teamToAdd.getTeamId()));
            teamToAdd.setPointsFor(this.pointsFor(teamToAdd.getTeamId()));
            teamToAdd.setPoinstAgainst(this.pointsAgainst(teamToAdd.getTeamId()));
            listOfTeams.add(teamToAdd);
        }
        responseDTO.setTeams(listOfTeams);

        return responseDTO;


    }

    @Override
    public List<PoolResponseDTO> getAlPools() {
        List<PoolResponseDTO> completeResponse = new ArrayList<>();
        List<String> pools = new ArrayList<>();
        pools.add("A");
        pools.add("B");
        pools.add("C");
        pools.add("D");


        for (String id: pools
             ) {
            PoolResponseDTO responseDTO = new PoolResponseDTO();
            responseDTO.setPoolId(id);
            List<TeamRequestDTO> teams = restServices.getTeamsByPool(id);
            List<TeamResponseDTO> listOfTeams = new ArrayList<>();
            for (TeamRequestDTO team: teams
            ) {
                TeamResponseDTO teamToAdd = new TeamResponseDTO();
                teamToAdd.setTeamId(team.getId().intValue());
                teamToAdd.setTeamName(team.getName());
                teamToAdd.setCountry(team.getCountry());
                teamToAdd.setMatchesPlayed(this.getMatchesPlayed(teamToAdd.getTeamId()));
                teamToAdd.setWins(this.wins(teamToAdd.getTeamId()));
                teamToAdd.setDraws(this.draws(teamToAdd.getTeamId()));
                teamToAdd.setLosses(this.loses(teamToAdd.getTeamId()));
                teamToAdd.setPointsDifferential(this.pointsDifferential(teamToAdd.getTeamId()));
                teamToAdd.setPoints(this.getPoints(teamToAdd.getTeamId()));
                teamToAdd.setTriesMade(this.triesMade(teamToAdd.getTeamId()));
                teamToAdd.setBonusPoints(this.getBonusPoints(teamToAdd.getTeamId()));
                teamToAdd.setTotalRedCards(this.getRedCards(teamToAdd.getTeamId()));
                teamToAdd.setTotalYellowCards(this.getYellowCards(teamToAdd.getTeamId()));
                teamToAdd.setPointsFor(this.pointsFor(teamToAdd.getTeamId()));
                teamToAdd.setPoinstAgainst(this.pointsAgainst(teamToAdd.getTeamId()));
                listOfTeams.add(teamToAdd);
            }
            responseDTO.setTeams(listOfTeams);
            completeResponse.add(responseDTO);

        }

        return completeResponse;
    }

    public Integer getMatchesPlayed(Integer teamId) {
        Integer wins = 0;
        List<MatchRequestDTO> matches = restServices.getMatches();

        for (MatchRequestDTO match : matches) {
            for (TemMatchesRequestDTO matchTeams : match.getTeams()) {
                teamId = teamId.intValue();

                if (matchTeams.getId().equals(teamId)) {
                    wins++;
                }
            }
        }

        return wins;
    }



    public Integer wins(Integer teamId) {
        int wins = 0;
       List<MatchRequestDTO> matches = restServices.getMatches();
        for (MatchRequestDTO match : matches) {
            for (TemMatchesRequestDTO teamMatch : match.getTeams()) {
                if (teamMatch.getId().equals(teamId)) {

                    for (TemMatchesRequestDTO opponent : match.getTeams()) {
                        if (!teamMatch.equals(opponent) && teamMatch.getPoints() > opponent.getPoints()) {
                            wins++;
                            break;
                        }
                    }
                }
            }
        }

        return wins;
    }

    public Integer draws(Integer teamId){
        int draws = 0;
        List<MatchRequestDTO> matches = restServices.getMatches();
        for (MatchRequestDTO match : matches) {
            for (TemMatchesRequestDTO teamMatch : match.getTeams()) {
                if (teamMatch.getId().equals(teamId)) {

                    for (TemMatchesRequestDTO opponent : match.getTeams()) {
                        if (!teamMatch.equals(opponent) && teamMatch.getPoints().equals(opponent.getPoints())) {
                            draws++;
                            break;
                        }
                    }
                }
            }
        }

        return draws;
    }
    public Integer loses(Integer teamId) {
        int losses = 0;
        List<MatchRequestDTO> matches = restServices.getMatches();
        for (MatchRequestDTO match : matches) {
            for (TemMatchesRequestDTO teamMatch : match.getTeams()) {
                if (teamMatch.getId().equals(teamId)) {

                    for (TemMatchesRequestDTO opponent : match.getTeams()) {
                        if (!teamMatch.equals(opponent) && teamMatch.getPoints() < opponent.getPoints()) {
                            losses++;
                            break;
                        }
                    }
                }
            }
        }

        return losses;
    }
    public Integer pointsFor(Integer teamId) {
        int pointsEarned = 0;
        List<MatchRequestDTO> matches = restServices.getMatches();
        for (MatchRequestDTO match : matches) {
            for (TemMatchesRequestDTO teamMatch : match.getTeams()) {
                if (teamMatch.getId().equals(teamId)) {
                    pointsEarned += teamMatch.getPoints();
                }
            }
        }

        return pointsEarned;
    }

    public Integer pointsAgainst(Integer teamId) {
        int pointsConceded = 0;
        List<MatchRequestDTO> matches = restServices.getMatches();

        for (MatchRequestDTO match : matches) {
            List<TemMatchesRequestDTO> teams = match.getTeams();


            boolean containsTeamId = teams.stream().anyMatch(team -> team.getId().equals(teamId));

            if (containsTeamId) {
                for (TemMatchesRequestDTO teamMatch : teams) {
                    if (!teamMatch.getId().equals(teamId)) {
                        pointsConceded += teamMatch.getPoints();
                    }
                }
            }
        }

        return pointsConceded;

    }
    public Integer pointsDifferential(Integer teamId) {
        int pointsFor = pointsFor(teamId);
        int pointsAgainst = pointsAgainst(teamId);

        return pointsFor - pointsAgainst;
    }
    public Integer triesMade(Integer teamId) {
        int totalTriesMade = 0;
        List<MatchRequestDTO> matches = restServices.getMatches();
        for (MatchRequestDTO match : matches) {
            for (TemMatchesRequestDTO teamMatch : match.getTeams()) {
                if (teamMatch.getId().equals(teamId)) {
                    totalTriesMade += teamMatch.getTries();
                }
            }
        }

        return totalTriesMade;
    }

    public Integer getPoints(Integer teamId){
        int wins = wins(teamId);
        int draws = draws(teamId);


        int totalPoints = (wins * 4) + (draws * 2);
        totalPoints = totalPoints + this.getBonusPoints(teamId);
        return totalPoints;
    }
    public Integer getBonusPoints(Integer teamId){

        int bonusPoints = 0;
        List<MatchRequestDTO> matches = restServices.getMatches();
        for (MatchRequestDTO match : matches) {
            for (TemMatchesRequestDTO teamMatch : match.getTeams()) {
                if (teamMatch.getId().equals(teamId)) {
                    int pointsDifference = pointsFor(teamId) - pointsAgainst(teamId);
                    if (teamMatch.getTries() >= 4) {
                        bonusPoints++;
                    }
                    if (teamMatch.getPoints() < pointsDifference && pointsDifference <= 7) {
                        bonusPoints++;
                    }
                }
            }
        }

        return bonusPoints;
    }
    public Integer getYellowCards(Integer teamId){
        int totalYellowCards = 0;
        List<MatchRequestDTO> matches = restServices.getMatches();
        for (MatchRequestDTO match : matches) {
            for (TemMatchesRequestDTO teamMatch : match.getTeams()) {
                if (teamMatch.getId().equals(teamId)) {
                    totalYellowCards += teamMatch.getYellowCards();
                }
            }
        }

        return totalYellowCards;
    }
    public Integer getRedCards(Integer teamId) {
        int totalRedCards = 0;
        List<MatchRequestDTO> matches = restServices.getMatches();
        for (MatchRequestDTO match : matches) {
            for (TemMatchesRequestDTO teamMatch : match.getTeams()) {
                if (teamMatch.getId().equals(teamId)) {
                    totalRedCards += teamMatch.getRedCards();
                }
            }
        }

        return totalRedCards;
    }
}
