/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.pages.tests;

import java.util.List;

import com.github.davidbolet.jpascalcoin.api.model.Account;

import pasa.cbentley.jpasc.pcore.pages.PagerAccount;
import pasa.cbentley.jpasc.pcore.tests.TestPCoreAbstract;

public class TestPagerAccount extends TestPCoreAbstract {


   public TestPagerAccount() {
      super(false);
   }

   public void setupAbstract() {
      super.setUpNoConnection();
   }

   public void testPageAccount() {

      Integer numAccounts = 100000; //assume 100k accounts on chain
      Integer pageSize = 5;
      PagerAccount page = new PagerAccount(pc, numAccounts, pageSize);
      page.setTimingEnabled(false);

      assertEquals(0, page.getCountProcessed());
      assertEquals(5, page.getEnd().intValue());
      assertEquals(5, page.getMax().intValue());
      assertEquals(0, page.getStart().intValue());

      List<Account> list0_4 = createListTest0_4();
      List<Account> listFiltered = list0_4;
      

      assertEquals(5, page.getCountProcessed());
      assertEquals(5, page.getStart().intValue());
      assertEquals(5, page.getMax().intValue());

      List<Account> list5_9 = createListTest5_9();
      listFiltered = list5_9;
      
      
      assertEquals(10, page.getCountProcessed());
      assertEquals(10, page.getStart().intValue());
      assertEquals(5, page.getMax().intValue());

      List<Account> list15_19 = createListTest15_19();
      listFiltered = list15_19;
      
      
      assertEquals(15, page.getCountProcessed());
      assertEquals(20, page.getStart().intValue());
      assertEquals(5, page.getMax().intValue());

   }

}
