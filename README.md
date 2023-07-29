# AMPS VS KAFKA
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
Second 10   | 170244 | 180567 | 183590 | 155785 | 207262 
Second 9   | 166681 | 142040 | 104340 | 178145 | 218108 
Second 8   | 132417 | 141936 | 93110 | 176473 | 203620 
Second 7   | 156403 | 149112 | 132292 | 119284 | 203511 
Second 6   | 151840 | 140775 | 116942 | 134280 | 124912 
Second 5   | 135417 | 46026 | 134939 | 137462 | 85720 
Second 4   | 155624 | 125508 | 154267 | 138940 | 132664 
Second 3   | 143026 | 110198 | 171380 | 163633 | 169199 
Second 2   | 138953 | 101413 | 185453 | 168192 | 189618 
Second 1  | 144889 | 157924 | 170224 | 99784 | 220975 



**Conclusion:**
1. In latency AMPS performs better.
2. In throughput KAFKA performs better.

Note: AMPS server is the evaluation version provided by 60east.

Install/Run amps and kafka service is in src/resource/notes.txt