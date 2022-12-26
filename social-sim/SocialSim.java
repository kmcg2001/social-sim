public class SocialSim
{
    public static void main(String[] args)
    {
        double probLike, probFollow;

        if (args.length < 1)
        {
            showUsage();
        }
        else
        {
            if (args[0].equals("-i"))
            {
                UserInterface.interactiveMenu();
            }
            else if (args[0].equals("-s"))
            {
                if (args.length != 5)
                {
                    System.out.println("Usage: java SocialSim -s network_filename event_filename " +
                                       "like_probability following_probability");
                }
                else
                {
                    probLike = Double.parseDouble(args[3]);
                    probFollow = Double.parseDouble(args[4]);
                    SimulationMode.runSimulation(args[1], args[2], probLike, probFollow);
                }
            }
            else
            {
                showUsage();
            }
        }
    }

    public static void showUsage()
    {
        System.out.println("Usage: java SocialSim -[MODE]");
        System.out.println("\nAvailable modes include:");
        System.out.println("\t-i: Interactive Testing Mode");
        System.out.println("\t-s: Simulation Mode");
    }

}