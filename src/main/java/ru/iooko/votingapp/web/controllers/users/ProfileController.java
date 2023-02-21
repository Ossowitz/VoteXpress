package ru.iooko.votingapp.web.controllers.users;

import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Secured("ROLE_USER")
@RequestMapping(value = ProfileController.REST_URL,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileController extends AbstractUserController {

    protected static final String REST_URL = "api/profile";

}