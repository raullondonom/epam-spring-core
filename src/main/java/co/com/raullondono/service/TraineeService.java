package co.com.raullondono.service;

import co.com.raullondono.domain.Trainee;

import java.time.LocalDate;

public interface TraineeService {

    Trainee createTrainee(Long userId,
                          String firstName,
                          String lastName,
                          LocalDate dateOfBirth,
                          String address,
                          String phoneNumber);

    Trainee updateTrainee(Trainee trainee);

    Trainee deleteTrainee(Trainee trainee);

    Trainee selectTrainee(Long traineeId);

}
