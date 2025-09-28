package co.com.raullondono.storage;

import co.com.raullondono.domain.Training;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class TrainingStorage extends ConcurrentHashMap<Long, Training> {


}
