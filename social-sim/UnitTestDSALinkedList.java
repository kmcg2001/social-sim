 /***************************************************************************
 *  FILE NAME: DSALinkedListTestHarness.java                                *
 *  ORIGINAL AUTHOR: Connor Beardsmore - 15504319                           *
 *  LAST MODIFIED BY: Kade McGarraghy (29/9/19)                             *
 *  [First Year].[Previosly Submitted DSA - Prac 03]*                       *
 ***************************************************************************/

import java.util.*;

public class UnitTestDSALinkedList
{
	public static void main(String args[])
	{
        // VARIABLE DECLARATIONS
        int iNumPassed = 0;
        int iNumTests = 0;
        DSALinkedList ll = null;
        String sTestString;
        Object nodeValue;
        Scanner sc = new Scanner(System.in);

//---------------------------------------------------------------------------

        System.out.println("\n\nTesting Normal Conditions - Constructor");
        System.out.println("=======================================");

        // TEST 1 : CONSTRUCTOR
        try {
            iNumTests++;
            ll = new DSALinkedList();
            System.out.print("Testing creation of DSALinkedList (isEmpty()): ");
            if (ll.isEmpty() == false)
                throw new IllegalArgumentException("Head must be null.");
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }

//---------------------------------------------------------------------------

        System.out.println("\nTest inserting first and removing first (stack behaviour)");
        System.out.println("==========================================================");

        // TEST 2 : INSERT FIRST
        try {
            iNumTests++;
            System.out.print("Testing insertFirst(): ");
            ll.insertFirst("abc");
            ll.insertFirst("jkl");
            ll.insertFirst("xyz");
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }

        // TEST 3 : PEEK LAST
        try {
            iNumTests++;
            System.out.print("Testing peekLast(): ");
            sTestString = (String)ll.peekLast();
            if (sTestString != "abc")
                throw new IllegalArgumentException("FAILED.");
            iNumPassed++;
            System.out.println("passed   ");
        } catch(Exception e) { System.out.println("FAILED"); }

        // TEST 4 : REMOVE FIRST
        try {
            iNumTests++;
            System.out.print("Testing removeFirst(): ");
            sTestString = (String)ll.removeFirst();
            if (sTestString != "xyz")
                throw new IllegalArgumentException("FAILED.");
            sTestString = (String)ll.removeFirst();
            if (sTestString != "jkl")
                throw new IllegalArgumentException("FAILED.");
            sTestString = (String)ll.removeFirst();
            if (sTestString != "abc")
                throw new IllegalArgumentException("FAILED.");
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }

        // TEST 5 : IS EMPTY
        try {
            iNumTests++;
            System.out.print("Testing isEmpty(): ");
            sTestString = (String)ll.removeFirst();
            System.out.println("FAILED");
        } catch(Exception e) { iNumPassed++; System.out.println("passed"); }

//---------------------------------------------------------------------------

        System.out.println("\nTest inserting last and removing first (queue behaviour)");
        System.out.println("========================================================");

        // TEST 6 : INSERT LAST()
        try {
            iNumTests++;
            System.out.print("Testing insertLast(): ");
            ll.insertLast("abc");
            ll.insertLast("jkl");
            ll.insertLast("xyz");
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }

        // TEST 7 : PEEK LAST
        try {
            iNumTests++;
            System.out.print("Testing peekLast(): ");
            sTestString = (String)ll.peekLast();
            if (sTestString != "xyz")
                throw new IllegalArgumentException("FAILED.");
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }

        // TEST 8 : REMOVE LAST
        try {
            iNumTests++;
            System.out.print("Testing removeLast(): ");
            sTestString = (String)ll.removeLast();
            if (sTestString != "xyz")
                throw new IllegalArgumentException("FAILED.");
            sTestString = (String)ll.removeLast();
            if (sTestString != "jkl")
                throw new IllegalArgumentException("FAILED.");
            sTestString = (String)ll.removeLast();
            if (sTestString != "abc")
                throw new IllegalArgumentException("FAILED.");
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }

        // TEST 9 : IS EMPTY 2
        try {
            iNumTests++;
            System.out.print("Testing isEmpty(): ");
            sTestString = (String)ll.removeFirst();
            System.out.println("FAILED");
        } catch(Exception e) { iNumPassed++; System.out.println("passed"); }

        // TEST 10 : INSERT FIRST
        try {
            iNumTests++;
            System.out.print("Testing insertFirst(): ");
            ll.insertFirst("abc");
            ll.insertFirst("jkl");
            ll.insertFirst("xyz");
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }



        //---------------------------------------------------------------------------

        System.out.println("\nTesting with items in the middle of list");
        System.out.println("========================================================");

        // TEST 11 : REMOVE
        try
        {
            iNumTests++;
            System.out.print("Testing remove(): ");
            sTestString = (String)ll.remove("abc");
            if (sTestString != "abc")
                throw new IllegalArgumentException("FAILED.");
            sTestString = (String)ll.remove("jkl");
            if (sTestString != "jkl")
                throw new IllegalArgumentException("FAILED.");
            sTestString = (String)ll.remove("xyz");
            if (sTestString != "xyz")
                throw new IllegalArgumentException("FAILED.");
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }


       // TEST 12 : ITERATOR
        try
        {
            iNumTests++;
            ll.insertFirst("xyz");
            Iterator iter = ll.iterator();
            System.out.print("Testing iterator: ");
            if (!iter.hasNext())
                throw new IllegalArgumentException("FAILED.");
            if (iter.next() != "xyz")
                throw new IllegalArgumentException("FAILED.");
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }

//---------------------------------------------------------------------------

        // PRINT TEST SUMMARY
        System.out.print("\nNumber PASSED: " + iNumPassed + "/" + iNumTests);
        System.out.print(" -> " + (int)(double)iNumPassed/iNumTests*100 + "%\n");
//---------------------------------------------------------------------------
            

           
    }
        
}
