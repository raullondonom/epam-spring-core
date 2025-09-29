package co.com.raullondono.service;

import co.com.raullondono.dao.TraineeDAO;
import co.com.raullondono.dao.TrainerDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

class UsernameServiceTest {

    private TraineeDAO traineeDAO;
    private TrainerDAO trainerDAO;
    private UsernameServiceImplementation service;

    @BeforeEach
    void setUp() {
        traineeDAO = Mockito.mock(TraineeDAO.class);
        trainerDAO = Mockito.mock(TrainerDAO.class);
        service = new UsernameServiceImplementation();
        service.setTraineeDAO(traineeDAO);
        service.setTrainerDAO(trainerDAO);
    }

    @Test
    void generate_shouldReturnUsername_whenNoCollision() {
        Mockito.when(traineeDAO.existsByUsername("Raul.Londono")).thenReturn(false);
        Mockito.when(trainerDAO.existsByUsername("Raul.Londono")).thenReturn(false);

        String username = service.generateUsername("Raul", "Londono");

        assertThat(username).isEqualTo("Raul.Londono");
    }

    @Test
    void generate_shouldAppendSuffix_whenCollisionExistsInEitherDao() {
        Mockito.when(traineeDAO.existsByUsername("Raul.Londono")).thenReturn(true);
        Mockito.when(trainerDAO.existsByUsername("Raul.Londono")).thenReturn(false);
        Mockito.when(traineeDAO.existsByUsername("Raul.Londono2")).thenReturn(false);
        Mockito.when(trainerDAO.existsByUsername("Raul.Londono2")).thenReturn(false);

        String username = service.generateUsername("Raul", "Londono");

        assertThat(username).isEqualTo("Raul.Londono2");
    }

    @Test
    void generate_shouldKeepCapitalization() {
        Mockito.when(traineeDAO.existsByUsername("Raul.Londono")).thenReturn(false);
        Mockito.when(trainerDAO.existsByUsername("Raul.Londono")).thenReturn(false);

        String username = service.generateUsername("Raul", "Londono");

        assertThat(username).isEqualTo("Raul.Londono");
    }
}
