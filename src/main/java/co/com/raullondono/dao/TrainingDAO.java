package co.com.raullondono.dao;

import co.com.raullondono.domain.Training;

public interface TrainingDAO {

    Training createTraining(Training training);

    Training selectTraining(Long trainingId);

}
