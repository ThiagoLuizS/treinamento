package br.com.treinamento.treinamento;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.ws.rs.core.MediaType;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.ultracar.treinamento.TreinamentoApplication;
import br.com.ultracar.treinamento.entidades.Estado;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;
import net.minidev.json.JSONObject;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TreinamentoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TreinamentoApplicationTests {
	
	@LocalServerPort
	private int port;
	
	@Before
	public void setUp() {
		RestAssured.baseURI = "http://localhost:8082/api";
	}
	
	@Test
	public void contextPostEstado() throws JSONException {
		RequestSpecification request = RestAssured.given().contentType(MediaType.APPLICATION_JSON);
		JSONObject requestParam = new JSONObject();
		
		requestParam.put("sigla", "MG");
		request.body(requestParam.toJSONString());
		Response response = request.post("/estados");
		
		Integer statusCode = response.getStatusCode();
		assertEquals(statusCode, 201);
	}
}
