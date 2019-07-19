package pasa.cbentley.jpasc.pcore.access.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

public class SuiteTestsAccess extends TestSuite {
   
   
   public SuiteTestsAccess() {
      
      this.addTestSuite(TestAccessAccountDBoletRPCChain.class);
   }
   
   public static Test suite() {
      SuiteTestsAccess suite = new SuiteTestsAccess();
      return suite;
   }

}
