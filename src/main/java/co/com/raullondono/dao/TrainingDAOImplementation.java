package co.com.raullondono.dao;

import co.com.raullondono.domain.Training;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Objects;

@Repository
public class TrainingDAOImplementation implements TrainingDAO {

    private static final Logger log = LoggerFactory.getLogger(TrainingDAOImplementation.class);

    private Map<Long, Training> storage;

    @Autowired
    public void setStorage(@Qualifier("trainingStorage") Map<Long, Training> storage) {
        this.storage = storage;
    }

    @Override
    public Training createTraining(Training training) {
        Objects.requireNonNull(training);
        Objects.requireNonNull(training.getTrainingId());
        storage.put(training.getTrainingId(), training);
        log.debug("Training creado id={}", training.getTrainingId());
        return training;
    }

    @Override
    public Training selectTraining(Long trainingId) {
        Objects.requireNonNull(trainingId);
        return storage.get(trainingId);
    }

}
