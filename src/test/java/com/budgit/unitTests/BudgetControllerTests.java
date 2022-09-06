package com.budgit.unitTests;

//unit testing Budget Controller

import org.junit.jupiter.api.Test;

public class BudgetControllerTests {

    //NEEDS:
    //App context with BudgetController only: alt to SpringBootTest(classes =....) ?
    //mock BudgetService, BudgetRepository & ExpenseRepository: All beans involved.
    //mockServer: autoConfigureMockMvc -- MockMvc the mockServer ?
    //ALGORITHM:
    //instantiate BudgetDto
    //instantiate Budget to be returned by BudgetRepository
    //instantiate expense, add to a list and set to budgetDto
    //to be continued....
    @Test
    public void shouldSaveAndReturnBudget() {

    }

}
