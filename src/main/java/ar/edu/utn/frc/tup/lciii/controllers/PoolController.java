package ar.edu.utn.frc.tup.lciii.controllers;

import ar.edu.utn.frc.tup.lciii.dtos.common.respones.PoolResponseDTO;
import ar.edu.utn.frc.tup.lciii.services.PoolsService;
import org.apache.coyote.Response;
import org.modelmapper.internal.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.TypeCollector;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/rwc/2023")
public class PoolController {


    @Autowired
    private PoolsService poolsService;
    @GetMapping("/pool")
    public ResponseEntity<PoolResponseDTO> getPoolStats(@RequestParam(name= "id") String id){
        PoolResponseDTO response = poolsService.getPool(id);
        if(response != null){
            return ResponseEntity.ok(response);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"error when retrieving info");


    }

    @GetMapping("pools")
    public ResponseEntity<List<PoolResponseDTO>> getAllPoolStats(){
        List<PoolResponseDTO> pools = poolsService.getAlPools();
        if(pools.size() > 0){
            return ResponseEntity.ok(pools);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"error when retrieving info");
    }
}
