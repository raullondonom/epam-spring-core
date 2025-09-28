package co.com.raullondono.service;

import co.com.raullondono.dao.TrainerDAO;
import co.com.raullondono.domain.Trainer;
import co.com.raullondono.domain.TrainingType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class TrainerServiceImplementation implements TrainerService {

    private static final Logger log = LoggerFactory.getLogger(TrainerServiceImplementation.class);

    @Autowired
    private TrainerDAO trainerDAO;

    @Override
    public Trainer createTrainer(Long userId,
                                 String firstName,
                                 String lastName,
                                 String username,
                                 String password,
                                 Boolean isActive,
                                 TrainingType specialization) {
        Objects.requireNonNull(userId, "userId");
        var t = new Trainer();
        t.setUserId(userId);
        t.setFirstName(firstName);
        t.setLastName(lastName);
        t.setUsername(username);
        t.setPassword(password);
        t.setSpecialization(specialization);

        var created = trainerDAO.createTrainer(t);
        log.info("Trainee created id={}, username={}", created.getUsername(), created.getUsername());
        return created;
    }

    @Override
    public Trainer updateTrainer(Trainer trainer) {
        Objects.requireNonNull(trainer, "trainee");
        var updated = trainerDAO.updateTrainer(trainer);
        log.info("Trainee updated id={}", updated.getUserId());
        return updated;
    }

    @Override
    public Trainer selectTrainer(Long trainerId) {
        Objects.requireNonNull(trainerId, "traineeId");
        return trainerDAO.selectTrainer(trainerId);
    }

}
