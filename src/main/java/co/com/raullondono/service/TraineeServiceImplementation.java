package co.com.raullondono.service;

import co.com.raullondono.dao.TraineeDAO;
import co.com.raullondono.domain.Trainee;
import co.com.raullondono.util.NameValidator;
import co.com.raullondono.util.PasswordGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.Objects;

@Service
public class TraineeServiceImplementation implements TraineeService {

    private static final Logger log = LoggerFactory.getLogger(TraineeServiceImplementation.class);

    @Autowired
    private TraineeDAO traineeDAO;
    @Autowired
    private UsernameService usernameService;
    @Autowired
    private PasswordGenerator passwordGenerator;

    public TraineeServiceImplementation() {

    }

    @Override
    public Trainee createTrainee(String firstName,
                                 String lastName,
                                 LocalDate dateOfBirth,
                                 String address,
                                 String phoneNumber) {
        var t = new Trainee();

        firstName = StringUtils.capitalize(firstName.toLowerCase());
        NameValidator.validateAndFormatSingleWord(firstName, "firstName");
        t.setFirstName(firstName);

        lastName = StringUtils.capitalize(lastName.toLowerCase());
        NameValidator.validateAndFormatSingleWord(lastName, "lastName");
        t.setLastName(lastName);

        t.setUsername(usernameService.generateUsername(firstName, lastName));
        t.setPassword(passwordGenerator.generatePassword());
        t.setIsActive(true);
        t.setDateOfBirth(dateOfBirth);

        var created = traineeDAO.createTrainee(t);
        log.info("Trainee created id={}, username={}", created.getUsername(), created.getUsername());
        return created;
    }

    @Override
    public Trainee updateTrainee(Trainee trainee) {
        Objects.requireNonNull(trainee, "trainee");
        var updated = traineeDAO.updateTrainee(trainee);
        log.info("Trainee updated id={}", updated.getUserId());
        return updated;
    }

    @Override
    public Trainee deleteTrainee(Long traineeId) {
        Objects.requireNonNull(traineeId, "traineeId");
        var removed = traineeDAO.deleteTrainee(traineeId);
        log.warn("Trainee deleted id={}", traineeId);
        return removed;
    }

    @Override
    public Trainee selectTrainee(Long traineeId) {
        Objects.requireNonNull(traineeId, "traineeId");
        return traineeDAO.selectTrainee(traineeId);
    }

    public void setTraineeDAO(TraineeDAO traineeDAO) {
        this.traineeDAO = traineeDAO;
    }

    public void setUsernameService(UsernameService usernameService) {
        this.usernameService = usernameService;
    }

    public void setPasswordGenerator(PasswordGenerator passwordGenerator) {
        this.passwordGenerator = passwordGenerator;
    }

}
