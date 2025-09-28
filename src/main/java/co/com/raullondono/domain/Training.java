package co.com.raullondono.domain;

import java.time.LocalDateTime;

public class Training {

    private Long trainingId;
    private Trainer trainerId;
    private Trainee traineeId;
    private String trainingName;
    private TrainingType trainingType;
    private LocalDateTime trainingDate;
    private Double trainingDuration;

    public Training() {

    }

    public Training(Long trainingId,
                    Trainer trainerId,
                    Trainee traineeId,
                    String trainingName,
                    TrainingType trainingType,
                    LocalDateTime trainingDate,
                    Double trainingDuration) {
        this.trainingId = trainingId;
        this.trainerId = trainerId;
        this.traineeId = traineeId;
        this.trainingName = trainingName;
        this.trainingType = trainingType;
        this.trainingDate = trainingDate;
        this.trainingDuration = trainingDuration;
    }

    public Long getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(Long trainingId) {
        this.trainingId = trainingId;
    }

    public Trainer getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(Trainer trainerId) {
        this.trainerId = trainerId;
    }

    public Trainee getTraineeId() {
        return traineeId;
    }

    public void setTraineeId(Trainee traineeId) {
        this.traineeId = traineeId;
    }

    public String getTrainingName() {
        return trainingName;
    }

    public void setTrainingName(String trainingName) {
        this.trainingName = trainingName;
    }

    public TrainingType getTrainingType() {
        return trainingType;
    }

    public void setTrainingType(TrainingType trainingType) {
        this.trainingType = trainingType;
    }

    public LocalDateTime getTrainingDate() {
        return trainingDate;
    }

    public void setTrainingDate(LocalDateTime trainingDate) {
        this.trainingDate = trainingDate;
    }

    public Double getTrainingDuration() {
        return trainingDuration;
    }

    public void setTrainingDuration(Double trainingDuration) {
        this.trainingDuration = trainingDuration;
    }

}
