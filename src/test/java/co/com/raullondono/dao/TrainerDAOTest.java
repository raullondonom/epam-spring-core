package co.com.raullondono.dao;

import co.com.raullondono.domain.Trainer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ConcurrentHashMap;

import static org.assertj.core.api.Assertions.assertThat;

class TrainerDAOTest {

    private TrainerDAOImplementation dao;

    @BeforeEach
    void setUp() {
        dao = new TrainerDAOImplementation();
        dao.setStorage(new ConcurrentHashMap<>());
    }

    @Test
    void createTrainer_shouldStoreById() {
        Trainer t = new Trainer();
        t.setUserId(1L);
        t.setFirstName("Raul");
        t.setLastName("Londono");
        t.setUsername("Raul.Londono");

        Trainer saved = dao.createTrainer(t);

        assertThat(saved).isNotNull();
        assertThat(dao.selectTrainer(1L)).isNotNull();
        assertThat(dao.selectTrainer(1L).getUsername()).isEqualTo("Raul.Londono");
    }

    @Test
    void updateTrainer_shouldReplace() {
        Trainer t = new Trainer();
        t.setUserId(11L);
        t.setFirstName("Alejandra");
        t.setLastName("Pulgarin");
        t.setUsername("Alejandra.Pulgarin");
        dao.createTrainer(t);

        Trainer changed = new Trainer();
        changed.setUserId(11L);
        changed.setFirstName("Alejandra");
        changed.setLastName("Linares");
        changed.setUsername("Alejandra.Linares");

        Trainer updated = dao.updateTrainer(changed);

        assertThat(updated).isNotNull();
        assertThat(dao.selectTrainer(11L).getLastName()).isEqualTo("Linares");
        assertThat(dao.selectTrainer(11L).getUsername()).isEqualTo("Alejandra.Linares");
    }

    @Test
    void selectTrainer_shouldReturnNullWhenNotExists() {
        assertThat(dao.selectTrainer(777L)).isNull();
    }
}
