package troubleShootSearch.util;

import java.util.List;

public class NaiveStemmingMatchVisitor implements VisitorI {


    public NaiveStemmingMatchVisitor() {
    	MyLogger.writeMessage("NaiveStemmingMatchVisitor Constructor called", MyLogger.DebugLevel.CONSTRUCTOR);
    }

    @Override
    public void visit(ProductOne productOne) {

        for (String keyword: Utility.keywordList) {
            matchByNaiveStemming(keyword, productOne);
        }
    }

    @Override
    public void visit(ProductTwo productTwo) {

        for (String keyword: Utility.keywordList) {
            matchByNaiveStemming(keyword, productTwo);
        }
    }

    @Override
    public void visit(ProductThree productThree) {

        for (String keyword: Utility.keywordList) {
            matchByNaiveStemming(keyword, productThree);
        }
    }

    @Override
    public void visit(ProductFour productFour) {

        for (String keyword: Utility.keywordList) {
            matchByNaiveStemming(keyword, productFour);
        }
    }

    /**
     * Naive Stemming pattern matching algorithm logic.
     * @param keyword
     * @param product
     */
    private void matchByNaiveStemming(String keyword, DSeaGate product) {

        String[] tokens = Utility.tokenizeKeyword(keyword);
        List<String> productList = product.getProductList();

        for (String token: tokens) {
            if(!productList.isEmpty()) {
                for (String line: productList) {
                    if (line.matches("(.*)"+ token +"(.*)") && !token.equals("")) {
                        Results.sb.append("NAIVE STEMMING MATCH: ");
                        Results.sb.append(System.getProperty("line.separator"));
                        Results.sb.append("Line: "+ line + "\nMatched word: " + keyword);
                        Results.sb.append(System.getProperty("line.separator"));
                        Results.sb.append(System.getProperty("line.separator"));
                        MyLogger.writeMessage("NaiveStemming Match for keyword: -> " + keyword, MyLogger.DebugLevel.MATCH_FOUND);
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        return "NaiveStemmingMatchVisitor{}";
    }
}
