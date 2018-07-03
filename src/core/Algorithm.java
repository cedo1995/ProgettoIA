package core;

import core.Model;
import core.Solution;

/**
 * Algorithm interface
 */
public interface Algorithm {
    /**
     * An algorithm takes a Model and returns a solution
     * @param model problem's model
     * @return Solution of the problem
     */
    Solution computeModel(Model model);

    String testDescription();
}
