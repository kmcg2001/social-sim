// [Kade McGarraghy].[First Year].[Modified Previously submitted DSA Prac 05]

import java.io.*;

public class FileIO
{
    public static Network readNetworkFile(String fileName, Network network)
    {
        FileInputStream fileStrm = null;
        InputStreamReader rdr;
        BufferedReader bufRdr;
        int lineNum;
        String line, newName, followeeName, followerName;
        String[] tokens;
        try
        {
            fileStrm = new FileInputStream(fileName);
            rdr = new InputStreamReader(fileStrm);
            bufRdr = new BufferedReader(rdr);

            lineNum = 0;
            line = bufRdr.readLine();
            while (line != null)
            {
                lineNum++;
                tokens = line.split(":");
                if (tokens.length == 1)
                {
                    newName = tokens[0];
                    if (!newName.equals(""))
                    {
                        network.addPerson(newName);
                    }
                }
                else
                {
                    followeeName = tokens[0];
                    followerName = tokens[1];
                    network.addFollow(followerName, followeeName);

                }
                line = bufRdr.readLine();
            }
            fileStrm.close();
            System.out.println("\nYour network has been loaded.");
        }

        catch (IOException e)
        {
            if (fileStrm != null)
            {
                try
                {
                    fileStrm.close();
                }
                catch (IOException e2)
                {
                }
            }
            System.out.println("Error in file processing: " + e.getMessage());
        }
        return network;
    }

    public static Network readEventFile(String fileName, Network network)
    {
        FileInputStream fileStrm = null;
        InputStreamReader rdr;
        BufferedReader bufRdr;
        int lineNum;
        String line, newName, poster, post, followeeName, followerName, unfolloweeName, unfollowerName;
        String[] tokens;
        char choice;
        int clickbaitFactor;
        try
        {
            fileStrm = new FileInputStream(fileName);
            rdr = new InputStreamReader(fileStrm);
            bufRdr = new BufferedReader(rdr);

            lineNum = 0;
            line = bufRdr.readLine();
            while (line != null)
            {
                lineNum++;
                tokens = line.split(":");
                choice = (tokens[0].toUpperCase()).charAt(0);
                switch (choice)
                {
                    case 'A':
                    {
                        newName = tokens[1];
                        if (!newName.equals(""))
                        {
                            network.addPerson(newName);
                        }
                    }
                    break;

                    case 'P':
                    {
                        poster = tokens[1];
                        post = tokens[2];

                        if (tokens.length == 4)
                        {
                            clickbaitFactor = Integer.parseInt(tokens[3]);
                        }
                        else
                        {
                            clickbaitFactor = 0;
                        }

                        if (network.personExists(poster))
                        {
                            network.addPost(poster, post, clickbaitFactor);
                        }
                        else
                        {
                            System.out.println("Could not add post for '" + poster + "' because they don't exist in the network.");
                        }
                    }
                    break;

                    case 'F':
                    {
                        followeeName = tokens[1];
                        followerName = tokens[2];
                        network.addFollow(followerName, followeeName);
                    }
                    break;

                    case 'U':
                    {
                        unfolloweeName = tokens[1];
                        unfollowerName = tokens[2];
                        network.removeFollow(unfollowerName, unfolloweeName);
                    }
                    break;

                    default:
                    {
                        System.out.println("ERROR: Unknown operation entered.");
                    }

                }
                line = bufRdr.readLine();
            }
            fileStrm.close();
        }

        catch (IOException e)
        {
            if (fileStrm != null)
            {
                try
                {
                    fileStrm.close();
                }
                catch (IOException e2)
                {
                }
            }
            System.out.println("ERROR in file processing: " + e.getMessage());
        }
        return network;
    }

    public static void writeNetworkToFile(String fileName, Network network) // DSAhash prac
    {
        FileOutputStream fileStrm = null;
        PrintWriter pw;

        try
        {
            fileStrm = new FileOutputStream(fileName);
            pw = new PrintWriter(fileStrm);
            pw.print(network.displayPeopleToFile());
            pw.print(network.displayFollowingToFile());
            pw.close();
        }
        catch (IOException e)
        {
            if (fileStrm != null)
            {
                try
                {
                    fileStrm.close();
                }
                catch (IOException ex2)
                {

                }
            }
            System.out.println("ERROR in writing to file: " + e.getMessage());
        }
    }

    public static void writeTimestepToFile(int nTimesteps, Network network)
    {
        FileOutputStream fileStrm = null;
        PrintWriter pw;
        String fileName = "Simulation.txt";

        try
        {
            fileStrm = new FileOutputStream(fileName);
            pw = new PrintWriter(fileStrm);
            for (int i = 0; i < nTimesteps; i++)
            {
                pw.println("Time step: " + i);
                network.runTimestep();
                pw.print(network.displayNetwork());
                pw.println();
            }

            pw.close();
        }
        catch (IOException e)
        {
            if (fileStrm != null)
            {
                try
                {
                    fileStrm.close();
                }
                catch (IOException ex2)
                {

                }
            }
            System.out.println("ERROR in writing to file: " + e.getMessage());
        }
    }
}
