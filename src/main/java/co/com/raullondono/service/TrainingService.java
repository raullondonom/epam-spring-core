package co.com.raullondono.service;

import co.com.raullondono.domain.Training;
import co.com.raullondono.domain.TrainingType;

import java.time.LocalDateTime;

public interface TrainingService {

    Training createTraining(Long trainingId,
                            Long trainerId,
                            Long traineeId,
                            String trainingName,
                            TrainingType trainingType,
                            LocalDateTime trainingDate,
                            Double trainingDuration);

    Training selectTraining(Long trainingId);

}
