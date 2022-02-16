/**

 * This class represents a Generic List to be used as a Data Structure

 * @version final

 * @author Sapir Talker and Bar Amrani
 * @date 16/02/2022
 *
 * fields:
 *
 *  head : Head of this List
 *  tail : Tail of this List
 *  length : The length of the List (int)


 */

@SuppressWarnings("all")
public class List<T> {
    item head;
    item tail;
    int length;

    List() {
        this.head = null;
        this.tail = null;
        this.length = 0;
    }

    /**
     * this class represents a Generic Item of the List
     * @param <T> the data's Type
     *
     *
     *
     *   data the data that is being held inside this Item
     *   next the next item in the list
     *   prev the prev item in the list
     */
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

    /**
     * Inserting a new data to this list
     * @param newData the data to be inserted
     * @return the holding item's reference
     */
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

    /**
     * Delete an item from the list
     * @param item deletes this item from the List
     */
    public void delete(item item){

        if(item == null){
            return;
        }

        if(item == tail && item == head){
            this.head = this.tail = null;
        }
        else if(item == tail){
            tail = item.prev;
            item.prev.next = null;
        }
        else if (item.prev != null){
            item.prev.next = item.next;
        }
        else
            this.head = item.next;

        if (item.next != null){
            item.next.prev = item.prev;
        }
        length--;
    }

    /**
     *
     * @return if the List is empty
     */
    public boolean isEmpty() {
        return this.head == null;
    }
    
}
