This project involved creating an AI path planning robot using dynamic programming. The goal of the robot is to detect and pick up boxes in a warehouse. The boxes are not plotted in the images, but wherever there is a turn, the robot approached and picked up a box.

The boxes can be inbetween grid cells anywhere at all in 'continuous space'. NOT just limited to discrete movement

The attached images are the AI moving about it's current world.
'results.txt' show the cost of movement compared to the minimum cost of movement for 10 trial runs.

The AI performs about about 90% of minimum cost in each world. This could be imporved by implementing diagnal movements, but that greatly increases risk of collision to obstacles.
