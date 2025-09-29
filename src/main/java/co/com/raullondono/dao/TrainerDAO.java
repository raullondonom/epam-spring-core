package co.com.raullondono.dao;

import co.com.raullondono.domain.Trainer;

public interface TrainerDAO {

    Trainer createTrainer(Trainer trainer);

    Trainer updateTrainer(Trainer trainer);

    Trainer selectTrainer(Long traineeId);

    boolean existsByUsername(String username);
}
