/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.task.list.dbolet.operation.tests;

import java.util.List;

import pasa.cbentley.jpasc.pcore.listlisteners.ListenerHolderOperation;
import pasa.cbentley.jpasc.pcore.pages.PagerOperation;
import pasa.cbentley.jpasc.pcore.rpc.model.Operation;
import pasa.cbentley.jpasc.pcore.task.list.dbolet.operation.ListTaskOperationBlock;
import pasa.cbentley.jpasc.pcore.tests.TestPCoreAbstract;

public class TestListTaskOperationBlock extends TestPCoreAbstract {


   public TestListTaskOperationBlock() {
      super(false);
   }

   
   public void testListTaskOperationBlock() {
      
      ListenerHolderOperation list = new ListenerHolderOperation(pc);
      Integer block = 333865;
      ListTaskOperationBlock task = new ListTaskOperationBlock(pc,list,block);
      PagerOperation pager = new PagerOperation(pc);
      pager.setManualExactPageSize(true);
      pager.setLookUpRangeStart(0);
      pager.setPageSize(pc.getDefaultPageSizeRootBlockOperations());
      pager.setTimingEnabled(false);
      pager.build();
      
      task.setPager(pager);
      
      task.runAbstract();
      
      List<Operation> ops = list.getItemsCollected();
      
      //#debug
      toDLog().pTest(pc.toPD().dOperations(ops), null, TestListTaskOperationBlock.class, "testListTaskOperationBlock", LVL_05_FINE, true);
      
      assertEquals(14, ops.size());
   }

}
