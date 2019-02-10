package troubleShootSearch.util;

import java.util.ArrayList;
import java.util.List;

public class ProductFour extends DSeaGate implements VisitorAcceptanceI {

    private static List<String> productFourList = new ArrayList<String>();

    public ProductFour() {
    	MyLogger.writeMessage("ProductFour Constructor called", MyLogger.DebugLevel.CONSTRUCTOR);
    }

    @Override
    public void accept(VisitorI visitorI) {
        visitorI.visit(this);
    }

    public List<String> getProductFourList() {
        return productFourList;
    }

    @Override
    public List<String> getProductList() {
        return productFourList;
    }

    public void setProductFourList(List<String> productFourList) {
        this.productFourList = productFourList;
    }
}
