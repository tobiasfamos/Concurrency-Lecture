# Evaluation Runtimes Task 1
I have modified the task slightly, as counting all the prime numbers up to 100 Million took too long. Thus I only calculated up to 10 Million. 

We can see here that the run times decrease with the Number of threads.
It also decreases up to 16 theads.  The test was run on an AppleM1 chip with 16GB Memory. 


| Number of Threads | Up To 1'000'000 | Up To 10'000'000 |
| ----------------- | --------------: | ---------------: |
| 1                 |           13632 |            13632 |
| 2                 |            9249 |           770246 |
| 4                 |            5727 |           471128 |
| 8                 |            4063 |           319843 |
| 16                |            3581 |           270367 |