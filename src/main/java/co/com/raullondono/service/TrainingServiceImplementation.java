package co.com.raullondono.service;

import co.com.raullondono.dao.TrainingDAO;
import co.com.raullondono.domain.Training;
import co.com.raullondono.domain.TrainingType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class TrainingServiceImplementation implements TrainingService {

    private static final Logger log = LoggerFactory.getLogger(TrainerServiceImplementation.class);

    @Autowired
    private TrainingDAO trainingDAO;

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
        Objects.requireNonNull(trainingId, "traineeId");
        return trainingDAO.selectTraining(trainingId);
    }

}
