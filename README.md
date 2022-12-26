# social-sim

This program simulates a social network in the form of a graph. The graph’s nodes represent the people using the network, and the directional edges connecting these nodes represent follows. The graph has been constructed using custom built data structures as an instructive academic exercise.

Users' posts can propagate through the network through other users liking them. This propagation will occur in a 'timestep'. A timestep is just a period of time where the followers of a post’s current likers see the post and thus have a chance of liking it if they haven’t already, and additionally have a chance of following the original poster if they haven’t already. As each timestep is performed, you can start to visualise the gradual spread of information in the network.


## Usage
The program has two modes – interactive mode and simulation mode. The user can select their desired mode by using command line arguments when running the program. 

Interactive mode allows the user to load a network file, change event probabilities, perform node and edge operations, display the graph and its statistics as well as saving the network in a form of a network file.

``java SocialSim -i``


Simulation mode allows the user to enter a network file, event file and event probabilities via command line arguments and then executes the user’s requested number of timesteps.

``java SocialSim -s [network_file_name] [event_file_name] [likeProbability] [followProbability]``

## TODO
* Improve efficiency of timestep algorithm. Currently O(N^3) on timesteps subsequent to the first, which is O(N).
