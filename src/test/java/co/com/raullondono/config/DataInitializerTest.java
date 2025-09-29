package co.com.raullondono.config;

import co.com.raullondono.domain.Trainee;
import co.com.raullondono.domain.Trainer;
import co.com.raullondono.domain.Training;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.core.env.Environment;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class DataInitializerTest {

    private Environment env;
    private Map<Long, Trainee> traineeStorage;
    private Map<Long, Trainer> trainerStorage;
    private Map<Long, Training> trainingStorage;

    @BeforeEach
    void setUp() {
        env = Mockito.mock(Environment.class);
        traineeStorage = new ConcurrentHashMap<>();
        trainerStorage = new ConcurrentHashMap<>();
        trainingStorage = new ConcurrentHashMap<>();
    }

    private DataInitializer newInitializer() {
        return new DataInitializer(env, traineeStorage, trainerStorage, trainingStorage);
    }

    @Test
    void afterSingletonsInstantiated_shouldLoadAllStorages_fromClasspathFiles() {
        when(env.getProperty("storage.trainee.file"))
                .thenReturn("fixtures/trainees.json");
        when(env.getProperty("storage.trainer.file"))
                .thenReturn("fixtures/trainers.json");
        when(env.getProperty("storage.training.file"))
                .thenReturn("fixtures/trainings.json");

        DataInitializer initializer = newInitializer();

        initializer.afterSingletonsInstantiated();

        assertThat(traineeStorage).hasSize(2);
        assertThat(traineeStorage.get(1L).getUsername()).isEqualTo("Raul.Londono");
        assertThat(traineeStorage.get(2L).getFirstName()).isEqualTo("Alejandra");

        assertThat(trainerStorage).hasSize(1);
        assertThat(trainerStorage.get(1L).getLastName()).isEqualTo("Gonzalez");
        assertThat(trainerStorage.get(1L).getUsername()).isEqualTo("Carlos.Gonzalez");

        assertThat(trainingStorage).hasSize(2);
        Training t1 = trainingStorage.get(1L);
        assertThat(t1).isNotNull();
        assertThat(t1.getTrainingName()).isEqualTo("Sesión Cardio");
        assertThat(t1.getTraineeId().getUserId()).isEqualTo(1L);
        assertThat(t1.getTrainerId().getUserId()).isEqualTo(1L);

        assertThat(t1.getTrainingType().toString()).contains("CARDIO");
        assertThat(t1.getTrainingDuration()).isEqualTo(60.0);
        assertThat(t1.getTrainingDate()).isNotNull();
    }

    @Test
    void afterSingletonsInstantiated_shouldNotThrow_whenFilesMissing_andLeaveStoragesEmpty() {
        when(env.getProperty("storage.trainee.file")).thenReturn("classpath:nope/trainees.json");
        when(env.getProperty("storage.trainer.file")).thenReturn("classpath:nope/trainers.json");
        when(env.getProperty("storage.training.file")).thenReturn("classpath:nope/trainings.csv");

        DataInitializer initializer = newInitializer();

        initializer.afterSingletonsInstantiated();

        assertThat(traineeStorage).isEmpty();
        assertThat(trainerStorage).isEmpty();
        assertThat(trainingStorage).isEmpty();
    }
}
