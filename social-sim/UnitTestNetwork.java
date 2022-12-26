// [Kade McGarraghy].[First Year].[Modified Previously submitted DSA Prac 06]

public class UnitTestNetwork
{
    public static void main (String[] args)
    {
        int numPassed = 0;
        int numTests = 0;
        DSALinkedList list;
        String testStr;
        Network graph = new Network();


        // TEST 1 - ADD PERSON

        try
        {
            System.out.print("Testing add person: ");
            numTests++;
            graph.addPerson("Crusty the Clown");
            graph.addPerson("Bart Simpson");
            if (!graph.personExists("Crusty the Clown") || !graph.personExists("Bart Simpson"))
            {
                System.out.print("FAILED");
            }
            numPassed++;
            System.out.println("passed");
        }
        catch (Exception e)
        {
            System.out.println("FAILED");
        }

        // TEST 2 - ADD FOLLOW

        try
        {
            System.out.print("Testing adding follow: ");
            numTests++;
            graph.addFollow("Bart Simpson", "Crusty the Clown");
            list = graph.retrieveFollowers("Crusty the Clown");
            if (!list.peekFirst().equals("Bart Simpson"))
            {
                System.out.print("FAILED");
            }
            numPassed++;
            System.out.println("passed");
        }
        catch (Exception e)
        {
            System.out.println("FAILED");
        }

        // TEST 3 - REMOVE FOLLOW

        try
        {

            numTests++;
            graph.removeFollow("Bart Simpson", "Crusty the Clown");
            graph.removeFollow("Bart Simpson", "Crusty the Clown");
            numPassed++;
            System.out.println("Testing removing follow: passed");
        }
        catch (Exception e)
        {
            System.out.println("Testing removing follow: FAILED");
        }

        // TEST 4 - ADD POST

        try
        {
            System.out.print("Testing adding post: ");
            numTests++;
            testStr = graph.addPost("Bart Simpson", "Don't forget to log out of your phone Bart! - Lisa", 0.9);
            if (!testStr.equals("Posted."))
            {
                System.out.print("FAILED");
            }
            numPassed++;
            System.out.println("passed");
        }
        catch (Exception e)
        {
            System.out.println("FAILED");
        }

        // TEST 5 - DISPLAY METHODS

        try
        {

            numTests++;
            graph.addPerson("Crusty the Clown The Second");
            graph.addPerson("Lisa Simpson");
            graph.addPerson("Homer Simpson");
            graph.addFollow("Homer Simpson", "Crusty the Clown The Second");
            graph.addFollow("Lisa Simpson", "Crusty the Clown The Second");
            graph.addFollow("Bart Simpson", "Crusty the Clown The Second");
            graph.addFollow("Lisa Simpson", "Homer Simpson");
            graph.addFollow("Bart Simpson", "Crusty the Clown");
            graph.addFollow("Homer Simpson", "Crusty the Clown");
            graph.addPerson("Jennifer Lopez");
            graph.addFollow("Homer Simpson", "Jennifer Lopez");
            graph.addFollow("Lisa Simpson", "Jennifer Lopez");
            graph.addFollow("Bart Simpson", "Jennifer Lopez");
            graph.removePerson("Jennifer Lopez");

            System.out.println();
            System.out.println(graph.displayNetwork());
            graph.displayPopularPeople();
            graph.displayPopularPosts();
            graph.displayRecord("Bart Simpson");
            numPassed++;
            System.out.println("Testing display methods: no exceptions");
        }
        catch (Exception e)
        {
            System.out.println("Testing display methods: FAILED");
        }


        // TEST 6 - PERSON EXISTS

        try
        {
            System.out.print("Testing person exists: ");
            numTests++;
            if (graph.personExists("Jennifer Lopez"))
            {
                System.out.println("FAILED");
            }
            if (!graph.personExists("Bart Simpson"))
            {
                System.out.println("FAILED");
            }
            numPassed++;
            System.out.println("passed");
        }
        catch (Exception e)
        {
            System.out.println("FAILED");
        }


        // TEST 7- FILE READING

        try
        {
            System.out.print("Testing file io ");
            numTests++;
            FileIO.readNetworkFile("toyStory.txt", graph);
            FileIO.readEventFile("eventFileSmaller.txt", graph);
            FileIO.writeNetworkToFile("Test.txt", graph);
            numPassed++;
            System.out.println("passed");
        }
        catch (Exception e)
        {
            System.out.println("FAILED");
        }



        // PRINT TEST SUMMARY
        System.out.print("\nNumber PASSED: " + numPassed + "/" + numTests);
        System.out.print(" -> " + (int)(double)numPassed/numTests*100 + "%\n");
    }
}

