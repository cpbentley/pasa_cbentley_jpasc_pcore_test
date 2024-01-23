package pasa.cbentley.jpasc.pcore.task.list.dbolet.key.tests;

import java.util.List;

import pasa.cbentley.jpasc.pcore.listlisteners.ListenerHolder;
import pasa.cbentley.jpasc.pcore.pages.PagerPublicKey;
import pasa.cbentley.jpasc.pcore.rpc.model.PublicKey;
import pasa.cbentley.jpasc.pcore.task.list.dbolet.key.ListTaskPublicKeyWalletCanUse;
import pasa.cbentley.jpasc.pcore.task.list.dbolet.key.ListTaskPublicKeyWalletCannotUse;
import pasa.cbentley.jpasc.pcore.tests.TestPCoreAbstract;

public abstract class TestListTaskPublicKeyWalletCannotUse extends TestPCoreAbstract {

   public TestListTaskPublicKeyWalletCannotUse() {
   }

   public void setupAbstract() {
      super.setupAbstract();
   }

   public void testTestListTaskPublicKeyWalletCannotUse() {

      ListenerHolder<PublicKey> holder = new ListenerHolder<>(pc);

      ListTaskPublicKeyWalletCannotUse task = new ListTaskPublicKeyWalletCannotUse(pc, holder);
      task.runAbstract();

      List<PublicKey> keys = holder.getItemsCollected();
      //#debug
      toDLog().pTest("testTestListTaskPublicKeyWalletCannotUse " + pc.toPD().dPublicKeys(keys), null, TestListTaskPublicKeyWalletCannotUse.class, "testTestListTaskPublicKeyWalletCannotUse", LVL_05_FINE, true);

      assertListKeysCannotUse(keys);
   }

   protected abstract void assertListKeysCannotUse(List<PublicKey> list);

   public void testListTaskPublicKeyWalletCannotUsePage() {

      
      ListenerHolder<PublicKey> holder = new ListenerHolder<>(pc);
      ListTaskPublicKeyWalletCannotUse task = new ListTaskPublicKeyWalletCannotUse(pc, holder);

      PagerPublicKey pager = new PagerPublicKey(pc);
      pager.setPageSize(5);
      pager.setManualExactPageSize(true);
      pager.build();
      
      task.setPager(pager);
      task.runAbstract();

      //filtered keys that cannot be used.. this will depend on your wallet
      assertEquals(30, pager.getCountProcessed());
      assertEquals(5, pager.getCountPublished());
      assertEquals(5, pager.getCountFiltered());
      assertEquals(30, pager.getStart());
      assertEquals(5, pager.getMax());
      
      List<PublicKey> keys = holder.getItemsCollected();
      //#debug
      toDLog().pTest("testListTaskPublicKeyWalletCannotUsePage " + pc.toPD().dPublicKeys(keys), null, TestListTaskPublicKeyWalletCannotUse.class, "testListTaskPublicKeyWalletCannotUsePage", LVL_05_FINE, true);

      assertEquals(5, keys.size());

    
   }
}
