package embs_design_contest;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class MasterLinkList {

    ConcurrentHashMap<Link, Double> costs;


    MasterLinkList(){
        costs = new ConcurrentHashMap<>();
    };

    void add(Link l, double val){
        var tmp = costs.getOrDefault(l, 0.0);
        costs.put(l, tmp + val);
    }

    int howManyOverUtelised(double factorFi){
        int count = 0;
        for(var cost : costs.values()){
            count += cost * (1/factorFi) >= 1.0 ? 1 :0;
        }
        return count;
    }

}
