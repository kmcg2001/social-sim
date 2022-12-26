import java.util.*;
public class SimulationMode
{
    public static void runSimulation(String networkFile, String eventFile, double probLike, double probFollow)
    {
        Scanner sc = new Scanner(System.in);
        int nTimesteps = 0;
        Network network = new Network();
        network = FileIO.readNetworkFile(networkFile, network);
        network.setProbLike(probLike);
        network.setProbFollow(probFollow);
        network = FileIO.readEventFile(eventFile, network);

        try
        {
            System.out.println("Enter the number of timesteps you would like to perform.");
            nTimesteps = sc.nextInt();
        }
        catch (InputMismatchException e)
        {
            sc.nextLine();
            System.out.println("Please enter an integer number for the number of timesteps you want");
        }

        FileIO.writeTimestepToFile(nTimesteps, network);



    }
}
