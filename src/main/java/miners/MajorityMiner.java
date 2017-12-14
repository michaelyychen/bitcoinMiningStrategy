package miners;

import blockchain.*;

public class MajorityMiner extends BaseMiner implements Miner {

    private Block currentHead;
    private boolean majority = false;
    public MajorityMiner(String id, int hashRate, int connectivity) {
        super(id, hashRate, connectivity);
    }

    @Override
    public Block currentlyMiningAt() {
        return currentHead;
    }

    @Override
    public Block currentHead() {
        return currentHead;
    }

    @Override
    public void blockMined(Block block, boolean isMinerMe) {
        if(isMinerMe) {
            if (block.getHeight() > currentHead.getHeight()) {
                this.currentHead = block;
             //   System.out.println("Attacker's currently at height: " + currentHead.getHeight());
            }
        }
        else{
      //  System.out.println( "Mined:  " + block.getMinedBy()+ " Height: " + block.getHeight() + " Value: "+block.getBlockValue());
            if (currentHead == null) {
                currentHead = block;
            }else if (block != null && block.getHeight() > currentHead.getHeight() && !majority) {
                this.currentHead = block;

            }
        }
    }

    @Override
    public void initialize(Block genesis, NetworkStatistics networkStatistics) {
        this.currentHead = genesis;
    }

    @Override
    public void networkUpdate(NetworkStatistics statistics) {
        double attackerProportion = (double)this.getHashRate()/statistics.getTotalHashRate();

        if(attackerProportion >= 0.50)
            this.majority = true;
        else
            this.majority = false;


    }

}
