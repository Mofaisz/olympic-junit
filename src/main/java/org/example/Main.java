package org.example;
import java.io.*;
import java.util.*;


public class Main {
    // constant defination
    private static final int ID = 0;
    private static final int NAME = 1;
    private static final int SEX = 2;
    private static final int AGE = 3;
    private static final int HEIGHT = 4;
    private static final int WEIGHT = 5;
    private static final int TEAM = 6;
    private static final int NOC = 7;
    private static final int GAMES = 8;
    private static final int YEAR = 9;
    private static final int SEASON = 10;
    private static final int CITY = 11;
    private static final int SPORT = 12;
    private static final int EVENT = 13;
    private static final int MEDAL = 14;

    public static void main(String[] args) {

        List<AthleteEvents> athleteEventList = athleteEvents();

        // uncomment below to run the method
         getNumberOfGoldMedalWonByEachPlayer(athleteEventList);
        // getNameOFGoldMedallistAgeBelow30(athleteEventList);
        // eventWiseMedalCountFor1980(athleteEventList);
        // displayGoldWinnersOfFootball(athleteEventList);
        // femaleAthleteWithMostGoldMedals(athleteEventList);
//         athletesWithMoreThanThreeOlympics(athleteEventList);
//         EventWithMostGoldMedalEveryYear(athleteEventList);
//        goldMedalWinnersIn1992And1996(athleteEventList);
    }

    // Method to read CSV and return list of AthleteEvents
    public static List<AthleteEvents> athleteEvents() {

        // file path and list intitalization
        String path = "/home/faiz/IdeaProjects/olympic-junit/data/athlete_events.csv";
        List<AthleteEvents> athleteEventList = new ArrayList<>();

        try {
            // Setting up the read file
            File file = new File(path);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader); // read one line at a time.

            // reads each line of CSV
            String readsline;
            while ((readsline = bufferedReader.readLine()) != null) {
                String[] data = readsline.split(",");

                // Creating an AthleteEvents Object and Setting Its Properties:
                AthleteEvents athleteEvent = new AthleteEvents();
                athleteEvent.setID(data[ID]);
                athleteEvent.setName(data[NAME]);
                athleteEvent.setSex(data[SEX]);
                athleteEvent.setAge(data[AGE]);
                athleteEvent.setHeight(data[HEIGHT]);
                athleteEvent.setWeight(data[WEIGHT]);
                athleteEvent.setTeam(data[TEAM]);
                athleteEvent.setNOC(data[NOC]);
                athleteEvent.setGames(data[GAMES]);
                athleteEvent.setYear(data[YEAR]);
                athleteEvent.setSeason(data[SEASON]);
                athleteEvent.setCity(data[CITY]);
                athleteEvent.setSport(data[SPORT]);
                athleteEvent.setEvent(data[EVENT]);
                athleteEvent.setMedal(data[MEDAL]);

                // adding object to the list
                athleteEventList.add(athleteEvent);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return athleteEventList;
    }

    // 1.display Year Wise Number of Gold medals WonBy each player
    public static void getNumberOfGoldMedalWonByEachPlayer(List<AthleteEvents> athleteEventList) {

        Map<String, Map<String, Integer>> numberOfGoldMedalWonByPlayer = new TreeMap<>();

        for (AthleteEvents athleteEvent : athleteEventList) {
            String year = athleteEvent.getYear();
            if (athleteEvent.getMedal().contains("Gold")) {
                if (!numberOfGoldMedalWonByPlayer.containsKey(year)) {
                    numberOfGoldMedalWonByPlayer.put(year, new HashMap<>());
                }

                Map<String, Integer> player = numberOfGoldMedalWonByPlayer.get(year);
                player.put(athleteEvent.getName(), player.getOrDefault(athleteEvent.getName(), 0) + 1);
            }
        }

        for (Map.Entry<String, Map<String, Integer>> entry : numberOfGoldMedalWonByPlayer.entrySet()) {
            String year = entry.getKey();
            System.out.println(year + ":");
            Map<String, Integer> dataValue = entry.getValue();
            System.out.println("[");
            for (Map.Entry<String, Integer> value : dataValue.entrySet()) {
                System.out.println(value.getKey() + value.getValue());
            }
            System.out.println("]");
        }

    }

    // 2.display Athletes Who Won GoldMedal In 1980 And AgeIsLess Than 30Years

    public static void getNameOFGoldMedallistAgeBelow30(List<AthleteEvents> athleteEventList) {

        Map<String, Integer> namesAndAge = new HashMap<>();
        for (AthleteEvents athleteEvent : athleteEventList) {
            String yearStr = athleteEvent.getYear();
            String ageStr = athleteEvent.getAge();
            String goldStr = athleteEvent.getMedal();
            if (!ageStr.equals(null) && !ageStr.isEmpty() && yearStr.contains("1980") && goldStr.contains("Gold")) {
                int age = Integer.parseInt(ageStr);
                if (age < 30) {
                    int ages = Integer.parseInt(athleteEvent.getAge());
                    namesAndAge.put(athleteEvent.getName(), ages);

                }
            }
        }
        // System.out.println(namesAndAge);
        for (Map.Entry<String, Integer> entry : namesAndAge.entrySet()) {
            Object key = entry.getKey();
            Object val = entry.getValue();
            System.out.println(key + ":" + val);

        }
    }

    // 3.event Wise Number Of Gold Silver Bronze Medals In Year1980
    public static void eventWiseMedalCountFor1980(List<AthleteEvents> athleteEventList) {
        Map<String, Map<String, Integer>> eventAndMedals = new HashMap<>();

        for (AthleteEvents athleteEvent : athleteEventList) {
            if (athleteEvent.getYear().contains("1980")) {
                if (!athleteEvent.getMedal().contains("NA")) {
                    String event = athleteEvent.getEvent();
                    if (!eventAndMedals.containsKey(event)) {
                        eventAndMedals.put(event, new HashMap<>());
                    }
                    Map<String, Integer> medalsCount = eventAndMedals.get(event);
                    if (!medalsCount.containsKey(athleteEvent.getMedal())) {
                        medalsCount.put(athleteEvent.getMedal(), 0);
                    }
                    medalsCount.put(athleteEvent.getMedal(), medalsCount.get(athleteEvent.getMedal()) + 1);
                }

            }

        }

        for (Map.Entry<String, Map<String, Integer>> entry : eventAndMedals.entrySet()) {
            System.out.println("Event : " + entry.getKey());
            entry.getValue().forEach((medal, count) -> {
                System.out.println(medal + " :" + count);
            });
            System.out.println();

        }

    }

    // 4.display Gold Winner Of Football Of EveryOlympic
    public static void displayGoldWinnersOfFootball(List<AthleteEvents> athleteEventList) {

        Map<String, Set<String>> goldWinner = new TreeMap<>();

        for(AthleteEvents athleteEvent : athleteEventList){
            if(athleteEvent.getSport().contains("Football") && athleteEvent.getMedal().contains("Gold")){
                goldWinner.putIfAbsent(athleteEvent.getYear(), new HashSet<>());
                goldWinner.get(athleteEvent.getYear()).add(athleteEvent.getTeam());

            }
        }

        // System.out.println(goldWinner);

        for (Map.Entry<String, Set<String>> entry : goldWinner.entrySet()) {
            Object key = entry.getKey();
            Object val = entry.getValue();
            System.out.println(key + ":" + val);

        }



    }

    // 5.Find the female athlete with the maximum gold medals.
    public static void femaleAthleteWithMostGoldMedals(List<AthleteEvents> athleteEventList) {

        Map<String,Integer> mostFemaleGoldMedallist = new HashMap<>();

        for(AthleteEvents athleteEvent : athleteEventList){
            if(athleteEvent.getMedal().contains("Gold") && athleteEvent.getSex().contains("F")){
                mostFemaleGoldMedallist.put(athleteEvent.getName(),mostFemaleGoldMedallist.getOrDefault(athleteEvent.getName(), 0)+1);
            }
        }
        // System.out.println(mostFemaleGoldMedallist)
        String nameStr = "";
        int count = 0;

        for (Map.Entry<String, Integer> en : mostFemaleGoldMedallist.entrySet()) {
            String key = en.getKey();
            Integer val = en.getValue();

            if(val > count){
                count = val;
                nameStr = key;
            }

        }
        System.out.println(nameStr + ":" + count);
    }

    // 6.name Of Athlete Participated In More Than Three Olympics
    public static void athletesWithMoreThanThreeOlympics(List<AthleteEvents> athleteEventList) {
        Map<String,Set<String>> athleteMoreThanThreeYear = new HashMap<>();

        for(AthleteEvents athleteEvent:athleteEventList){
            athleteMoreThanThreeYear.putIfAbsent(athleteEvent.getName(), new HashSet<>());
            athleteMoreThanThreeYear.get(athleteEvent.getName()).add(athleteEvent.getYear());

        }

        for (Map.Entry<String, Set<String>> en : athleteMoreThanThreeYear.entrySet()) {
            String key = en.getKey();

            if(en.getValue().size()>3){
                System.out.println(key + " "+ en.getValue().size() + "times participated in olympics");
            }

        }

    }


    //display the athelete names who won gold medal in 1992 and 1996
    public static void goldMedalWinnersIn1992And1996(List<AthleteEvents> athleteEventList) {
        Map<String, Integer> athleteGoldYears = new HashMap<>();

        for (AthleteEvents athlete : athleteEventList) {
            if (athlete.getMedal().contains("Gold")) { // Check only Gold medalists
                String name = athlete.getName();
                String year = athlete.getYear();

                if (year.contains("1992") || year.contains("1996")) { // Only track 1992 & 1996
                    int currentYears = athleteGoldYears.getOrDefault(name, 0);

                    // Update the tracking value using bitwise OR
                    if (year.contains("1992")) currentYears |= 1;
                    if (year.contains("1996")) currentYears |= 2;

                    athleteGoldYears.put(name, currentYears);
                }
            }
        }

        // Print names of athletes who won gold in both years (1 | 2 = 3)
        System.out.println("Athletes who won Gold in both 1992 and 1996:");
        for (Map.Entry<String, Integer> entry : athleteGoldYears.entrySet()) {
            if (entry.getValue() == 3) { // 3 means both years are present (1 | 2)
                System.out.println("  " + entry.getKey());
            }
        }
    }



}
