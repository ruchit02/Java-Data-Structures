package com.topNotch.dataStructures.priorityQueue;

import java.util.LinkedList;
import java.util.NoSuchElementException;

import com.topNotch.dataStructures.linkedList.TNLinkedList;

public class TNPriorityQueue<E extends Comparable<E>> {

	private PriorityNode rootNode ;
	
	private int length ;
	
	public TNPriorityQueue() { }
	
	public void insert( E elem ) {
		
		rootNode = insertHelper( rootNode , elem ) ;
	}
	
	private PriorityNode insertHelper( PriorityNode rootNode , E elem ) {
		
		if( rootNode == null ) {
			
			++length ;
			
			return new PriorityNode( elem ) ; 
		}
		
		int index = rootNode.leftHeight ;
		int result = 1 ;
		while( index != 0 ) {
			
			result= 2*result ;
			--index ;
		}
		
		if( (rootNode.leftCount == rootNode.rightCount) && (rootNode.leftCount == (result-1)) || (rootNode.leftCount < (result - 1)) ) {
			
			rootNode.leftNode = insertHelper( rootNode.leftNode , elem ) ;
			
			if( rootNode.leftHeight <= rootNode.rightHeight ) {
				
				rootNode.leftHeight = rootNode.leftHeight + 1 ; 
			}
			rootNode.leftCount = rootNode.leftNode.leftCount + rootNode.leftNode.rightCount + 1 ;
			
            if( rootNode.elem.compareTo( rootNode.leftNode.elem ) > 0 ) {
				
				E tempNode = rootNode.elem ;
				
				rootNode.elem = rootNode.leftNode.elem ;
				rootNode.leftNode.elem = tempNode ;
			}
		}else {
			
			rootNode.rightNode = insertHelper( rootNode.rightNode , elem ) ;
			
			if( rootNode.leftHeight != rootNode.rightHeight ) {
				
				rootNode.rightHeight = rootNode.rightHeight + 1 ;
			}
			
			rootNode.rightCount = rootNode.rightNode.leftCount + rootNode.rightNode.rightCount + 1;
			
            if( rootNode.elem.compareTo( rootNode.rightNode.elem ) > 0 ) {
				
				E tempNode = rootNode.elem ;
				
				rootNode.elem = rootNode.rightNode.elem ;
				rootNode.rightNode.elem = tempNode ;
			}
		}
		
		return rootNode ;
	}
	
	public E deleteMin() {
		
		if( rootNode == null ) {
			throw new NoSuchElementException( "Empty Queue" ) ;
		}
		
		E tempNode = rootNode.elem ;
		
		rootNode = deleteHelper( rootNode ) ;
		
		return tempNode ;
	}
	
	private PriorityNode deleteHelper( PriorityNode rootNode ) {
		
		if( rootNode.leftNode != null && rootNode.rightNode != null ) {
			
			if( rootNode.rightNode.elem.compareTo( rootNode.leftNode.elem ) < 0 ) {
				
				E tempElem = rootNode.rightNode.elem ;
				
				rootNode.rightNode = deleteHelper( rootNode.rightNode ) ;
				
				rootNode.elem = tempElem ;
				rootNode.rightHeight = rootNode.rightHeight - 1 ;
				rootNode.rightCount = rootNode.rightCount - 1 ;
				
				return rootNode ;
			}else {
				
				E tempElem = rootNode.leftNode.elem ;
				
				rootNode.leftNode = deleteHelper( rootNode.leftNode ) ;
				
				rootNode.elem = tempElem ;
				rootNode.leftHeight = rootNode.leftHeight - 1 ;
				rootNode.leftCount = rootNode.leftCount - 1 ;
				
				return rootNode ;
			}
		}else {
			
			if( rootNode.leftNode != null ) {
				
				E tempElem = rootNode.leftNode.elem ;
				
				rootNode.leftNode = deleteHelper( rootNode.leftNode ) ;
				
				rootNode.elem = tempElem ;
				rootNode.leftHeight = rootNode.leftHeight - 1 ;
				rootNode.leftCount = rootNode.leftCount - 1 ;
				
				return rootNode ;
			}
			
			if( rootNode.rightNode != null ) {
				
                E tempElem = rootNode.rightNode.elem ;
				
				rootNode.rightNode = deleteHelper( rootNode.rightNode ) ;
				
				rootNode.elem = tempElem ;
				rootNode.rightHeight = rootNode.rightHeight - 1 ;
				rootNode.rightCount = rootNode.rightCount - 1 ;
				
				return rootNode ;
			}
		}
		
		--length ;
		return null ;
	}
	
	public boolean isEmpty() { return (size() > 0) ? false : true ; } 
	
	public int size(){ return length ; }
	
	public void levelOrderTraversal() {
		
		TNLinkedList<PriorityNode> dll = new TNLinkedList<PriorityNode>() ;
		dll.addFirst( rootNode );
		System.out.println( "\n\n\nRoot Elem : " + dll.getLast().elem );
		
		while( !dll.isEmpty() ) {
			
			PriorityNode tempNode = dll.removeFirst() ;
			
			if( tempNode.leftNode != null ) {
				
				dll.addLast( tempNode.leftNode );
				System.out.println( "Elem : " + tempNode.elem + " Left Element : " +  dll.getLast().elem );
			}
			
			if( tempNode.rightNode != null ) {
				
				dll.addLast( tempNode.rightNode );
				System.out.println( "Elem : " + tempNode.elem + " Right Element : " + dll.getLast().elem + "\n*******************************************" );
			}
		}
	}
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder() ;
		
		sb.append( "\nLeft Height : " + rootNode.leftHeight + " Right Height : " + rootNode.rightHeight ) ;
		
		return sb.toString() ;
	}
	
	private class PriorityNode {
		
		private E elem ;
		
		private PriorityNode leftNode = null;
		private PriorityNode rightNode = null ;
		
		private int leftHeight = 0 ;
		private int rightHeight = 0 ;
		
		private int leftCount = 0 ;
		private int rightCount = 0 ;
		
		private PriorityNode(E elem) {
			
			this.elem = elem ;
		}
		
		private E getElement() {
			return this.elem ;
		}
		
		private PriorityNode getLeft() {
			return this.leftNode ;
		}
		
		private PriorityNode getRight() {
			return this.rightNode ;
		}
		
		private void setElement( E elem ) {
			this.elem = elem ;
		}
		
		private void setLeft( PriorityNode leftNode ) {
			this.leftNode = leftNode ;
		}
		
		private void setRight( PriorityNode rightNode ) {
			this.rightNode = rightNode ;
		}
	}
}



















/*E tempNode = rootNode.leftNode.elem ;

rootNode.leftNode = deleteHelper( rootNode.leftNode ) ;

rootNode.elem = tempNode ;

if( rootNode.leftNode == null ) {
	
	rootNode.leftNode = rootNode.rightNode ;
	rootNode.leftCount = rootNode.rightCount ;
	rootNode.leftHeight = rootNode.rightHeight ;
	
	rootNode.rightNode = null ;
	rootNode.rightCount = 0 ;
	rootNode.rightHeight = 0 ;
}

reArrange( rootNode ) ;

return rootNode ;*/








/*
 * 
 * 
 * if( rootNode.leftNode != null && rootNode.rightNode != null ) {
			
			if( rootNode.leftNode.elem.compareTo( rootNode.rightNode.elem ) > 0 ) {
				
				E tempNode = rootNode.leftNode.elem ;
				
				rootNode.leftNode.elem = rootNode.rightNode.elem ;
				rootNode.rightNode.elem = tempNode ;
			}
			
			if( rootNode.elem.compareTo( rootNode.leftNode.elem ) > 0 ) {
				
				E tempNode = rootNode.elem ;
				
				rootNode.elem = rootNode.leftNode.elem ;
				rootNode.leftNode.elem = tempNode ;
			}
			
			if( rootNode.elem.compareTo( rootNode.rightNode.elem ) > 0 ) {
				
				E tempNode = rootNode.elem ;
				
				rootNode.elem = rootNode.rightNode.elem ;
				rootNode.rightNode.elem = tempNode ;
			}
		}else {
			
			if( rootNode.leftNode != null ) {
	        	
	        	if( rootNode.elem.compareTo( rootNode.leftNode.elem ) > 0 ) {
					
					E tempNode = rootNode.elem ;
					
					rootNode.elem = rootNode.leftNode.elem ;
					rootNode.leftNode.elem = tempNode ;
				}
	        }
			
			if( rootNode.rightNode != null ) {
	        	
	        	if( rootNode.elem.compareTo( rootNode.rightNode.elem ) > 0 ) {
					
					E tempNode = rootNode.elem ;
					
					rootNode.elem = rootNode.rightNode.elem ;
					rootNode.rightNode.elem = tempNode ;
				}
	        }
		}
 */
