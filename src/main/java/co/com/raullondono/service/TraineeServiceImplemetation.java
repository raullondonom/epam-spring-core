package co.com.raullondono.service;

import co.com.raullondono.domain.Trainee;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TraineeServiceImplemetation implements TraineeService {

    @Override
    public Trainee createTrainee(Long userId,
                                 String firstName,
                                 String lastName,
                                 String username,
                                 String password,
                                 Boolean isActive,
                                 LocalDate dateOfBirth,
                                 String address,
                                 String phoneNumber) {
        return null;
    }

    @Override
    public Trainee updateTrainee(Trainee trainee) {
        return null;
    }

    @Override
    public Trainee deleteTrainee(Trainee trainee) {
        return null;
    }

    @Override
    public Trainee selectTrainee(Long traineeId) {
        return null;
    }

}
