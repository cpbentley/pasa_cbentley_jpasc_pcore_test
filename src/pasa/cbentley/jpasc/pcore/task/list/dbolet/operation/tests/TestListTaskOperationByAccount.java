/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.task.list.dbolet.operation.tests;

import java.util.List;

import com.github.davidbolet.jpascalcoin.api.model.Operation;

import pasa.cbentley.jpasc.pcore.TestPCoreAbstract;
import pasa.cbentley.jpasc.pcore.listlisteners.ListenerHolderOperation;
import pasa.cbentley.jpasc.pcore.pages.PagerOperation;
import pasa.cbentley.jpasc.pcore.task.list.dbolet.operation.ListTaskOperationByAccount;

public class TestListTaskOperationByAccount extends TestPCoreAbstract {

   public TestListTaskOperationByAccount() {
      super(false);
   }

   public void testAccount604653() {
      Integer account = 604653;
      ListenerHolderOperation holder = new ListenerHolderOperation(pc);
      ListTaskOperationByAccount task = new ListTaskOperationByAccount(pc, holder, account);
      task.runAbstract();

      List<Operation> ops = holder.getItemsCollected();

      //#debug
      toDLog().pTest(pc.toPD().dOperations(ops), null, TestListTaskOperationPending.class, "testListTaskOperationPending", LVL_05_FINE, true);

      assertEquals(17, ops.size());
   }
   
   public void testAccount604653Page() {
      Integer account = 604653;
      ListenerHolderOperation holder = new ListenerHolderOperation(pc);
      ListTaskOperationByAccount task = new ListTaskOperationByAccount(pc, holder, account);
      
      PagerOperation pager = new PagerOperation(pc);
      pager.setLookUpRangeStart(0);
      pager.setPageSize(5);
      pager.setTimingEnabled(false);
      pager.setManualExactPageSize(true);
      pager.build();
      
      task.setPager(pager);
      
      task.runAbstract();

      List<Operation> ops = holder.getItemsCollected();

      //#debug
      toDLog().pTest(pc.toPD().dOperations(ops), null, TestListTaskOperationPending.class, "testListTaskOperationPending", LVL_05_FINE, true);

      assertEquals(5, ops.size());
      
      holder = new ListenerHolderOperation(pc);
      task = new ListTaskOperationByAccount(pc, holder, account);
      task.setPager(pager);
      task.runAbstract();

      
      ops = holder.getItemsCollected();

      //#debug
      toDLog().pTest(pc.toPD().dOperations(ops), null, TestListTaskOperationPending.class, "testListTaskOperationPending", LVL_05_FINE, true);

      assertEquals(5, ops.size());
   }
}
