package Days;

import Interfaces.IAOCPuzzle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Day1 implements IAOCPuzzle<ArrayList<ArrayList<Integer>>, Integer> {

    public Day1() {

    }

    public Integer part1() {
        ArrayList<ArrayList<Integer>> input = getPuzzleInput(getClass().getSimpleName());
        return part1(input);
    }

    public Integer part2() {
        ArrayList<ArrayList<Integer>> input = getPuzzleInput(getClass().getSimpleName());
        return part2(input);
    }

    public ArrayList<ArrayList<Integer>> getPuzzleInput(String filename) {
        ArrayList<ArrayList<Integer>> input = new ArrayList<>();

        ArrayList<Integer> column1 = new ArrayList<>();
        ArrayList<Integer> column2 = new ArrayList<>();

        String inputFile = "C:\\Users\\morle\\IdeaProjects\\AdventOfCode\\input\\" + filename + ".txt";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));

            String line;
            while((line = reader.readLine()) != null) {

                String[] split = line.split("\\s+");

                column1.add(Integer.parseInt(split[0]));
                column2.add(Integer.parseInt(split[1]));
            }

        } catch(IOException ioe) {
            throw new RuntimeException(ioe);
        }

        input.add(column1);
        input.add(column2);

        return input;
    }

    private Integer part1(ArrayList<ArrayList<Integer>> input) {
        Collections.sort(input.get(0));
        Collections.sort(input.get(1));

        int answer = 0;
        for(int i = 0; i < input.get(0).size(); i++) {
            answer += Math.abs(input.get(0).get(i) - input.get(1).get(i));
        }
        return answer;
    }

    private Integer part2(ArrayList<ArrayList<Integer>> input) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for(Integer key : input.get(0)) {
            if(!map.containsKey(key)) {
                map.put(key, 0);
            }
        }

        for(Integer value : input.get(1)) {
            if(map.containsKey(value)) {
                int count = map.get(value);
                ++count;
                map.put(value, count);
            }
        }

        int answer = 0;
        for(Integer i : input.get(0)) {
            if(map.containsKey(i)) {
                answer += (i * map.get(i));
            }
        }
        return answer;
    }
}