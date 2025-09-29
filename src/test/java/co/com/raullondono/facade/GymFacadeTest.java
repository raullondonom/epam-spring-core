package co.com.raullondono.facade;

import co.com.raullondono.domain.Trainee;
import co.com.raullondono.domain.Training;
import co.com.raullondono.domain.TrainingType;
import co.com.raullondono.service.TraineeService;
import co.com.raullondono.service.TrainerService;
import co.com.raullondono.service.TrainingService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GymFacadeTest {

    @Mock
    TraineeService traineeService;
    @Mock
    TrainerService trainerService;
    @Mock
    TrainingService trainingService;

    @InjectMocks
    GymFacadeImplementation facade;

    @Nested
    @DisplayName("createTrainee")
    class CreateTraineeTests {

        @Test
        @DisplayName("Delegates to TraineeService and returns created trainee")
        void createTrainee_ok() {
            String first = "Ana";
            String last = "García";
            LocalDate dob = LocalDate.of(1995, 5, 10);
            String address = "Calle 123";
            String phone = "3001234567";

            Trainee traineeMock = mock(Trainee.class);
            when(traineeMock.getUserId()).thenReturn(42L);
            when(traineeMock.getUsername()).thenReturn("agarcia");

            when(traineeService.createTrainee(first, last, dob, address, phone))
                    .thenReturn(traineeMock);

            Trainee result = facade.createTrainee(first, last, dob, address, phone);

            assertThat(result).isSameAs(traineeMock);
            verify(traineeService).createTrainee(first, last, dob, address, phone);
            verifyNoMoreInteractions(traineeService, trainerService, trainingService);
        }
    }

    @Nested
    @DisplayName("selectTraining")
    class SelectTrainingTests {

        @Test
        @DisplayName("Throws NPE when trainingId is null (Objects.requireNonNull)")
        void selectTraining_nullId_throws() {
            assertThatThrownBy(() -> facade.selectTraining(null))
                    .isInstanceOf(NullPointerException.class);
            verifyNoInteractions(trainingService);
        }

        @Test
        @DisplayName("Delegates to TrainingService and returns training")
        void selectTraining_ok() {
            Long trainingId = 99L;
            Training trMock = mock(Training.class);

            when(trainingService.selectTraining(trainingId)).thenReturn(trMock);

            Training res = facade.selectTraining(trainingId);

            assertThat(res).isSameAs(trMock);
            verify(trainingService).selectTraining(trainingId);
            verifyNoMoreInteractions(trainingService);
            verifyNoInteractions(traineeService, trainerService);
        }
    }

    @Nested
    @DisplayName("createTraining (assumed signature based on code snippet)")
    class CreateTrainingTests {

        @Test
        @DisplayName("Delegates to TrainingService with all parameters and returns created training")
        void createTraining_ok() {
            Long traineeId = 1L;
            Long trainerId = 2L;
            String name = "Morning Cardio";
            String type = TrainingType.CARDIO.getName();
            LocalDateTime date = LocalDateTime.of(2025, 1, 1, 10, 0);
            Double duration = 60.0;

            Training trMock = mock(Training.class);
            when(trMock.getTrainingId()).thenReturn(777L);

            when(trainingService.createTraining(eq(traineeId), eq(trainerId), eq(name), eq(type), eq(date), eq(duration)))
                    .thenReturn(trMock);

            Training res = facade.createTraining(traineeId, trainerId, name, type, date, duration);

            assertThat(res).isSameAs(trMock);
            verify(trainingService).createTraining(traineeId, trainerId, name, type, date, duration);
            verifyNoMoreInteractions(trainingService);
            verifyNoInteractions(traineeService, trainerService);
        }
    }

    @Nested
    @DisplayName("updateTrainee (if present)")
    class UpdateTraineeTests {

        @Test
        @DisplayName("Throws NPE when userId is null")
        void updateTrainee_nullId_throws() {
            assertThatThrownBy(() -> facade.updateTrainee(null, "3000000000", "Nueva dir"))
                    .isInstanceOf(NullPointerException.class);
            verifyNoInteractions(traineeService);
        }

        @Test
        @DisplayName("Delegates to TraineeService and returns updated trainee")
        void updateTrainee_ok() {
            Long userId = 10L;
            String phone = "3111111111";
            String address = "Calle nueva 456";

            Trainee updated = mock(Trainee.class);
            when(traineeService.updateTrainee(updated)).thenReturn(updated);
            when(traineeService.selectTrainee(userId)).thenReturn(updated);

            Trainee res = facade.updateTrainee(userId, phone, address);

            assertThat(res).isSameAs(updated);
            verify(traineeService).updateTrainee(updated);
            verifyNoMoreInteractions(traineeService);
            verifyNoInteractions(trainerService, trainingService);
        }
    }
}
