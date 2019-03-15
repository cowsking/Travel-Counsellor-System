```
CITYRECOMENDATION (N, L, S, P)
Input: N: Number of cities N selected. L: Location of all the cities. S: sorted list of cities according to user preference. P: Starting point of the route
	Output: R: route path
	city  Select first N*2 cities from array S
	present  P
	Add present to R
4	while city ≠ ∅
5			do
6			Remove the closest city c with present
			Add c to R
7	Return the set R


```
