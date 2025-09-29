package co.com.raullondono.service;

import co.com.raullondono.dao.TrainerDAO;
import co.com.raullondono.domain.Trainer;
import co.com.raullondono.domain.TrainingType;
import co.com.raullondono.util.NameValidator;
import co.com.raullondono.util.PasswordGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Service
public class TrainerServiceImplementation implements TrainerService {

    private static final Logger log = LoggerFactory.getLogger(TrainerServiceImplementation.class);

    @Autowired
    private TrainerDAO trainerDAO;
    @Autowired
    private UsernameService usernameService;
    @Autowired
    PasswordGenerator passwordGenerator;

    public TrainerServiceImplementation() {

    }

    @Override
    public Trainer createTrainer(String firstName,
                                 String lastName,
                                 String specialization) {
        var t = new Trainer();

        firstName = StringUtils.capitalize(firstName.toLowerCase());
        NameValidator.validateAndFormatSingleWord(firstName, "firstName");
        t.setFirstName(firstName);

        lastName = StringUtils.capitalize(lastName.toLowerCase());
        NameValidator.validateAndFormatSingleWord(lastName, "lastName");
        t.setLastName(lastName);

        t.setUsername(usernameService.generateUsername(firstName, lastName));
        t.setPassword(passwordGenerator.generatePassword());
        t.setSpecialization(TrainingType.valueOf(specialization));

        var created = trainerDAO.createTrainer(t);
        log.info("Trainer created id={}, username={}", created.getUsername(), created.getUsername());
        return created;
    }

    @Override
    public Trainer updateTrainer(Trainer trainer) {
        Objects.requireNonNull(trainer, "trainer");
        var updated = trainerDAO.updateTrainer(trainer);
        log.info("Trainer updated id={}", updated.getUserId());
        return updated;
    }

    @Override
    public Trainer selectTrainer(Long trainerId) {
        Objects.requireNonNull(trainerId, "trainerId");
        return trainerDAO.selectTrainer(trainerId);
    }

    public void setTrainerDAO(TrainerDAO trainerDAO) {
        this.trainerDAO = trainerDAO;
    }

    public void setUsernameService(UsernameService usernameService) {
        this.usernameService = usernameService;
    }

    public void setPasswordGenerator(PasswordGenerator passwordGenerator) {
        this.passwordGenerator = passwordGenerator;
    }

}
