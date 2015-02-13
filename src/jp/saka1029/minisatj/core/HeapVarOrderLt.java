package jp.saka1029.minisatj.core;

import jp.saka1029.minisatj.mtl.VecInt;

////=================================================================================================
//// A heap implementation with support for decrease/increase key.
//
//
//template<class Comp>
//class Heap {
public class HeapVarOrderLt {
//    Comp     lt;       // The heap is a minimum-heap with respect to this comparator
	private VarOrderLt lt;
//    vec<int> heap;     // Heap of integers
	private VecInt heap = new VecInt();
//    vec<int> indices;  // Each integers position (index) in the Heap
	private VecInt indices = new VecInt();
//
//    // Index "traversal" functions
//    static inline int left  (int i) { return i*2+1; }
	private static int left(int i) { return i * 2 + 1; }
//    static inline int right (int i) { return (i+1)*2; }
	private static int right(int i) { return (i + 1) * 2; }
//    static inline int parent(int i) { return (i-1) >> 1; }
	private static int parent(int i) { return (i - 1) >> 1; }
//
//
//    void percolateUp(int i)
//    {
//        int x  = heap[i];
//        int p  = parent(i);
//        
//        while (i != 0 && lt(x, heap[p])){
//            heap[i]          = heap[p];
//            indices[heap[p]] = i;
//            i                = p;
//            p                = parent(p);
//        }
//        heap   [i] = x;
//        indices[x] = i;
//    }
	private void percolateUp(int i) {
        int x  = heap.get(i);
        int p  = parent(i);
        
        while (i != 0 && lt.call(x, heap.get(p))){
            heap.set(i, heap.get(p));
            indices.set(heap.get(p), i);
            i                = p;
            p                = parent(p);
        }
        heap.set(i, x);
        indices.set(x,  i);
	}
//
//
//    void percolateDown(int i)
//    {
//        int x = heap[i];
//        while (left(i) < heap.size()){
//            int child = right(i) < heap.size() && lt(heap[right(i)], heap[left(i)]) ? right(i) : left(i);
//            if (!lt(heap[child], x)) break;
//            heap[i]          = heap[child];
//            indices[heap[i]] = i;
//            i                = child;
//        }
//        heap   [i] = x;
//        indices[x] = i;
//    }
    private void percolateDown(int i) {
        int x = heap.get(i);
        while (left(i) < heap.size()){
            int child = right(i) < heap.size() && lt.call(heap.get(right(i)), heap.get(left(i))) ? right(i) : left(i);
            if (!lt.call(heap.get(child), x)) break;
            heap.set(i, heap.get(child));
            indices.set(heap.get(i), i);
            i = child;
        }
        heap.set(i, x);
        indices.set(x,  i);
    }
//
//
//  public:
//    Heap(const Comp& c) : lt(c) { }
    public HeapVarOrderLt(VarOrderLt lt) {
    	this.lt = lt;
    }
//
//    int  size      ()          const { return heap.size(); }
    public int size() { return heap.size(); }
//    bool empty     ()          const { return heap.size() == 0; }
    public boolean empty() { return heap.size() == 0; }
//    bool inHeap    (int n)     const { return n < indices.size() && indices[n] >= 0; }
    public boolean inHeap(int n) { return n < indices.size() && indices.get(n) >= 0; }
//    int  operator[](int index) const { assert(index < heap.size()); return heap[index]; }
    public int get(int index) {
    	if (index >= heap.size())
    		throw new IndexOutOfBoundsException("index");
    	return heap.get(index);
    }
//
//
//    void decrease  (int n) { assert(inHeap(n)); percolateUp  (indices[n]); }
    public void decrease(int n) {
    	if (!inHeap(n))
    		throw new IllegalArgumentException("n");
    	percolateUp(indices.get(n));
    }
//    void increase  (int n) { assert(inHeap(n)); percolateDown(indices[n]); }
    public void indrease(int n) {
    	if (!inHeap(n))
    		throw new IllegalArgumentException("n");
    	percolateDown(indices.get(n));
    }
//
//
//    // Safe variant of insert/decrease/increase:
//    void update(int n)
//    {
//        if (!inHeap(n))
//            insert(n);
//        else {
//            percolateUp(indices[n]);
//            percolateDown(indices[n]); }
//    }
    public void update(int n) {
    	if (!inHeap(n))
    		insert(n);
    	else {
    		percolateUp(indices.get(n));
    		percolateDown(indices.get(n));
    	}
    }
//
//
//    void insert(int n)
//    {
//        indices.growTo(n+1, -1);
//        assert(!inHeap(n));
//
//        indices[n] = heap.size();
//        heap.push(n);
//        percolateUp(indices[n]); 
//    }
    public void insert(int n) {
    	indices.growTo(n + 1, -1);
    	if (inHeap(n))
    		throw new IllegalArgumentException("n");
    	indices.set(n, heap.size());
    	heap.push(n);
    	percolateUp(indices.get(n));
    }
//
//
//    int  removeMin()
//    {
//        int x            = heap[0];
//        heap[0]          = heap.last();
//        indices[heap[0]] = 0;
//        indices[x]       = -1;
//        heap.pop();
//        if (heap.size() > 1) percolateDown(0);
//        return x; 
//    }
    public int removeMin() {
    	int x = heap.get(0);
    	heap.set(0, heap.last());
    	indices.set(heap.get(0), 0);
    	indices.set(x,  -1);
    	heap.pop();
    	if (heap.size() > 1)
    		percolateDown(0);
    	return x;
    }
//
//
//    // Rebuild the heap from scratch, using the elements in 'ns':
//    void build(vec<int>& ns) {
//        for (int i = 0; i < heap.size(); i++)
//            indices[heap[i]] = -1;
//        heap.clear();
//
//        for (int i = 0; i < ns.size(); i++){
//            indices[ns[i]] = i;
//            heap.push(ns[i]); }
//
//        for (int i = heap.size() / 2 - 1; i >= 0; i--)
//            percolateDown(i);
//    }
    public void build(VecInt ns) {
        for (int i = 0; i < heap.size(); i++)
            indices.set(heap.get(i), -1);
        heap.clear();
        for (int i = 0; i < ns.size(); i++){
        	indices.set(ns.get(i), i);
        	heap.push(ns.get(i));
        }
        for (int i = heap.size() / 2 - 1; i >= 0; i--)
            percolateDown(i);
    }
//
//    void clear(bool dealloc = false) 
//    { 
//        for (int i = 0; i < heap.size(); i++)
//            indices[heap[i]] = -1;
//        heap.clear(dealloc); 
//    }
    public void clear() { 
        for (int i = 0; i < heap.size(); i++)
        	indices.set(heap.get(i), -1);
        heap.clear(); 
    }
//};
//
//
////=================================================================================================
//}
}
