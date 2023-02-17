package com.budgit.unitTests;

//unit testing Budget Controller

import com.budgit.data.BudgetRepository;
import com.budgit.data.ExpenseRepository;
import com.budgit.dto.BudgetDTO;
import com.budgit.extensions.BudgetDTOParamResolver;
import com.budgit.hateoas.assembler.BudgetModelAssembler;
import com.budgit.hateoas.model.BudgetModel;
import com.budgit.service.BudgetService;
import com.budgit.service.ExpenseService;
import com.budgit.table.Budget;
import com.budgit.table.Expense;
import com.budgit.web.api.BudgetController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.StreamUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@WebFluxTest(controllers = BudgetController.class) //loads TestContext, adding declared controller only. How can I start a mock server instead?
@ExtendWith(BudgetDTOParamResolver.class)
public class BudgetControllerSpec {

    @Autowired
    private WebTestClient client;

    @MockBean
    private BudgetService budgetService;
    private BudgetDTO budgetDTO;

    @BeforeEach
    void init(BudgetDTO resolvedBudgetDTO) {
        budgetDTO = resolvedBudgetDTO;
    }

    @DisplayName("Creates Budget")
    @Nested
    class CreateBudgetSpec {

        @Test
        @DisplayName("Handles save-budget requests and return savedBudgetDTO.")
        void createBudget() throws IOException {
            Mono<BudgetDTO> budgetDTOMono = Mono.just(budgetDTO);
            ClassPathResource expectedJson = new ClassPathResource("/jsonTestData/CreateBudgetResponse.json");
            String expectedJsonString = StreamUtils.copyToString(expectedJson.getInputStream(), Charset.defaultCharset());

            when(budgetService.create(any())).thenReturn(budgetDTOMono);

            client
                    .post()
                    .uri("http://localhost:8080/api/budgets")
                    .body(budgetDTOMono, BudgetDTO.class)
                    .exchange()
                    .expectStatus()
                    .isCreated()
                    .expectHeader()
                    .contentType("application/json")
                    .expectBody()
                    .json(expectedJsonString);

            Mockito.verify(budgetService, times(1)).create(any());
        }

        @DisplayName("Returns Http.BadRequest when non-empty field is empty.")
        @Test
        void createBudgetWithEmptyField() {
            BudgetDTO budgetDTO1 = new BudgetDTO();
            budgetDTO1.set_month("");
            budgetDTO1.setIncome("");
            budgetDTO1.setIncomeStreams("");
            budgetDTO1.setBalance("");

            Mono<BudgetDTO> budgetDTOMono = Mono.just(budgetDTO1);

            client
                    .post()
                    .uri("http://localhost:8080/api/budgets")
                    .body(budgetDTOMono, BudgetDTO.class)
                    .exchange()
                    .expectStatus()
                    .isBadRequest();
        }

        @DisplayName("Returns HttpStatus.BADREQUEST when non-blank field is blank.")
        @Test
        void createWithBlankField() {

            BudgetDTO budgetDTO1 = new BudgetDTO();
            budgetDTO1.set_month(" ");
            budgetDTO1.setIncome(" ");
            budgetDTO1.setBalance(" ");

            client
                    .post()
                    .uri("http://localhost:8080/api/budgets")
                    .body(Mono.just(budgetDTO1), BudgetDTO.class)
                    .exchange()
                    .expectStatus()
                        .isBadRequest();
        }
    }

    @DisplayName("Updates Budget")
    @Nested
    class UpdateBudgetSpec {

        @DisplayName("Updates budget successfully.")
        @Test
        void updateBudget() throws IOException {
            Mono<BudgetDTO> budgetDTOMono = Mono.just(budgetDTO);
            when(budgetService.update(anyLong(), any())).thenReturn(budgetDTOMono);

            ClassPathResource expectedJson = new ClassPathResource("/jsonTestData/CreateBudgetResponse.json");
            String expectedJsonString = StreamUtils.copyToString(expectedJson.getInputStream(), Charset.defaultCharset());

            client
                    .put()
                    .uri("http://localhost:8080/api/budgets/{budgetId}", 1L)
                    .body(budgetDTOMono, BudgetDTO.class)
                    .exchange()
                    .expectStatus()
                        .isOk()
                    .expectHeader()
                        .contentType("application/json")
                    .expectBody()
                        .json(expectedJsonString);

            verify(budgetService, times(1)).update(anyLong(), any());
        }
    }
}