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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@WebFluxTest(controllers = BudgetController.class) //loads TestContext, adding declared controller only. How can I start a mock server instead?
@ExtendWith(BudgetDTOParamResolver.class)
public class BudgetControllerSpec {

    @MockBean
    private BudgetService budgetService;
    @Autowired
    private WebTestClient client;

    private BudgetDTO budgetDTO;
    @BeforeEach
    void init(BudgetDTO resolvedBudgetDTO) {
        budgetDTO = new BudgetDTO();
        budgetDTO.setId(resolvedBudgetDTO.getId());
        budgetDTO.set_month(resolvedBudgetDTO.get_month());
        budgetDTO.setBalance(resolvedBudgetDTO.getBalance());
        budgetDTO.setIncome(resolvedBudgetDTO.getIncome());
        budgetDTO.setCreatedAt(LocalDateTime.parse("2023-01-12T06:26:12.183725274"));
    }

    @Test
    @DisplayName("Handle save-budget requests and return savedBudgetDTO")
    void reachSaveBudgetHandler() throws IOException {
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
}