package com.leafchild.cashmachine;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leafchild.cashmashine.BootCashMachine;
import com.leafchild.cashmashine.dto.card.CardService;
import com.leafchild.cashmashine.dto.transaction.CardTransactionService;
import com.leafchild.cashmashine.entity.Card;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by: vmalyshev
 * Date: 27/06/16
 * TIME: 17:35
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(BootCashMachine.class)
@WebIntegrationTest
public class MainControllerTest {

  @Autowired
  private CardService cardService;
  @Autowired
  private CardTransactionService transactionService;
  RestTemplate restTemplate = new TestRestTemplate();
  //Required to Generate JSON content from Java objects
  public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
  String API_ENDPOINT = "http://localhost:8080/api/cards/";
  private static Card newCard;

  @BeforeClass
  public static void init() {

    newCard = new Card();
    BigDecimal balance = new BigDecimal(1000);
    newCard.setCurrentBalance(balance);
    newCard.setLocked(false);
    newCard.setPin((short) 1111);


  }

  @Test
  public void findCardByIdTest() throws Exception {

    long testId = 123;

    HttpHeaders requestHeaders = new HttpHeaders();
    requestHeaders.setContentType(MediaType.APPLICATION_JSON);

    //Creating http entity object with request body and headers
    HttpEntity<String> httpEntity = new HttpEntity<>(requestHeaders);
    //Check unexisting entity
    ResponseEntity<Boolean> isFound = restTemplate.getForEntity(API_ENDPOINT + testId, Boolean.class, httpEntity);

    //Just fail case, will cover successful ones in the test below
    assertTrue(isFound.getStatusCode().is4xxClientError());

    cardService.save(newCard);

    isFound = restTemplate.getForEntity(API_ENDPOINT + newCard.getCard_id(), Boolean.class, httpEntity);

    assertTrue(isFound.hasBody());
    assertTrue(isFound.getStatusCode().is2xxSuccessful());
    //Should return true as a result
    assertTrue(isFound.getBody());

  }

  @Test
  public void getCardForBalanceTest() throws Exception {

    HttpHeaders requestHeaders = new HttpHeaders();
    requestHeaders.setContentType(MediaType.APPLICATION_JSON);

    //Creating http entity object with request body and headers
    HttpEntity<String> httpEntity = new HttpEntity<>(requestHeaders);

    cardService.save(newCard);
    //Check unexisting entity
    ResponseEntity<Card> entity = restTemplate.getForEntity(API_ENDPOINT + newCard.getCard_id() + "/balance",
        Card.class, httpEntity);

    assertTrue(entity.getStatusCode().is2xxSuccessful());
    assertTrue(entity.hasBody());

    Card foundCard = entity.getBody();
    assertEquals(foundCard.getCard_id(), newCard.getCard_id());
    assertEquals(foundCard.getPin(), newCard.getPin());
    assertEquals(foundCard.isLocked(), newCard.isLocked());
    assertEquals(foundCard.getCurrentBalance().longValueExact(), newCard.getCurrentBalance().longValueExact());

  }


  @Test
  public void makeWithdrawTest() throws Exception {

    long amount = 15;
    Map<String, Object> requestBody = new HashMap<>();
    requestBody.put("amount", amount);
    HttpHeaders requestHeaders = new HttpHeaders();
    requestHeaders.setContentType(MediaType.APPLICATION_JSON);
    //Creating http entity object with request body and headers
    HttpEntity<String> httpEntity = new HttpEntity<>(OBJECT_MAPPER.writeValueAsString(requestBody), requestHeaders);

    cardService.save(newCard);
    //Check unexisting entity
    ResponseEntity<Card> entity = restTemplate.postForEntity(API_ENDPOINT + newCard.getCard_id() + "/withdraw",
        httpEntity, Card.class);

    assertTrue(entity.getStatusCode().is2xxSuccessful());
    assertTrue(entity.hasBody());

    Card foundCard = entity.getBody();
    assertEquals(foundCard.getCard_id(), newCard.getCard_id());
    assertEquals(foundCard.getCurrentBalance().longValueExact(), newCard.getCurrentBalance().longValueExact() - amount);


    requestBody = new HashMap<>();
    requestBody.put("amount", 1001);
    //Creating http entity object with request body and headers
    httpEntity = new HttpEntity<>(OBJECT_MAPPER.writeValueAsString(requestBody), requestHeaders);

    entity = restTemplate.postForEntity(API_ENDPOINT + newCard.getCard_id() + "/withdraw",
        httpEntity, Card.class);

    assertTrue(entity.getStatusCode().is4xxClientError());
    assertFalse(entity.hasBody());

  }

  @Test
  public void checkPinCodeTest() throws Exception {

    cardService.save(newCard);
    Map<String, Object> requestBody = new HashMap<>();
    requestBody.put("id", newCard.getCard_id());
    requestBody.put("isLocked", "false");
    requestBody.put("pinCode", 1111);
    HttpHeaders requestHeaders = new HttpHeaders();
    requestHeaders.setContentType(MediaType.APPLICATION_JSON);
    //Creating http entity object with request body and headers
    HttpEntity<String> httpEntity = new HttpEntity<>(OBJECT_MAPPER.writeValueAsString(requestBody), requestHeaders);

    //Check unexisting entity
    ResponseEntity<String> entity = restTemplate.postForEntity(API_ENDPOINT + "/pincode", httpEntity, String.class);

    assertTrue(entity.getStatusCode().is2xxSuccessful());
    assertTrue(entity.hasBody());
    assertEquals(entity.getBody(), "Pin is correct");

    //TODO: Fail cases for isLocked and incorrectPin
  }

}
