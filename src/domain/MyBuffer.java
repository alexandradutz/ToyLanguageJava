package domain;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by Dutzi on 1/21/2016.
 */
public class MyBuffer implements Serializable {
    private Integer[] elements;
    private Integer threadId;

    public MyBuffer(Integer id){
        this.elements =  new Integer[2];
        this.threadId = id;
    }

    public Integer[] getElements() {
        return elements;
    }

    public void setElements(Integer[] elements) {
        this.elements = elements;
    }

    public Integer getThreadId() {
        return threadId;
    }

    public void setThreadId(Integer threadId) {
        this.threadId = threadId;
    }

    @Override
    public String toString() {
        return "File opened by " + this.threadId + " with the content: " + Arrays.toString(elements);
    }
}
