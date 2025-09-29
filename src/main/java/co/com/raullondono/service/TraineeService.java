package co.com.raullondono.service;

import co.com.raullondono.domain.Trainee;

import java.time.LocalDate;

public interface TraineeService {

    Trainee createTrainee(String firstName,
                          String lastName,
                          LocalDate dateOfBirth,
                          String address,
                          String phoneNumber);

    Trainee updateTrainee(Trainee trainee);

    Trainee deleteTrainee(Long traineeId);

    Trainee selectTrainee(Long traineeId);

}
