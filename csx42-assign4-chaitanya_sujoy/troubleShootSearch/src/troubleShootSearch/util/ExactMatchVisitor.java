package troubleShootSearch.util;

import java.util.List;

public class ExactMatchVisitor implements VisitorI {

    public ExactMatchVisitor() { 
    	MyLogger.writeMessage("ExactMatchVisitor Constructor called", MyLogger.DebugLevel.CONSTRUCTOR);
    }

    @Override
    public void visit(ProductOne productOne) {

        for (String keyword: Utility.keywordList) {
            matchExactly(keyword, productOne);
        }
    }

    @Override
    public void visit(ProductTwo productTwo) {

        for (String keyword: Utility.keywordList) {
            matchExactly(keyword, productTwo);
        }
    }

    @Override
    public void visit(ProductThree productThree) {

        for (String keyword: Utility.keywordList) {
            matchExactly(keyword, productThree);
        }
    }

    @Override
    public void visit(ProductFour productFour) {

        for (String keyword: Utility.keywordList) {
            matchExactly(keyword, productFour);
        }
    }

    /**
     * Exact match algorithm logic
     * @param keyword
     * @param product
     */
    private void matchExactly(String keyword, DSeaGate product) {

        List<String> productList = product.getProductList();
        if(!productList.isEmpty()) {
            for (String line: productList) {
                if (line.contains(keyword)) {
                    // There's an exact match for the given keyword in the line.
                    Results.sb.append("EXACT MATCH: ");
                    Results.sb.append(System.getProperty("line.separator"));
                    Results.sb.append("Line: "+ line + "\nMatched word: " + keyword);
                    Results.sb.append(System.getProperty("line.separator"));
                    Results.sb.append(System.getProperty("line.separator"));
                    MyLogger.writeMessage("Exact Match for keyword: -> " + keyword, MyLogger.DebugLevel.MATCH_FOUND);
                }
            }
        }
    }

    @Override
    public String toString() {
        return "ExactMatchVisitor{}";
    }
}
