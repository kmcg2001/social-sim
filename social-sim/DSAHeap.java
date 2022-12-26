// [Kade McGarraghy].[First Year].[Previously submitted DSA Prac 07]

public class DSAHeap
{

    private DSAHeapEntry[] heap;
    private int count;

    public DSAHeap(int maxSize)
    {
        heap = new DSAHeapEntry[maxSize];
        count = 0;
    }

    public DSAHeapEntry[] getHeap()
    {
        return heap;
    }

    public int getCount()
    {
        return count;
    }

    public String display()
    {
        String str = "";
        for (int i = 0; i < count; i++)
        {
            str = str + (heap[i].toString()) + "\n";
        }
        return str;
    }

    public void add(int priority, Object value)
    {
        if (count == heap.length)
        {
            throw new IllegalArgumentException("Heap is full.");
        } else
        {
            DSAHeapEntry item = new DSAHeapEntry(priority, value);
            heap[count] = item;
            trickleUp(count);
            count++;
        }
    }

    public DSAHeapEntry remove()
    {
        DSAHeapEntry temp;
        DSAHeapEntry removedItem;
        if (count == 0)
        {
            throw new IllegalArgumentException("Cannot remove from empty heap.");
        } else
        {
            temp = heap[0];
            removedItem = temp;
            heap[0] = heap[count - 1];
            trickleDown(0, count);
            count--;
        }
        return removedItem;
    }

    public void heapify()
    {
        for (int i = (count / 2) - 1; i >= 0; i--)
        {
            trickleDown(i, count);
        }
    }

    public void heapSort(Object[] values)
    {
        DSAHeapEntry temp;
        heap = (DSAHeapEntry[]) values;
        count = values.length;
        heapify();
        for (int i = (count - 1); i >= 1; i--)
        {
            swap(0, i);
            trickleDown(0, i);

        }

    }

    private void trickleUp(int index)
    {
        DSAHeapEntry temp;
        int parentIndex = (index - 1) / 2;
        if (index > 0)
        {
            if (heap[index].getPriority() > heap[parentIndex].getPriority())
            {
                temp = heap[parentIndex];
                heap[parentIndex] = heap[index];
                heap[index] = temp;
                trickleUp(parentIndex);
            }
        }
    }

    private void trickleDown(int index, int numItems)
    {
        DSAHeapEntry temp;
        int lChildIndex = (index * 2) + 1;
        int rChildIndex = lChildIndex + 1;
        int largeIndex;

        if (lChildIndex < numItems)
        {
            largeIndex = lChildIndex;
            if (rChildIndex < numItems)
            {
                if (heap[lChildIndex].getPriority() < heap[rChildIndex].getPriority())
                {
                    largeIndex = rChildIndex;
                }
            }
            if (heap[largeIndex].getPriority() > heap[index].getPriority())
            {
                swap(largeIndex, index);
                trickleDown(largeIndex, numItems);
            }
        }
    }

    private void swap(int first, int second)
    {
        DSAHeapEntry temp;
        temp = heap[first];
        heap[first] = heap[second];
        heap[second] = temp;
    }
}







