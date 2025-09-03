package Days;

import Interfaces.IAOCPuzzle;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day2 implements IAOCPuzzle<ArrayList<List<Integer>>, Integer> {

    public Day2() {
    }

    public Integer part1() {
        ArrayList<List<Integer>> input = getPuzzleInput(getClass().getSimpleName());
        return part1(input);
    }

    public Integer part2() {
        ArrayList<List<Integer>> input = getPuzzleInput(getClass().getSimpleName());
        return part2(input);
    }

    public ArrayList<List<Integer>> getPuzzleInput(String filename) {

        ArrayList<List<Integer>> input = new ArrayList<>();

        String inputFile = "C:\\Users\\morle\\IdeaProjects\\AdventOfCode\\Input\\" + filename + ".txt";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));

            String line;
            while((line = reader.readLine()) != null) {
                List<Integer> report =
                        Arrays.stream(line.split(" "))
                                .map(Integer::parseInt)
                                .toList();

                input.add(report);
            }

        } catch(IOException ioe) {
            throw new RuntimeException(ioe);
        }

        return input;
    }

    private Integer part1(ArrayList<List<Integer>> input) {
        int count = 0;
        for(List<Integer> list : input) {

            boolean ascending = isSorted(list, true);
            boolean descending = isSorted(list, false);

            if(ascending || descending) {
                count++;
            }
        }
        return count;
    }

    private Integer part2(ArrayList<List<Integer>> input) {
        int count = 0;
        for(List<Integer> list : input) {

            for(int i = 0; i < list.size(); i++) {
                ArrayList<Integer> temp = new ArrayList<>(list);
                temp.remove(i);
                if(isSorted(temp, true) || isSorted(temp, false)) {
                    ++count;
                    break;
                }
            }
        }
        return count;
    }

    private boolean isSorted(List<Integer> input, boolean ascending) {
        boolean isSorted = false;

        if(input.isEmpty()) return false;
        if(input.size() == 1) return true;

        if(input.size() > 2) {

            for(int i = 1; i < input.size(); i++) {
                if(ascending) {
                    if(!(input.get(i) > input.get(i-1)) || !(input.get(i) - input.get(i-1) >= 1) || !(input.get(i) - input.get(i-1) <= 3)) {
                        return isSorted;
                    }
                } else {
                    if(!(input.get(i-1) > input.get(i)) || !(input.get((i-1)) - input.get(i) >= 1) || !(input.get((i-1)) - input.get(i) <= 3)) {
                        return isSorted;
                    }
                }
            }
            isSorted = true;
        }
        return isSorted;
    }
}
