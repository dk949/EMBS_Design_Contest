package embs_design_contest;

public class Link {

    int source, dest;

    Link(int x, int y){
        source = x;
        dest = y;
    }
    
    public String toString() {
		return "Link from " + source + " to " + dest;
    }
}
