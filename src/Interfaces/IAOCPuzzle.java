package Interfaces;

public interface IAOCPuzzle<I, A> {

    I getPuzzleInput(String filename);

    A part1();

    A part2();
}
