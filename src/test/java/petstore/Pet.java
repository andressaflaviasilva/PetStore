package petstore;

import groovy.transform.ASTTest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;

public class Pet {
    String uri = "https://petstore.swagger.io/v2/pet";

    //lê arquivo json
    public String lerJson(String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths
                .get(caminhoJson)));
    }
//incluir pet
    @Test(priority=1)
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
                .statusCode(200)
                .body("name", is("Dogona"))
                .body("status", is("available"))
                .body("category.name", is("Cachorro"))
                .body("tags.name", contains("Banho"));

    }
    //pesquisar pet
    @Test(priority=2)
    public void consultarPet(){
        String petId = "1982120640";

                given()
                        .contentType("application/json")
                        .log().all()
                .when()
                        .get(uri + "/" + petId)
                .then()
                        .log().all()
                        .statusCode(200)
                        .body("name", is("Dogona"))
                        .body("category.name", is("Cachorro"))
                        .body("tags.name", contains("Banho"))
                        ;
    }
    @Test(priority=3)
    public void alterarPet()throws IOException{
        String jsonBody = lerJson("db/pet2.json");
        given()
                .contentType("application/json")
                .log().all()
                .body(jsonBody)
        .when()
                .put(uri)
        .then()
                .log().all()
                .statusCode(200)
                .body("name", is("Dogona"))
                .body("status", is("sold"))
                .body("category.name", is("Cachorro"))
                .body("tags.name", contains("Banho"));

    }
    @Test(priority=4)
    public void consultarPetStatus(){
        String petId = "1982120640";
        given()
                .contentType("application/json")
                .log().all()
        .when()
                .get(uri + "/" + "findByStatus?status=sold")
        .then()
                .log().all()
                .statusCode(200)
        ;
    }
}

