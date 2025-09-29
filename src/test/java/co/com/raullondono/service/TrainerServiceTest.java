package co.com.raullondono.service;

import co.com.raullondono.dao.TrainerDAO;
import co.com.raullondono.domain.Trainer;
import co.com.raullondono.util.PasswordGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class TrainerServiceTest {

    private TrainerDAO trainerDAO;
    private UsernameService usernameService;
    private PasswordGenerator passwordGenerator;
    private TrainerServiceImplementation service;

    @BeforeEach
    void setUp() {
        trainerDAO = mock(TrainerDAO.class);
        usernameService = mock(UsernameService.class);
        passwordGenerator = mock(PasswordGenerator.class);
        service = new TrainerServiceImplementation();
        service.setTrainerDAO(trainerDAO);
        service.setUsernameService(usernameService);
        service.setPasswordGenerator(passwordGenerator);
    }

    @Test
    void create_shouldSetUsernameAndPassword() {
        when(usernameService.generateUsername("Raul", "Londono")).thenReturn("Raul.Londono");
        when(passwordGenerator.generatePassword(10)).thenReturn("ABCDEFGHIJ");

        Trainer saved = new Trainer();
        saved.setUserId(55L);
        saved.setFirstName("Raul");
        saved.setLastName("Londono");
        saved.setUsername("Raul.Londono");
        when(trainerDAO.createTrainer(any(Trainer.class))).thenReturn(saved);

        Trainer result = service.createTrainer("Raul", "Londono", "CARDIO");

        assertThat(result.getUserId()).isEqualTo(55L);
        assertThat(result.getUsername()).isEqualTo("Raul.Londono");
        verify(trainerDAO).createTrainer(any(Trainer.class));
    }

    @Test
    void update_shouldPersistChanges() {
        Trainer existing = new Trainer();
        existing.setUserId(55L);
        existing.setFirstName("Raul");
        existing.setLastName("Londono");
        when(trainerDAO.selectTrainer(55L)).thenReturn(existing);

        Trainer updated = new Trainer();
        updated.setUserId(55L);
        updated.setFirstName("Raulino");
        updated.setLastName("Londino");
        when(trainerDAO.updateTrainer(any(Trainer.class))).thenReturn(updated);

        Trainer result = service.updateTrainer(updated);

        assertThat(result.getFirstName()).isEqualTo("Raulino");
        verify(trainerDAO).updateTrainer(any(Trainer.class));
    }
}
