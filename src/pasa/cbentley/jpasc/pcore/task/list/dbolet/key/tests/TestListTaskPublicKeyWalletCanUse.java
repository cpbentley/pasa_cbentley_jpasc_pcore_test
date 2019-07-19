/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.task.list.dbolet.key.tests;

import java.util.List;

import com.github.davidbolet.jpascalcoin.api.model.PublicKey;

import pasa.cbentley.jpasc.pcore.TestPCoreAbstract;
import pasa.cbentley.jpasc.pcore.listlisteners.ListenerHolder;
import pasa.cbentley.jpasc.pcore.pages.PagerPublicKey;
import pasa.cbentley.jpasc.pcore.task.list.dbolet.key.ListTaskPublicKeyWalletCanUse;

/**
 * ATTENTION: SENSITIVE DATA
 * 
 * When testing this class, you override with a non source controlled class with your own wallet data file
 * 
 * @author Charles Bentley
 *
 */
public abstract class TestListTaskPublicKeyWalletCanUse extends TestPCoreAbstract {

   public TestListTaskPublicKeyWalletCanUse() {
      super(false);
   }

   public void setupAbstract() {
      super.setupAbstract();
   }

   public void testListTaskPublicKeyWalletCanUse() {

      ListenerHolder<PublicKey> holder = new ListenerHolder<>(pc);

      ListTaskPublicKeyWalletCanUse task = new ListTaskPublicKeyWalletCanUse(pc, holder);
      task.runAbstract();

      List<PublicKey> keys = holder.getItemsCollected();
      //#debug
      toDLog().pTest("testListTaskPublicKeyWalletCanUse " + pc.toPD().dPublicKeys(keys), null, TestListTaskPublicKeyWalletCanUse.class, "testListTaskPublicKeyWalletCanUse", LVL_05_FINE, true);

      assertListKeys(keys);
   }

   public void testListTaskPublicKeyWalletCanUsePage() {

      
      ListenerHolder<PublicKey> holder = new ListenerHolder<>(pc);
      ListTaskPublicKeyWalletCanUse task = new ListTaskPublicKeyWalletCanUse(pc, holder);

      PagerPublicKey pager = new PagerPublicKey(pc);
      pager.setPageSize(10);
      pager.setManualExactPageSize(true);
      pager.build();
      
      task.setPager(pager);
      task.runAbstract();

      //filtered keys that cannot be used.. this will depend on your wallet
      assertEquals(20, pager.getCountProcessed());
      assertEquals(10, pager.getCountPublished());
      assertEquals(16, pager.getCountFiltered());
      assertEquals(20, pager.getStart());
      assertEquals(10, pager.getMax());
      
      List<PublicKey> keys = holder.getItemsCollected();
      //#debug
      toDLog().pTest("testListTaskPublicKeyWalletCanUse " + pc.toPD().dPublicKeys(keys), null, TestListTaskPublicKeyWalletCanUse.class, "testListTaskPublicKeyWalletCanUse", LVL_05_FINE, true);

      assertEquals(10, keys.size());

    
      
      holder = new ListenerHolder<>(pc);
      task = new ListTaskPublicKeyWalletCanUse(pc, holder);

      task.setPager(pager);
      task.runAbstract();

      assertEquals(30, pager.getCountProcessed());
      assertEquals(20, pager.getCountPublished());
      assertEquals(25, pager.getCountFiltered());
      assertEquals(30, pager.getStart());
      assertEquals(10, pager.getMax());
      
      
      keys = holder.getItemsCollected();

      //#debug
      toDLog().pTest("testListTaskPublicKeyWalletCanUse " + pc.toPD().dPublicKeys(keys), null, TestListTaskPublicKeyWalletCanUse.class, "testListTaskPublicKeyWalletCanUse", LVL_05_FINE, true);
      
      assertEquals(10, keys.size());

      
      holder = new ListenerHolder<>(pc);
      task = new ListTaskPublicKeyWalletCanUse(pc, holder);

      task.setPager(pager);
      task.runAbstract();

      assertEquals(40, pager.getCountProcessed());
      assertEquals(30, pager.getCountPublished());
      assertEquals(31, pager.getCountFiltered());
      assertEquals(40, pager.getStart());
      assertEquals(10, pager.getMax());
      
      keys = holder.getItemsCollected();

      //#debug
      toDLog().pTest("testListTaskPublicKeyWalletCanUse " + pc.toPD().dPublicKeys(keys), null, TestListTaskPublicKeyWalletCanUse.class, "testListTaskPublicKeyWalletCanUse", LVL_05_FINE, true);
      
      assertEquals(10, keys.size());

      
      holder = new ListenerHolder<>(pc);
      task = new ListTaskPublicKeyWalletCanUse(pc, holder);

      task.setPager(pager);
      task.runAbstract();

      assertEquals(45, pager.getCountProcessed());
      assertEquals(35, pager.getCountPublished());
      assertEquals(35, pager.getCountFiltered());
      assertEquals(45, pager.getStart());
      assertEquals(10, pager.getMax());
      
      keys = holder.getItemsCollected();

      //#debug
      toDLog().pTest("testListTaskPublicKeyWalletCanUse " + pc.toPD().dPublicKeys(keys), null, TestListTaskPublicKeyWalletCanUse.class, "testListTaskPublicKeyWalletCanUse", LVL_05_FINE, true);
      
      assertEquals(5, keys.size());

      //for a total of 35 keys
      
   }

   protected abstract void assertListKeys(List<PublicKey> list);

}
