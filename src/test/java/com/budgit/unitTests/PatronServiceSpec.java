package com.budgit.unitTests;

import com.budgit.data.PatronRepository;
import com.budgit.extensions.PatronParameterResolver;
import com.budgit.hateoas.model.Response;
import com.budgit.service.PatronService;
import com.budgit.table.Patron;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith({MockitoExtension.class, PatronParameterResolver.class})
public class PatronServiceSpec {

    @Mock
    PatronRepository patronRepository;

    @InjectMocks
    PatronService patronService;

    Patron patron;

    @BeforeEach
    void init(Patron injectedPatron) {
           patron = injectedPatron;
    }

    @Test
    @DisplayName("Return Response obj with welcome msg after persisting patron.")
    void createPatronViaPatronService() {
        Mono<Response> expectedResponseStream = Mono.just(new Response("Hey! Now our patron!"));
        Mockito.when(patronRepository.save(patron)).thenReturn(Mono.just(patron));
        Mono<Response> returnedResponseStream = patronService.create(patron);

        assertEquals(expectedResponseStream.block(), returnedResponseStream.block());
    }

//    @Test
//    @DisplayName("Return Mono publisher of persisted patron.")
//    void updatePatron() {
//        Mono<Patron> mockedMono = Mono.just(patron);
//        Mockito.when(patronRepotory.save(patron)).thenReturn(mockedMono);
//        Mono<Patron> returnedMono = patronService.update(patron);
//
//        assertEquals(mockedMono, returnedMono);
//    }

    @DisplayName("Deletes patron with matching id in repo.")
    @Test
    void deletePatronInRepo() {
        //Algo:
        //Stub PatronRepo.deleteById
        //assert equality to Mono.void
        //Verify stubbing
        Mockito.when(patronRepository.deleteById(anyLong())).thenReturn(Mono.empty());

        Mono<Void> returnedMono = patronService.deleteById(1L);
        assertEquals(Mono.empty().block(), returnedMono.block());

        verify(patronRepository, times(1)).deleteById(anyLong());
    }
}