import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;
public class Queue<E> {

	public SLLNode<E> head;
	public int size = 0;
/*********************************************************
 * Adds a given element to the end of the queue
 *********************************************************/
	public E offer(E element){
		if(size==0){
			head = new SLLNode(element);
			size++;
		}
		else{
			SLLNode temp = head;
			int count = 0;
			while(temp.getNext()!=null){
				temp = temp.getNext();
				//System.out.println("TempData "+temp.getData());
				count++;
			}
			temp.setNext(new SLLNode(element));
			size++;
		}
		return element;
	}
/*********************************************************
* Removes and returns the head of the queue, or returns null if the queue is empty
*********************************************************/
	public E poll(){
		E data =null;
		if(size>=1){
			data = head.getData();
			head = head.getNext();
			size--;
		}
		return data;
	}
/*********************************************************
* Removes and returns the head of the queue, or throws a
* NoSuchElementException if the queue is empty
*********************************************************/
	public E remove()throws NoSuchElementException{
		E data = null;
		try{
			if(size>=1){
				data = head.getData();
				head = head.getNext();
				size--;
			}
			else{
				throw new NoSuchElementException();
			}
		}
		catch(NoSuchElementException e){}
		return data;
	}
/*********************************************************
* Returns the head of the queue, or returns null if the queue is empty
*********************************************************/
	public E peek(){
		E data = null;
		if(size!=0){
			data = head.getData();
		}
		return data;
	}
/*********************************************************
 * Returns the head of the queue, or throws a NoSuchElementException if the queue is empty
 *********************************************************/
	public E element()throws NoSuchElementException{
		E data=null;
		try{
			if(size!=0){
				data = head.getData();
			}
			else{
				throw new NoSuchElementException();
			}
		}
		catch(NoSuchElementException e){}
		return data;
	}
/*********************************************************
*  Prints out the contents of the Queue
*********************************************************/
	public void getQueue(){
		SLLNode temp = head;
		System.out.print("Queue: ");
		while(temp!=null){
			System.out.print(temp.getData()+" ");
			temp = temp.getNext();
		}
		System.out.println();
	}
/*********************************************************
* If the Queue has at least one element it will return true
*********************************************************/
	public boolean isFull(){
		boolean full = true;
		if(size == 0){full = false;}
		return full;
	}
	public int getSize(){
		return size;
	}
}
