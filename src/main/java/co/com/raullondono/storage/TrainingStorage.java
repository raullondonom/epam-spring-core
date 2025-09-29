package co.com.raullondono.storage;

import co.com.raullondono.domain.Training;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
@DependsOn({"traineeStorage", "trainerStorage"})
public class TrainingStorage extends ConcurrentHashMap<Long, Training> {


}
