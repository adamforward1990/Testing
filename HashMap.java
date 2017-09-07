package CtCILibrary;
import java.util.ArrayList; 

public class HashMap<K,V>  { 

	private ArrayList<HashNode<K, V>> buckets = new ArrayList<>(); 
	private static final double MAX_LOAD_FACTOR = 0.7;
	private static final int INIT_NUM_BUCKETS = 10;
	private int  numOfBuckets = 0; 
	int size; 

	public HashMap(){ 
		for(int i = 0; i < INIT_NUM_BUCKETS; i++){ 
			buckets.add(null); 
			numOfBuckets++;
		} 
	} 

	public int size(){ 
		return size; 
	} 

	public boolean isEmpty(){ 
		return size == 0; 
	} 

	private int getBucketIndex(K key){ 
		return key.hashCode() %  numOfBuckets; 
	} 

	public HashNode<K, V> getBucketHead(K key){
		int bucketIndex = getBucketIndex(key); 
		HashNode<K, V>  bucketHead = buckets.get(bucketIndex); 
		return bucketHead;
	}

	public V get(K key){
		HashNode<K, V>  bucketHead = getBucketHead(key); 
		HashNode<K, V> currentNode = bucketHead;

		while(currentNode != null){ 
			if(keyEquals(currentNode.key, key)){ 
				return currentNode.value; 
			} 
			currentNode = currentNode.next; 
		} 
		return null;	 
	}

	public boolean keyEquals(K nodeKey, K key){
		return nodeKey.equals(key);
	}

	public V removeBucketHead(HashNode<K, V> bucketHead, K key){
		int index = getBucketIndex(key);
		buckets.set(index, bucketHead.next); //set new bucket head in bucket list
		size--; 
		return bucketHead.value; 
	}

	public V removeNodeFromBucket(HashNode<K, V> bucketHead, K key){
		HashNode<K, V> prev = bucketHead; 
		HashNode<K, V> currentNode = bucketHead.next;//already checked head

		while(currentNode!= null){ 
			if(keyEquals(currentNode.key, key)){ 
				prev.next = currentNode.next; // remove from bucket list
				size--; 
				return currentNode.value; 
			} 
			prev = currentNode; 
			currentNode = currentNode.next; 
		}  
		return null; 
	}

	public V remove(K key){
		HashNode<K, V> bucketHead = getBucketHead(key);

		if(bucketHead == null){
			return null;
		}else if(keyEquals(bucketHead.key, key)){
			return removeBucketHead(bucketHead, key); 
		}else{
			return removeNodeFromBucket(bucketHead, key);
		}
	}

	public void initializeBucketHead(HashNode<K, V> addItem){
		int index = getBucketIndex(addItem.key); 
		buckets.set(index, addItem);
		size++;
	}

	public void setNodeValue(HashNode<K, V> node, V value){
		node.value = value;
	}

	public void prependToBucket(HashNode<K, V>  bucketHead, HashNode<K, V> addItem){
		addItem.next = bucketHead; 
		int index = getBucketIndex(addItem.key);
		buckets.set(index, addItem); 
		size++;   
	}

	public void putNodeInBucket(K key, V value){
		HashNode<K, V>  bucketHead  =  getBucketHead(key);
		HashNode<K, V>  currentNode = bucketHead;

		while(currentNode!= null) { 
			if(keyEquals(currentNode.key,key)) { 
				setNodeValue(currentNode, value);
				return; 
			} 
			currentNode = currentNode.next; 
		} 

		HashNode<K, V> addItem = new HashNode<K, V>(key, value);
		prependToBucket(bucketHead, addItem);
	}
	
	public boolean collision(K key){
		HashNode<K, V>  bucketHead  =  getBucketHead(key);
		return bucketHead != null;
	}

	public void put(K key,V value) { 
		HashNode<K, V> addItem = new HashNode<> (key, value); 

		if(!collision(key)) { 
			initializeBucketHead(addItem);
		} else { 
			putNodeInBucket(key, value);
		}
		
		updateBucketList();
	} 

	public void updateBucketList(){
		if(needMoreBuckets()){ 
			doubleBucketListSize();
		} 
	}
	
	public boolean needMoreBuckets(){
		double loadFactor = (double) size / numOfBuckets;
		return  loadFactor >= MAX_LOAD_FACTOR;
	}
	
	public void doubleBucketListSize(){
		for(int i = 0; i < numOfBuckets; i++){
			buckets.add(null);
		}
		numOfBuckets = numOfBuckets * 2;
	}

	public static void main(String[] args) { 

	} 
} 