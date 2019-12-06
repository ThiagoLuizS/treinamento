package br.com.treinamento.treinamento;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.MediaType;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.ultracar.treinamento.TreinamentoApplication;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.minidev.json.JSONObject;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TreinamentoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TreinamentoApplicationTests {
	
	@LocalServerPort
	private int port;
	
	@Before
	public void setUp() {
		RestAssured.baseURI = "http://localhost:"+port+"/api";
	}
	
	@Test
	public void contextPostEstado() throws JSONException {
		RequestSpecification request = RestAssured.given().contentType(MediaType.APPLICATION_JSON);
		JSONObject requestParam = new JSONObject();		
		requestParam.put("sigla", "MG");
		request.body(requestParam.toJSONString());
		Response response = request.post("/estados");	
		Integer statusCode = response.getStatusCode();
		assertEquals(Long.parseLong(statusCode.toString()), 201);
	}
	
	@Test
	public void ContextGetEstado() {
		RequestSpecification request = RestAssured.given().contentType(MediaType.APPLICATION_JSON);
		Response response = request.get("/estados");
		Integer statusCode = response.getStatusCode();
		assertEquals(Long.parseLong(statusCode.toString()), 200);
	}
	
	
}
