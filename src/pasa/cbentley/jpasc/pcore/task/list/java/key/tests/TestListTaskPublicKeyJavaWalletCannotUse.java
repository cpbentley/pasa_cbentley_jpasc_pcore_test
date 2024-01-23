package pasa.cbentley.jpasc.pcore.task.list.java.key.tests;

import java.util.List;

import pasa.cbentley.jpasc.pcore.domain.java.PublicKeyJava;
import pasa.cbentley.jpasc.pcore.listlisteners.ListenerHolder;
import pasa.cbentley.jpasc.pcore.task.list.java.key.ListTaskPublicKeyJavaWalletCannotUse;
import pasa.cbentley.jpasc.pcore.tests.TestPCoreAbstract;

public abstract class TestListTaskPublicKeyJavaWalletCannotUse  extends TestPCoreAbstract {

   public TestListTaskPublicKeyJavaWalletCannotUse() {
   }

   public void setupAbstract() {
      super.setupAbstract();
   }
   
   public void testListTaskPublicKeyJavaWalletCannotUse() {

      ListenerHolder<PublicKeyJava> holder = new ListenerHolder<>(pc);

      ListTaskPublicKeyJavaWalletCannotUse task = new ListTaskPublicKeyJavaWalletCannotUse(pc, holder);
      task.setComputeNumAccounts(true);
      task.runAbstract();

      List<PublicKeyJava> keys = holder.getItemsCollected();
      //#debug
      toDLog().pTest("testListTaskPublicKeyJavaWalletCannotUse " + pc.toPD().dPublicKeyJavas(keys), null, TestListTaskPublicKeyJavaWalletCannotUse.class, "testListTaskPublicKeyJavaWalletCannotUse", LVL_05_FINE, true);

      assertListKeys(keys);
   }

   protected abstract void assertListKeys(List<PublicKeyJava> keys);
}
