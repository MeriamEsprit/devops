package tn.esprit.devops_project.entities;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import tn.esprit.devops_project.repositories.OperatorRepository;
import tn.esprit.devops_project.services.OperatorServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class OperatorTest {
@Mock
private OperatorRepository repository;
@InjectMocks
private OperatorServiceImpl service;
    @Test
    public void testAddOperator(){
        Operator operatorToAdd = new Operator();
        operatorToAdd.setIdOperateur(1L);
        operatorToAdd.setFname("John");
        operatorToAdd.setLname("Doe");
        operatorToAdd.setPassword("password");

        Mockito.when(repository.save(any(Operator.class))).thenReturn(operatorToAdd);
//        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(operatorToAdd));

        Operator addedOperator = service.addOperator(operatorToAdd);

        assertEquals(operatorToAdd.getIdOperateur(), addedOperator.getIdOperateur());
        assertEquals(operatorToAdd.getFname(), addedOperator.getFname());
        assertEquals(operatorToAdd.getLname(), addedOperator.getLname());
        assertEquals(operatorToAdd.getPassword(), addedOperator.getPassword());

        verify(repository, times(1)).save(any(Operator.class));

    }
}