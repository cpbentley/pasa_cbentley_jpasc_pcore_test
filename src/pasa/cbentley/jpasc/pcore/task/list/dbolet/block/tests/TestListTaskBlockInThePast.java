/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.task.list.dbolet.block.tests;

import java.util.List;

import com.github.davidbolet.jpascalcoin.api.model.Block;

import pasa.cbentley.jpasc.pcore.listlisteners.ListenerHolderBlock;
import pasa.cbentley.jpasc.pcore.task.list.dbolet.block.ListTaskBlockInThePast;
import pasa.cbentley.jpasc.pcore.tests.TestPCoreAbstract;

public class TestListTaskBlockInThePast  extends TestPCoreAbstract {


   public TestListTaskBlockInThePast() {
      super(false);
   }
   

   public void testListTaskBlockInThePast() {
      
      Integer lastMinedBlock = pc.getRPCConnection().getLastBlockMined();
      
      
      ListenerHolderBlock list = new ListenerHolderBlock(pc);
      Integer blocksInThePast = 100;
      ListTaskBlockInThePast task = new ListTaskBlockInThePast(pc,list,blocksInThePast);
      
      
      task.runAbstract();
      
      List<Block> ops = list.getItemsCollected();
      
      //#debug
      toDLog().pTest(pc.toPD().dBlocks(ops), null, TestListTaskBlockInThePast.class, "testListTaskBlockInThePast", LVL_05_FINE, true);
      
      assertEquals(100, ops.size());
   
      assertEquals(lastMinedBlock, ops.get(0).getBlock());
   }
   
}
