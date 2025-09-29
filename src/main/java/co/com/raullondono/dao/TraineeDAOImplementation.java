package co.com.raullondono.dao;

import co.com.raullondono.domain.Trainee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Objects;

@Repository
public class TraineeDAOImplementation implements TraineeDAO {

    private static final Logger log = LoggerFactory.getLogger(TraineeDAOImplementation.class);
    private static Long actualId = 1L;
    private Map<Long, Trainee> storage;

    @Autowired
    public void setStorage(@Qualifier("traineeStorage") Map<Long, Trainee> storage) {
        this.storage = storage;
    }

    @Override
    public Trainee createTrainee(Trainee trainee) {
        Objects.requireNonNull(trainee.getUserId(), "id cannot be null");
        trainee.setUserId(actualId);
        storage.put(actualId, trainee);
        actualId++;
        log.debug("Trainee saved id={}", trainee.getUserId());
        return trainee;
    }

    @Override
    public Trainee updateTrainee(Trainee trainee) {
        Objects.requireNonNull(trainee);
        Objects.requireNonNull(trainee.getUserId());
        storage.put(trainee.getUserId(), trainee);
        log.debug("Trainee updated id={}", trainee.getUserId());
        return trainee;
    }

    @Override
    public Trainee deleteTrainee(Long userId) {
        Objects.requireNonNull(userId);
        Trainee removed = storage.remove(userId);
        log.warn("Trainee deleted id={}", userId);
        return removed;
    }

    @Override
    public Trainee selectTrainee(Long traineeId) {
        Objects.requireNonNull(traineeId);
        return storage.get(traineeId);
    }

    @Override
    public boolean existsByUsername(String username) {
        return storage.values().stream().anyMatch(t -> username.equals(t.getUsername()));
    }

}
