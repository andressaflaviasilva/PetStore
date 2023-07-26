package petstore;

import groovy.transform.ASTTest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class Pet {
    String uri = "https://petstore.swagger.io/v2/pet";

    //lê arquivo json
    public String lerJson(String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths
                .get(caminhoJson)));
    }
//incluir
    @Test
    public void incluirPet()throws IOException{
        String jsonBody = lerJson("db/pet1.json");
        given()
                .contentType("application/json")
                .log().all()
                .body(jsonBody)
        .when()
                .post(uri)
        .then()
                .log().all()
                .statusCode(200);
    }

}

