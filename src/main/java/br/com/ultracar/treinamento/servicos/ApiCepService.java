package br.com.ultracar.treinamento.servicos;

import java.util.Objects;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import br.com.ultracar.treinamento.entidades.Cep;
import br.com.ultracar.treinamento.entidades.dto.CepDto;

public class ApiCepService {
	private static Client client = ClientBuilder.newClient();
	private static final String URL_CONSULTA_CEP = "http://cep.republicavirtual.com.br/web_cep.php";

	public static CepDto findBySaveCep(Cep cep) {
		CepDto cepDto = new CepDto();
		Gson builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		WebTarget target = client.target(URL_CONSULTA_CEP + "?cep="+cep.getNumero()+"&formato=json");
		Response json = target.request(MediaType.APPLICATION_JSON).get();
		if (Objects.equals(json.getStatus(), 200)) {
			cepDto = builder.fromJson(json.readEntity(String.class), new TypeToken<CepDto>() {}.getType());
		}
		return cepDto;
	}
}
