package ar.edu.utn.frc.tup.lciii.services.imp;

import ar.edu.utn.frc.tup.lciii.dtos.common.requests.MatchRequestDTO;
import ar.edu.utn.frc.tup.lciii.dtos.common.requests.TeamRequestDTO;
import ar.edu.utn.frc.tup.lciii.services.RestServices;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.apache.logging.log4j.util.ReadOnlyStringMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class RestServicesImp implements RestServices {

    @Autowired
    RestTemplate restTemplate;

    @CircuitBreaker(name = "circuitA", fallbackMethod = "handleFallback")
    public List<TeamRequestDTO> getTeamsByPool(String id) {
        String apiUrl = "https://my-json-server.typicode.com/LCIV-2023/fake-api-rwc2023/teams";

        ResponseEntity<TeamRequestDTO[]> responseEntity = restTemplate.getForEntity(apiUrl, TeamRequestDTO[].class);

        TeamRequestDTO[] allTeams = responseEntity.getBody();

        if (allTeams == null) {
            return null;
        }

        List<TeamRequestDTO> poolTeams = new ArrayList<>();
        for (TeamRequestDTO team : allTeams) {
            if (team.getPool().equals(id)) {
                poolTeams.add(team);
            }
        }
        return poolTeams;
    }
    @CircuitBreaker(name = "circuitB", fallbackMethod = "handleFallback")
    public List<TeamRequestDTO> getAllTeams() {
        String apiUrl = "https://my-json-server.typicode.com/LCIV-2023/fake-api-rwc2023/teams";

        ResponseEntity<TeamRequestDTO[]> responseEntity = restTemplate.getForEntity(apiUrl, TeamRequestDTO[].class);

        TeamRequestDTO[] allTeams = responseEntity.getBody();

        if (allTeams == null) {
            return null;
        }

        return List.of(allTeams);
    }
    @CircuitBreaker(name = "circuitC", fallbackMethod = "handleFallback")
    @Override
    public List<MatchRequestDTO> getMatches() {
        String apiUrl = "https://my-json-server.typicode.com/LCIV-2023/fake-api-rwc2023/matches";

        ResponseEntity<MatchRequestDTO[]> responseEntity = restTemplate.getForEntity(apiUrl, MatchRequestDTO[].class);

        MatchRequestDTO[] matches = responseEntity.getBody();

        if (matches == null) {
            return null;
        }

        return Arrays.asList(matches);
    }
    public String handleFallback(Exception exception){
        return "default";
    }


}
