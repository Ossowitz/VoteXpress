package ru.iooko.votingapp.util.accessory;

import lombok.experimental.UtilityClass;
import ru.iooko.votingapp.dto.RestaurantDTO;
import ru.iooko.votingapp.model.Menu;
import ru.iooko.votingapp.model.Restaurant;
import ru.iooko.votingapp.model.Votes;
import ru.iooko.votingapp.security.SecurityUtil;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@UtilityClass
public class RestaurantUtil {

    public static List<RestaurantDTO> getDTOs(Collection<Restaurant> restaurantCollection) {
        return restaurantCollection.stream()
                .map(RestaurantUtil::asTo)
                .toList();
    }

    public static RestaurantDTO asTo(Restaurant restaurant) {
        Menu menu = restaurant.getMenues()
                .stream().findFirst()
                .orElse(null);
        if (menu == null || menu.getVotes() == null) {
            return new RestaurantDTO(restaurant.getId(), restaurant.getName(), restaurant.getMenues(), 0, false);
        }

        Set<Votes> votes = menu.getVotes();
        Integer size = votes.size();
        boolean isVoted = votes.stream()
                .anyMatch(
                        it -> {
                            int id = it.getUser().getId() == null ? 0 : it.getUser().getId();
                            return id == SecurityUtil.authUserId();
                        }
                );
        return new RestaurantDTO(restaurant.getId(), restaurant.getName(),
                restaurant.getMenues(), size, isVoted);
    }
}