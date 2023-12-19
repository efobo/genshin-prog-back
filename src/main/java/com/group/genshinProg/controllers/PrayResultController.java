package com.group.genshinProg.controllers;

import com.group.genshinProg.model.DTO.*;
import com.group.genshinProg.services.PrayResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/main")
public class PrayResultController {
    @Autowired
    private final PrayResultService prayResultService;

    @Autowired
    public PrayResultController(PrayResultService prayResultService) {
        this.prayResultService = prayResultService;
    }

    @CrossOrigin
    @PostMapping(path = "/add-prayer", consumes = "application/json")
    public ResponseEntity<?> addPrayer (@RequestBody PrayerDTO prayerDTO) {
        PrayerDTO newpr = prayerDTO;
        List<ProbabilityDTO> list = prayResultService.addPrayer(prayerDTO);
        return new ResponseEntity<>(list, HttpStatus.CREATED);
    }




    @GetMapping(path = "/get-probabilities/{amount}/{userName}", produces = "application/json")
    public ResponseEntity<?> getProbabilities (@PathVariable Integer amount, @PathVariable String userName) {
        List<ProbabilityDTO> list = prayResultService.getProbabilities(amount, userName);
        if (list.size() == 0) System.out.println("LIST IS NULL");
        return new ResponseEntity<>(list, HttpStatus.CREATED);
    }

    // получать историю всех круток
    @GetMapping("/get-history/{userName}")
    public ResponseEntity<?> getHistory (@PathVariable String userName) {
        List<PrayResultDTO> list = prayResultService.getAllPrayResult(userName);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    @Transactional
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePrayResult (@PathVariable("id") Long id) {
        prayResultService.deletePrayResult(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
