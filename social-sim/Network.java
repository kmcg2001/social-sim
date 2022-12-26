// [Kade McGarraghy].[First Year].[Modified Previously submitted DSA Prac 06]

import java.util.*;

public class Network
{
    private class Person
    {
        private String name;
        private DSALinkedList following;
        private int followingCount;

        public Person(String inName)
        {

            name = inName;
            following = new DSALinkedList();
            followingCount = 0;
        }

        public String getName()
        {
            return name;
        }

        public DSALinkedList getFollowing()
        {
            return following;
        }


        public int getFollowingCount()
        {
            return followingCount;
        }

        public void addFollow(String name)
        {
            following.insertLast(name);
            followingCount++;
        }

        public void removeFollow(Person followee)
        {
            following.remove(followee.getName());
            followingCount--;
        }

        public boolean isFollowing(Person person)
        {
            Iterator followingIter = following.iterator();
            boolean follows = false;
            String temp;
            while (followingIter.hasNext())
            {
                temp = (String) followingIter.next();
                if (temp.equals(person.name))
                {
                    follows = true;
                }
            }
            return follows;
        }

        public String followingToString()
        {
            Iterator followingIter = following.iterator();
            String str = "";
            String finalStr = "";
            String temp;
            while (followingIter.hasNext())
            {
                temp = (String) followingIter.next();
                if (temp != null)
                {
                    if (followingIter.hasNext())
                    {
                        str = str + temp + ", ";
                    }
                    else
                    {
                        str = str + temp;
                    }
                }
            }
            if (str.equals(""))
            {
                finalStr = (name + " follows -> NONE");
            }
            else
            {
                finalStr = (name + " follows -> " + str);
            }
            return (finalStr);
        }


        public String followingToStringFile()
        {
            Iterator followingIter = following.iterator();
            String str = "";
            String temp;
            while (followingIter.hasNext())
            {
                temp = (String) followingIter.next();
                if (temp != null)
                {
                    if (followingIter.hasNext())
                    {
                        str = str + temp + ":" + name + "\n";
                    }
                    else
                    {
                        str = str + temp + ":" + name;
                    }
                }
            }
            return (str);
        }
    }

    private class Post
    {

        private String poster;
        private String text;
        private double clickbaitFactor;
        private DSALinkedList likers;
        private int likeCount;
        private int nTimesteps;

        public Post(String inPoster, String inText)
        {
            poster = inPoster;
            text = inText;
            clickbaitFactor = 1.0;
            likers = new DSALinkedList();
            likeCount = 0;
            nTimesteps = 0;
        }

        public void setClickbaitFactor(double inClickbaitFactor)
        {
            clickbaitFactor = inClickbaitFactor;
        }


        public String getPoster()
        {
            return poster;
        }

        public String getText()
        {
            return text;
        }


        public int getLikeCount()
        {
            return likeCount;
        }

        public void addLike(String name)
        {
            likers.insertLast(name);
            likeCount++;
        }

        public boolean likesPost(String name)
        {
            String liker;
            boolean likes = false;
            Iterator likersIter = likers.iterator();
            while (likersIter.hasNext())
            {
                liker = (String) likersIter.next();
                if ((liker.equals(name)) || poster.equals(name))
                {
                    likes = true;
                }
            }
            return likes;
        }

        public void runTimestep()
        {
            String outputStr;
            DSALinkedList currentFollowers;


            String name, liker;
            Person person, followee;
            boolean likes = false;

            if (nTimesteps == 0)
            {
                currentFollowers = retrieveFollowers(poster);
                Iterator followersIter = currentFollowers.iterator();
                while (followersIter.hasNext())
                {
                    name = (String) followersIter.next();
                    double d = Math.random();
                    if (d <= (probLike * clickbaitFactor))
                    {
                        addLike(name);
                    }
                }

            }
            else
            {
                Iterator likersIter = likers.iterator();
                while (likersIter.hasNext())
                {
                    liker = (String) likersIter.next();
                    currentFollowers = retrieveFollowers(liker);
                    Iterator followersIter = currentFollowers.iterator();
                    while (followersIter.hasNext())
                    {
                        name = (String) followersIter.next();
                        double d = Math.random();
                        if (d <= (probLike * clickbaitFactor))
                        {
                            if (!likesPost(name))
                            {
                                addLike(name);
                                System.out.println(name + " liked " + poster + "'s post");
                            }
                        }
                    }
                }

                likersIter = likers.iterator();
                while (likersIter.hasNext())
                {
                    liker = (String) likersIter.next();

                    person = getPerson(liker);
                    followee = getPerson(poster);
                    double d2 = Math.random();
                    if (d2 <= probFollow)
                    {
                        if (!person.isFollowing(followee))
                        {
                            person.addFollow(poster);
                            System.out.println(liker + " is now following " + poster);
                        }
                    }

                }

            }
            nTimesteps++;
        }

        public String toString()
        {
            String outputStr;
            outputStr = poster + ": " + text;
            return outputStr;
        }
    }

    private DSALinkedList people;
    private DSALinkedList posts;
    private int peopleCount;
    private int postCount;
    private double probLike;
    private double probFollow;

    public Network()
    {
        people = new DSALinkedList();
        posts = new DSALinkedList();
        peopleCount = 0;
        postCount = 0;
        probLike = 0;
        probFollow = 0;
    }

    public void setProbLike(double inProbLike)
    {
        probLike = inProbLike;
    }

    public void setProbFollow(double inProbFollow)
    {
        probFollow = inProbFollow;
    }

    public double getProbLike()
    {
        return probLike;
    }

    public void addPerson(String name)
    {
        if ((people.isEmpty()) || (!personExists(name)))
        {
            Person newPerson = new Person(name);
            people.insertFirst(newPerson);
            peopleCount++;
        }
    }

    public String addFollow(String followerName, String followeeName)
    {
        String successMessage = null;
        Person follower = getPerson(followerName);
        Person followee = getPerson(followeeName);

        if ((follower != null) && (followee != null))
        {
            if (!isFollowing(follower.getName(), followee.getName()))
            {
                follower.addFollow(followeeName);
                successMessage = (followerName + " now follows " + followeeName + ".");
            }
            else
            {
                System.out.println(followerName + " already follows " + followeeName + ".");
            }
        }
        else
        {
            System.out.println("\nError adding follow between '" + followerName + "' and '" + followeeName + "'.");
            System.out.print("Make sure you have given the correct name for both users.\n");
        }
        return successMessage;
    }

    public String addPost(String name, String text, double clickbaitFactor)
    {
        Person poster;
        poster = getPerson(name);
        String completionStr;

        if (poster != null)
        {
            Post newPost = new Post(name, text);
            if (clickbaitFactor != 1.0)
            {
                newPost.setClickbaitFactor(clickbaitFactor);
            }
            posts.insertFirst(newPost);
            postCount++;
            completionStr = "Posted.";
        }
        else
        {
            completionStr = "Cannot add post as " + name + " is not in the network.";
        }
        return completionStr;
    }

    public Object removePerson(String name)
    {
        Person person = getPerson(name);
        if (person == null)
        {
            System.out.println("ERROR: Could not find " + name + " in the network.");
        }
        else
        {
            person = (Person) people.remove(person); // removes person from list of people in network
            Iterator iter = people.iterator();
            Person temp;
            while (iter.hasNext())
            {
                temp = (Person) iter.next();
                if (temp.isFollowing(person))
                {
                    temp.removeFollow(person); // everyone now unfollows the removed person.
                }
            }
            peopleCount--;
        }
        return person;
    }

    public Object removeFollow(String unfollowerName, String unfolloweeName)
    {
        Person unfollower = getPerson(unfollowerName);
        Person unfollowee = getPerson(unfolloweeName);
        if ((unfollower != null) && (unfollowee != null))
        {
            if (unfollower.isFollowing(unfollowee))
            {
                unfollower.removeFollow(unfollowee);
            }
            else
            {
                System.out.println("'" + unfollowerName + "' is not following '" + unfolloweeName + "'.");
            }

        }
        else
        {
            System.out.println("\nError removing follow between '" + unfollowerName + "' and '" + unfolloweeName + "'.");
            System.out.print("Make sure you have given the correct name for both users.\n");
        }
        return unfollowee;
    }

    public boolean personExists(String name)
    {
        boolean exists = false;
        Iterator iter = people.iterator();
        Person temp;
        while (iter.hasNext())
        {
            temp = (Person) iter.next();
            if (temp.getName().equals(name))
            {
                exists = true;
            }
        }
        return exists;
    }

    public String displayNetwork()
    {
        Iterator peopleIter = people.iterator();
        String outputStr = "";
        Person person;
        while (peopleIter.hasNext())
        {
            person = (Person) peopleIter.next();
            outputStr = outputStr + person.followingToString() + "\n";
        }
        return outputStr;
    }


    public String displayPeopleToFile()
    {
        String outputStr = "";
        Iterator peopleIter = people.iterator();
        Person person;
        while (peopleIter.hasNext())
        {
            person = (Person) peopleIter.next();
            outputStr = outputStr + person.getName() + "\n";
        }
        return outputStr;
    }

    public String displayFollowingToFile()
    {
        String outputStr = "";
        Iterator peopleIter = people.iterator();
        Person person;

        while (peopleIter.hasNext())
        {
            person = (Person) peopleIter.next();

            if (peopleIter.hasNext())
            {
                outputStr = outputStr + person.followingToStringFile() + "\n";
            }
            else
            {
                outputStr = outputStr + person.followingToStringFile();
            }
        }
        return outputStr;
    }

    public void displayPopularPosts()
    {
        Iterator postIter = posts.iterator();
        Post temp;
        DSAHeapEntry[] entryArray = new DSAHeapEntry[postCount];
        int count = 0;
        DSAHeap heap = new DSAHeap(postCount);


        while (postIter.hasNext())
        {
            temp = (Post) postIter.next();
            DSAHeapEntry entry = new DSAHeapEntry(temp.getLikeCount(), temp);
            entryArray[count] = entry;
            count++;
        }

        heap.heapSort(entryArray);
        for (int i = (postCount - 1); i >= 0; i--)
        {
            temp = (Post) entryArray[i].getValue();
            System.out.println(temp.getPoster() + ": " + temp.getText() + " (" + temp.getLikeCount() + " likes)");
        }
        System.out.println();
    }

    public void displayPopularPeople()
    {
        Iterator peopleIter = people.iterator();
        Person temp;
        DSAHeapEntry[] entryArray = new DSAHeapEntry[peopleCount];
        int count = 0, tempFollowerCount = 0;
        DSAHeap heap = new DSAHeap(peopleCount);

        while (peopleIter.hasNext())
        {
            temp = (Person) peopleIter.next();
            tempFollowerCount = retrieveFollowerCount(temp);
            DSAHeapEntry entry = new DSAHeapEntry(tempFollowerCount, temp);
            entryArray[count] = entry;
            count++;
        }

        heap.heapSort(entryArray);

        for (int i = (peopleCount - 1); i >= 0; i--)
        {
            temp = (Person) heap.getHeap()[i].getValue();
            System.out.println(temp.getName() + ": " + retrieveFollowerCount(temp) + " followers");
        }
        System.out.println();
    }

    public void displayRecord(String name)
    {
        Person person;
        person = getPerson(name);

        if (person != null)
        {
            System.out.println("Name: " + name +
                    "\nFollowing: " + person.getFollowingCount() +
                    "\nFollowers: " + retrieveFollowerCount(person) +
                    "\nPosts: " + retrievePostCount(person));
        }
        else
        {
            System.out.println(name + " does not exist in the network.");
        }
        System.out.println();
    }

    public int retrieveFollowerCount(Person person)
    {
        Iterator peopleIter = people.iterator();
        Person temp;
        int count;
        count = 0;
        while (peopleIter.hasNext())
        {
            temp = (Person) peopleIter.next();
            if (temp.isFollowing(person))
            {
                count++;
            }
        }
        return count;
    }

    public int retrievePostCount(Person person)
    {
        Iterator postIter = posts.iterator();
        Post post;
        int count;
        count = 0;
        while (postIter.hasNext())
        {
            post = (Post) postIter.next();
            if (post.getPoster().equals(person.getName()))
            {
                count++;
            }
        }
        return count;
    }

    public DSALinkedList retrieveFollowers(String name)
    {
        DSALinkedList personsFollowers = new DSALinkedList();
        Iterator peopleIter = people.iterator();
        Person temp;
        Person person = getPerson(name);
        while (peopleIter.hasNext())
        {
            temp = (Person) peopleIter.next();
            if (temp.isFollowing(person))
            {
                personsFollowers.insertLast(temp.getName());
            }
        }
        return personsFollowers;
    }

    public void runTimestep()
    {
        Iterator postsIter = posts.iterator();
        Post post;
        while (postsIter.hasNext())
        {
            post = (Post) postsIter.next();
            post.runTimestep();
        }
    }

    public Person getPerson(String name)
    {
        Iterator iter = people.iterator();
        Person temp, foundPerson = null;
        while (iter.hasNext())
        {
            temp = (Person) iter.next();
            if (temp.getName().equals(name))
            {
                foundPerson = temp;
            }
        }
        return foundPerson;
    }

    private DSALinkedList getFollowing(String name)
    {
        DSALinkedList list = null;
        Person person;
        person = getPerson(name);
        if (person != null)
        {
            list = person.getFollowing();
        }
        return list;
    }


    private boolean isFollowing(String name1, String name2)
    {
        DSALinkedList list;
        String temp;
        boolean following = false;
        if ((personExists(name1)) && (personExists(name2)))
        {
            list = getFollowing(name1);
            Iterator followingIter = list.iterator();
            while (followingIter.hasNext())
            {
                temp = (String) followingIter.next();
                if (temp.equals(name2))
                {
                    following = true;
                }
            }
        }
        return following;
    }
}


