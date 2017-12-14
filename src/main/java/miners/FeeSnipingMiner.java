package miners;


import blockchain.*;

import java.util.ArrayList;

public class FeeSnipingMiner extends BaseMiner implements Miner {

    private Block currentHead;
    private Block currentlyMining;
    private Block feeSniping;
    private double attackerHashPower;
    private double targetBlockValue;


    public FeeSnipingMiner(String id, int hashRate, int connectivity) {
        super(id, hashRate, connectivity);

}

    @Override
    public Block currentlyMiningAt() {
        return currentlyMining;
    }

    @Override
    public Block currentHead() {
        return currentHead;
    }

    @Override
    public void blockMined(Block block, boolean isMinerMe) {
        if(isMinerMe) {

            if (block.getHeight() > currentlyMining.getHeight()) {

                this.currentlyMining = block;
                this.currentHead = block;

            }
        }
        else{

            if (currentHead == null) {

                this.currentHead = block;
                this.currentlyMining = block;

            } else if (block != null && block.getHeight() > currentlyMining.getHeight() && block.getBlockValue() > targetBlockValue) {


//                this.currentHead = block;
//                if(block.getHeight() - this.currentlyMining.getHeight() > 3)
//                    this.currentlyMining = block;
//                this.currentlyMining = block;
//                this.feeSniping = block;

                if (this.feeSniping == null || block.getHeight() - feeSniping.getHeight() > 1)  {
                    this.feeSniping = block;
                    this.currentlyMining = block;
                }

            }else if (block != null && block.getHeight() > currentlyMining.getHeight()) {

                this.currentHead = block;
                this.currentlyMining = block;

            }

        }
    }


    @Override
    public void initialize(Block genesis, NetworkStatistics networkStatistics) {

        this.currentHead = genesis;
        this.currentlyMining = genesis;

    }

    @Override
    public void networkUpdate(NetworkStatistics statistics) {

        this.attackerHashPower = (double) this.getHashRate() / statistics.getTotalHashRate();
        this.targetBlockValue = ( 5.5 / attackerHashPower ) + 5;
        System.out.println("Hashing Power: " + attackerHashPower + " Block Value: "+ targetBlockValue);

    }
}
