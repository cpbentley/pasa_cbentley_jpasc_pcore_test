/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.access.tests;

import java.util.List;

import com.github.davidbolet.jpascalcoin.api.model.Account;

import pasa.cbentley.core.src4.logging.BaseDLogger;
import pasa.cbentley.core.src4.logging.IDLogConfig;
import pasa.cbentley.core.src4.logging.ITechLvl;
import pasa.cbentley.core.src4.logging.ITechTags;
import pasa.cbentley.jpasc.pcore.access.AccessAccountDBoletRPCChain;
import pasa.cbentley.jpasc.pcore.filter.account.FilterAccountName;
import pasa.cbentley.jpasc.pcore.listlisteners.ListenerHolderAccount;
import pasa.cbentley.jpasc.pcore.pages.PagerAccount;
import pasa.cbentley.jpasc.pcore.task.list.dbolet.account.chain.ListTaskAccountChainBalanceMinMax;
import pasa.cbentley.jpasc.pcore.tests.TestPCoreAbstract;

/**
 * For those tests we want single access 
 * 
 * @author Charles Bentley
 *
 */
public class TestAccessAccountDBoletRPCChain extends TestPCoreAbstract {

   private AccessAccountDBoletRPCChain aa;

   public TestAccessAccountDBoletRPCChain() {
      super(false);
   }

   public void setupAbstract() {
      super.setupAbstract();
      aa = new AccessAccountDBoletRPCChain(pc);
   }

   public void testAccountWithName() {

      Account ac = aa.getAccountWithName("pasc");

      assertNotNull(ac);
      assertEquals(111444, ac.getAccount().intValue());
      assertEquals("pasc", ac.getName());

   }

   public void testGetAccountsWithNameNull() {

      BaseDLogger loggerFirst = (BaseDLogger) uc.toDLog();
      IDLogConfig config = loggerFirst.getDefault().getConfig();
      config.setLevelGlobal(ITechLvl.LVL_03_FINEST);
      config.setFlagTag(ITechTags.FLAG_17_PRINT_TEST, true);
      config.setFlagTag(ITechTags.FLAG_15_PRINT_DATA, true);
      
      PagerAccount pager = new PagerAccount(pc);
      pager.setLookUpRangeStart(0); //start
      pager.setLookUpRangeEnd(200000);
      pager.setPageSize(5); //
      pager.setTimingEnabled(false);
      pager.setManualExactPageSize(true);
      pager.build();

      List<Account> accounts = aa.getAccountsWithName(pager, null);

      //#debug
      toDLog().pTest("getAccountsWithName for 'pasc' \n" + pc.toPD().dAccounts(accounts), null, TestAccessAccountDBoletRPCChain.class, "testGetAccountsWithName", LVL_05_FINE, false);

      assertNotNull(accounts);
      assertEquals(5, accounts.size());

      assertEquals(1, accounts.get(0).getAccount().intValue());
      assertEquals(2, accounts.get(1).getAccount().intValue());
      assertEquals(3, accounts.get(2).getAccount().intValue());
      assertEquals(4, accounts.get(3).getAccount().intValue());
      assertEquals(5, accounts.get(4).getAccount().intValue());

   }

   public void testGetAccountsWithNameNull2325() {

      PagerAccount pager = new PagerAccount(pc);
      pager.setLookUpRangeStart(2325);
      pager.setLookUpRangeEnd(200000);
      pager.setPageSize(5); //
      pager.setTimingEnabled(false);
      pager.setManualExactPageSize(true); //really important if you don't want to search everything
      pager.build();

      List<Account> accounts = aa.getAccountsWithName(pager, null);

      //#debug
      toDLog().pTest("testGetAccountsWithNameNull2325 \n" + pc.toPD().dAccounts(accounts), null, TestAccessAccountDBoletRPCChain.class, "testGetAccountsWithName", LVL_05_FINE, false);

      assertNotNull(accounts);
      //assertEquals(5, accounts.size());

      assertEquals(2325, accounts.get(0).getAccount().intValue());
      assertEquals(2327, accounts.get(1).getAccount().intValue());
      assertEquals(2332, accounts.get(2).getAccount().intValue());
      assertEquals(2333, accounts.get(3).getAccount().intValue());
      assertEquals(2334, accounts.get(4).getAccount().intValue());

   }

   public void testGetAccountsWithName() {

      PagerAccount pager = new PagerAccount(pc);
      pager.setLookUpRangeStart(50000); //start
      pager.setLookUpRangeEnd(200000);
      pager.setPageSize(5); //
      pager.setTimingEnabled(false);
      pager.setManualExactPageSize(true);
      pager.build();

      List<Account> ac = aa.getAccountsWithName(pager, "pasc");

      //#debug
      toDLog().pTest("getAccountsWithName for 'pasc' \n" + pc.toPD().dAccounts(ac), null, TestAccessAccountDBoletRPCChain.class, "testGetAccountsWithName", LVL_05_FINE, false);

      assertNotNull(ac);
      assertEquals(5, ac.size());

      assertEquals(54935, ac.get(0).getAccount().intValue());
      assertEquals("trumpascal", ac.get(0).getName());
      assertEquals(55505, ac.get(1).getAccount().intValue());
      assertEquals("pascal", ac.get(1).getName());
      assertEquals(57001, ac.get(2).getAccount().intValue());
      assertEquals("pascalcoin", ac.get(2).getName());
      assertEquals(67050, ac.get(3).getAccount().intValue());
      assertEquals("pascalcoin-faucetter", ac.get(3).getName());
      assertEquals(70095, ac.get(4).getAccount().intValue());
      assertEquals("pascal-hat", ac.get(4).getName());
   }

   public void testGetAccountsWithNameFilter() {

      BaseDLogger loggerFirst = (BaseDLogger) uc.toDLog();
      IDLogConfig config = loggerFirst.getDefault().getConfig();
      config.setLevelGlobal(ITechLvl.LVL_03_FINEST);
      config.setFlagTag(ITechTags.FLAG_17_PRINT_TEST, true);
      config.setFlagTag(ITechTags.FLAG_15_PRINT_DATA, true);

      PagerAccount pager = new PagerAccount(pc);
      pager.setLookUpRangeStart(54900); //start
      pager.setLookUpRangeEnd(200000);
      pager.setPageSize(3); //
      pager.setTimingEnabled(false);
      pager.setManualExactPageSize(true);
      pager.build();

      FilterAccountName filterIndexOf = new FilterAccountName(pc, "pasc");

      List<Account> ac = aa.getAccountsFilter(pager, filterIndexOf);

      //#debug
      toDLog().pTest("getAccountsFilter for 'pasc' \n" + pc.toPD().dAccounts(ac), null, TestAccessAccountDBoletRPCChain.class, "testGetAccountsWithNameFilter", LVL_05_FINE, false);

      assertNotNull(ac);
      assertEquals(3, ac.size());

      assertEquals(54935, ac.get(0).getAccount().intValue());
      assertEquals("trumpascal", ac.get(0).getName());
      assertEquals(55505, ac.get(1).getAccount().intValue());
      assertEquals("pascal", ac.get(1).getName());
      assertEquals(57001, ac.get(2).getAccount().intValue());
      assertEquals("pascalcoin", ac.get(2).getName());
      
      

   }

   /**
    * This method must return the same as testGetAccountsRangeBalance
    * 
    * but it uses the default pager
    * 
    * @throws Exception
    */
   public void testGetAccountsRangeBalanceListTask() throws Exception {
      ListenerHolderAccount list = new ListenerHolderAccount(pc);
      ListTaskAccountChainBalanceMinMax task = new ListTaskAccountChainBalanceMinMax(pc,list,100000.0,null);
      task.runAbstract();
      doTestRichest(list.getAccounts());
   }
   
   private void doTestRichest(List<Account> accountRichAll) {
      assertEquals(22, accountRichAll.size());
      //those checks are etalons.. 
      //it will have to be changed over time as the chain evolves
      int index = 0;
      assertEquals(111, accountRichAll.get(index++));
      assertEquals(200, accountRichAll.get(index++));
      assertEquals(2120, accountRichAll.get(index++));
      assertEquals(4029, accountRichAll.get(index++));
      assertEquals(4630, accountRichAll.get(index++));
      assertEquals(5559, accountRichAll.get(index++));
      assertEquals(6111, accountRichAll.get(index++));
      assertEquals(25678, accountRichAll.get(index++));
      assertEquals(65549, accountRichAll.get(index++));
      assertEquals(86646, accountRichAll.get(index++));
      assertEquals(110069, accountRichAll.get(index++));
      assertEquals(110177, accountRichAll.get(index++));
      assertEquals(129100, accountRichAll.get(index++));
      assertEquals(195655, accountRichAll.get(index++));
      assertEquals(228053, accountRichAll.get(index++));
      assertEquals(244000, accountRichAll.get(index++));
      assertEquals(273663, accountRichAll.get(index++));
      assertEquals(349491, accountRichAll.get(index++));
      assertEquals(371018, accountRichAll.get(index++));
      assertEquals(483429, accountRichAll.get(index++));
      assertEquals(819531, accountRichAll.get(index++));
      assertEquals(1477294, accountRichAll.get(index++));
   }
   
   public void testGetAccountsRangeBalance() throws Exception {
      
      //first get them all
    //we need a page that check the last account for computing the next start
      PagerAccount pagerAll = new PagerAccount(pc);
      pagerAll.setLookUpRangeStart(0); //start
      pagerAll.setPageSize(5); //
      pagerAll.setTimingEnabled(true);
      pagerAll.build();

      //minimum of 100k. the page definition will get them all from start
      List<Account> accountRichAll = aa.getAccountsRangeBalance(pagerAll, 100000.0, null);
      assertNotNull(accountRichAll);

      //#debug
      toDLog().pTest("getAccountsRangeBalance for all +100k coins accounts \n" + pc.toPD().dAccounts(accountRichAll), null, TestAccessAccountDBoletRPCChain.class, "testGetAccountsWithName", LVL_05_FINE, false);

      doTestRichest(accountRichAll);
      
      
      //test the paging relative to the 
      //we need a page that check the last account for computing the next start
      PagerAccount pager = new PagerAccount(pc);
      pager.setLookUpRangeStart(0); //start
      pager.setLookUpRangeEnd(100000);
      pager.setPageSize(5); //
      pager.setTimingEnabled(false);
      pager.setManualExactPageSize(true);
      pager.build();

      //minimum of 100k
      List<Account> accounts = aa.getAccountsRangeBalance(pager, 100000.0, null);
      assertNotNull(accounts);

      //#debug
      toDLog().pTest("getAccountsRangeBalance#1 for min 100k coins \n" + pc.toPD().dAccounts(accounts), null, TestAccessAccountDBoletRPCChain.class, "testGetAccountsWithName", LVL_05_FINE, false);

      assertEquals(5, accounts.size());
      //since bla
      assertEquals(4631, pager.getStart().intValue());

      //this might evolve with time
      assertEquals(111, accounts.get(0).getAccount().intValue());
      assertEquals(200, accounts.get(1).getAccount().intValue());
      assertEquals(2120, accounts.get(2).getAccount().intValue());
      assertEquals(4029, accounts.get(3).getAccount().intValue());
      assertEquals(4630, accounts.get(4).getAccount().intValue());

      accounts = aa.getAccountsRangeBalance(pager, 100000.0, null);

      //#debug
      toDLog().pTest("getAccountsRangeBalance#1 for min 100k coins \n" + pc.toPD().dAccounts(accounts), null, TestAccessAccountDBoletRPCChain.class, "testGetAccountsWithName", LVL_05_FINE, false);

      assertNotNull(accounts);
      assertEquals(5, accounts.size());

      //this might evolve with time
      assertEquals(5559, accounts.get(0).getAccount().intValue());
      assertEquals(6111, accounts.get(1).getAccount().intValue());
      assertEquals(25678, accounts.get(2).getAccount().intValue());
      assertEquals(65549, accounts.get(3).getAccount().intValue());
      assertEquals(86646, accounts.get(4).getAccount().intValue());

      accounts = aa.getAccountsRangeBalance(pager, 100000.0, null);

      //#debug
      toDLog().pTest("getAccountsRangeBalance#3 for min 100k coins \n" + pc.toPD().dAccounts(accounts), null, TestAccessAccountDBoletRPCChain.class, "testGetAccountsWithName", LVL_05_FINE, false);

      assertEquals(110069, accounts.get(0).getAccount().intValue());
      assertEquals(110177, accounts.get(1).getAccount().intValue());
      assertEquals(129100, accounts.get(2).getAccount().intValue());
      assertEquals(195655, accounts.get(3).getAccount().intValue());
      assertEquals(228053, accounts.get(4).getAccount().intValue());

      //#debug
      toDLog().pTest("getAccountsRangeBalance#3 for min 100k coins \n" + pc.toPD().dAccounts(accounts), null, TestAccessAccountDBoletRPCChain.class, "testGetAccountsWithName", LVL_05_FINE, false);

      assertEquals(110069, accounts.get(0).getAccount().intValue());
      assertEquals(110177, accounts.get(1).getAccount().intValue());
      assertEquals(129100, accounts.get(2).getAccount().intValue());
      assertEquals(195655, accounts.get(3).getAccount().intValue());
      assertEquals(228053, accounts.get(4).getAccount().intValue());
   }

   public void testGetAccountsRangePrice() throws Exception {

      BaseDLogger loggerFirst = (BaseDLogger) uc.toDLog();
      IDLogConfig config = loggerFirst.getDefault().getConfig();
      config.setLevelGlobal(ITechLvl.LVL_03_FINEST);
      config.setFlagTag(ITechTags.FLAG_17_PRINT_TEST, true);
      config.setFlagTag(ITechTags.FLAG_15_PRINT_DATA, true);

      //DEFINE PAGER so we get at maximum 5 results per call
      //we need a page that check the last account for computing the next start
      PagerAccount pager = new PagerAccount(pc);
      pager.setLookUpRangeStart(0); //start at the begining
      pager.setLookUpRangeEnd(100000);
      pager.setPageSize(5); //
      pager.setTimingEnabled(false);
      pager.setManualExactPageSize(true);
      pager.build();

      double priceMin = 3.0;
      double priceMax = 1000.0;
      List<Account> accounts = aa.getAccountsRangePrice(pager, priceMin, priceMax);

      //#debug
      toDLog().pTest("getAccountsRangePrice#1 for '3-1000' \n" + pc.toPD().dAccounts(accounts), null, TestAccessAccountDBoletRPCChain.class, "testGetAccountsRangePrice", LVL_05_FINE, false);

      assertNotNull(accounts);
      assertEquals(5, accounts.size());
      assertEquals(10, pager.getCountProcessed());
      assertEquals(8, pager.getCountFiltered());
      assertEquals(5, pager.getCountPublished());
      assertEquals(3, pager.getListLeftOver().size());

      assertEquals(1954, pager.getListLeftOver().get(0).getAccount().intValue());
      assertEquals(1961, pager.getListLeftOver().get(1).getAccount().intValue());
      assertEquals(1962, pager.getListLeftOver().get(2).getAccount().intValue());

      assertEquals(1924, accounts.get(0).getAccount().intValue());
      assertEquals(1936, accounts.get(1).getAccount().intValue());
      assertEquals(1937, accounts.get(2).getAccount().intValue());
      assertEquals(1950, accounts.get(3).getAccount().intValue());
      assertEquals(1953, accounts.get(4).getAccount().intValue());

      accounts = aa.getAccountsRangePrice(pager, priceMin, priceMax);

      //#debug
      toDLog().pTest("getAccountsRangePrice#2 for '3-1000' \n" + pc.toPD().dAccounts(accounts), null, TestAccessAccountDBoletRPCChain.class, "testGetAccountsRangePrice", LVL_05_FINE, false);

      assertEquals(15, pager.getCountProcessed());
      assertEquals(13, pager.getCountFiltered());
      assertEquals(10, pager.getCountPublished());
      assertEquals(3, pager.getListLeftOver().size());

      
      assertEquals(5, accounts.size());
      assertEquals(1954, accounts.get(0).getAccount().intValue());
      assertEquals(1961, accounts.get(1).getAccount().intValue());
      assertEquals(1962, accounts.get(2).getAccount().intValue());
      assertEquals(1963, accounts.get(3).getAccount().intValue());
      assertEquals(1965, accounts.get(4).getAccount().intValue());

      accounts = aa.getAccountsRangePrice(pager, priceMin, priceMax);

      //#debug
      toDLog().pTest("getAccountsRangePrice#3 for '3-1000' \n" + pc.toPD().dAccounts(accounts), null, TestAccessAccountDBoletRPCChain.class, "testGetAccountsRangePrice", LVL_05_FINE, false);
      assertEquals(5, accounts.size());
      assertEquals(1967, accounts.get(0).getAccount().intValue());
      assertEquals(1968, accounts.get(1).getAccount().intValue());
      assertEquals(1981, accounts.get(2).getAccount().intValue());
      assertEquals(2327, accounts.get(3).getAccount().intValue());
      assertEquals(2328, accounts.get(4).getAccount().intValue());

   }

   public void testGetAccountsRangeAge() throws Exception {

      BaseDLogger loggerFirst = (BaseDLogger) uc.toDLog();
      IDLogConfig config = loggerFirst.getDefault().getConfig();
      config.setLevelGlobal(ITechLvl.LVL_03_FINEST);
      config.setFlagTag(ITechTags.FLAG_17_PRINT_TEST, true);
      config.setFlagTag(ITechTags.FLAG_15_PRINT_DATA, true);

      //we need a page that check the last account for computing the next start
      PagerAccount pager = new PagerAccount(pc);
      pager.setLookUpRangeStart(0);
      pager.setLookUpRangeEnd(100000);
      pager.setPageSize(5); //
      pager.setTimingEnabled(false);
      pager.setManualExactPageSize(true);
      pager.build();

      //minimum of 100k
      int ageMin = 0;
      int ageMax = 10000;
      List<Account> accounts = aa.getAccountsRangeAge(pager, ageMin, ageMax);

      //#debug
      toDLog().pTest("getAccountsRangeAge for '0-500' \n" + pc.toPD().dAccounts(accounts), null, TestAccessAccountDBoletRPCChain.class, "testGetAccountsWithName", LVL_05_FINE, false);

      assertNotNull(accounts);
      assertEquals(5, accounts.size());

      //impossible to hard code those accounts. they change all the time.

      Integer blockReference = pc.getPClient().getBlockCount();

      int blockAge = blockReference - accounts.get(0).getUpdatedB();
      assertEquals(true, blockAge >= ageMin);
      assertEquals(true, blockAge <= ageMax);

      blockAge = blockReference - accounts.get(1).getUpdatedB();
      assertEquals(true, blockAge >= ageMin);
      assertEquals(true, blockAge <= ageMax);

   }

}
