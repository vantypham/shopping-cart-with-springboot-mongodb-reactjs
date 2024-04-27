package com.webstore.test;

import com.webstore.service.dto.ProductDTO;
import com.webstore.service.dto.ReviewDTO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductTest {

    @BeforeClass
    public static void setup() {
        RestAssured.port = Integer.valueOf(8080);
        RestAssured.baseURI = "http://localhost";
        RestAssured.basePath = "";
    }


    @Test
    @Order(1)
    public void testCreateProduct_P991234567() {
        ProductDTO dto = new ProductDTO("P991234567", "Red Apple", 4.3, "Fresh Red Apple from New Zeland", 20);
        given().contentType("application/json").body(dto)
                .when().post("/api/products").then()
                .log().body().statusCode(200);
    }
    @Test
    @Order(2)
    public void testCreateProduct_P991234568() {
        ProductDTO dto = new ProductDTO("P991234568", "Blue Apple", 4.2, "Fresh BLUE Apple from New Australia", 20);
        given().contentType("application/json").body(dto)
                .when().post("/api/products").then()
                .log().body().statusCode(200);
    }
    @Test
    @Order(3)
    public void testGetProduct_P991234567() {
        RestAssured.given().when().get("/api/products/P991234567")
                .then()
                .contentType(ContentType.JSON)
                .log().body()
                .and()
                .body("productNumber",equalTo("P991234567"))
                .body("name",equalTo("Red Apple"));
    }

    @Test
    @Order(4)
    public void testUpdateProduct_P991234567() {
        ProductDTO dto = new ProductDTO("P991234567", "Green Apple", 2.5, "Fresh GREEN Apple from USA", 100);
        RestAssured.given()
                .contentType("application/json")
                .body(dto)
                .when().put("/api/products/P991234567")
                .then()
                .contentType(ContentType.JSON)
                .log().body()
                .and()
                .body("numberInStock",equalTo(100))
                .body("name",equalTo("Green Apple"));
    }

    @Test
    @Order(5)
    public void testMakeReviewProduct_P991234567() {
        ReviewDTO dto = new ReviewDTO();
        dto.setUsername("user111");
        dto.setMessage("I like the color of this food");
        dto.setRate("5 - EXCELLENT");
        RestAssured.given()
                .contentType("application/json")
                .body(dto)
                .when().post("/api/products/P991234567")
                .then()
                .contentType(ContentType.JSON)
                .log().body()
                .and()
                .body("reviewList.message",hasItem("I like the color of this food"))
                .body("reviewList.username",hasItem("user111"));
    }

    @Test
    @Order(6)
    public void testSearchProductContainsName_Apple() {
        // search
        RestAssured.given().when().get("/api/products/search?name=Apple")
                .then()
                .contentType(ContentType.JSON)
                .log().body()
                .and()
                .body("products.productNumber", hasItem("P991234567"))
                .body("products.name",hasItem("Blue Apple"));
    }

    @Test
    @Order(7)
    public void testDeleteProduct_P991234567() {
        given().when().delete("/api/products/P991234567").then().statusCode(204);
    }
    @Test
    @Order(9)
    public void testDeleteProduct_P991234568() {
        given().when().delete("/api/products/P991234568").then().statusCode(204);
    }

}
