package co.com.raullondono.service;

import co.com.raullondono.domain.Trainer;

public interface TrainerService {

    Trainer createTrainer(String firstName,
                          String lastName,
                          String specialization);

    Trainer updateTrainer(Trainer trainer);

    Trainer selectTrainer(Long trainerId);

}
