package co.com.raullondono.domain;

public class Trainer extends User {

    private TrainingType specialization;

    public Trainer() {

    }

    public Trainer(User user, TrainingType specialization) {
        super(user.getUserId(),
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                user.getPassword(),
                user.getIsActive());
        this.specialization = specialization;
    }

    public Trainer(Long userId,
                   String firstName,
                   String lastName,
                   String username,
                   String password,
                   Boolean isActive,
                   TrainingType specialization) {
        super(userId, firstName, lastName, username, password, isActive);
        this.specialization = specialization;
    }

    public TrainingType getSpecialization() {
        return specialization;
    }

    public void setSpecialization(TrainingType specialization) {
        this.specialization = specialization;
    }

}
