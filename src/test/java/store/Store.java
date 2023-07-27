package Store;

import groovy.transform.ASTTest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;

@Test
public class Store {
    String uri = "https://petstore.swagger.io/v2/store/order";
     public String lerJson(String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths
                .get(caminhoJson)));
}

    //incluir pedido
    @Test
    public void incluirPedido()throws IOException{
        String jsonBody = lerJson("db/pedido.json");
        given()
                .contentType("application/json")
                .log().all()
                .body(jsonBody)
        .when()
                .post(uri)
        .then()
                .log().all()
                .statusCode(200)
;

    }

}
