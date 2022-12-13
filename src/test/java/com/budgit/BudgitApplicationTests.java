//package com.budgit;
//
//import com.budgit.data.PatronRepository;
//import com.budgit.service.PatronService;
//import com.budgit.table.Patron;
//import com.budgit.web.api.PatronController;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.Import;
//import org.springframework.test.web.reactive.server.WebTestClient;
//import reactor.core.publisher.Mono;
//
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
////@SpringBootTest
//@WebFluxTest(controllers = {PatronController.class})
//@Import({PatronService.class})
//class BudgitApplicationTests {
//
////	@Test
////	void contextLoads() {
////	}
//
//    @MockBean
//    private PatronRepository patronRepo;
//    @Autowired
//    private WebTestClient webTestClient;
//
//    @Test
//    void createPatronWithNullFields() {
//        Patron requestPatron = new Patron();
//        requestPatron.setFirstName(""); requestPatron.setLastName(""); requestPatron.setOtherNames("");
//        requestPatron.setCountry(""); requestPatron.setState(""); requestPatron.setLga("");
//        requestPatron.setCity(""); requestPatron.setSex(""); requestPatron.setCateringFor(1);
//        requestPatron.setEmail(""); requestPatron.setPassword("");
//
//        webTestClient.post()
//                .uri("http://localhost:8080/api/patrons")
//                .body(Mono.just(requestPatron), Patron.class)
//                .exchange()
//                .expectStatus()
//                    .isBadRequest();
//    }
//
//    @Test
//    @DisplayName("Creating patron with blank fields should throw Exception and return Http.BadRequest")
//    void createPatronWithBlankFields() {
//        Patron patron = new Patron();
//        patron.setFirstName(" "); patron.setLastName(" "); patron.setOtherNames(" ");
//        patron.setCountry(" "); patron.setState(" "); patron.setLga(" "); patron.setCity(" ");
//        patron.setSex(" "); patron.setEmail(" "); patron.setPassword(" ");
//
//        webTestClient.post()
//                .uri("http://localhost:8080/api/patrons")
//                .body(Mono.just(patron), Patron.class)
//                .exchange()
//                .expectStatus()
//                    .isBadRequest();
//    }
//}