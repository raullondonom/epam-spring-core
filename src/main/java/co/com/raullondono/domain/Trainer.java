package co.com.raullondono.domain;

public class Trainer extends User {

    private TrainingType specialization;

    public Trainer() {

    }

    public TrainingType getSpecialization() {
        return specialization;
    }

    public void setSpecialization(TrainingType specialization) {
        this.specialization = specialization;
    }

}
