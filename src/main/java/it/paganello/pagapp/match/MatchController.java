package it.paganello.pagapp.match;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping(value = "/match")
public class MatchController {
    @Autowired
    MatchService service;

    @PutMapping(value = "/{id}")
    public Optional<Match> modifyMatch(@PathVariable Long id, @RequestBody Match entity) {
        return service.modifyMatch(id, entity);
    }

    @GetMapping(value = "/{id}")
    public Optional<Match> getMatch(@PathVariable Long id) {
        return service.getMatchById(id);
    }
}
