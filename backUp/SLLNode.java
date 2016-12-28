/***************************************************************************************
 *
 * NAME: Zachary Heth
 *
 * HOMEWORK: 5
 *
 * CLASS: ICS 211
 *
 * INSTRUCTOR: Scott Robertson
 *
 * DATE: February 24, 2016
 *
 * FILE: SLLNode.java
 *
 * DESCRIPTION: Contains the methods for the Queue node object
 *
 ***************************************************************************************/
public class SLLNode<E> {
	private SLLNode<E> next;
	private E data;
	/*********************************************************
	 * 
	 * Method: Constructor
	 * 
	 * Description: Calls member function setData, and sets next to null
	 * 
	 * @param: E
	 * 
	 * @return: none
	 * 
	 *********************************************************/	
	public SLLNode (E newData){
		setData(newData);
		next = null;
	}
	/*********************************************************
	 * 
	 * Method: Constructor
	 * 
	 * Description: Calls member function setData, and sets next to null
	 * 
	 * @param: E, SLLNode<E>, SLLNode<E>
	 * 
	 * @return: none
	 * 
	 *********************************************************/	
	public SLLNode(E newData, SLLNode<E> refNode){
		setData(newData);
		next = refNode;
	}
	/*********************************************************
	 * 
	 * Method: setData
	 * 
	 * Description: changes the value of data
	 * 
	 * @param: E
	 * 
	 * @return: none
	 * 
	 *********************************************************/
	public void setData(E newData){
		data = newData;
	}
	/*********************************************************
	 *
	 * Method: setNext
	 * 
	 * Description: changes the value of the next node
	 * 
	 * @param: DLLNode<E>
	 * 
	 * @return: none
	 * 
	 *********************************************************/
	public void setNext(SLLNode<E> node){
		next = node;
	}
	/*********************************************************
	 * 
	 * Method: getData
	 * 
	 * Description: Gets the value of data
	 * 
	 * @param: none
	 * 
	 * @return: E
	 * 
	 *********************************************************/
	public E getData(){
		return data;
	}
	/*********************************************************
	 * 
	 * Method: getNext
	 * 
	 * Description: Returns the value of the member variable node, next
	 * 
	 * @param: none
	 * 
	 * @return: none
	 * 
	 *********************************************************/
	public SLLNode<E> getNext(){
		return next;

	}
}