package co.com.raullondono.config;

import co.com.raullondono.domain.Trainee;
import co.com.raullondono.domain.Trainer;
import co.com.raullondono.domain.Training;
import co.com.raullondono.domain.TrainingType;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Component
public class DataInitializer implements SmartInitializingSingleton {

    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

    private final Environment env;
    private final ObjectMapper mapper;

    private final Map<Long, Trainee> traineeStorage;
    private final Map<Long, Trainer> trainerStorage;
    private final Map<Long, Training> trainingStorage;

    public DataInitializer(
            Environment env,
            @Qualifier("traineeStorage") Map<Long, Trainee> traineeStorage,
            @Qualifier("trainerStorage") Map<Long, Trainer> trainerStorage,
            @Qualifier("trainingStorage") Map<Long, Training> trainingStorage
    ) {
        this.env = env;
        this.mapper = new ObjectMapper();
        this.mapper.findAndRegisterModules();
        this.traineeStorage = traineeStorage;
        this.trainerStorage = trainerStorage;
        this.trainingStorage = trainingStorage;
    }

    @Override
    public void afterSingletonsInstantiated() {
        preloadTrainees();
        preloadTrainers();
        preloadTrainings();
    }

    private void preloadTrainees() {
        String path = env.getProperty("storage.trainee.file");
        if (path == null) return;

        try (InputStream is = getResource(path)) {
            if (is == null) {
                log.warn("Trainees file not found: {}", path);
                return;
            }
            List<Trainee> rows = mapper.readValue(is, new TypeReference<List<Trainee>>() {
            });
            rows.forEach(t -> traineeStorage.put(t.getUserId(), t));
            log.info("Trainees preloaded: {}", traineeStorage.size());
        } catch (Exception e) {
            log.error("Error preloading trainees from {}: {}", path, e.getMessage());
        }
    }

    private void preloadTrainers() {
        String path = env.getProperty("storage.trainer.file");
        if (path == null) return;

        try (InputStream is = getResource(path)) {
            if (is == null) {
                log.warn("Trainers file not found: {}", path);
                return;
            }
            List<Trainer> rows = mapper.readValue(is, new TypeReference<List<Trainer>>() {
            });
            rows.forEach(t -> trainerStorage.put(t.getUserId(), t));
            log.info("Trainers preloaded: {}", trainerStorage.size());
        } catch (Exception e) {
            log.error("Error preloading trainers from {}: {}", path, e.getMessage());
        }
    }

    private void preloadTrainings() {
        String path = env.getProperty("storage.training.file");
        if (path == null) return;

        try (InputStream is = getResource(path)) {
            if (is == null) {
                log.warn("Trainings file not found: {}", path);
                return;
            }

            List<Map<String, Object>> rows = mapper.readValue(is, new TypeReference<List<Map<String, Object>>>() {
            });
            for (Map<String, Object> r : rows) {
                Training tr = new Training();
                tr.setTrainingId(((Number) r.get("trainingId")).longValue());

                Long traineeId = ((Number) r.get("traineeId")).longValue();
                Long trainerId = ((Number) r.get("trainerId")).longValue();

                Trainee trainee = traineeStorage.get(traineeId);
                Trainer trainer = trainerStorage.get(trainerId);
                tr.setTraineeId(trainee);
                tr.setTrainerId(trainer);

                tr.setTrainingDate(LocalDateTime.parse(r.get("trainingDate").toString()));
                TrainingType type = TrainingType.valueOf(r.get("trainingType").toString());
                tr.setTrainingType(type);

                trainingStorage.put(tr.getTrainingId(), tr);
            }
            log.info("Trainings preloaded: {}", trainingStorage.size());
        } catch (Exception e) {
            log.error("Error preloading trainings from {}: {}", path, e.getMessage());
        }
    }

    private InputStream getResource(String path) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
    }
}
