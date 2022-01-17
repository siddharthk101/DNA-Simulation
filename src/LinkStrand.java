

public class LinkStrand implements IDnaStrand {

    private class Node {
        String info;
        Node next;
        public Node(String s) {
            info = s;
            next = null;
        }
    }
    private Node myFirst,myLast;
    private long mySize;
    private int myAppends;
    private int myIndex;
    private int myLocalIndex;
    private Node myCurrent;

    public LinkStrand(String source){
        initialize(source);
    }

    /**
     * calls initialize for this LinkStrand with given String
     */
    public LinkStrand(){
        this("");
    }

    /**
     * gives size of this LinkStrand
     * @return mySize
     */
    @Override
    public long size() {
        return mySize;
    }

    /**
     * initialized LinkStrand object given a string. initializes instance variables.
     * @param source
     */
    @Override
    public void initialize(String source) {
            myFirst = new Node(source);
            myLast = myFirst;
            myAppends = 0;
            mySize = source.length();
            myIndex = 0;
            myLocalIndex = 0;
            myCurrent = myFirst;
    }

    /**
     * String Source is a strand of DNA
     * @param source is data from which object constructed
     * @return new LinkStrand(source)
     */
    @Override
    public IDnaStrand getInstance(String source) {
        return new LinkStrand(source);
    }


    /**
     * adds new node with info = dna to this LinkStrand
     * @param dna
     *            is the string appended to this strand
     * @return IDnaStrand object with added node with info = dna
     */
    @Override
    public IDnaStrand append(String dna) {
        myLast.next = new Node(dna);
        myLast = myLast.next;
        mySize += dna.length();
        myAppends++;
        return this;
    }

    /**
     * Creates a new LinkStrand which is the reverse of this LinkStrand
     * @return copy, the reverse of this LinkStrand
     */
    @Override
    public IDnaStrand reverse() {
        LinkStrand copy = new LinkStrand();
        Node temp = myFirst;
        Node ret = null;
        while (temp != null) {
            StringBuilder str = new StringBuilder(temp.info);
            Node mid  = new Node(str.reverse().toString());
            mid.next = ret;
            ret = mid;
            temp = temp.next;
        }
        while (ret!=null){
            copy.append(ret.info);
            ret = ret.next;
        }
        return copy;

    }

    /**
     * value of myAppends
     * @return myAppends
     */
    @Override
    public int getAppendCount() {
        return myAppends;
    }

    /**
     * moves through LinkStrand until desired index is found
     * @param index specifies which character will be returned
     * @return String character at the given index
     */
    @Override
    public char charAt(int index) {
        if(index >= mySize || index < 0){
            throw new IndexOutOfBoundsException();
        }
        if (index < myIndex) {
            myIndex = 0;
            myLocalIndex = 0;
            myCurrent = myFirst;
        }
        while (myIndex != index) {
            myIndex++;
            myLocalIndex++;
            if (myLocalIndex >= myCurrent.info.length()) {
                myLocalIndex = 0;
                myCurrent = myCurrent.next;
            }
        }
        return myCurrent.info.charAt(myLocalIndex);

    }

    /**
     * loops over each node in LinkStrand and appends values to StringBuilder ret
     * @return LinkStrand as a String
     */
    @Override
    public String toString() {
        StringBuilderStrand ret = new StringBuilderStrand();
        Node temp = myFirst;
        while(temp!=null){
            ret.append(temp.info);
            temp = temp.next;
        }
        return ret.toString();
    }

}
