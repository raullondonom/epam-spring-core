package co.com.raullondono.dao;

import co.com.raullondono.domain.Trainee;
import co.com.raullondono.domain.Trainer;
import co.com.raullondono.domain.Training;
import co.com.raullondono.domain.TrainingType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;

import static org.assertj.core.api.Assertions.assertThat;

class TrainingDAOTest {

    private TrainingDAOImplementation dao;

    @BeforeEach
    void setUp() {
        dao = new TrainingDAOImplementation();
        dao.setStorage(new ConcurrentHashMap<>());
    }

    @Test
    void createTraining_shouldStoreAndReturnEntity() {
        Training tr = new Training();
        tr.setTrainingId(100L);


        Trainee trainee = new Trainee();
        trainee.setUserId(1L);
        trainee.setFirstName("Raul");
        trainee.setLastName("Londono");
        trainee.setUsername("Raul.Londono");
        tr.setTraineeId(trainee);

        Trainer trainer = new Trainer();
        trainer.setUserId(1L);
        trainer.setFirstName("Raul");
        trainer.setLastName("Londono");
        trainer.setUsername("Raul.Londono");
        tr.setTrainerId(trainer);

        tr.setTrainingName("Initial Cardio");
        tr.setTrainingType(TrainingType.CARDIO);
        tr.setTrainingDate(LocalDateTime.now());
        tr.setTrainingDuration(45.0);

        Training saved = dao.createTraining(tr);

        assertThat(saved).isNotNull();
        Training found = dao.selectTraining(1L);
        assertThat(found).isNotNull();
        assertThat(found.getTrainingName()).isEqualTo("Initial Cardio");
        assertThat(found.getTrainingDuration()).isEqualTo(45.0);
    }

    @Test
    void selectTraining_shouldReturnNullWhenNotExists() {
        assertThat(dao.selectTraining(999L)).isNull();
    }
}
