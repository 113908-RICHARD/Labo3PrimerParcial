package ar.edu.utn.frc.tup.lciii.services;

import ar.edu.utn.frc.tup.lciii.dtos.common.requests.MatchRequestDTO;
import ar.edu.utn.frc.tup.lciii.dtos.common.requests.TeamRequestDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RestServices {


    List<TeamRequestDTO> getTeamsByPool(String id);
    List<TeamRequestDTO> getAllTeams();

    List<MatchRequestDTO> getMatches();
}
