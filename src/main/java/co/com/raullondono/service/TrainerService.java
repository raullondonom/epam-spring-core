package co.com.raullondono.service;

import co.com.raullondono.domain.Trainer;
import co.com.raullondono.domain.TrainingType;

public interface TrainerService {

    Trainer createTrainer(Long userId,
                          String firstName,
                          String lastName,
                          String username,
                          String password,
                          Boolean isActive,
                          TrainingType specialization);

    Trainer updateTrainer(Trainer trainer);

    Trainer selectTrainer(Long trainerId);

}
