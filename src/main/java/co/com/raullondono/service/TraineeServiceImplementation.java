package co.com.raullondono.service;

import co.com.raullondono.dao.TraineeDAO;
import co.com.raullondono.domain.Trainee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Objects;

@Service
public class TraineeServiceImplementation implements TraineeService {

    private static final Logger log = LoggerFactory.getLogger(TraineeServiceImplementation.class);

    @Autowired
    private TraineeDAO traineeDAO;

    @Override
    public Trainee createTrainee(Long userId,
                                 String firstName,
                                 String lastName,
                                 String username,
                                 String password,
                                 Boolean isActive,
                                 LocalDate dateOfBirth,
                                 String address,
                                 String phoneNumber) {
        Objects.requireNonNull(userId, "userId");
        var t = new Trainee();
        t.setUserId(userId);
        t.setFirstName(firstName);
        t.setLastName(lastName);
        t.setUsername(username);
        t.setPassword(password);
        t.setDateOfBirth(dateOfBirth);

        var created = traineeDAO.createTrainee(t);
        log.info("Trainee created id={}, username={}", created.getUsername(), created.getUsername());
        return created;
    }

    @Override
    public Trainee updateTrainee(Trainee trainee) {
        Objects.requireNonNull(trainee, "trainee");
        var updated = traineeDAO.updateTrainee(trainee);
        log.info("Trainee updated id={}", updated.getUserId());
        return updated;
    }

    @Override
    public Trainee deleteTrainee(Trainee trainee) {
        Objects.requireNonNull(trainee, "trainee");
        var removed = traineeDAO.deleteTrainee(trainee);
        log.warn("Trainee deleted id={}", trainee.getUserId());
        return removed;
    }

    @Override
    public Trainee selectTrainee(Long traineeId) {
        Objects.requireNonNull(traineeId, "traineeId");
        return traineeDAO.selectTrainee(traineeId);
    }

}
