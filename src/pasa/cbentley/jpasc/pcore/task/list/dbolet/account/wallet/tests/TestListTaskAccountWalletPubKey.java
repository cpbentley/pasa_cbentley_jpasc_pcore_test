package pasa.cbentley.jpasc.pcore.task.list.dbolet.account.wallet.tests;

import pasa.cbentley.jpasc.pcore.listlisteners.ListenerPrinterAccount;
import pasa.cbentley.jpasc.pcore.rpc.model.PublicKey;
import pasa.cbentley.jpasc.pcore.task.list.dbolet.account.wallet.ListTaskAccountWalletPubKey;
import pasa.cbentley.jpasc.pcore.tests.TestPCoreAbstract;

public class TestListTaskAccountWalletPubKey extends TestPCoreAbstract {


   public TestListTaskAccountWalletPubKey() {
      super(false);
   }

   public void setupAbstract() {
      super.setupAbstract();
   }

   public void testListTaskAccountWalletPubKey() {
      
      PublicKey walletPubKey = pc.getPClient().getWalletPubKey("CA022000DAA571C29C58F48CE4C92F8BFD09B211C749248904E950FC0D224776FC10D42620009108590CBBAE1463315C67D6585C1980C3E24A18E65A42BE0C81B85FB78C3B4F", null);
      ListenerPrinterAccount holder = new ListenerPrinterAccount(pc);

      ListTaskAccountWalletPubKey task = new ListTaskAccountWalletPubKey(pc,holder,walletPubKey);
      
      task.runAbstract();
   }
}
