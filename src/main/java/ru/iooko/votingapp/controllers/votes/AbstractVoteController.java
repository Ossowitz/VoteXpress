package ru.iooko.votingapp.controllers.votes;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import ru.iooko.votingapp.model.Votes;
import ru.iooko.votingapp.security.SecurityUtil;
import ru.iooko.votingapp.service.VoteService;

import java.time.LocalTime;

@Slf4j
public class AbstractVoteController {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private VoteService service;

    public Votes create(int menuId, LocalTime currentTime, LocalTime constraintTime) {
        int authUserId = SecurityUtil.authUserId();
        log.info("create vote of user with id {} for menu with id {}", authUserId, menuId);
        return service.create(authUserId, menuId, currentTime, constraintTime);
    }
}