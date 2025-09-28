package co.com.raullondono.service;

import co.com.raullondono.domain.Training;
import co.com.raullondono.domain.TrainingType;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TrainingServiceImplementation implements TrainingService {

    @Override
    public Training createTraining(Long trainingId,
                                   Long trainerId,
                                   Long traineeId,
                                   String trainingName,
                                   TrainingType trainingType,
                                   LocalDateTime trainingDate,
                                   Double trainingDuration) {
        return null;
    }

    @Override
    public Training selectTraining(Long trainingId) {
        return null;
    }
}
