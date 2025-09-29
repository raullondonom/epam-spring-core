package co.com.raullondono.facade;

import co.com.raullondono.domain.Trainee;
import co.com.raullondono.domain.Trainer;
import co.com.raullondono.domain.Training;
import co.com.raullondono.domain.TrainingType;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface GymFacade {

    // Trainee
    Trainee createTrainee(String firstName, String lastName, LocalDate dateOfBirth, String address, String phoneNumber);
    Trainee updateTrainee(Long userId, String phoneNumber, String address);
    Trainee deleteTrainee(Long userId);
    Trainee selectTrainee(Long userId);

    // Trainer
    Trainer createTrainer(String firstName, String lastName, String specialization);
    Trainer updateTrainer(Long userId, String specialization);
    Trainer selectTrainer(Long userId);

    // Training
    Training createTraining(Long traineeId,
                            Long trainerId,
                            String trainingName,
                            String trainingType,
                            LocalDateTime trainingDate,
                            Double durationMinutes);

    Training selectTraining(Long trainingId);
}
