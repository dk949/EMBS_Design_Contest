package embs_design_contest;

public class Element {
	
	public int num, x, y;

	public Element(int elementNum, int NoCDimensionX) {
		this.num = elementNum;
		this.x = elementNum % NoCDimensionX;
		this.y =  elementNum / NoCDimensionX;
	}
}
