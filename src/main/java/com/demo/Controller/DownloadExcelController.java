package com.demo.Controller;

import com.demo.Model.Client;
import com.demo.Model.Customer;
import com.demo.Service.ExcelFileExporter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class DownloadExcelController {

  @Autowired private RestTemplate restTemplate;

  @RequestMapping("/viewemp/{id}")
  public ResponseEntity<Client> viewemp(@PathVariable(value = "id") String id, Model m) {
   // System.out.print
    // String Id="1410000199509";
    System.out.println("test");
    // HttpHeaders
    Client list = null;
    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(
        Arrays.asList(
            new MediaType[] {MediaType.APPLICATION_JSON})); // Request to return JSON format
    // headers.setContentType(MediaType.APPLICATION_JSON);
    // HttpEntity<String>: To get result as String.
    HttpEntity<Client> entity = new HttpEntity<Client>(headers);
    // Send request with GET method, and Headers.
    System.out.println(entity);
    String url = "http://portail-client.osc-fr1.scalingo.io/api/v1/comptes/titres/";
    ResponseEntity<Client> response =
        restTemplate.exchange(url + id, HttpMethod.GET, entity, Client.class);

    return response;
  }

  @RequestMapping("/viewempList")
  public ResponseEntity<Client[]> viewemplist(Model m) {
    // String Id="14100001 99509";
    // HttpHeaders
    List<Client[]> list = null;
    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(
        Arrays.asList(
            new MediaType[] {MediaType.APPLICATION_JSON})); // Request to return JSON format
    // headers.setContentType(MediaType.APPLICATION_JSON);
    // HttpEntity<String>: To get result as String.
    HttpEntity<Client[]> entity = new HttpEntity<Client[]>(headers);
    // Send request with GET method, and Headers.
    System.out.println(entity);
    String url = "http://portail-client.osc-fr1.scalingo.io/api/v1/comptes/titres/";
    ResponseEntity<Client[]> response =
        restTemplate.exchange(url, HttpMethod.GET, entity, Client[].class);

    return response;
  }

  @RequestMapping("/object")
  public String view1(Model m) throws JsonProcessingException {
    final ObjectMapper objectMapper = new ObjectMapper();
    String json = "http://portail-client.osc-fr1.scalingo.io/api/v1/comptes/titres/";
    Client[] langs = objectMapper.readValue(json, Client[].class);
    List<Client> langList = new ArrayList(Arrays.asList(langs));
    langList.forEach(x -> System.out.println(x.toString()));
    return "Affichage effectue";
  }

  @GetMapping("/download/customers.xls")
  public void downloadCsv(HttpServletResponse response) throws IOException {
    response.setContentType("application/octet-stream");
    response.setHeader("Content-Disposition", "attachment; filename=customers.xls");
    ByteArrayInputStream stream = ExcelFileExporter.contactListToExcelFile(createTestData());
    IOUtils.copy(stream, response.getOutputStream());
  }

  private List<Customer> createTestData() {
    List<Customer> customers = new ArrayList<Customer>();
    customers.add(new Customer("Vernon", "Barlow", "0123456789", "test1@simplesolution.dev"));
    customers.add(new Customer("Maud", "Brock", "0123456788", "test2@simplesolution.dev"));
    customers.add(new Customer("Chyna", "Cowan", "0123456787", "test3@simplesolution.dev"));
    customers.add(new Customer("Krisha", "Tierney", "0123456786", "test4@simplesolution.dev"));
    customers.add(new Customer("hamza", "oukhyad", "0123456785", "test5@simplesolution.dev"));
    customers.add(new Customer("oumaima", "sedik", "0123456785", "test5@simplesolution.dev"));

    return customers;
  }
}
