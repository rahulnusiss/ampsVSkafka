# ampsVSkafka
comparison of performance between AMPS and KAFKA

Both server running on wsl (ubuntu 22)

Results below

**Latency:**

(In milli seconds)
On average: Run 1 | 2 | 3 | 4 | 5

AMPS: 6.0 | 4.0 | 3.0 | 4.0 | 6.0

KAFKA: 12.0 | 12.0 | 13.0 | 12.0 | 22.0


**Throughput**:

each cell represents number of messages in that second.
Taking last 10 seconds excluding the last second.

*AMPS:*

Attempt    | #1 | #2 | #3 | #4 | #5 
--- | ---  | --- | --- |--- |---
Second 10   | 104671 | 101856 | 103346 | 107656 | 106858 
Second 9   | 107160 | 94722 | 104651 | 110745 | 86799 
Second 8   | 104287 | 99819 | 105053 | 109754 | 99493 
Second 7   | 106786 | 78138 | 103256 | 110488 | 105801 
Second 6   | 98934 | 75413 | 102043 | 79050 | 118060 
Second 5   | 99636 | 81525 | 103294 | 94381 | 111617 
Second 4   | 96483 | 72393 | 104219 | 97009 | 107034 
Second 3   | 97325 | 68667 | 102867 | 102272 | 111322 
Second 2   | 98015 | 73092 | 100738 | 105505 | 114787 
Second 1  | 102953 | 66250 | 105300 | 100755 | 110220 


*KAFKA:*

Attempt    | #1 | #2 | #3 | #4 | #5 
--- | ---  | --- | --- |--- |---
Second 10   | 253693 | 238246 | 234191 | 210002 | 349333 
Second 9   | 253267 | 114140 | 251094 | 165403 | 270468 
Second 8   | 258035 | 265705 | 243162 | 243579 | 205615 
Second 7   | 264576 | 237765 | 259259 | 224169 | 272369 
Second 6   | 174206 | 299523 | 247376 | 229433 | 277390 
Second 5   | 255172 | 287777 | 229699 | 216835 | 237547 
Second 4   | 230148 | 263573 | 252012 | 266244 | 233712 
Second 3   | 281418 | 240778 | 182569 | 218989 | 204401 
Second 2   | 274064 | 278175 | 220647 | 202888 | 251525 
Second 1  | 265218 | 265772 | 269409 | 196735 | 248446 



**Conclusion:**
1. In latency AMPS performs better.
2. In throughput KAFKA performs better.

Note: AMPS server is the evaluation version provided by 60east.

Install/Run amps and kafka service is in src/resource/notes.txt