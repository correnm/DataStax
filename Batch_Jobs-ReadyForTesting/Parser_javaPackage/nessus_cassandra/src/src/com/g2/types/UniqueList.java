package src.com.g2.types;

	import java.util.ArrayList;
	import java.util.Arrays;
	import java.util.HashSet;
	import java.util.List;
	/* w  w w  .  ja va 2  s .  c  o m*/
	public class UniqueList {
	  private HashSet masterSet = new HashSet();
	  private ArrayList innerList; 
	  private Object[] returnable;

	  public UniqueList() {
	    innerList = new ArrayList();
	  }

	  public UniqueList(int size) {
	    innerList = new ArrayList(size);
	  }

	  public void add(Object thing) {
	    if (!masterSet.contains(thing)) {
	      masterSet.add(thing);
	      innerList.add(thing);
	    }
	  }
	  public List getList() {
	    return innerList;
	  }

	  public Object get(int index) {
	    return innerList.get(index);
	  }

	  public Object[] toObjectArray() {
	    int size = innerList.size();
	    returnable = new Object[size];
	    for (int i = 0; i < size; i++) {
	      returnable[i] = innerList.get(i);
	    }
	    return returnable;
	  }
	  
	  public void clear(){
		  masterSet.clear();
		  innerList.clear();
		  for (int i = 0; i < innerList.size(); i++) {
		      returnable[i] = innerList.get(i);
		    }
	  }
	}

