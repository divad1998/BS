package com.budgit.unitTests;

//unit testing Budget Controller

import com.budgit.data.BudgetRepository;
import com.budgit.data.ExpenseRepository;
import com.budgit.dto.BudgetDTO;
import com.budgit.service.BudgetService;
import com.budgit.service.ExpenseService;
import com.budgit.table.Budget;
import com.budgit.table.Expense;
import com.budgit.web.api.BudgetController;
import org.junit.jupiter.api.Test;
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
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.reactive.server.WebTestClient.bindToController;

@WebFluxTest(controllers = BudgetController.class) //loads TestContext, adding declared controller only. How can I start a mock server instead?
public class BudgetControllerTests {

    @MockBean
    private BudgetService budgetService;
    @MockBean
    private BudgetRepository budgetRepository;
    @MockBean
    private ExpenseService expenseService;
    @MockBean
    private ExpenseRepository expenseRepository;

    @Autowired
    private WebTestClient webTestClient;

    //NEEDS (for a unit test):
    //App context with BudgetController only: is there an alt to SpringBootTest(classes =BudgetController.class....) ?
    //mock BudgetService, BudgetRepository, ExpenseService & ExpenseRepository: All beans involved.
    //mockServer: is it still @AutoConfigureMockMvc?
    //ALGORITHM:
    //instantiate BudgetDto which would be persisted and returned by BudgetService
    //instantiate expense, add to a list and set to budgetDto. This is returned by ExpenseRepo and ExpenseService
    //instantiate Budget to be returned by BudgetRepository. Reminder: a Mono is returned
    //set bean expectations
    //set and launch
    //expect media type of /hal+json
    //expect CREATED http response
    //expect return object type as BudgetModel.class
    //links: selves, budgets and expenses.
    @Test
    public void shouldSaveAndReturnBudget() throws URISyntaxException, IOException {

        when(budgetService.create(Mono.just(getBudgetDTO()))).thenReturn(Mono.just(getBudgetDTO())); //are the below required?
        when(budgetRepository.saveAll(Mono.just(getBudget()))).thenReturn(Flux.just(getBudget()));
        when(expenseService.save(Flux.fromIterable(getExpenses()), Mono.just(1L))).thenReturn(Flux.fromIterable(getExpenses()));
        when(expenseRepository.saveAll(Flux.fromIterable(getExpenses()))).thenReturn(Flux.fromIterable(getExpenses())); //same with above line

        //fetch expected json (which is in file) and convert to string
        ClassPathResource expectedJson = new ClassPathResource("/jsonTestData/budgetResponse.json");
        String expectedString = StreamUtils.copyToString(expectedJson.getInputStream(), Charset.defaultCharset());

        //build up request
        webTestClient.post()
                    .uri(new URI("/api/budgets"))
                    .body(Mono.just(getBudgetDTO()), BudgetDTO.class)
                    .exchange()
                    .expectStatus()
                        .isCreated()
                    .expectHeader()
                        .contentType("application/hal+json") //ToDo: change
                    .expectBody()
                        .json(expectedString);
    }

    public BudgetDTO getBudgetDTO() {
        //set-up
        BudgetDTO budgetDTO = new BudgetDTO();
        budgetDTO.setId(1L);
        budgetDTO.setMonth("September");
        budgetDTO.setIncome("N67000");
        budgetDTO.setBalance("N500");
        budgetDTO.setExpenses(getExpenses());
        //ToDo: fix parsing error
        budgetDTO.setCreatedAt(null); //(LocalDateTime.parse("2022-08-13 21:54:45"));

        return budgetDTO;
    }


    public Budget getBudget() {
        Budget budget = new Budget();
        budget.setMonth("September");
        budget.setIncome("N67000");
        budget.setBalance("N500");
        budget.setPatronId(1L);
        //ToDo: fix parsing error
        budget.setCreatedAt(null); //(LocalDateTime.parse("2022-08-13 21:54:45"));

        return budget;
    }

    public List<Expense> getExpenses() {
        //set-up
        Expense expense = new Expense();
        expense.setId(1L);
        expense.setTitle("Improving imaginative ability");
        expense.setType("Luxury");
        expense.setAmount("N5000");
        expense.setBalance("N5000");
        expense.setDescription("improving my qol");
        expense.setBudgetId(1L);
        expense.setPatronId(1L);
        //ToDo: fix parsing error
        expense.setCreatedAt(null); //(LocalDateTime.parse("2022-08-13 21:54:45"));

        return List.of(expense);
    }
}