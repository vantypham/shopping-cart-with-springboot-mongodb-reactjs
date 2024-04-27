package com.webstore.test;

import com.webstore.service.dto.*;
import com.webstore.web.OrderStatus;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderTest {


    @BeforeClass
    public static void setup() {
        RestAssured.port = Integer.valueOf(8080);
        RestAssured.baseURI = "http://localhost";
        RestAssured.basePath = "";
    }


    @Test
    @Order(1)
    public void testCreateOrder_111() {
        OrderDTO dto = new OrderDTO();
        dto.setOrderId("ORDER_111");
        dto.setTotalAmount(22.0);
        PersonalInfoDTO personalInfoDTO = new PersonalInfoDTO("Bob Train", "bobtrain111@abc.com", "16421233999", "1000 N 4th ST", "Fairfield", "52557");
        dto.setPersonalInfo(personalInfoDTO);
        PaymentInfoDTO paymentInfoDTO = new PaymentInfoDTO("Visa", "4664-1234-2345-2345", "12/2030", "123");
        dto.setPaymentInfo(paymentInfoDTO);
        List<OrderItemDTO> orderItemList = new ArrayList<>();
        OrderItemDTO redApple = new OrderItemDTO("P991234567", "Red Apple", 2, 3.5);
        OrderItemDTO blueApple = new OrderItemDTO("P991234568", "Blue Apple", 5, 3.0);
        orderItemList.add(redApple);
        orderItemList.add(blueApple);
        dto.setOrderItemList(orderItemList);

        given().contentType("application/json")
                .body(dto)
                .when()
                .post("/api/orders")
                .then()
                .log().body().statusCode(200);
    }
    @Test
    @Order(2)
    public void testCreateOrder_222() {
        OrderDTO dto = new OrderDTO();
        dto.setOrderId("ORDER_222");
        dto.setTotalAmount(7.5);
        PersonalInfoDTO personalInfoDTO = new PersonalInfoDTO("Mike", "mike@abc.com", "6412330000", "200 W Burlington", "Fairfield", "52556");
        dto.setPersonalInfo(personalInfoDTO);
        PaymentInfoDTO paymentInfoDTO = new PaymentInfoDTO("Mastercard", "4664-1234-2345-8888", "10/2029", "674");
        dto.setPaymentInfo(paymentInfoDTO);
        List<OrderItemDTO> orderItemList = new ArrayList<>();
        OrderItemDTO redApple = new OrderItemDTO("P991234567", "Red Apple", 1, 3.5);
        OrderItemDTO blueApple = new OrderItemDTO("P991234568", "Blue Apple", 1, 3.0);
        orderItemList.add(redApple);
        orderItemList.add(blueApple);
        dto.setOrderItemList(orderItemList);

        given().contentType("application/json")
                .body(dto)
                .when()
                .post("/api/orders")
                .then()
                .log().body().statusCode(200);
    }

    @Test
    @Order(3)
    public void testGetOrder_222() {
        RestAssured.given()
                .when()
                .get("/api/orders/ORDER_222")
                .then()
                .contentType(ContentType.JSON)
                .log().body()
                .and()
                .body("totalAmount",equalTo(7.5F))
                .body("status",equalTo("PLACED"));
    }
    @Test
    @Order(4)
    public void testChangeStatus_222_to_SHIPPED() {
        OrderStatus commandObj = new OrderStatus("SHIPPED");
        given().contentType("application/json")
                .body(commandObj)
                .when().post("/api/orders/ORDER_222")
                .then()
                .log().body().statusCode(200);
    }
    @Test
    @Order(5)
    public void testSearchOrdersByStatus_PLACED() {
            // search
            RestAssured.given().when().get("/api/orders/search?status=PLACED")
                    .then()
                    .contentType(ContentType.JSON)
                    .log().body()
                    .and()
                    .body("orderList.orderId", hasItem("ORDER_111"))
                    .body("orderList.personalInfo.name",hasItem("Bob Train"));
        }

    @Test
    @Order(6)
    public void testSearchOrdersByStatus_SHIPPED() {
        // search
        RestAssured.given().when().get("/api/orders/search?status=SHIPPED")
                .then()
                .contentType(ContentType.JSON)
                .log().body()
                .and()
                .body("orderList.orderId", hasItem("ORDER_222"))
                .body("orderList.personalInfo.name",hasItem("Mike"));
    }

    @Test
    @Order(8)
    public void testDeleteOrder_111() {
        // delete
        RestAssured.given().when().delete("/api/orders/ORDER_111")
                .then()
                .log().body().statusCode(204);
    }
    @Test
    @Order(9)
    public void testDeleteOrder_222() {
        // delete
        RestAssured.given().when().delete("/api/orders/ORDER_222")
                .then()
                .log().body().statusCode(204);
    }



}
