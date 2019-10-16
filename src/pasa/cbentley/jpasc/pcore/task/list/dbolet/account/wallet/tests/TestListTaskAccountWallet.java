package pasa.cbentley.jpasc.pcore.task.list.dbolet.account.wallet.tests;

import pasa.cbentley.jpasc.pcore.listlisteners.ListenerPrinterAccount;
import pasa.cbentley.jpasc.pcore.task.list.dbolet.account.wallet.ListTaskAccountWallet;
import pasa.cbentley.jpasc.pcore.tests.TestPCoreAbstract;

public class TestListTaskAccountWallet extends TestPCoreAbstract {


   public TestListTaskAccountWallet() {
      super(false);
   }

   public void setupAbstract() {
      super.setupAbstract();
   }

   public void testListTaskAccountWalletPubKey() {
      ListenerPrinterAccount holder = new ListenerPrinterAccount(pc);
      ListTaskAccountWallet task = new ListTaskAccountWallet(pc,holder);
      task.runAbstract();
   }

}
