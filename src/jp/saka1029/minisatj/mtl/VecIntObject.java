package jp.saka1029.minisatj.mtl;

import java.util.Iterator;
import java.util.NoSuchElementException;

///*******************************************************************************************[VecIntObject.h]
//Copyright (c) 2003-2007, Niklas Een, Niklas Sorensson
//Copyright (c) 2007-2010, Niklas Sorensson
//
//Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
//associated documentation files (the "Software"), to deal in the Software without restriction,
//including without limitation the rights to use, copy, modify, merge, publish, distribute,
//sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
//furnished to do so, subject to the following conditions:
//
//The above copyright notice and this permission notice shall be included in all copies or
//substantial portions of the Software.
//
//THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
//NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
//NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
//DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT
//OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
//**************************************************************************************************/
//
//#ifndef Minisat_VecIntObject_h
//#define Minisat_VecIntObject_h
//
//#include <assert.h>
//#include <new>
//
//#include "mtl/IntTypes.h"
//#include "mtl/XAlloc.h"
//
//namespace Minisat {
//
////=================================================================================================
//// Automatically resizable arrays
////
//// NOTE! Don't use this vector on datatypes that cannot be re-located in memory (with realloc)
//
//template<class T>
//class vec {
public abstract class VecIntObject<T> implements Iterable<T> {
//    T*  data;
	private int[] data = null;
//    int sz;
	private int sz = 0;
//    int cap;
	// see data.length.
//
//    // Don't allow copying (error prone):
//    vec<T>&  operator = (vec<T>& other) { assert(0); return *this; }
//             vec        (vec<T>& other) { assert(0); }
//             
//    // Helpers for calculating next capacity:
//    static inline int  imax   (int x, int y) { int mask = (y-x) >> (sizeof(int)*8-1); return (x&mask) + (y&(~mask)); }
	public static int imax(int x, int y) {
		int mask = (y - x) >> (4 * 8 - 1);
		return (x & mask) + (y & ~mask);
	}
//    //static inline void nextCap(int& cap){ cap += ((cap >> 1) + 2) & ~1; }
//    static inline void nextCap(int& cap){ cap += ((cap >> 1) + 2) & ~1; }
	// never used.
//
//public:
//    // Constructors:
//    vec()                       : data(NULL) , sz(0)   , cap(0)    { }
	private final T defaultObject;
	private final int defaultValue;
	
	public VecIntObject() {
		this.defaultObject = create();
		this.defaultValue = value(this.defaultObject);
	}
//    explicit vec(int size)      : data(NULL) , sz(0)   , cap(0)    { growTo(size); }
	public VecIntObject(int size) {
		this();
		growTo(size);
	}
//    vec(int size, const T& pad) : data(NULL) , sz(0)   , cap(0)    { growTo(size, pad); }
	public VecIntObject(int size, T pad) {
		this();
		growTo(size, pad);
	}
//   ~vec()                                                          { clear(true); }
//
//    // Pointer to first element:
//    operator T*       (void)           { return data; }
	// do not support.
//
//    // Size operations:
//    int      size     (void) const     { return sz; }
	public int size() { return sz; }
//    void     shrink   (int nelems)     { assert(nelems <= sz); for (int i = 0; i < nelems; i++) sz--, data[sz].~T(); }
	public void shrink(int nelems) {
		if (nelems > sz)
			throw new IllegalArgumentException("nelems");
		for (int i = 0; i < nelems; ++i)
			data[--sz] = defaultValue;
	}
//    void     shrink_  (int nelems)     { assert(nelems <= sz); sz -= nelems; }
	public void shrink_(int nelems) {
		if (nelems > sz)
			throw new IllegalArgumentException("nelems");
		sz -= nelems;
	}
//    int      capacity (void) const     { return cap; }
	public int capacity() { return data == null ? 0 : data.length; }
//    void     capacity (int min_cap);
//    void     growTo   (int size);
//    void     growTo   (int size, const T& pad);
//    void     clear    (bool dealloc = false);
//
//    // Stack interface:
//    void     push  (void)              { if (sz == cap) capacity(sz+1); new (&data[sz]) T(); sz++; }
	public void push() {
		push(defaultObject);
	}
//    void     push  (const T& elem)     { if (sz == cap) capacity(sz+1); data[sz++] = elem; }
	public void push(T elem) {
		if (sz == capacity())
			capacity(sz + 1);
		data[sz++] = value(elem);
	}
//    void     push_ (const T& elem)     { assert(sz < cap); data[sz++] = elem; }
	public void push_ (T elem) {
		if (sz >= capacity())
			throw new IndexOutOfBoundsException();
		data[sz++] = value(elem);
	}
//    void     pop   (void)              { assert(sz > 0); sz--, data[sz].~T(); }
	public void pop() {
		if (sz <= 0)
			throw new IndexOutOfBoundsException();
		sz--;
	}
//    // NOTE: it seems possible that overflow can happen in the 'sz+1' expression of 'push()', but
//    // in fact it can not since it requires that 'cap' is equal to INT_MAX. This in turn can not
//    // happen given the way capacities are calculated (below). Essentially, all capacities are
//    // even, but INT_MAX is odd.
//
//    const T& last  (void) const        { return data[sz-1]; }
	public T last() {
		return create(data[sz - 1]);
	}
//    T&       last  (void)              { return data[sz-1]; }
//
//    // VecIntObjecttor interface:
//    const T& operator [] (int index) const { return data[index]; }
	public T get(int index) {
//		if (index >= sz)
//			throw new IndexOutOfBoundsException("index");
		return create(data[index]);
	}
	public T set(int index, T elem) {
//		if (index >= sz)
//			throw new IndexOutOfBoundsException("index");
		data[index] = value(elem);
		return elem;
	}
//    T&       operator [] (int index)       { return data[index]; }
//
//    // Duplicatation (preferred instead):
//    void copyTo(vec<T>& copy) const { copy.clear(); copy.growTo(sz); for (int i = 0; i < sz; i++) copy[i] = data[i]; }
	public void copyTo(VecIntObject<T> copy) {
		copy.clear();
		copy.growTo(sz);
		if (sz <= 0) return;
		System.arraycopy(data, 0, copy.data, 0, sz);
	}
//    void moveTo(vec<T>& dest) { dest.clear(true); dest.data = data; dest.sz = sz; dest.cap = cap; data = NULL; sz = 0; cap = 0; }
	public void moveTo(VecIntObject<T> dest) {
		dest.clear(true);
		dest.data = data;
		dest.sz = sz;
		data = null;
		sz = 0;
	}
//};
//
//
//template<class T>
//void vec<T>::capacity(int min_cap) {
//    if (cap >= min_cap) return;
//    int add = imax((min_cap - cap + 1) & ~1, ((cap >> 1) + 2) & ~1);   // NOTE: grow by approximately 3/2
//    if (add > INT_MAX - cap || ((data = (T*)::realloc(data, (cap += add) * sizeof(T))) == NULL) && errno == ENOMEM)
//        throw OutOfMemoryException();
// }
	public void capacity(int min_cap) {
		int cap = capacity();
	    if (cap >= min_cap) return;
	    int add = imax((min_cap - cap + 1) & ~1, ((cap >> 1) + 2) & ~1);   // NOTE: grow by approximately 3/2
		int[] newData = new int[cap + add];
	    if (data != null)
	    	System.arraycopy(data, 0, newData, 0, sz);
	    data = newData;
	 }
//
//
//template<class T>
//void vec<T>::growTo(int size, const T& pad) {
//    if (sz >= size) return;
//    capacity(size);
//    for (int i = sz; i < size; i++) data[i] = pad;
//    sz = size; }
	public void growTo(int size, T pad) {
	    if (sz >= size) return;
	    capacity(size);
	    for (int i = sz; i < size; i++)
	    	data[i] = value(pad);
	    sz = size;
	}
//
//
//template<class T>
//void vec<T>::growTo(int size) {
//    if (sz >= size) return;
//    capacity(size);
//    for (int i = sz; i < size; i++) new (&data[i]) T();
//    sz = size; }
	public void growTo(int size) {
		growTo(size, create());
	}
//
//
//template<class T>
//void vec<T>::clear(bool dealloc) {
//    if (data != NULL){
//        for (int i = 0; i < sz; i++) data[i].~T();
//        sz = 0;
//        if (dealloc) free(data), data = NULL, cap = 0; } }
	public void clear(boolean dealloc) {
		if (data != null) {
			sz = 0;
			if (dealloc) data = null;
		}
	}
	
	public void clear() {
		clear(false);
	}
//
////=================================================================================================
//}
//
//#endif
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		String sep = "";
		for (int i = 0; i < sz; ++i) {
			sb.append(sep).append(toString(data[i]));
			sep = ", ";
		}
		sb.append("]");
		return sb.toString();
	}
	
    public void quickSort(int left, int right){
        if (left <= right) {
            int p = data[(left+right) / 2];
            int l = left;
            int r = right;
            while(l <= r) {
                while(compare(data[l], p) < 0) l++;
                while(compare(data[r], p) > 0){ r--; }
                
                if (l <= r) {
                    int tmp = data[l];
                    data[l] = data[r];
                    data[r] = tmp;
                    l++; 
                    r--;
                }
            }
            quickSort(left, r);
            quickSort(l, right);
        }
    }

	public void sort() {
		if (data == null) return;
		quickSort(0, sz - 1);
	}

	public void remove(T t) {
	    int j = 0;
	    for (; j < data.length && data[j] != value(t); j++);
	    if (j >= data.length)
	    	throw new NoSuchElementException("t");
	    for (; j < data.length-1; j++) data[j] = data[j+1];
	    pop();
	}
	
	protected abstract T create();
	protected abstract T create(int value);
	protected abstract int value(T object);
	protected abstract int compare(int a, int b);
	protected abstract String toString(int value);
	
	@Override
		public Iterator<T> iterator() {
			return new Iterator<T>() {
				int i = 0;
				@Override public boolean hasNext() { return i < sz; }
				@Override public T next() { return create(data[i++]); }
			};
		}
}