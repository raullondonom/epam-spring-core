package co.com.raullondono.dao;

import co.com.raullondono.domain.Trainee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ConcurrentHashMap;

import static org.assertj.core.api.Assertions.assertThat;

class TraineeDAOTest {

    private TraineeDAOImplementation dao;

    @BeforeEach
    void setUp() {
        dao = new TraineeDAOImplementation();
        dao.setStorage(new ConcurrentHashMap<>());
    }

    @Test
    void createTrainee_shouldStoreByIdAndReturnEntity() {
        Trainee t = new Trainee();
        t.setUserId(1L);
        t.setFirstName("Raul");
        t.setLastName("Londono");
        t.setUsername("Raul.Londono");

        Trainee saved = dao.createTrainee(t);

        assertThat(saved).isNotNull();
        assertThat(dao.selectTrainee(1L)).isNotNull();
        assertThat(dao.selectTrainee(1L).getUsername()).isEqualTo("Raul.Londono");
    }

    @Test
    void updateTrainee_shouldReplaceExistingFields() {
        Trainee t = new Trainee();
        t.setUserId(2L);
        t.setFirstName("Ana");
        t.setLastName("Diaz");
        t.setUsername("Ana.Diaz");
        dao.createTrainee(t);

        Trainee changed = new Trainee();
        changed.setUserId(2L);
        changed.setFirstName("Ana");
        changed.setLastName("Gomez");
        changed.setUsername("Ana.Gomez");

        Trainee updated = dao.updateTrainee(changed);

        assertThat(updated).isNotNull();
        assertThat(dao.selectTrainee(2L).getLastName()).isEqualTo("Gomez");
        assertThat(dao.selectTrainee(2L).getUsername()).isEqualTo("Ana.Gomez");
    }

    @Test
    void selectTrainee_shouldReturnNullWhenNotExists() {
        assertThat(dao.selectTrainee(999L)).isNull();
    }

    @Test
    void deleteTrainee_shouldRemoveAndReturnTrueWhenExists() {
        Trainee t = new Trainee();
        t.setUserId(3L);
        dao.createTrainee(t);

        Trainee removed = dao.deleteTrainee(3L);

        assertThat(removed).isNotNull();
        assertThat(dao.selectTrainee(3L)).isNull();
    }

    @Test
    void deleteTrainee_shouldReturnFalseWhenNotExists() {
        assertThat(dao.deleteTrainee(404L)).isNull();
    }
}
