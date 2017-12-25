# Bitcoin Mining Strategy Simulator 

In this project we will explore three of different malicious mining strategies for Bitcoin Mining. 
1. Majority Mining 
    - When a miner has more than 51% of the networks total hashing power, <br>
     performs a 51% attack and try to extract as much relative profit as possible. 
2. Selfish Mining
    - Performs a temporary block withholding if profitable.
    - Temporary block withholding attack is when a miner doesn't announce the block right away, <br>
    instead he tries to get ahead others by mining on top of the secret block in hopes of finding two blocks in a row.
3. Fee Sniping Mining 
    - Attempts to fork the blockchain to try stealing unusually valuable blocks when profitable. 
    - That is, when a block with an unusually large transaction fee is mined, an attacker would temporarily reject that 
    block and try to re-mine a longer fork where it keeps the large transaction fee for itself.
    
    
## Where to Start

1. The class "Mining Simulation" provides a set of tests that compare the revenue between normal behaving miners and malicious miners.
2. To run the test, you can run the class "MiningSimulation" as JUnit test.


