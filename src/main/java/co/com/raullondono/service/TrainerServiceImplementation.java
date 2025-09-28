package co.com.raullondono.service;

import co.com.raullondono.domain.Trainer;
import co.com.raullondono.domain.TrainingType;
import org.springframework.stereotype.Service;

@Service
public class TrainerServiceImplementation implements TrainerService {

    @Override
    public Trainer createTrainer(Long userId,
                                 String firstName,
                                 String lastName,
                                 String username,
                                 String password,
                                 Boolean isActive,
                                 TrainingType specialization) {
        return null;
    }

    @Override
    public Trainer updateTrainer(Trainer trainer) {
        return null;
    }

    @Override
    public Trainer selectTrainer(Long traineeId) {
        return null;
    }

}
