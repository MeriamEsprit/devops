package tn.esprit.devops_project.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.devops_project.repositories.OperatorRepository;
import tn.esprit.devops_project.services.OperatorServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class OperatorTest {
@Mock
private OperatorRepository repository;
@InjectMocks
private OperatorServiceImpl service;
    @Test
    public void retrieveOperator_ValidId_OperatorReturned(){
    Operator operator=new Operator();
    operator.setIdOperateur(1L);
    operator.setFname("test");
           Mockito.when(repository.findById(1L)).thenReturn(Optional.of(operator));
           Operator op=service.retrieveOperator(1L);
        // Act
        Operator retrievedOperator = service.retrieveOperator(1L);

        // Assert
        assertNotNull(retrievedOperator);
        assertEquals(1L, retrievedOperator.getIdOperateur());
        assertEquals("test", retrievedOperator.getFname());

    /*    // Verify interaction with the mocked repository
        Mockito.verify(repository, Mockito.times(1)).findById(1L);*/

    }
}