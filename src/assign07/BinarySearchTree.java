package assign07;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

/**
 * A class for representing a sorted set of generically-typed items. Contains no duplicate items.
 * Items are ordered using their natural ordering (i.e., each item must be Comparable).
 *
 * @author Michael Wadley & Adam Liu
 * @version 10/24/22
 */
public class BinarySearchTree<Type extends Comparable<? super Type>> implements SortedSet<Type> {

    private BinaryNode<Type> rootNode;

    private int size = 0;

    public BinarySearchTree() {
        rootNode = null;
    }

    /**
     * Ensures that this set contains the specified item.
     *
     * @param item - the item whose presence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     * the input item was actually inserted); otherwise, returns false
     */
    @Override
    public boolean add(Type item) {
        return add(item, rootNode);
    }

    /**
     * Recursive method used by the add driver method.
     * Adds the item to its sorted position in the BST.
     *
     * @param item the item whose presence is ensured in this set
     * @param node A BinaryNode object
     * @return true if this set changed as a result of this method call (that is, if
     * the input item was actually inserted); otherwise, returns false
     */
    protected boolean add(Type item, BinaryNode<Type> node) {
        if (node == null) {
            rootNode = new BinaryNode<>(item);
            size++;
            return true;
        }
        if (item.compareTo(node.getData()) < 0) {
            if (node.getLeftChild() == null) {
                node.setLeftChild(new BinaryNode<>(item));
                node.getLeftChild().setParent(node);
                size++;
                return true;
            } else
                return add(item, node.getLeftChild());
        }
        if (item.compareTo(node.getData()) > 0) {
            if (node.getRightChild() == null) {
                node.setRightChild(new BinaryNode<>(item));
                node.getRightChild().setParent(node);
                size++;
                return true;
            } else
                return add(item, node.getRightChild());
        }
        return false;
    }

    /**
     * Ensures that this set contains all items in the specified collection.
     *
     * @param items - the collection of items whose presence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     * any item in the input collection was actually inserted); otherwise,
     * returns false
     */
    @Override
    public boolean addAll(Collection<? extends Type> items) {
        boolean flag = false;
        for (Type item : items) {
            if (add(item))
                flag = true;
        }
        return flag;
    }

    /**
     * Removes all items from this set. The set will be empty after this method
     * call.
     */
    @Override
    public void clear() {
        rootNode = null;
        size = 0;
    }

    /**
     * Determines if there is an item in this set that is equal to the specified
     * item.
     *
     * @param item - the item sought in this set
     * @return true if there is an item in this set that is equal to the input item;
     * otherwise, returns false
     */
    @Override
    public boolean contains(Type item) {
        return contains(rootNode, item);
    }

    /**
     * Recursive method used by the contains driver.
     * Searches the set until the item is found and returns true. If the item is not found, return false.
     *
     * @param node A BinaryNode object.
     * @param item the item sought in this set
     * @return true if there is an item in this set that is equal to the input item;
     * otherwise, returns false
     */
    protected boolean contains(BinaryNode<Type> node, Type item) {
        if (node == null)
            return false;
        if (node.getData().equals(item))
            return true;
        if (item.compareTo(node.getData()) < 0)
            return contains(node.getLeftChild(), item);
        if (item.compareTo(node.getData()) > 0)
            return contains(node.getRightChild(), item);
        return false;
    }

    /**
     * Determines if for each item in the specified collection, there is an item in
     * this set that is equal to it.
     *
     * @param items - the collection of items sought in this set
     * @return true if for each item in the specified collection, there is an item
     * in this set that is equal to it; otherwise, returns false
     */
    @Override
    public boolean containsAll(Collection<? extends Type> items) {
        boolean flag = true;
        for (Type item : items) {
            if (!contains(item))
                flag = false;
        }
        return flag;
    }

    /**
     * Returns the first (i.e., smallest) item in this set.
     *
     * @throws NoSuchElementException if the set is empty
     */
    @Override
    public Type first() throws NoSuchElementException {
        if (isEmpty())
            throw new NoSuchElementException();
        return rootNode.getLeftmostNode().getData();
    }

    /**
     * Returns true if this set contains no items.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the last (i.e., largest) item in this set.
     *
     * @throws NoSuchElementException if the set is empty
     */
    @Override
    public Type last() throws NoSuchElementException {
        if (isEmpty())
            throw new NoSuchElementException();
        return rootNode.getRightmostNode().getData();
    }

    /**
     * Ensures that this set does not contain the specified item.
     *
     * @param item - the item whose absence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     * the input item was actually removed); otherwise, returns false
     */
    @Override
    public boolean remove(Type item) {
        if (rootNode == null)
            return false;
        if (rootNode.getData() == item) {
            if (rootNode.getLeftChild() == null && rootNode.getRightChild() == null) {
                this.clear();
                return true;
            }
            if (rootNode.getLeftChild() == null && rootNode.getRightChild() != null)
                rootNode = rootNode.getRightChild();
            if (rootNode.getLeftChild() != null && rootNode.getRightChild() == null)
                rootNode = rootNode.getLeftChild();
            if (rootNode.getLeftChild() != null && rootNode.getRightChild() != null) {
                rootNode.setData(rootNode.successor().getData());
                if (rootNode.successor() == rootNode.getRightChild())
                    rootNode.setRightChild(rootNode.successor().getRightChild());
                else {
                    if (rootNode.successor().getParent().getLeftChild() == rootNode.successor())
                        rootNode.successor().getParent().setLeftChild(null);
                    else
                        rootNode.successor().getParent().setRightChild(null);
                }
            }
            size--;
            return true;
        }
        if (item.compareTo(rootNode.getData()) < 0)
            return remove(item, rootNode.getLeftChild());
        if (item.compareTo(rootNode.getData()) > 0)
            return remove(item, rootNode.getRightChild());
        return false;
    }

    /**
     * Recursive method used by the remove driver.
     *
     * @param item the item whose absence is ensured in this set
     * @param node a BinaryNode object.
     * @return true if this set changed as a result of this method call (that is, if
     * the input item was actually removed); otherwise, returns false
     */
    public boolean remove(Type item, BinaryNode<Type> node) {
        if (node == null)
            return false;
        if (node.getData() == item) {
            if (node.getLeftChild() == null && node.getRightChild() == null) {
                if (node.getParent().getRightChild() == node)
                    node.getParent().setRightChild(null);
                if (node.getParent().getLeftChild() == node)
                    node.getParent().setLeftChild(null);
            }
            if (node.getLeftChild() == null && node.getRightChild() != null) {
                if (node.getParent().getRightChild() == node)
                    node.getParent().setRightChild(node.getRightChild());
                if (node.getParent().getLeftChild() == node)
                    node.getParent().setLeftChild(node.getRightChild());
            }
            if (node.getLeftChild() != null && node.getRightChild() == null) {
                if (node.getParent().getRightChild() == node) {
                    node.getParent().setRightChild(node.getLeftChild());
                }
                if (node.getParent().getLeftChild() == node)
                    node.getParent().setLeftChild(node.getLeftChild());
            }
            if (node.getLeftChild() != null && node.getRightChild() != null) {
                node.setData(node.successor().getData());
                if (node.successor() == node.getRightChild())
                    node.setRightChild(node.successor().getRightChild());
                else {
                    if (node.successor().getParent().getLeftChild() == node.successor())
                        node.successor().getParent().setLeftChild(null);
                    else
                        node.successor().getParent().setRightChild(null);
                }
            }
            size--;
            return true;
        }
        if (item.compareTo(node.getData()) < 0)
            return remove(item, node.getLeftChild());
        if (item.compareTo(node.getData()) > 0)
            return remove(item, node.getRightChild());
        return false;
    }

    /**
     * Ensures that this set does not contain any of the items in the specified
     * collection.
     *
     * @param items - the collection of items whose absence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     * any item in the input collection was actually removed); otherwise,
     * returns false
     */
    @Override
    public boolean removeAll(Collection<? extends Type> items) {
        boolean flag = false;
        for (Type item : items) {
            if (remove(item))
                flag = true;
        }
        return flag;
    }

    /**
     * Returns the number of items in this set.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns an ArrayList containing all the items in this set, in sorted order.
     */
    @Override
    public ArrayList<Type> toArrayList() {
        ArrayList<Type> list = new ArrayList<>();
        toArrayList(list, rootNode);
        return list;
    }

    /**
     * Recursive method used by the toArrayList driver.
     *
     * @param list The list to which to add all items and return.
     * @param node A BinaryNode object.
     */
    public void toArrayList(ArrayList<Type> list, BinaryNode<Type> node) {
        if (node == null)
            return;
        toArrayList(list, node.getLeftChild());
        list.add(node.getData());
        toArrayList(list, node.getRightChild());
    }

    /**
     * Writes the tree to a dot file
     * @param filename - the file to write to
     */
    public void writeDot(String filename)
    {
        try {
            // PrintWriter(FileWriter) will write output to a file
            PrintWriter output = new PrintWriter(new FileWriter(filename));

            // Set up the dot graph and properties
            output.println("digraph BST {");
            output.println("node [shape=record]");

            if(rootNode != null)
                writeDotRecursive(rootNode, output);
            // Close the graph
            output.println("}");
            output.close();
        }
        catch(Exception e){e.printStackTrace();}
    }


    /**
     * Recursive helper for writing this tree to a dot file
     * @param n - the current subtree
     * @param output - a PrintWriter with an open output file
     * @throws Exception
     */
    private void writeDotRecursive(BinaryNode<Type> n, PrintWriter output) throws Exception
    {
        output.println(n.getData() + "[label=\"<L> |<D> " + n.getData() + "|<R> \"]");
        if(n.getLeftChild() != null)
        {
            // write the left subtree
            writeDotRecursive(n.getLeftChild(), output);

            // then make a link between n and the left subtree
            output.println(n.getData() + ":L -> " + n.getLeftChild().getData() + ":D" );
        }
        if(n.getRightChild() != null)
        {
            // write the left subtree
            writeDotRecursive(n.getRightChild(), output);

            // then make a link between n and the right subtree
            output.println(n.getData() + ":R -> " + n.getRightChild().getData() + ":D" );
        }
    }

}
