import java.util.*;

public class UserInterface
{
    public static void interactiveMenu()
    {
        Scanner sc = new Scanner(System.in);
        int choice, timestepCount = 0;
        Network network = new Network();
        String menuChoices =
                "\n\t1. Load network" +
                        "\n\t2. Set probabilities" +
                        "\n\t3. Perform node operations" +
                        "\n\t4. Perform follow operations" +
                        "\n\t5. Make new posts" +
                        "\n\t6. Display network" +
                        "\n\t7. Display statistics" +
                        "\n\t8. Run update" +
                        "\n\t9. Save network" +
                        "\n\t10. Exit";
        String error = "You must enter a number between 1 and 10 to select your choice.";

        System.out.println("Welcome to SocialSim's Interactive Testing Mode!");
        do
        {
            try
            {
                System.out.println("\nPlease select the number that corresponds with the option you would like to choose:");
                System.out.println(menuChoices);
                choice = sc.nextInt();

                switch (choice)
                {
                    case 1:
                    {
                        network = loadNetwork(network);

                    }
                    break;

                    case 2:
                    {
                        network = setProbabilities(network);
                    }
                    break;

                    case 3:
                    {
                        network = nodeOperations(network);
                    }
                    break;

                    case 4:
                    {
                        network = followOperations(network);
                    }
                    break;

                    case 5:
                    {
                        network = makeNewPosts(network);
                    }
                    break;

                    case 6:
                    {
                        network = displayNetwork(network);
                    }
                    break;

                    case 7:
                    {
                        network = displayStatistics(network);
                    }
                    break;

                    case 8:
                    {
                        timestepCount++;
                        network = runUpdate(network, timestepCount);
                    }
                    break;

                    case 9:
                    {
                        saveNetwork(network);
                    }
                    break;

                    case 10:
                    {
                        System.out.println("Now exiting. Goodbye!");
                    }

                    default:
                    {
                        System.out.println(error);
                    }
                }
            } catch (InputMismatchException e)
            {
                sc.nextLine(); // clears input buffer
                System.out.println("You must enter an integer number between 1 and 10 to select your choice.");
                choice = 11;
            }
        }
        while (choice != 10);

    }

    public static Network loadNetwork(Network network)
    {
        String fileName;
        String prompt;
        prompt = "Please enter the name of your network file.";
        fileName = stringInput(prompt);
        FileIO.readNetworkFile(fileName, network);
        return network;
    }

    public static Network setProbabilities(Network network)
    {
        Scanner sc = new Scanner(System.in);
        int choice;
        String menuChoices = "\n\t1. Set like probability" +
                "\n\t2. Set follow probability" +
                "\n\t3. Return to main menu";
        String prompt, error = "ERROR: Please enter a real number between 0 and 1";
        double userInput, min = 0.0, max = 1.0;


        do
        {
            System.out.println("Select the number that corresponds with the option you would like to choose.");
            try
            {
                System.out.println(menuChoices);
                choice = sc.nextInt();

                switch (choice)
                {
                    case 1:
                    {
                        prompt = "Enter the probability of liking a post at each timestep.";
                        userInput = realInput(prompt, error, min, max);
                        setLikeProbability(network, userInput);
                        System.out.println("Like probability set to " + userInput + ".\n");
                    }
                    break;

                    case 2:
                    {
                        prompt = "Enter the probability of a liker following the original poster at each timestep.";
                        userInput = realInput(prompt, error, min, max);
                        setFollowProbability(network, userInput);
                        System.out.println("Follow probability set to " + userInput + ".\n");
                    }
                    break;

                    case 3:
                    {
                    }
                    break;

                    default:
                    {
                        System.out.println("ERROR: You must enter number between 1 and 3 to make your choice.");
                    }
                }
            } catch (InputMismatchException e)
            {
                System.out.println("ERROR: You must enter an integer number to make your choice.");
                sc.nextLine(); // clears input buffer
                choice = 0; //
            }
        }
        while (choice != 3);
        return network;

    }

    public static Network nodeOperations(Network network)
    {
        Scanner sc = new Scanner(System.in);
        int choice;
        String menuChoices = "\n\t1. Find person" +
                "\n\t2. Add person" +
                "\n\t3. Remove person" +
                "\n\t4. Return to main menu";
        String prompt;
        String userInput;
        Object removedPerson;


        do
        {
            try
            {
                System.out.println("Select the number that corresponds with the option you would like to choose.");
                System.out.println(menuChoices);
                choice = sc.nextInt();
                sc.nextLine();

                switch (choice)
                {
                    case 1:
                    {
                        prompt = "Enter the name of the person you would like to find.";
                        userInput = stringInput(prompt);
                        if (network.personExists(userInput))
                        {
                            System.out.println(userInput + " exists on the network.");
                        } else
                        {
                            System.out.println(userInput + " does not exist on the network.");
                        }
                    }
                    break;

                    case 2:
                    {
                        prompt = "Enter the name of the person you would like to add to the network.";
                        userInput = stringInput(prompt);
                        network.addPerson(userInput);
                        System.out.println(userInput + " has been added to the network.\n");
                    }
                    break;

                    case 3:
                    {
                        prompt = "Enter the name of the person you would like to remove from the network.";
                        userInput = stringInput(prompt);
                        removedPerson = network.removePerson(userInput);
                        if (removedPerson != null)
                        {
                            System.out.println(userInput + " has been removed from the network.\n");
                        }
                    }

                    break;

                    case 4:
                    {
                    }
                    break;

                    default:
                    {
                        System.out.println("ERROR: You must enter number between 1 and 4 to make your choice.");
                    }
                }
            } catch (InputMismatchException e)
            {
                System.out.println("ERROR: You must enter an integer number to make your choice.");
                sc.nextLine(); // clears input buffer
                choice = 0; //
            }
        }
        while (choice != 4);
        return network;
    }

    public static Network followOperations(Network network)
    {
        Scanner sc = new Scanner(System.in);
        int choice;
        String menuChoices = "\n\t1. Add follow" +
                "\n\t2. Remove follow" +
                "\n\t3. Return to main menu";
        String prompt, followerInput, followeeInput, successMessage;


        do
        {
            try
            {
                System.out.println("Select the number that corresponds with the option you would like to choose.");
                System.out.println(menuChoices);
                choice = sc.nextInt();
                sc.nextLine();

                switch (choice)
                {
                    case 1:
                    {
                        prompt = "Enter the name of the person who will be the follower.";
                        followerInput = stringInput(prompt);
                        prompt = "Enter the name of the person who will be followed.";
                        followeeInput = stringInput(prompt);
                        successMessage = network.addFollow(followerInput, followeeInput);
                        if (successMessage != null)
                        {
                            System.out.println(successMessage);
                        }
                    }
                    break;

                    case 2:
                    {
                        prompt = "Enter the name of the person who will unfollow someone.";
                        followerInput = stringInput(prompt);
                        prompt = "Enter the name of the person who will be unfollowed.";
                        followeeInput = stringInput(prompt);
                        network.removeFollow(followerInput, followeeInput);
                    }
                    break;

                    case 3:
                    {
                    }
                    break;

                    default:
                    {
                        System.out.println("ERROR: You must enter number between 1 and 3 to make your choice.");
                    }
                }
            } catch (InputMismatchException e)
            {
                System.out.println("ERROR: You must enter an integer number to make your choice.");
                sc.nextLine(); // clears input buffer
                choice = 0; //
            }
        }
        while (choice != 3);
        return network;
    }

    public static Network makeNewPosts(Network network)
    {
        Scanner sc = new Scanner(System.in);
        int choice;
        String menuChoices = "\n\t1. Set poster" +
                "\n\t2. Create new post" +
                "\n\t3. Return to main menu";
        String prompt, error;
        String posterInput = "None", postInput;
        double likeProb = network.getProbLike();
        double max, min = 0, clickbaitInput;

        max = (1 / likeProb);

        do
        {
            try
            {
                System.out.println("Current poster -> " + posterInput + "\n");
                System.out.println("Select the number that corresponds with the option you would like to choose.");
                System.out.println(menuChoices);
                choice = sc.nextInt();
                sc.nextLine();

                switch (choice)
                {
                    case 1:
                    {
                        prompt = "Enter the name of the person to create new posts from.";
                        posterInput = stringInput(prompt);
                        if (!network.personExists(posterInput))
                        {
                            System.out.println("Can't post from " + posterInput + " as they don't exist in the network.\n");
                            posterInput = "None";
                        }
                    }
                    break;

                    case 2:
                    {
                        if (posterInput.equals("None"))
                        {
                            System.out.println("Please select a poster before making a post.\n");
                        } else
                        {
                            prompt = "Enter the message you would like to post.";
                            postInput = stringInput(prompt);
                            prompt = "Enter the clickbait factor for the post (1 is the default)";

                            error = ("You must enter a number between " + min + " and " + max + ".");
                            clickbaitInput = realInput(prompt, error, min, max);
                            network.addPost(posterInput, postInput, clickbaitInput);
                        }
                    }
                    break;

                    case 3:
                    {
                    }
                    break;

                    default:
                    {
                        System.out.println("ERROR: You must enter number between 1 and 3 to make your choice.");
                    }
                }

            } catch (InputMismatchException e)
            {
                System.out.println("ERROR: You must enter an integer number to make your choice.");
                sc.nextLine(); // clears input buffer
                choice = 0; //
            }
        }
        while (choice != 3);
        return network;
    }

    public static double realInput(String prompt, String error, double min, double max)
    {
        double userInput;
        String str = prompt;
        Scanner sc = new Scanner(System.in);

        do
        {
            try
            {
                System.out.println(str);
                str = error + "\n" + prompt;
                userInput = sc.nextDouble();
            } catch (InputMismatchException e)
            {
                sc.nextLine();
                userInput = min - 1.0;
            }
        }
        while ((userInput < min) && (userInput > max));
        return userInput;
    }

    public static String stringInput(String prompt)
    {
        String userInput;
        String str = prompt;
        Scanner sc = new Scanner(System.in);

        System.out.println(prompt);
        userInput = sc.nextLine();
        return userInput;
    }

    public static Network setLikeProbability(Network network, double likeProbability)
    {
        network.setProbLike(likeProbability);
        return network;
    }

    public static Network setFollowProbability(Network network, double followProbability)
    {
        network.setProbFollow(followProbability);
        return network;
    }

    public static Network displayNetwork(Network network)
    {
        System.out.println(network.displayNetwork());
        return network;
    }

    public static Network displayStatistics(Network network)
    {
        Scanner sc = new Scanner(System.in);
        int choice;
        String menuChoices = "\n\t1. View posts in order of popularity" +
                "\n\t2. View people in order of popularity" +
                "\n\t3. View a person's individual record" +
                "\n\t4. Return to main menu";
        String prompt, name;


        do
        {
            try
            {
                System.out.println("Select the number that corresponds with the option you would like to choose.");
                System.out.println(menuChoices);
                choice = sc.nextInt();
                sc.nextLine();

                switch (choice)
                {
                    case 1:
                    {
                        network.displayPopularPosts();
                    }
                    break;

                    case 2:
                    {
                        network.displayPopularPeople();
                    }
                    break;

                    case 3:
                    {
                        prompt = "Enter the name of the person who's record you want to see.";
                        name = stringInput(prompt);
                        network.displayRecord(name);
                    }
                    break;

                    case 4:
                    {
                    }

                    default:
                    {
                        System.out.println("ERROR: You must enter number between 1 and 4 to make your choice.");
                    }
                }

            } catch (InputMismatchException e)
            {
                System.out.println("ERROR: You must enter an integer number to make your choice.");
                sc.nextLine(); // clears input buffer
                choice = 0; //
            }
        }
        while (choice != 4);
        return network;
    }

    public static void saveNetwork(Network network)
    {
        if (network != null)
        {
            String fileName;
            String prompt;
            prompt = "Please enter the name of your network file.";
            fileName = stringInput(prompt);
            FileIO.writeNetworkToFile(fileName, network);
        }
        else
        {
            System.out.println("Can't save an empty network.");
        }
    }

    public static Network runUpdate(Network network, int count)
    {
        network.runTimestep();
        System.out.println("Timestep " + count + ":");
        displayNetwork(network);
        return network;
    }
}
