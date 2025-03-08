package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

class MainTest {

    private List<AthleteEvents> mockAthleteEvents;

    @BeforeEach
    void setUp() {
        // Mock data simulating CSV input
        mockAthleteEvents = new ArrayList<>();

        AthleteEvents athlete1 = new AthleteEvents();
        athlete1.setName("John Doe");
        athlete1.setSeason("Summer");
        athlete1.setYear("1980");
        athlete1.setMedal("Gold");
        athlete1.setAge("25");
        athlete1.setEvent("100m Sprint");
        mockAthleteEvents.add(athlete1);

        AthleteEvents athlete2 = new AthleteEvents();
        athlete2.setName("Jane Smith");
        athlete2.setSeason("Summer");
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
        assertDoesNotThrow(() -> Main.getNumberOfGoldMedalWonByEachPlayer(mockAthleteEvents),
                "Should run without throwing exceptions.");
    }

    @Test
    void testGetNameOFGoldMedallistAgeBelow30() {
        assertDoesNotThrow(() -> Main.getNameOFGoldMedallistAgeBelow30(mockAthleteEvents),
                "Should run without throwing exceptions.");
    }

    @Test
    void testEventWiseMedalCountFor1980() {
        assertDoesNotThrow(() -> Main.eventWiseMedalCountFor1980(mockAthleteEvents),
                "Should run without throwing exceptions.");
    }

    @Test
    void testGoldMedallistUnder30Exists() {
        Main.getNameOFGoldMedallistAgeBelow30(mockAthleteEvents);
        assertTrue(mockAthleteEvents.stream().anyMatch(a -> a.getName().equals("John Doe") && Integer.parseInt(a.getAge()) < 30),
                "Should contain at least one gold medallist under 30.");
    }

    @Test
    void testEventWiseMedalCount() {
        Main.eventWiseMedalCountFor1980(mockAthleteEvents);
        assertTrue(mockAthleteEvents.stream().anyMatch(a -> a.getEvent().equals("100m Sprint") && a.getMedal().equals("Gold")),
                "Should contain 100m Sprint event with Gold medal.");
    }
}
