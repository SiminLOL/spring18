public class LinkedListDeque<T>{
    public class IntNode{
        public IntNode prev;
        public T item;
        public IntNode next;
        public IntNode(IntNode a, T i,IntNode n){
            item=i;
            next=n;
            prev=a;
        }
    }
    private int size;
    private IntNode sentinel;


    /*Creates an empty linked list deque.*/
    public LinkedListDeque(){
        sentinel=new IntNode(null,null,null); /* creates the space for sentinel*/
        sentinel.prev=sentinel; /*set the value for sentinel*/
        sentinel.next=sentinel;
        size=0;
    }

    public LinkedListDeque(T x){
        sentinel=new IntNode(null,null,null); /* creates the space for sentinel*/
        sentinel.prev=sentinel; /*set the value for sentinel*/
        sentinel.next=sentinel;
        size=1;
    }

    public void addFirst(T x) {
        sentinel.next = new IntNode(sentinel,x,sentinel.next);
        sentinel.next.next.prev=sentinel.next;
        size+=1;
    }

    public boolean isEmpty(){
        return(size==0);
    }

    public void addLast(T x){
        sentinel.prev=new IntNode(sentinel.prev,x,sentinel);
        sentinel.prev.prev.next=sentinel.prev;
        size+=1;
    }


    public int size(){
        return size;
    }

    public void printDeque(){
        IntNode p=sentinel.next;
        while(p.next!=sentinel){
            System.out.print(p.item+" ");
            p=p.next;
        }
    }

    public T removeFirst(){
        if(sentinel.next==sentinel) {
            return null;
        }
        sentinel.next.next.prev=sentinel;
        sentinel.next=sentinel.next.next;
        size-=1;

        return sentinel.next.item;
    }

    public T removeLast(){
        if(sentinel.prev==sentinel){
            return null;
        }
        sentinel.prev.prev.next=sentinel;
        sentinel.prev=sentinel.prev.prev;
        return sentinel.prev.item;
    }

    public T get(int index){
        if(index>=size-1){
            return null;
        }
            IntNode p=sentinel.next;
            for(int i=0;i<index;i++){
                p=p.next;
                i+=1;
            }
            return p.item;
    }

    public IntNode getRecursiveHelper(int index){
        if(index==0){
            return sentinel.next;
        }
        return getRecursiveHelper(index-1).next;
    }

    public T getRecursive(int index){
        return getRecursiveHelper(index).item;
    }





}
