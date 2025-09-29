package co.com.raullondono.service;

import co.com.raullondono.dao.TrainingDAO;
import co.com.raullondono.domain.Trainee;
import co.com.raullondono.domain.Trainer;
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

    private static final Logger log = LoggerFactory.getLogger(TrainingServiceImplementation.class);

    @Autowired
    private TrainingDAO trainingDAO;
    @Autowired
    private TrainerService trainerService;
    @Autowired
    private TraineeService traineeService;

    @Override
    public Training createTraining(Long trainerId,
                                   Long traineeId,
                                   String trainingName,
                                   TrainingType trainingType,
                                   LocalDateTime trainingDate,
                                   Double trainingDuration) {
        Trainee trainee = traineeService.selectTrainee(traineeId);
        Trainer trainer = trainerService.selectTrainer(trainerId);

        Objects.requireNonNull(trainee, "trainee");
        Objects.requireNonNull(trainer, "trainer");

        var tr = new Training();
        tr.setTraineeId(trainee);
        tr.setTrainerId(trainer);
        tr.setTrainingName(trainingName);
        tr.setTrainingType(trainingType);
        tr.setTrainingDate(trainingDate);
        tr.setTrainingDuration(trainingDuration);

        var created = trainingDAO.createTraining(tr);
        log.info("Training created id={}, name={}", created.getTrainingId(), created.getTrainingName());
        return created;
    }

    @Override
    public Training selectTraining(Long trainingId) {
        Objects.requireNonNull(trainingId, "trainingId");
        return trainingDAO.selectTraining(trainingId);
    }

}
