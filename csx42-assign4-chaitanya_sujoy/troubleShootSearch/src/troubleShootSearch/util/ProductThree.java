package troubleShootSearch.util;

import java.util.ArrayList;
import java.util.List;

public class ProductThree extends DSeaGate implements VisitorAcceptanceI {

    private static List<String> productThreeList = new ArrayList<String>();

    public ProductThree() {
    	MyLogger.writeMessage("ProductThree Constructor called", MyLogger.DebugLevel.CONSTRUCTOR);
    }

    @Override
    public void accept(VisitorI visitorI) {
        visitorI.visit(this);
    }

    public List<String> getProductThreeList() {
        return productThreeList;
    }

    @Override
    public List<String> getProductList() {
        return productThreeList;
    }

    public void setProductThreeList(List<String> productThreeList) {
        this.productThreeList = productThreeList;
    }
}
