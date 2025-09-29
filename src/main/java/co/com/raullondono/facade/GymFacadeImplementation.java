package co.com.raullondono.facade;

import co.com.raullondono.domain.Trainee;
import co.com.raullondono.domain.Trainer;
import co.com.raullondono.domain.Training;
import co.com.raullondono.domain.TrainingType;
import co.com.raullondono.service.TraineeService;
import co.com.raullondono.service.TrainerService;
import co.com.raullondono.service.TrainingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class GymFacadeImplementation implements GymFacade {

    private static final Logger log = LoggerFactory.getLogger(GymFacadeImplementation.class);

    private final TraineeService traineeService;
    private final TrainerService trainerService;
    private final TrainingService trainingService;

    public GymFacadeImplementation(TraineeService traineeService,
                         TrainerService trainerService,
                         TrainingService trainingService) {
        this.traineeService = Objects.requireNonNull(traineeService);
        this.trainerService = Objects.requireNonNull(trainerService);
        this.trainingService = Objects.requireNonNull(trainingService);
    }

    @Override
    public Trainee createTrainee(String firstName,
                                 String lastName,
                                 LocalDate dateOfBirth,
                                 String address,
                                 String phoneNumber) {
        log.info("Facade: creating trainee (firstName='{}', lastName='{}')", firstName, lastName);
        Trainee t = traineeService.createTrainee(firstName, lastName, dateOfBirth, address, phoneNumber);
        log.info("Facade: trainee created id={} username={}", t.getUserId(), t.getUsername());
        return t;
    }

    @Override
    public Trainee updateTrainee(Long userId, String phoneNumber, String address) {
        Objects.requireNonNull(userId, "userId");
        log.info("Facade: updating trainee id={} (phone='{}', address='{}')", userId, phoneNumber, address);
        Trainee t = traineeService.selectTrainee(userId);
        Objects.requireNonNull(t, "trainee");
        t.setPhoneNumber(phoneNumber);
        t.setAddress(address);
        t = traineeService.updateTrainee(t);
        log.info("Facade: trainee updated id={}", t.getUserId());
        return t;
    }

    @Override
    public Trainee deleteTrainee(Long userId) {
        Objects.requireNonNull(userId, "userId");
        log.info("Facade: deleting trainee id={}", userId);
        Trainee removed = traineeService.deleteTrainee(userId);
        if (removed == null) {
            log.warn("Facade: trainee id={} not found to delete", userId);
        } else {
            log.info("Facade: trainee id={} deleted", userId);
        }
        return removed;
    }

    @Override
    public Trainee selectTrainee(Long userId) {
        Objects.requireNonNull(userId, "userId");
        log.info("Facade: selecting trainee id={}", userId);
        return traineeService.selectTrainee(userId);
    }

    @Override
    public Trainer createTrainer(String firstName, String lastName, String specialization) {
        log.info("Facade: creating trainer (firstName='{}', lastName='{}')", firstName, lastName);
        Trainer t = trainerService.createTrainer(firstName, lastName, specialization);
        log.info("Facade: trainer created id={} username={}", t.getUserId(), t.getUsername());
        return t;
    }

    @Override
    public Trainer updateTrainer(Long userId, String specialization) {
        Objects.requireNonNull(userId, "userId");
        log.info("Facade: updating trainer id={} (specialization='{}')", userId, specialization);
        Trainer t = trainerService.selectTrainer(userId);
        Objects.requireNonNull(t, "trainer");
        t.setSpecialization(TrainingType.valueOf(specialization));
        t = trainerService.updateTrainer(t);
        log.info("Facade: trainer updated id={}", t.getUserId());
        return t;
    }

    @Override
    public Trainer selectTrainer(Long userId) {
        Objects.requireNonNull(userId, "userId");
        log.info("Facade: selecting trainer id={}", userId);
        return trainerService.selectTrainer(userId);
    }

    @Override
    public Training createTraining(Long traineeId,
                                   Long trainerId,
                                   String trainingName,
                                   String trainingType,
                                   LocalDateTime trainingDate,
                                   Double durationMinutes) {

        Objects.requireNonNull(traineeId, "traineeId");
        Objects.requireNonNull(trainerId, "trainerId");
        Objects.requireNonNull(trainingType, "trainingType");
        Objects.requireNonNull(trainingName, "trainingName");
        Objects.requireNonNull(durationMinutes, "durationMinutes");
        Objects.requireNonNull(trainingDate, "trainingDate");

        log.info("Facade: creating training (traineeId={}, trainerId={}, name='{}', type='{}', date={}, duration={})",
                traineeId, trainerId, trainingName, trainingType, trainingDate, durationMinutes);

        Training tr = trainingService.createTraining(traineeId,
                trainerId,
                trainingName,
                trainingType,
                trainingDate,
                durationMinutes);

        log.info("Facade: training created id={} for traineeId={} trainerId={}", tr.getTrainingId(), traineeId, trainerId);
        return tr;
    }

    @Override
    public Training selectTraining(Long trainingId) {
        Objects.requireNonNull(trainingId, "trainingId");
        log.info("Facade: selecting training id={}", trainingId);
        return trainingService.selectTraining(trainingId);
    }
}
