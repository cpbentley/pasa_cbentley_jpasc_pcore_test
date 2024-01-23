package pasa.cbentley.jpasc.pcore.tools.tests;

import java.io.File;

import pasa.cbentley.core.src4.logging.BaseDLogger;
import pasa.cbentley.core.src4.logging.IDLogConfig;
import pasa.cbentley.core.src4.logging.ITechLvl;
import pasa.cbentley.core.src4.logging.ITechTags;
import pasa.cbentley.jpasc.pcore.tests.TestPCoreAbstract;
import pasa.cbentley.jpasc.pcore.tools.PkNamesStore;

public class TestPkNamesStore extends TestPCoreAbstract {

   public TestPkNamesStore() {
   }

   private void logBasic() {
      config.setFlagTag(ITechTags.FLAG_20_PRINT_INIT, true);
   }

   public void testEmtpy() {

      logBasic();

      String fileName = "testpkstore.state";

      PkNamesStore pk = new PkNamesStore(pc, fileName);

      String name = pk.getKeyName("EncodedKey1");
      assertEquals(null, name);

      name = pk.getKeyNameAdd("EncodedKey1");
      assertEquals("key#1", name);

      name = pk.getKeyNameAdd("EncodedKey1");
      assertEquals("key#1", name);

      name = pk.getKeyName("EncodedKey1");
      assertEquals("key#1", name);

      pk.cmdExitSave();

      PkNamesStore pkRestored = new PkNamesStore(pc, fileName);

      pkRestored.initialize();

      //#debug
      toDLog().pTest("pkRestored", pkRestored, TestPkNamesStore.class, "testEmtpy", LVL_05_FINE, false);

      //automatic names are not saved
      name = pkRestored.getKeyName("EncodedKey1");
      assertEquals(null, name);

   }

   public void testSave() {
      logBasic();
      String fileName = "testpkstore.state";
      File file = new File(fileName);
      if (file.exists()) {
         file.delete();
      }
      PkNamesStore pk = new PkNamesStore(pc, fileName);
      pk.setPkName("EncodedKey1", "name1");
      pk.cmdExitSave();

      PkNamesStore pkRestored = new PkNamesStore(pc, fileName);
      pkRestored.initialize();

      String name = pk.getKeyName("EncodedKey1");
      assertEquals("name1", name);

   }
}
