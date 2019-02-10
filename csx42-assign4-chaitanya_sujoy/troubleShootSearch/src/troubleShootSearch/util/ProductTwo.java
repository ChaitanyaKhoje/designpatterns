package troubleShootSearch.util;

import java.util.ArrayList;
import java.util.List;

public class ProductTwo extends DSeaGate implements VisitorAcceptanceI {

    private static List<String> productTwoList = new ArrayList<String>();

    public ProductTwo() {
    	MyLogger.writeMessage("ProductTwo Constructor called", MyLogger.DebugLevel.CONSTRUCTOR);
    }

    @Override
    public void accept(VisitorI visitorI) {
        visitorI.visit(this);
    }

    public List<String> getProductTwoList() {
        return productTwoList;
    }

    @Override
    public List<String> getProductList() {
        return productTwoList;
    }

    public void setProductTwoList(List<String> productTwoList) {
        this.productTwoList = productTwoList;
    }
}
