package simpledatabase;


import java.util.ArrayList;

public class Selection extends Operator{
	
	ArrayList<Attribute> attributeList;
	String whereTablePredicate;
	String whereAttributePredicate;
	String whereValuePredicate;

	
	public Selection(Operator child, String whereTablePredicate, String whereAttributePredicate, String whereValuePredicate) {
		this.child = child;
		this.whereTablePredicate = whereTablePredicate;
		this.whereAttributePredicate = whereAttributePredicate;
		this.whereValuePredicate = whereValuePredicate;
		attributeList = new ArrayList<Attribute>();

	}
	
	
	/**
     * Get the tuple which match to the where condition
     * @return the tuple
     */
	@Override
	public Tuple next(){
		Tuple tuple = child.next();
		if(tuple!=null){
			if(whereTablePredicate.compareTo(child.from)==0){
				int index=0;
				for(int y=0;y<tuple.col.length;y++){
					if(tuple.getAttributeName(y).compareTo(whereAttributePredicate)==0){
						index=y;
					}
				}
				if((tuple.getAttributeValue(index).toString().compareTo(whereValuePredicate))==0){
					return tuple;
				}
				else{
					return this.next();
				}	
			}
			else{
				return tuple;
			}
		}
		else {
			return null;//no more record
		}
	}
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return the attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		
		return(child.getAttributeList());
	}

	
}