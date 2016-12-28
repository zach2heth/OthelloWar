
import java.util.Iterator;
public class SLL<E> {
	private SLLNode<E> head;
	private int size;
	public SLL(){								//Might be useful later
		head = null; 							// Iterator<E> iter = Iterator();
		size = 0;								//The next day: It was useful indeed
	}
	/*********************************************************
	 *  Adds an element to the end of the list
	 *********************************************************/
	public boolean add(E element){
		boolean added = false;
		if(size == 0){
			head = new SLLNode(element);
			added = true;
		}
		else{
			SLLNode curr = head;
			while(curr.getNext() != null){
				//			System.out.println("Data: "+curr.getData());
				curr = curr.getNext();
			}
			curr.setNext(new SLLNode(element));
			added = true;
		}
		if(added = true){
			size++;
		}
		return added;
	}
	/*********************************************************
	* Adds an element to the given index
	 *********************************************************/
	public boolean add(int index, E element){
		boolean added = false;
		int count = 0;
		SLLNode curr = head;
		SLLNode tempNode;
		if(index==0){
			head = new SLLNode(element);
			head.setNext(curr);
			size++;
		}
		else if(index>0 && index<size){
			while(count!=index-1){
				//		System.out.println("Position: "+count+" Data: "+curr.getData());
				curr = curr.getNext();
				count++;
			}
			tempNode = curr.getNext(); //Holds on to node that comes after one that was just added
			curr.setNext(new SLLNode(element));
			curr.getNext().setNext(tempNode);
			added = true;
			//	System.out.println("Position: "+(count+1)+" Data: "+curr.getNext().getData());
			size++;
		}
		return added;
	}

	public void clear(){
		head.setData(null);
		head.setNext(null);
		size = 0;
	}
	/*********************************************************
	 *
	 * Method: contains
	 *
	 * Description: checks if the list contains the given element
	 *
	 * @param: E
	 *
	 * @return: boolean
	 *
	 *********************************************************/
	public boolean contains(E element){
		boolean contains = false;
		Iterator<E> iter = Iterator();
		while(iter.hasNext()){
			if(iter.next()==element){
				contains = true;
			}
		}
		return contains;
	}
	/*********************************************************
	 *
	 * Method: get
	 *
	 * Description: Returns the element at the given index
	 *
	 * @param: int
	 *
	 * @return: E
	 *
	 *********************************************************/
	public E get(int index){
		Iterator<E> iter = Iterator();
		E data = null;
		int count=0;
		if(index>=0 && index<size){
			while(count!=index){
				iter.next();
				count++;
			}
			data = iter.next();
		}
		return data;

	}
	/*********************************************************
	 *
	 * Method: indexOf
	 *
	 * Description: Returns the first occurrence of a given element, or -1 if it doesn't exist
	 *
	 * @param: E
	 *
	 * @return: int
	 *
	 *********************************************************/
	public int indexOf(E element){
		Iterator<E> iter = Iterator();
		int index = -1;
		int count = 0;
		while(iter.hasNext()){
			if(iter.next()!=element){
				count++;
			}
			else{
				index = count;
				break;
			}
		}
		return index;

	}
	/*********************************************************
	 *
	 * Method: isEmpty
	 *
	 * Description: Returns true if the list is empty, false if not
	 *
	 * @param: none
	 *
	 * @return: boolean
	 *
	 *********************************************************/
	public boolean isEmpty(){
		boolean empty = false;
		if(size==0){
			empty = true;
		}
		return empty;
	}
	/*********************************************************
	 *
	 * Method: lastIndexOf
	 *
	 * Description: Returns the last occurrence of an element, or -1 if it doesn't exist
	 *
	 * @param: int
	 *
	 * @return: E
	 *
	 *********************************************************/
	public int lastIndexOf(E element){
		Iterator<E> iter = Iterator();
		int index = -1;
		int count = 0;
		try{
			while(count<=size){
				if(iter.next()==element){
					index = count;
				}
				count++;
			}
		}
		catch(NullPointerException e){}
		return index;

	}
	/*********************************************************
	 *
	 * Method: removeIndex
	 *
	 * Description: Removes the element at the given index
	 *
	 * @param: int
	 *
	 * @return: E
	 *
	 *********************************************************/
	public E removeIndex(int index){
		E element = null;
		SLLNode curr = head;
		SLLNode tempNode;
		SLLNode tempNode2 = null;
		int count = 0;
		if(index==0){
			element = head.getData();
			head = curr.getNext();
			size--;
		}
		if(index>0 && index<size){
			while(count!=index-1){
				curr = curr.getNext();
				count++;
			}
			tempNode = curr;
			element = (E) curr.getNext().getData();
			if(count<size-1){
				tempNode2 = tempNode.getNext().getNext();
			}
			curr.getNext().setNext(null);
			tempNode.setNext(tempNode2);
			size--;
		}
		return element;

	}
	/*********************************************************
	 *
	 * Method: removeElement
	 *
	 * Description: Removes the first occurrence of the given element
	 *
	 * @param: E
	 *
	 * @return: E
	 *
	 *********************************************************/
	public E removeElement(E element){
		Iterator<E> iter = Iterator();
		int count = 0;
		E removedElement = null;
		while(iter.hasNext()){
			if(iter.next()!=element){
				count++;
			}
			else{
				removedElement = element;
				break;
			}
		}
		if(removedElement!=null){
			removeIndex(count);
		}
		return removedElement;
	}
	/*********************************************************
	 *
	 * Method: set
	 *
	 * Description: changes the value at the given index to the given element
	 *
	 * @param: int, E
	 *
	 * @return: E
	 *
	 *********************************************************/
	public E set(int index, E element){
		SLLNode tempNode = head;
		E data = null;
		int count = 0;
		if(index==0){
			data = (E) tempNode.getData();
			tempNode.setData(element);
		}
		else if(index<size && index>=0){
			while(count!=index){
				count++;
				tempNode = tempNode.getNext();
			}
			data = (E) tempNode.getData();
			tempNode.setData(element);
		}
		return data;
	}
	/*********************************************************
	 *
	 * Method: size
	 *
	 * Description: returns the number of elements in the list
	 *
	 * @param: none
	 *
	 * @return: none
	 *
	 *********************************************************/
	public int size(){
		return size;
	}
	public SLL<E> copySLL(){
		SLL<E> copy = new SLL<E>();
		Iterator<E> iter = Iterator();
		while(iter.hasNext()){
			copy.add(iter.next());
		}
		return copy;
	}
	/*********************************************************
	 *
	 * Method: toString
	 *
	 * Description: Prints the elements in the list in order from index 0
	 *
	 * @param: none
	 *
	 * @return: String
	 *
	 *********************************************************/
	public String toString(){
		int count = 0;
		Iterator<E> iter = Iterator();
		String list = "[";
		while(iter.hasNext()){
			if(size==1){
				list+=iter.next();
			}
			else if(count==size-1){
				list+=iter.next();
			}
			else{
				list+=iter.next()+", ";
				count++;
			}
		}
		list+="]";
		System.out.println(list);
		return list;
	}
	/*********************************************************
	 *
	 * Method: Iterator
	 *
	 * Description: Returns an Iterator objects which starts at the head of the list
	 *
	 * @param: none
	 *
	 * @return: Iterator<E>
	 *
	 *********************************************************/
	public Iterator<E> Iterator(){
		SLLIterator Iter = new SLLIterator();
		return Iter;
	}
	/***************************************************************************************
	 *
	 * NAME: Zachary Heth
	 *
	 * HOMEWORK: 4
	 *
	 * CLASS: ICS 211
	 *
	 * INSTRUCTOR: Scott Robertson
	 *
	 * DATE: February 17, 2016
	 *
	 * FILE: SLLIterator.java
	 *
	 * DESCRIPTION: Contains the methods for the Iterator
	 *
	 ***************************************************************************************/
	private class SLLIterator implements Iterator {
		private SLLNode<E> next;

		/*********************************************************
		 *
		 * Method: Constructor
		 *
		 * Description: sets the member variable next to head
		 *
		 * @param: none
		 *
		 * @return: none
		 *
		 *********************************************************/
		public SLLIterator(){
			next = head;
		}
		/*********************************************************
		 *
		 * Method: hasNext
		 *
		 * Description: Returns true if the next method will return a value that is not null
		 *
		 * @param: none
		 *
		 * @return: boolean
		 *
		 *********************************************************/
		public boolean hasNext(){
			boolean hasNext = false;
			try{
				if(next.getData()!=null){
					hasNext = true;
					//	System.out.println("HasNext "+next.getData());
				}
			}
			//	throw(NullPointerException e);
			catch(NullPointerException e){}
			return hasNext;
		}
		/*********************************************************
		 *
		 * Method: next
		 *
		 * Description: Returns the value of the member variable next and sets next to the next node in the list
		 *
		 * @param: none
		 *
		 * @return: E
		 *
		 *********************************************************/
		public E next(){
			E data = next.getData();
			next = next.getNext();

			return data;
		}
		/*********************************************************
		 *
		 * Method: remove
		 *
		 * Description: Removes the element last returned by the next method
		 *
		 * @param: none
		 *
		 * @return: none
		 *
		 *********************************************************/
		public void remove(){
			boolean removed = true;
			SLLNode temp = null;
			SLLNode temp2 = null;
			SLLNode temp3 = null;
			SLLNode iter = head;
			while(removed){
				if(iter!=next){
					temp = iter;
					iter = iter.getNext();
				}
				else{
					while(temp2!=temp){
						temp3 = temp2;
						temp2 = temp.getNext();
					}
					temp.setNext(null);
					temp3.setNext(next);
					removed = false;
				}
			}
		}
	}
}
