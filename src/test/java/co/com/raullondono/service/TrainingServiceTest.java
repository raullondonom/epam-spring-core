package co.com.raullondono.service;

import co.com.raullondono.dao.TrainingDAO;
import co.com.raullondono.domain.Trainee;
import co.com.raullondono.domain.Trainer;
import co.com.raullondono.domain.Training;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class TrainingServiceTest {

    private TrainingDAO trainingDAO;
    private TrainerService trainerService;
    private TraineeService traineeService;
    private TrainingServiceImplementation service;

    @BeforeEach
    void setUp() {
        trainingDAO = mock(TrainingDAO.class);
        trainerService = mock(TrainerService.class);
        traineeService = mock(TraineeService.class);
        service = new TrainingServiceImplementation();
        service.setTrainingDAO(trainingDAO);
        service.setTrainerService(trainerService);
        service.setTraineeService(traineeService);
    }

    @Test
    void create_shouldBuildAndPersistTraining() {
        LocalDateTime now = LocalDateTime.now();

        Training persisted = new Training();
        persisted.setTrainingId(999L);
        when(trainingDAO.createTraining(any(Training.class))).thenReturn(persisted);
        when(traineeService.selectTrainee(2L)).thenReturn(new Trainee());
        when(trainerService.selectTrainer(1L)).thenReturn(new Trainer());

        Training result = service.createTraining(
                1L, 2L, "Initial Session",
                "CARDIO",
                LocalDateTime.now(),
                65.0
        );

        assertThat(result.getTrainingId()).isEqualTo(999L);
        verify(trainingDAO).createTraining(any(Training.class));
    }

    @Test
    void select_shouldReturnTraining() {
        Training t = new Training();
        t.setTrainingId(999L);
        when(trainingDAO.selectTraining(999L)).thenReturn(t);

        Training selected = service.selectTraining(999L);

        assertThat(selected).isNotNull();
        assertThat(selected.getTrainingId()).isEqualTo(999L);
    }
}
