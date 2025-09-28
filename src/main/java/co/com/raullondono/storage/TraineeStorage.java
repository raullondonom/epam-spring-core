package co.com.raullondono.storage;

import co.com.raullondono.domain.Trainee;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class TraineeStorage extends ConcurrentHashMap<Long, Trainee> {


}
