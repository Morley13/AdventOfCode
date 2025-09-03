package Days;

import Interfaces.IAOCPuzzle;

import java.io.*;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 implements IAOCPuzzle<String, Integer> {

    public Day3() {
    }

    public Integer part1() {
        String input = getPuzzleInput(getClass().getSimpleName());
        return part1(input);
    }

    public Integer part2() {
        String input = getPuzzleInput(getClass().getSimpleName());
        return part2(input);
    }

    public String getPuzzleInput(String filename) {
        String input = "";

        String inputFile = "C:\\Users\\morle\\IdeaProjects\\AdventOfCode\\Input\\" + filename + ".txt";
        try {

            BufferedReader reader = new BufferedReader(new FileReader(new File(inputFile)));

            String line;
            while((line = reader.readLine()) != null) {
                input += line;
            }

        } catch(IOException ioe) {
            throw new RuntimeException(ioe);
        }
        return input;
    }

    private Integer part1(String input) {
        Pattern pattern = Pattern.compile("mul\\([0-9]{1,3},[0-9]{1,3}\\)");
        Matcher matcher = pattern.matcher(input);

        int sum = 0;
        while(matcher.find()) {
            String group = matcher.group();

            group = group.replace("mul(", "");
            group = group.replace(")", "");

            sum += Arrays.stream(group.split(","))
                    .mapToInt(Integer::parseInt)
                    .reduce(1, (a,b) -> a * b);
        }
        return sum;
    }


    private Integer part2(String input) {
        Pattern pattern = Pattern.compile("mul\\([0-9]{1,3},[0-9]{1,3}\\)|do\\(\\)|don't\\(\\)");
        Matcher matcher = pattern.matcher(input);

        boolean enabled = true;
        int sum = 0;
        while(matcher.find()) {
            String group = matcher.group();

            if(group.matches("do\\(\\)")) {
                enabled = true;
            } else if(group.matches("don't\\(\\)")) {
                enabled = false;
            } else {
                if(enabled) {
                    group = group.replace("mul(", "");
                    group = group.replace(")", "");

                    sum += Arrays.stream(group.split(","))
                            .mapToInt(Integer::parseInt)
                            .reduce(1, (a,b) -> a * b);
                }
            }
        }
        return sum;
    }
}