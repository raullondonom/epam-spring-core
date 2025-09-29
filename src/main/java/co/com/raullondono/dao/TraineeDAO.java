package co.com.raullondono.dao;

import co.com.raullondono.domain.Trainee;

public interface TraineeDAO {

    Trainee createTrainee(Trainee trainee);

    Trainee updateTrainee(Trainee trainee);

    Trainee deleteTrainee(Trainee trainee);

    Trainee selectTrainee(Long traineeId);

    boolean existsByUsername(String username);

}
