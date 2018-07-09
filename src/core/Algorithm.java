package core;

/**
 * Algorithm interface
 */
public interface Algorithm {
    /**
     * An algorithm takes a Problem and returns a solution
     * @param problem problem to solve
     * @return Solution of the problem
     */
    Solution solveProblem(Problem problem);
}
