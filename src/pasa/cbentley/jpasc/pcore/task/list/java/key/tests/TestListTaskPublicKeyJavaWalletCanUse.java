package pasa.cbentley.jpasc.pcore.task.list.java.key.tests;

import java.util.List;

import pasa.cbentley.jpasc.pcore.domain.java.PublicKeyJava;
import pasa.cbentley.jpasc.pcore.listlisteners.ListenerHolder;
import pasa.cbentley.jpasc.pcore.task.list.dbolet.key.tests.TestListTaskPublicKeyWalletCanUse;
import pasa.cbentley.jpasc.pcore.task.list.java.key.ListTaskPublicKeyJavaWalletCanUse;
import pasa.cbentley.jpasc.pcore.tests.TestPCoreAbstract;

public abstract class TestListTaskPublicKeyJavaWalletCanUse extends TestPCoreAbstract {

   public TestListTaskPublicKeyJavaWalletCanUse() {
      super(false);
   }

   public void setupAbstract() {
      super.setupAbstract();
   }

   public void testListTaskPublicKeyJavaWalletCanUse() {

      ListenerHolder<PublicKeyJava> holder = new ListenerHolder<>(pc);

      ListTaskPublicKeyJavaWalletCanUse task = new ListTaskPublicKeyJavaWalletCanUse(pc, holder);
      task.setComputeNumAccounts(true);
      task.runAbstract();

      List<PublicKeyJava> keys = holder.getItemsCollected();
      //#debug
      toDLog().pTest("testListTaskPublicKeyJavaWalletCanUse " + pc.toPD().dPublicKeyJavas(keys), null, TestListTaskPublicKeyWalletCanUse.class, "testListTaskPublicKeyWalletCanUse", LVL_05_FINE, true);

      assertListKeys(keys);
   }

   protected abstract void assertListKeys(List<PublicKeyJava> keys);
}
