package ru.iooko.votingapp.util.validation;

import lombok.experimental.UtilityClass;
import ru.iooko.votingapp.exception.NotFoundException;
import ru.iooko.votingapp.exception.RequestDataException;
import ru.iooko.votingapp.util.PersistableId;

import java.time.LocalTime;

@UtilityClass
public class ValidationUtil {

    private static final LocalTime TIME_CONSTRAINT = LocalTime.of(11, 0, 0);

    public static <T> T checkNotFoundWithId(T object, int id) {
        checkNotFoundWithId(object != null, id);
        return object;
    }

    public static void checkNotFound(boolean found, String message) {
        if (!found) {
            throw new NotFoundException("Not found entity with " + message);
        }
    }

    public static <T> T checkNotFound(T object, String msg) {
        checkNotFound(object != null, msg);
        return object;
    }

    public static void checkNotFoundWithId(boolean found, int id) {
        checkNotFound(found, "id=" + id);
    }

    public static void checkNew(PersistableId object) {
        if (!object.isNew()) {
            throw new RequestDataException(object + " must be new, so id = null");
        }
    }

    public static void assureIdConsistent(PersistableId object, int id) {
        if (object.isNew()) {
            object.setId(id);
        } else if (object.id() != id) {
            throw new RequestDataException(object + " must be with id = " + id);
        }
    }
}