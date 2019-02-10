package troubleShootSearch.driver;

import troubleShootSearch.util.DSeaGate;
import troubleShootSearch.util.ExactMatchVisitor;
import troubleShootSearch.util.NaiveStemmingMatchVisitor;
import troubleShootSearch.util.Results;
import troubleShootSearch.util.SemanticMatchVisitor;
import troubleShootSearch.util.Utility;

public class Driver {

    /**
     * Main method:
     * Validates arguments
     * Process visitor pattern for the given keywords.
     * Results are written to the output files.
     * <p>
     * Since no other classes are going to create Driver's instances, all methods in the Driver are made static
     * to handle non static methods being called in a static method (main method).
     *
     * @param args Program arguments
     */
    public static void main(String[] args) {

        Utility util = new Utility();
        util.validate(args);

        DSeaGate dSeaGateProducts = new DSeaGate();
        ExactMatchVisitor exactMatch = new ExactMatchVisitor();
        SemanticMatchVisitor semanticMatch = new SemanticMatchVisitor();
        NaiveStemmingMatchVisitor naiveStemmingMatch = new NaiveStemmingMatchVisitor();

        dSeaGateProducts.accept(exactMatch);
        dSeaGateProducts.accept(semanticMatch);
        dSeaGateProducts.accept(naiveStemmingMatch);

        Results results = new Results();
        results.writeOutput(args);
    }
}
