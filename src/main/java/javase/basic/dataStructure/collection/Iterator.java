package javase.basic.dataStructure.collection;

/**
 * 迭代器接口
 */
public interface Iterator<T> {
    
    /**next element*/
    T next();

    /**have next element*/
    boolean hasNext();
}
