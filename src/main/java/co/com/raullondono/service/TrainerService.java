package co.com.raullondono.service;

import co.com.raullondono.domain.Trainer;
import co.com.raullondono.domain.TrainingType;

public interface TrainerService {

    Trainer createTrainer(String firstName,
                          String lastName,
                          TrainingType specialization);

    Trainer updateTrainer(Trainer trainer);

    Trainer selectTrainer(Long trainerId);

}
