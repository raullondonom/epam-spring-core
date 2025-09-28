package co.com.raullondono.storage;

import co.com.raullondono.domain.Trainer;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class TrainerStorage extends ConcurrentHashMap<Long, Trainer> {


}
