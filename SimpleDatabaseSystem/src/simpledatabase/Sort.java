package simpledatabase;
import java.util.ArrayList;

public class Sort extends Operator{
	
	private ArrayList<Attribute> newAttributeList;
	private String orderPredicate;
	ArrayList<Tuple> tuplesResult;
	int count = 0;
	int record = 0;
	int c=0;
	ArrayList<Tuple> tempList = new ArrayList<Tuple>();
	
	public Sort(Operator child, String orderPredicate){
		this.child = child;
		this.orderPredicate = orderPredicate;
		newAttributeList = new ArrayList<Attribute>();
		tuplesResult = new ArrayList<Tuple>();
		
	}
	
	
	/**
     * The function is used to return the sorted tuple
     * @return tuple
     */
	@Override
	public Tuple next(){
		Tuple temp;
		int index = 0;
		int[] tempIndex = new int[10];
		while((temp=child.next())!=null){
			tempList.add(temp);;
			tempIndex[c]=c++;;
		}
		for(int x=0; x<c;x++){
			Tuple cur = tempList.get(x);
			while(cur.getAttributeName(index).compareTo(orderPredicate)!=0){
				index++;
			}
			
			for(int y=0; y<c-x-1;y++){
				String first = tempList.get(tempIndex[y]).getAttributeValue(index).toString();
				String second = tempList.get(tempIndex[y+1]).getAttributeValue(index).toString();
				if(first.compareTo(second)>0){
					//swap
					int i = tempIndex[y];
					tempIndex[y] = tempIndex[y+1];
					tempIndex[y+1] = i;
				}
			}
		}
		for(int z=0; z<c;z++){
			tuplesResult.add(tempList.get(tempIndex[z]));
		}
		if(record<c){
			count++;
			return tuplesResult.get(record++);
		}
		else{
			return null;
		}
	}
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		return child.getAttributeList();
	}

}