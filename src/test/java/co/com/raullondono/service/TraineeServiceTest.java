package co.com.raullondono.service;

import co.com.raullondono.dao.TraineeDAO;
import co.com.raullondono.domain.Trainee;
import co.com.raullondono.util.PasswordGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class TraineeServiceTest {

    private TraineeDAO traineeDAO;
    private UsernameService usernameService;
    private PasswordGenerator passwordGenerator;
    private TraineeServiceImplementation service;

    @BeforeEach
    void setUp() {
        traineeDAO = mock(TraineeDAO.class);
        usernameService = mock(UsernameService.class);
        passwordGenerator = mock(PasswordGenerator.class);
        service = new TraineeServiceImplementation();
        service.setTraineeDAO(traineeDAO);
        service.setUsernameService(usernameService);
        service.setPasswordGenerator(passwordGenerator);
    }

    @Test
    void create_shouldCapitalizeValidateAndPersist() {
        when(usernameService.generateUsername("Raul", "Londono")).thenReturn("Raul.Londono");
        when(passwordGenerator.generatePassword(10)).thenReturn("ABCDEFGHIJ");

        Trainee saved = new Trainee();
        saved.setUserId(123L);
        saved.setFirstName("Raul");
        saved.setLastName("Londono");
        saved.setUsername("Raul.Londono");

        ArgumentCaptor<Trainee> traineeCaptor = ArgumentCaptor.forClass(Trainee.class);
        when(traineeDAO.createTrainee(traineeCaptor.capture())).thenReturn(saved);

        Trainee result = service.createTrainee("rAuL",
                "lOnDoNo",
                LocalDate.of(1991, 12, 3),
                "Calle 10 #20-10",
                "3117559702");

        Trainee persisted = traineeCaptor.getValue();
        assertThat(persisted.getFirstName()).isEqualTo("Raul");
        assertThat(persisted.getLastName()).isEqualTo("Londono");
        assertThat(persisted.getUsername()).isEqualTo("Raul.Londono");
        assertThat(result.getUserId()).isEqualTo(123L);

        verify(traineeDAO, times(1)).createTrainee(any(Trainee.class));
        verify(passwordGenerator).generatePassword();
    }

    @Test
    void delete_shouldReturnTrueWhenRemoved() {
        when(traineeDAO.deleteTrainee(10L)).thenReturn(new Trainee());

        Trainee removed = service.deleteTrainee(10L);

        assertThat(removed).isNotNull();
        verify(traineeDAO).deleteTrainee(10L);
    }
}
