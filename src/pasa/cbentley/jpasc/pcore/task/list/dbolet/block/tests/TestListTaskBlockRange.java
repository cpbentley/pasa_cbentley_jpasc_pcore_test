/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.task.list.dbolet.block.tests;

import java.util.List;

import pasa.cbentley.jpasc.pcore.listlisteners.ListenerHolderBlock;
import pasa.cbentley.jpasc.pcore.pages.PagerBlock;
import pasa.cbentley.jpasc.pcore.rpc.model.Block;
import pasa.cbentley.jpasc.pcore.task.list.dbolet.block.ListTaskBlockRange;
import pasa.cbentley.jpasc.pcore.tests.TestPCoreAbstract;

public class TestListTaskBlockRange extends TestPCoreAbstract {

   public TestListTaskBlockRange() {
   }

   public void testListTaskBlockRangePageDefault() {

      ListenerHolderBlock list = new ListenerHolderBlock(pc);
      Integer blockStart = 333775;
      Integer blockEnd = 333788;
      ListTaskBlockRange task = new ListTaskBlockRange(pc, list, blockStart, blockEnd);
      task.setAscendingOrder(false);
      
      task.runAbstract();

      List<Block> ops = list.getItemsCollected();

      //#debug
      toDLog().pTest(pc.toPD().dBlocks(ops), null, TestListTaskBlockRange.class, "testListTaskBlockRangePageDefault", LVL_05_FINE, true);

      assertEquals(14, ops.size());

   }

   public void testListTaskBlockRange1BlockDefault() {

      ListenerHolderBlock list = new ListenerHolderBlock(pc);
      Integer blockStart = 333775;
      Integer blockEnd = 333775;
      ListTaskBlockRange task = new ListTaskBlockRange(pc, list, blockStart, blockEnd);

      task.runAbstract();

      List<Block> ops = list.getItemsCollected();

      //#debug
      toDLog().pTest(pc.toPD().dBlocks(ops), null, TestListTaskBlockRange.class, "testListTaskBlockRangePageDefault", LVL_05_FINE, true);

      assertEquals(1, ops.size());

   }

   public void testListTaskBlockRangeError() {

      ListenerHolderBlock list = new ListenerHolderBlock(pc);
      Integer blockStart = 333775;
      Integer blockEnd = 333770;
      ListTaskBlockRange task = new ListTaskBlockRange(pc, list, blockStart, blockEnd);

      task.runAbstract();

      List<Block> ops = list.getItemsCollected();

      //#debug
      toDLog().pTest(pc.toPD().dBlocks(ops), null, TestListTaskBlockRange.class, "testListTaskBlockRangePageDefault", LVL_05_FINE, true);
      assertEquals(0, ops.size());
   }

   public void testListTaskBlockRangePagerDescending() {

      ListenerHolderBlock list = new ListenerHolderBlock(pc);
      Integer blockStart = 333775;
      Integer blockEnd = 333788;

      ListTaskBlockRange task = new ListTaskBlockRange(pc, list, blockStart, blockEnd);
      task.setAscendingOrder(false);

      PagerBlock pager = new PagerBlock(pc);
      pager.setLookUpRangeStart(333775);
      pager.setLookUpRangeEnd(333788);
      pager.setAscending(false);
      pager.setPageSize(5); //we want max 5 blocks per page
      pager.setManualExactPageSize(true);
      pager.setTimingEnabled(false);
      pager.build();

      task.setPager(pager);

      task.runAbstract();

      List<Block> ops = list.getItemsCollected();

      //#debug
      toDLog().pTest(pc.toPD().dBlocks(ops), null, TestListTaskBlockRange.class, "testListTaskBlockRangePagerDescending", LVL_05_FINE, true);

      assertEquals(5, ops.size());
      
      assertEquals(5, pager.getCountProcessed());

      
      
      assertEquals(333788, ops.get(0));
      assertEquals(333787, ops.get(1));
      assertEquals(333786, ops.get(2));
      assertEquals(333785, ops.get(3));
      assertEquals(333784, ops.get(4));

      list = new ListenerHolderBlock(pc);
      task = new ListTaskBlockRange(pc, list, blockStart, blockEnd);
      task.setPager(pager);

      task.runAbstract();

      ops = list.getItemsCollected();

      //#debug
      toDLog().pTest(pc.toPD().dBlocks(ops), null, TestListTaskBlockRange.class, "testListTaskBlockRangePagerDescending", LVL_05_FINE, true);

      assertEquals(5, ops.size());
      assertEquals(333783, ops.get(0));
      assertEquals(333782, ops.get(1));
      assertEquals(333781, ops.get(2));
      assertEquals(333780, ops.get(3));
      assertEquals(333779, ops.get(4));

      list = new ListenerHolderBlock(pc);
      task = new ListTaskBlockRange(pc, list, blockStart, blockEnd);
      task.setPager(pager);

      task.runAbstract();

      ops = list.getItemsCollected();

      //#debug
      toDLog().pTest(pc.toPD().dBlocks(ops), null, TestListTaskBlockRange.class, "testListTaskBlockRangePagerDescending", LVL_05_FINE, true);

      assertEquals(4, ops.size());
      assertEquals(333778, ops.get(0));
      assertEquals(333777, ops.get(1));
      assertEquals(333776, ops.get(2));
      assertEquals(333775, ops.get(3));
   }

   public void testListTaskBlockRangePagerAscending() {

      ListenerHolderBlock list = new ListenerHolderBlock(pc);
      Integer blockStart = 333775;
      Integer blockEnd = 333788;

      ListTaskBlockRange task = new ListTaskBlockRange(pc, list, blockStart, blockEnd);
      task.setAscendingOrder(true);

      PagerBlock pager = new PagerBlock(pc);
      pager.setLookUpRangeStart(333775);
      pager.setLookUpRangeEnd(333788);
      pager.setAscending(true);
      pager.setPageSize(5); //we want max 5 blocks per page
      pager.setManualExactPageSize(true);
      pager.setTimingEnabled(false);
      pager.build();

      task.setPager(pager);

      task.runAbstract();

      List<Block> ops = list.getItemsCollected();

      //#debug
      toDLog().pTest(pc.toPD().dBlocks(ops), null, TestListTaskBlockRange.class, "testListTaskBlockRangePagerAscending", LVL_05_FINE, true);

      assertEquals(5, ops.size());
      
      assertEquals(5, pager.getCountProcessed());

      
      assertEquals(333775, ops.get(0));
      assertEquals(333776, ops.get(1));
      assertEquals(333777, ops.get(2));
      assertEquals(333778, ops.get(3));
      assertEquals(333779, ops.get(4));
      
      list = new ListenerHolderBlock(pc);
      task = new ListTaskBlockRange(pc, list, blockStart, blockEnd);
      task.setAscendingOrder(true);
      task.setPager(pager);

      task.runAbstract();

      ops = list.getItemsCollected();

      //#debug
      toDLog().pTest(pc.toPD().dBlocks(ops), null, TestListTaskBlockRange.class, "testListTaskBlockRangePagerAscending", LVL_05_FINE, true);

      assertEquals(5, ops.size());
      assertEquals(333780, ops.get(0));
      assertEquals(333781, ops.get(1));
      assertEquals(333782, ops.get(2));
      assertEquals(333783, ops.get(3));
      assertEquals(333784, ops.get(4));

      list = new ListenerHolderBlock(pc);
      task = new ListTaskBlockRange(pc, list, blockStart, blockEnd);
      task.setAscendingOrder(true);
      task.setPager(pager);

      task.runAbstract();

      ops = list.getItemsCollected();

      //#debug
      toDLog().pTest(pc.toPD().dBlocks(ops), null, TestListTaskBlockRange.class, "testListTaskBlockRangePagerAscending", LVL_05_FINE, true);

      assertEquals(4, ops.size());
      assertEquals(333785, ops.get(0));
      assertEquals(333786, ops.get(1));
      assertEquals(333787, ops.get(2));
      assertEquals(333788, ops.get(3));
   }
}
