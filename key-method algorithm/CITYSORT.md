```
CITYSORT(U, C)
Input: Preference of the user U which stores an unidimensional array, and C[i][j] array stores the degree of factors for all the cities in a country.
	Output: Sorted list S of cities
1 	difference[] = 0
2	for i  1 to |C| do
3			sum  0
4			for j  1 to |C[1]| do
5				sum  sum + (U[j] – C[i][j])2
6			sum  sum1/2
7			/* L2 norm of the difference between user preference
8			and the city */
9			difference[i]  sum

10	procedure MERGESORT(difference[0..n-1])
11 	if n > 1 then begin
12 		copy difference[0..n/2-1] to B[0..n/2-1]
13    	copy difference[n/2..n-1] to C[0..n/2-1]
14    	MERGESORT(B[0..n/2-1])
15    	MERGESORT(C[0..n/2-1])
16    	MERGE(B, C, difference)
17 end procedure

18	procedure MERGE(B[0..p-1], C[0..q-1], A[0..p+q-1])
19		Set i  0, j  0, k  0
20		while i < p and j < q do
21		begin
22			if B[i] <= C[j] then set A[k]=B[i] and increase i
23			else set A[k]  C[j] and increase j
24		k  k+1
25		end
26		if I  p then copy C[j..q-1] to A[k..p+q-1]
27		else copy B[i..p-1] to A[k..p+q-1]

```
