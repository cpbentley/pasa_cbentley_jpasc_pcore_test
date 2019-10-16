package pasa.cbentley.jpasc.pcore.task.list.dbolet.account.wallet.tests;

import pasa.cbentley.jpasc.pcore.listlisteners.ListenerPrinterAccount;
import pasa.cbentley.jpasc.pcore.task.list.dbolet.account.wallet.ListTaskAccountWalletBalance;
import pasa.cbentley.jpasc.pcore.tests.TestPCoreAbstract;

public class TestListTaskAccountWalletBalance extends TestPCoreAbstract {

   public TestListTaskAccountWalletBalance() {
      super(false);
   }

   public void setupAbstract() {
      super.setupAbstract();
   }

   public void testListTaskAccountWalletPubKey() {
      ListenerPrinterAccount holder = new ListenerPrinterAccount(pc);
      Double minBalance = 10.0d;
      Double maxBalance = null;
      ListTaskAccountWalletBalance task = new ListTaskAccountWalletBalance(pc, holder, minBalance, maxBalance);
      task.runAbstract();
   }

}
