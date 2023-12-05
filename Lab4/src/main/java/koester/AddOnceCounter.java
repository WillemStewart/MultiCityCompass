// Interface AddOnceCounter.java

package koester;

interface AddOnceCounter <E extends Comparable<? super E>> { 
    public void incrementCounter();
    public int getCounter();
}
