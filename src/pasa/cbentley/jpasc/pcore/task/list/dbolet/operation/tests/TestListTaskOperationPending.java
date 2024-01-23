/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.task.list.dbolet.operation.tests;

import java.util.List;

import pasa.cbentley.jpasc.pcore.listlisteners.ListenerHolderOperation;
import pasa.cbentley.jpasc.pcore.rpc.model.Operation;
import pasa.cbentley.jpasc.pcore.task.list.dbolet.operation.ListTaskOperationPending;
import pasa.cbentley.jpasc.pcore.tests.TestPCoreAbstract;

public class TestListTaskOperationPending extends TestPCoreAbstract {


   public TestListTaskOperationPending() {
   }

   
   public void testListTaskOperationPending() {
      ListenerHolderOperation list = new ListenerHolderOperation(pc);
      ListTaskOperationPending task = new ListTaskOperationPending(pc,list);
      task.runAbstract();
      
      List<Operation> ops = list.getItemsCollected();
      
      //#debug
      toDLog().pTest(pc.toPD().dOperations(ops), null, TestListTaskOperationPending.class, "testListTaskOperationPending", LVL_05_FINE, true);
      
      assertEquals(pc.getPClient().getPendingsCount().intValue(), ops.size());
   }

}
