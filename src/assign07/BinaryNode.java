package assign07;

/**
 * Represents a generically-typed binary tree node. Each binary node contains
 * data, a reference to the left child, and a reference to the right child.
 *
 * @author Erin Parker and Michael Wadley
 * @version March 19, 2021
 */
public class BinaryNode<Type> {

    private Type data;

    private BinaryNode<Type> leftChild;

    private BinaryNode<Type> rightChild;

    private BinaryNode<Type> parent;

    /**
     * Constructs a BinaryNode object, and assigns its data, left child, & right child.
     *
     * @param data       The data to store in the node.
     * @param leftChild  The left child node of this node.
     * @param rightChild The right child node of this node.
     */
    public BinaryNode(Type data, BinaryNode<Type> leftChild, BinaryNode<Type> rightChild, BinaryNode<Type> parent) {
        this.data = data;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.parent = parent;
    }

    /**
     * Constructs a BinaryNode object, and assigns its data.
     *
     * @param data The data to store in the node.
     */
    public BinaryNode(Type data) {
        this(data, null, null, null);
    }

    /**
     * @return the node data
     */
    public Type getData() {
        return data;
    }

    /**
     * @param data - the node data to be set
     */
    public void setData(Type data) {
        this.data = data;
    }

    /**
     * @return reference to the left child node
     */
    public BinaryNode<Type> getLeftChild() {
        return leftChild;
    }

    /**
     * @return reference to the parent node.
     */
    public BinaryNode<Type> getParent() {
        return parent;
    }

    /**
     * @param parent - reference of the parent node to be set
     */
    public void setParent(BinaryNode<Type> parent) {
        this.parent = parent;
    }

    /**
     * @param leftChild - reference of the left child node to be set
     */
    public void setLeftChild(BinaryNode<Type> leftChild) {
        this.leftChild = leftChild;
    }

    /**
     * @return reference to the right child node
     */
    public BinaryNode<Type> getRightChild() {
        return rightChild;
    }

    /**
     * @param rightChild - reference of the right child node to be set
     */
    public void setRightChild(BinaryNode<Type> rightChild) {
        this.rightChild = rightChild;
    }

    /**
     * @return reference to the leftmost node in the binary tree rooted at this node
     */
    public BinaryNode<Type> getLeftmostNode() {
        if (leftChild == null) {
            return this;
        }
        return leftChild.getLeftmostNode();
    }

    /**
     * @return reference to the rightmost node in the binary tree rooted at this node
     */
    public BinaryNode<Type> getRightmostNode() {
        if (rightChild == null)
            return this;
        return rightChild.getRightmostNode();
    }

    /**
     * @return the height of the binary tree rooted at this node
     * <p>
     * The height of a tree is the length of the longest path to a leaf
     * node. Consider a tree with a single node to have a height of zero.
     */
    public int height() {
        if (leftChild == null && rightChild == null)
            return 0;
        int leftCount = 0;
        int rightCount = 0;
        if (leftChild != null)
            leftCount += leftChild.height();
        if (rightChild != null)
            rightCount += rightChild.height();
        return Math.max(leftCount, rightCount) + 1;
    }

    /**
     * Determines and returns the node containing the next sorted item (the successor node)
     *
     * @return the successor node
     */
    public BinaryNode<Type> successor() {
        return rightChild.getLeftmostNode();
    }

}
