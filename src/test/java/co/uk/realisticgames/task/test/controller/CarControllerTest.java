package co.uk.realisticgames.task.test.controller;

import co.uk.realisticgames.task.model.api.CarDataItem;
import com.fasterxml.jackson.core.type.TypeReference;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * CarControllerTest class.
 *
 * @author Paulius Matulionis
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CarControllerTest extends AbstractControllerTest {

    @Test
    @Order(1)
    public void testFindAllCarData()
            throws Exception {
        mvc
                .perform(get(API_PATH + "/car-data"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$..id").exists());
    }

    @Test
    @Order(2)
    public void testFindAllSortedCarData()
            throws Exception {
        mvc
                .perform(get(API_PATH + "/car-data?sort=country:desc"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].country").value("United States (USA)"))
                .andExpect(jsonPath("$.[0].commonName").value("Tesla"))
                .andExpect(jsonPath("$.[0].name").value("TESLA, INC."));

        mvc
                .perform(get(API_PATH + "/car-data?sort=country:asc"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].country").value(""))
                .andExpect(jsonPath("$.[0].commonName").value("Quest Manufacturing"))
                .andExpect(jsonPath("$.[0].name").value("Quest Manufacturing"));

        mvc
                .perform(get(API_PATH + "/car-data?sort=country"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].country").value(""))
                .andExpect(jsonPath("$.[0].commonName").value("Quest Manufacturing"))
                .andExpect(jsonPath("$.[0].name").value("Quest Manufacturing"));

        mvc
                .perform(get(API_PATH + "/car-data?sort=name:desc"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].country").value("China"))
                .andExpect(jsonPath("$.[0].commonName").value("Geely"))
                .andExpect(jsonPath("$.[0].name").value("ZHEJIANG GEELY HOLDING GROUP CO.,LTD"));

        mvc
                .perform(get(API_PATH + "/car-data?sort=name:asc"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].country").value("United States (USA)"))
                .andExpect(jsonPath("$.[0].commonName").value("ACG"))
                .andExpect(jsonPath("$.[0].name").value("AMERICAN CUSTOM GOLF CARS, INC"));

        mvc
                .perform(get(API_PATH + "/car-data?sort=name:invalid"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].country").value("United States (USA)"))
                .andExpect(jsonPath("$.[0].commonName").value("ACG"))
                .andExpect(jsonPath("$.[0].name").value("AMERICAN CUSTOM GOLF CARS, INC"));

        mvc
                .perform(get(API_PATH + "/car-data?sort=commonName:asc"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].country").value("United States (USA)"))
                .andExpect(jsonPath("$.[0].commonName").value("Tesla"))
                .andExpect(jsonPath("$.[0].name").value("TESLA, INC."));

        mvc
                .perform(get(API_PATH + "/car-data?sort=commonName"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].country").value("United States (USA)"))
                .andExpect(jsonPath("$.[0].commonName").value("Tesla"))
                .andExpect(jsonPath("$.[0].name").value("TESLA, INC."));

        mvc
                .perform(get(API_PATH + "/car-data?sort="))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].country").value("United States (USA)"))
                .andExpect(jsonPath("$.[0].commonName").value("Tesla"))
                .andExpect(jsonPath("$.[0].name").value("TESLA, INC."));
    }

    @Test
    @Order(3)
    public void testSearch()
            throws Exception {
        mvc
                .perform(get(API_PATH + "/car-data?country=China&sort=country"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$..['country']", hasSize(2)))

                .andExpect(jsonPath("$.[0].id").value(963))
                .andExpect(jsonPath("$.[0].country").value("China"))
                .andExpect(jsonPath("$.[0].commonName").value("Jialing"))
                .andExpect(jsonPath("$.[0].name").value("SHANGHAI JIALING VEHICLE BUSINESS CO., LTD"))

                .andExpect(jsonPath("$.[1].id").value(1011))
                .andExpect(jsonPath("$.[1].country").value("China"))
                .andExpect(jsonPath("$.[1].commonName").value("Geely"))
                .andExpect(jsonPath("$.[1].name").value("ZHEJIANG GEELY HOLDING GROUP CO.,LTD"));

        mvc
                .perform(get(API_PATH + "/car-data?name=AMERICAN CUSTOM GOLF CARS, INC"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$..['commonName']", hasSize(2)))

                .andExpect(jsonPath("$.[0].id").value(973))
                .andExpect(jsonPath("$.[0].country").value("United States (USA)"))
                .andExpect(jsonPath("$.[0].commonName").value("ACG"))
                .andExpect(jsonPath("$.[0].name").value("AMERICAN CUSTOM GOLF CARS, INC"))

                .andExpect(jsonPath("$.[1].id").value(973))
                .andExpect(jsonPath("$.[1].country").value("United States (USA)"))
                .andExpect(jsonPath("$.[1].commonName").value("ACG"))
                .andExpect(jsonPath("$.[1].name").value("AMERICAN CUSTOM GOLF CARS, INC"));

        mvc
                .perform(get(API_PATH + "/car-data?type=Incomplete Vehicle&name=NAVISTAR, INC."))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$..['commonName']", hasSize(2)));

        mvc
                .perform(get(API_PATH + "/car-data?type=Incomplete Vehicle"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$..['commonName']", hasSize(15)));
    }

    @Test
    @Order(4)
    public void testCreate()
            throws Exception {
        mvc
                .perform(get(API_PATH + "/car-data"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$..['id']", hasSize(100)));

        mvc
                .perform(post(API_PATH + "/car-data")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "commonName": "TestCommonName",
                                  "country": "TestCountry",
                                  "id": 123456789,
                                  "name": "Tester",
                                  "types": [
                                    {
                                      "name": "TestingType",
                                      "primary": true
                                    }
                                  ]
                                }""")
                        .accept(MediaType.APPLICATION_JSON))

                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("ok"));

        mvc
                .perform(get(API_PATH + "/car-data"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$..['id']", hasSize(101)));

        mvc
                .perform(get(API_PATH + "/car-data?name=Tester"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$..['commonName']", hasSize(1)))
                .andExpect(jsonPath("$.[0].id").value(123456789))
                .andExpect(jsonPath("$.[0].country").value("TestCountry"))
                .andExpect(jsonPath("$.[0].commonName").value("TestCommonName"))
                .andExpect(jsonPath("$.[0].name").value("Tester"))
                .andExpect(jsonPath("$.[0].types[0].primary").value(true))
                .andExpect(jsonPath("$.[0].types[0].name").value("TestingType"));
    }

    @Test
    @Order(5)
    public void testUpdate()
            throws Exception {
        mvc
                .perform(get(API_PATH + "/car-data?name=BLUE DIAMOND TRUCK, S. DE R. L. DE C. V."))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$..['commonName']", hasSize(1)));

        mvc
                .perform(put(API_PATH + "/car-data/985")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "country": "Mexico Updated",
                                  "commonName": "Blue Diamond Updated",
                                  "name": "BLUE DIAMOND TRUCK, S. DE R. L. DE C. V. Updated",
                                  "types": [
                                    {
                                      "primary": true,
                                      "name": "Incomplete Vehicle Updated"
                                    }
                                  ]
                                }""")
                        .accept(MediaType.APPLICATION_JSON))

                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("ok"));

        mvc
                .perform(get(API_PATH + "/car-data?name=BLUE DIAMOND TRUCK, S. DE R. L. DE C. V. Updated"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$..['commonName']", hasSize(1)))
                .andExpect(jsonPath("$.[0].id").value(985))
                .andExpect(jsonPath("$.[0].country").value("Mexico Updated"))
                .andExpect(jsonPath("$.[0].commonName").value("Blue Diamond Updated"))
                .andExpect(jsonPath("$.[0].name").value("BLUE DIAMOND TRUCK, S. DE R. L. DE C. V. Updated"))
                .andExpect(jsonPath("$.[0].types[0].primary").value(true))
                .andExpect(jsonPath("$.[0].types[0].name").value("Incomplete Vehicle Updated"));
    }

    @Test
    @Order(6)
    public void testDelete()
            throws Exception {
        MvcResult result = mvc
                .perform(get(API_PATH + "/car-data?name=FREIGHTLINER CUSTOM CHASSIS CORP."))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$..['country']", hasSize(1)))
                .andReturn();

        List<CarDataItem> cars = objectMapper.readValue(result.getResponse().getContentAsByteArray(), new TypeReference<>() {
        });

        CarDataItem carDataItem = cars.get(0);

        mvc
                .perform(delete(API_PATH + "/car-data/" + carDataItem.getId()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("ok"));

        mvc
                .perform(get(API_PATH + "/car-data?name=FREIGHTLINER CUSTOM CHASSIS CORP."))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$..['country']", hasSize(0)));
    }
}
