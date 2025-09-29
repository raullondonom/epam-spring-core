package co.com.raullondono.domain;

public enum TrainingType {

    CARDIO("Cardio", 1),
    STRENGTH("Strength", 2),
    FLEXIBILITY("Flexibility", 3),
    BALANCE("Balance", 4),
    HIIT("HIIT", 5),
    YOGA("Yoga", 6),
    PILATES("Pilates", 7),
    CROSSFIT("CrossFit", 8),
    DANCE("Dance", 9),
    MARTIAL_ARTS("Martial Arts", 10);

    private final String name;
    private final int trainingTypeId;

    TrainingType(String name, int trainingTypeId) {
        this.name = name;
        this.trainingTypeId = trainingTypeId;
    }

    public String getName() {
        return name;
    }

    public int getTrainingTypeId() {
        return trainingTypeId;
    }

}
