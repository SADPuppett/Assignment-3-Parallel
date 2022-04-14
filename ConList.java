

class ConList<Integer extends Comparable<Integer>>
  {
    private Present<Integer> head;
    private Present<Integer> tail;

    public ConList(int Max) { // make head and tail outside limits of contents
		head = new Present<>(-1);
		head.next = new Present<>(Max + 1);
    tail = head.next;
	  }


    private boolean validate(Present<Integer> pred, Present<Integer> curr) {
		  return !pred.marked && !curr.marked && pred.next == curr;
	  }

    public boolean add(int tag) {
  		while (true) {
  			Present<Integer> pred = this.head;
  			Present<Integer> curr = head.next;
  			while (curr.tag < tag) {
  				pred = curr;
  				curr = curr.next;
  			}
  			pred.lock();
  			try {
  				curr.lock();
  				try {
  					if (validate(pred, curr)) { // Boths nodes are locked and in list, can add   
                if (curr.tag == tag)  // already exists
							    return false;
                else{
    							Present<Integer> present = new Present<>(tag);
    							present.next = curr;
    							pred.next = present;
    							return true;
                }
  					}
  				} finally { 
  					curr.unlock();
  				}
  			} finally { 
  				pred.unlock();
  			}
  		}
	  }

    public boolean remove(/*int tag*/) { // removes 1st node other than head
  			Present<Integer> pred = this.head;
  			Present<Integer> curr = head.next;
  			/*while (curr.tag < tag) {
  				pred = curr;
  				curr = curr.next;
  			}*/
  			pred.lock();
  			try {
  				curr.lock();
  				try {
  					if (validate(pred, curr)) {
  						if (curr == tail) { // cant remove, cause nothing there
  							return false;
  						} else {
  							curr.marked = true; // mark logical removal
  							pred.next = curr.next; 
  							return true;
  						}
  					}
  				} finally { 
  					curr.unlock();
  				}
  			} finally { 
  				pred.unlock();
  			}
      return false;
  	}

    public boolean search(int tag) {
  		Present<Integer> curr = this.head;
  		while (curr.tag < tag)
  			curr = curr.next;
  		return curr.tag == tag && !curr.marked;
  	}

    public int length() {
      int length = 0;
  		Present<Integer> curr = this.head;
      while (curr.next == null)
        {
          curr = curr.next;
          length++;
        }
  		return length;
  	}

    
    
  }