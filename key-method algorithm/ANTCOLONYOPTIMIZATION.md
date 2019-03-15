```
ANTCOLONYOPTIMIZATION(S, E, G, M)
Input: Location of start city S, location of end city E, G(V, E) of all the cities selected and Set M of ants.
	Output: Path route P of the result.
1	for i  1 to |C| do
2			distmat[i][j]  distance between city i and city j
3	procedure SETINITINFORMATION			
4		for ∀k ∈ M do
5				Let rk1 be the starting city for ant k
6				Jk(rk1)  V – {rk1}
7				/* the set to be visited for ant k in city r */
8				rk  rk1
9				/* city the ant k located in */
10	end procedure

11	procedure ROUTES
12		for i  1 to |V| -1 do
13			for ∀k ∈ M do
14				Select next city sk from the formula mentioned
15				add edge(rk, sk) to Tourk
16 end procedure

17	procedure UPDATE
18		Compute Lk ∀k ∈ M
19		/* the length of tour of ant k */
20		Update τr,s  from the formula mentioned
21	end procedure

22	procedure MAIN
23		for ∀edge(r,s) ∈ E do
24			τr,s   τ0
25			ηr,s   1/distmat[i][j]
26		while Not End_Condition do
27			SETINITINFORMATION
28			ROUTES
29			UPDATE
30	end procedure
```
