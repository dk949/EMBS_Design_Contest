package embs_design_contest;

import java.util.HashMap;

public class MasterLinkList {

    HashMap<Link, Double> costs;

    MasterLinkList(){
        costs = new HashMap<Link, Double>();
    };

    void add(Link l, double val){
        var tmp = costs.getOrDefault(l, 0.0);
        costs.put(l, tmp + val);
    }

    long howManyOverUtelised(){
        long count = 0;
        for(var cost : costs.values()){
            count += cost >= 1.0 ? 1 :0;
        }
        return count;
    }

}
