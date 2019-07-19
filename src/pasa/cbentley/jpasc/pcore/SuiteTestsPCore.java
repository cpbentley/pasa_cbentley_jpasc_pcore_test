package pasa.cbentley.jpasc.pcore;

import junit.framework.Test;
import junit.framework.TestSuite;
import pasa.cbentley.jpasc.pcore.access.tests.SuiteTestsAccess;
import pasa.cbentley.jpasc.pcore.task.list.dbolet.block.tests.SuiteTestsBlock;

public class SuiteTestsPCore extends TestSuite {
   

   public SuiteTestsPCore() {
   }
   
   public static Test suite() {
      SuiteTestsPCore suite = new SuiteTestsPCore();
      SuiteTestsAccess suiteAccess = new SuiteTestsAccess();
      SuiteTestsBlock suiteBlock = new SuiteTestsBlock();
      suite.addTest(suiteAccess);
      suite.addTest(suiteBlock);
      return suite;
   }
}
