package pasa.cbentley.jpasc.pcore.task.list.dbolet.block.tests;

import junit.framework.Test;
import junit.framework.TestSuite;
import pasa.cbentley.jpasc.pcore.access.tests.SuiteTestsAccess;

public class SuiteTestsBlock extends TestSuite {
   

   public SuiteTestsBlock() {
      this.addTestSuite(TestListTaskBlockRange.class);
      this.addTestSuite(TestListTaskBlockInThePast.class);
   }
   
   public static Test suite() {
      SuiteTestsAccess suite = new SuiteTestsAccess();
      return suite;
   }
}
