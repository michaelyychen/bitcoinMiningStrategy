package miners;

import blockchain.*;
import java.util.ArrayList;

public class SelfishMiner extends BaseMiner implements Miner {
    private Block currentHead;
    private Block currentlyMining;
    private ArrayList<Block> temp;

    public SelfishMiner(String id, int hashRate, int connectivity) {
        super(id, hashRate, connectivity);
        temp = new ArrayList<>();
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
                temp.add(block);
            }
        }
        else{

            if (block != null && block.getHeight() + 1 >= currentlyMining.getHeight() && !temp.isEmpty()) {

                this.currentHead = temp.get(temp.size() - 1);
                this.currentlyMining = this.currentHead;
                temp.clear();

            } else if (block != null && temp.isEmpty()) {
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

    }
}
