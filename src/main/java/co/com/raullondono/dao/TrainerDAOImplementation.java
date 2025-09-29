package co.com.raullondono.dao;

import co.com.raullondono.domain.Trainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Objects;

@Repository
public class TrainerDAOImplementation implements TrainerDAO {

    private static final Logger log = LoggerFactory.getLogger(TrainerDAOImplementation.class);

    private Map<Long, Trainer> storage;

    @Autowired
    public void setStorage(@Qualifier("trainerStorage") Map<Long, Trainer> storage) {
        this.storage = storage;
    }

    @Override
    public Trainer createTrainer(Trainer trainer) {
        Objects.requireNonNull(trainer);
        Objects.requireNonNull(trainer.getUserId());
        storage.put(trainer.getUserId(), trainer);
        log.debug("Trainer created id={}", trainer.getUserId());
        return trainer;
    }

    @Override
    public Trainer updateTrainer(Trainer trainer) {
        Objects.requireNonNull(trainer);
        Objects.requireNonNull(trainer.getUserId());
        storage.put(trainer.getUserId(), trainer);
        log.debug("Trainer updated id={}", trainer.getUserId());
        return trainer;
    }

    @Override
    public Trainer selectTrainer(Long traineeId) {
        Objects.requireNonNull(traineeId);
        return storage.get(traineeId);
    }

    @Override
    public boolean existsByUsername(String username) {
        return storage.values().stream().anyMatch(t -> username.equals(t.getUsername()));
    }

}
