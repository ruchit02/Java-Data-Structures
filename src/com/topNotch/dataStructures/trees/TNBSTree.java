package com.topNotch.dataStructures.trees;

import java.util.NoSuchElementException;
import com.topNotch.dataStructures.linkedList.TNLinkedList;
import com.topNotch.dataStructures.stack.TNLinkStack;

public class TNBSTree<E> {
	
	private BNode rootNode = null ;
	private int length = 0 ;
	private int sortIndex = 0 ;
	
	public TNBSTree() {
		
	}
	
	
	
	
	
	public void insert( Comparable<E> elem ) {
		
		rootNode = insertHelper( rootNode , elem ) ;
	}
	
	private BNode insertHelper(  BNode root , Comparable<E> elem ) {
		
        if( root == null ) { 
        	
        	root = new BNode( (E)elem ) ;
        	++length ; 
        	
        	root.leftHeight = 0 ;
        	root.rightHeight = 0 ;
        	
        	return root ;
        }
		
		if( elem.compareTo( root.elem ) < 0 ) { 
			
			root.leftNode = insertHelper( root.leftNode , elem ) ;
			
			if( root.leftNode.leftHeight >= root.leftNode.rightHeight ) {
				
				root.leftHeight = root.leftNode.leftHeight + 1 ;
			}else {
				root.leftHeight = root.leftNode.rightHeight + 1 ;
			}
		}else {
			
			if( elem.compareTo( root.elem ) > 0 ) { 
				root.rightNode = insertHelper( root.rightNode , elem ) ;
				
				if( root.rightNode.leftHeight >= root.rightNode.rightHeight ) {
					root.rightHeight = root.rightNode.leftHeight + 1 ;
				}else {
					root.rightHeight = root.rightNode.rightHeight + 1 ;
				}
			}else { 
				throw new IllegalArgumentException("Duplicate Element Inserted") ;
			}
		}
		
		return selfBalance( root ) ;
	}
	
	
	
	
	
	
	public void delete( Comparable<E> elem ) {
		
		if( isEmpty() ) { throw new NoSuchElementException("Empty Tree") ; }
		
		rootNode = deleteHelper( rootNode , elem ) ;
	}
	
	private BNode deleteHelper( BNode root , Comparable<E> elem ) {
		
		if( elem.compareTo( root.elem ) < 0 ) {
			
			if( root.leftNode != null ) { 
				root.leftNode = deleteHelper( root.leftNode , elem ) ;
				
				if( root.leftNode != null ) {

					if( root.leftNode.leftHeight >= root.leftNode.rightHeight ) {
						root.leftHeight = root.leftNode.leftHeight + 1 ;
					}else {
						root.leftHeight = root.leftNode.rightHeight + 1 ;
					}
				}else {
					
					root.leftHeight = 0 ;
				}	
			}
		}else {
			
			if( elem.compareTo( root.elem ) > 0 ) {
				
				if( root.rightNode != null ) { 
					root.rightNode = deleteHelper( root.rightNode , elem ) ;
					
					if( root.rightNode != null ) {
						
						if( root.rightNode.leftHeight >= root.rightNode.rightHeight ) {
							root.rightHeight = root.rightNode.leftHeight + 1 ;
						}else {
							root.rightHeight = root.rightNode.rightHeight + 1 ;
						}
					}else {
						
						root.rightHeight = 0 ;
					}
				}
			}else {
				
				--length ;
				
				if( root.leftNode == null ) { 
					
					BNode tempNode = root.rightNode ; 
					root.elem = null ; 
					root.rightNode = null ; 
					return tempNode ; 
				}
				
				if( root.rightNode == null ) { 
					BNode tempNode = root.leftNode ; 
					root.elem = null ; 
					root.leftNode = null ; 
					return tempNode ; 
				}
				else {
					return getLargestNode( root , elem ) ;
				}
			}
		}
		
		return selfBalance( root ) ;
	}
	
    private BNode getLargestNode( BNode root , Comparable<E> elem ) {
		
		BNode tempNode = root ;
		BNode prevNode = root ;
		
		tempNode = tempNode.leftNode ;
		
		if( tempNode.rightNode != null ) {
			
			while( tempNode.rightNode != null ) {
				prevNode = tempNode ;
				tempNode = tempNode.rightNode ; }
			
			prevNode.rightNode = tempNode.leftNode ;
			
			if( tempNode.leftNode != null ) {
				
				if( tempNode.leftNode.leftHeight >= tempNode.leftNode.rightHeight ) {
					
					prevNode.rightHeight = tempNode.leftNode.leftHeight + 1 ;
				}else {
					
					prevNode.rightHeight = tempNode.leftNode.rightHeight + 1 ;
				}
			}else {
				prevNode.rightHeight = 0 ;
			}
		}
		else {
			
			prevNode.leftNode = tempNode.leftNode ; 
			
			if( tempNode.leftNode != null ) {
				
				if( tempNode.leftNode.leftHeight >= tempNode.leftNode.rightHeight ) {
					
					prevNode.leftHeight = tempNode.leftNode.leftHeight + 1 ;
				}else {
					
					prevNode.leftHeight = tempNode.leftNode.rightHeight + 1 ;
				}
			}
			else {
				prevNode.leftHeight = 0 ;
			}
		}
		
		root.elem = tempNode.elem ;
		
		tempNode.elem = null ;
		tempNode.leftNode = null ;
		
		return selfBalance( root ) ;
	}
	
    
    
	
	
	private BNode selfBalance( BNode root ) {
		
		int heightDiff = root.leftHeight - root.rightHeight ;
		
		if( heightDiff > 1 ) {
			
			BNode nextNode = root.leftNode ;
			BNode tempNode = nextNode.rightNode ;
			
			root.leftNode = tempNode ;
			
			if( tempNode != null ) {
				
				 if( tempNode.leftHeight >= tempNode.rightHeight ) {
						
					root.leftHeight = tempNode.leftHeight + 1 ;
				}else {
					root.leftHeight = tempNode.rightHeight + 1 ;
				}
			}else {
				root.leftHeight = 0 ;
			}
			
			nextNode.rightNode = root ;
			
			if( root.leftHeight >= root.rightHeight ) {
				
				nextNode.rightHeight = root.leftHeight + 1 ; 
			}else {
				
				nextNode.rightHeight = root.rightHeight + 1 ; 
			}
			
			return nextNode ;
		}
		
		if( heightDiff < -1 ) {
			
			BNode nextNode = root.rightNode ;
			BNode tempNode = nextNode.leftNode ;
			
			root.rightNode = tempNode ;
			
			if( tempNode != null ) {
				
				 if( tempNode.leftHeight >= tempNode.rightHeight ) {
						
					root.rightHeight = tempNode.leftHeight + 1 ;
				}else {
					root.rightHeight = tempNode.rightHeight + 1 ;
				}
			}else {
				root.rightHeight = 0 ;
			}
			
			nextNode.leftNode = root ;
			
			if( root.leftHeight >= root.rightHeight ) {
				
				nextNode.leftHeight = root.leftHeight + 1 ;
			}else {
				nextNode.leftHeight = root.rightHeight + 1 ;
			}
			
			return nextNode ;
		}
		
		return root ;
	}
	
	
	
	
	
	
    public int height() {
		
		return heightFinder( rootNode ) ;
	}
	
	private int heightFinder( BNode root ) {
		
		if( root == null ) { return -1 ; }
		
		return 1 + Math.max( heightFinder( root.leftNode ) , heightFinder( root.rightNode ) ) ;
	}
	
	
	
	
	
	
	public void preOrderTraversal() {
		
		preOrderHelper(rootNode) ;
	}
	
	private void preOrderHelper( BNode root ) {
		
		System.out.println( root.elem );
		
		if( root.leftNode != null ) { preOrderHelper( root.leftNode ) ; }
		
		if( root.rightNode != null ) { preOrderHelper( root.rightNode ) ; }
	}
	
	public void inOrderTraversal() { inOrderHelper( rootNode ) ; } 
	
	private void inOrderHelper( BNode root ) {
		
		if( root.leftNode != null ) { inOrderHelper( root.leftNode ) ; }
		
		//System.out.println( root.elem );
		
		if( root.rightNode != null ) { inOrderHelper( root.rightNode ) ; }
	}
	
	public void postOrderTraversal() { postOrderHelper( rootNode ) ; }
	
	private void postOrderHelper( BNode root ) {
		
        if( root.leftNode != null ) { postOrderHelper( root.leftNode ) ; }
		
		if( root.rightNode != null ) { postOrderHelper( root.rightNode ) ; }
		
		System.out.println( root.elem );
	}
	
	public void levelOrderTraversal() {
		
		TNLinkedList<BNode> dll = new TNLinkedList<>() ;
		dll.addFirst( rootNode );
		
		while( !dll.isEmpty() ) {
			
			BNode root = dll.removeFirst() ;
			
			if( root.leftNode != null ) {
				dll.addLast( root.leftNode );
			}
			
			if( root.rightNode != null ) {
				dll.addLast( root.rightNode );
			}
			
			System.out.println( root.elem ) ;
		}
	}
	
	public E[] sort() {
		
		E[] sortedTree = (E[])new Object[size()] ;
		sortIndex = 0 ;
		sortHelper( rootNode , sortedTree ) ;
		
		return sortedTree ;
	}
	
	private void sortHelper( BNode rootNode , Object[] sortedTree ) {
		
		if( rootNode != null ) {
			
			sortHelper( rootNode.leftNode , sortedTree ) ;
			
			sortedTree[sortIndex++] = rootNode.elem ;
			
			sortHelper( rootNode.rightNode , sortedTree ) ;	
		}
	}
	
	public void mirror() {
		
		mirrorHelper( rootNode ) ;
	}
	
	private void mirrorHelper( BNode rootNode ) {
		
		if( rootNode != null ) {
			
			mirrorHelper( rootNode.leftNode ) ;
			mirrorHelper( rootNode.rightNode ) ;
			
			BNode tempNode = rootNode.leftNode ;
			int tempHeight = rootNode.leftHeight ;
			
			rootNode.leftNode = rootNode.rightNode ; 
			rootNode.rightNode = tempNode ;
			
			rootNode.leftHeight = rootNode.rightHeight ;
			rootNode.rightHeight = tempHeight ;
		}
	}
	
	
	
	
	public E minimum() {
		
		return minimumFinder( rootNode ) ;
	}
	
	private E minimumFinder( BNode root ) {
		
		while( root.leftNode != null ) {
			root = root.leftNode ;
		}
		
		return root.elem ;
	}
	
	public E maximum() {
		
		return maximumFinder( rootNode ) ;
	}
	
	private E maximumFinder( BNode root ) {
		
		while( root.rightNode != null ) {
			root = root.rightNode ;
		}
		
		return root.elem ;
	}
	
    public TNLinkStack<E> leftSubTree(){
		
		return traverseSubTree( rootNode , SubTree.LEFT ) ;
	}
	
	public TNLinkStack<E> rightSubTree() {
		
		return traverseSubTree( rootNode , SubTree.RIGHT ) ;
	}
	
	private TNLinkStack<E> traverseSubTree( BNode root , SubTree subTree ){
		
		if( root == null ) { return null ; }
		
		TNLinkStack<E> elementsStack = new TNLinkStack<>() ;
		
		switch( subTree ) {
		
		case LEFT : 
			
			if( root.leftNode != null )
				elementInOrderTraversal( root.leftNode , elementsStack ) ;
			
			break ;
		case RIGHT :
			
			if( root.rightNode != null )
				elementInOrderTraversal( root.rightNode , elementsStack ) ;
			
			break ;
		}
		
		return elementsStack ;
	}
	
	private void elementInOrderTraversal( BNode root , TNLinkStack<E> treeElements ) {
		
		if( root.leftNode != null ) {
			elementInOrderTraversal( root.leftNode , treeElements ) ;
		}
		
		treeElements.push( root.elem );
		
		if( root.rightNode != null ) {
			elementInOrderTraversal( root.rightNode , treeElements ) ;
		}
	}
	
	public int leafCount() {
		
		return countLeaves( rootNode ) ;
	}
	
	private int countLeaves( BNode root ) {
		
		int count = 0 ;
		
		TNLinkedList<BNode> dll = new TNLinkedList<>() ;
		
		if( root != null ) {
			dll.add( root ) ;
			++count ; 	
		}
		
		
		while( !dll.isEmpty() ) {
			
			BNode tempNode = dll.removeFirst() ;
			
			if( tempNode.leftNode != null && tempNode.rightNode != null ) {
				dll.addLast( tempNode.leftNode );
				dll.addLast( tempNode.rightNode );
			}
			else {
				
				if( tempNode.leftNode != null ) {
					dll.addLast( tempNode.leftNode );
				}
				else {
					
					if( tempNode.rightNode != null ) {
						dll.addLast( tempNode.rightNode ) ;
					}
					else {
						++count;
					}
				}
			}
		}
		
		return count ;
	}
	
	public int size() { return length ; }
	
	public boolean isEmpty() { return (length>0)? false : true ; }
	
	private enum SubTree {
		LEFT , RIGHT
	}
	
	public class BNode {
		
		private E elem ;
		private BNode leftNode ;
		private BNode rightNode ;
		public int leftHeight = 0 ;
		public int rightHeight = 0 ;
		
		private BNode() {
			
			this.elem = null ;
			this.leftNode = null ;
			this.rightNode = null ;
		}
		
		private BNode( E elem ) {
			
			this.elem = elem ;
			this.leftNode = null ;
			this.rightNode = null ;
		}
		
		public BNode getLeftNode() {
			
			return this.leftNode ;
		}
		
		public BNode getRightNode() {
			
			return this.rightNode ;
		}
		
		public void setLeftNode( BNode leftNode ) {
			
			this.leftNode = leftNode ;
		}
		
		public void setRightNode( BNode rightNode ) {
			
			this.rightNode = rightNode ;
		}
	}
}
