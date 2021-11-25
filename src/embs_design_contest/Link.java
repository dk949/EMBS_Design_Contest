package embs_design_contest;

public class Link implements Comparable<Link>{

    int source, dest;

    Link(int x, int y){
        source = x;
        dest = y;
    }

    @Override
    public boolean equals(Object other){
        if(other instanceof Link){
            return ((Link) other).dest == dest && ((Link) other).source == source;
        }
        return false;
    }
    
    public String toString() {
		return "Link from " + source + " to " + dest;
    }


    @Override
    public int hashCode(){
        return Integer.valueOf(source).hashCode() ^ Integer.valueOf(dest).hashCode();
    }

    @Override
    public int compareTo(Link o) {
        return (o.source + o.dest) - (source + dest);
    }
}
