package ru.iooko.votingapp.controllers.votes;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.iooko.votingapp.model.Votes;
import ru.iooko.votingapp.util.validation.ValidationUtil;

import java.net.URI;
import java.time.LocalTime;

@RestController
@Secured({"ROLE_ADMIN", "ROLE_USER"})
@RequestMapping(value = VoteController.REST_URL,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteController extends AbstractVoteController {

    protected static final String REST_URL = "/api/votes";

    @PostMapping
    public ResponseEntity<Votes> createVote(@RequestParam Integer menuId) {
        Votes createdVote = super.create(menuId, LocalTime.now(), ValidationUtil.TIME_CONSTRAINT);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(createdVote.getId()).toUri();
        return ResponseEntity.created(uri).body(createdVote);
    }
}