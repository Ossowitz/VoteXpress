package ru.iooko.votingapp.controllers.menu;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.iooko.votingapp.dto.MenuDTO;
import ru.iooko.votingapp.model.Menu;
import ru.iooko.votingapp.util.accessory.MenuUtil;

import javax.validation.Valid;
import java.net.URI;

@RestController
@Secured("ROLE_ADMIN")
@RequestMapping(value = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuController extends AbstractMenuController {

    protected static final String REST_URL = "/api/menues";

    @GetMapping("/{id}")
    public Menu getWithDishes(@PathVariable int id) {
        return super.getWithDishes(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menu> createDish(@Valid @RequestBody MenuDTO menuDTO) {
        Menu createdMenu = super.create(MenuUtil.asTo(menuDTO));
        URI uri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(createdMenu.getId()).toUri();
        return ResponseEntity.created(uri).body(createdMenu);
    }
}