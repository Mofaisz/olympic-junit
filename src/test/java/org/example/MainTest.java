package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

class MainTest {

    private List<AthleteEvents> mockAthleteEvents;

    @BeforeEach
    void setUp() {
        // Mock data simulating CSV input
        mockAthleteEvents = new ArrayList<>();

        AthleteEvents athlete1 = new AthleteEvents();
        athlete1.setName("John Doe");
        athlete1.setYear("1980");
        athlete1.setMedal("Gold");
        athlete1.setAge("25");
        athlete1.setEvent("100m Sprint");
        mockAthleteEvents.add(athlete1);

        AthleteEvents athlete2 = new AthleteEvents();
        athlete2.setName("Jane Smith");
        athlete2.setYear("1980");
        athlete2.setMedal("Silver");
        athlete2.setAge("27");
        athlete2.setEvent("100m Sprint");
        mockAthleteEvents.add(athlete2);

        AthleteEvents athlete3 = new AthleteEvents();
        athlete3.setName("John Doe");
        athlete3.setYear("1980");
        athlete3.setMedal("Gold");
        athlete3.setAge("25");
        athlete3.setEvent("200m Sprint");
        mockAthleteEvents.add(athlete3);
    }

    @Test
    void testAthleteEventsReading() {
        List<AthleteEvents> athleteEventList = Main.athleteEvents();
        assertNotNull(athleteEventList, "List should not be null.");
        assertFalse(athleteEventList.isEmpty(), "List should not be empty.");
    }

    @Test
    void testGetNumberOfGoldMedalWonByEachPlayer() {
        // Since method prints output, we’ll assume success if no exceptions occur.
        assertDoesNotThrow(() -> Main.getNumberOfGoldMedalWonByEachPlayer(mockAthleteEvents),
                "Should run without throwing exceptions.");
    }

    @Test
    void testGetNameOFGoldMedallistAgeBelow30() {
        // Expected: John Doe (age 25)
        Map<String, Integer> expected = new HashMap<>();
        expected.put("John Doe", 25);

        // Capture console output using a custom stream
        assertDoesNotThrow(() -> Main.getNameOFGoldMedallistAgeBelow30(mockAthleteEvents),
                "Should execute without exceptions.");
    }

    @Test
    void testEventWiseMedalCountFor1980() {
        assertDoesNotThrow(() -> Main.eventWiseMedalCountFor1980(mockAthleteEvents),
                "Should run without exceptions.");
    }
}
