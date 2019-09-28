package pasa.cbentley.jpasc.pcore.task.list.dbolet.account.chain.tests;

import java.util.List;

import com.github.davidbolet.jpascalcoin.api.model.Account;

import pasa.cbentley.jpasc.pcore.listlisteners.ListenerHolder;
import pasa.cbentley.jpasc.pcore.pages.PagerAccountArray;
import pasa.cbentley.jpasc.pcore.task.list.dbolet.account.chain.ListTaskAccountChainArrayValue;
import pasa.cbentley.jpasc.pcore.tests.TestPCoreAbstract;

public class TestListTaskAccountChainArrayValue extends TestPCoreAbstract {

   public TestListTaskAccountChainArrayValue() {
      super(false);
   }

   public void setupAbstract() {
      super.setupAbstract();
   }

   public void testTestListTaskAccountChainArrayValue() {

      ListenerHolder<Account> holder = new ListenerHolder<>(pc);

      int[] array = new int[] { 10, 46, 789, 44, 87, 1111, 745, 4687, 467897, 4345, 64, 7, 98 };
      ListTaskAccountChainArrayValue task = new ListTaskAccountChainArrayValue(pc, holder, array);

      PagerAccountArray pager = new PagerAccountArray(pc);
      pager.setLookUpRangeStart(0);
      pager.setPageSize(5);
      pager.setTimingEnabled(false);
      pager.setManualExactPageSize(true);
      pager.build();

      task.setPager(pager);

      task.runAbstract();

      List<Account> ops = holder.getItemsCollected();

      assertEquals(5, ops.size());

      assertEquals(10, ops.get(0).getAccount());
      assertEquals(46, ops.get(1).getAccount());
      assertEquals(789, ops.get(2).getAccount());
      assertEquals(44, ops.get(3).getAccount());
      assertEquals(87, ops.get(4).getAccount());

      holder = new ListenerHolder<>(pc);
      task = new ListTaskAccountChainArrayValue(pc, holder, array);
      task.setPager(pager);

      task.runAbstract();

      ops = holder.getItemsCollected();

      assertEquals(5, ops.size());
      assertEquals(1111, ops.get(0).getAccount());
      assertEquals(745, ops.get(1).getAccount());
      assertEquals(4687, ops.get(2).getAccount());
      assertEquals(467897, ops.get(3).getAccount());
      assertEquals(4345, ops.get(4).getAccount());

      holder = new ListenerHolder<>(pc);
      task = new ListTaskAccountChainArrayValue(pc, holder, array);
      task.setPager(pager);

      task.runAbstract();

      ops = holder.getItemsCollected();

      assertEquals(3, ops.size());

      assertEquals(64, ops.get(0).getAccount());
      assertEquals(7, ops.get(1).getAccount());
      assertEquals(98, ops.get(2).getAccount());

   }
   
   public void testTestListTaskAccountChainArrayValuePagerDefault() {

      ListenerHolder<Account> holder = new ListenerHolder<>(pc);

      int[] array = new int[] { 10, 46, 789, 44, 87, 1111, 745, 4687, 467897, 4345, 64, 7, 98 };
      ListTaskAccountChainArrayValue task = new ListTaskAccountChainArrayValue(pc, holder, array);

      task.runAbstract();
      
      List<Account> ops = holder.getItemsCollected();
      
      assertEquals(array.length, ops.size());
      assertEquals(10, ops.get(0).getAccount());
      assertEquals(46, ops.get(1).getAccount());
      assertEquals(789, ops.get(2).getAccount());
      assertEquals(44, ops.get(3).getAccount());
      assertEquals(87, ops.get(4).getAccount());

   }
}
