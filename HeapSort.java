import java.util.Arrays;

public class HeapSort{

	public static void sort(int array[]){
		buildHeap(array);
		System.out.println(Arrays.toString(array));
		for (int i= array.length-1; i >= 0; i--){
			swap(array, 0, i);
			heapify(array, 0, i);
		}
	}

	public static void buildHeap(int[] array){
		//int mid = (array.length/2 - 1);
		for (int i = array.length; i >= 0; i--){
			heapify(array, i, array.length);
		}
	}

	public static void heapify(int array[], int rootIndex, int n){
		int largestElementIndex = getLargestElementIndex(array, rootIndex, n);
		if (largestElementIndex != rootIndex){
			swap(array, largestElementIndex, rootIndex);
			heapify(array, largestElementIndex, n);
		}
	}

	public static int getLargestElementIndex(int array[], int rootIndex, int n){
		int leftChildIndex = getLeftChildIndex(rootIndex);  
		int rightChildIndex = getRightChildIndex(rootIndex);  
		int largestElementIndex = rootIndex; 

		if((leftChildIndex < n) && (array[leftChildIndex] > array[largestElementIndex])){
			largestElementIndex = leftChildIndex;
		}
		if((rightChildIndex < n)  && (array[rightChildIndex] > array[largestElementIndex])){
			largestElementIndex = rightChildIndex;
		}

		return largestElementIndex;
	}
	
	public static int getLeftChildIndex(int rootIndex){
		return (2 * rootIndex) + 1;  
	}
	
	public static int getRightChildIndex(int rootIndex){
		return (2 * rootIndex) + 2;  
	}

	public static void swap(int[] array, int a, int b){
		int temp = array[a];
		array[a] = array[b];
		array[b] = temp;
	}

	public static void main(String args[]){
		int[] array = new int[]{4,1,5,3,2};
		HeapSort.sort(array);
		System.out.println(Arrays.toString(array));
	}
}
