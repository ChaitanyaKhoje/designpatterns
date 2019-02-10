package troubleShootSearch.util;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

public class DSeaGate implements VisitorAcceptanceI{

    private static List<VisitorAcceptanceI> dSeaGateProducts = new ArrayList<VisitorAcceptanceI>();

    /**
     * Adds a child instance to the static list of VisitorAcceptanceI interface containing the accept method.
     * @param productIn
     */
    public static void addChild(DSeaGate productIn) {

        dSeaGateProducts.add(productIn);
    }

    /**
     * Each product accepts a visitor one by one.
     * @param visitorI
     */
    @Override
    public void accept(VisitorI visitorI) {

        for (VisitorAcceptanceI product: dSeaGateProducts) {
            product.accept(visitorI);
        }
    }

    /**
     * Used to get the ArrayList of a particular product.
     * @return
     */
    public List<String> getProductList() {

        throw new NotImplementedException();
    }
}
