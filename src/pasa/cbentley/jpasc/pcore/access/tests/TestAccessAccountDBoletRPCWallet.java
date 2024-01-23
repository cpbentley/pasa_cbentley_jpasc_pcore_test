/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.jpasc.pcore.access.tests;

import pasa.cbentley.jpasc.pcore.access.AccessAccountDBoletRPCWallet;
import pasa.cbentley.jpasc.pcore.rpc.model.Account;
import pasa.cbentley.jpasc.pcore.tests.TestPCoreAbstract;


/**
 * ATTENTION: SENSITIVE DATA
 * 
 * When testing this class, you override with a non source controlled class with your own wallet data file
 * 
 * @author Charles Bentley
 *
 */
public abstract class TestAccessAccountDBoletRPCWallet extends TestPCoreAbstract {

   private AccessAccountDBoletRPCWallet aa;

   public TestAccessAccountDBoletRPCWallet() {
   }

   public void setupAbstract() {
      super.setupAbstract();
      aa = new AccessAccountDBoletRPCWallet(pc);
   }

   public void testAccountWithName() {

      Account ac = aa.getAccountWithName("pasc");

      assertNotNull(ac);
      assertEquals(111444, ac.getAccount().intValue());
      assertEquals("pasc", ac.getName());

   }


}
