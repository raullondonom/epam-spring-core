package co.com.raullondono.service;

import co.com.raullondono.domain.Training;

import java.time.LocalDateTime;

public interface TrainingService {

    Training createTraining(Long trainerId,
                            Long traineeId,
                            String trainingName,
                            String trainingType,
                            LocalDateTime trainingDate,
                            Double trainingDuration);

    Training selectTraining(Long trainingId);

}
