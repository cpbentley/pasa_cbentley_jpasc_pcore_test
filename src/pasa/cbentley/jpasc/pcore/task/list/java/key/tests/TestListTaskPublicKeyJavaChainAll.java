package pasa.cbentley.jpasc.pcore.task.list.java.key.tests;

import java.util.List;

import pasa.cbentley.core.src4.logging.BaseDLogger;
import pasa.cbentley.core.src4.logging.IDLogConfig;
import pasa.cbentley.core.src4.logging.ITechLvl;
import pasa.cbentley.core.src4.logging.ITechTags;
import pasa.cbentley.jpasc.pcore.domain.java.PublicKeyJava;
import pasa.cbentley.jpasc.pcore.listlisteners.ListenerHolder;
import pasa.cbentley.jpasc.pcore.listlisteners.ListenerPrinter;
import pasa.cbentley.jpasc.pcore.pages.PagerPublicKeyJava;
import pasa.cbentley.jpasc.pcore.task.list.java.key.ListTaskPublicKeyJavaChainAll;
import pasa.cbentley.jpasc.pcore.tests.TestPCoreAbstract;
import pasa.cbentley.jpasc.pcore.utils.PublicKeyJavaCache;

public class TestListTaskPublicKeyJavaChainAll extends TestPCoreAbstract {

   public TestListTaskPublicKeyJavaChainAll() {
      super(false);
   }

   private boolean isBigTest = false;

   public void setupAbstract() {
      super.setupAbstract();
   }

   public void testListTaskPublicKeyJavaChainAll() {

      BaseDLogger loggerFirst = (BaseDLogger) uc.toDLog();
      IDLogConfig config = loggerFirst.getDefault().getConfig();
      config.setLevelGlobal(ITechLvl.LVL_03_FINEST);
      config.setFlagTag(ITechTags.FLAG_17_PRINT_TEST, true);
      config.setFlagTag(ITechTags.FLAG_15_PRINT_DATA, true);

      PublicKeyJavaCache cache = new PublicKeyJavaCache(pc);
      ListenerHolder<PublicKeyJava> holder = new ListenerHolder<>(pc);
      ListTaskPublicKeyJavaChainAll task = new ListTaskPublicKeyJavaChainAll(pc, holder, cache);

      PagerPublicKeyJava pager = new PagerPublicKeyJava(pc);
      pager.setTimingEnabled(false);
      pager.setManualExactPageSize(true);
      pager.setPageSize(10);
      pager.build();

      task.setPager(pager);
      task.runAbstract();
      List<PublicKeyJava> keys = holder.getItemsCollected();
      //#debug
      toDLog().pTest("testListTaskPublicKeyJavaChainAll#1 " + pc.toPD().dPublicKeyJavas(keys), null, TestListTaskPublicKeyJavaChainAll.class, "testListTaskPublicKeyJavaChainAll", LVL_05_FINE, true);
      assertEquals(10, keys.size());

      holder = new ListenerHolder<>(pc);
      task = new ListTaskPublicKeyJavaChainAll(pc, holder, cache);
      task.setPager(pager);
      task.runAbstract();
      keys = holder.getItemsCollected();
      //#debug
      toDLog().pTest("testListTaskPublicKeyJavaChainAll#2 " + pc.toPD().dPublicKeyJavas(keys), null, TestListTaskPublicKeyJavaChainAll.class, "testListTaskPublicKeyJavaChainAll", LVL_05_FINE, true);
      assertEquals(10, keys.size());

      holder = new ListenerHolder<>(pc);
      task = new ListTaskPublicKeyJavaChainAll(pc, holder, cache);
      task.setPager(pager);
      task.runAbstract();
      keys = holder.getItemsCollected();
      //#debug
      toDLog().pTest("testListTaskPublicKeyJavaChainAll#3 " + pc.toPD().dPublicKeyJavas(keys), null, TestListTaskPublicKeyJavaChainAll.class, "testListTaskPublicKeyJavaChainAll", LVL_05_FINE, true);
      assertEquals(10, keys.size());

   }

   /**
    * This test is for performance checks
    */
   public void testAll() {
      //
      if(!isBigTest) {
         return;
      }
      PublicKeyJavaCache cache = new PublicKeyJavaCache(pc);
      ListenerPrinter<PublicKeyJava> holder = new ListenerPrinter<>(pc);
      ListTaskPublicKeyJavaChainAll task = new ListTaskPublicKeyJavaChainAll(pc, holder, cache);
      task.runAbstract();
   }
}
