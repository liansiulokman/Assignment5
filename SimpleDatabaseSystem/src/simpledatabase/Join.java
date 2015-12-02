package simpledatabase;
import java.util.ArrayList;
import java.util.Arrays;

public class Join extends Operator{

	private ArrayList<Attribute> newAttributeList;
	private String joinPredicate;
	ArrayList<Tuple> tuples1;
	int count=0;
	boolean match = false;
	Tuple tuple;
	int c=0;

	
	//Join Constructor, join fill
	public Join(Operator leftChild, Operator rightChild, String joinPredicate){
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.joinPredicate = joinPredicate;
		newAttributeList = new ArrayList<Attribute>();
		tuples1 = new ArrayList<Tuple>();
		
	}

	
	/**
     * It is used to return a new tuple which is already joined by the common attribute
     * @return the new joined tuple
     */
	//The record after join with two tables
	@Override
	public Tuple next(){
		
		Tuple right;
		Tuple left;
		int c=0;
		int index=0;
		int index1=0;
		String name,name1;
		
		while((left=leftChild.next())!=null){
			tuples1.add(left);
		}
		
		
		for(int h=0; h<c;h++){
			right=rightChild.next();
		}
		
		if((right=rightChild.next())!=null){
			match=false;
			
			while(match==false){
				index=0;
				index1=0;
				count=0;
				left = tuples1.get(count);
				
				while((name=right.getAttributeName(index))!=null){
					if(name.compareTo("id")==0){
						break;
					}
					index++;
				}
				while((name1=left.getAttributeName(index1))!=null){
					if(name1.compareTo("id")==0){
						break;
					}
					index1++;
				}
				
				while((!(left.getAttributeValue(index1).equals(right.getAttributeValue(index))))&&((count+1)<tuples1.size())){
					left=tuples1.get(++count);
					
				}
				//if(left.getAttributeValue(index1).equals(right.getAttributeValue(index))){
					String joinNames = null;
					String joinTypes = null;
					String joinValues = null;
					int rightSize = right.col.length;
					int leftSize = left.col.length;
					for(int k=0;k<(rightSize+leftSize);k++){
						if(k==0){
							joinNames =left.col[k];
							joinTypes = left.col1[k];
							joinValues = left.col2[k];
						}
						else if(k>0 && k<leftSize){
							joinNames = joinNames+","+left.col[k];
							joinTypes = joinTypes+","+left.col1[k];
							joinValues = joinValues+","+left.col2[k];
						}
						else if(k>=leftSize && k<(rightSize+leftSize-1)){
							if((right.col[k-leftSize].compareTo("id")!=0)){
								joinNames = joinNames+","+right.col[k-leftSize];
								joinTypes = joinTypes+","+right.col1[k-leftSize];
								joinValues = joinValues+","+right.col2[k-leftSize];
							}
						}
						else if(k==(rightSize+leftSize-1)){
							if(right.col[k-leftSize].compareTo("id")!=0){
								joinNames = joinNames+","+right.col[k-leftSize];
								joinTypes = joinTypes+","+right.col1[k-leftSize];
								joinValues = joinValues+","+right.col2[k-leftSize];
							}
						}
						
					}
					tuple = new Tuple(joinNames, joinTypes, joinValues);
					tuple.setAttributeName();
					tuple.setAttributeType();
					tuple.setAttributeValue();
					tuple.addAttriubteList();
					match=true;
					c++;
				//}
			}
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
		if(joinPredicate.isEmpty())
			return child.getAttributeList();
		else
			return(newAttributeList);
	}

}