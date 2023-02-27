package com.example.demo.controller;




import com.example.demo.model.Workout;
import com.example.demo.security.JwtAuthConverter;
import com.example.demo.security.JwtAuthConverterProperties;
import com.example.demo.service.WorkoutService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WorkoutController.class)
public class WorkoutControllerTests {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private WorkoutService workoutServiceMock;

    @TestConfiguration
    static class TestConfig {
        @Bean
        public JwtAuthConverterProperties getJwtAuthConverterProperties() {
            return new JwtAuthConverterProperties();
        }
    }



    /**
     * Testing of WorkoutController controller module.
     * GET call for Unauthorized user
     *
     * @throws Exception
     */
    @Test
    //@WithMockUser(username = "gculumr")
    public void getAllworkoutsUnauthorized() throws Exception
    {
        List<Workout> workoutMock = mockWorkouts();
        when(workoutServiceMock.findWorkouts()).thenReturn(workoutMock);


        mvc.perform(MockMvcRequestBuilders.get("/api/v1/workout")
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andReturn();
    }


    @Test
    @WithMockUser(username = "gculum")
    public void getAllworkouts() throws Exception
    {
        List<Workout> workoutMock = mockWorkouts();
        when(workoutServiceMock.findWorkouts()).thenReturn(workoutMock);


        mvc.perform(MockMvcRequestBuilders.get("/api/v1/workout")
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("[0].user").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("[0].start").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("[0].end").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("[0].difficulty").exists());
    }


    /**
     * Mock factory method used by controller test
     * method
     *
     * @return list of Workout objects
     */
    private List<Workout> mockWorkouts() {
        List<Workout> workoutList = new ArrayList<>();

        Workout workout1 = new Workout();
        workout1.setId(1);
        workout1.setUser("gculum");
        workout1.setStart(LocalDateTime.of(2023, Month.JANUARY, 12, 19, 30, 40));
        workout1.setEnd(LocalDateTime.of(2023, Month.JANUARY, 12, 20, 30, 40));
        workout1.setDifficulty(3);
        workoutList.add(workout1);

        Workout workout2 = new Workout();
        workout2.setId(1);
        workout2.setUser("gculum");
        workout2.setStart(LocalDateTime.of(2023, Month.JANUARY, 13, 19, 30, 40));
        workout2.setEnd(LocalDateTime.of(2023, Month.JANUARY, 13, 20, 30, 40));
        workout2.setDifficulty(3);
        workoutList.add(workout2);

        return workoutList;

    }
}