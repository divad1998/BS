package com.budgit.integrationTests;

import com.budgit.extensions.PatronParameterResolver;
import com.budgit.table.Patron;
import com.budgit.web.api.PatronController;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.StreamUtils;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDateTime;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureWebTestClient
@ExtendWith({PatronParameterResolver.class})
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
public class PatronIntegrationTests {
    private Patron patron;

    @Autowired
    private WebTestClient webTestClient;

    @BeforeEach
    void createPatron(Patron injectedPatron) throws IOException {
        patron = injectedPatron;
    }

    @Nested
    @DisplayName("Creating a patron.")
    @Order(1)
    class CreatingPatron {

        @Test
        @DisplayName("Creating patron returns HttpStatus.CREATED, hal+json return type -- holding welcome string and links.")
        void createPatron() throws IOException {
            ClassPathResource expectedJson = new ClassPathResource("/jsonTestData/CreatePatronResponse.json");
            String expectedString = StreamUtils.copyToString(expectedJson.getInputStream(), Charset.defaultCharset());

            webTestClient
                    .post()
                    .uri("/api/patrons")
                    .body(Mono.just(patron), Patron.class)
                    .exchange()
                    .expectStatus()
                        .isCreated()
                    .expectHeader()
                        .contentType("application/hal+json")
                    .expectBody()
                        .json(expectedString);
        }

        @Test
        @DisplayName("Returns HTTP.400 and appropriate response body when a non-empty property is empty.")
        void createPatronWithEmptyProperty() {
            Patron emptyPatron = new Patron();
            emptyPatron.setFirstName(""); emptyPatron.setLastName(""); emptyPatron.setOtherNames("");
            emptyPatron.setCountry(""); emptyPatron.setState(""); emptyPatron.setLga(""); emptyPatron.setCity("");
            emptyPatron.setSex(""); emptyPatron.setCateringFor(1); emptyPatron.setEmail(""); emptyPatron.setPassword("");

            webTestClient
                    .post()
                    .uri("http://localhost:8080/api/patrons")
                    .body(Mono.just(emptyPatron), Patron.class)
                    .exchange()
                    .expectStatus()
                        .isBadRequest()
                    .expectBody(String.class)
                        .isEqualTo("First name can't be empty.");
        }

        @Test
        @DisplayName("Returns HTTP.400 and appropriate response body when a non-blank property is blank.")
        void createPatronWithBlankProperty() {
            Patron blankPatron = new Patron();
            blankPatron.setFirstName(" "); blankPatron.setLastName(" "); blankPatron.setOtherNames(" ");
            blankPatron.setCountry(" "); blankPatron.setState(" "); blankPatron.setLga(" ");
            blankPatron.setCity(" "); blankPatron.setSex(" "); blankPatron.setCateringFor(1);
            blankPatron.setEmail(" "); blankPatron.setPassword(" ");

            webTestClient
                    .post()
                    .uri("http://localhost:8080/api/patrons", Patron.class)
                    .body(Mono.just(blankPatron), Patron.class)
                    .exchange()
                    .expectStatus()
                        .isBadRequest()
                    .expectBody(String.class)
                        .isEqualTo("First name can't be blank.");
    }

    @Test
    @DisplayName("Returns HTTP 400 and appropriate response when a property with size constraint is out of range. ")
    void createPatronWithOutOfRangeProperty() {
        Patron outOfRangePatron = new Patron();
        outOfRangePatron.setFirstName("123456789012345678901"); outOfRangePatron.setLastName("s");
        outOfRangePatron.setOtherNames("s"); outOfRangePatron.setCountry("s");
        outOfRangePatron.setState("s"); outOfRangePatron.setLga("s"); outOfRangePatron.setCity("s");
        outOfRangePatron.setSex("s"); outOfRangePatron.setCateringFor(1);
        outOfRangePatron.setEmail("s"); outOfRangePatron.setPassword("s");

        webTestClient
                .post()
                .uri("http://localhost:8080/api/patrons")
                .body(Mono.just(outOfRangePatron), Patron.class)
                .exchange()
                .expectStatus()
                    .isBadRequest()
                .expectBody(String.class)
                    .isEqualTo("First name is too long.");
            }
    }

    @Nested
    @DisplayName("Updating a patron.")
    @Order(2)
    class UpdatingPatron {

        @Test
        @DisplayName("Patron is updated indeed.")
        void updatePatron() throws IOException {
            Patron patronUpdate = new Patron(); //has an updated firstname
            patronUpdate.setProfileMedia("$$$");
            patronUpdate.setFirstName("Dave"); patronUpdate.setOtherNames("Chigozie");
            patronUpdate.setLastName("Dinneya"); patronUpdate.setProfileMedia("$$$"); patronUpdate.setCountry("Nigeria");
            patronUpdate.setState("Nasarawa"); patronUpdate.setLga("Karu"); patronUpdate.setCity("One man village");
            patronUpdate.setSex("Male"); patronUpdate.setCateringFor(1); patronUpdate.setEmail("divadchigozie@gmail.com");
            patronUpdate.setPassword("123456"); patronUpdate.setCreatedAt(LocalDateTime.parse("2022-12-03T18:29:20.200198928"));

            ClassPathResource resource = new ClassPathResource("/jsonTestData/UpdatePatronResponse.json");
            String expectedJson = StreamUtils.copyToString(resource.getInputStream(), Charset.defaultCharset());

            webTestClient
                    .put()
                    .uri("http://localhost:8080/api/patrons/{patronId}", 1)
                    .body(Mono.just(patronUpdate), Patron.class)
                    .exchange()
                    .expectStatus()
                        .isOk()
                    .expectHeader()
                        .contentType("application/hal+json")
                    .expectBody()
                        .json(expectedJson);
        }
    }

    @Nested
    @DisplayName("Getting patrons.") //do i understand with this hungry brain? yas!
    class GettingPatrons {

        @Test
        @DisplayName("Returns Http.200, hal+json response type, patron owning id, self and logout links.")
        void getPatronById() throws IOException {
            ClassPathResource resource = new ClassPathResource("/jsonTestData/GetPatronByIdResponse.json");
            String expectedJson = StreamUtils.copyToString(resource.getInputStream(), Charset.defaultCharset());

            webTestClient
                    .get()
                    .uri("http://localhost:8080/api/patrons/{patronId}", 1)
                    .exchange()
                    .expectStatus()
                    .isOk()
                    .expectHeader()
                    .contentType("application/hal+json")
                    .expectBody()
                    .json(expectedJson);
        }

        @Test
        @DisplayName("Returns Http.200, hal+json response type,  Patron in db with self and patrons links.")
        void getAllPatrons() throws IOException {
            ClassPathResource resource = new ClassPathResource("/jsonTestData/GetAllPatronsResponse.json");
            String expectedJson = StreamUtils.copyToString(resource.getInputStream(), Charset.defaultCharset());

            webTestClient
                    .get()
                    .uri("http://localhost:8080/api/patrons")
                    .exchange()
                    .expectStatus()
                        .isOk()
                    .expectHeader()
                        .contentType("application/hal+json")
                    .expectBody()
                        .json(expectedJson);
        }
    }
}