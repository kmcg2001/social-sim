// [Kade McGarraghy].[First Year].[Previously submitted DSA Prac 07]
public class UnitTestDSAHeap
{
    public static void main(String[] args)
    {
        DSAHeap tempHeap = new DSAHeap(9);
        DSAHeapEntry removedItem;


        // heapSort() TEST
        System.out.println("Testing: heapSort()");
        DSAHeapEntry[] entryArray = new DSAHeapEntry[9];

        System.out.println("\nInitial array:");
        entryArray[0] = new DSAHeapEntry(5, 0);
        System.out.println(entryArray[0].toString());
        entryArray[1] = new DSAHeapEntry(4, 0);
        System.out.println(entryArray[1].toString());
        entryArray[2] = new DSAHeapEntry(1, 0);
        System.out.println(entryArray[2].toString());
        entryArray[3] = new DSAHeapEntry(11, 0);
        System.out.println(entryArray[3].toString());
        entryArray[4] = new DSAHeapEntry(10, 0);
        System.out.println(entryArray[4].toString());
        entryArray[5] = new DSAHeapEntry(3, 0);
        System.out.println(entryArray[5].toString());
        entryArray[6] = new DSAHeapEntry(2, 0);
        System.out.println(entryArray[6].toString());
        entryArray[7] = new DSAHeapEntry(16, 0);
        System.out.println(entryArray[7].toString());
        entryArray[8] = new DSAHeapEntry(12, 0);
        System.out.println(entryArray[8].toString());


        tempHeap.heapSort(entryArray);
        if (tempHeap.display().equals("1,0\n2,0\n3,0\n4,0\n5,0\n10,0\n11,0\n12,0\n16,0\n"))
        {
            System.out.println("\n============ HEAP SORTING: PASSED ============");
        }
        else
        {
            System.out.println("\n============ HEAP SORTING: FAILED ============");
        }

        System.out.println("\nSorted array:");
        System.out.println(tempHeap.display());
        System.out.println("---------------------------");

    }
}
