public class List<T> {
    item head;
    item tail;
    int length;

    List() {
        this.head = null;
        this.tail = null;
        this.length = 0;
    }

    class item<T> {

        T data;
        item<T> next;
        item<T> prev;

        item(T data)
        {
            this.data = data;
            this.next = null;
            this.prev = null;
        }

    }

    public item insert (T newData){
        item x = new item<T>(newData);

        if(isEmpty()){
            this.tail = x;
        }
        else{
            x.next = this.head;
            head.prev = x;
        }
        this.head = x;

        length++;

        return x;

    }

    public void delete(item item){

        if(item == null){
            return;
        }

        if(item == tail){
            tail = item.prev;
        }

        if (item .prev != null){
            item.prev.next = item.next;
        }
        else
            this.head = item.next;
        if (item.next != null){
            item.next.prev = item.prev;
        }
        length--;
    }


    public boolean isEmpty() {
        return this.head == null;
    }

    public List<T> copyList (){
        List<T> newList = new List<>();

        item temp = this.tail;

        while (temp != null){
            newList.insert((T) temp.data);
            temp = temp.prev;
        }

        return newList;
    }

}
