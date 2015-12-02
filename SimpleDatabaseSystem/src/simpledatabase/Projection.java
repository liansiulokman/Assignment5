package simpledatabase;
import java.util.ArrayList;

public class Projection extends Operator{
	
	ArrayList<Attribute> newAttributeList;
	private String attributePredicate;


	public Projection(Operator child, String attributePredicate){
		
		this.attributePredicate = attributePredicate;
		this.child = child;
		newAttributeList = new ArrayList<Attribute>();
		
	}
	
	
	/**
     * Return the data of the selected attribute as tuple format
     * @return tuple
     */
	@Override
	public Tuple next(){
		Tuple temp = child.next();
		if(temp!=null){
			int index=0;
			String name;
			while((name=temp.getAttributeName(index))!=null){
				if(name.compareTo(attributePredicate)==0){
					break;
				}
				index++;
			}
			Tuple tuple = new Tuple(temp.getAttributeName(index),temp.col1[index],temp.getAttributeValue(index).toString());
			tuple.setAttributeName();
			tuple.setAttributeType();
			tuple.setAttributeValue();
			return tuple;
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