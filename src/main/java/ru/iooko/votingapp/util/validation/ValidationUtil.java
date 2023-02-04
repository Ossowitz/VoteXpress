package ru.iooko.votingapp.util.validation;

import lombok.experimental.UtilityClass;
import ru.iooko.votingapp.exception.NotFoundException;

import java.time.LocalTime;

@UtilityClass
public class ValidationUtil {

    private static final LocalTime TIME_CONSTRAINT = LocalTime.of(11, 0, 0);

    public static <T> T getWithCheckNotFoundWithId(T object, int id) {
        checkNotFoundWithId(object != null, id);
        return object;
    }

    public static void checkNotFound(boolean found, String message) {
        if (!found) {
            throw new NotFoundException("Not found entity with " + message);
        }
    }

    public static void checkNotFoundWithId(boolean found, int id) {
        checkNotFound(found, "id=" + id);
    }
}
