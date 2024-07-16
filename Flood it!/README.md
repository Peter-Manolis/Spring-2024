FloodIt! Student Support Code

Revision: Fall 2023

Question 1: F(n) = n^2 fits the time complexity for the graph shown when using flood().

Question 2: The time complexity for this flood function is O(n^2) as it is a for loop inside a
for loop, each for loop has a time complexity of O(n), so one inside one another is n * n which is equal to n^2.

Question 3: Yes the analysis does match up with what you see in the graph. The graph shows a parabola that increases
at the speed of n^2 and our analysis shows that the time complexity for the flood function is O(n^2).

Question 4: We used a linked list,which was the flooded_list, and a 2d array, or tiles. The time can be better and equal
to O(n) if we used a hashmap and separated the double for loop into two separate singular for loops.

Question 5: The time complexity of the new flood function, flood1() is O(n).