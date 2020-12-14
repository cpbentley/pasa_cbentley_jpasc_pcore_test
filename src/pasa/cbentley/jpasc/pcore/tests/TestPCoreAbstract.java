/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.tests;

import java.util.ArrayList;
import java.util.List;

import pasa.cbentley.core.src4.helpers.BasicPrefs;
import pasa.cbentley.core.src4.logging.BaseDLogger;
import pasa.cbentley.core.src4.logging.IDLogConfig;
import pasa.cbentley.core.src4.logging.ITechLvl;
import pasa.cbentley.core.src4.logging.ITechTags;
import pasa.cbentley.core.src5.ctx.C5Ctx;
import pasa.cbentley.jpasc.pcore.ctx.ITechPCore;
import pasa.cbentley.jpasc.pcore.ctx.PCoreCtx;
import pasa.cbentley.jpasc.pcore.rpc.model.Account;
import pasa.cbentley.jpasc.pcore.rpc.model.Block;
import pasa.cbentley.jpasc.pcore.rpc.model.Operation;
import pasa.cbentley.testing.engine.TestCaseBentley;

public abstract class TestPCoreAbstract extends TestCaseBentley {

   protected PCoreCtx pc;

   /**
    * Log Config
    */
   protected IDLogConfig  config;

   /**
    * by default hides sysout when test is valid
    */
   public TestPCoreAbstract(boolean b) {
      super(b);
   }

   /**
    * by default hides sysout when test is valid
    */
   public TestPCoreAbstract() {
      super(true);
   }

   public void setupAbstract() {

      BaseDLogger loggerFirst = (BaseDLogger) uc.toDLog();
      config = loggerFirst.getDefault().getConfig();
      config.setLevelGlobal(ITechLvl.LVL_03_FINEST);
      config.setFlagTag(ITechTags.FLAG_17_PRINT_TEST, true);
      config.setFlagTag(ITechTags.FLAG_15_PRINT_DATA, true);

      C5Ctx c5 = new C5Ctx(uc);
      pc = new PCoreCtx(uc, c5);
      BasicPrefs prefs = new BasicPrefs(uc);
      pc.setPrefs(prefs);
      //don't auto lock wallet on connection
      prefs.putBoolean(ITechPCore.PKEY_AUTO_LOCK, false);
      pc.getRPCConnection().connectLocalhost();
   }

   public void setUpNoConnection() {
      BaseDLogger loggerFirst = (BaseDLogger) uc.toDLog();
      config = loggerFirst.getDefault().getConfig();
      config.setLevelGlobal(ITechLvl.LVL_03_FINEST);
      config.setFlagTag(ITechTags.FLAG_17_PRINT_TEST, true);
      C5Ctx c5 = new C5Ctx(uc);
      pc = new PCoreCtx(uc, c5);
   }

   /**
    * Create a list with 5 accounts [0,1,2,3,4]
    * @return
    */
   public List<Account> createListTest0_4() {
      List<Account> list = new ArrayList<>(5);
      for (int i = 0; i < 5; i++) {
         Account ac1 = new Account(pc);
         ac1.setAccount(i);
         list.add(ac1);
      }
      return list;
   }

   public void assertEquals(int val, Account account) {
      assertNotNull(account.getAccount());
      assertEquals(val, account.getAccount().intValue());
   }

   public void assertEquals(int b, Block block) {
      assertNotNull(block.getBlock());
      assertEquals(b, block.getBlock().intValue());
   }

   public void assertEquals(Operation op1, Operation op2) {
      assertNotNull(op1.getBlock());
      assertNotNull(op2.getBlock());
      assertEquals(op1.getBlock().intValue(), op1.getBlock().intValue());
      assertNotNull(op1.getOperationBlock());
      assertNotNull(op2.getOperationBlock());
      assertEquals(op1.getOperationBlock().intValue(), op1.getOperationBlock().intValue());
   }

   public void assertEquals(Account account1, Account account2) {
      assertNotNull(account1.getAccount());
      assertNotNull(account2.getAccount());
      assertEquals(account1.getAccount().intValue(), account2.getAccount().intValue());
   }

   /**
    * Create a list with 5 accounts [5,6,7,8,9]
    * @return
    */
   public List<Account> createListTest5_9() {
      List<Account> list = new ArrayList<>(5);
      for (int i = 5; i < 10; i++) {
         Account ac1 = new Account(pc);
         ac1.setAccount(i);
         list.add(ac1);
      }
      return list;
   }

   /**
    * Create a list with 5 accounts [5,6,7,8,9]
    * @return
    */
   public List<Account> createListTest15_19() {
      List<Account> list = new ArrayList<>(5);
      for (int i = 15; i < 20; i++) {
         Account ac1 = new Account(pc);
         ac1.setAccount(i);
         list.add(ac1);
      }
      return list;
   }

   /**
    * Create a list with 100 accounts [0,1,...98,99]
    * @return
    */
   public List<Account> createListTest0_99() {
      List<Account> list = new ArrayList<>(100);
      for (int i = 0; i < 100; i++) {
         Account ac1 = new Account(pc);
         ac1.setAccount(i);
         list.add(ac1);
      }
      return list;
   }
}
