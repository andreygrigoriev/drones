package com.example.drones;

import com.example.drones.repository.DroneRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { TestConfig.class })
@AutoConfigureMockMvc
@ActiveProfiles({"test"})
public class ApiControllerIntegrationTests {
   private static final String PATH_REGISTER = "/api/v1/drones/register";
   private static final String PATH_LOAD_ITEMS = "/api/v1/drones/%s/load";
   @Autowired
   @SuppressWarnings("unused")
   private MockMvc mvc;

   @Autowired
   @SuppressWarnings("unused")
   private DroneRepository droneRepository;

   @Test
   public void simpleTest_db_has_been_initialized() {
      Assertions.assertThatIterable(droneRepository.findAll()).hasSizeGreaterThan(0);
   }

   @Test
   public void givenDroneWithNegativeBatteryCapacity_register() throws Exception {
      String json = """
            {
                "serialNumber": "1234567",
                "model": "LIGHTWEIGHT",
                "batteryCapacity": -55,
                "state": "IDLE"
            }""";
      mvc.perform(MockMvcRequestBuilders.post(PATH_REGISTER)
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(json)
                  .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().is4xxClientError())
            .andExpect(result -> Assertions.assertThat(result.getResolvedException().getMessage()).startsWith("Validation failed"));
   }

   @Test
   public void givenDroneWithEmptySerialNumber_register() throws Exception {
      String json = """
            {
                "serialNumber": "",
                "model": "LIGHTWEIGHT",
                "batteryCapacity": 55,
                "state": "IDLE"
            }""";
      mvc.perform(MockMvcRequestBuilders.post(PATH_REGISTER)
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(json)
                  .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().is4xxClientError())
            .andExpect(result -> Assertions.assertThat(result.getResolvedException().getMessage()).startsWith("Validation failed"));
   }

   @Test
   public void givenDroneWithTooLargeSerialNumber_register() throws Exception {
      String json = """
            {
                "serialNumber": "1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111",
                "model": "LIGHTWEIGHT",
                "batteryCapacity": 55,
                "state": "IDLE"
            }""";
      mvc.perform(MockMvcRequestBuilders.post(PATH_REGISTER)
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(json)
                  .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().is4xxClientError())
            .andExpect(result -> Assertions.assertThat(result.getResolvedException().getMessage()).startsWith("Validation failed"));
   }

   @Test
   public void givenValidDrone_register_two_times() throws Exception {
      String json = """
            {
                "serialNumber": "234567",
                "model": "LIGHTWEIGHT",
                "batteryCapacity": 55,
                "state": "IDLE"
            }""";
      mvc.perform(MockMvcRequestBuilders.post(PATH_REGISTER)
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(json)
                  .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

      mvc.perform(MockMvcRequestBuilders.post(PATH_REGISTER)
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(json)
                  .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(result -> Assertions.assertThat(result.getResolvedException().getMessage()).startsWith("Drone with serial number 234567 was already registered"));
   }

   @Test
   public void givenValidDrone_register() throws Exception {
      String json = """
            {
                "serialNumber": "1234567",
                "model": "LIGHTWEIGHT",
                "batteryCapacity": 55,
                "state": "IDLE"
            }""";
      mvc.perform(MockMvcRequestBuilders.post(PATH_REGISTER)
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(json)
                  .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
   }

   @Test
   public void givenInvalidDroneAndValidMedication_load_items() throws Exception {
      String path = String.format(PATH_LOAD_ITEMS, "12445");
      String json = """
            {
                "name": "ibuprofen",
                "weight": 40,
                "code": "IBP_0101",
                "picture": "https://drsilvasultrawellness.com/wp-content/uploads/2019/04/00749603_dghl_ibuprofen_brown_500ct.jpg"
            }""";
      mvc.perform(MockMvcRequestBuilders.post(path)
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(json)
                  .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
   }

   @Test
   public void givenValidDroneAndValidMedication_load_too_much_weight() throws Exception {
      String path = String.format(PATH_LOAD_ITEMS, "123");
      String json = """
            {
                "name": "Xanax",
                "weight": 500,
                "code": "XNX_0104",
                "picture": "https://drsilvasultrawellness.com/wp-content/uploads/2019/04/00749603_dghl_ibuprofen_brown_500ct.jpg"
            }""";
      mvc.perform(MockMvcRequestBuilders.post(path)
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(json)
                  .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
   }

   @Test
   public void givenValidDroneAndValidMedication_load_items() throws Exception {
      String path = String.format(PATH_LOAD_ITEMS, "123");
      String json = """
            {
                "name": "ibuprofen",
                "weight": 40,
                "code": "IBP_0101",
                "picture": "https://drsilvasultrawellness.com/wp-content/uploads/2019/04/00749603_dghl_ibuprofen_brown_500ct.jpg"
            }""";
      mvc.perform(MockMvcRequestBuilders.post(path)
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(json)
                  .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
   }
}
