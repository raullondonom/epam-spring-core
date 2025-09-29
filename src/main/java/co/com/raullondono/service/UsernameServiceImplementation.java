package co.com.raullondono.service;

import co.com.raullondono.dao.TraineeDAO;
import co.com.raullondono.dao.TrainerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UsernameServiceImplementation implements  UsernameService {

    @Autowired
    private TraineeDAO traineeDAO;
    @Autowired
    private TrainerDAO trainerDAO;

    @Override
    public String generateUsername(String firstName, String lastName) {
        String formattedFirst = StringUtils.capitalize(firstName);
        String formattedLast = StringUtils.capitalize(lastName);

        String base = formattedFirst + "." + formattedLast;

        if (!exists(base)) {
            return base;
        }

        int i = 2;
        while (true) {
            String candidate = base + "." + i;
            if (!exists(candidate)) {
                return candidate;
            }
            i++;
        }
    }

    private boolean exists(String username) {
        return traineeDAO.existsByUsername(username) || trainerDAO.existsByUsername(username);
    }
}

