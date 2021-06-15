package com.topNotch.dataStructures.stack;

import java.util.NoSuchElementException;
import com.topNotch.dataStructures.linkedList.TNLinkedList;

public class TNLinkStack<E> {

	private final TNLinkedList<E> linkedList = new TNLinkedList<>() ;
	
	public TNLinkStack() {
		
	}
	
	public boolean isEmpty() {
		
		return linkedList.isEmpty() ;
	}
	
	public E peek() {
		
		if( linkedList.isEmpty() ) { throw new NoSuchElementException() ; }
		
		return linkedList.getFirst() ;
	}
	
	public E pop() {
		
		if( linkedList.isEmpty() ) { throw new NoSuchElementException() ; }
		
		return linkedList.removeFirst() ;
	}
	
	public void push( E elem ) {
		
		linkedList.addFirst( elem ) ;
	}
	
	public int size() {
		
		return linkedList.size() ;
	}
	
	@Override
	public String toString() {
		
		return linkedList.toString() ;
	}
}
