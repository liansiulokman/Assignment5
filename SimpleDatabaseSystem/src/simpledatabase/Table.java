package simpledatabase;



import java.util.ArrayList;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Table extends Operator{
	private BufferedReader br = null;
	private boolean getAttribute=false;
	private Tuple tuple;
	private ArrayList<String> names = new ArrayList<String>();
	private ArrayList<String> types = new ArrayList<String>();
	private ArrayList<Integer> counter = new ArrayList<Integer>();
	private ArrayList<String> tables = new ArrayList<String>();
	
	public Table(String from){
		this.from = from;
		
		//Create buffer reader
		try{
			br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/datafile/"+from+".csv")));
		}
		catch (Exception e) {
			e.printStackTrace();
		} 
		
	}

	
	/**
     * Create a new tuple and return the tuple to its parent.
     * Set the attribute list if you have not prepare the attribute list
     * @return the tuple
     */
	@Override
	public Tuple next(){
		String test=null;
		int index=-1;
		for(int i=0;i<tables.size();i++){
			if(tables.get(i).compareTo(from)==0){
				index=i;
			}
		}
		if(index<0){
			tables.add(from);
			counter.add(0);
			index = tables.size()-1;
		}
		try {
			if(counter.get(index)==0){
				test = br.readLine();
				names.add(test);
				test = br.readLine();
				types.add(test);
			}
			test = br.readLine();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(test!=null){
			String aName = names.get(index);
			String aType = types.get(index);
			String aValue = test;
			Tuple newTuple = new Tuple(aName,aType,aValue);
			newTuple.setAttributeName();
			newTuple.setAttributeType();
			newTuple.setAttributeValue();
			newTuple.addAttriubteList();
			
			counter.set(index, (counter.get(index))+1);
			return newTuple;
		}
		else{
			return null;
		}
	}

	/**
     * The function is used to get the attribute list of the tuple
     * @return the attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		return tuple.getAttributeList();
	}
	
}